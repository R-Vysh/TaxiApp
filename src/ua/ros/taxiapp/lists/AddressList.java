package ua.ros.taxiapp.lists;

import java.util.ArrayList;
import java.util.List;

import ua.ros.taxiapp.R;
import ua.ros.taxiapp.activities.MakeOrderSecondActivity;
import ua.ros.taxiapp.activities.RegisterCustomerActivity;
import android.app.ListFragment;
import android.content.Intent;
import android.location.Address;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class AddressList extends ListFragment implements OnClickListener {
	
	public AddressList() {
		super();
	}

	List<Address> addresses = new ArrayList<Address>();
	AddressAdapter adapter;
	ListView lv;
	Button confirmButton;;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_address_list, null);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		adapter = new AddressAdapter(getActivity().getApplicationContext(), addresses);
		lv = this.getListView();
		lv.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
	    this.setListAdapter(adapter);
	    confirmButton = (Button) getView().findViewById(R.id.selectButtonAddressFragment);
	    confirmButton.setOnClickListener(this);
	}

	public void setAddresses(List<Address> addresses) {
		this.addresses = addresses;
	}

	public AddressAdapter getAdapter() {
		return adapter;
	}

	@Override
	public void onClick(View v) {
		if(v.equals(confirmButton)) {
			int index = lv.getCheckedItemPosition();
			if(index == -1) {
				Toast toast = Toast.makeText(getActivity().getApplicationContext(),
						"You haven't chosen any address", Toast.LENGTH_SHORT);
				toast.show();
			} else {
				Address address = addresses.get(index);
				Intent intent = new Intent(getActivity().getApplicationContext(), MakeOrderSecondActivity.class);
				intent.putExtra("fromPlace", address);
				startActivity(intent);
			}
		}
		
	}
	
}
