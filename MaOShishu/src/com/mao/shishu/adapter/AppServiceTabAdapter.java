package com.mao.shishu.adapter;

import java.util.List;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;

import com.mao.shishu.R;
import com.mao.shishu.data.AppServiceData;

public class AppServiceTabAdapter extends ArrayAdapter<AppServiceData> {
	Context context;
	AppServiceData rowItem;

	public AppServiceTabAdapter(Context context, int resource,
			List<AppServiceData> items) {
		super(context, resource, items);
		this.context = context;
	}

	private class ViewHolder {
		Button btnServices;
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
					R.layout.app_service_fragment_list_layout, null);
			holder = new ViewHolder();

			holder.btnServices = (Button) convertView
					.findViewById(R.id.btn_app_service);

			convertView.setTag(holder);

		} else
			holder = (ViewHolder) convertView.getTag();
		holder.btnServices.setText(rowItem.getTitle());
		return convertView;
	}

}
