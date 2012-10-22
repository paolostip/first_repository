package pdm.test.mappe;

import com.google.android.maps.MapActivity;
import com.google.android.maps.MapView;
import com.google.android.maps.MyLocationOverlay;

import android.app.Activity;
import android.os.Bundle;

public class RunnerActivity extends MapActivity {
	
	private MyLocationOverlay myLocationOverlay;
	MapView mapView;    //variabili di istanza
	
	
    /** Called when the activity is first created. */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        mapView= (MapView) findViewById(R.id.mapview);
        
         mapView.setClickable(true);                 //abilita il pan(trascinamento mappa)
         mapView.setBuiltInZoomControls(true);      //abilita lo zoom
         mapView.setSatellite(true);               //abilita la vista con mappe satelitari
         
         //trova la nostra posizione
         myLocationOverlay = new MyLocationOverlay(this, mapView); 
         //al primo fix della posizione, zomma e anima il centro dello schermo sulla nostra posizione
         myLocationOverlay.runOnFirstFix(new Runnable(){
        	 public void run(){
        		 mapView.getController().animateTo(myLocationOverlay.getMyLocation());
        	 }
         });
         mapView.getOverlays().add(myLocationOverlay);
    }

	
	@Override
	protected boolean isRouteDisplayed() {
		// TODO Auto-generated method stub
		return false;
		
			
	}
	
	
	@Override
	public void onResume(){
		super.onResume();
		myLocationOverlay.enableMyLocation();
	}
	
	@Override
	public void onPause(){
		super.onPause();
		myLocationOverlay.disableMyLocation();
	}
	
	
	
}
