package ua.ros.taxiapp.activities;

import ua.ros.taxiapp.R;
import ua.ros.taxiapp.domain.User;
import ua.ros.taxiapp.tasks.SignInTask;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

public class StartActivity extends Activity implements OnClickListener {
	Button signInButton, registerTaxiButton, registerCustomerButton;
	CheckBox rememberMeBox;
	EditText usernameInput, passwordInput;
	SharedPreferences sharedPrefs;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_start);
		sharedPrefs = this.getPreferences(Context.MODE_PRIVATE);
		signInButton = (Button) findViewById(R.id.signInButtonStartActivity);
		signInButton.setOnClickListener(this);
		registerTaxiButton = (Button) findViewById(R.id.registerTaxistButtonStartActivity);
		registerTaxiButton.setOnClickListener(this);
		registerCustomerButton = (Button) findViewById(R.id.registerCustomerButtonStartActivity);
		rememberMeBox = (CheckBox) findViewById(R.id.rememberMeCheckboxStartActivity);
		usernameInput = (EditText) findViewById(R.id.usernameInputStartActivity);
		passwordInput  = (EditText) findViewById(R.id.passwordInputStartActivity);
		registerCustomerButton.setOnClickListener(this);
		String username = sharedPrefs.getString("taxiAppUsername","");
		String password = sharedPrefs.getString("taxiAppPassword","");		
		usernameInput.setText(username);
		passwordInput.setText(password);
	}

	@Override
	public void onClick(View v) {
		if (v.equals(signInButton)) {
			String username = usernameInput.getText().toString();
			String password = passwordInput.getText().toString();
			if (rememberMeBox.isChecked()) {
				SharedPreferences.Editor editor = sharedPrefs.edit();
				editor.putString("taxiAppUsername", username);
				editor.putString("taxiAppPassword", password);
				editor.commit();
			}
			SignInTask sTask = new SignInTask(this, getApplicationContext());
			sTask.execute(username, password);
			
		}
		if (v.equals(registerTaxiButton)) {
			Intent regIntent = new Intent(getApplicationContext(), RegisterTaxistActivity.class);
			startActivity(regIntent);
		}
		if (v.equals(registerCustomerButton)) {
			Intent regIntent = new Intent(getApplicationContext(), RegisterCustomerActivity.class);
			startActivity(regIntent);
		}
	}
}
