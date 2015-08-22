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
import java.util.ArrayList;
import java.util.List;

/**
 * Created by JenuNagil on 8/3/2015.
 */
public class InventoryItemDaoImpl implements InventoryItemDao {
    public static final String TAG = "InventoryItemDao";

    private SQLiteDatabase database;
    private DatabaseHelper dbHelper;
    private Context context;
    private String[] allProductColumns = {
            TableData.TableProduct._ID,
            TableData.TableProduct.PRODUCT_NAME,
            TableData.TableProduct.UNIT_PRICE
    };
    private String[] allInventoryColumns = {
            TableData.TableInventoryItem._ID,
            TableData.TableInventoryItem.PRODUCT_ID,
            TableData.TableInventoryItem.INVENTORY_QUANTITY,
            TableData.TableInventoryItem.CATEGORY_ID,
            TableData.TableInventoryItem.ADDED_DATE
    };

    public InventoryItemDaoImpl(Context context) throws SQLException {
        this.context = context;
        dbHelper = DatabaseHelper.getInstance(context);
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
        inventoryItem.setId(cursor.getLong(0));
        inventoryItem.setProduct(product);
        inventoryItem.setCategory(category);
        inventoryItem.setQuantity(cursor.getInt(6));
        return inventoryItem;
    }

    @Override
    public InventoryItem fetchInventoryItemById(Long itemId) {

        String query = "SELECT InventoryItem._id, InventoryItem.product_id, Product.product_name, " +
                "Product.unit_price, InventoryItem.category_id, Category.name, InventoryItem.inventory_quantity, InventoryItem.added_date " +
                "FROM InventoryItem INNER JOIN Product ON InventoryItem.product_id=Product._id " +
                "INNER JOIN Category ON InventoryItem.category_id=Category._id " +
                "WHERE InventoryItem.product_id = "+itemId+";";
        Log.d(TAG, itemId.toString());
        Cursor cursor = database.rawQuery(query, null);
        cursor.moveToFirst();
        InventoryItem newItem = cursorToInventoryItemWithProductAndCategory(cursor);
        cursor.close();

        return newItem;
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

    @Override
    public List<InventoryItem> fetchAllItems() {

        String query = "SELECT InventoryItem._id, InventoryItem.product_id, Product.product_name, " +
                "Product.unit_price, InventoryItem.category_id, Category.name, InventoryItem.inventory_quantity, InventoryItem.added_date " +
                "FROM InventoryItem INNER JOIN Product ON InventoryItem.product_id=Product._id " +
                "INNER JOIN Category ON InventoryItem.category_id=Category._id ;";
        Cursor cursor = database.rawQuery(query, null);
        cursor.moveToFirst();
        List<InventoryItem> listOfProductItems = new ArrayList<InventoryItem>();
        while(!cursor.isAfterLast()) {
            InventoryItem newItem = cursorToInventoryItemWithProductAndCategory(cursor);
            listOfProductItems.add(newItem);
            cursor.moveToNext();
        }
        cursor.close();
        return listOfProductItems;
    }

    @Override
    public InventoryItem update(InventoryItem updatedItem) throws SQLException {
        database.beginTransaction();
        InventoryItem newInventory;
        try {
            ContentValues productValues = new ContentValues();
            productValues.put(TableData.TableProduct.PRODUCT_NAME, updatedItem.getProduct().getName());
            productValues.put(TableData.TableProduct.UNIT_PRICE, updatedItem.getProduct().getUnitPrice().toString());

            long numberOfUpdatedProducts = database.update(TableData.TableProduct.TABLE_NAME, productValues, TableData.TableProduct._ID + " = ?",
                    new String[] { String.valueOf(updatedItem.getProduct().getId()) } );

            if(numberOfUpdatedProducts < 1)
                throw new SQLException();

            ContentValues inventoryItemValues = new ContentValues();
            inventoryItemValues.put(TableData.TableInventoryItem.CATEGORY_ID, updatedItem.getCategory().getId());
            inventoryItemValues.put(TableData.TableInventoryItem.INVENTORY_QUANTITY, updatedItem.getQuantity());

            long numberOfUpdatedItems = database.update(TableData.TableInventoryItem.TABLE_NAME, inventoryItemValues, TableData.TableInventoryItem._ID + " = ?",
                    new String[] { String.valueOf(updatedItem.getId())});

            if(numberOfUpdatedItems < 1)
                throw new SQLException();

            String query = "SELECT InventoryItem._id, InventoryItem.product_id, Product.product_name, " +
                    "Product.unit_price, InventoryItem.category_id, Category.name, InventoryItem.inventory_quantity, InventoryItem.added_date " +
                    "FROM InventoryItem INNER JOIN Product ON InventoryItem.product_id=Product._id " +
                    "INNER JOIN Category ON InventoryItem.category_id=Category._id " +
                    "WHERE InventoryItem._id = "+updatedItem.getId() + ";";
            Cursor cursor = database.rawQuery(query, null);
            cursor.moveToFirst();
            newInventory = cursorToInventoryItemWithProductAndCategory(cursor);
            cursor.close();
            database.setTransactionSuccessful();
            return newInventory;
        } catch(Exception e) {
            throw new SQLException();
        } finally {
            database.endTransaction();
        }
    }
}
