package com.example.accel_samp;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends Activity 					implements SensorEventListener {

	// センサーマネージャ
	private SensorManager mSensorManager;
	private Sensor mAccelerometer;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

        // センサーマネージャのインスタンスを取得
        mSensorManager = (SensorManager)getSystemService(Context.SENSOR_SERVICE);
        // 加速度センサーの取得
        // Use this method to get the default sensor for a given type.
        mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

	}

	@Override
	protected void onPause() {
		// TODO 自動生成されたメソッド・スタブ
		super.onPause();
        mSensorManager.unregisterListener(this);
	}

	@Override
	protected void onResume() {
		// TODO 自動生成されたメソッド・スタブ
		super.onResume();
        mSensorManager.registerListener(this, mAccelerometer,500000);

	}

	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public void onSensorChanged(SensorEvent event) {
		// TODO 自動生成されたメソッド・スタブ
		TextView txt01 = (TextView)findViewById(R.id.txt01);
		StringBuilder strBuild = new StringBuilder();

		strBuild.append("X軸 " + event.values[0] + "\n");
		strBuild.append("Y軸 " + event.values[1] + "\n");
		strBuild.append("Z軸 " + event.values[2] + "\n");

		txt01.setText(strBuild.toString());

	}

}
