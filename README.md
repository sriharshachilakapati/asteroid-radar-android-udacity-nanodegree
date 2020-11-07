# Asteroid Radar

Asteroid Radar is an app to view the asteroids detected by NASA that pass near Earth, you can view all the detected asteroids in a period of time, their data (Size, velocity, distance to Earth) and if they are potentially hazardous.

## Getting Started

The app is consists of two screens: A Main screen with a list of all the detected asteroids and a Details screen that is going to display the data of that asteroid once it´s selected in the Main screen list. The main screen will also show the NASA image of the day to make the app more striking.

This kind of app is one of the most usual in the real world, what you will learn by doing this are some of the most fundamental skills you need to know to work as a professional Android developer, as fetching data from the internet, saving data to a database, and display the data in a clear, clear, compelling UI.

### Screenshots

<img src="solution/screenshots/screen_1.png" title="" alt="Screenshot 1" data-align="center">
<img src="solution/screenshots/screen_2.png" title="" alt="Screenshot 2" data-align="center">
<img src="solution/screenshots/screen_3.png" title="" alt="Screenshot 3" data-align="center">
<img src="solution/screenshots/screen_4.png" title="" alt="Screenshot 4" data-align="center">

### Dependencies

```groovy
implementation fileTree(dir: 'libs', include: ['*.jar'])
implementation "org.jetbrains.kotlin:kotlin-stdlib-jdk7:$kotlin_version"
implementation 'androidx.appcompat:appcompat:1.1.0'
implementation 'androidx.core:core-ktx:1.2.0'
implementation 'androidx.constraintlayout:constraintlayout:1.1.3'

implementation "androidx.lifecycle:lifecycle-extensions:2.2.0"

implementation "android.arch.navigation:navigation-fragment-ktx:1.0.0"
implementation "android.arch.navigation:navigation-ui-ktx:1.0.0"

// Download and parse data
implementation "com.squareup.moshi:moshi:1.8.0"
implementation "com.squareup.moshi:moshi-kotlin:1.8.0"
implementation "com.squareup.retrofit2:retrofit:2.6.2"
implementation "com.squareup.retrofit2:converter-moshi:2.5.0"
implementation 'com.squareup.retrofit2:converter-scalars:2.5.0'

// Kotlin coroutines
implementation "org.jetbrains.kotlinx:kotlinx-coroutines-core:1.3.0"
implementation "org.jetbrains.kotlinx:kotlinx-coroutines-android:1.3.0"
implementation "com.jakewharton.retrofit:retrofit2-kotlin-coroutines-adapter:0.9.2"

implementation "androidx.recyclerview:recyclerview:1.1.0"

// Image downloader
implementation 'com.squareup.picasso:picasso:2.5.2'

implementation "androidx.room:room-runtime:2.2.3"
kapt "androidx.room:room-compiler:2.2.3"

implementation "android.arch.work:work-runtime-ktx:1.0.1"

testImplementation 'junit:junit:4.12'
androidTestImplementation 'androidx.test.ext:junit:1.1.1'
androidTestImplementation 'androidx.test.espresso:espresso-core:3.2.0'
```

### Installation

To get the project running on your local machine, you need to follow these steps:

**Step 1: Clone the repo**

Use this to clone it to your local machine:

```bash
git clone https://github.com/sriharshachilakapati/asteroid-radar-android-udacity-nanodegree/
```

**Step 2: Check out the ‘master’ branch**

This branch is going to let you start working with it. The command to check out a branch would be:

```bash
git checkout master
```

**Step 3: Set the NASA API key in the local.properties file**

For this project to run, you will be needing a NASA API key. Get it from [api.nasa.gov](https://api.nasa.gov/) and add it to the local.properties file as follows.

```properties
NASA_API_KEY=<YOUR_API_KEY_HERE>
```

**Step 3: Run the project and check that it compiles correctly**

Open the project in Android Studio and click the Run ‘app’ button, check that it runs correctly and you can see the app in your device or emulator.

### Built With

To build this project you are going to use the NASA NeoWS (Near Earth Object Web Service) API, which you can find here.
https://api.nasa.gov/

Built with :love: by Sri Harsha Chilakapati as part of Udacity's Kotlin Android Developer Nanodegree programme.
