package ua.ros.taxiapp.activities;

import android.app.Activity;
import android.location.Address;
import android.os.Bundle;
import android.util.Log;

public class ShowOrderOnMapActivity extends Activity {
	Address fromAddress;
	Address toAddress;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		fromAddress = (Address) getIntent().getExtras().get("fromPlace");
		toAddress = (Address) getIntent().getExtras().get("toPlace");
		
		Log.d("fromPlace", fromAddress.toString());
		Log.d("toPlace", toAddress.toString());
	}
}
