package ua.ros.taxiapp.tasks;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import ua.ros.taxiapp.activities.MainCustomerActivity;
import ua.ros.taxiapp.activities.MainTaxistActivity;
import ua.ros.taxiapp.activities.StartActivity;
import ua.ros.taxiapp.domain.User;
import ua.ros.taxiapp.jsonhandlers.JSONParser;
import ua.ros.taxiapp.jsonhandlers.UserHandler;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;

public class SignInTask extends AsyncTask<String, JSONObject, JSONObject> {
	public static final String ADDRESS = "http://mrjumpy.no-ip.biz/rest/users/signIn";

	Context context;
	StartActivity activity;

	public SignInTask(StartActivity activity, Context context) {
		super();
		this.context = context;
		this.activity = activity;
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
		List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
		nameValuePairs.add(new BasicNameValuePair("username", params[0]));
		nameValuePairs.add(new BasicNameValuePair("password", params[1]));
		// send GET request
		JSONObject json = jParser.makeHttpRequest(ADDRESS, "GET",
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
				UserHandler handler = new UserHandler();
				User user = handler.handle(jsonData);
				if (user != null) {
					if (user.getTaxist() != true) {
						Intent mainIntent = new Intent(
								activity.getApplicationContext(),
								MainCustomerActivity.class);
						mainIntent.putExtra("user", user);
						activity.startActivity(mainIntent);
						activity.finish();
					} else {
						Intent mainIntent = new Intent(
								activity.getApplicationContext(),
								MainTaxistActivity.class);
						mainIntent.putExtra("user", user);
						activity.startActivity(mainIntent);
						activity.finish();
					}
				} else {
					Toast toast = Toast.makeText(context,
							"An error occured while signing in",
							Toast.LENGTH_SHORT);
					toast.show();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			Toast toast = Toast.makeText(context,
					"An error occured while signing in", Toast.LENGTH_SHORT);
			toast.show();
		}
		return;
	}
}
