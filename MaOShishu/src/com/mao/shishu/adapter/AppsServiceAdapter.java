package com.mao.shishu.adapter;

import java.util.List;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.mao.shishu.R;
import com.mao.shishu.data.AppServiceData;

public class AppsServiceAdapter extends ArrayAdapter<AppServiceData> {
	Context context;
	AppServiceData rowItem;
	String mCurrentModule;

	public AppsServiceAdapter(Context context, int resource,
			List<AppServiceData> items) {
		super(context, resource, items);
		this.context = context;
	}

	public String getmCurrentModule() {
		return mCurrentModule;
	}

	private class ViewHolder {

		TextView tvTitle;
	}

	@Override
	public int getViewTypeCount() {

		if (getCount() != 0)
			return getCount();

		return 1;
	}

	@Override
	public int getItemViewType(int position) {

		return position;
	}

	@SuppressLint("NewApi")
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		rowItem = getItem(position);
		LayoutInflater mInflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		if (convertView == null) {
			convertView = mInflater.inflate(
					R.layout.app_service_listview_layout, null);
			holder = new ViewHolder();

			holder.tvTitle = (TextView) convertView.findViewById(R.id.tv_title);

			convertView.setTag(holder);
		} else
			holder = (ViewHolder) convertView.getTag();
		holder.tvTitle.setText(rowItem.getTitle());

		return convertView;
	}
}
