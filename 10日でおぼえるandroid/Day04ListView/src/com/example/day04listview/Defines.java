package com.example.day04listview;

import java.text.SimpleDateFormat;

public interface Defines {
	//日付を取得するキー
	public static final String KEY_DATE = "date";
//	メモ帳のサブジェクトを取得する際のキー
	public static final String KEY_SUBJECT = "subject";
//	メモ項の内容を取得する際のキー
	public static final String KEY_CONTENT = "content";
//	日付を成形出力する際のフォーマッター
	public static SimpleDateFormat sFmt = new SimpleDateFormat("yyyy/MM/dd(E)");
	
}
