package com.mao.shishu.appservice;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.mao.shishu.R;
import com.mao.shishu.SettingActivity;
import com.mao.shishu.adapter.AppsServiceAdapter;
import com.mao.shishu.data.AppServiceData;

public class AppServiceMainActivity extends Activity implements
		Animation.AnimationListener {
	private RelativeLayout rlHighlighted;
	private LinearLayout linearLayoutMainBack;
	private ImageButton ibBack;
	private ImageView ivBack;
	private Animation animscale;
	private int actionViewId = 0;
	private LinearLayout settingWrapper;
	private Button btSettings;
	private AppsServiceAdapter adapter;
	private List<AppServiceData> rowItems;
	private ListView listView;
	public static int CURRENT_PAGE = 0;
	public static String TITLE;
	public static boolean isVideo;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.app_service_main);

		rlHighlighted = (RelativeLayout) findViewById(R.id.relative_layout_main_highlited);
		linearLayoutMainBack = (LinearLayout) findViewById(R.id.linear_layout_main_back);
		ibBack = (ImageButton) findViewById(R.id.imgbtn_back_main);
		ivBack = (ImageView) findViewById(R.id.image_view_back_main);
		settingWrapper = (LinearLayout) findViewById(R.id.linear_layout_setting_wrapper);
		btSettings = (Button) findViewById(R.id.btn_settings);
		listView = (ListView) findViewById(R.id.list_view_app_service);

		linearLayoutMainBack.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				rlHighlighted.setBackgroundColor(Color.parseColor("#80f0bc01"));
				ibBack.setImageResource(R.drawable.back_a);
				ivBack.setImageResource(R.drawable.sc_a);
				actionViewId = R.id.linear_layout_main_back;
				ibBack.startAnimation(animscale);

			}
		});

		settingWrapper.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View view) {
				setBackPanelBackgroundLin(R.id.linear_layout_setting_wrapper);
				btSettings.setBackgroundResource(R.drawable.settings_a);

				AlertDialog.Builder alert = new AlertDialog.Builder(
						AppServiceMainActivity.this);
				alert.setTitle("We need your mobile number");
				alert.setMessage("Please insert your mobile number here: ");
				// Set an EditText view to get user input
				final EditText input = new EditText(AppServiceMainActivity.this);
				input.setInputType(InputType.TYPE_CLASS_PHONE);
				alert.setView(input);
				alert.setPositiveButton("Ok",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int whichButton) {
								startSettingActivity(input);
							}
						});
				alert.setNegativeButton("Cancel",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int whichButton) {
							}
						});
				alert.show();
			}
		});

		rowItems = new ArrayList<AppServiceData>();

		rowItems.add(new AppServiceData(getResources().getString(
				R.string.services)));
		rowItems.add(new AppServiceData(getResources().getString(
				R.string.for_mom_main)));
		rowItems.add(new AppServiceData(getResources().getString(
				R.string.for_child_main)));
		rowItems.add(new AppServiceData(getResources().getString(
				R.string.video_instruction)));
		rowItems.add(new AppServiceData(getResources().getString(
				R.string.immergency_contact)));

		adapter = new AppsServiceAdapter(AppServiceMainActivity.this,
				R.layout.app_service_listview_layout, rowItems);
		listView.setAdapter(adapter);

		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Intent intent = new Intent(AppServiceMainActivity.this,
						AppServiceSlideFragmentActivity.class);

				if (position > 0) {
					CURRENT_PAGE = position - 1;
					if (position == 1) {
						intent.putExtra("MOTHER_SERVICE", true);
						isVideo = false;
					} else if (position == 2) {
						intent.putExtra("CHILD_SERVICE", true);
						isVideo = false;
					} else if (position == 3) {
						intent.putExtra("VIDEO_SERVICE", true);
						isVideo = true;
						CURRENT_PAGE = 0;
					} else if (position == 4) {
						intent.putExtra("IMMERGENCY_SERVICE", true);
						isVideo = false;
					}
					TITLE = rowItems.get(position).getTitle();
					startActivity(intent);
				}
			}
		});

		SQLiteDatabase db = openOrCreateDatabase("TestDB", MODE_PRIVATE, null);
		Cursor result = db.rawQuery("select * from users", null);
		Log.d("RESULT", "" + result);

		while (result.moveToNext()) {
			String userName = result.getString(3);
			Log.d("username", userName);
		}

		animscale = AnimationUtils.loadAnimation(this, R.anim.scale);
		animscale.setAnimationListener(this);

	}

	private void startSettingActivity(final EditText input) {
		String result = input.getText().toString();
		Intent intent = new Intent(AppServiceMainActivity.this,
				SettingActivity.class);
		intent.putExtra("PHONE_NUMBER", result);
		startActivity(intent);
	}

	protected void setBackPanelBackgroundLin(int animView) {
		settingWrapper.setBackgroundColor(Color.parseColor("#80f0bc01"));
		actionViewId = animView;
		findViewById(R.id.btn_settings).startAnimation(animscale);
	}

	@Override
	public void onAnimationEnd(Animation arg0) {
		switch (actionViewId) {
		case R.id.linear_layout_main_back:
			super.onBackPressed();
			break;

		case R.id.linear_layout_setting_wrapper:
			findViewById(R.id.linear_layout_setting_wrapper)
					.setBackgroundColor(Color.TRANSPARENT);
			break;
		}

	}

	@Override
	public void onAnimationRepeat(Animation arg0) {
	}

	@Override
	public void onAnimationStart(Animation arg0) {
		switch (actionViewId) {
		case R.id.linear_layout_main_back:
			ivBack.startAnimation(animscale);
			break;
		}

	}

}
