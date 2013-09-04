package com.mao.shishu;

import java.util.ArrayList;
import java.util.Calendar;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;

import com.mao.shishu.appservice.AppServiceMainActivity;

public class SignUpActivity extends Activity implements
		Animation.AnimationListener {
	private Animation animscale;
	private int actionViewId = 0;
	private RelativeLayout rlHighlighted;
	private LinearLayout linearLayoutSignUpBack;
	private ImageButton ibBack;
	private ImageView ivBack;
	private Button btSignUp;

	private int mYear;
	private int mMonth;
	private int mDay;
	static final int DATE_DIALOG_ID = 0;
	private Spinner spinnerZilla;
	private Spinner spinnerUpoZilla;
	private ArrayAdapter<String> adapterZilla;
	private ArrayAdapter<String> adapterUpoZilla;
	private EditText etName;
	private EditText etPragnencyDate;
	private EditText etMobileNumberOne;
	private EditText etMobileNumberTwo;
	private Bundle extras;
	private boolean isChild;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sign_up);

		rlHighlighted = (RelativeLayout) findViewById(R.id.relative_layout_signup_highlited);
		linearLayoutSignUpBack = (LinearLayout) findViewById(R.id.linear_layout_signup_back);
		ibBack = (ImageButton) findViewById(R.id.imgbtn_back_signup);
		ivBack = (ImageView) findViewById(R.id.image_view_back_signup);

		linearLayoutSignUpBack.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				rlHighlighted.setBackgroundColor(Color.parseColor("#80f0bc01"));
				ibBack.setImageResource(R.drawable.back_a);
				ivBack.setImageResource(R.drawable.sc_a);
				actionViewId = R.id.linear_layout_signup_back;
				ibBack.startAnimation(animscale);
			}
		});

		extras = getIntent().getExtras();
		if (extras != null) {
			if (extras.getBoolean("isForMom"))
				isChild = false;
			else
				isChild = true;
		}

		etName = (EditText) findViewById(R.id.et_name);
		etPragnencyDate = (EditText) findViewById(R.id.et_pregnancy_date);
		etMobileNumberOne = (EditText) findViewById(R.id.et_mobile_number_1);
		etMobileNumberTwo = (EditText) findViewById(R.id.et_mobile_number_2);

		if (isChild) {
			etPragnencyDate.setHint(getResources().getString(
					R.string.child_birth_date));
		}

		etPragnencyDate.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				showDialog(DATE_DIALOG_ID);
			}
		});

		final Calendar c = Calendar.getInstance();
		mYear = c.get(Calendar.YEAR);
		mMonth = c.get(Calendar.MONTH);
		mDay = c.get(Calendar.DAY_OF_MONTH);

		spinnerZilla = (Spinner) findViewById(R.id.sp_zilla);
		spinnerUpoZilla = (Spinner) findViewById(R.id.sp_upozilla);
		// Create an ArrayAdapter using the string array and a default spinner
		// layout
		adapterZilla = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, loadSpinnerDataZilla());
		adapterZilla
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinnerZilla.setAdapter(adapterZilla);

		spinnerZilla.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				adapterUpoZilla = new ArrayAdapter<String>(SignUpActivity.this,
						android.R.layout.simple_spinner_item,
						loadSpinnerDataUpoZilla(position + 1));
				adapterUpoZilla
						.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
				spinnerUpoZilla.setAdapter(adapterUpoZilla);
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {

			}
		});

		btSignUp = (Button) findViewById(R.id.btn_sign_up);
		btSignUp.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				int flag = (isChild == true) ? 1 : 0;
				createUserTable(flag);
				startActivity(new Intent(SignUpActivity.this,
						AppServiceMainActivity.class));

				finish();
			}
		});

		animscale = AnimationUtils.loadAnimation(this, R.anim.scale);
		animscale.setAnimationListener(this);
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

	@Override
	public void onAnimationEnd(Animation animation) {
		if (actionViewId == R.id.linear_layout_signup_back) {
			super.onBackPressed();
		}
	}

	@Override
	public void onAnimationRepeat(Animation animation) {
	}

	@Override
	public void onAnimationStart(Animation animation) {
		switch (actionViewId) {
		case R.id.linear_layout_signup_back:
			ivBack.startAnimation(animscale);
			break;
		}
	}

	DatePickerDialog.OnDateSetListener mDateSetListner = new DatePickerDialog.OnDateSetListener() {

		@Override
		public void onDateSet(DatePicker view, int year, int monthOfYear,
				int dayOfMonth) {
			mYear = year;
			mMonth = monthOfYear;
			mDay = dayOfMonth;
			updateDate();
		}
	};

	protected void updateDate() {
		etPragnencyDate.setText(new StringBuilder()
				// Month is 0 based so add 1
				.append(mMonth + 1).append("/").append(mDay).append("/")
				.append(mYear).append(" "));
		showDialog(DATE_DIALOG_ID);
	}

	@Override
	protected Dialog onCreateDialog(int id) {
		switch (id) {
		case DATE_DIALOG_ID:
			return new DatePickerDialog(this, mDateSetListner, mYear, mMonth,
					mDay);
		}
		return null;
	}

	private void createUserTable(int flag) throws SQLException {
		SQLiteDatabase db = openOrCreateDatabase("TestDB", MODE_PRIVATE, null);

		db.execSQL("DROP TABLE IF EXISTS users");
		db.execSQL("CREATE TABLE IF  NOT EXISTS users(user_name VARCHAR,pregnancy_date VARCHAR,district VARCHAR,sub_disctrict VARCHAR,mobile_one VERCHAR PRIMARY KEY,mobile_two VERCHAR,isChild INTEGER);");

		db.execSQL("INSERT INTO users (user_name, pregnancy_date, district,sub_disctrict,mobile_one,mobile_two,isChild) VALUES('"
				+ etName.getText().toString()
				+ "','"
				+ etPragnencyDate.getText().toString()
				+ "','"
				+ spinnerZilla.getSelectedItem().toString()
				+ "','"
				+ spinnerUpoZilla.getSelectedItem().toString()
				+ "','"
				+ etMobileNumberOne.getText().toString()
				+ "','"
				+ etMobileNumberTwo.getText().toString() + "','" + flag + "')");
		db.close();
	}

}
