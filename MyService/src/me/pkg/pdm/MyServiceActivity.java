package me.pkg.pdm;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MyServiceActivity extends Activity implements OnClickListener {
  private static final String TAG = "ServicesDemo";
  Button buttonStart, buttonStop;
private ComponentName service=null;
private NotificationManager mNotificationManager;
private Notification notification;

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.main);

    buttonStart = (Button) findViewById(R.id.button1);
    buttonStop = (Button) findViewById(R.id.button2);

    buttonStart.setOnClickListener(this);
    buttonStop.setOnClickListener(this);
  }

  public void onClick(View src) {
    switch (src.getId()) {
    case R.id.button1:
      Log.d(TAG, "onClick: starting srvice");
      service = startService(new Intent(this, MyService.class));
      break;
    case R.id.button2:
      Log.d(TAG, "onClick: stopping srvice");
      stopService(new Intent(this, MyService.class));
      break;
    }
  }
  
  public void onPause() {
	  //Log.i("mio", "pause");
	  //if(service != null) {
		//Log.i("mio", "if");
		  String ns = Context.NOTIFICATION_SERVICE;
	        mNotificationManager = (NotificationManager) getSystemService(ns);
	       
	       int icon = R.drawable.ic_launcher;
	       CharSequence tickerText = "Prova";
	       long when = System.currentTimeMillis();
	      

	       

	      notification = new Notification(icon, tickerText, when);
	       
	       Context context = getApplicationContext();
	       CharSequence contentTitle = "Servizio Conta Chilometri";
	       CharSequence contentText = "In esecuzione";
	       Intent notificationIntent = new Intent(this, MyServiceActivity.class);
	       PendingIntent contentIntent = PendingIntent.getActivity(this, 0, notificationIntent, 0);
	       
	  	   notification.flags |= Notification.FLAG_AUTO_CANCEL;   


	      notification.setLatestEventInfo(context, contentTitle, contentText, contentIntent);
	      mNotificationManager.notify(1, notification);
	  
	  super.onPause();
  }
}