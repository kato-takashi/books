package com.example.day04listview;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

public class Utils {
	//コンテンツプロバイダのuri
	public static final Uri CONTENT_URI = Uri.parse("content://com.example.day04listview/diary");
	public static final String TABLE_DIARY = "DIARY";
	public static final String FIELD_DATE = "DATE";
	public static final String FIELD_CONTENTS =  "CONTENTS";

	//	日付に対応したコンテンツを取得、存在しない場合はNULLを返却
	//	@param context
	//	@param date
	//	@return

	public static String getContents(Context context, long date, String def){
		Cursor cur = null;
		try{
			//検索処理の実行
			cur = context.getContentResolver().query(CONTENT_URI, new String[]{FIELD_CONTENTS}, FIELD_DATE+"=?", new String[]{Long.toString(date)}, null);
			if(cur != null && cur.moveToFirst()){
				String contents = cur.getString(cur.getColumnIndex(FIELD_CONTENTS));
				return contents;
			}else{
				return def;
			}
		}finally{
			//終了処理
			if(cur != null){
				cur.close();
			}
		}
	}

	//日付に該当するコンテンツを更新
	//	@param context
	//	@param date
	//	@return

	public static void updateContents(Context context, long date, String contents){
		//書き込み用のデータを作成する
		ContentValues values = new Contentvalues();
		values.put(FIELD_CONTENTS, contents);
		
		//更新処理の実行をする
		context.getContentResolver().update(CONTENT_URI, values, FIELD_DATE+"=?", new String[]{Long.toString(date)});
	}

	//コンテンツを新しく登録
	//	@param context
	//	@param date
	//	@return
	public static void createContents(Context context, long date, String contents){
		//書き込み用のデータを作成する
		ContentValues values = new Contentvalues();
		values.put(FIELD_DATE, date);
		values.put(FIELD_CONTENTS, contents);
		
		//追加処理の実行をする
		context.getContentResolver().insert(CONTENT_URI, values);
	}
}
