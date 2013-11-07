package com.example.readnfc;

import android.app.Activity;
import android.nfc.NfcAdapter;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		TextView textEnabled = (TextView)findViewById(R.id.textView1);
		
		NfcAdapter adapter = NfcAdapter.getDefaultAdapter(this);
		if(adapter == null){
			textEnabled.setText("この端末はNFCはサポートされていません。");
		}else{
			if(adapter.isEnabled()){
				textEnabled.setText("NFC is enabled.");
			}else{
				textEnabled.setText("NFC is disabled.");
			}
		}
	}
	
	@Override
	public void onResume(){
		////
	}
	
	
	
}
