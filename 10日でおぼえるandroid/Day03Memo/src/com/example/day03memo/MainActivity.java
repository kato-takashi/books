package com.example.day03memo;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	public void onClickButton(View view){
		switch(view.getId()){
		case R.id.Button01:{
			AlertDialog.Builder dlg = new AlertDialog.Builder(this);
			dlg.setTitle(getString(R.string.lbSaveTitle));
			dlg.setPositiveButton(getString(R.string.lbYes), 
					new DialogInterface.OnClickListener(){
				public void onClick(DialogInterface dialog, int which){
					//ï€ë∂èàóù
					finish();
				}
			} );
			dlg.setNegativeButton(getString(R.string.lbNo), 
					new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which){
					//cancel

				}

			});
			dlg.show();
		}break;

		case R.id.Button02:{
			finish();
		}break;
		}
	}


}
