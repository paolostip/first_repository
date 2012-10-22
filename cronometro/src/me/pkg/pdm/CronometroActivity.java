package me.pkg.pdm;

import android.app.Activity;
import android.os.Bundle;



import android.app.Activity;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Chronometer;

public class CronometroActivity extends Activity {
    Chronometer mChronometer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.main);

        Button button;

        mChronometer = (Chronometer) findViewById(R.id.chronometer1);

        // Watch for button clicks.
        button = (Button) findViewById(R.id.button1);
        button.setOnClickListener(mStartListener);

        button = (Button) findViewById(R.id.button2);
        button.setOnClickListener(mStopListener);
        
        button = (Button) findViewById(R.id.button3);
        button.setOnClickListener(mResetListener);

        button = (Button) findViewById(R.id.button4);
        button.setOnClickListener(mSetFormatListener);

        button = (Button) findViewById(R.id.button5);
        button.setOnClickListener(mClearFormatListener);

        
        


        
    }

    View.OnClickListener mStartListener = new OnClickListener() {
        public void onClick(View v) {
        	
            mChronometer.start();
        }
    };

    View.OnClickListener mStopListener = new OnClickListener() {
        public void onClick(View v) {
            
        	mChronometer.stop();
        }
    };
    View.OnClickListener mResetListener = new OnClickListener() {
        public void onClick(View v) {
            mChronometer.setBase(SystemClock.elapsedRealtime());
        }
    };

    View.OnClickListener mSetFormatListener = new OnClickListener() {
        public void onClick(View v) {
            mChronometer.setFormat("Formatted time (%s)");
        }
    };

    View.OnClickListener mClearFormatListener = new OnClickListener() {
        public void onClick(View v) {
            mChronometer.setFormat(null);
        }
    };


   
        
    };
