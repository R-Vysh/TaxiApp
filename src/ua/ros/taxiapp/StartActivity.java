package ua.ros.taxiapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class StartActivity extends Activity implements OnClickListener {
	Button signInButton, registerTaxiButton, registerCustomerButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_start);
		signInButton = (Button) findViewById(R.id.signInButton);
		signInButton.setOnClickListener(this);
		registerTaxiButton = (Button) findViewById(R.id.goToRegisterTaxiButton);
		registerTaxiButton.setOnClickListener(this);
		registerCustomerButton = (Button) findViewById(R.id.goToRegisterCustomerButton);
		registerCustomerButton.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		if (v.equals(signInButton)) {
			Intent mainIntent = new Intent(getApplicationContext(), MainCustomerActivity.class);
			startActivity(mainIntent);
		}
		if (v.equals(registerTaxiButton)) {
			Intent regIntent = new Intent(getApplicationContext(), RegisterTaxiActivity.class);
			startActivity(regIntent);
		}
		if (v.equals(registerCustomerButton)) {
			Intent regIntent = new Intent(getApplicationContext(), RegisterCustomerActivity.class);
			startActivity(regIntent);
		}
	}

}
