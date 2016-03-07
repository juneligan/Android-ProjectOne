package com.project_one.controller.api.v1.fragment.order_detail.array_adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.R;
import com.beardedhen.androidbootstrap.BootstrapButton;
import com.project_one.controller.api.v1.fragment.CustomArrayAdapter;
import com.project_one.model.InventoryItem;
import com.project_one.model.SalesOrder;

import java.util.List;

/**
 * Created by JenuNagil on 8/22/2015.
 */
public class OrderListArrayAdapter extends ArrayAdapter<InventoryItem> implements CustomArrayAdapter {

    public static final int ADDED_TO_POSITION_OF_LIST_ITEM_TO_BE_REMOVED = 1;
    public static final String THRESHOLD_FOR_UNIT_PRICE = "5.00";
    private List<InventoryItem> listOfAvailableItems;
    private Context context;
    private Activity activity;

    public OrderListArrayAdapter(Context context, int textViewResourceId, List<InventoryItem> objects) {
        super(context, textViewResourceId, objects);
        this.listOfAvailableItems = objects;
        this.context = context;
    }

    @Override
    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        View view = null;
        LayoutInflater inflater = activity.getLayoutInflater();
        view = inflater.inflate(R.layout.list_for_available_items, parent, false);
        //TODO
        TextView orderId = (TextView) view.findViewById(R.id.order_id);
        TextView usernameText = (TextView) view.findViewById(R.id.username);
        TextView totalAmountText = (TextView) view.findViewById(R.id.order_total_amount);
        TextView dateCreatedText = (TextView) view.findViewById(R.id.order_date_created);
        BootstrapButton updateButton = (BootstrapButton) view.findViewById(R.id.btn_view_order);

        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                notifyDataSetChanged();
            }
        });


        InventoryItem item = listOfAvailableItems.get(position);
//        productName.setText(listOfItems.get(position).getProduct().getName());
//        quantityString = Integer.toString(listOfItems.get(position).getQuantity());
//        inventoryQuantity.setText(quantityString);
//        unitPriceTextView.setText(listOfItems.get(position).getProduct().getUnitPrice().toString());

//
//        productName.setText(listOfItems.get(position).getProduct().getName());
//        quantityString = Integer.toString(listOfItems.get(position).getQuantity());
//        inventoryQuantity.setText(quantityString);
//        unitPriceTextView.setText(listOfItems.get(position).getProduct().getUnitPrice().toString());
        return view;
    }
}
