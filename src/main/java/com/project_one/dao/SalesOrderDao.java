package com.project_one.dao;

import com.project_one.model.SalesOrder;

import java.util.List;

/**
 * Created by JenuNagil on 8/31/2015.
 */
public interface SalesOrderDao {
    public List<SalesOrder> fetchAllOrders();
}
