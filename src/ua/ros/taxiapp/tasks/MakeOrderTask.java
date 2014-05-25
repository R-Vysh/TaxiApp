package ua.ros.taxiapp.tasks;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import ua.ros.taxiapp.activities.MainCustomerActivity;
import ua.ros.taxiapp.activities.RegisterCustomerActivity;
import ua.ros.taxiapp.activities.RegisterTaxistActivity;
import ua.ros.taxiapp.activities.ShowOrderOnMapActivity;
import ua.ros.taxiapp.domain.StatusMessage;
import ua.ros.taxiapp.jsonhandlers.JSONParser;
import ua.ros.taxiapp.jsonhandlers.StatusMessageHandler;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

public class MakeOrderTask extends AsyncTask<String, JSONObject, JSONObject> {
	Context context;
	Activity activity;
	public static final String ADDRESS = "http://mrjumpy.no-ip.biz/rest/orders/new";

	public MakeOrderTask(Context context) {
		super();
		this.context = context;
	}

	@Override
	protected void onPreExecute() {
		super.onPreExecute();
	}

	@Override
	protected JSONObject doInBackground(String... urls) {
		return loadJSON(urls);
	}

	public JSONObject loadJSON(String... params) {
		JSONParser jParser = new JSONParser();
		// add request parameters
		List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(9);
		nameValuePairs.add(new BasicNameValuePair("fromLat", params[0]));
		nameValuePairs.add(new BasicNameValuePair("fromLng", params[1]));
		nameValuePairs.add(new BasicNameValuePair("toLat", params[2]));
		nameValuePairs.add(new BasicNameValuePair("toLng", params[3]));
		nameValuePairs.add(new BasicNameValuePair("j_username", params[4]));
		nameValuePairs.add(new BasicNameValuePair("j_password", params[5]));
		nameValuePairs.add(new BasicNameValuePair("fromAddressName", params[7]));
		nameValuePairs.add(new BasicNameValuePair("toAddressName", params[8]));
		
		// send POST request
		JSONObject json = jParser.makeHttpRequest(ADDRESS, "POST",
				nameValuePairs);
		return json;
	}

	@Override
	protected void onPostExecute(JSONObject jsonData) {
		super.onPostExecute(jsonData);
		/*
		 * check if result is not null. If it is null, that means fail
		 */
		if (jsonData != null) {
			try {
				StatusMessageHandler handler = new StatusMessageHandler();
				StatusMessage msg = handler.handle(jsonData);
				if (msg.getMessage().equals(StatusMessage.OK)) {
					// Successfull. Go to start screen
					Toast toast = Toast.makeText(context,
							"Order created", Toast.LENGTH_SHORT);
					toast.show();
					Intent mainIntent = new Intent(
							activity.getApplicationContext(),
							MainCustomerActivity.class);
					mainIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
					activity.startActivity(mainIntent);
				} else {
					Toast toast = Toast.makeText(context,
							"An error occured while creating order",
							Toast.LENGTH_SHORT);
					toast.show();
				}
				Log.d("lab", msg.toString());
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			Toast toast = Toast.makeText(context,
					"An error occured while registering", Toast.LENGTH_SHORT);
			toast.show();
		}
	}

	public void setActivity(ShowOrderOnMapActivity showOrderOnMapActivity) {
		this.activity = showOrderOnMapActivity;
	}
}