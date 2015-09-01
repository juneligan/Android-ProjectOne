package com.project_one.service;

import com.project_one.model.SalesOrder;

import java.util.List;

/**
 * Created by JenuNagil on 8/24/2015.
 */
public interface SalesOrderService {
    public List<SalesOrder> fetchAllOrders();
}
