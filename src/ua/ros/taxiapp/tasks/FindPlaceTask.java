package ua.ros.taxiapp.tasks;

import java.io.IOException;
import java.util.List;

import ua.ros.taxiapp.activities.MakeOrderFirstActivity;
import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

public class FindPlaceTask extends AsyncTask<String, List<Address>, List<Address>> {
	
	Context context;
	MakeOrderFirstActivity activity;

	public FindPlaceTask(MakeOrderFirstActivity makeOrderFirstActivity, Context context) {
		super();
		this.context = context;
		this.activity = makeOrderFirstActivity;
	}

	@Override
	protected void onPreExecute() {
		super.onPreExecute();
	}

	@Override
	protected List<Address> doInBackground(String... address) {
		Geocoder geocoder = new Geocoder(context);
		List<Address> addresses = null;
		try {
			addresses = geocoder.getFromLocationName(address[0], 100);
			return addresses;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return addresses;
	}

	@Override
	protected void onPostExecute(List<Address> addresses) {
		super.onPostExecute(addresses);
		/*
		 * check if result is not null. If it is null, that means fail
		 */
		if (addresses != null) {
			activity.getAddressList().setAddresses(addresses);
			activity.getAddressList().getAdapter().setAddresses(addresses);
			activity.getAddressList().getAdapter().notifyDataSetChanged();
			for(Address ad : addresses) {
			 Log.d("msg", ad.toString());
			}
		} else {
			Toast toast = Toast.makeText(context,
					"No addresses were found", Toast.LENGTH_SHORT);
			toast.show();
		}
		return;
	}
}
