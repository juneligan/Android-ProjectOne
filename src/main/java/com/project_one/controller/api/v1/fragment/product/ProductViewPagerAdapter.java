package com.project_one.controller.api.v1.fragment.product;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by JenuNagil on 8/20/2015.
 */
public class ProductViewPagerAdapter extends FragmentStatePagerAdapter {

    private final static int PRODUCT_FRAGMENTS_COUNT = 3;
    private final static String MANAGE_QUANTITY_VIEW = "Quantity";
    private final static String MANAGE_PRICE_VIEW = "Price";
    private final static String MANAGE_PRODUCT_VIEW = "Product";

    public ProductViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch(position) {
            case 0: return ManageProductFragment.newInstance(position, MANAGE_PRODUCT_VIEW);
            case 1: return ManageProductQuantityFragment.newInstance(position, MANAGE_QUANTITY_VIEW);
            default: return ManageProductUnitPriceFragment.newInstance(position, MANAGE_PRICE_VIEW);
        }
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }

    @Override
    public int getCount() {
        return PRODUCT_FRAGMENTS_COUNT;
    }
}
