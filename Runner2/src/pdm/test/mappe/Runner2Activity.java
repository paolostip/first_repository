package pdm.test.mappe;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapView;
import com.google.android.maps.MyLocationOverlay;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class Runner2Activity extends MapActivity {
    /** Called when the activity is first created. */
	
	MapView mapView;
	MyLocationOverlay myLocationOverlay;
	
	private RadiusOverlay overlayTermini;
	private RadiusOverlay overlayPiazzaRepubblica;
	private RadiusOverlay overlayColosseo;
	private RadiusOverlay overlayCasaRomoloRemo;
	
	private PendingIntent mPendingTermini;
	private PendingIntent mPendingPiazzaRepubblica;
	private PendingIntent mPendingColosseo;
	private PendingIntent mPendingPCasaRomoloRemo;
	
	private GeoPoint termini, piazzaRepubblica, colosseo, casa;
	
	private ProximityBroadcast mProximityBroadcast;
	
	private LocationManager locationManager;
	
	private boolean a1=false,a2=false,a3=false,a4=false;
	
	
    
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        mapView= (MapView) findViewById(R.id.mapview); //prendere il riferimento alla MapView
        
		mapView.setClickable(true);
        mapView.setBuiltInZoomControls(true);
        mapView.setSatellite(true);
        
        
        termini = new GeoPoint(41902022, 12500882);
    	overlayTermini = new RadiusOverlay(termini, 400, Color.BLUE);
    	mapView.getOverlays().add(overlayTermini);
    	
    	piazzaRepubblica = new GeoPoint(41902622, 12495482);
    	overlayPiazzaRepubblica = new RadiusOverlay(piazzaRepubblica, 300, Color.RED);
    	mapView.getOverlays().add(overlayPiazzaRepubblica);
    	
    	colosseo = new GeoPoint(41890310, 12492410);
    	overlayColosseo = new RadiusOverlay(colosseo, 500, Color.YELLOW);
    	mapView.getOverlays().add(overlayColosseo);
    	
    	casa = new GeoPoint(41890492, 12484823);
    	overlayCasaRomoloRemo = new RadiusOverlay(casa, 450, Color.BLACK);
    	mapView.getOverlays().add(overlayCasaRomoloRemo);
    	
    	

        myLocationOverlay = new MyLocationOverlay(this, mapView);
        
        myLocationOverlay.runOnFirstFix(new Runnable(){
        	public void run(){
        		mapView.getController().animateTo(myLocationOverlay.getMyLocation());
        	}
        	
        });
        
    }
    
    @Override
    public void onResume(){
    	super.onResume();
    	myLocationOverlay.enableMyLocation();
    	
    	//imposto un Alert per l'ingresso-uscita dalle zone
    	Intent intentTermini = new Intent ("pdm.test.mappe");
    	intentTermini.putExtra("overlay", 1);
    	mPendingTermini = PendingIntent.getBroadcast(this, 1, intentTermini, PendingIntent.FLAG_CANCEL_CURRENT);
    
    	Intent intentPiazzaRepubblica = new Intent ("pdm.test.mappe");
    	intentPiazzaRepubblica.putExtra("overlay", 2);
    	mPendingPiazzaRepubblica = PendingIntent.getBroadcast(this, 2, intentPiazzaRepubblica, PendingIntent.FLAG_CANCEL_CURRENT);
    

    	Intent intentColosseo = new Intent ("pdm.test.mappe");
    	intentColosseo.putExtra("overlay", 3);
    	mPendingColosseo = PendingIntent.getBroadcast(this, 3, intentColosseo, PendingIntent.FLAG_CANCEL_CURRENT);
    
        
    	Intent intentCasaRomoloRemo = new Intent ("pdm.test.mappe");
    	intentCasaRomoloRemo.putExtra("overlay", 4);
    	mPendingPCasaRomoloRemo = PendingIntent.getBroadcast(this, 4, intentCasaRomoloRemo, PendingIntent.FLAG_CANCEL_CURRENT);
    
    
    locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
    //if(locationManager == null) Log.i("my","loc man null");
    //if(termini == null) Log.i("my","term nul");
   // if(mPendingTermini == null) Log.i("my","pending nul");
    locationManager.addProximityAlert(termini.getLatitudeE6() * 0.000001, termini.getLongitudeE6() * 0.000001,400,-1,mPendingTermini);
    
   
    locationManager.addProximityAlert(piazzaRepubblica.getLatitudeE6() * 0.000001,piazzaRepubblica.getLongitudeE6() * 0.000001,300, -1,mPendingPiazzaRepubblica);
    
    
    locationManager.addProximityAlert(colosseo.getLatitudeE6() * 0.000001,colosseo.getLongitudeE6() * 0.000001,500,-1,mPendingColosseo);
    
    
    locationManager.addProximityAlert(casa.getLatitudeE6() * 0.000001,casa.getLongitudeE6() * 0.000001,450,-1,mPendingPCasaRomoloRemo);
    
    registerReceiver(mProximityBroadcast, new IntentFilter("pdm.test.mappe"));
    
    
    
    
    } 
    
    @Override
	protected boolean isRouteDisplayed() {
		// TODO Auto-generated method stub
		return false;
	}

    @Override
    public void onPause(){
    	super.onPause();
    	myLocationOverlay.disableMyLocation();
    	
    	//elimina dalle "registrazioni" sia il broadcast receiver che il proximity alert
    	//unregisterReceiver(mProximityBroadcast);
    	locationManager.removeProximityAlert(mPendingTermini);
    	locationManager.removeProximityAlert(mPendingPiazzaRepubblica);
    	locationManager.removeProximityAlert(mPendingColosseo);
    	locationManager.removeProximityAlert(mPendingPCasaRomoloRemo);
    	
    }
    
   
    //creo una broadcast receiver come inner class
    class ProximityBroadcast extends BroadcastReceiver{ 
    	@Override
    	public void onReceive(Context arg0, Intent arg1){
    		
    		boolean stoEntrando = arg1.getBooleanExtra(LocationManager.KEY_PROXIMITY_ENTERING,true);
    		
    		int area = arg1.getIntExtra("overlay",-1);
    		
    		//ogni volta che l'utente entra nell'area cambia colore e diventa verde
    		
    		if(stoEntrando){
    			if(area == 1){
    				overlayTermini.setColor(Color.GREEN);
    				Toast.makeText(getApplicationContext(), "BENVENUTO A TERMINI", Toast.LENGTH_SHORT).show();
    				a1=true;
    			}
    			
    			if(area == 2){
    				overlayPiazzaRepubblica.setColor(Color.GREEN);
    				Toast.makeText(getApplicationContext(), "BENVENUTO A PIAZZA DELLA REPUBBLICA", Toast.LENGTH_SHORT).show();
    				a2=true;
    			}
    			
    			if(area == 3){
    				overlayColosseo.setColor(Color.GREEN);
    				Toast.makeText(getApplicationContext(), "BENVENUTO AR COLOSSEO", Toast.LENGTH_SHORT).show();
    				a3=true;
    			}
    			if(area == 4){
    				overlayCasaRomoloRemo.setColor(Color.GREEN);
    				Toast.makeText(getApplicationContext(), "BENVENUTO A CASA di romolo e remo", Toast.LENGTH_SHORT).show();
    				a4=true;
    			}
    				
    			
    		}else{
    			Toast.makeText(getApplicationContext(), "ARRIVEDERCI", Toast.LENGTH_SHORT).show();
    			if(a1)
    			overlayTermini.setColor(Color.GRAY);
    			
    			if(a2)
    			overlayPiazzaRepubblica.setColor(Color.GRAY);
    			
    			if(a3)
    			overlayColosseo.setColor(Color.GRAY);
    			
    			if(a4)
    			overlayCasaRomoloRemo.setColor(Color.GRAY);
    			
    		}
    		
			//Log.d(TAG,"Proximity Alert");
    		Toast.makeText(getApplicationContext(), "Alert di prossimità", Toast.LENGTH_LONG).show();
    		
    		
    	}
    }
    
    
    

	
}