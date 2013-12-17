package com.example.day04listview;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MemoActivity extends Activity {
	// Loggerのタグ
	private static final String TAG = "Chapter04";

	// SharedPreferencesのインスタンス
	private SharedPreferences mPrefs;

	// パラメータで受け取った日付
	private long mDate = 0;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.edit);

		// SharedPreferencesのインスタンスを取得
		mPrefs = PreferenceManager.getDefaultSharedPreferences(this);

		// Intentから日付を取得する
		Intent intent = getIntent();
		if (intent != null) {
			if (intent.hasExtra(Defines.KEY_DATE)) {
				mDate = intent.getLongExtra(Defines.KEY_DATE, 0);
			}
		}

		// SharedPreferencesから"content"の値を取得する
		String content = mPrefs.getString(getKey(mDate), "");

		// 今日のカレンダーを取得
		// Calendar cal = Calendar.getInstance();

		// 今日の日付を文字列に変換する
		String date = Defines.sFmt.format(mDate);

		TextView txtsubject = (TextView) findViewById(R.id.TextView01);
		EditText txtcontent = (EditText) findViewById(R.id.EditText01);

		txtsubject.setText(date);
		txtcontent.setText(content);
	}

	/***
	 * 設定ファイルから値を取得するKEYを生成
	 * 
	 * @param value
	 * @return
	 */
	private String getKey(long value) {
		return "key." + value;
	}

	public void onClickButton(View view) {
		switch (view.getId()) {
		case R.id.Button01: {
			AlertDialog.Builder dlg = new AlertDialog.Builder(this);
			dlg.setTitle(getString(R.string.lbSaveTitle));
			dlg.setPositiveButton(getString(R.string.lbYes),
			        new DialogInterface.OnClickListener() {
				        @Override
				        public void onClick(DialogInterface dialog, int which) {
					        // 保存処理
					        EditText txtcontent = (EditText) findViewById(R.id.EditText01);
					        String content = txtcontent.getText().toString();

					        // 保存されたデータを確認する
					        Log.d(TAG, "content=" + content);

					        // Editorのインスタンスを取得
					        Editor editor = mPrefs.edit();

					        // "content"に入力された文字列を設定する
					        editor.putString(getKey(mDate), content);

					        // 設定を反映する
					        editor.commit();

					        finish();
				        }
			        });
			dlg.setNegativeButton(getString(R.string.lbNo),
			        new DialogInterface.OnClickListener() {
				        @Override
				        public void onClick(DialogInterface dialog, int which) {
					        // キャンセル
				        }
			        });
			dlg.show();
		}
			break;
		case R.id.Button02: {
			finish();
		}
			break;
		}
	}
}