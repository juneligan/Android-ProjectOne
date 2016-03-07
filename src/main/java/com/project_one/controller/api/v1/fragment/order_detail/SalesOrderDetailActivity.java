package com.project_one.controller.api.v1.fragment.order_detail;

import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.R;
import com.project_one.controller.api.v1.fragment.order.OrderViewPagerAdapter;

public class SalesOrderDetailActivity extends ActionBarActivity {

    ViewPager pager;
    SalesOrderDetailViewPagerAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sales_order_detail_view_pager);

        pager = (ViewPager) findViewById(R.id.pager);
        adapter = new SalesOrderDetailViewPagerAdapter(getSupportFragmentManager());
        pager.setAdapter(adapter);
    }
}
