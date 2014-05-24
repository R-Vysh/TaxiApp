package ua.ros.taxiapp.jsonhandlers;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.json.JSONException;
import org.json.JSONObject;

import ua.ros.taxiapp.domain.User;

public class UserHandler {
	public User handle(JSONObject object)
			throws ClientProtocolException, IOException {
		User user = null;
		try {
			user = new User();
			user.setMobile(object.getString("mobile"));
			user.setPassword(object.getString("password"));
			user.setTaxist(Boolean.valueOf(object.getString("taxist")));
			user.setUserId(object.getInt("userId"));
			user.setUsername(object.getString("username"));
			//TODO createdTime
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return user;
	}
}
