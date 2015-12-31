<img src="https://raw.githubusercontent.com/ahmedrizwan/ChronometerPersist/master/sample/src/main/res/drawable/chronometer.png" width=150px  />
## ChronometerPersist
A helper library for keeping and maintaining the state of Chronometers.

###How to use it?
* Creating an instance of ChronometerPersist passing in SharedPreferences and a chronometer.

```java
ChronometerPersist chronometerPersist = ChronometerPersist.getInstance(chronometer, sharedPreferences);
```
* You'll get methods
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

//State recovery: call this in onResume() 
resumeState(); 
```

######Check out the sample for a working example!
The sample demonstrates how even if you stop the app - state will be recovered.

<img src="https://raw.githubusercontent.com/ahmedrizwan/ChronometerPersist/master/sample/src/main/res/drawable/chronopersist.gif" width=400px  />

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

