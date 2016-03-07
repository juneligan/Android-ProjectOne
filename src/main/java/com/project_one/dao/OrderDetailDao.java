package com.project_one.dao;

import com.project_one.model.OrderDetail;

import java.util.List;

/**
 * Created by JenuNagil on 9/20/2015.
 */
public interface OrderDetailDao {
    public List<OrderDetail> fetchAllAvailableItems();
}
