package com.example.day04listview;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

public class DiaryProvider extends ContentProvider{
	//コンテンツプロバイダのUri
	public static final Uri CONTENT_URI = Uri.parse("content://com.example.day04listview/diary");

	//データベースの操作クラス
	private DatabaseHelper mHelper;

	@Override
	public boolean onCreate(){
		//プロバイダで利用するデータベースを生成
		mHelper = new DatabaseHelper(getContext());
		return true;
	}

	@Override
	public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder){
		//データベールヘルパーから読み込み用のデータベースを取得
		SQLiteDatabase db = mHelper.getReadableDatabase();
		//selectionを条件に条件を検索し, projectionで指定された項目を取得
		Cursor c = db.query(DatabaseHelper.TABLE_DIARY, projection,  selection, selectionArgs, null, null, sortOrder);
		//検索されたことを登録されているリスナーへ通知
		c.setNotificationUri(getContext().getContentResolver(), uri);
		return c;
	}

	@Override
	public String getType(Uri uri){
		return null;
	}

	@Override
	public Uri insert(Uri uri, ContentValues values){
		//データベースヘルパーから書き込み用のデータベースを取得
		SQLiteDatabase db = mHelper.getWritableDatabase();
		//データベースにvaluesを追加、
		//追加に成功したらその位置の_IDがrowIDに代入される
		long rowId = db.insert(DatabaseHelper.TABLE_DIARY, null, values);
		//追加に失敗したら_IDには-1が代入される
		if(rowId>0){
			//追加されたコンテンツの_IDをもとにUriを作成
			Uri noteUri = ContentUris.withAppendedId(CONTENT_URI, rowId);
			//更新されたことを登録されているリスナーへ通知
			getContext().getContentResolver().notifyChange(noteUri, null);
			return noteUri;
		}
		throw new SQLException("insertに失敗しました" + uri);
	}

	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs){
		//データベースヘルパーから書き込み用のデータベースを取得
		SQLiteDatabase db = mHelper.getWritableDatabase();
		//sleectionを条件に削除
		//複数対称となる場合はその全てが削除される countには削除された数が入る
		int count = db.delete(DatabaseHelper.TABLE_DIARY, selection, selectionArgs);
		//削除されたことを登録されているリスナーへ通知
		getContext().getContentResolver().notifyChange(uri, null);
		return count;
	}

	@Override
	public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs){
		//データベースヘルパーから書き込み用のデータベースを取得
		SQLiteDatabase db = mHelper.getWritableDatabase();
		//sleectionを条件に更新
		//複数対称となる場合はその全てが更新される countには更新された数が入る
		int count = db.update(DatabaseHelper.TABLE_DIARY, values, selection, selectionArgs);
		//更新されたことを登録されているリスナーへ通知
		getContext().getContentResolver().notifyChange(uri, null);
		return count;
	}
}
