package com.project_one.model;

import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

/**
 * Created by JenuNagil on 7/30/2015.
 */
@Table(name = "Inventory_Item")
public class InventoryItem extends AbstractDomain {

    @Column(name = "product", index = true)
    public Product product;
    @Column(name = "quantity", index = true)
    public int quantity;
    @Column(name = "category", index = true)
    public Category category;

    public InventoryItem() {
        super();
    }

    public InventoryItem(Product product, int quantity, Category category) {
        this.product = product;
        this.quantity = quantity;
        this.category = category;
    }
}
