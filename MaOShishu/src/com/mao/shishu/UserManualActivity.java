package com.mao.shishu;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class UserManualActivity extends Activity implements
		Animation.AnimationListener {
	private RelativeLayout rlHighlighted;
	private LinearLayout linearLayoutUserManualBack;
	private ImageButton ibBack;
	private ImageView ivBack;
	private Animation animscale;
	private int actionViewId = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.user_manual);

		rlHighlighted = (RelativeLayout) findViewById(R.id.relative_layout_user_manual_highlited);
		linearLayoutUserManualBack = (LinearLayout) findViewById(R.id.linear_layout_user_manual_back);
		ibBack = (ImageButton) findViewById(R.id.imgbtn_back_user_manual);
		ivBack = (ImageView) findViewById(R.id.image_view_back_user_manual);
		linearLayoutUserManualBack
				.setOnClickListener(new View.OnClickListener() {

					@Override
					public void onClick(View arg0) {
						rlHighlighted.setBackgroundColor(Color
								.parseColor("#80f0bc01"));
						ibBack.setImageResource(R.drawable.back_a);
						ivBack.setImageResource(R.drawable.sc_a);
						actionViewId = R.id.linear_layout_user_manual_back;
						ibBack.startAnimation(animscale);
					}
				});

		animscale = AnimationUtils.loadAnimation(this, R.anim.scale);
		animscale.setAnimationListener(this);

	}

	@Override
	public void onAnimationEnd(Animation arg0) {
		switch (actionViewId) {
		case R.id.linear_layout_user_manual_back:
			super.onBackPressed();
			break;
		}
	}

	@Override
	public void onAnimationRepeat(Animation arg0) {

	}

	@Override
	public void onAnimationStart(Animation arg0) {
		switch (actionViewId) {
		case R.id.linear_layout_user_manual_back:
			ivBack.startAnimation(animscale);
			break;
		}

	}
}
