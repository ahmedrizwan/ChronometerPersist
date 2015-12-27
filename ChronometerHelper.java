import android.content.SharedPreferences;
import android.os.SystemClock;
import android.widget.Chronometer;

public class ChronometerHelper {
    private static final String KEY_TIME_PAUSED = "TimePaused";
    private static final String KEY_BASE = "TimeBase";
    private static final String KEY_STATE = "ChronometerState";

    enum ChronometerState {Running, Paused, Stopped}

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
        mTimeWhenPaused = sharedPreferences.getLong(KEY_TIME_PAUSED + mChronometer.getId(), mChronometer.getBase() - SystemClock
                .elapsedRealtime
                        ());
        //some negative value
        mChronometer.setBase(SystemClock.elapsedRealtime() + mTimeWhenPaused);
        mChronometer.stop();
        CharSequence text = mChronometer.getText();
        if (text.length() == 5) {
            mChronometer.setText("00:" + text);
        } else if (text.length() == 7) {
            mChronometer.setText("0" + text);
        }
    }

    private void storeState(ChronometerState state) {
        sharedPreferences.edit().putInt(KEY_STATE + mChronometer.getId(),
                state.ordinal()).commit();
    }

    public void startChronometer() {
        storeState(ChronometerState.Running);
        saveBase();
        startStateChronometer();
    }

    private void startStateChronometer() {
        mTimeBase = sharedPreferences.getLong(KEY_BASE + mChronometer.getId(), SystemClock.elapsedRealtime()); //0
        mTimeWhenPaused = sharedPreferences.getLong(KEY_TIME_PAUSED + mChronometer.getId(), 0);
        mChronometer.setBase(mTimeBase + mTimeWhenPaused);
        mChronometer.start();
    }

    private ChronometerHelper() {
    }

    public static ChronometerHelper getInstance(Chronometer chronometer, SharedPreferences sharedPreferences) {
        ChronometerHelper chronometerHelper = new ChronometerHelper();
        chronometerHelper.sharedPreferences = sharedPreferences;
        chronometerHelper.mChronometer = chronometer;
        chronometerHelper.mChronometer.setText("00:00:00");

        chronometerHelper.mChronometer.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {
            @Override
            public void onChronometerTick(Chronometer chronometer) {
                CharSequence text = chronometer.getText();
                if (text.length() == 5) {
                    chronometer.setText("00:" + text);
                } else if (text.length() == 7) {
                    chronometer.setText("0" + text);
                }
            }
        });
        return chronometerHelper;
    }

    public void stopChronometer() {
        storeState(ChronometerState.Stopped);
        mChronometer.setBase(SystemClock.elapsedRealtime());
        mChronometer.setText("00:00:00");
        mChronometer.stop();
        clearState();
    }

    private void clearState() {
        storeState(ChronometerState.Stopped);
        sharedPreferences.edit().remove(KEY_BASE + mChronometer.getId()).remove(KEY_TIME_PAUSED + mChronometer.getId()).commit();
        mTimeWhenPaused = 0;
    }

    private void saveBase() {
        sharedPreferences.edit().putLong(KEY_BASE + mChronometer.getId(), SystemClock.elapsedRealtime())
                .commit();
    }

    private void saveTimeWhenPaused() {
        sharedPreferences.edit().putLong(KEY_TIME_PAUSED + mChronometer.getId(), mChronometer.getBase() - SystemClock
                .elapsedRealtime()).commit();
    }

    public void resumeState() {
        ChronometerState state = ChronometerState.values()[sharedPreferences.getInt(KEY_STATE + mChronometer.getId(),
                ChronometerState.Stopped.ordinal())];
        if (state.ordinal() == ChronometerState.Stopped.ordinal())
            stopChronometer();
        else if (state.ordinal() == ChronometerState.Paused.ordinal())
            pauseStateChronometer();
        else
            startStateChronometer();
    }

    public boolean isRunning() {
        return ChronometerState.values()[sharedPreferences.getInt(KEY_STATE + mChronometer, ChronometerState.Stopped.ordinal())] ==
                ChronometerState.Running;
    }

}
