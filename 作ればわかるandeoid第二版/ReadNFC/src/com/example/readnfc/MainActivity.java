package com.example.readnfc;

import android.app.Activity;
import android.content.Intent;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
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
		super.onResume();
		TextView textIDm = (TextView)findViewById(R.id.textView2);
		TextView textTech = (TextView)findViewById(R.id.textView3);
		TextView textNdef = (TextView)findViewById(R.id.textView4);
		
		Intent intent = getIntent();
		String action = intent.getAction();
		
		if(NfcAdapter.ACTION_TECH_DISCOVERED.equals(action)){
			byte[] mID = intent.getByteArrayExtra(NfcAdapter.EXTRA_ID);
			
			String hexStr=bytesToText(mID);
			textIDm.setText("Felica IDm: "+ hexStr);
			
			Tag tag = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);
			String [] techList = tag.getTechList();
			String techStr = "";
			for (String tech : techList ){
				techStr += tech + "\n";
				
			}
			textTech.setText(techStr);
		}
	}
	
	private String bytesToText(byte[] bytes){
		StringBuilder buffer = new StringBuilder();
		for(byte b : bytes){
			String hex = String.format("%02X", b);
			buffer.append(hex).append(" ");
		}
		String text = buffer.toString().trim();
		return text;
	}
	
}
