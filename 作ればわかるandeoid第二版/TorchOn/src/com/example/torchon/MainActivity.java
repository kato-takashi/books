package com.example.torchon;

import android.app.Activity;
import android.content.Context;
import android.hardware.Camera;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends Activity implements SensorEventListener{
	private static final int THRESHOLS =100;
	private SensorManager mSensorManager;
	private Sensor mLight;
	private Camera mCamera;
	private boolean mLightOn = false;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mSensorManager = (SensorManager)getSystemService(Context.SENSOR_SERVICE);
		mLight = mSensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
	}
	
	@Override
	public void onResume(){
		super.onResume();
		mSensorManager.registerListener(this, mLight, SensorManager.SENSOR_DELAY_NORMAL);
		try{
			mCamera = Camera.open();
		}catch(Exception e){
			Log.e("TorchSamp", "Cameraがオープンできません");
		}
	}
	
	@Override
	public void onPause(){
		
	}
	
	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy){
		
	}
	
	@Override
	public void onSensorChanged(SensorEvent event){
		
	}
	
	private void lightOn(){
		
	}
	
	private void lightOff(){
		
	}

}
