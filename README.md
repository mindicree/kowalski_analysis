# Kowalski, Analysis

## Overview
Kowalski, Analysis is a computer-vision based mobile application for classifying different species of birds. <br>
Currently, our model supports the following bird species:
  
 - Bluejay
 - Cardinal
 - Crow
 - Dove
 - Eagle
 - Flamingo
 - Falcon
 - Hummingbird
 - Magpie
 - Owl
 - Parrot
 - Robin
 - Turkey
 - Woodpecker

The model for bird classification is a Convolutional Neural Network (CNN) developed using the Python library, Tensorflow. 
Our dataset for training the model, <a href="https://drive.google.com/drive/folders/1q4sPCeBlGH_9cWTynWN5EuPelqXBUZ9f?usp=sharing" target="_blank">linked here</a>, is compiled from the Cornell Lab Internet Bird Collection + Macaulay Library, a bird collection containin. <a href="https://www.macaulaylibrary.org/the-internet-bird-collection-the-macaulay-library/" target="_blank">Here is a link to the collection.</a> Future goals for the model include improving accuracy, incorporating more species as well as subspecies, and adding bounding box functionality.

For distribution and use of the model, we have developed an Android application with an easy-to-use user interface (UI) and connected it to a Google Firebase instance, which we use to host our model. The user is allowed to load an image from the gallery into the app and the downloaded model will make a prediction. We chose to target mobile devices for its widespread usage and understandability among most of the world, and we chose Android specifically as our first platform for its easily accessible development platforms and greater worldwide marketshare. Future goals for the deployment of the model include adding more platforms, such as iOS and web apps, as well as to add camera functionality for real-time predictions.

For an in-depth look at the research and development process, as well as a demonstration of the app, please <a href="https://youtu.be/-UASz66WOuk" target="_blank"> click here for our video presentation. </a>

## Jupyter Notebooks
The Jupyter Notebook file, "model_notebook," contains the code to load a dataset, create a model, train and validate the model, and export the model to a Keras model and a TensorFlowLite model. For viewing the notebook and using it, please follow these steps:

### Google Colab Setup
1. Make sure you have Google Colab setup on your account
2. Make a copy of the Google Drive Jupyter Notebook, <a href="https://colab.research.google.com/drive/1S0-k97FXd3_dIzB7yBinpmC4Hi8LGbsr?usp=sharing"> found here </a> to your drive
3. Save a copy of the dataset to your Google Drive, or use your own dataset, <a href="https://drive.google.com/drive/folders/1q4sPCeBlGH_9cWTynWN5EuPelqXBUZ9f?usp=sharing" target="_blank">linked here</a>, or use your own
4. Open the Jupyter Notebook file using Google Colab
5. Rename the directories according to where you want to read the dataset and where you want to export the models

### Anaconda Setup
1. Install <a href="https://www.anaconda.com/"> Anaconda </a> on your local computer
2. Download the "model_notebook" notebook file to your computer.
3. <a href="https://drive.google.com/drive/folders/1q4sPCeBlGH_9cWTynWN5EuPelqXBUZ9f?usp=sharing" target="_blank">Download the dataset here</a>, or create/use your own
4. Create an Anaconda environment with the following dependencies:
- tensorflow
- matplotlib
- numpy
5. Install Jupyter Notebook for the environment (if not already installed)
6. Open Jupyter Notebook and navigate to the downloaded notebook file
7. Rename the directories according to where you want to read the dataset and where you want to export the models

NOTE: While setup instructions are kept general, initial design was done on the Windows 10 operating system and were not tested on other operating systems. Installation process and mileage may vary

## Android Application
The Android application was created in Android Studio. The source code for the application is located in the "android_application" folder. For editing and comiling the source code, please follow these steps.
1. Download the KowalskiAnalysis project folder located in the "android_application" directory
2. Install <a href="https://developer.android.com/studio/?gclid=Cj0KCQjws-OEBhCkARIsAPhOkIbq74EeGbMN8IAoSgTmpUprk1RJipgh-_X1OHlb8kzoau9nsJTfJtoaAuzFEALw_wcB&gclsrc=aw.ds"> Android Studio </a>
3. Start up Android Studio and select Import Project from the startup menu
4. Navigate to the location of the downloaded project folder and import the project
5. To run the application, create an emulation instance via Android Studio's Android Virtual Device (AVD) manager OR connect your own android device and connect it to Android Studio.

NOTE: While setup instructions are kept general, initial design was done on the Windows 10 operating system and were not tested on other operating systems. Installation process and mileage may vary

## Other Files
The "proposal_and_resume" PDF document contains the initial proposal of the project as well as information on the contributors of the project. The "research_and_review" PDF document contains a research paper and literature review on other similar projects. These documents were submitted to the Computer Vision course ITCS-4152 at the University of North Carolina at Charlotte. These documents are not required to use any of the material in the repository.

## Project Contributors
The following people are responsible for the research, development, and maitenance of the project:
- <b>Jonathan Helms</b> - Research, Data Collection
- <b>Tyler Johnson</b> - CNN Development, App Development
- <b>Cameron Nixon</b> - CNN Development, Project Management
- <b>Ansh Sawant</b> - App Develpment
- <b>Sarah Shealy</b> - Research, Data Collection
- <b>Vernard Roy Ulrich, III</b> - Research, Data Collection

All material within this repository was created by the contributors for the educational purposes of said contributors. Duplication, resubmission, or plagarism of the material is not allowed.
