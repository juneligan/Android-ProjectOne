package com.project_one.service;

import com.project_one.model.OrderDetail;

import java.util.List;

/**
 * Created by JenuNagil on 9/20/2015.
 */
public interface OrderDetailService {
    public List<OrderDetail> fetchAllAvailableItems();
}
