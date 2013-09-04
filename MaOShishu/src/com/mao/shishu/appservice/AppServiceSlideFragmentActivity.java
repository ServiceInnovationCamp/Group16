package com.mao.shishu.appservice;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

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
