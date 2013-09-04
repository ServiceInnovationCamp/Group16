package com.mao.shishu;

import android.app.Activity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class HospitalMapActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.hospital_map);

		android.app.FragmentManager fragmentManager = getFragmentManager();
		MapFragment mapFragment = (MapFragment) fragmentManager
				.findFragmentById(R.id.hospital_map);
		GoogleMap googleMap = mapFragment.getMap();

		LatLng sfLatLng = new LatLng(37.7750, -122.4183);
		googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
		googleMap.moveCamera(CameraUpdateFactory.newLatLng(sfLatLng));
		googleMap.addMarker(new MarkerOptions()
				.position(sfLatLng)
				.title("San Francisco")
				.snippet("Population: 776733")
				.icon(BitmapDescriptorFactory
						.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));
	}

}
