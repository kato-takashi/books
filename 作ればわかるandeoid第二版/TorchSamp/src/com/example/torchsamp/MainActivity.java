package com.example.torchsamp;

import android.app.Activity;
import android.hardware.Camera;
import android.hardware.Camera.Parameters;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {
	private Camera mCamera;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Button mBtnOn = (Button)findViewById(R.id.button1);
		mBtnOn.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v){
				lightOn();
			}
		});
		Button mBtnOff = (Button)findViewById(R.id.button2);
		mBtnOff.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v){
				lightOff();
			}
		});
	}

	@Override
	protected void onResume(){
		super.onResume();
		try{
			mCamera = Camera.open();
		}catch(Exception e){
			Log.e("TorchSamp", "Cameraがオープンできません");
		}
	}

	@Override
	protected void onPause(){
		if(mCamera != null){
			mCamera.release();
			mCamera = null;
		}
		super.onPause();
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
