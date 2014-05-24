package ua.ros.taxiapp.jsonhandlers;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.json.JSONException;
import org.json.JSONObject;

import ua.ros.taxiapp.domain.StatusMessage;

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
