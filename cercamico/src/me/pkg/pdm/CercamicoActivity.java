package me.pkg.pdm;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

public class CercamicoActivity extends Activity {
    /** Called when the activity is first created. */
	

	
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }
    
    @Override
	public boolean onCreateOptionsMenu(Menu menu) {
	menu.add("Aggiungi amico");
    return true;
	}

	@Override
    public boolean onOptionsItemSelected(MenuItem item) {
      switch (item.getItemId()) {
      case 0:
    	  
    	  AlertDialog.Builder alert = new AlertDialog.Builder(this);

    	  
    	  alert.setTitle("Cerca utente"); 
          alert.setMessage("Inserire username"); 
          
       // Set an EditText view to get user input   
         
        
          final EditText input = new EditText(this); 
          alert.setView(input);

          alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {  
        	    public void onClick(DialogInterface dialog, int whichButton) {  
        	        String value = input.getText().toString();
        	        
        	        return;                  
        	       }  
        	     });  

        	    alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {

        	        public void onClick(DialogInterface dialog, int which) {
        	            // TODO Auto-generated method stub
        	            return;   
        	        }
        	    });         
        	            alert.show();

        return true;
        
      default:
          return super.onOptionsItemSelected(item);
}
    }   
    
}