package library.minimize.com.chronometer;

import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import library.minimize.com.chronometer.databinding.ActivityMainBinding;
import library.minimize.com.chronometerpersist.ChronometerPersist;

public class MainActivity extends AppCompatActivity {
  ActivityMainBinding mBinding;
  ChronometerPersist chronometerPersist;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
    SharedPreferences sharedPreferences = getSharedPreferences("ChronometerSample", MODE_PRIVATE);
    chronometerPersist =
        ChronometerPersist.getInstance(mBinding.chronometerView, sharedPreferences);
    //chronometerPersist.hourFormat(true);
    mBinding.start.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        if (chronometerPersist.isRunning()) {
          mBinding.start.setText(R.string.resume);
          chronometerPersist.pauseChronometer();
        } else {
          mBinding.start.setText(R.string.pause);
          chronometerPersist.startChronometer();
        }
      }
    });

    mBinding.stop.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        mBinding.start.setText(R.string.start);
        chronometerPersist.stopChronometer();
      }
    });
  }

  @Override protected void onResume() {
    super.onResume();
    chronometerPersist.resumeState();
    if (chronometerPersist.isRunning()) {
      mBinding.start.setText(R.string.pause);
    } else if (chronometerPersist.isPaused()) {
      mBinding.start.setText(R.string.resume);
    }
  }
}
