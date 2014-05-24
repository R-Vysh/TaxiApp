package ua.ros.taxiapp.lists;

import java.util.ArrayList;
import java.util.List;

import ua.ros.taxiapp.R;
import android.app.ListFragment;
import android.location.Address;
import android.os.Bundle;

public class AddressList extends ListFragment {
	List<Address> addresses = new ArrayList<Address>();
	
//	@Override
//	public View onCreateView(LayoutInflater inflater, ViewGroup container,
//			Bundle savedInstanceState) {
//		return inflater.inflate(R.layout.fragment_address_list, null);
//	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		AddressAdapter adapter = new AddressAdapter(getActivity().getApplicationContext(), R.layout.list_view_row_item, addresses);
			        
	    this.setListAdapter(adapter);
		//	        listViewItems.setOnItemClickListener(new OnItemClickListenerListViewItem());
		//setListAdapter(adapter);
	}

	public void setAddresses(List<Address> addresses) {
		this.addresses = addresses;
	}

}
