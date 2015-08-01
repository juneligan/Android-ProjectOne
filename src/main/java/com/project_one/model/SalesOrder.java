package com.project_one.model;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by JenuNagil on 7/30/2015.
 */
public class SalesOrder {
    private Long id;
    private User user;
    private List<OrderDetail> orderDetails;
    private BigDecimal amount;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setOrderDetails(List<OrderDetail> orderDetails) {
        this.orderDetails = orderDetails;
    }

    public List<OrderDetail> getOrderDetails() {
        return orderDetails;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }
}
