package ua.ros.taxiapp;

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

public class StatusMessageHandler {
	
	public StatusMessage handle(JSONObject object)
			throws ClientProtocolException, IOException {
		StatusMessage result = new StatusMessage();
		try {
//			JSONObject object = (JSONObject) new JSONTokener(JSONResponse)
//					.nextValue();
			result.setMessage(object.getString("message"));
//			JSONArray earthquakes = object.getJSONArray("earthquakes");
//			
//			for (int i = 0; i < earthquakes.length(); i++) {
//				JSONObject tmp = (JSONObject) earthquakes.get(i);
//				result.add(new EarthQuakeRec(
//						tmp.getDouble("lat"),
//						tmp.getDouble("lng"),
//						tmp.getDouble("magnitude")));
//			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return result;
	}

}
