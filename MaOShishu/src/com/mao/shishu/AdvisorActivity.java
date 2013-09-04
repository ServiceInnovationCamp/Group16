package com.mao.shishu;

import java.util.ArrayList;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ListView;

import com.mao.shishu.adapter.AdvisorAdapter;
import com.mao.shishu.data.AdvisorData;

public class AdvisorActivity extends Activity {
	private ListView listView;
	private Bundle extras;
	private String tableName;
	private int UpozZillaId;
	private ArrayList<AdvisorData> rowItems;
	private AdvisorAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.advisor_info_main);
		listView = (ListView) findViewById(R.id.lv_advisor_info);

		extras = this.getIntent().getExtras();
		if (extras != null) {
			tableName = extras.getString("TABLE_NAME");
			UpozZillaId = extras.getInt("UPOZILLA_ID");
		}

		rowItems = new ArrayList<AdvisorData>();
		getDataForEmmergency();
		adapter = new AdvisorAdapter(AdvisorActivity.this,
				R.layout.app_service_listview_layout, rowItems);
		listView.setAdapter(adapter);
	}

	private void getDataForEmmergency() {

		SQLiteDatabase db = openOrCreateDatabase("TestDB", MODE_PRIVATE, null);
		String query = "select ha.ha_name, ha.ha_contact, h.hos_name,ha.hos_id from healthadvisor ha inner join hospital h on h.hos_id = h.hos_id where h.upozilla_id ='"
				+ UpozZillaId + "' group by h.hos_id";
		// "select * from '" + tableName + "' where upozilla_id='"
		// + UpozZillaId + "'";

		Cursor cursor = db.rawQuery(query, null);
		if (cursor.moveToFirst()) {
			do {
				rowItems.add(new AdvisorData(cursor.getString(0), cursor
						.getString(2), cursor.getString(1)));
			} while (cursor.moveToNext());
		}
		db.close();
		cursor.close();
	}
}
