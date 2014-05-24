package ua.ros.taxiapp.lists;

import java.util.List;

import ua.ros.taxiapp.R;
import android.app.Activity;
import android.content.Context;
import android.location.Address;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class AddressAdapter extends BaseAdapter {

    private Context fContext;
    protected LayoutInflater fInflater;
    protected List<Address> addresses;

    public AddressAdapter(Context aContext, List<Address> addresses) {
        fContext = aContext;
        this.addresses = addresses;
        fInflater = (LayoutInflater) fContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

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
        View lView = convertView;
        if (lView == null) {
            lView = fInflater.inflate(R.layout.list_view_row_item, parent, false);
        }

        Address lRow = addresses.get(position);
        TextView lName = (TextView) lView.findViewById(R.id.textViewItem);
        
        lName.setText(lRow.getCountryCode());
        //lCompany.setText(this.getRowValueAsString(lRow, "ContactAddress", ""));

        return lView;
    }

    // implementation of TableChangedListener
    public void update() {
        this.notifyDataSetChanged();
    }
    
    public void setAddresses(List<Address> addresses) {
    	this.addresses = addresses;
    }

    
}