package com.project_one.model;

import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

import java.math.BigDecimal;

/**
 * Created by JenuNagil on 7/30/2015.
 */
@Table(name="Product")
public class Product extends AbstractDomain {

    @Column(name = "name", index = true)
    public String name;
    @Column(name = "unit_price", index = true)
    public BigDecimal unitPrice;

    public Product() {
        super();
    }

    public Product(String name, BigDecimal unitPrice) {
        this.name = name;
        this.unitPrice = unitPrice;
    }

}
