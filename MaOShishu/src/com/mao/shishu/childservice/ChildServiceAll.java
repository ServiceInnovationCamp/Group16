package com.mao.shishu.childservice;

import com.mao.shishu.R;
import com.mao.shishu.motherservice.MotherServiceAll;
import com.mao.shishu.motherservice.MotherServiceSingleActivity;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.app.Activity;

public class ChildServiceAll extends ListActivity {

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.child_service_all);

		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, getResources()
						.getStringArray(R.array.child_service_all));
		setListAdapter(adapter);

	}
	
	protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
		Intent intent = new Intent(ChildServiceAll.this,
				ChildServiceSingleActivity.class);
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