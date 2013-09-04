package com.mao.shishu.childservice;

import com.mao.shishu.R;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;

public class ChildServiceSingleActivity extends Activity {

	WebView webview;
	private Bundle extras;
	private int position;
	private String services;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.child_service_single);
		webview = (WebView) findViewById(R.id.wv_child_service_single);
		extras = this.getIntent().getExtras();
		if (extras != null) {
			position = extras.getInt("POSITION");
		}

		services = "<html><body>"
				+ getResources().getString(R.string.food_list)
				+ "<ul><li>Trim, tailored fit for a bespoke feel</li><li>Medium spread collar, one-button mitered barrel cuffs</li><li>Applied placket with genuine mother-of-pearl buttons</li><li>;Split back yoke, rear side pleats</li><li>Made in the U.S.A. of 100% imported cotton.</li></ul>"
				+ getResources().getString(R.string.suggested_work)
				+ "<ul><li>Trim, tailored fit for a bespoke feel</li><li>Medium spread collar, one-button mitered barrel cuffs</li><li>Applied placket with genuine mother-of-pearl buttons</li><li>;Split back yoke, rear side pleats</li><li>Made in the U.S.A. of 100% imported cotton.</li></ul>"
				+ getResources().getString(R.string.avaoidable_work)
				+ "<ul><li>Trim, tailored fit for a bespoke feel</li><li>Medium spread collar, one-button mitered barrel cuffs</li><li>Applied placket with genuine mother-of-pearl buttons</li><li>;Split back yoke, rear side pleats</li><li>Made in the U.S.A. of 100% imported cotton.</li></ul>"
				+ getResources().getString(R.string.vaccination_info)
				+ "<ul><li>Trim, tailored fit for a bespoke feel</li><li>Medium spread collar, one-button mitered barrel cuffs</li><li>Applied placket with genuine mother-of-pearl buttons</li><li>;Split back yoke, rear side pleats</li><li>Made in the U.S.A. of 100% imported cotton.</li></ul></body></html>";

		webview.loadDataWithBaseURL(null, services, "text/html", "utf-8", null);

	}

}
