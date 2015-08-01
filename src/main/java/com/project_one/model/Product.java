package com.project_one.model;

import java.math.BigDecimal;

/**
 * Created by JenuNagil on 7/30/2015.
 */
public class Product {
    private Long id;
    private Category category;
    private String name;
    private BigDecimal unitPrice;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Category getCategory() {
        return category;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }
}
