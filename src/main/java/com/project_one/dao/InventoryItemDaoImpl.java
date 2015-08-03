package com.project_one.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.project_one.contract.TableData;
import com.project_one.model.Category;
import com.project_one.model.InventoryItem;
import com.project_one.model.Product;
import com.project_one.utils.DatabaseHelper;

import java.math.BigDecimal;
import java.sql.SQLException;

/**
 * Created by JenuNagil on 8/3/2015.
 */
public class InventoryItemDaoImpl implements InventoryItemDao {
    public static final String TAG = "UserDao";

    private SQLiteDatabase database;
    private DatabaseHelper dbHelper;
    private Context context;
    private String[] allColumns = {
            TableData.TableUser._ID,
            TableData.TableUser.USERNAME,
            TableData.TableUser.ROLE_ID,
            TableData.TableUser.PASSWORD
    };

    public InventoryItemDaoImpl(Context context) throws SQLException {
        this.context = context;
        dbHelper = new DatabaseHelper(context);
        open();
    }

    private void open() {
        database = dbHelper.getWritableDatabase();
    }

    private void close() {
        dbHelper.close();
    }

    @Override
    public InventoryItem create(InventoryItem item) throws SQLException {
        database.beginTransaction();
        try {
            ContentValues productValues = new ContentValues();
            productValues.put(TableData.TableProduct.PRODUCT_NAME, item.getProduct().getName());
            productValues.put(TableData.TableProduct.UNIT_PRICE, item.getProduct().getUnitPrice().toString());

            long insertProductId = database.insert(TableData.TableProduct.TABLE_NAME, null, productValues);

            ContentValues inventoryItemValues = new ContentValues();
            inventoryItemValues.put(TableData.TableInventoryItem.PRODUCT_ID, insertProductId);
            inventoryItemValues.put(TableData.TableInventoryItem.CATEGORY_ID, item.getCategory().getId());
            inventoryItemValues.put(TableData.TableInventoryItem.INVENTORY_QUANTITY, item.getQuantity());

            long insertInventoryItemId = database.insert(TableData.TableInventoryItem.TABLE_NAME, null, inventoryItemValues);

            String query = "SELECT InventoryItem._id, InventoryItem.product_id, Product.product_name, " +
                    "Product.unit_price, InventoryItem.category_id, Category.name, InventoryItem.inventory_quantity, InventoryItem.added_date " +
                    "FROM InventoryItem INNER JOIN Product ON InventoryItem.product_id=Product._id " +
                    "INNER JOIN Category ON InventoryItem.category_id=Category._id " +
                    "WHERE InventoryItem._id = "+insertInventoryItemId + ";";
            Cursor cursor = database.rawQuery(query, null);
            cursor.moveToFirst();
            InventoryItem newInventory = cursorToInventoryItemWithProductAndCategory(cursor);
            cursor.close();
            database.setTransactionSuccessful();
            return newInventory;
        } catch(Exception e) {
            throw new SQLException();
        } finally {
            database.endTransaction();
        }
    }

    private InventoryItem cursorToInventoryItemWithProductAndCategory(Cursor cursor) {

        if(cursor.getCount() == 0) {
            return null;
        }
        Product product = new Product();
        product.setId(cursor.getLong(1));
        product.setName(cursor.getString(2));
        product.setUnitPrice(new BigDecimal(cursor.getString(3)));

        Category category = new Category();
        category.setId(cursor.getLong(4));
        category.setName(cursor.getString(5));

        InventoryItem inventoryItem = new InventoryItem();
        inventoryItem.setProduct(product);
        inventoryItem.setCategory(category);
        return inventoryItem;
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

        String query = "SELECT InventoryItem._id, InventoryItem.product_id, Product.product_name, " +
                "Product.unit_price, InventoryItem.category_id, Category.name, InventoryItem.inventory_quantity, InventoryItem.added_date " +
                "FROM InventoryItem INNER JOIN Product ON InventoryItem.product_id=Product._id " +
                "INNER JOIN Category ON InventoryItem.category_id=Category._id " +
                "WHERE Category._id = "+category.getId() +" AND Product.product_name = '" + productName + "';";
        Cursor cursor = database.rawQuery(query, null);
        cursor.moveToFirst();
        InventoryItem newItem = cursorToInventoryItemWithProductAndCategory(cursor);
        cursor.close();

        return newItem;
    }
}
