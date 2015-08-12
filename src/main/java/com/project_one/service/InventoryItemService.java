package com.project_one.service;

import com.project_one.model.Category;
import com.project_one.model.InventoryItem;
import com.project_one.model.Product;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by JenuNagil on 8/3/2015.
 */
public interface InventoryItemService {

    public InventoryItem create(InventoryItem item) throws SQLException;

    public InventoryItem fetchInventoryItemById(Long itemId);

    public InventoryItem fetchInventoryItemByName(String itemName);

    public InventoryItem fetchInventoryItemByCategory(Long categoryId);

    public InventoryItem fetchInventoryItemByNameAndCategory(String productName, Category category);

    public List<InventoryItem> fetchAllItems();

    public InventoryItem addQuantity(Product product, int newQuantity);
}
