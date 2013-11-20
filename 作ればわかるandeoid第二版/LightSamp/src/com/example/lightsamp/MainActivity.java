package com.example.lightsamp;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends Activity
				implements SensorEventListener {

	private SensorManager mSensorManager;
	private Sensor mLight;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
        mSensorManager = (SensorManager)getSystemService(Context.SENSOR_SERVICE);
        mLight = mSensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);


	}
    @Override
    public void onResume() {
        super.onResume();
        mSensorManager.registerListener(this, mLight,SensorManager.SENSOR_DELAY_NORMAL);
    }
    @Override
    public void onPause() {
        super.onPause();
        mSensorManager.unregisterListener(this);
    }

	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public void onSensorChanged(SensorEvent event) {
		// TODO 自動生成されたメソッド・スタブ
		TextView txtView01 = (TextView)findViewById(R.id.TextView01);
		TextView txtView02 = (TextView)findViewById(R.id.TextView02);
		TextView txtView03 = (TextView)findViewById(R.id.TextView03);
		txtView01.setText(String.valueOf(event.values[0]));
		txtView02.setText(String.valueOf(event.values[1]));
		txtView03.setText(String.valueOf(event.values[2]));
	}

}
