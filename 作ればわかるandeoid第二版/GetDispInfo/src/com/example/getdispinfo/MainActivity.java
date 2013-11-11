package com.example.getdispinfo;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.TextView;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		TextView text01 = (TextView)findViewById(R.id.textView1);
		DisplayMetrics metrics = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(metrics);
		
		StringBuilder buffer = new StringBuilder();
		buffer.append("density(論理密度)；" + String.valueOf(metrics.density) + "\n");
		buffer.append("densityDpi(ドット数・インチ)" + String.valueOf(metrics.densityDpi) + "\n");
		buffer.append("widthPixels(幅)：" + String.valueOf(metrics.widthPixels) + "\n");
		buffer.append("heightPixels（高さ）：" + String.valueOf(metrics.heightPixels) + "\n");
		buffer.append("xdpi（x軸の1インチあたりのピクセル数）：" + String.valueOf(metrics.xdpi) + "\n");
		buffer.append("ydpi(y軸の1インチあたりのピクセル数)；" + String.valueOf(metrics.ydpi) + "\n");
		text01.setText(buffer.toString());
	}


}
