package library.minimize.com.chronometerpersist;

import android.content.SharedPreferences;
import android.os.SystemClock;
import android.widget.Chronometer;

public class ChronometerPersist {

  private static final String KEY_TIME_PAUSED = "TimePaused";
  private static final String KEY_BASE = "TimeBase";
  private static final String KEY_STATE = "ChronometerState";
  private boolean isHourFormat = false;
  enum ChronometerState {Running, Paused, Stopped}

  public void hourFormat(boolean hourFormat) {
    isHourFormat = hourFormat;
    if (isHourFormat) {
      mChronometer.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {
        public void onChronometerTick(Chronometer c) {
          final long elapsedMillis = SystemClock.elapsedRealtime() - c.getBase();
          if (elapsedMillis > 3600000L) {
            c.setFormat("0%s");

          } else {
            c.setFormat("00:%s");
          }
        }
      });
    } else {

      mChronometer.setOnChronometerTickListener(null);
      mChronometer.setFormat("%s");
    }
  }

  Chronometer mChronometer;
  long mTimeWhenPaused;
  long mTimeBase;

  SharedPreferences sharedPreferences;

  public void pauseChronometer() {
    storeState(ChronometerState.Paused);
    saveTimeWhenPaused();
    pauseStateChronometer();
  }

  private void pauseStateChronometer() {
    mTimeWhenPaused = sharedPreferences.getLong(KEY_TIME_PAUSED + mChronometer.getId(),
        mChronometer.getBase() - SystemClock.elapsedRealtime());
    //some negative value
    mChronometer.setBase(SystemClock.elapsedRealtime() + mTimeWhenPaused);
    mChronometer.stop();
    if (isHourFormat) {
      final CharSequence text = mChronometer.getText();
      if (text.length() == 5) {
        mChronometer.setText("00:" + text);
      } else if (text.length() == 7) {
        mChronometer.setText("0" + text);
      }
    }
  }

  private void storeState(ChronometerState state) {
    sharedPreferences.edit().putInt(KEY_STATE + mChronometer.getId(), state.ordinal()).apply();
  }

  public void startChronometer() {
    storeState(ChronometerState.Running);
    saveBase();
    startStateChronometer();
  }

  private void startStateChronometer() {
    mTimeBase = sharedPreferences.getLong(KEY_BASE + mChronometer.getId(),
        SystemClock.elapsedRealtime()); //0
    mTimeWhenPaused = sharedPreferences.getLong(KEY_TIME_PAUSED + mChronometer.getId(), 0);
    mChronometer.setBase(mTimeBase + mTimeWhenPaused);
    mChronometer.start();
  }

  private ChronometerPersist() {
  }

  public static ChronometerPersist getInstance(final Chronometer chronometer,
      final SharedPreferences sharedPreferences) {
    ChronometerPersist chronometerPersist = new ChronometerPersist();
    chronometerPersist.sharedPreferences = sharedPreferences;
    chronometerPersist.mChronometer = chronometer;
    return chronometerPersist;
  }

  public void stopChronometer() {
    storeState(ChronometerState.Stopped);
    mChronometer.setBase(SystemClock.elapsedRealtime());
    mChronometer.stop();
    if (isHourFormat) mChronometer.setText("00:00:00");
    else mChronometer.setText("00:00");
    clearState();
  }

  private void clearState() {
    storeState(ChronometerState.Stopped);
    sharedPreferences.edit()
        .remove(KEY_BASE + mChronometer.getId())
        .remove(KEY_TIME_PAUSED + mChronometer.getId())
        .apply();
    mTimeWhenPaused = 0;
  }

  private void saveBase() {
    sharedPreferences.edit()
        .putLong(KEY_BASE + mChronometer.getId(), SystemClock.elapsedRealtime())
        .apply();
  }

  private void saveTimeWhenPaused() {
    sharedPreferences.edit()
        .putLong(KEY_TIME_PAUSED + mChronometer.getId(),
            mChronometer.getBase() - SystemClock.elapsedRealtime())
        .apply();
  }

  public void resumeState() {
    ChronometerState state =
        ChronometerState.values()[sharedPreferences.getInt(KEY_STATE + mChronometer.getId(),
            ChronometerState.Stopped.ordinal())];
    if (state.ordinal() == ChronometerState.Stopped.ordinal()) {
      stopChronometer();
    } else if (state.ordinal() == ChronometerState.Paused.ordinal()) {
      pauseStateChronometer();
    } else {
      startStateChronometer();
    }
  }

  public boolean isRunning() {
    return ChronometerState.values()[sharedPreferences.getInt(KEY_STATE + mChronometer.getId(),
        ChronometerState.Stopped.ordinal())] == ChronometerState.Running;
  }

  public boolean isPaused() {
    return ChronometerState.values()[sharedPreferences.getInt(KEY_STATE + mChronometer.getId(),
        ChronometerState.Stopped.ordinal())] == ChronometerState.Paused;
  }
}