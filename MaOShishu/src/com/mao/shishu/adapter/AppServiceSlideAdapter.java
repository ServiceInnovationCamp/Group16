package com.mao.shishu.adapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.mao.shishu.appservice.AppServiceMainActivity;
import com.mao.shishu.appservice.AppServiceSlideDataViewFragment;

public class AppServiceSlideAdapter extends FragmentPagerAdapter {

	private int PAGE_COUNT = 1;

	public AppServiceSlideAdapter(FragmentManager fm) {
		super(fm);
		/*
		 * if (AppServiceMainActivity.isVideo) PAGE_COUNT = 3; else PAGE_COUNT =
		 * 1;
		 */
	}

	@Override
	public Fragment getItem(int position) {

		AppServiceSlideDataViewFragment myFragment = new AppServiceSlideDataViewFragment();
		Bundle data = new Bundle();
		data.putInt("current_page", position);

		myFragment.setArguments(data);
		return myFragment;

	}

	@Override
	public int getCount() {
		return PAGE_COUNT;
	}

	@Override
	public CharSequence getPageTitle(int position) {
		String header = AppServiceMainActivity.TITLE;
		if (AppServiceMainActivity.isVideo) {
			position = position + 1;
			header = header + " - " + position;
		}
		return header;
	}

}
