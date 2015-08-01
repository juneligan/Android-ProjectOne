package com.project_one.model;

/**
 * Created by JenuNagil on 7/30/2015.
 */
public class InventoryItem {

    private Product product;
    private int quantity;

    public InventoryItem() {
        product = new Product();
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Product getProduct() {
        return product;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getQuantity() {
        return quantity;
    }
}
