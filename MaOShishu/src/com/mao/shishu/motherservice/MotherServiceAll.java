package com.mao.shishu.motherservice;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.mao.shishu.R;

public class MotherServiceAll extends ListActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mother_service_all);

		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, getResources()
						.getStringArray(R.array.mothe_service_all));
		setListAdapter(adapter);

	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
		Intent intent = new Intent(MotherServiceAll.this,
				MotherServiceSingleActivity.class);
		if (position == 0) {
			intent.putExtra("POSITION", position);
		} else if (position == 1) {
			intent.putExtra("POSITION", position);
		} else if (position == 2) {
			intent.putExtra("POSITION", position);
		} else if (position == 3) {
			intent.putExtra("POSITION", position);
		} else if (position == 4) {
			intent.putExtra("POSITION", position);
		}
		startActivity(intent);

	}

}
