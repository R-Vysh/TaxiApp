package ua.ros.taxiapp.tasks;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import ua.ros.taxiapp.activities.RegisterTaxistActivity;
import ua.ros.taxiapp.domain.StatusMessage;
import ua.ros.taxiapp.jsonhandlers.JSONParser;
import ua.ros.taxiapp.jsonhandlers.StatusMessageHandler;
import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

public class RegisterTaxistTask extends AsyncTask<String, JSONObject, JSONObject> {

	Context context;
	Activity activity;
	public static final String ADDRESS = "http://mrjumpy.no-ip.biz/rest/taxists/register";

	public RegisterTaxistTask(Context context) {
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
		List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(3);
		nameValuePairs.add(new BasicNameValuePair("mobile", params[0]));
		nameValuePairs.add(new BasicNameValuePair("password", params[1]));
		nameValuePairs.add(new BasicNameValuePair("username", params[2]));
		nameValuePairs.add(new BasicNameValuePair("brand", params[3]));
		nameValuePairs.add(new BasicNameValuePair("model", params[4]));
		nameValuePairs.add(new BasicNameValuePair("regNumber", params[5]));
		nameValuePairs.add(new BasicNameValuePair("pricePerKm", params[6]));
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
			super.onPostExecute(jsonData);
			try {
				StatusMessageHandler handler = new StatusMessageHandler();
				StatusMessage msg = handler.handle(jsonData);
				if (msg.getMessage().equals(StatusMessage.OK)) {
					// Successful. Go to start screen
					Toast toast = Toast.makeText(context,
							"Successfully registered", Toast.LENGTH_SHORT);
					toast.show();
					activity.finish();
				} else {
					Toast toast = Toast.makeText(context,
							"An error occured while registering",
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

	public void setActivity(RegisterTaxistActivity act) {
		this.activity = act;
	}
}
