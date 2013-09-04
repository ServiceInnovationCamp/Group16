package com.mao.shishu.adapter;

import java.util.List;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import com.mao.shishu.R;
import com.mao.shishu.data.DoctorProfileData;

public class EmmergencyDprofileAdapter extends ArrayAdapter<DoctorProfileData> {
	Context context;
	DoctorProfileData rowItem;

	public EmmergencyDprofileAdapter(Context context, int resource,
			List<DoctorProfileData> items) {
		super(context, resource, items);
		this.context = context;
	}

	private class ViewHolder {
		TextView tvDoctoTitle;
		TextView tvDoctorProfile;
		TextView tvDoctorMobile;
		ImageButton btnShowCall;
		TextView tvDoctorHospital;
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
			convertView = mInflater.inflate(R.layout.doctor_profile_info, null);
			holder = new ViewHolder();

			holder.tvDoctoTitle = (TextView) convertView
					.findViewById(R.id.tv_doctor_profile_title);
			holder.tvDoctorProfile = (TextView) convertView
					.findViewById(R.id.tv_doctor_profile);
			holder.tvDoctorMobile = (TextView) convertView
					.findViewById(R.id.tv_doctor_profile_mobile);
			holder.tvDoctorHospital = (TextView) convertView
					.findViewById(R.id.tv_doctor_hospital);
			convertView.setTag(holder);

		} else
			holder = (ViewHolder) convertView.getTag();
		holder.tvDoctoTitle.setText(rowItem.getTitle());
		holder.tvDoctorProfile.setText("Profile: " + rowItem.getProfile());
		holder.tvDoctorHospital.setText(rowItem.getHospital());
		holder.tvDoctorMobile.setText("Mobile:" + rowItem.getMobile());
		return convertView;
	}

}
