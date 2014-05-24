package ua.ros.taxiapp.activities;

import ua.ros.taxiapp.R;
import ua.ros.taxiapp.lists.AddressList;
import ua.ros.taxiapp.lists.AddressListTo;
import ua.ros.taxiapp.tasks.FindPlaceTask;
import android.location.Address;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class MakeOrderSecondActivity extends MakeOrderFirstActivity implements OnClickListener {
	AddressListTo addressList;
	Address fromAddress;
	Button searchButton;
	EditText addressEdit;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_make_order_second);
		searchButton = (Button) findViewById(R.id.searchButtonMakeOrderSecondActivity);
		searchButton.setOnClickListener(this);
		addressEdit = (EditText) findViewById(R.id.fromEditMakeOrderSecondActivity);
		addressList = (AddressListTo) getFragmentManager().findFragmentById(R.id.searchResultFragmentMakeOrderSecondActivity);
		fromAddress = (Address) getIntent().getExtras().get("fromPlace");
		addressList.setFromAddress(fromAddress);
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
