[![Release](https://img.shields.io/badge/jCenter-1.1.0-brightgreen.svg)](https://bintray.com/sbrukhanda/maven/FragmentViewPager)
[![GitHub license](https://img.shields.io/badge/license-Apache%20Version%202.0-blue.svg)](https://github.com/sbrukhanda/fragmentviewpager/blob/master/LICENSE.txt)
[![Android Arsenal](https://img.shields.io/badge/Android%20Arsenal-RxRecyclerAdapter-green.svg?style=flat)](https://android-arsenal.com/details/1/2084)


<img src="https://raw.githubusercontent.com/ahmedrizwan/ChronometerPersist/master/sample/src/main/res/drawable/chronometer.png" width=150px  />

## ChronometerPersist

A helper library for keeping and maintaining the state of Chronometers.

### How to use it?
##### Creating an instance of ChronometerPersist

```kotlin
val chronometerPersist = ChronometerPersist.getInstance(
                              chronometer = chronometerView,
                              identifier = "mainChronometer", // unique identifier
                              sharedPreferences = mySharedPreferences
                         )
```
##### Control Methods
```kotlin
//Starting the chronometer
startChronometer();
//Stoping the chronometer
stopChronometer();
//Pausing the chronometer
pauseChronometer();
```
##### Status Checking Methods
```kotlin 
//Checking status
isRunning(); 
isPaused(); 
```
##### Format-Changing Method
```kotlin
//if true then chronometer's format -> HH:MM:SS otherwise MM:SS
hourFormat(boolean); 
```
##### State-Recovery Method
```kotlin
//State recovery: call this in onResume() 
resumeState(); 
```

###### Check out the sample for a working example!
The sample demonstrates how the library helps recover chronometer state even after the app stops.

<img src="https://raw.githubusercontent.com/ahmedrizwan/ChronometerPersist/master/sample/src/main/res/drawable/chronopersist.gif" width=400px  />

## Download 
Repository available on jCenter

```groovy
compile 'com.minimize.library:chronometerpersist:1.2.0'
```
*If the dependency fails to resolve, add this to your project repositories*
```groovy
repositories {
  maven {
      url  "http://dl.bintray.com/ahmedrizwan/maven" 
  }
}
```

## License 
```
Copyright 2015 Ahmed Rizwan

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
