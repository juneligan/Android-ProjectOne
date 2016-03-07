package com.project_one.dao;

import android.content.ContentValues;
import android.database.Cursor;

import com.activeandroid.query.Select;
import com.project_one.contract.TableData;
import com.project_one.model.Category;
import com.project_one.model.InventoryItem;
import com.project_one.model.Product;
import com.project_one.service.ProductService;
import com.project_one.service.ProductServiceImpl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by JenuNagil on 8/3/2015.
 */
public class InventoryItemDaoImpl implements InventoryItemDao {

    private ProductService productService;

    public InventoryItemDaoImpl() {
        productService = new ProductServiceImpl();
    }

    @Override
    public InventoryItem createOrUpdate(InventoryItem item) throws SQLException {
        Product product = productService.create(item.product);
        item.save();
        return item;
    }

    @Override
    public InventoryItem fetchInventoryItemById(Long itemId) {
        return new Select().from(InventoryItem.class).where("id = ?", itemId).executeSingle();
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
        //TODO fetch product
        Product product = productService.findByName(productName);
        if(product == null) return null;
        return new Select().from(InventoryItem.class).where("product = ? And category", product.getId(), category.getId()).executeSingle();
    }

    @Override
    public List<InventoryItem> fetchAllItems() {
        return new Select().from(InventoryItem.class).execute();
    }
}
