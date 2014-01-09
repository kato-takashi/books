package com.example.day04listview;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

public class DiaryAdapter extends CursorAdapter{
	//レイアウト生成に使用
	LayoutInflater mInflater;
	
	public DiaryAdapter(Context context, Cursor c){
		super(context, c);
		mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		
	}
	
	@Override
	public View newView(Context context, Cursor cursor, ViewGroup parent){
		//レイアウトを生成
		View view = mInflater.inflate(android.R.layout.simple_list_item_2, null);
		ViewHolder holder = new ViewHolder();
		holder.date = (TextView) view.findViewById(android.R.id.text1);
		holder.content = (TextView) view.findViewById(android.R.id.text2);
		view.setTag(holder);
		
		return view;
	}
	
	@Override
	public void bindView(View view, Context context, Cursor cursor){
		//画面オブジェクトの取得
		ViewHolder holder = (ViewHolder) view.getTag();
		
		//データベースからデータを取得
		long date = cursor.getLong(cursor.getColumnIndex(Utils.FIELD_DATE));
		String content = cursor.getString(cursor.getColumnIndex(Utils.FIELD_CONTENTS));
		//各Viewにデータを設定
		holder.date.setText(Defines.sFmt.format(date));
		holder.content.setText(content);
	}
	
	
	private class ViewHolder{
		TextView date;
		TextView content;
	}
}
