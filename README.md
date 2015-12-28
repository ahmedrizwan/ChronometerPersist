## ChronometerPersist
A helper library for keeping and maintaining the state of Chronometers. 
Instead of using a service, it maintains the state using SharedPreferences!  

###How to use?
* Create an instance of ChronometerPersist

```java
ChronometerPersist chronometerPersist = ChronometerPersist.getInstance(chronometer, sharedPreferences);
```
* Available methods
```java
//Starting the chronometer
startChronometer();
//Stoping the chronometer
stopChronometer();
//Pausing the chronometer
pauseChronometer();

//Change format : if true then chronometer's format -> HH:MM:SS otherwise MM:SS
hourFormat(boolean); 

//Checking status
isRunning(); 
isPaused(); 

//Status recovery: call this in onResume() and chronometer will recover state
resumeState(); 
```

Check out the sample for a working example!

##Download 
Repository available on jCenter

```Gradle
compile 'com.minimize.library:chronometerpersist:1.0.0'
```
*If the dependency fails to resolve, add this to your project repositories*
```Gradle
repositories {
  maven {
      url  "http://dl.bintray.com/ahmedrizwan/maven" 
  }
}
```

##License 
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

