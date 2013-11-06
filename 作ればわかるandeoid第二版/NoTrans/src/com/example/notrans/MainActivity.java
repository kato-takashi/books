package com.example.notrans;

import java.util.Locale;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity 
implements View.OnClickListener, TextToSpeech.OnInitListener{
	private static final int REQUEST_CODE = 0;
	private static final int MAX_RESULT = 1;
	private static final Float SPEECH_RATE = 1.0f;
	private static final Float SPEECH_PITCH = 1.0f;
	private static final String TAG = "NoTrans";

	private TextToSpeech tts;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Button btn1 = (Button)findViewById(R.id.button_hear);
		btn1.setOnClickListener(this);
		Button btn2 = (Button)findViewById(R.id.button_speech);
		btn2.setOnClickListener(this);
		Button btn3 = (Button)findViewById(R.id.button_ok);
		btn3.setOnClickListener(this);

		tts = new TextToSpeech(this, this);
	}

	@Override
	protected void onDestroy(){
		super.onDestroy();
		if(tts != null){
			tts.shutdown();
		}
	}

	@Override
	public void onInit(int status){
		if(status == TextToSpeech.SUCCESS){
			Locale locale = Locale.ENGLISH;
			if(tts.isLanguageAvailable(locale) >= TextToSpeech.LANG_AVAILABLE){
				tts.setLanguage(locale);
			}else{
				Log.d(TAG, "Unsupported Language");
			}
		} else if(status == TextToSpeech.ERROR){
			Log.d(TAG, "onInit ERROR");
		}
	}

	@Override
	public void onClick(View v){
		switch (v.getId()){
		case R.id.button_hear:
			StartRecognize();
			break;
		case R.id.button_speech:
			StartSpeech();
			break;
		case R.id.button_ok:
			Edit2TextView();
			break;
		}
	}

	public void StartRecognize(){

	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data){

	}

	public void StartSpeech(){

	}

	public void Edit2TextView(){

	}


}
