package com.project_one.controller.api.v1.fragment.order;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.project_one.controller.api.v1.fragment.product.ManageProductFragment;
import com.project_one.controller.api.v1.fragment.product.ManageProductQuantityFragment;
import com.project_one.controller.api.v1.fragment.product.ManageProductUnitPriceFragment;

/**
 * Created by JenuNagil on 8/20/2015.
 */
public class OrderViewPagerAdapter extends FragmentStatePagerAdapter {

    private final static int ORDER_FRAGMENTS_COUNT = 2;
    private final static String ORDER_LIST_VIEW = "Order List";
    private final static String CUSTOMER_ORDER_VIEW = "Order Customer View";
    private final static String MANAGE_ORDER_OF_CUSTOMER_VIEW = "Product";

    public OrderViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch(position) {
            case 0: return OrderListViewPagerFragment.newInstance(position, MANAGE_ORDER_OF_CUSTOMER_VIEW);
            default: return OrderListViewPagerFragment.newInstance(position, MANAGE_ORDER_OF_CUSTOMER_VIEW);
        }
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }

    @Override
    public int getCount() {
        return ORDER_FRAGMENTS_COUNT;
    }
}
