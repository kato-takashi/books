package com.example.day04listview;

import java.util.ArrayList;
import java.util.Calendar;

import android.app.ListActivity;
import android.content.Intent;
import android.database.ContentObserver;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.CursorAdapter;
import android.widget.EditText;
import android.widget.ListView;

public class ListMain extends ListActivity {
	// 検索ボックスのインスタンス
    EditText mEditText;
    // 検索結果を保持するCursor
    Cursor mCursor;
    
    @Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.listmain);
		//画面オブジェクトを取得
		mEditText = (EditText)findViewById(R.id.EditText01);

		//プロバイダが更新されたらリストを更新するリスナーを設定
		getContentResolver().registerContentObserver(Utils.CONTENT_URI, true, new ContentObserver(new Handler()){
			@Override
			public void onChange(boolean selfChange){
				searchList();
			}
		});
		// 初期データ読み込み
		searchList();
	}
    
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		// TODO 自動生成されたメソッド・スタブ
		super.onListItemClick(l, v, position, id);

		//選択したアイテムから日付を取得する
		CursorAdapter adapter = (CursorAdapter) l.getAdapter();
		Cursor cursor = adapter.getCursor();
		if(cursor != null){
			//MemoActivityを呼び出すIntentを生成
			Intent intent = new Intent(this, MemoActivity.class);
			//パラメーターに選択した日付を設定
			intent.putExtra(Defines.KEY_DATE, cursor.getLong(cursor.getColumnIndex(Utils.FIELD_DATE)));
			//intentの呼び出し実行
			startActivity(intent);
		}
	}
	
	@SuppressWarnings("deprecation")
	private void searchList(){
		// 検索ボックスに入力された内容でデータを検索しリストに表示
		String search = mEditText.getText().toString();
		String where = null;
		if(search.length()>0){
			where = Utils.FIELD_CONTENTS + "LIKE '%" + search + "%'";
		}
		if( mCursor != null){
			stopManagingCursor(mCursor);
			mCursor.close();
			mCursor = null;
		}
		//検索処理の実行
		mCursor = managedQuery(Utils.CONTENT_URI, null, where, null, Utils.FIELD_DATE);
		if(mCursor != null){
			//検索したデータをもとにアダプターを生成
			DiaryAdapter adapter = new DiaryAdapter(this, mCursor);
			
			//アダプターをListViewに設定
			getListView().setAdapter(adapter);
			
		}
	}
}
