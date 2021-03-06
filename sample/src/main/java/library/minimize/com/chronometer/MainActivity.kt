package library.minimize.com.chronometer

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import library.minimize.com.chronometer.databinding.ActivityMainBinding
import library.minimize.com.chronometerpersist.ChronometerPersist

class MainActivity : AppCompatActivity() {
    private var mBinding: ActivityMainBinding? = null
    private var chronometerPersist: ChronometerPersist? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        val mBinding = mBinding ?: return

        val sharedPreferences = getSharedPreferences("ChronometerSample", Context.MODE_PRIVATE)
        chronometerPersist = ChronometerPersist.getInstance(
            chronometer = mBinding.chronometerView,
            identifier = "mainChronometer",
            sharedPreferences = sharedPreferences
        )
        val chronometerPersist = chronometerPersist ?: return
        //chronometerPersist.hourFormat(true);
        mBinding.start.setOnClickListener {
            if (chronometerPersist.isRunning) {
                mBinding.start.setText(R.string.resume)
                chronometerPersist.pauseChronometer()
            } else {
                mBinding.start.setText(R.string.pause)
                chronometerPersist.startChronometer()
            }
        }

        mBinding.checkBoxHourFormat.setOnCheckedChangeListener { _, isChecked ->
            chronometerPersist.hourFormat(
                isChecked
            )
        }

        mBinding.stop.setOnClickListener {
            mBinding.start.setText(R.string.start)
            chronometerPersist.stopChronometer()
        }
    }

    override fun onResume() {
        super.onResume()
        val chronometerPersist = chronometerPersist ?: return
        val mBinding = mBinding ?: return

        chronometerPersist.resumeState()
        if (chronometerPersist.isRunning) {
            mBinding.start.setText(R.string.pause)
        } else if (chronometerPersist.isPaused) {
            mBinding.start.setText(R.string.resume)
        }
    }
}
