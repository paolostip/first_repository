package pdm.pkg.paolo;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class Second extends Activity {
	
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second);
        
        TextView label = (TextView) findViewById(R.id.textView1);
    	String iltestoricevuto = getIntent().getExtras().getString("iltestonelbox");
    	label.setText(iltestoricevuto);
	}
}
