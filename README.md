## ChronometerPersist
A helper class for keeping and maintaining the state of Chronometers. 
Instead of using a service, it maintains the state using SharedPreferences!  

###How to use?
* Create an instance of ChronometerPersist

```java
ChronometerPersist chronometerPersist = ChronometerPersist.getInstance(chronometer, sharedPreferences);
```

* When you want to start chronometer, call

```java
chronometerPersist.startChronometer();
```
* Pause 

```java
chronometerPersist.pauseChronometer(); 
```
* If you're using a single Button or trigger for Start/Pause, use this method to determine the state

```java
if(chronometerPersist.isRunning())
    chronometerPersist.pauseChronometer();
else
    chronometerPersist.startChronometer(); 
``` 

* Stop 

```java
chronometerPersist.stopChronometer();
```
* And when you have to resume the state, inside your onResume(), call

```java
chronometerPersist.resumeState(); 
```
