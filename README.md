
# Soul Swipe

SoulSwipe is a dating app designed to help individuals discover their soul mate. With its innovative features and user-friendly interface, SoulSwipe aims to make the process of finding a meaningful connection more enjoyable and efficient. The app allows users to create personalized profiles, swipe through potential matches, and engage in conversations with those who share mutual interest. SoulSwipe utilizes advanced algorithms and matching criteria to suggest compatible profiles, increasing the chances of finding a soul mate. By combining technology and human connection, SoulSwipe offers a platform where users can explore, connect, and potentially find a deep and lasting relationship with someone who truly resonates with their soul.


## Developed Using 👨🏻‍💻

* Android Architecture Components -Collection of libraries that help you design robust, testable, and maintainable apps.
* LiveData - Data objects that notify views when the underlying database changes.
* ViewModel - Stores UI-related data that isn't destroyed on UI changes.
* Dagger-Hilt - Standard library to incorporate Dagger dependency injection into an Android application.
* Retrofit - A type-safe HTTP client for Android and Java.
* Kotlin Coroutines - For asynchronous and more..
* Jetpack DataStore - Jetpack DataStore is a data storage solution that allows you to store key-value pairs. Basically it's a replacement for SharedPreferences.
## Folder Structure 🗂️

```
───app
│   ├───libs
│   └───src
│       ├───androidTest
│       │   └───java
│       │       └───com
│       │           └───discover
│       │               └───soulswipe
│       ├───main
│       │   ├───java
│       │   │   └───com
│       │   │       └───discover
│       │   │           └───soulswipe
│       │   │               ├───core
│       │   │               │   ├───di
│       │   │               │   ├───network
│       │   │               │   └───utils
│       │   │               ├───data
│       │   │               │   ├───model
│       │   │               │   │   ├───request
│       │   │               │   │   └───response
│       │   │               │   │       └───notes
│       │   │               │   ├───remote
│       │   │               │   └───repository
│       │   │               ├───domain
│       │   │               │   ├───repository
│       │   │               │   └───usecase
│       │   │               └───presentation
│       │   │                   ├───discover
│       │   │                   │   └───ui
│       │   │                   ├───landing
│       │   │                   │   └───ui
│       │   │                   ├───login
│       │   │                   │   ├───ui
│       │   │                   │   └───viewmodel
│       │   │                   ├───matches
│       │   │                   │   └───ui
│       │   │                   ├───notes
│       │   │                   │   ├───ui
│       │   │                   │   │   └───adapter
│       │   │                   │   └───viewmodel
│       │   │                   ├───profile
│       │   │                   │   └───ui
│       │   │                   └───splash
│       │   │                       └───ui
│       │   └───res
│       │       ├───color
│       │       ├───drawable
│       │       ├───drawable-v24
│       │       ├───layout
│       │       ├───menu
│       │       ├───mipmap-anydpi-v26
│       │       ├───mipmap-hdpi
│       │       ├───mipmap-mdpi
│       │       ├───mipmap-xhdpi
│       │       ├───mipmap-xxhdpi
│       │       ├───mipmap-xxxhdpi
│       │       ├───navigation
│       │       ├───values
│       │       └───xml
```