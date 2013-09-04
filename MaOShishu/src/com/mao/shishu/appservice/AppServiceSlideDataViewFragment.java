package com.mao.shishu.appservice;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;

import org.joda.time.DateTime;
import org.joda.time.Days;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebView.FindListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.Spinner;
import android.widget.VideoView;

import com.mao.shishu.AdvisorActivity;
import com.mao.shishu.DoctorProfileActivity;
import com.mao.shishu.HotLineActivity;
import com.mao.shishu.R;
import com.mao.shishu.childservice.ChildServiceAll;
import com.mao.shishu.motherservice.MotherServiceActivity;
import com.mao.shishu.motherservice.MotherServiceAll;

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
	private int upoZillaId;
	private String dayDifferent;
	private VideoView video;

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
		video = (VideoView) v.findViewById(R.id.video_app_service);
		spZilla = (Spinner) v.findViewById(R.id.sp_zilla_immergency);
		spUpoZilla = (Spinner) v.findViewById(R.id.sp_upozilla_immergency);
		spType = (Spinner) v.findViewById(R.id.sp_type_immergency);

		btMore = (Button) v.findViewById(R.id.btn_continue_reading);

		if (isMotherService) {
			if (getDays() >= 1 && getDays() <= 180) {
				mainService = "<html><body>"
						+ getResources().getString(R.string.food_list)
						+ "<ul><li>(three to six month), tailored fit for a bespoke feel</li><li>Medium spread collar, one-button mitered barrel cuffs</li><li>Applied placket with genuine mother-of-pearl buttons</li><li>;Split back yoke, rear side pleats</li><li>Made in the U.S.A. of 100% imported cotton.</li></ul>"
						+ getResources().getString(R.string.suggested_work)
						+ "<ul><li>Trim, tailored fit for a bespoke feel</li><li>Medium spread collar, one-button mitered barrel cuffs</li><li>Applied placket with genuine mother-of-pearl buttons</li><li>;Split back yoke, rear side pleats</li><li>Made in the U.S.A. of 100% imported cotton.</li></ul>"
						+ getResources().getString(R.string.avaoidable_work)
						+ "<ul><li>Trim, tailored fit for a bespoke feel</li><li>Medium spread collar, one-button mitered barrel cuffs</li><li>Applied placket with genuine mother-of-pearl buttons</li><li>;Split back yoke, rear side pleats</li><li>Made in the U.S.A. of 100% imported cotton.</li></ul>"
						+ getResources().getString(R.string.vaccination_info)
						+ "<ul><li>Trim, tailored fit for a bespoke feel</li><li>Medium spread collar, one-button mitered barrel cuffs</li><li>Applied placket with genuine mother-of-pearl buttons</li><li>;Split back yoke, rear side pleats</li><li>Made in the U.S.A. of 100% imported cotton.</li></ul></body></html>";
			} else if (getDays() >= 181 && getDays() <= 270) {
				mainService = "<html><body>"
						+ getResources().getString(R.string.food_list)
						+ "<ul><li>(seven to three ten), tailored fit for a bespoke feel</li><li>Medium spread collar, one-button mitered barrel cuffs</li><li>Applied placket with genuine mother-of-pearl buttons</li><li>;Split back yoke, rear side pleats</li><li>Made in the U.S.A. of 100% imported cotton.</li></ul>"
						+ getResources().getString(R.string.suggested_work)
						+ "<ul><li>Trim, tailored fit for a bespoke feel</li><li>Medium spread collar, one-button mitered barrel cuffs</li><li>Applied placket with genuine mother-of-pearl buttons</li><li>;Split back yoke, rear side pleats</li><li>Made in the U.S.A. of 100% imported cotton.</li></ul>"
						+ getResources().getString(R.string.avaoidable_work)
						+ "<ul><li>Trim, tailored fit for a bespoke feel</li><li>Medium spread collar, one-button mitered barrel cuffs</li><li>Applied placket with genuine mother-of-pearl buttons</li><li>;Split back yoke, rear side pleats</li><li>Made in the U.S.A. of 100% imported cotton.</li></ul>"
						+ getResources().getString(R.string.vaccination_info)
						+ "<ul><li>Trim, tailored fit for a bespoke feel</li><li>Medium spread collar, one-button mitered barrel cuffs</li><li>Applied placket with genuine mother-of-pearl buttons</li><li>;Split back yoke, rear side pleats</li><li>Made in the U.S.A. of 100% imported cotton.</li></ul></body></html>";
			} else {
				mainService = "No data available";
			}
		} else if (isChildService) {
			if (getDays() >= 1 && getDays() <= 90) {
				mainService = "<html><body>"
						+ getResources().getString(R.string.food_list)
						+ "<ul><li>(three to six month), tailored fit for a bespoke feel</li><li>Medium spread collar, one-button mitered barrel cuffs</li><li>Applied placket with genuine mother-of-pearl buttons</li><li>;Split back yoke, rear side pleats</li><li>Made in the U.S.A. of 100% imported cotton.</li></ul>"
						+ getResources().getString(R.string.suggested_work)
						+ "<ul><li>Trim, tailored fit for a bespoke feel</li><li>Medium spread collar, one-button mitered barrel cuffs</li><li>Applied placket with genuine mother-of-pearl buttons</li><li>;Split back yoke, rear side pleats</li><li>Made in the U.S.A. of 100% imported cotton.</li></ul>"
						+ getResources().getString(R.string.avaoidable_work)
						+ "<ul><li>Trim, tailored fit for a bespoke feel</li><li>Medium spread collar, one-button mitered barrel cuffs</li><li>Applied placket with genuine mother-of-pearl buttons</li><li>;Split back yoke, rear side pleats</li><li>Made in the U.S.A. of 100% imported cotton.</li></ul>"
						+ getResources().getString(R.string.vaccination_info)
						+ "<ul><li>Trim, tailored fit for a bespoke feel</li><li>Medium spread collar, one-button mitered barrel cuffs</li><li>Applied placket with genuine mother-of-pearl buttons</li><li>;Split back yoke, rear side pleats</li><li>Made in the U.S.A. of 100% imported cotton.</li></ul></body></html>";
			} else if (getDays() >= 91 && getDays() <= 180) {
				mainService = "<html><body>"
						+ getResources().getString(R.string.food_list)
						+ "<ul><li>(seven to three ten), tailored fit for a bespoke feel</li><li>Medium spread collar, one-button mitered barrel cuffs</li><li>Applied placket with genuine mother-of-pearl buttons</li><li>;Split back yoke, rear side pleats</li><li>Made in the U.S.A. of 100% imported cotton.</li></ul>"
						+ getResources().getString(R.string.suggested_work)
						+ "<ul><li>Trim, tailored fit for a bespoke feel</li><li>Medium spread collar, one-button mitered barrel cuffs</li><li>Applied placket with genuine mother-of-pearl buttons</li><li>;Split back yoke, rear side pleats</li><li>Made in the U.S.A. of 100% imported cotton.</li></ul>"
						+ getResources().getString(R.string.avaoidable_work)
						+ "<ul><li>Trim, tailored fit for a bespoke feel</li><li>Medium spread collar, one-button mitered barrel cuffs</li><li>Applied placket with genuine mother-of-pearl buttons</li><li>;Split back yoke, rear side pleats</li><li>Made in the U.S.A. of 100% imported cotton.</li></ul>"
						+ getResources().getString(R.string.vaccination_info)
						+ "<ul><li>Trim, tailored fit for a bespoke feel</li><li>Medium spread collar, one-button mitered barrel cuffs</li><li>Applied placket with genuine mother-of-pearl buttons</li><li>;Split back yoke, rear side pleats</li><li>Made in the U.S.A. of 100% imported cotton.</li></ul></body></html>";
			} else if (getDays() >= 181 && getDays() <= 365) {
				mainService = "<html><body>"
						+ getResources().getString(R.string.food_list)
						+ "<ul><li>(seven to three ten), tailored fit for a bespoke feel</li><li>Medium spread collar, one-button mitered barrel cuffs</li><li>Applied placket with genuine mother-of-pearl buttons</li><li>;Split back yoke, rear side pleats</li><li>Made in the U.S.A. of 100% imported cotton.</li></ul>"
						+ getResources().getString(R.string.suggested_work)
						+ "<ul><li>Trim, tailored fit for a bespoke feel</li><li>Medium spread collar, one-button mitered barrel cuffs</li><li>Applied placket with genuine mother-of-pearl buttons</li><li>;Split back yoke, rear side pleats</li><li>Made in the U.S.A. of 100% imported cotton.</li></ul>"
						+ getResources().getString(R.string.avaoidable_work)
						+ "<ul><li>Trim, tailored fit for a bespoke feel</li><li>Medium spread collar, one-button mitered barrel cuffs</li><li>Applied placket with genuine mother-of-pearl buttons</li><li>;Split back yoke, rear side pleats</li><li>Made in the U.S.A. of 100% imported cotton.</li></ul>"
						+ getResources().getString(R.string.vaccination_info)
						+ "<ul><li>Trim, tailored fit for a bespoke feel</li><li>Medium spread collar, one-button mitered barrel cuffs</li><li>Applied placket with genuine mother-of-pearl buttons</li><li>;Split back yoke, rear side pleats</li><li>Made in the U.S.A. of 100% imported cotton.</li></ul></body></html>";
			} else if (getDays() >= 366 && getDays() <= 545) {
				mainService = "<html><body>"
						+ getResources().getString(R.string.food_list)
						+ "<ul><li>(seven to three ten), tailored fit for a bespoke feel</li><li>Medium spread collar, one-button mitered barrel cuffs</li><li>Applied placket with genuine mother-of-pearl buttons</li><li>;Split back yoke, rear side pleats</li><li>Made in the U.S.A. of 100% imported cotton.</li></ul>"
						+ getResources().getString(R.string.suggested_work)
						+ "<ul><li>Trim, tailored fit for a bespoke feel</li><li>Medium spread collar, one-button mitered barrel cuffs</li><li>Applied placket with genuine mother-of-pearl buttons</li><li>;Split back yoke, rear side pleats</li><li>Made in the U.S.A. of 100% imported cotton.</li></ul>"
						+ getResources().getString(R.string.avaoidable_work)
						+ "<ul><li>Trim, tailored fit for a bespoke feel</li><li>Medium spread collar, one-button mitered barrel cuffs</li><li>Applied placket with genuine mother-of-pearl buttons</li><li>;Split back yoke, rear side pleats</li><li>Made in the U.S.A. of 100% imported cotton.</li></ul>"
						+ getResources().getString(R.string.vaccination_info)
						+ "<ul><li>Trim, tailored fit for a bespoke feel</li><li>Medium spread collar, one-button mitered barrel cuffs</li><li>Applied placket with genuine mother-of-pearl buttons</li><li>;Split back yoke, rear side pleats</li><li>Made in the U.S.A. of 100% imported cotton.</li></ul></body></html>";
			} else {
				mainService = "No data available";
			}
		} else if (isVideoService) {
			/*btMore.setVisibility(View.GONE);
			video.setVisibility(View.VISIBLE);
			btMore.setVisibility(View.GONE);

			video.setMediaController(new MediaController(getActivity()));
			Uri url = Uri.parse("android.resource://"
					+ getActivity().getPackageName() + "/" + R.raw.shakira);
			video.setVideoURI(url);
			video.start();
*/
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

				} else if (isMotherService) {
					startActivity(new Intent(getActivity(),
							MotherServiceAll.class));
				} else if (isChildService) {
					startActivity(new Intent(getActivity(),
							ChildServiceAll.class));
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

	public int getDays() {
		SQLiteDatabase db = getActivity().openOrCreateDatabase("TestDB",
				getActivity().MODE_PRIVATE, null);
		Cursor cursor = db.rawQuery("select * from users", null);
		String date = null;
		if (cursor.moveToFirst()) {
			do {
				date = cursor.getString(1);
			} while (cursor.moveToNext());
		}

		String dateStart = date;
		String dateStop = new SimpleDateFormat("MM/dd/yyyy").format(Calendar
				.getInstance().getTime());

		SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
		java.util.Date d1 = null;
		java.util.Date d2 = null;

		try {
			d1 = format.parse(dateStart);
			d2 = format.parse(dateStop);

			DateTime dt1 = new DateTime(d1);
			DateTime dt2 = new DateTime(d2);
			dayDifferent = "" + Days.daysBetween(dt1, dt2).getDays();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return Integer.parseInt(dayDifferent);
	}

}
