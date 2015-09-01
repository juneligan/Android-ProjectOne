package com.project_one.controller.api.v1.fragment.order;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.R;
import com.project_one.controller.api.v1.fragment.product.ProductViewPagerAdapter;
import com.project_one.model.Category;
import com.project_one.model.SalesOrder;

import java.util.ArrayList;
import java.util.List;

public class OrderViewPagerActivity extends ActionBarActivity {

    ViewPager pager;
    OrderViewPagerAdapter adapter;
    private Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_view_pager);

        pager = (ViewPager) findViewById(R.id.pager);
        adapter = new OrderViewPagerAdapter(getSupportFragmentManager());
        pager.setAdapter(adapter);
    }

}
