package com.project_one.model;

import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

import java.math.BigDecimal;

/**
 * Created by JenuNagil on 7/30/2015.
 */
@Table(name = "Order_Detail")
public class OrderDetail extends AbstractDomain {

    @Column(name = "sales_order", index = true)
    public SalesOrder order;
    @Column(name = "product", index = true)
    public Product product;
    @Column(name = "sell_price", index = true)
    public BigDecimal sellPrice;
    @Column(name = "quantity", index = true)
    public int quantity;

    public OrderDetail() {
        super();
    }

    public OrderDetail(SalesOrder order, Product product, BigDecimal sellPrice, int quantity) {
        this.order = order;
        this.product = product;
        this.sellPrice = sellPrice;
        this.quantity = quantity;
    }
}
