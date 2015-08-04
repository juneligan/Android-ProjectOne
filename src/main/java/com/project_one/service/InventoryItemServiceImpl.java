package com.project_one.service;

import android.content.Context;

import com.project_one.dao.InventoryItemDao;
import com.project_one.dao.InventoryItemDaoImpl;
import com.project_one.model.Category;
import com.project_one.model.InventoryItem;

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
        return null;
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
}
