package com.project_one.controller.api.v1.fragment.order.array_adapter;

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
import com.project_one.model.SalesOrder;
import com.project_one.service.InventoryItemService;
import com.project_one.service.InventoryItemServiceImpl;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by JenuNagil on 8/22/2015.
 */
public class OrderListArrayAdapter extends ArrayAdapter<SalesOrder> implements CustomArrayAdapter {

    public static final int ADDED_TO_POSITION_OF_LIST_ITEM_TO_BE_REMOVED = 1;
    public static final String THRESHOLD_FOR_UNIT_PRICE = "5.00";
    private List<SalesOrder> listOfOrders;
    private Context context;
    private Activity activity;

    public OrderListArrayAdapter(Context context, int textViewResourceId, List<SalesOrder> objects) {
        super(context, textViewResourceId, objects);
        this.listOfOrders = objects;
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
        view = inflater.inflate(R.layout.order_list_row, parent, false);
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


        SalesOrder order = listOfOrders.get(position);
        orderId.setText(order.getId().toString());
        usernameText.setText(order.getUser().getUsername());
        totalAmountText.setText(order.getAmount().toString());
        dateCreatedText.setText(order.getDateCreated().toString());

//
//        productName.setText(listOfItems.get(position).getProduct().getName());
//        quantityString = Integer.toString(listOfItems.get(position).getQuantity());
//        inventoryQuantity.setText(quantityString);
//        unitPriceTextView.setText(listOfItems.get(position).getProduct().getUnitPrice().toString());
        return view;
    }
}
