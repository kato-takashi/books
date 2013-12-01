package com.example.torchonever;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.hardware.Camera;
import android.hardware.Camera.Parameters;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.IBinder;
import android.util.Log;

public class TorchOnService extends Service implements SensorEventListener{

	private static final int THRESHOLS =100;
	private SensorManager mSensorManager;
	private Sensor mLight;
	private Camera mCamera;
	private boolean mLightOn = false;
	private static final int NOTI_ID = 1;
	
	@SuppressWarnings("deprecation")
	private void showNotification(){
		Intent notiIntent = new Intent(this, TorchOnEverActivity.class);
		PendingIntent contentIntent = PendingIntent.getActivity(this, 0, notiIntent, PendingIntent.FLAG_UPDATE_CURRENT);
		int smalliconId = R.drawable.ic_launcher;
		Bitmap largeIconBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher);
		NotificationManager nManager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
		Notification.Builder builder = new Notification.Builder(this);
		builder.setContentIntent(contentIntent)
		.setSmallIcon(smalliconId)
		.setLargeIcon(largeIconBitmap)
		.setWhen(System.currentTimeMillis())
		.setContentTitle(getResources().getString(R.string.app_name))
		.setContentText(getResources().getString(R.string.noti_text))
		.setOngoing(true);
		nManager.notify(NOTI_ID, builder.getNotification());
	}
	@Override
	public void onCreate() {
		super.onCreate();
		mSensorManager = (SensorManager)getSystemService(Context.SENSOR_SERVICE);
		mLight = mSensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
		mSensorManager.registerListener(this, mLight, SensorManager.SENSOR_DELAY_NORMAL);
		try{
			mCamera = Camera.open();
		}catch(Exception e){
			Log.e("TotchSamp", "Cameraがオープンできません");
		}
		showNotification();
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId){
		return super.onStartCommand(intent, flags, startId);
	}

	@Override
	public void onDestroy(){
		super.onDestroy();
		mSensorManager.unregisterListener(this);
		if(mCamera != null){
			mCamera.release();
			mCamera = null;
		}
		NotificationManager nManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
		nManager.cancel(NOTI_ID);
	}

	@Override
	public IBinder onBind(Intent arg0){
		return null;
	}

	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy){

	}

	@Override
	public void onSensorChanged(SensorEvent event){
		if(event.sensor.getType() != Sensor.TYPE_LIGHT){
			return;
		}
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
