package com.project_one.model;

import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created by JenuNagil on 7/30/2015.
 */
@Table(name = "Sales_Order")
public class SalesOrder extends AbstractDomain {

    @Column(name = "user", index = true)
    public User buyer;
    @Column(name = "total_amount", index = true)
    public BigDecimal totalAmount;
    @Column(name = "date_created", index = true)
    public Date dateCreated;

    public SalesOrder() {
        super();
    }

    public SalesOrder(User buyer, BigDecimal totalAmount, Date dateCreated) {
        this.buyer = buyer;
        this.totalAmount = totalAmount;
        this.dateCreated = dateCreated;
    }
    public List<OrderDetail> orders() {
        return getMany(OrderDetail.class, "Order");
    }
}
