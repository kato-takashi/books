package com.example.rakumail;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener{
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		Button btn1 = (Button)findViewById(R.id.button1);
		btn1.setOnClickListener(this);
		Button btn2 = (Button)findViewById(R.id.button2);
		btn2.setOnClickListener(this);
	}

	@Override
	public void onClick(View v){
		switch(v.getId()){
		case R.id.button1:
			Spinner2EditText();
			break;
		case R.id.button2:
			ActionSend();
			break;
		}
	}

	private void Spinner2EditText(){
		Spinner spinner1 = (Spinner)findViewById(R.id.spinner1);
		String iiwake = (String)spinner1.getSelectedItem();
		Spinner spinner2 = (Spinner)findViewById(R.id.spinner2);
		String iitaikoto = (String)spinner2.getSelectedItem();
		Spinner spinner3 = (Spinner)findViewById(R.id.spinner3);
		String sime = (String)spinner3.getSelectedItem();
		EditText edit01 = (EditText)findViewById(R.id.editText1);
		EditText edit02 = (EditText)findViewById(R.id.editText2);
		edit01.setText(iitaikoto);
		edit02.setText(iiwake+iitaikoto + sime);
	}

	private void ActionSend(){
		EditText edit01 = (EditText)findViewById(R.id.editText1);
		EditText edit02 = (EditText)findViewById(R.id.editText2);
		String title = edit01.getText().toString();
		if(title.equals("")){
			Toast.makeText(this, "タイトルが未入力です", Toast.LENGTH_SHORT).show();
			return;
		}
		String content = edit02.getText().toString();
		if(content.equals("")){
			Toast.makeText(this, "本文が未入力です", Toast.LENGTH_SHORT).show();
			return;
		}
		Resources res = getResources();
		Uri uri = Uri.parse("mailto:"+res.getString(R.string.mail_to).toString());
		Intent intent = new Intent(Intent.ACTION_SENDTO, uri);
		intent.putExtra(Intent.EXTRA_SUBJECT, title);
		intent.putExtra(Intent.EXTRA_TEXT, content);
		startActivity(intent);

		edit01.setText("");
		edit01.setText("");
	}
	

}
