package com.project_one.controller.api.v1.fragment.product;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.R;
import com.beardedhen.androidbootstrap.BootstrapButton;
import com.project_one.controller.api.v1.fragment.CustomArrayAdapter;
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
    private CustomArrayAdapter customArrayAdapter;

    public CustomAdapterHolder(Context context, int textViewResourceId, List<InventoryItem> objects) {
        customInventoryArrayAdapter = new CustomInventoryArrayAdapter(context, textViewResourceId, objects);
    }

    public CustomAdapterHolder(CustomArrayAdapter customArrayAdapter) {
        this.customArrayAdapter = customArrayAdapter;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    public CustomInventoryArrayAdapter getCustomInventoryArrayAdapter() {
        return customInventoryArrayAdapter;
    }

    public CustomArrayAdapter getCustomArrayAdapter() {
        return customArrayAdapter;
    }

    public class CustomInventoryArrayAdapter extends ArrayAdapter<InventoryItem> {

        public static final int POSTION_OF_LIST_ITEM_TO_BE_ADDED = 1;
        public static final int VALID_MINIMUM_QUANTITY_VALUE = 0;
        private List<InventoryItem> listOfItems;
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
            view = inflater.inflate(R.layout.inventory_items_row_for_manage_product_inventory_quantity, parent, false);
            TextView productName = (TextView) view.findViewById(R.id.product_name);
            TextView inventoryQuantity = (TextView) view.findViewById(R.id.product_quantity);
            TextView unitPriceTextView = (TextView) view.findViewById(R.id.product_unit_price);
            final EditText newQuantity = (EditText) view.findViewById(R.id.new_quantity);
            BootstrapButton updateButton = (BootstrapButton) view.findViewById(R.id.btn_update);

            String quantityString = Integer.toString(listOfItems.get(position).quantity);
            updateButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        Integer quantityToBeAdded = convertStringQuantityToInt(newQuantity.getText().toString());
                        if(quantityToBeAdded == null) return;

                        if((listOfItems.get(position).quantity + quantityToBeAdded) < VALID_MINIMUM_QUANTITY_VALUE) {
                            Toast.makeText(context, "Cannot add the specified quantity", Toast.LENGTH_LONG).show();
                            return;
                        }

                        InventoryItemService inventoryItemServiceImpl = new InventoryItemServiceImpl(context);
                        Product product = listOfItems.get(position).product;
                        InventoryItem inventoryItem = inventoryItemServiceImpl.addQuantity(product, quantityToBeAdded);
                        listOfItems.add(position, inventoryItem);
                        listOfItems.remove(position + POSTION_OF_LIST_ITEM_TO_BE_ADDED);
                        clearQuantityTextField(newQuantity);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    customInventoryArrayAdapter.notifyDataSetChanged();
                    //Toast.makeText(activity, "Item deleted", Toast.LENGTH_SHORT).show();
                }
            });


            productName.setText(listOfItems.get(position).product.name);
            quantityString = Integer.toString(listOfItems.get(position).quantity);
            inventoryQuantity.setText(quantityString);
            unitPriceTextView.setText(listOfItems.get(position).product.unitPrice.toString());
            return view;
        }

        private void clearQuantityTextField(EditText newQuantity) {
            newQuantity.setText("");
        }

        private Integer convertStringQuantityToInt(String value) {
            try {
                return value.equals("Quantity") || value.equals("") ?  0 : Integer.parseInt(value);
            } catch (NumberFormatException e) {

                Toast.makeText(context, "Quantity is invalid", Toast.LENGTH_LONG).show();
                return null;
            }
        }
    }

}
