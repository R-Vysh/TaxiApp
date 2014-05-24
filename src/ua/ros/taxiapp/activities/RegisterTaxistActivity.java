package ua.ros.taxiapp.activities;

import ua.ros.taxiapp.R;
import ua.ros.taxiapp.tasks.RegisterTaxistTask;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterTaxistActivity extends Activity implements OnClickListener {

	EditText mobileInput;
	EditText passwordInput;
	EditText confirmInput;
	EditText usernameInput;
	EditText brandInput;
	EditText modelInput;
	EditText regNumberInput;
	EditText pricePerKmInput;
	Button registerButton;
	Button backButton;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register_taxist);

		usernameInput = (EditText) findViewById(R.id.usernameInputRegisterTaxistActivity);
		mobileInput = (EditText) findViewById(R.id.mobileInputRegisterTaxistActivity);
		passwordInput = (EditText) findViewById(R.id.passwordInputRegisterTaxistActivity);
		confirmInput = (EditText) findViewById(R.id.confirmInputRegisterTaxistActivity);
		brandInput = (EditText) findViewById(R.id.brandInputRegisterTaxistActivity);
		modelInput = (EditText) findViewById(R.id.modelInputRegisterTaxistActivity);
		regNumberInput = (EditText) findViewById(R.id.regNumberInputRegisterTaxistActivity);
		pricePerKmInput = (EditText) findViewById(R.id.pricePerKmInputRegisterTaxistActivity);
		registerButton = (Button) findViewById(R.id.registerTaxistButtonRegisterTaxistActivity);
		registerButton.setOnClickListener(this);
		
		try {
			TelephonyManager tMgr = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
			String mPhoneNumber = tMgr.getLine1Number();
			mobileInput.setText(mPhoneNumber);
		} catch (NullPointerException ex) {
		}
		
	}

	@Override
	public void onClick(View v) {
		if (v.equals(registerButton)) {
			String mobile = mobileInput.getText().toString();
			String password = passwordInput.getText().toString();
			String username = usernameInput.getText().toString();
			String confirm = confirmInput.getText().toString();
			String brand = brandInput.getText().toString();
			String model = modelInput.getText().toString();
			String regNumber = regNumberInput.getText().toString();
			String pricePerKm = pricePerKmInput.getText().toString();
			regNumber.replaceAll("\\s+","");
			
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
			if(!regNumber.matches("[a-zA-Z]{2}[0-9]{4}[a-zA-Z]{2}") && !regNumber.matches("[0-9]{3}-?[0-9]{2}[a-zA-Z]{2}")) {
				Toast toast = Toast.makeText(getApplicationContext(),
						"Registrational number invalid", Toast.LENGTH_SHORT);
				toast.show();
				isOk = false;
			}
			if(!pricePerKm.matches("[0-9]*[,|.]?[0-9]{0,1,2}")) {
				Toast toast = Toast.makeText(getApplicationContext(),
						"Price per kilometer is invalid", Toast.LENGTH_SHORT);
				toast.show();
				isOk = false;
			}
			if (isOk) {
				RegisterTaxistTask task = new RegisterTaxistTask(RegisterTaxistActivity.this);
				task.setActivity(this);
				task.execute(mobile, password, username, brand, model, regNumber, pricePerKm);
			}
		}
	}

}
