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
import com.mao.shishu.data.AdvisorData;

public class AdvisorAdapter extends ArrayAdapter<AdvisorData> {
	Context context;
	AdvisorData rowItem;

	public AdvisorAdapter(Context context, int resource, List<AdvisorData> items) {
		super(context, resource, items);
		this.context = context;
	}

	private class ViewHolder {
		TextView tvName;
		TextView tvHospital;
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
			convertView = mInflater.inflate(R.layout.advisor_info, null);
			holder = new ViewHolder();

			holder.tvName = (TextView) convertView
					.findViewById(R.id.tv_advisor_name);
			holder.tvHospital = (TextView) convertView
					.findViewById(R.id.tv_advisor_hospital);
			holder.tvMobile = (TextView) convertView
					.findViewById(R.id.tv_advisor_mobile);

			convertView.setTag(holder);

		} else
			holder = (ViewHolder) convertView.getTag();
		holder.tvName.setText(rowItem.getTitle());
		holder.tvHospital.setText(rowItem.getHospital());
		holder.tvMobile.setText(context.getResources().getString(
				R.string.mobile_number)
				+ rowItem.getMobile());
		return convertView;
	}

}
