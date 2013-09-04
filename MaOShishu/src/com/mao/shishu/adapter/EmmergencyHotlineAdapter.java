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
import com.mao.shishu.data.EmmergencyHotlineData;

public class EmmergencyHotlineAdapter extends
		ArrayAdapter<EmmergencyHotlineData> {
	Context context;
	EmmergencyHotlineData rowItem;

	public EmmergencyHotlineAdapter(Context context, int resource,
			List<EmmergencyHotlineData> items) {
		super(context, resource, items);
		this.context = context;
	}

	private class ViewHolder {
		TextView tvHostpitalName;
		TextView tvMobile;
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
			convertView = mInflater.inflate(R.layout.hotline_info, null);
			holder = new ViewHolder();

			holder.tvHostpitalName = (TextView) convertView
					.findViewById(R.id.tv_hotline_name);
			holder.tvMobile = (TextView) convertView
					.findViewById(R.id.tv_hotline_mobile_number);
			convertView.setTag(holder);

		} else
			holder = (ViewHolder) convertView.getTag();
		holder.tvHostpitalName.setText(rowItem.getTitle());
		holder.tvMobile.setText(context.getResources().getString(
				R.string.mobile_number)
				+ rowItem.getMobile());
		return convertView;
	}

}
