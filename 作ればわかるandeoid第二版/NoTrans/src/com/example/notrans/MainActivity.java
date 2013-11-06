package com.example.notrans;

import java.util.ArrayList;
import java.util.Locale;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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
		try{
			Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
			intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
			intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.US.toString());
			intent.putExtra(RecognizerIntent.EXTRA_PROMPT, getString(R.string.please_speak));
			intent.putExtra(RecognizerIntent.EXTRA_MAX_RESULTS, MAX_RESULT);
			startActivityForResult(intent, REQUEST_CODE);

		}catch(ActivityNotFoundException e){
			Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data){
		if(requestCode == REQUEST_CODE && resultCode == RESULT_OK ){
			ArrayList<String> results = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
			EditText edSomeOne = (EditText)findViewById(R.id.ed_someone);
			edSomeOne.setText(results.get(0));
		}
		super.onActivityResult(requestCode, resultCode, data);
	}

	public void StartSpeech(){
		EditText edYou = (EditText)findViewById(R.id.ed_you);
		String strSpeech = edYou.getText().toString();
		if(strSpeech.length() == 0) return;
		if(tts.isSpeaking()){
			tts.stop();
		}
		tts.setSpeechRate(SPEECH_RATE);
		tts.setPitch(SPEECH_PITCH);
		tts.speak(strSpeech, TextToSpeech.QUEUE_FLUSH, null);

	}

	public void Edit2TextView(){
		EditText edSomeone = (EditText)findViewById(R.id.ed_someone);
		EditText edYou = (EditText)findViewById(R.id.ed_you);
		TextView tvTalk = (TextView)findViewById(R.id.tv_talk);
		if((edSomeone.getText().toString()).length()>0){
			tvTalk.append(getString(R.string.someone)+":"+ edSomeone.getText().toString() + "\n");
			edYou.setText("");
		}
		if((edYou.getText().toString()).length()>0){
			tvTalk.append(getString(R.string.you)+":"+ edYou.getText().toString() + "\n");
			edYou.setText("");
		}
	}


}
