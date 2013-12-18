package com.example.day04listview;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

public class DatabaseHelper extends SQLiteOpenHelper{
	private static final String DATEBASE_NAME = "mydatabase.db";
	private static final int DATEBASE_VERSION = 1;

	public static final String TABLE_DIARY = "DIARY";
	public static final String FIELD_DATE = "DATE";
	public static final String FIELD_CONTENTS = "CONTENTS";

	public DatabaseHelper(Context context){
		super(context, DATEBASE_NAME, null, DATEBASE_VERSION);
	}

	public void  onCreate (SQLiteDatabase db){
		db.beginTransaction();
		try{
			db.execSQL("CREATE TABLE" + TABLE_DIARY + 
					"(" + BaseColumns._ID +" INTEGER PRIMARY KEY AUTOINCREMENT, " + FIELD_DATE +
					" LONG UNIQUE, "+FIELD_CONTENTS + " TEXT"+");");
			//SQLを反映
			db.setTransactionSuccessful();
		}finally{
			//データベース処理終了
			db.endTransaction();
		}
	}
	
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
		//テーブルが存在する場合は削除する
		db.execSQL("DROP TABLE IF EXISTS DIARY");
		
		//テーブルを生成する
		onCreate(db);
		
	}

}
