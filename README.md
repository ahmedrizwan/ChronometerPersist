# ChronometerHelper
A helper class for keeping and maintaining the state of Chronometers in Android. 
Instead of using a service, it maintains the state using SharedPreferences so you don't have to worry about synchronization issues.  

#How to use?
* Create an instance of ChronometerHelper

```java
ChronometerHelper chronometerHelper = ChronometerHelper.getInstance(mChronometer,mSharedPreferences);
```

* When you want to start chronometer, call

```java
chronometerHelper.startChronometer();
```
* Pause 

```java
chronometerHelper.pauseChronometer(); 
```
* If you're using a single Button or trigger for Start/Pause, use this method to determine the state

```java
if(chronometerHelper.isRunning())
    chronometerHelper.pauseChronometer();
else
    chronometerHelper.startChronometer(); 
``` 

* Stop 

```java
chronometerHelper.stopChronometer();
```
* And when you have to resume the state, inside your onResume(), call

```java
chronometerHelper.resumeState(); 
```
