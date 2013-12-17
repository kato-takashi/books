package com.example.day04listview;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import android.app.ListActivity;
import android.content.Intent;
import android.net.ParseException;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ListMain extends ListActivity {
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		// TODO 自動生成されたメソッド・スタブ
		super.onListItemClick(l, v, position, id);

		//選択したアイテムから日付を取得する
		String date = (String) l.getAdapter().getItem(position);
		try{
			Date dtime = Defines.sFmt.parse(date);
			//MemoActivityを呼び出すIntentを生成
			Intent intent = new Intent(this, MemoActivity.class);
			//パラメータに選択した日付を設定
			intent.putExtra(Defines.KEY_DATE, dtime.getTime());
			//			Intentを呼び出し実行する
			startActivity(intent);

		}catch(java.text.ParseException e){

		}
	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.listmain);

		//		今月の日付の一覧をリストにして返却
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, selectDays());
		//		ArrayAdapterをリストに設定する
		getListView().setAdapter(adapter);
	}

	private ArrayList<String> selectDays(){
		ArrayList<String> ret = new ArrayList<String>();

		//		今日のカレンダーを取得
		Calendar cal = Calendar.getInstance();

		//		月の最大日数分だけ繰り返す
		int maxday = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
		for(int i=0; i < maxday; i++){
			cal.set(Calendar.DAY_OF_MONTH, i+1);
			//			整形した日付の文字列をイストに追加する
			String datestr = Defines.sFmt.format(cal.getTime());
			ret.add(datestr);
		}
		return ret;
	}


}
