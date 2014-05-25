package ua.ros.taxiapp.activities;

import ua.ros.taxiapp.R;
import ua.ros.taxiapp.tasks.MakeOrderTask;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.location.Address;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class ShowOrderOnMapActivity extends Activity implements OnClickListener {
	Address fromAddress;
	Address toAddress;
	GoogleMap map;
	Button confirmButton;
	String toAddressName;
	String fromAddressName;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		fromAddress = (Address) getIntent().getExtras().get("fromPlace");
		toAddress = (Address) getIntent().getExtras().get("toPlace");
		setContentView(R.layout.activity_show_order_on_map);	
		confirmButton = (Button) findViewById(R.id.sendOrderToServerShowOrderOnMapActivity);
		confirmButton.setOnClickListener(this);
		Log.d("fromPlace", fromAddress.toString());
		Log.d("toPlace", toAddress.toString());	
		map = ((MapFragment) getFragmentManager().findFragmentById(ua.ros.taxiapp.R.id.map)).getMap();
		if (null != map) {
			// Add a marker for every earthquake
							// Add a new marker for this earthquake
				StringBuilder sb = new StringBuilder();
				for (int i = 0; i < fromAddress.getMaxAddressLineIndex(); i++) {
					sb.append(fromAddress.getAddressLine(i) + " ");
				}
				sb.setLength(70);
				fromAddressName = sb.toString();
				map.addMarker(new MarkerOptions()
						// Set the Marker's position
						.position(new LatLng(fromAddress.getLatitude(), fromAddress.getLongitude()))
						// Set the title of the Marker's information window
						.title(String.valueOf(sb.toString()))
						// Set the color for the Marker
						.icon(BitmapDescriptorFactory
								.defaultMarker(120)));
				sb.setLength(0);
				for (int i = 0; i < toAddress.getMaxAddressLineIndex(); i++) {
					sb.append(toAddress.getAddressLine(i) + " ");
				}
				sb.setLength(70);
				toAddressName = sb.toString();
				map.addMarker(new MarkerOptions()
						// Set the Marker's position
						.position(new LatLng(toAddress.getLatitude(), toAddress.getLongitude()))
						// Set the title of the Marker's information window
						.title(String.valueOf(sb.toString()))
						// Set the color for the Marker
						.icon(BitmapDescriptorFactory
								.defaultMarker(240)));
			}

			// Center the map 
			// Should compute map center from the actual data		
			map.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(
					fromAddress.getLatitude(), fromAddress.getLongitude())));

		}

	@Override
	public void onClick(View v) {
		SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
		String username = sharedPrefs.getString("taxiAppUsername", "");
		String password = sharedPrefs.getString("taxiAppPassword", "");
		String userId = sharedPrefs.getString("taxiAppUserId", "");
		if(v.equals(confirmButton)) {
		MakeOrderTask task = new MakeOrderTask(getApplicationContext());
		task.setActivity(this);
		task.execute(((Double)fromAddress.getLatitude()).toString(), ((Double)fromAddress.getLongitude()).toString(),
				((Double)toAddress.getLatitude()).toString(), ((Double)toAddress.getLongitude()).toString(), username, password, userId, fromAddressName, toAddressName);
		}
	}
}
