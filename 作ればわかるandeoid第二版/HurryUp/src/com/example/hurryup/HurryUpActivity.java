package com.example.hurryup;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class HurryUpActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_hurry_up);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.hurry_up, menu);
		return true;
	}

}
