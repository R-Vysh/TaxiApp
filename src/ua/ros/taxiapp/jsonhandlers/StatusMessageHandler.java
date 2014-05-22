package ua.ros.taxiapp.jsonhandlers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.impl.client.BasicResponseHandler;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import ua.ros.taxiapp.StatusMessage;

public class StatusMessageHandler {

	public StatusMessage handle(JSONObject object)
			throws ClientProtocolException, IOException {
		StatusMessage result = new StatusMessage();
		try {
			result.setMessage(object.getString("message"));
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return result;
	}
}
