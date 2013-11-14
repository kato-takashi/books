package com.example.getsensorlist;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		TextView txt01 = (TextView)findViewById(R.id.txt01);
		
		StringBuilder strBuild = new StringBuilder();
		SensorManager mSensorManager = (SensorManager)getSystemService(Context.SENSOR_SERVICE);
		List<Sensor> list = mSensorManager.getSensorList(Sensor.TYPE_ALL);
		for(Sensor sensor : list){
			strBuild.append(sensor.getType());
			strBuild.append(",");
			strBuild.append(sensor.getName());
			strBuild.append(",");
			strBuild.append(sensor.getVendor());
			strBuild.append("\n");
		}
		txt01.setText(strBuild.toString());
	}

}
