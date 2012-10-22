package me.pkg.pdm;

import android.os.CountDownTimer;
import android.os.Handler;

public class MyCount extends CountDownTimer{
    private Handler mHandler;
    public MyCount(long millisInFuture, long countDownInterval, Handler m) {
     super(millisInFuture, countDownInterval);
     mHandler = m;
     
     }

     public void onFinish() {
//da qua fai partire la nuova activity
    	 mHandler.sendEmptyMessage(0);
     }

            public void onTick(long arg0) {
                   
                   
            }
            
}
