package com.example.day04listview;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class Utils {
	public static String getContents(Context context, long date, String def){
		DatabaseHelper helper = null;
		SQLiteDatabase db = null;
		Cursor cur = null;

		try{
			//Helperクラスの生成
			helper = new DatabaseHelper(context);
			//読み込み用SQLiteDatabaseを生成
			db = helper.getWritableDatabase();

			//検索処理の実行
			cur = db.query(DatabaseHelper.TABLE_DIARY, 
					new String[]{DatabaseHelper.FIELD_CONTENTS}, 
					DatabaseHelper.FIELD_DATE + " =?", 
					new String[]{Long.toString(date)}, null, null, null, "1");

			if(cur != null && cur.moveToFirst()){
				String contents = cur.getString(cur.getColumnIndex(DatabaseHelper.FIELD_CONTENTS));
				return contents;
			}else{
				return def;
			}

		}finally{
			//終了処理
			if(cur != null){
				cur.close();
			}
			if(db != null){
				db.close();
			}

			if(helper != null){
				helper.close();
			}

		}
	}

	//日付に該当するコンテンツを更新
	public static void updateContents(Context context, long date, String contents){
		DatabaseHelper helper = null;
		SQLiteDatabase db = null;
		
		try{
			//Helperクラスの生成
			helper = new DatabaseHelper(context);
			//書き込み用SQLiteDatabaseを生成
			db = helper.getWritableDatabase();
			
			//書き込み用のデータベースを作成
			ContentValues values = new ContentValues();
			values.put(DatabaseHelper.FIELD_CONTENTS, contents);
			
			//アップデート処理の実行
			db.update(DatabaseHelper.TABLE_DIARY, values, 
					DatabaseHelper.FIELD_DATE+" = ?", 
					new String[]{Long.toString(date)});
		}finally{
			//終了処理
			if(db != null){
				db.close();
			}
			if(helper != null){
				helper.close();
			}
		}
	}
	
	//コンテンツを新しく登録
	public static void createContents(Context context, long date, String contents){
		DatabaseHelper helper = null;
		SQLiteDatabase db = null;
		try{
			//Helperクラスの生成
			helper = new DatabaseHelper(context);
			//書き込み用SQLitedatabaseを生成
			db = helper.getWritableDatabase();
			
			//書き込み用のデータを作成
			ContentValues values = new ContentValues();
			values.put(DatabaseHelper.FIELD_DATE, date);
			values.put(DatabaseHelper.FIELD_CONTENTS, contents);
			
			//インサート処理の実行
			db.insert(DatabaseHelper.TABLE_DIARY, null, values);
		}finally{
			//終了処理
			if(db != null){
				db.close();
			}
			if(helper != null){
				helper.close();
			}
		}
	}
}
