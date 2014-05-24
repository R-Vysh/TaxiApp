package ua.ros.taxiapp.activities;

import ua.ros.taxiapp.R;
import ua.ros.taxiapp.tasks.RegisterCustomerTask;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterCustomerActivity extends Activity implements
		OnClickListener {
	EditText mobileInput;
	EditText passwordInput;
	EditText confirmInput;
	EditText usernameInput;
	Button registerButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register_customer);

		usernameInput = (EditText) findViewById(R.id.usernameInputRegisterCustomerActivity);
		mobileInput = (EditText) findViewById(R.id.mobileInputRegisterCustomerActivity);
		passwordInput = (EditText) findViewById(R.id.passwordInputRegisterCustomerActivity);
		confirmInput = (EditText) findViewById(R.id.confirmInputRegisterCustomerActivity);
		try {
			TelephonyManager tMgr = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
			String mPhoneNumber = tMgr.getLine1Number();
			mobileInput.setText(mPhoneNumber);
		} catch (NullPointerException ex) {
		}
		registerButton = (Button) findViewById(R.id.registerCustomerButtonRegisterCustomerActivity);
		registerButton.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		if (v.equals(registerButton)) {
			String mobile = mobileInput.getText().toString();
			String password = passwordInput.getText().toString();
			String username = usernameInput.getText().toString();
			String confirm = confirmInput.getText().toString();
			boolean isOk = true;

			if (!mobile.matches("0[0-9]{9}")) {
				Toast toast = Toast.makeText(getApplicationContext(),
						"Mobile invalid", Toast.LENGTH_SHORT);
				toast.show();
				isOk = false;
			}
			if (!(confirm.equals(password))) {
				Toast toast = Toast.makeText(getApplicationContext(),
						"Passwords don't match", Toast.LENGTH_SHORT);
				toast.show();
				isOk = false;
			}
			if (username.equals("")) {
				Toast toast = Toast.makeText(getApplicationContext(),
						"Username empty", Toast.LENGTH_SHORT);
				toast.show();
				isOk = false;
			}
			if (password.length() < 3) {
				Toast toast = Toast.makeText(getApplicationContext(),
						"Password must be at least 3 characters long", Toast.LENGTH_SHORT);
				toast.show();
				isOk = false;
			}
			if (isOk) {
				RegisterCustomerTask task = new RegisterCustomerTask(
						RegisterCustomerActivity.this);
				task.setActivity(this);
				task.execute(mobileInput.getText().toString(), passwordInput
						.getText().toString(), usernameInput.getText()
						.toString());
			}
		}
	}
}
