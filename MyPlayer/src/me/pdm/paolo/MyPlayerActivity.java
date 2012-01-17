package me.pdm.paolo;


import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MyPlayerActivity extends Activity {
    /** Called when the activity is first created. */
	 MediaPlayer mp;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
       
        mp = MediaPlayer.create(MyPlayerActivity.this, R.raw.dst);
        
        Button startButton = (Button)findViewById(R.id.button1);
        startButton.setOnClickListener(new OnClickListener() {
        	

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				mp.start();
			}
        });
        
        Button startButton1 = (Button)findViewById(R.id.button2);
        startButton1.setOnClickListener(new OnClickListener(){
        	
        	public void onClick(View v){
        		mp.pause();
        	}
        });
           
        
        
    }
    @Override
    protected void onDestroy() {
	super.onDestroy();
	mp.release();  }
}