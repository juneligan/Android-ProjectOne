package com.project_one.service;

import android.content.Context;
import android.support.v4.app.FragmentActivity;

import com.project_one.dao.SalesOrderDao;
import com.project_one.dao.SalesOrderDaoImpl;
import com.project_one.model.SalesOrder;

import java.util.List;

/**
 * Created by JenuNagil on 8/24/2015.
 */
public class SalesOrderServiceImpl implements SalesOrderService {
    private SalesOrderDao salesOrderDaoImpl;

    public SalesOrderServiceImpl() {
        salesOrderDaoImpl = new SalesOrderDaoImpl();
    }

    @Override
    public List<SalesOrder> fetchAllOrders() {
        return salesOrderDaoImpl.fetchAllOrders();
    }
}
