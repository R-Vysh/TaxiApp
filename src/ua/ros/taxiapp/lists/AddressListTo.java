package ua.ros.taxiapp.lists;

import ua.ros.taxiapp.activities.MakeOrderSecondActivity;
import ua.ros.taxiapp.activities.ShowOrderOnMapActivity;
import android.content.Intent;
import android.location.Address;
import android.view.View;
import android.widget.Toast;

public class AddressListTo extends AddressList {
		Address fromAddress;
		
		public AddressListTo() {
			super();
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
					Intent intent = new Intent(getActivity().getApplicationContext(), ShowOrderOnMapActivity.class);
					intent.putExtra("toPlace", address);
					intent.putExtra("fromPlace", fromAddress);
					startActivity(intent);
				}
			}
			
		}
		
		public void setFromAddress(Address fromAddress) {
			this.fromAddress = fromAddress;
		}
		
}
