package ua.ros.taxiapp.lists;

import java.util.ArrayList;
import java.util.List;

import ua.ros.taxiapp.R;
import android.app.ListFragment;
import android.content.Context;
import android.location.Address;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class AddressList extends ListFragment {
	
	public AddressList() {
		super();
	}

	List<Address> addresses = new ArrayList<Address>();
	AddressAdapter adapt;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_address_list, null);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		adapt = new AddressAdapter(getActivity().getApplicationContext(), addresses);
			        
	    this.setListAdapter(adapt);
//	        listViewItems.setOnItemClickListener(new OnItemClickListenerListViewItem());
//		//setListAdapter(adapter);
	}

	public void setAddresses(List<Address> addresses) {
		this.addresses = addresses;
	}

	public AddressAdapter getAdapter() {
		return adapt;
	}
}
