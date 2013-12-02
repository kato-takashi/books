package com.example.torchonever;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class TorchOnEverActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_torch_on_ever);
	}
	
	public void startService(View view){
		startService(new Intent(this, TorchOnService.class));
	}
	
	public void stopService(View view){
		stopService(new Intent(this, TorchOnService.class));
	}

}
