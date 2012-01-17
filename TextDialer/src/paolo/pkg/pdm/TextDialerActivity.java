package paolo.pkg.pdm;



import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.PhoneNumberFormattingTextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class TextDialerActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        EditText etext = (EditText) findViewById(R.id.editText1);
        etext.addTextChangedListener(new PhoneNumberFormattingTextWatcher());
        
        
        
        Button startButton = (Button)findViewById(R.id.button1);
        startButton.setOnClickListener(new OnClickListener() {
        	
        	@Override
        	public void onClick(View v) {
        		// TODO Auto-generated method stub
        		  EditText etext = (EditText) findViewById(R.id.editText1);
        	        String telString = etext.getText().toString();
        	        
        	        String telUriString = "tel:" + telString;
        	        Uri telURI = Uri.parse(telUriString);
        	        
        	        Intent intent = new Intent(Intent.ACTION_DIAL);
        	        intent.setData(telURI);
        	        startActivity(intent);
        	}

        		
        	}
        
        );
        

    	Button startButton2 = (Button)findViewById(R.id.button2);
    	startButton2.setOnClickListener(new OnClickListener() {
    	
    	@Override
    	public void onClick(View v) {
    		// TODO Auto-generated method stub
    		  EditText etext = (EditText) findViewById(R.id.editText1);
    	        String telString = etext.getText().toString();
    	        
    	        String telUriString = "tel:" + telString;
    	        Uri telURI = Uri.parse(telUriString);
    	        
    	        Intent intent = new Intent(Intent.ACTION_CALL);
    	        intent.setData(telURI);
    	        startActivity(intent);
    }
    	});
    	}
    ;
    }
