package com.project_one.controller.api.v1.fragment.order;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.Toast;

import com.R;
import com.project_one.controller.api.v1.fragment.CustomArrayAdapter;
import com.project_one.controller.api.v1.fragment.order.array_adapter.OrderListArrayAdapter;
import com.project_one.service.SalesOrderService;
import com.project_one.service.SalesOrderServiceImpl;

import java.util.ArrayList;
import java.util.List;

/**
 * A placeholder fragment containing a simple view.
 */
public class OrderListViewPagerFragment extends ListFragment {

    public OrderListViewPagerFragment() {
    }

    public static OrderListViewPagerFragment newInstance(int position, String viewTitle) {
        OrderListViewPagerFragment orderListFragment = new OrderListViewPagerFragment();

        Bundle bundle = new Bundle();
        bundle.putInt("viewPosition", position);
        bundle.putString("viewTitle", viewTitle);
        orderListFragment.setArguments(bundle);

        return orderListFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_order_list, container, false);

        int position = getArguments().getInt("viewPosition");
        String title = getArguments().getString("viewTitle");

        Toast.makeText(getActivity(), "title: " + title, Toast.LENGTH_SHORT).show();
//        displayCategories(rootView);
        displayOrders();
        return rootView;
    }

    private void displayOrders() {
        SalesOrderService salesOrderServiceImpl = new SalesOrderServiceImpl(getActivity());

        CustomArrayAdapter customArrayAdapter = new OrderListArrayAdapter(getActivity(), R.layout.inventory_items_row_for_manage_product_inventory_quantity, salesOrderServiceImpl.fetchAllOrders());
        customArrayAdapter.setActivity(getActivity());
        setListAdapter((ListAdapter) customArrayAdapter);
    }
}
