package com.project_one.controller;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.R;
import com.project_one.model.InventoryItem;
import com.project_one.model.Product;
import com.project_one.service.InventoryItemService;
import com.project_one.service.InventoryItemServiceImpl;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by JenuNagil on 8/11/2015.
 */
public class CustomAdapterHolder {

    private CustomInventoryArrayAdapter customInventoryArrayAdapter;
    private Activity activity;

    public CustomAdapterHolder(Context context, int textViewResourceId, List<InventoryItem> objects) {
        customInventoryArrayAdapter = new CustomInventoryArrayAdapter(context, textViewResourceId, objects);
    }
    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    public CustomInventoryArrayAdapter getCustomInventoryArrayAdapter() {
        return customInventoryArrayAdapter;
    }

    public class CustomInventoryArrayAdapter extends ArrayAdapter<InventoryItem> {

        private List<InventoryItem> listOfItems;
        private TextView text;
        private Context context;

        public CustomInventoryArrayAdapter(Context context, int textViewResourceId, List<InventoryItem> objects) {
            super(context, textViewResourceId, objects);
            this.listOfItems = objects;
            this.context = context;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {

            View view = null;
            LayoutInflater inflater = activity.getLayoutInflater();
            view = inflater.inflate(R.layout.list_row, parent, false);
            TextView productName = (TextView) view.findViewById(R.id.product_name);
            final TextView inventoryQuantity = (TextView) view.findViewById(R.id.product_quantity);
            final EditText newQuantity = (EditText) view.findViewById(R.id.new_quantity);
            Button updateButton = (Button) view.findViewById(R.id.btn_update);
//            Button delButton = (Button) view.findViewById(R.id.btn_del);

            updateButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        InventoryItemService inventoryItemServiceImpl = new InventoryItemServiceImpl(context);
                        Product product = listOfItems.get(position).getProduct();
                        InventoryItem inventoryItem = inventoryItemServiceImpl.addQuantity(product, Integer.parseInt(newQuantity.getText().toString()));
                        inventoryQuantity.setText(inventoryItem.getQuantity());
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
//                    listOfItems.remove(position);
                    customInventoryArrayAdapter.notifyDataSetChanged();
                    //Toast.makeText(activity, "Item deleted", Toast.LENGTH_SHORT).show();
                }
            });

            productName.setText(listOfItems.get(position).getProduct().getName());
            String quantityString = listOfItems.get(position).getQuantity() + "";
            inventoryQuantity.setText(quantityString);
            return view;
        }
    }

}
