package com.project_one.controller.api.v1.fragment.order_detail;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;

import com.R;
import com.project_one.controller.api.v1.fragment.CustomArrayAdapter;
import com.project_one.controller.api.v1.fragment.order_detail.array_adapter.OrderListArrayAdapter;
import com.project_one.controller.api.v1.fragment.product.array_adapter.InventoryItemQuantityArrayAdapter;
import com.project_one.model.OrderDetail;
import com.project_one.service.InventoryItemService;
import com.project_one.service.InventoryItemServiceImpl;
import com.project_one.service.OrderDetailService;
import com.project_one.service.OrderDetailServiceImpl;
import com.project_one.service.SalesOrderService;
import com.project_one.service.SalesOrderServiceImpl;

import java.sql.SQLException;
import java.util.List;

/**
 * A placeholder fragment containing a simple view.
 */
public class SalesOrderDetailFragment extends ListFragment {

    public SalesOrderDetailFragment() {
    }

    public static SalesOrderDetailFragment newInstance(int position, String viewTitle) {
        SalesOrderDetailFragment salesOrderDetailFragment = new SalesOrderDetailFragment();

        Bundle bundle = new Bundle();
        bundle.putInt("viewPosition", position);
        bundle.putString("viewTitle", viewTitle);
        salesOrderDetailFragment.setArguments(bundle);

        return salesOrderDetailFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_sales_order_detail, container, false);
        displayAvailableItems();
        return rootView;
    }

    private void displayAvailableItems() {
        InventoryItemService inventoryItemServiceImpl;
        try {
            inventoryItemServiceImpl = new InventoryItemServiceImpl(getActivity());

            CustomArrayAdapter customArrayAdapter = new OrderListArrayAdapter(getActivity(), R.layout.list_for_available_items, inventoryItemServiceImpl.fetchAllItems());
            customArrayAdapter.setActivity(getActivity());
            setListAdapter((ListAdapter) customArrayAdapter);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
