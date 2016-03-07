package com.project_one.controller.api.v1.fragment.order_detail;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by JenuNagil on 8/20/2015.
 */
public class SalesOrderDetailViewPagerAdapter extends FragmentStatePagerAdapter {

    private final static int SALES_ORDER_DETAILS_FRAGMENTS_COUNT = 4;
    private final static String TRANSACTION_OF_SALES_ORDER_DETAIL = "Transaction of sales order detail";
    private final static String DETAILS_OF_SALES_ORDER= "Details of sales order";

    public SalesOrderDetailViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch(position) {
            case 0: return SalesOrderDetailFragment.newInstance(position, TRANSACTION_OF_SALES_ORDER_DETAIL);
            default: return SalesOrderDetailFragment.newInstance(position, TRANSACTION_OF_SALES_ORDER_DETAIL);
        }
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }

    @Override
    public int getCount() {
        return SALES_ORDER_DETAILS_FRAGMENTS_COUNT;
    }
}
