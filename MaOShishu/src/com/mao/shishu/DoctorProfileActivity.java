package com.mao.shishu;

import java.util.ArrayList;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ListView;

import com.mao.shishu.adapter.EmmergencyDprofileAdapter;
import com.mao.shishu.data.DoctorProfileData;

public class DoctorProfileActivity extends Activity {
	private ArrayList<DoctorProfileData> rowItems;
	private EmmergencyDprofileAdapter adapter;
	private ListView listView;
	private Bundle extras;
	private int UpozZillaId;
	private String tableName;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.doctor_profile_info_main);
		listView = (ListView) findViewById(R.id.lv_doctor_profile);

		extras = this.getIntent().getExtras();
		if (extras != null) {
			tableName = extras.getString("TABLE_NAME");
			UpozZillaId = extras.getInt("UPOZILLA_ID");
		}

		rowItems = new ArrayList<DoctorProfileData>();
		getDataForEmmergency();
		adapter = new EmmergencyDprofileAdapter(DoctorProfileActivity.this,
				R.layout.app_service_listview_layout, rowItems);
		listView.setAdapter(adapter);
	}

	private void getDataForEmmergency() {

		SQLiteDatabase db = openOrCreateDatabase("TestDB", MODE_PRIVATE, null);
		String query = "select d.doctor_name, d.doctor_contact, d.doctor_profile, h.hos_name,d.doctor_id from doctor d inner join hospital h on h.hos_id = d.hos_id where h.upozilla_id ='"
				+ UpozZillaId + "' group by d.doctor_id";

		Cursor cursor = db.rawQuery(query, null);
		if (cursor.moveToFirst()) {
			do {
				rowItems.add(new DoctorProfileData(cursor.getString(0), cursor.getString(2), cursor.getString(1), cursor.getString(3)));
			} while (cursor.moveToNext());
		}
		db.close();
		cursor.close();
	}
}