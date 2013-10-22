package com.example.lifecycle;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;

public class MainActivity extends Activity {
	private static final String TAG = "LifeCycle";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.v(TAG, "onCreate is called.");
		setContentView(R.layout.activity_main);		
	}
	
	@Override
	protected void onStart() {
		//TODO�@�����������ꂽ���\�b�h
		super.onStart();
		Log.v(TAG, "onStart is called.");		
	}
	
	@Override
	protected void onRestart() {
		//TODO�@�����������ꂽ���\�b�h
		super.onRestart();
		Log.v(TAG, "onRestart is called.");		
	}
	
	@Override
	protected void onResume() {
		//TODO�@�����������ꂽ���\�b�h
		super.onResume();
		Log.v(TAG, "onResume is called.");		
	}
	
	@Override
	protected void onPause() {
		//TODO�@�����������ꂽ���\�b�h
		super.onPause();
		Log.v(TAG, "onPause is called.");		
	}
	
	@Override
	protected void onStop() {
		//TODO�@�����������ꂽ���\�b�h
		super.onStop();
		Log.v(TAG, "onStop is called.");		
	}
	
	@Override
	protected void onDestroy() {
		//TODO�@�����������ꂽ���\�b�h
		super.onDestroy();
		Log.v(TAG, "onDestroy is called.");		
	}

}
