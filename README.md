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
Our dataset for training the model, <a href="https://drive.google.com/drive/folders/1q4sPCeBlGH_9cWTynWN5EuPelqXBUZ9f?usp=sharing" target="_blank">linked here </a>, is compiled from the Cornell Lab Internet Bird Collection + Macaulay Library, a bird collection containin. <a href="https://www.macaulaylibrary.org/the-internet-bird-collection-the-macaulay-library/" target="_blank">Here is a link to the collection.</a> Future goals for the model include improving accuracy, incorporating more species as well as subspecies, and adding bounding box functionality.

For distribution and use of the model, we have developed an Android application with an easy-to-use user interface (UI) and connected it to a Google Firebase instance, which we use to host our model. The user is allowed to load an image from the gallery into the app and the downloaded model will make a prediction. We chose to target mobile devices for its widespread usage and understandability among most of the world, and we chose Android specifically as our first platform for its easily accessible development platforms and greater worldwide marketshare. Future goals for the deployment of the model include adding more platforms, such as iOS and web apps, as well as to add camera functionality for real-time predictions.

For an in-depth look at the research and development process, as well as a demonstration of the app, please <a href="https://youtu.be/-UASz66WOuk" target="_blank"> click here </a>.
