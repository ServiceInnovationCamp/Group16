package com.mao.shishu.motherservice;

import java.util.ArrayList;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.mao.shishu.R;
import com.mao.shishu.adapter.EmmergencyHospitalAdapter;
import com.mao.shishu.data.EmmergencyHostpitalData;

public class MotherServiceActivity extends Activity {
	private Bundle extras;

	private int UpozZillaId;
	private ArrayList<EmmergencyHostpitalData> rowItems;
	private EmmergencyHospitalAdapter adapter;
	private ListView listView;
	private String tableName;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.emmergency_hospital_main);
		listView = (ListView) findViewById(R.id.lv_hostpital_main);

		extras = this.getIntent().getExtras();
		if (extras != null) {
			tableName = extras.getString("TABLE_NAME");
			UpozZillaId = extras.getInt("UPOZILLA_ID");
		}
		rowItems = new ArrayList<EmmergencyHostpitalData>();

		getDataForEmmergency();
		adapter = new EmmergencyHospitalAdapter(MotherServiceActivity.this,
				R.layout.app_service_listview_layout, rowItems);
		listView.setAdapter(adapter);
	}

	private void getDataForEmmergency() {

		SQLiteDatabase db = openOrCreateDatabase("TestDB", MODE_PRIVATE, null);
		String query = "select * from '"
				+ tableName
				+ "' where hos_id in(select hos_id from hospital where upozilla_id='"
				+ UpozZillaId + "' ); ";

		Cursor cursor = db.rawQuery(query, null);
		if (cursor.moveToFirst()) {
			do {
				rowItems.add(new EmmergencyHostpitalData(cursor.getString(1),
						cursor.getString(2), cursor.getString(3), cursor
								.getString(4), cursor.getString(8)));
			} while (cursor.moveToNext());
		}
	}

	private String getTableName(int position) {
		if (position == 0) {
			return "hospital";
		} else if (position == 1) {
			return "doctor";
		} else if (position == 2) {
			return "healthadvisor";
		} else if (position == 3) {
			return "hotline";
		}
		return null;
	}

}
