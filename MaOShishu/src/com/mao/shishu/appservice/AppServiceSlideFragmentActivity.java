package com.mao.shishu.appservice;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import org.joda.time.DateTime;
import org.joda.time.Days;

import com.mao.shishu.R;
import com.mao.shishu.adapter.AppServiceSlideAdapter;

@SuppressLint("NewApi")
public class AppServiceSlideFragmentActivity extends FragmentActivity implements
		Animation.AnimationListener, OnClickListener {
	private Animation animscale;
	private int actionViewId = 0;
	private RelativeLayout rlHighlighted;
	private LinearLayout linearLayoutBack;
	private ImageButton ibBack;
	private ImageView ivBack;
	ViewPager pager;
	public static String dayDifferent;

	public static int pagenumber;

	@SuppressLint({ "NewApi", "SimpleDateFormat" })
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.slide_tab_main);
		pager = (ViewPager) findViewById(R.id.view_pager_module);

		FragmentManager fm = getSupportFragmentManager();

		AppServiceSlideAdapter pagerAdapter = new AppServiceSlideAdapter(fm);

		pager.setAdapter(pagerAdapter);

		pager.setCurrentItem(AppServiceMainActivity.CURRENT_PAGE);

		SQLiteDatabase db = openOrCreateDatabase("TestDB", MODE_PRIVATE, null);
		Cursor cursor = db.rawQuery("select * from users", null);
		String date = null;
		if (cursor.moveToFirst()) {
			do {
				date = cursor.getString(1);
			} while (cursor.moveToNext());
		}

		String dateStart = date;
		String dateStop = new SimpleDateFormat("MM/dd/yyyy").format(Calendar
				.getInstance().getTime());

		SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
		java.util.Date d1 = null;
		java.util.Date d2 = null;

		try {
			d1 = format.parse(dateStart);
			d2 = format.parse(dateStop);

			DateTime dt1 = new DateTime(d1);
			DateTime dt2 = new DateTime(d2);
			dayDifferent = "" + Days.daysBetween(dt1, dt2).getDays();

		} catch (Exception e) {
			e.printStackTrace();
		}

		animscale = AnimationUtils.loadAnimation(this, R.anim.scale);
		animscale.setAnimationListener(this);

	}

	@Override
	public void onClick(View v) {

	}

	@Override
	public void onAnimationEnd(Animation animation) {
	}

	@Override
	public void onAnimationRepeat(Animation animation) {

	}

	@Override
	public void onAnimationStart(Animation animation) {
	}

}
