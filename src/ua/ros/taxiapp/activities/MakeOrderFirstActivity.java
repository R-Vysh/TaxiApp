package ua.ros.taxiapp.activities;

import ua.ros.taxiapp.R;
import ua.ros.taxiapp.lists.AddressList;
import ua.ros.taxiapp.tasks.FindPlaceTask;
import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class MakeOrderFirstActivity extends Activity implements OnClickListener {
	Button searchButton;
	EditText addressEdit;
	AddressList addressList;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_make_order_first);
		searchButton = (Button) findViewById(R.id.searchButtonMakeOrderFirstActivity);
		searchButton.setOnClickListener(this);
		addressEdit = (EditText) findViewById(R.id.fromEditMakeOrderFirstActivity);
		addressList = (AddressList) getFragmentManager().findFragmentById(R.id.searchResultFragmentMakeOrderFirstActivity);
	}
	
	@Override
	public void onClick(View v) {
		if(v.equals(searchButton)) {
			String address = addressEdit.getText().toString();
			FindPlaceTask task = new FindPlaceTask(this, getApplicationContext());
			task.execute(address);
		}	
	}
	
	public AddressList getAddressList() {
		return addressList;
	}

}
