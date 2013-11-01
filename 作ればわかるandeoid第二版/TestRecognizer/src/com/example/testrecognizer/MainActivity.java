package com.example.testrecognizer;

import java.util.Locale;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends Activity {
	private static final int REQUEST_CODE = 0;
	private static final int MAX_RESULT = 1;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}
	
	public void StartRecognize(View v){
		try{
			Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
			intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
			intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.US.toString());
			intent.putExtra(RecognizerIntent.EXTRA_PROMPT, getString(R.string.Recognize));
			intent.putExtra(RecognizerIntent.EXTRA_MAX_RESULTS, MAX_RESULT);
			
			startActivityForResult(intent, REQUEST_CODE);
		}catch(ActivityNotFoundException e){
			Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_LONG ).show();
		}
	}

}
