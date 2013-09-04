package com.mao.shishu;

import java.util.ArrayList;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ListView;

import com.mao.shishu.adapter.EmmergencyHotlineAdapter;
import com.mao.shishu.data.EmmergencyHotlineData;

public class HotLineActivity extends Activity {
	private ArrayList<EmmergencyHotlineData> rowItems;
	private EmmergencyHotlineAdapter adapter;
	private ListView listView;
	private Bundle extras;
	private int UpozZillaId;
	private String tableName;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.hotline_main);
		listView = (ListView) findViewById(R.id.lv_hotline_main);

		extras = this.getIntent().getExtras();
		if (extras != null) {
			tableName = extras.getString("TABLE_NAME");
			UpozZillaId = extras.getInt("UPOZILLA_ID");
		}

		rowItems = new ArrayList<EmmergencyHotlineData>();
		getDataForEmmergency();
		adapter = new EmmergencyHotlineAdapter(HotLineActivity.this,
				R.layout.app_service_listview_layout, rowItems);
		listView.setAdapter(adapter);
	}

	private void getDataForEmmergency() {

		SQLiteDatabase db = openOrCreateDatabase("TestDB", MODE_PRIVATE, null);
		String query = "select * from '" + tableName + "' where upozilla_id='"
				+ UpozZillaId + "'";

		Cursor cursor = db.rawQuery(query, null);
		if (cursor.moveToFirst()) {
			do {
				rowItems.add(new EmmergencyHotlineData(cursor.getString(1),
						cursor.getString(2)));
			} while (cursor.moveToNext());
		}
		db.close();
		cursor.close();
	}
}
