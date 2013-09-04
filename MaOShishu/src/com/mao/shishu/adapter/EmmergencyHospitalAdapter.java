package com.mao.shishu.adapter;

import java.util.List;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.sax.StartElementListener;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.android.gms.internal.cn;
import com.mao.shishu.HospitalMapActivity;
import com.mao.shishu.R;
import com.mao.shishu.data.EmmergencyHostpitalData;

public class EmmergencyHospitalAdapter extends
		ArrayAdapter<EmmergencyHostpitalData> {
	Context context;
	EmmergencyHostpitalData rowItem;

	public EmmergencyHospitalAdapter(Context context, int resource,
			List<EmmergencyHostpitalData> items) {
		super(context, resource, items);
		this.context = context;
	}

	private class ViewHolder {
		TextView tvHostpitalName;
		TextView tvLocation;
		TextView tvOpen;
		TextView tvTime;
		TextView tvMobileNumber;
		ImageButton btnShowMap;
		ImageButton btnShowCall;
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
			convertView = mInflater.inflate(R.layout.emmergency_hospital_info,
					null);
			holder = new ViewHolder();

			holder.tvHostpitalName = (TextView) convertView
					.findViewById(R.id.tv_hospital_name);
			holder.tvLocation = (TextView) convertView
					.findViewById(R.id.tv_location);
			holder.tvOpen = (TextView) convertView.findViewById(R.id.tv_open);
			holder.tvTime = (TextView) convertView.findViewById(R.id.tv_time);
			holder.tvMobileNumber = (TextView) convertView
					.findViewById(R.id.tv_mobile_number);
			holder.btnShowMap = (ImageButton) convertView
					.findViewById(R.id.bt_show_map);
			holder.btnShowCall = (ImageButton) convertView
					.findViewById(R.id.bt_show_call);
			convertView.setTag(holder);

		} else
			holder = (ViewHolder) convertView.getTag();
		holder.tvHostpitalName.setText(rowItem.getHospitalName());
		holder.tvLocation.setText(context.getResources().getString(
				R.string.location)
				+ rowItem.getLocation());
		holder.tvOpen.setText(context.getResources().getString(
				R.string.openclose_time)
				+ rowItem.getOpen());
		holder.tvTime.setText(context.getResources().getString(
				R.string.service_time)
				+ rowItem.getTime());
		holder.tvMobileNumber.setText(context.getResources().getString(
				R.string.mobile_number)
				+ rowItem.getMobileNumber());
		holder.btnShowMap.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				context.startActivity(new Intent(context,
						HospitalMapActivity.class));
			}
		});

		holder.btnShowCall.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(Intent.ACTION_CALL);
				intent.setData(Uri.parse("tel:" + rowItem.getMobileNumber()));
				context.startActivity(intent);
			}
		});

		return convertView;
	}

}
