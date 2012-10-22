package me.pkg.paolu;



import me.pkg.paolo.R;

import org.jivesoftware.smack.*;
import org.jivesoftware.smack.filter.MessageTypeFilter;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.packet.Packet;

import android.app.Activity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ChatActivity extends Activity {
	
	Connection connection;

	

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		 final EditText et = (EditText) findViewById(R.id.editText1);

		 final TextView tv = (TextView) findViewById(R.id.textView1);

		tv.setMovementMethod(new ScrollingMovementMethod());

		Button startButton = (Button) findViewById(R.id.button1);
		startButton.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {

				tv.append("ME: " + et.getText().toString() + "\n");
				
				Message msg = new Message();
				//msg.setTo("paolostip@chat.facebook.com");
				//msg.setTo("olee12@chatme.im");
				msg.setTo("stipcic@ppl.eln.uniroma2.it");
				msg.setBody(et.getText().toString());
				
				((XMPPConnection) connection).sendPacket(msg);
			}
		});
		
		try{
			//ConnectionConfiguration config = new ConnectionConfiguration("chat.facebook.com", 5222);
			//ConnectionConfiguration config = new ConnectionConfiguration("chatme.im", 5222);
			ConnectionConfiguration config = new ConnectionConfiguration("ppl.eln.uniroma2.it", 5222);
			config.setSecurityMode(ConnectionConfiguration.SecurityMode.disabled);
			connection = (Connection) new XMPPConnection(config);
			((XMPPConnection) connection).connect();
			//((org.jivesoftware.smack.Connection) connection).login("mattiapan","totti10");
			//((org.jivesoftware.smack.Connection) connection).login("admin5551","admin5551");
			((org.jivesoftware.smack.Connection) connection).login("panattoni","mattia");
		} catch (XMPPException e) {
			//Log.e("paolo", "try catch error connection");
			e.printStackTrace();
		}
		
		((org.jivesoftware.smack.Connection) connection).addPacketListener(new PacketListener() {
			@Override
			public void processPacket(Packet pkt) {
				Message msg = (Message) pkt;
				String from = msg.getFrom();
				String body = msg.getBody();
				tv.append(from+" : "+body+"\n");
			}
		},new MessageTypeFilter(Message.Type.normal));
	}
}