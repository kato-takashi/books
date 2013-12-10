package com.example.day04listview;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;

public class ListMain extends ListActivity {

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
		
//		日付成形出力するためのフォーマッターを生成
		SimpleDateFormat fmt = new SimpleDateFormat("yyyy/MM/dd (E)");
		
//		今日のカレンダーを取得
		Calendar cal = Calendar.getInstance();
		
//		月の最大日数分だけ繰り返す
		int maxday = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
		for(int i=0; i < maxday; i++){
			cal.set(Calendar.DAY_OF_MONTH, i+1);
//			整形した日付の文字列をイストに追加する
			String datestr = fmt.format(cal.getTime());
			ret.add(datestr);
		}
		return ret;
	}
	

}
