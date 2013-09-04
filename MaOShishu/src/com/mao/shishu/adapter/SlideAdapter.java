package com.mao.shishu.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mao.shishu.R;

public class SlideAdapter extends PagerAdapter {
	private LayoutInflater mInflater;
	private Context mContext;
	private static int[] mLayouts = { R.layout.slide_layout_one,
			R.layout.slide_layout_two, R.layout.slide_layout_three };

	public SlideAdapter(Context context) {
		mInflater = LayoutInflater.from(context);
		this.mContext = context;
	}

	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		container.removeView((View) object);
	}

	@Override
	public Object instantiateItem(ViewGroup container, int position) {

		ViewGroup pageView = (ViewGroup) mInflater.inflate(mLayouts[position],
				container, false);
		container.addView(pageView);
		getItemPosition(pageView);

		return pageView; 
	}

	@Override
	public int getCount() {
		return mLayouts.length;
	}

	@Override
	public boolean isViewFromObject(View view, Object obj) {
		return view == obj;
	}
}