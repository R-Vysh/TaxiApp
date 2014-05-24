package ua.ros.taxiapp.lists;

import java.util.List;

import ua.ros.taxiapp.R;
import android.app.Activity;
import android.content.Context;
import android.location.Address;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class AddressAdapter extends BaseAdapter {

	int layoutResourceId;
	List<Address> addresses = null;
	private Context context;

	public AddressAdapter(Context context, int layoutResourceId,
			List<Address> addresses) {

		super(context, layoutResourceId, addresses);

		this.layoutResourceId = layoutResourceId;
		this.context = context;
		this.addresses = addresses;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		/*
		 * The convertView argument is essentially a "ScrapView" as described is
		 * Lucas post
		 * http://lucasr.org/2012/04/05/performance-tips-for-androids-listview/
		 * It will have a non-null value when ListView is asking you recycle the
		 * row layout. So, when convertView is not null, you should simply
		 * update its contents instead of inflating a new row layout.
		 */
		
		if (convertView == null) {
			// inflate the layout
			LayoutInflater inflater = ((Activity) context).getLayoutInflater();
			convertView = inflater.inflate(layoutResourceId, parent, false);
		}

		// object item based on the position
		Address objectItem = addresses.get(position);

		// get the TextView and then set the text (item name) and tag (item ID)
		// values
		TextView textViewItem = (TextView) convertView
				.findViewById(R.id.textViewItem);
		textViewItem.setText(objectItem.getAdminArea());
		textViewItem.setTag(objectItem.getCountryCode());

		return convertView;

	}

}
