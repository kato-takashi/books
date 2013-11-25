package com.example.torchon;

import android.app.Activity;
import android.content.Context;
import android.hardware.Camera;
import android.hardware.Camera.Parameters;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

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
		super.onPause();
		mSensorManager.unregisterListener(this);
		if(mCamera != null){
			mCamera.release();
			mCamera = null;
		}
	}
	
	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy){
		
	}
	
	@Override
	public void onSensorChanged(SensorEvent event){
		if(event.sensor.getType() != Sensor.TYPE_LIGHT){
			return;
		}
		TextView textView01 = (TextView)findViewById(R.id.textView1);
		textView01.setText(String.valueOf(event.values[0]));
		if(event.values[0] < THRESHOLS){
			if(!mLightOn){
				lightOn();
				mLightOn = true;
			}
		}else{
			if(mLightOn){
				lightOff();
				mLightOn = false;
			}
		}
	}
	
	private void lightOn(){
		if(mCamera != null){
			Parameters params = mCamera.getParameters();
			params.setFlashMode(Parameters.FLASH_MODE_TORCH);
			mCamera.setParameters(params);
		}
	}
	
	private void lightOff(){
		if(mCamera != null){
			Parameters params = mCamera.getParameters();
			params.setFlashMode(Parameters.FLASH_MODE_OFF);
			mCamera.setParameters(params);
		}
	}

}
