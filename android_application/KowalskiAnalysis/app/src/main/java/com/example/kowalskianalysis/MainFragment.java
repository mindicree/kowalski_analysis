package com.example.kowalskianalysis;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.ml.modeldownloader.CustomModel;
import com.google.firebase.ml.modeldownloader.CustomModelDownloadConditions;
import com.google.firebase.ml.modeldownloader.DownloadType;
import com.google.firebase.ml.modeldownloader.FirebaseModelDownloader;

import org.tensorflow.lite.Interpreter;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import static android.app.Activity.RESULT_OK;

public class MainFragment extends Fragment {
    //Debug Tag
    public static final String TAG = "Kowalski";

    //UI Elements
    ImageView imageView;
    TextView textView;
    Button buttonAdd, buttonIdentify;

    //Gallery stuff
    private static final int PICK_IMAGE = 100;
    Uri imageUri;

    //ML stuff
    Interpreter interpreter;
    String[] labels =
            {
                    "Bluejay",
                    "Cardinal",
                    "Crow",
                    "Dove",
                    "Eagle",
                    "Falcon",
                    "Flamingo",
                    "Hummingbird",
                    "Magpie",
                    "Ostrich",
                    "Owl",
                    "Parrot",
                    "Robin",
                    "Turkey",
                    "Woodpecker"
            };
    /*String[] labels =
            {
                    "Bluejay",
                    "Cardinal",
                    "Crow",
                    "Hummingbird",
                    "Robin"
            };*/

    public MainFragment() {
        // Required empty public constructor
    }

    public static MainFragment newInstance() {
        MainFragment fragment = new MainFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        //UI Initializations
        imageView = view.findViewById(R.id.imageViewImage);
        textView = view.findViewById(R.id.textViewIdentify);
        buttonAdd = view.findViewById(R.id.buttonAddPhoto);
        buttonIdentify = view.findViewById(R.id.buttonIdentifyPhoto);
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
                startActivityForResult(gallery, PICK_IMAGE);
            }
        });
        buttonIdentify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                beginClassification(imageView);
            }
        });


        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == PICK_IMAGE){
            imageUri = data.getData();
            imageView.setImageURI(imageUri);
            textView.setText("Loading Model...");
            CustomModelDownloadConditions conditions = new CustomModelDownloadConditions.Builder()
                    .requireWifi()
                    .build();
            FirebaseModelDownloader.getInstance()
                    .getModel("avg5709med6133dim256", DownloadType.LOCAL_MODEL, conditions)
                    .addOnSuccessListener(new OnSuccessListener<CustomModel>() {
                        @Override
                        public void onSuccess(CustomModel model) {
                            // Download complete. Depending on your app, you could enable
                            // the ML feature, or switch from the local model to the remote
                            // model, etc.
                            File modelFile = model.getFile();
                            if (modelFile != null) {
                                interpreter = new Interpreter(modelFile);
                                buttonIdentify.setEnabled(true);
                                textView.setText("Ready");
                            }
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
        }
    }

    public void beginClassification(ImageView image) {
        int imgDim = 256;
        //TODO begin classification process
        /*After you have determined the format of your model's input and output, get your input data
            and perform any transformations on the data that are necessary to get an input of the right shape for your model.
            For example, if you have an image classification model with an input shape of [1 224 224 3]
            floating-point values, you could generate an input ByteBuffer from a Bitmap object as
            shown in the following example:*/
        Bitmap bitmap = Bitmap.createScaledBitmap(((BitmapDrawable)image.getDrawable()).getBitmap(), imgDim, imgDim, true);
        ByteBuffer input = ByteBuffer.allocateDirect(1 * imgDim * imgDim * 3 * 4).order(ByteOrder.nativeOrder());
        Log.d(TAG, "Input Size: " + (1*imgDim*imgDim*3*4));
        for (int y = 0; y < imgDim; y++) {
            for (int x = 0; x < imgDim; x++) {
                int px = bitmap.getPixel(x, y);

                // Get channel values from the pixel value.
                float r = Color.red(px);
                float g = Color.green(px);
                float b = Color.blue(px);

                // Normalize channel values to [-1.0, 1.0]. This requirement depends
                // on the model. For example, some models might require values to be
                // normalized to the range [0.0, 1.0] instead.
                //float rf = r / 255.0f;
                //float gf = g / 255.0f;
                //float bf = b / 255.0f;

                input.putFloat(r);
                input.putFloat(g);
                input.putFloat(b);
            }
        }

            /*Then, allocate a ByteBuffer large enough to contain the model's output and pass the
              input buffer and output buffer to the TensorFlow Lite interpreter's run() method.
              For example, for an output shape of [1 1000] floating-point values:*/
        int bufferSize = 15 * (java.lang.Float.SIZE / java.lang.Byte.SIZE);
        Log.d(TAG, "beginClassification: buffer size: " + bufferSize);
        Log.d(TAG, "Buffer Size: " + bufferSize);
        ByteBuffer modelOutput = ByteBuffer.allocateDirect(bufferSize).order(ByteOrder.nativeOrder());
        interpreter.run(input, modelOutput);

            /*How you use the output depends on the model you are using. For example, if you are
            performing classification, as a next step, you might map the indexes of the result
            to the labels they represent:*/
        modelOutput.rewind();
        FloatBuffer probabilities = modelOutput.asFloatBuffer();
        String prediction = "";
        double max = Integer.MIN_VALUE;

        for (int i = 0; i < probabilities.capacity(); i++) {
            //Log.d(TAG, "beginClassification: For loop; iteration-" + i);
            String label = labels[i];
            float probability = probabilities.get(i);
            Log.d(TAG, String.format("%s: %1.8f", label, probability));
            if (probability > max) {
                prediction = label;
                max = probability;
                Log.d(TAG, "beginClassification: New prediction: " + label);
            }
        }
        /*try {
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(getActivity().getAssets().open("custom_labels.txt")));
            for (int i = 0; i < probabilities.capacity(); i++) {
                String label = reader.readLine();
                float probability = probabilities.get(i);
                Log.d(TAG, String.format("%s: %1.4f", label, probability));
            }
        } catch (IOException e) {
            // File not found?
            Toast.makeText(getContext(), "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
            Log.d(TAG, "beginClassification: Entered IOException Catch");
        }*/

        textView.setText("Prediction: " + prediction + "\nProbability: " + Double.toString(max*100).substring(0, 5) + "%");
    }
}