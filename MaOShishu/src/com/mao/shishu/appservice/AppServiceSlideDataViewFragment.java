package com.mao.shishu.appservice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.mao.shishu.AdvisorActivity;
import com.mao.shishu.DoctorProfileActivity;
import com.mao.shishu.HotLineActivity;
import com.mao.shishu.R;
import com.mao.shishu.motherservice.MotherServiceActivity;

public class AppServiceSlideDataViewFragment extends Fragment {
	private WebView webView;

	private Bundle extras;
	boolean isMotherService, isChildService, isVideoService,
			isImmergencyService;
	String mainService;

	private Spinner spZilla;
	private Spinner spUpoZilla;
	private Spinner spType;
	private Button btMore;

	private ArrayAdapter<String> adapterZilla;
	private ArrayAdapter<String> adapterUpoZilla;
	private ArrayAdapter<String> adapterImmergencyType;
	private String spinnerTextFirst;
	private String spinnerTextSecond;
	private int InfoTypePosition;
	private HashMap<Integer, String> hashmap;
	private ArrayList<String> upoZillaName;
	int upoZillaId;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		extras = getActivity().getIntent().getExtras();
		if (extras != null) {
			isMotherService = extras.getBoolean("MOTHER_SERVICE");
			isChildService = extras.getBoolean("CHILD_SERVICE");
			isVideoService = extras.getBoolean("VIDEO_SERVICE");
			isImmergencyService = extras.getBoolean("IMMERGENCY_SERVICE");
		}
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.app_service_fragment, container,
				false);

		webView = (WebView) v.findViewById(R.id.webview);

		spZilla = (Spinner) v.findViewById(R.id.sp_zilla_immergency);
		spUpoZilla = (Spinner) v.findViewById(R.id.sp_upozilla_immergency);
		spType = (Spinner) v.findViewById(R.id.sp_type_immergency);

		btMore = (Button) v.findViewById(R.id.btn_continue_reading);

		if (isMotherService) {
			mainService = "<html><body>"
					+ getResources().getString(R.string.food_list)
					+ "<ul><li>Trim, tailored fit for a bespoke feel</li><li>Medium spread collar, one-button mitered barrel cuffs</li><li>Applied placket with genuine mother-of-pearl buttons</li><li>;Split back yoke, rear side pleats</li><li>Made in the U.S.A. of 100% imported cotton.</li></ul>"
					+ getResources().getString(R.string.suggested_work)
					+ "<ul><li>Trim, tailored fit for a bespoke feel</li><li>Medium spread collar, one-button mitered barrel cuffs</li><li>Applied placket with genuine mother-of-pearl buttons</li><li>;Split back yoke, rear side pleats</li><li>Made in the U.S.A. of 100% imported cotton.</li></ul>"
					+ getResources().getString(R.string.avaoidable_work)
					+ "<ul><li>Trim, tailored fit for a bespoke feel</li><li>Medium spread collar, one-button mitered barrel cuffs</li><li>Applied placket with genuine mother-of-pearl buttons</li><li>;Split back yoke, rear side pleats</li><li>Made in the U.S.A. of 100% imported cotton.</li></ul>"
					+ getResources().getString(R.string.vaccination_info)
					+ "<ul><li>Trim, tailored fit for a bespoke feel</li><li>Medium spread collar, one-button mitered barrel cuffs</li><li>Applied placket with genuine mother-of-pearl buttons</li><li>;Split back yoke, rear side pleats</li><li>Made in the U.S.A. of 100% imported cotton.</li></ul></body></html>";
		} else if (isChildService) {

		} else if (isVideoService) {
			btMore.setVisibility(View.GONE);

		} else if (isImmergencyService) {
			webView.setVisibility(View.GONE);
			spType.setVisibility(View.VISIBLE);
			spUpoZilla.setVisibility(View.VISIBLE);
			spZilla.setVisibility(View.VISIBLE);

			adapterZilla = new ArrayAdapter<String>(getActivity(),
					android.R.layout.simple_spinner_item,
					loadSpinnerDataZilla());
			adapterZilla
					.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
			spZilla.setAdapter(adapterZilla);
			int pos = adapterZilla.getPosition(spinnerTextFirst);
			spZilla.setSelection(pos);

			spZilla.setOnItemSelectedListener(new OnItemSelectedListener() {

				@Override
				public void onItemSelected(AdapterView<?> parent, View view,
						int position, long id) {

					hashmap = loadSpinnerDataUpoZilla(position + 1);

					upoZillaName = new ArrayList<String>();
					Set<Integer> set = hashmap.keySet();
					Iterator<Integer> iterator = set.iterator();
					while (iterator.hasNext()) {
						Integer keyMap = iterator.next();
						upoZillaName.add(hashmap.get(keyMap));
					}
					adapterUpoZilla = new ArrayAdapter<String>(getActivity(),
							android.R.layout.simple_spinner_item, upoZillaName);
					adapterUpoZilla
							.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
					spUpoZilla.setAdapter(adapterUpoZilla);
					int pos = adapterUpoZilla.getPosition(spinnerTextSecond);
					spUpoZilla.setSelection(pos);
				}

				@Override
				public void onNothingSelected(AdapterView<?> arg0) {
				}
			});

			adapterImmergencyType = new ArrayAdapter<String>(getActivity(),
					android.R.layout.simple_spinner_item, getResources()
							.getStringArray(R.array.immergency_info_type_array));
			adapterImmergencyType
					.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
			spType.setAdapter(adapterImmergencyType);
			btMore.setText(getResources().getString(
					R.string.show_imergency_info_btn_text));

		}

		webView.loadDataWithBaseURL(null, mainService, "text/html", "utf-8",
				null);

		btMore.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				Intent activityHospital = new Intent(getActivity(),
						MotherServiceActivity.class);
				Intent activityHotline = new Intent(getActivity(),
						HotLineActivity.class);
				Intent activityDProfile = new Intent(getActivity(),
						DoctorProfileActivity.class);
				Intent activityAdvisor = new Intent(getActivity(),
						AdvisorActivity.class);
				if (isImmergencyService) {
					getUpoZillaId();

					if (spType.getSelectedItemPosition() == 0) {
						activityHospital.putExtra("UPOZILLA_ID", upoZillaId);
						activityHospital.putExtra("TABLE_NAME", "hospital");
						startActivity(activityHospital);
					} else if (spType.getSelectedItemPosition() == 1) {
						activityDProfile.putExtra("UPOZILLA_ID", upoZillaId);
						activityDProfile.putExtra("TABLE_NAME", "doctor");
						startActivity(activityDProfile);
					} else if (spType.getSelectedItemPosition() == 2) {
						activityAdvisor.putExtra("UPOZILLA_ID", upoZillaId);
						activityAdvisor.putExtra("TABLE_NAME", "healthadvisor");
						startActivity(activityAdvisor);
					} else if (spType.getSelectedItemPosition() == 3) {
						activityHotline.putExtra("UPOZILLA_ID", upoZillaId);
						activityHotline.putExtra("TABLE_NAME", "hotline");
						startActivity(activityHotline);
					}

				}

			}
		});

		return v;
	}

	public ArrayList<String> loadSpinnerDataZilla() {
		SQLiteDatabase db = getActivity().openOrCreateDatabase("TestDB",
				getActivity().MODE_PRIVATE, null);
		Cursor cursor = db.rawQuery("select * from zilla", null);
		ArrayList<String> labels = new ArrayList<String>();
		if (cursor.moveToFirst()) {
			do {
				labels.add(cursor.getString(1));
			} while (cursor.moveToNext());
		}
		cursor.close();
		db.close();
		return labels;
	}

	private HashMap<Integer, String> loadSpinnerDataUpoZilla(int id) {
		SQLiteDatabase db = getActivity().openOrCreateDatabase("TestDB",
				getActivity().MODE_PRIVATE, null);

		HashMap<Integer, String> values = new HashMap<Integer, String>();

		Cursor cursor = db.rawQuery("select * from upozilla where zilla_id='"
				+ id + "'", null);
		if (cursor.moveToFirst()) {
			do {
				values.put(cursor.getInt(0), cursor.getString(1));
			} while (cursor.moveToNext());
		}
		cursor.close();
		db.close();
		return values;
	}

	private void getUpoZillaId() {
		for (Entry<Integer, String> entry : hashmap.entrySet()) {
			if (entry.getValue()
					.equals(spUpoZilla.getSelectedItem().toString())) {
				upoZillaId = entry.getKey();
			}
		}
	}

}
