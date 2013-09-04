package com.mao.shishu;

import java.util.ArrayList;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class SettingActivity extends Activity {
	private Bundle extras;
	private String phoneNumber;
	private EditText etName;
	private EditText etPregnancyDate;
	private EditText etMobileOne;
	private EditText etMobileTwo;
	private Spinner spinnerZilla;
	private Spinner spinnerUpoZilla;
	private ArrayAdapter<String> adapterZilla;
	private ArrayList<String> spinnerTextZilla = new ArrayList<String>();
	private ArrayList<String> spinnerTextUpoZilla = new ArrayList<String>();
	private String spinnerTextFirst;
	private ArrayAdapter<String> adapterUpoZilla;
	private String spinnerTextSecond;
	private Button btnUpdate;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.settings);

		etName = (EditText) findViewById(R.id.et_name_update);
		etPregnancyDate = (EditText) findViewById(R.id.et_pregnancy_date_update);
		etMobileOne = (EditText) findViewById(R.id.et_mobile_number_1_update);
		etMobileTwo = (EditText) findViewById(R.id.et_mobile_number_2_update);
		spinnerZilla = (Spinner) findViewById(R.id.sp_zilla_update);
		spinnerUpoZilla = (Spinner) findViewById(R.id.sp_upozilla_update);
		btnUpdate = (Button) findViewById(R.id.btn_update);

		extras = getIntent().getExtras();
		if (extras != null) {
			phoneNumber = extras.getString("PHONE_NUMBER");
		}

		SQLiteDatabase db = openOrCreateDatabase("TestDB", MODE_PRIVATE, null);
		Cursor cursor = db.rawQuery("select * from users where mobile_one='"
				+ phoneNumber + "'", null);
		if (cursor.moveToFirst()) {
			do {
				etName.setText(cursor.getString(0));
				etPregnancyDate.setText(cursor.getString(1));
				spinnerTextZilla.add(cursor.getString(2));
				spinnerTextFirst = cursor.getString(2);
				spinnerTextUpoZilla.add(cursor.getString(3));
				spinnerTextSecond = cursor.getString(3);
				etMobileOne.setText(cursor.getString(4));
				etMobileTwo.setText(cursor.getString(5));
			} while (cursor.moveToNext());
		}
		cursor.close();
		db.close();

		adapterZilla = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, loadSpinnerDataZilla());
		adapterZilla
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinnerZilla.setAdapter(adapterZilla);
		int pos = adapterZilla.getPosition(spinnerTextFirst);
		spinnerZilla.setSelection(pos);

		spinnerZilla.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				adapterUpoZilla = new ArrayAdapter<String>(
						SettingActivity.this,
						android.R.layout.simple_spinner_item,
						loadSpinnerDataUpoZilla(position + 1));
				adapterUpoZilla
						.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
				spinnerUpoZilla.setAdapter(adapterUpoZilla);
				int pos = adapterUpoZilla.getPosition(spinnerTextSecond);
				spinnerUpoZilla.setSelection(pos);
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
			}
		});

		btnUpdate.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View view) {
				updateUserTable();
			}
		});

	}

	private void updateUserTable() throws SQLException {
		SQLiteDatabase db = openOrCreateDatabase("TestDB", MODE_PRIVATE, null);

		String query = "UPDATE users SET user_name='"
				+ etName.getText().toString() + "',pregnancy_date='"
				+ etPregnancyDate.getText().toString().trim() + "',district='"
				+ spinnerZilla.getSelectedItem().toString()
				+ "',sub_disctrict='"
				+ spinnerUpoZilla.getSelectedItem().toString()
				+ "',mobile_one='" + etMobileOne.getText().toString()
				+ "',mobile_two='" + etMobileTwo.getText().toString()
				+ "' WHERE mobile_one='" + phoneNumber + "'";
		db.execSQL(query);
		db.close();
	}

	private ArrayList<String> loadSpinnerDataZilla() {
		SQLiteDatabase db = openOrCreateDatabase("TestDB", MODE_PRIVATE, null);
		Cursor cursor = db.rawQuery("select * from zilla", null);
		ArrayList<String> labels = new ArrayList<String>();
		if (cursor.moveToFirst()) {
			do {
				labels.add(cursor.getString(1));
			} while (cursor.moveToNext());
		}
		cursor.close();
		db.close();
		return labels;
	}

	private ArrayList<String> loadSpinnerDataUpoZilla(int id) {
		SQLiteDatabase db = openOrCreateDatabase("TestDB", MODE_PRIVATE, null);

		ArrayList<String> labels = new ArrayList<String>();

		Cursor cursor = db.rawQuery("select * from upozilla where zilla_id='"
				+ id + "'", null);
		if (cursor.moveToFirst()) {
			do {
				labels.add(cursor.getString(1));
			} while (cursor.moveToNext());
		}
		cursor.close();
		db.close();
		return labels;
	}

}
