package ua.ros.taxiapp;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.content.Intent;
import android.net.http.AndroidHttpClient;
import android.os.AsyncTask;
import android.util.Log;

public class RegisterTask extends AsyncTask<String, JSONObject, JSONObject> {
	Context context;
	RegisterCustomerActivity activity;

	public RegisterTask(Context context) {
		super();
		this.context = context;
	}

	@Override
	protected void onPreExecute() {
		super.onPreExecute();
	}

	AndroidHttpClient mClient = AndroidHttpClient.newInstance("");

	// @Override
	// protected Void doInBackground(String... params) {
	//
	// HttpClient httpclient = new DefaultHttpClient();
	// HttpPost httppost = new
	// HttpPost("http://mrjumpy.no-ip.biz/rest/customers/register");
	//
	// try {
	// // Add your data
	// List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(4);
	// nameValuePairs.add(new BasicNameValuePair("mobile", params[1]));
	// nameValuePairs.add(new BasicNameValuePair("username", params[3]));
	// nameValuePairs.add(new BasicNameValuePair("password", params[2]));
	// httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
	//
	// // Execute HTTP Post Request
	// httpclient.execute(httppost);
	//
	// } catch (ClientProtocolException e) {
	// Log.d("MESSAG", e.getMessage());
	// Log.d("MESSAG", e.getStackTrace().toString());
	//
	// } catch (IOException e) {
	// Log.d("MESSAG", e.getMessage());
	// Log.d("MESSAG", e.getStackTrace().toString());
	// }
	// Log.d("MESSAG", "successfully");
	// // try {
	// // URL url = new
	// URL("http://mrjumpy.no-ip.biz/rest/customers/3?j_username=kotik&j_password=123");
	// // HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	// // conn.setReadTimeout(10000 /* milliseconds */);
	// // conn.setConnectTimeout(15000 /* milliseconds */);
	// // conn.setRequestMethod("GET");
	// // conn.setDoInput(true);
	// // // Starts the query
	// // conn.connect();
	// // int response = conn.getResponseCode();
	// // Log.d("MSG", "The response is: " + response);
	// // Log.d("MSG", url.toString());
	// // Log.d("MSG", conn.getResponseMessage());
	// //
	// // //is = conn.getInputStream();
	// //
	// // // Convert the InputStream into a string
	// // //String contentAsString = readIt(is, len);
	// // //return contentAsString;
	// //
	// // // Makes sure that the InputStream is closed after the app is
	// // // finished using it.
	// // } catch (MalformedURLException e) {
	// // // TODO Auto-generated catch block
	// // e.printStackTrace();
	// // } catch (ProtocolException e) {
	// // // TODO Auto-generated catch block
	// // e.printStackTrace();
	// // } catch (IOException e) {
	// // // TODO Auto-generated catch block
	// // e.printStackTrace();
	// // } finally {
	// // // if (is != null) {
	// // // is.close();
	// // // }
	// // }
	// return null;
	//
	// }

	// @Override
	// protected void onPostExecute(Void arg) {
	//
	// }

	@Override
	protected JSONObject doInBackground(String... urls) {
		return loadJSON(urls);
	}

	public JSONObject loadJSON(String... params) {

		JSONParser jParser = new JSONParser();
		// здесь параметры необходимые в запрос добавляем
		List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(3);
		nameValuePairs.add(new BasicNameValuePair("mobile", params[1]));
		nameValuePairs.add(new BasicNameValuePair("username", params[3]));
		nameValuePairs.add(new BasicNameValuePair("password", params[2]));

		String address = "http://mrjumpy.no-ip.biz/rest/customers/register";
		//
		// посылаем запрос методом GET
		JSONObject json = jParser.makeHttpRequest(address, "POST",
				nameValuePairs);
		return json;
	}

	// }
	//
	@Override
	protected void onPostExecute(JSONObject jsonData) {
		super.onPostExecute(jsonData);
		activity.startActivity(new Intent(activity.getApplicationContext(),
				StartActivity.class));
		// startActivity(new Intent(activity.this, StartActivity.class));
		context.startActivity(new Intent(context, StartActivity.class));
		// если какой-то фейл, проверяем на null
		// фейл может быть по многим причинам: сервер сдох, нет сети на
		// устройстве и т.д.
		
		Log.d("lab", jsonData.toString());
		if (jsonData != null) {
			super.onPostExecute(jsonData);
			String res = "";
			try {
				// прочитать параметр, который отправил сервер;
				// здесь вместо "result" подставляйте то, что вам надо
				StatusMessageHandler handler = new StatusMessageHandler();
				StatusMessage msg = handler.handle(jsonData);
				//res = jsonData.getString("message");
				//if (res == "OK")
				//	Log.i("lab", "result ok");
				// что-то делаем, к примеру вызываем метод главного Activity на
				// обновление GUI
				// ((MainActivity) context).updateGUI(res);
				Log.d("lab", msg.toString());
			} catch (IOException e) {
				e.printStackTrace();
			} 
		} else {
			// ((MainActivity) context).updateGUI(res);
		}
	}

	public void setActivity(RegisterCustomerActivity act) {
		this.activity = act;
	}
}