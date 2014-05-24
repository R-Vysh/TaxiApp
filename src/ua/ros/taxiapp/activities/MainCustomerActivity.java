package ua.ros.taxiapp.activities;

import ua.ros.taxiapp.R;
import ua.ros.taxiapp.domain.User;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MainCustomerActivity extends Activity implements OnClickListener {
	User user;
	TextView usernameLabel;
	Button makeOrderButton;
	Button viewHistoryButton;
	Button viewMapButton;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_customer);
		Intent intent = getIntent();
	    user = (User)intent.getExtras().get("user");
	    usernameLabel = (TextView) findViewById(R.id.usernameLabelMainCustomerActivity);
	    usernameLabel.setText("Signed in as " + user.getUsername());
	    makeOrderButton = (Button) findViewById(R.id.makeOrderButtonMainCustomerActivity);
	    viewHistoryButton = (Button) findViewById(R.id.viewHistoryButtonMainCustomerActivity);
	    viewMapButton = (Button) findViewById(R.id.viewMapButtonMainCustomerActivity);
	    makeOrderButton.setOnClickListener(this);
	    viewMapButton.setOnClickListener(this);
	    viewHistoryButton.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		if(v.equals(viewMapButton)) {
			Intent mapIntent = new Intent(getApplicationContext(), ViewMapActivity.class);
			startActivity(mapIntent);
		}
		if(v.equals(viewHistoryButton)) {
			Intent mapIntent = new Intent(getApplicationContext(), ViewHistoryActivity.class);
			startActivity(mapIntent);
		}
		if(v.equals(makeOrderButton)) {
			Intent mapIntent = new Intent(getApplicationContext(), MakeOrderFirstActivity.class);
			startActivity(mapIntent);
		}
		
	}

}
