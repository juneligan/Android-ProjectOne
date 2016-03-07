package com.project_one.controller.api.v1.fragment.product.array_adapter;

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

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by JenuNagil on 8/22/2015.
 */
public class InventoryItemUnitPriceArrayAdapter extends ArrayAdapter<InventoryItem> implements CustomArrayAdapter {

    public static final int ADDED_TO_POSITION_OF_LIST_ITEM_TO_BE_REMOVED = 1;
    public static final String THRESHOLD_FOR_UNIT_PRICE = "5.00";
    private List<InventoryItem> listOfItems;
    private Context context;
    private Activity activity;

    public InventoryItemUnitPriceArrayAdapter(Context context, int textViewResourceId, List<InventoryItem> objects) {
        super(context, textViewResourceId, objects);
        this.listOfItems = objects;
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
        view = inflater.inflate(R.layout.inventory_items_row_for_manage_product_unit_price, parent, false);
        TextView productName = (TextView) view.findViewById(R.id.product_name);
        TextView inventoryQuantity = (TextView) view.findViewById(R.id.product_quantity);
        TextView unitPriceTextView = (TextView) view.findViewById(R.id.product_unit_price);
        final EditText newUnitPrice = (EditText) view.findViewById(R.id.new_unit_price);
        BootstrapButton updateButton = (BootstrapButton) view.findViewById(R.id.btn_update);

        String quantityString = Integer.toString(listOfItems.get(position).quantity);
        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    BigDecimal newUnitPriceToUpdate = convertStringPriceToBigDecimal(newUnitPrice.getText().toString());
                    if (newUnitPriceToUpdate == null) return;

                    BigDecimal unitPricePlusMaxThreshold = listOfItems.get(position).product.unitPrice.add(new BigDecimal(THRESHOLD_FOR_UNIT_PRICE));
                    BigDecimal unitPriceMinusMinThreshold = listOfItems.get(position).product.unitPrice.subtract(new BigDecimal(THRESHOLD_FOR_UNIT_PRICE));
                    if (newUnitPriceToUpdate.compareTo(unitPricePlusMaxThreshold) > 0) {
                        Toast.makeText(context, "Too High for new increased price", Toast.LENGTH_LONG).show();
                        return;
                    } else if (newUnitPriceToUpdate.compareTo(unitPriceMinusMinThreshold) < 0) {
                        Toast.makeText(context, "Too Low for new decreased price", Toast.LENGTH_LONG).show();
                        return;
                    }

                    InventoryItemService inventoryItemServiceImpl = new InventoryItemServiceImpl(context);
                    Product product = listOfItems.get(position).product;
                    InventoryItem inventoryItem = inventoryItemServiceImpl.updateUnitPrice(product,newUnitPriceToUpdate);
                    listOfItems.add(position, inventoryItem);
                    listOfItems.remove(position + ADDED_TO_POSITION_OF_LIST_ITEM_TO_BE_REMOVED);
                    clearQuantityTextField(newUnitPrice);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                notifyDataSetChanged();
            }
        });


        productName.setText(listOfItems.get(position).product.name);
        inventoryQuantity.setText(quantityString);
        unitPriceTextView.setText(listOfItems.get(position).product.unitPrice.toString());
        return view;
    }

    private void clearQuantityTextField(EditText newQuantity) {
        newQuantity.setText("");
    }


    private BigDecimal convertStringPriceToBigDecimal(String value) {
        try {
            BigDecimal defaultUnitPrice = new BigDecimal("0.00");
            return value.equals("Price") || value.equals("") ? defaultUnitPrice : (new BigDecimal(value)).add(defaultUnitPrice);
        } catch(NumberFormatException e) {
            Toast.makeText(context, "Price is invalid", Toast.LENGTH_LONG).show();
//            throw new NumberFormatException();
        }
        return null;
    }
}
