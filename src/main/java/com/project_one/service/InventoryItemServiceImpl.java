package com.project_one.service;

import android.content.Context;

import com.project_one.dao.InventoryItemDao;
import com.project_one.dao.InventoryItemDaoImpl;
import com.project_one.model.Category;
import com.project_one.model.InventoryItem;
import com.project_one.model.Product;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by JenuNagil on 8/3/2015.
 */
public class InventoryItemServiceImpl implements InventoryItemService {

    private Context context;
    private InventoryItemDao inventoryItemDaoimpl;

    public InventoryItemServiceImpl(Context context) throws SQLException {
        this.context = context;
        inventoryItemDaoimpl = new InventoryItemDaoImpl(context);
    }

    @Override
    public InventoryItem create(InventoryItem item) throws SQLException {
        return inventoryItemDaoimpl.create(item);
    }

    @Override
    public InventoryItem fetchInventoryItemById(Long itemId) {
        return inventoryItemDaoimpl.fetchInventoryItemById(itemId);
    }

    @Override
    public InventoryItem fetchInventoryItemByName(String itemName) {
        return null;
    }

    @Override
    public InventoryItem fetchInventoryItemByCategory(Long categoryId) {
        return null;
    }

    @Override
    public InventoryItem fetchInventoryItemByNameAndCategory(String productName, Category category) {
        return inventoryItemDaoimpl.fetchInventoryItemByNameAndCategory(productName, category);
    }

    @Override
    public List<InventoryItem> fetchAllItems() {
        return inventoryItemDaoimpl.fetchAllItems();
    }

    @Override
    public InventoryItem addQuantity(Product product, int quantityToBeAdded) {
        InventoryItem inventoryItem = fetchInventoryItemById(product.getId());

        int quantity = inventoryItem.getQuantity() + quantityToBeAdded;
        inventoryItem.setQuantity(quantity);
        return update(inventoryItem);
    }

    public InventoryItem update(InventoryItem updatedItem) {
        try {
            return inventoryItemDaoimpl.update(updatedItem);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
