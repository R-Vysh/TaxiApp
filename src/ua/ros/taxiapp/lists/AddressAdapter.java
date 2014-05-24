package ua.ros.taxiapp.lists;

import java.util.List;

import ua.ros.taxiapp.R;
import android.content.Context;
import android.location.Address;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class AddressAdapter extends BaseAdapter {

	private Context context;
	protected LayoutInflater inflater;
	protected List<Address> addresses;

	public AddressAdapter(Context aContext, List<Address> addresses) {
		context = aContext;
		this.addresses = addresses;
		inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

	}

	public int getCount() {
		return addresses.size();
	}

	public Object getItem(int i) {
		return addresses.get(i);
	}

	public long getItemId(int i) {
		return (long) i;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		View view = convertView;
		if (view == null) {
			view = inflater.inflate(R.layout.list_view_row_item, parent, false);
		}

		Address address = addresses.get(position);
		TextView name = (TextView) view.findViewById(R.id.textViewItem);

		int numOfLines = address.getMaxAddressLineIndex();
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < numOfLines; i++) {
			sb.append(address.getAddressLine(i) + " ");
		}
		name.setText(sb.toString());
		sb.setLength(0);
		return view;
	}

	public void setAddresses(List<Address> addresses) {
		this.addresses = addresses;
	}
}