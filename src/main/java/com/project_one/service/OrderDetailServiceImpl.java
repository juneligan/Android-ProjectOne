package com.project_one.service;

import android.content.Context;
import android.support.v4.app.FragmentActivity;

import com.project_one.dao.OrderDetailDao;
import com.project_one.dao.OrderDetailDaoimpl;
import com.project_one.model.OrderDetail;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by JenuNagil on 9/20/2015.
 */
public class OrderDetailServiceImpl implements OrderDetailService {

    private Context context;
    private OrderDetailDao orderDetailDaoimpl;

    public OrderDetailServiceImpl(Context context) throws SQLException {
        this.context = context;
        orderDetailDaoimpl = new OrderDetailDaoimpl(context);
    }

    @Override
    public List<OrderDetail> fetchAllAvailableItems() {
        return orderDetailDaoimpl.fetchAllAvailableItems();
    }
}
