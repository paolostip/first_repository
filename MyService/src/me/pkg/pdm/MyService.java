package me.pkg.pdm;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

public class MyService extends Service {
	private static final String TAG = "MyService";
	MediaPlayer player;
	 public NotificationManager mNotificationManager;
	 public Notification notification;
	 private static final int HELLO_ID = 1;
	 CountDownTimer m;
	
	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}
	
	@Override
	public void onCreate() {
		Toast.makeText(this, "My Service Created", Toast.LENGTH_LONG).show();
		Log.d(TAG, "onCreate");
		
		player = MediaPlayer.create(this, R.raw.dst);
		player.setLooping(false); // Set looping
		
		
		
	     //MyCount m = new MyCount(10000,10000,handler);
	      //m.start();
	      
	    //notifica
	        String ns = Context.NOTIFICATION_SERVICE;
	        mNotificationManager = (NotificationManager) getSystemService(ns);
	       
	       int icon = R.drawable.ic_launcher;
	       CharSequence tickerText = "Hai vinto!";
	       long when = System.currentTimeMillis();
	      

	       

	       notification = new Notification(icon, tickerText, when);
	       
	       Context context = getApplicationContext();
	       CharSequence contentTitle = "notifica Conta Chilometri";
	       CharSequence contentText = "Hai vinto!";
	       Intent notificationIntent = new Intent(this, MyServiceActivity.class);
	       PendingIntent contentIntent = PendingIntent.getActivity(this, 0, notificationIntent, 0);
	       
	      

	      notification.setLatestEventInfo(context, contentTitle, contentText, contentIntent);
	      
	      
	  	
	      
	        //permette alla notifica di sparire quando viene riaperta l'app
	  	   notification.flags |= Notification.FLAG_AUTO_CANCEL;   
	  	   
	  	    //vibrazione all'arrivo della notifica 
	        notification.defaults |=Notification.DEFAULT_VIBRATE;
			
	        notification.flags |= Notification.FLAG_ONGOING_EVENT;
		
		
		
        
	
	}
	
	
	
	

	@Override
	public void onDestroy() {
		Toast.makeText(this, "My Service Stopped", Toast.LENGTH_LONG).show();
		Log.d(TAG, "onDestroy");
		player.stop();
	}
	
	
	public void onPause(){
		}
	
	
	
	
	
   /* public void onResume() {
        super.onResume();
        startNotification();
    }*/
	
	@Override
	public void onStart(Intent intent, int startid) {
		Toast.makeText(this, "My Service Started", Toast.LENGTH_LONG).show();
		Log.d(TAG, "onStart");
		player.start();
	}
	
	private Handler handler = new Handler() {

    	@Override

    	public void handleMessage(Message msg) {

    	super.handleMessage(msg);

    	mNotificationManager.notify(HELLO_ID, notification);

    	}

    	};
}