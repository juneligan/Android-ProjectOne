package com.project_one.controller.api.v1.fragment.product;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.Toast;

import com.R;
import com.project_one.controller.api.v1.fragment.product.array_adapter.InventoryItemArrayAdapter;
import com.project_one.controller.api.v1.fragment.product.array_adapter.InventoryItemUnitPriceArrayAdapter;
import com.project_one.model.InventoryItem;
import com.project_one.service.InventoryItemService;
import com.project_one.service.InventoryItemServiceImpl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by JenuNagil on 8/22/2015.
 */
public class ManageProductUnitPriceFragment extends ListFragment {

    public ManageProductUnitPriceFragment() {
    }

    public static ManageProductUnitPriceFragment newInstance(int position, String viewTitle) {
        ManageProductUnitPriceFragment manageProductUnitPriceFragment = new ManageProductUnitPriceFragment();

        Bundle bundle = new Bundle();
        bundle.putInt("viewPosition", position);
        bundle.putString("viewTitle", viewTitle);
        manageProductUnitPriceFragment.setArguments(bundle);

        return manageProductUnitPriceFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_manage_product_quantity, container, false);

        int position = getArguments().getInt("viewPosition");
        String title = getArguments().getString("viewTitle");

        Toast.makeText(getActivity(), "title: " + title, Toast.LENGTH_SHORT).show();
        displayProducts();
        return rootView;
    }

    private void displayProducts() {
        InventoryItemService inventoryItemServiceImpl;
        List<String> productNames = new ArrayList<String>();
        try {
            inventoryItemServiceImpl = new InventoryItemServiceImpl(getActivity());

            for(InventoryItem inventoryItem : inventoryItemServiceImpl.fetchAllItems()) {
                Log.d("Controller", inventoryItem.getProduct().getName());
                productNames.add(inventoryItem.getProduct().getName());
            }

            InventoryItemArrayAdapter inventoryItemArrayAdapter = new InventoryItemUnitPriceArrayAdapter(getActivity(), R.layout.inventory_items_row_for_manage_product_inventory_quantity, inventoryItemServiceImpl.fetchAllItems());
            inventoryItemArrayAdapter.setActivity(getActivity());
            setListAdapter((ListAdapter) inventoryItemArrayAdapter);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
