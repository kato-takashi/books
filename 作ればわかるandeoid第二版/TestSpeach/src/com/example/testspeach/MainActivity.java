package com.example.testspeach;

import java.util.Locale;

import android.app.Activity;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity 
	implements View.OnClickListener, TextToSpeech.OnInitListener{
	
	private static final Float SPEECH_PATE = 1.0f;
	private static final Float SPEECH_PITCH = 1.0f;
	private static final String TAG = "TestSpeech";
	private TextToSpeech tts;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Button buttonSpeech = (Button)findViewById(R.id.button1);
		buttonSpeech.setOnClickListener(this);
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
		}else if(status == TextToSpeech.ERROR){
			Log.d(TAG, "onInit ERROR");
		}
	}

	@Override
	public void onClick(View v){
		EditText edit01 = (EditText)findViewById(R.id.editText1);
		
		String strSpeech = edit01.getText().toString();
		if(strSpeech.length() == 0){
			return;
		}
		if(tts.isSpeaking()){
			tts.stop();
		}
		tts.setSpeechRate(SPEECH_PATE);
		tts.setPitch(SPEECH_PITCH);
		//スピーチの再生
		tts.speak(strSpeech, TextToSpeech.QUEUE_FLUSH, null);
	}

}
