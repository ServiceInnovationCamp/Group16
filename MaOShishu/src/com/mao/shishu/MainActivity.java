package com.mao.shishu;

import java.util.ArrayList;
import java.util.StringTokenizer;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources.NotFoundException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.mao.shishu.adapter.SlideAdapter;
import com.mao.shishu.appservice.AppServiceMainActivity;

public class MainActivity extends Activity {
	private ViewPager mViewPager;
	private SlideAdapter adapter;
	private ImageView iv1, iv2, iv3;
	private TextView tvSignUp;
	private TextView tvUserManual;
	private AlertDialog showAlertInRegistration;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		boolean isDbCreated = false;
		if (!isDbCreated) {
			createDbWithStaticData();
		}

		iv1 = (ImageView) findViewById(R.id.iv_current_select1);
		iv2 = (ImageView) findViewById(R.id.iv_current_select2);
		iv3 = (ImageView) findViewById(R.id.iv_current_select3);
		tvSignUp = (TextView) findViewById(R.id.textview_sign_up);
		tvSignUp.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				/*
				 * Intent intent = new Intent(MainActivity.this,
				 * AppServiceMainActivity.class); startActivity(intent);
				 * finish();
				 */

				showAlertInRegistration = new AlertDialog.Builder(
						MainActivity.this)
						.setTitle(R.string.signin_for_which)
						.setMessage(R.string.signin_for_which_message)
						.setPositiveButton(R.string.for_me,
								new AlertDialog.OnClickListener() {

									@Override
									public void onClick(DialogInterface arg0,
											int arg1) {
										Intent intent = new Intent(
												MainActivity.this,
												SignUpActivity.class);
										intent.putExtra("isForMom", true);
										startActivity(intent);
									}
								})
						.setNegativeButton(R.string.for_child,
								new AlertDialog.OnClickListener() {

									@Override
									public void onClick(DialogInterface dialog,
											int which) {
										Intent intent = new Intent(
												MainActivity.this,
												SignUpActivity.class);
										intent.putExtra("isForMom", false);
										startActivity(intent);
									}
								}).create();
				showAlertInRegistration.show();

			}
		});

		tvUserManual = (TextView) findViewById(R.id.textview_Peek);
		tvUserManual.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				startActivity(new Intent(MainActivity.this,
						UserManualActivity.class));
			}
		});

		// activate indicator first slide
		activeIndicatorSlideFirst();

		mViewPager = (ViewPager) findViewById(R.id.pager);
		// set the adapter
		adapter = new SlideAdapter(MainActivity.this);
		mViewPager.setAdapter(adapter);

		mViewPager.setOnPageChangeListener(new OnPageChangeListener() {

			@Override
			public void onPageSelected(int position) {
				if (position == 0) {
					activeIndicatorSlideFirst();
				} else if (position == 1) {
					activeIndicatorSlideSecond();
				} else if (position == 2) {
					activeIndicatorSlideThird();
				}
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
			}

			@Override
			public void onPageScrollStateChanged(int arg0) {
			}
		});

	}

	protected void activeIndicatorSlideThird() {
		iv1.setVisibility(ImageView.INVISIBLE);
		iv2.setVisibility(ImageView.INVISIBLE);
		iv3.setVisibility(ImageView.VISIBLE);
	}

	protected void activeIndicatorSlideSecond() {
		iv1.setVisibility(ImageView.INVISIBLE);
		iv2.setVisibility(ImageView.VISIBLE);
		iv3.setVisibility(ImageView.INVISIBLE);
	}

	private void activeIndicatorSlideFirst() {
		iv1.setVisibility(ImageView.VISIBLE);
		iv2.setVisibility(ImageView.INVISIBLE);
		iv3.setVisibility(ImageView.INVISIBLE);
	}

	private void createDbWithStaticData() {
		SQLiteDatabase db = openOrCreateDatabase("TestDB", MODE_PRIVATE, null);

		manageZilla(db);
		manageUpozilla(db);
		manageHotline(db);
		manageHospital(db);
		manageDoctor(db);
		manageHealthAdvisor(db);

		db.close();

	}

	private void manageHealthAdvisor(SQLiteDatabase db) {
		createHealthAdvisor(db);
		String healthAdvisors = getResources().getString(
				R.string.healthadvisors);
		ArrayList<String> healthAdvisorList = new ArrayList<String>();
		healthAdvisorList = getListFromString(healthAdvisors);
		insertHealthAdvisor(db, healthAdvisorList);

	}

	private void insertHealthAdvisor(SQLiteDatabase db,
			ArrayList<String> healthAdvisorList) {
		for (int i = 0; i < healthAdvisorList.size(); i++) {
			db.execSQL("INSERT INTO healthadvisor (ha_id, ha_name, ha_contact, hos_id) VALUES('"
					+ healthAdvisorList.get(i)
					+ "','"
					+ healthAdvisorList.get(++i)
					+ "','"
					+ healthAdvisorList.get(++i)
					+ "','"
					+ healthAdvisorList.get(++i) + "')");
		}

	}

	private void createHealthAdvisor(SQLiteDatabase db) {
		deleteTable(db, "healthadvisor");
		db.execSQL("CREATE TABLE IF  NOT EXISTS healthadvisor(ha_id INTEGER PRIMARY KEY ,ha_name VARCHAR, ha_contact VARCHAR, hos_id INTEGER);");

	}

	private void manageDoctor(SQLiteDatabase db) {
		createDoctor(db);
		String doctors = getResources().getString(R.string.doctors);
		ArrayList<String> doctorList = new ArrayList<String>();
		doctorList = getListFromString(doctors);
		insertDoctor(db, doctorList);

	}

	private void insertDoctor(SQLiteDatabase db, ArrayList<String> doctorList) {
		for (int i = 0; i < doctorList.size(); i++) {
			db.execSQL("INSERT INTO doctor (doctor_id,doctor_name ,doctor_contact ,doctor_profile, hos_id) VALUES('"
					+ doctorList.get(i)
					+ "','"
					+ doctorList.get(++i)
					+ "','"
					+ doctorList.get(++i)
					+ "','"
					+ doctorList.get(++i)
					+ "','"
					+ doctorList.get(++i) + "')");
		}

	}

	private void createDoctor(SQLiteDatabase db) {
		deleteTable(db, "doctor");
		db.execSQL("CREATE TABLE IF  NOT EXISTS doctor(doctor_id INTEGER PRIMARY KEY ,doctor_name VARCHAR,doctor_contact VARCHAR,doctor_profile VARCHAR, hos_id INTEGER);");

	}

	private void manageHospital(SQLiteDatabase db) {

		createHospital(db);
		String hospitals = getResources().getString(R.string.hospitals);
		ArrayList<String> hospitalList = new ArrayList<String>();
		hospitalList = getListFromString(hospitals);
		insertHospital(db, hospitalList);
	}

	private void insertHospital(SQLiteDatabase db,
			ArrayList<String> hospitalList) {

		for (int i = 0; i < hospitalList.size(); i++) {
			db.execSQL("INSERT INTO hospital (hos_id ,hos_name ,hos_location, hos_day, hos_time , hos_lat,hos_long, upozilla_id, hos_contact) VALUES('"
					+ hospitalList.get(i)
					+ "','"
					+ hospitalList.get(++i)
					+ "','"
					+ hospitalList.get(++i)
					+ "','"
					+ hospitalList.get(++i)
					+ "','"
					+ hospitalList.get(++i)
					+ "','"
					+ hospitalList.get(++i)
					+ "','"
					+ hospitalList.get(++i)
					+ "','"
					+ hospitalList.get(++i)
					+ "','" + hospitalList.get(++i) + "')");
		}
	}

	private void createHospital(SQLiteDatabase db) {
		deleteTable(db, "hospital");
		db.execSQL("CREATE TABLE IF  NOT EXISTS hospital(hos_id INTEGER PRIMARY KEY ,hos_name VARCHAR,hos_location VARCHAR, hos_day VARCHAR, hos_time VARCHAR, hos_lat REAL, hos_long REAL, upozilla_id INTEGER, hos_contact VARCHAR);");

	}

	private void manageHotline(SQLiteDatabase db) {
		createHotline(db);
		String hotlines = getResources().getString(R.string.hotlines);
		ArrayList<String> hotlineList = new ArrayList<String>();
		hotlineList = getListFromString(hotlines);
		insertHotline(db, hotlineList);
	}

	private void insertHotline(SQLiteDatabase db, ArrayList<String> hotlineList) {
		for (int i = 0; i < hotlineList.size(); i++) {
			db.execSQL("INSERT INTO hotline (hot_id,hot_name,hot_contact, upozilla_id) VALUES('"
					+ hotlineList.get(i)
					+ "','"
					+ hotlineList.get(++i)
					+ "','"
					+ hotlineList.get(++i)
					+ "','"
					+ hotlineList.get(++i)
					+ "')");
		}
	}

	private void createHotline(SQLiteDatabase db) {
		deleteTable(db, "hotline");
		db.execSQL("CREATE TABLE IF  NOT EXISTS hotline(hot_id INTEGER PRIMARY KEY ,hot_name VARCHAR,hot_contact VARCHAR, upozilla_id INTEGER);");

	}

	private void manageUpozilla(SQLiteDatabase db) throws NotFoundException {
		createUpoZilla(db);
		String upozillas = getResources().getString(R.string.upozillas);
		ArrayList<String> upozillaList = new ArrayList<String>();
		upozillaList = getListFromString(upozillas);
		insertUpoZilla(db, upozillaList);
	}

	private void manageZilla(SQLiteDatabase db) throws NotFoundException {
		createZilla(db);
		String zillas = getResources().getString(R.string.zillas);
		ArrayList<String> zillaList = new ArrayList<String>();
		zillaList = getListFromString(zillas);
		insertZilla(db, zillaList);
	}

	private void insertUpoZilla(SQLiteDatabase db,
			ArrayList<String> upozillaList) {

		for (int i = 0; i < upozillaList.size(); i++) {
			db.execSQL("INSERT INTO upozilla (upozilla_id,upozilla_name,zilla_id) VALUES('"
					+ upozillaList.get(i)
					+ "','"
					+ upozillaList.get(++i)
					+ "','" + upozillaList.get(++i) + "')");
		}
	}

	private void createUpoZilla(SQLiteDatabase db) {
		deleteTable(db, "upozilla");
		db.execSQL("CREATE TABLE IF  NOT EXISTS upozilla(upozilla_id INTEGER PRIMARY KEY ,upozilla_name VARCHAR, zilla_id INTEGER);");

	}

	private void createZilla(SQLiteDatabase db) {
		deleteTable(db, "zilla");
		db.execSQL("CREATE TABLE IF  NOT EXISTS zilla(zilla_id INTEGER PRIMARY KEY ,zilla_name VARCHAR);");
	}

	private void insertZilla(SQLiteDatabase db, ArrayList<String> zillaList) {

		for (int i = 0; i < zillaList.size(); i++) {
			db.execSQL("INSERT INTO zilla (zilla_id,zilla_name) VALUES('"
					+ zillaList.get(i) + "','" + zillaList.get(++i) + "')");
		}

	}

	private ArrayList<String> getListFromString(String zillas) {
		ArrayList<String> list = new ArrayList<String>();
		StringTokenizer st = new StringTokenizer(zillas, ",");
		while (st.hasMoreElements()) {
			list.add(st.nextElement().toString());

		}
		return list;
	}

	private void deleteTable(SQLiteDatabase db, String tableName) {
		db.execSQL("DROP TABLE IF EXISTS " + tableName);
	}

	@Override
	protected void onResume() {
		super.onResume();
	}

}
