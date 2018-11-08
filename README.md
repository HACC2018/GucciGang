<a href="https://github.com/HACC2018/GucciGang"><img src="images/ainaCoverImage.png" alt="Aina Cover Photo" /></a>

<hr>

<h3 align="center" id="developedbygucciganghttpsgithubcomsaehyunsgithelper">
  Developed by: <a href="https://github.com/HACC2018/GucciGang">Gucci Gang</a>
</h3>
<h3 align="center"> 
  Check out the: <a href="https://devpost.com/software/guccigang">DevPost</a>
</h3>

# Table of Contents:
* [Aina Vision](#our-aina-vision)
* [Prerequisites](#prerequisites)
* [Setting up the Application](#setting-up-the-application)
* [Much Mahalos](#much-mahalos)
* [Our Ohana](#our-ohana)

# Our Aina Vision:
Our vision is to increase the percentage of threatened and endangered native species managed in Hawai'i by 2030 through a focus on sustainability goals demonstrated in the Aloha+ Challenge Dashboard. As students of the University of Hawai'i, we aim to ultimately provide sustainable solutions that are appropriately matched with technologies and platforms learned throughout our education career. We hope to give back to our Aina as much as it has given us.

## Our Challenge:

* No surefire way to identify plants out in the field
* No way to keep track of plants out in the field
* No way to cross reference plants
* No way to confirm data on all plants
* No sustainable way to pass down information to newcomers or the public

## Our Solution:

* A way to identify plants using machine learning software
* A scalable database and data hosting system to keep track of plant sightings
* User image submissions, allowing the user to report back to the DLNR with information such as plant appearance and sighting location
* Fast database searching, empowering users to quickly cross-reference plants with pictures already in our database
* Compressed machine learning models and images in order to allow for a small, portable application size

# Prerequisites:

## Linux:
A basic understanding of Linux is highly recommended as we will be using the terminal / commandline. To learn more about linux and its commands click [here](https://maker.pro/linux/tutorial/basic-linux-commands-for-beginners).

## Python:
Python is an interpreted, object-oriented, high-level programming language with dynamic semantics. It is utilized in conjunction with TensorflowLite to train a model to identify the plants. Click [here](https://www.python.org/downloads/) to install the most recent version of Python.

## TensorFlowLite & Pillow:
After installing Python, the next thing you would want to install is TensorFlowLite using PIP that comes with the Python installation. TensorFlow Lite is the official solution for running machine learning models on mobile and embedded devices. It enables on‑device machine learning inference with low latency and a small binary size on Android, iOS, and other operating 
systems. To learn more click [here](https://www.tensorflow.org/lite/).

Pillow is the friendly PIL fork by [Alex Clark and Contributors](https://github.com/python-pillow/Pillow/graphs/contributors). PIL is the Python Imaging Library by Fredrik Lundh and Contributors.

In the terminal / commandline: 
```
pip install tensorflow
pip install PILLOW
```

## Android Studio:
After installing Tensorflow, the next thing you need to install is Android Studio. Android Studio is the official [Integrated Development Environment (IDE)](https://searchsoftwarequality.techtarget.com/definition/integrated-development-environment) for [Android app](https://en.wikipedia.org/wiki/Android_(operating_system)) development, based on [IntelliJ IDEA](https://www.jetbrains.com/idea/). Android Studio will be used as our main working environment. To learn more and download, click [here](https://developer.android.com/studio/).

## Clone the Repository:
Clone the repository by doing git clone in the terminal / commandline:
```
git clone https://github.com/brendtmcfeeley/GucciGang.git
```

# Setting up the Application:
Open AndroidStudio. After it loads select "Open an existing Android Studio project" from this popup:

<p align="center">
  <img src="images/android1.png?raw=true" alt="Andrew"/>
</p>

In the file selector, choose GucciGang/android/tflite from your working directory.

You will get a "Gradle Sync" popup, the first time you open the project, asking about using gradle wrapper. Click "OK".

<p align="center">
  <img src="images/android2.png?raw=true" alt="Andrew"/>
</p>

To set up an emulator or a hardware device to run your app, click [here](https://developer.android.com/studio/run/) for more information.

## Interface:

The app will open the camera as soon as it's loaded and will be running the model in real time! You can also choose to search plants that are in the database!

<p align="center">
  <img src="images/exImg3.jpg?raw=true" alt="Andrew"/>
  <img src="images/exImg2.jpg?raw=true" alt="Andrew"/>
</p>

After selecting an item from the list you should see something like this!

<p align="center">
  <img src="images/exImg1.jpg?raw=true" alt="Andrew"/>
  <img src="images/exImg4.jpg?raw=true" alt="Andrew"/>
</p>

You can then choose to press the Report Sighting button to report that plant to the agency or upload your own image of an unidentified plant to the database for the agencies to see what you've found!

# Much Mahalos:

## HACC:
![HACC IMAGE](http://hacc.hawaii.gov/wp-content/uploads/2017/08/HACC-with-Flag-final.png)

We would like to thank [HACC](http://hacc.hawaii.gov/) for the opportunity to help contribute solutions to challenges focusing on social, economic, and environemntal factors that are faced today. This was an engaging experience with the community in trying to modernize state functions, services, and supporting the IT workforce development.

## Sponsors:
We would also like to thank the sponsors, partners, and presenters for making such an event possible for not just us but for everyone else who had been involved with this competition!

![Sponsors Image](images/sponsorsImg.png)
![Partners Image](images/partnersImg.png)

# Our Ohana:
This project would not have been possible without each every one of our amazing and talented teammates!

<h2 align="center" id="">Team Lead:</h2>

<p align="center">
  <img width="250px" height="250px" src="images/brendt.png?raw=true" alt="Andrew"/>
</p>
<h3 align="center" id="brendtmcfeeleylinkedinhttpswwwlinkedincominbrendtmcfeeleygithubhttpsgithubcombrendtmcfeeley">Brendt Mcfeeley | <a href="https://www.linkedin.com/in/brendt-mcfeeley/">LinkedIn</a> | <a href="https://github.com/brendtmcfeeley">GitHub</a></h3>


<h2 align="center" id="">User Interface / User Experience Developers:</h2>

<p align="center">
  <img width="250px" height="250px" src="images/kenneth.png?raw=true" alt="Andrew"/>
  <img width="250px" height="250px" src="images/andrew.png?raw=true" alt="Andrew"/>
  <h3 align="center" id="kennethlauritzenlinkedinhttpslinkedincominkennethlauritzen49907216agithub">
    Kenneth Lauritzen: <a href="https://linkedin.com/in/kenneth-lauritzen-49907216a/">LinkedIn</a> | <a href="https://github.com/klauritz">GitHub</a>  
    || Andrew Obatake: <a href="https://www.linkedin.com/in/andrew-obatake-8a5232106/">LinkedIn</a> | <a href="https://github.com/aobatake">GitHub</a>
  </h3>
</p>

<h2 align="center" id="">Machine Learning / Backend Developers:</h2>

<p align="center">
  <img width="250px" height="250px" src="images/sae.png?raw=true" alt="Andrew"/>
  <img width="250px" height="250px" src="images/kian.png?raw=true" alt="Andrew"/>
  <h3 align="center" id="saehyunsonglinkedinhttpswwwlinkedincominsaehyunsonggithubhttpsgithubcomsaehyuns">
    Sae Hyun Song: <a href="https://www.linkedin.com/in/sae-hyun-song/">LinkedIn</a> | <a href="https://github.com/saehyuns">GitHub</a>
    || Kian Kobayashi: <a href="https://www.linkedin.com/in/kiankobayashi/">LinkedIn</a> | <a href="https://github.com/kiankoba">GitHub</a>
  </h3>
</p>
