package com.project_one.utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.project_one.contract.TableData;

/**
 * Created by JenuNagil on 7/28/2015.
 */
public class DatabaseHelper extends SQLiteOpenHelper {
    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "ProjectOne.db";
    private static final String TEXT_TYPE = " TEXT";
    private static final String INTEGER_TYPE = " INTEGER";
    private static final String TIMESTAMP = " TIMESTAMP DEFAULT CURRENT_TIMESTAMP";
    private static final String FOREIGN_KEY = " FOREIGN KEY";
    private static final String OPEN_PAREN = " (";
    private static final String CLOSE_PAREN = ") ";
    private static final String REFERENCES = " REFERENCES ";
    private static final String COMMA_SEP = ",";

    private static final String SQL_CREATE_ROLE_TABLE =
            "CREATE TABLE IF NOT EXISTS " + TableData.TableRole.TABLE_NAME + " ( " +
                    TableData.TableRole._ID + " INTEGER PRIMARY KEY," +
                    TableData.TableRole.TYPE + TEXT_TYPE + " );";

    private static final String SQL_CREATE_USER_TABLE =
            "CREATE TABLE IF NOT EXISTS " + TableData.TableUser.TABLE_NAME + " ( " +
                    TableData.TableUser._ID + " INTEGER PRIMARY KEY," +
                    TableData.TableUser.USERNAME + TEXT_TYPE + COMMA_SEP +
                    TableData.TableUser.PASSWORD + TEXT_TYPE + COMMA_SEP +
                    TableData.TableUser.ROLE_ID + INTEGER_TYPE + COMMA_SEP +
                    FOREIGN_KEY + OPEN_PAREN + TableData.TableUser.ROLE_ID + CLOSE_PAREN +
                    REFERENCES + TableData.TableRole.TABLE_NAME + OPEN_PAREN +
                    TableData.TableRole._ID + CLOSE_PAREN +
                    " );";

    private static final String SQL_CREATE_CATEGORY_TABLE =
            "CREATE TABLE IF NOT EXISTS " + TableData.TableCategory.TABLE_NAME + " ( " +
                    TableData.TableCategory._ID + " INTEGER PRIMARY KEY," +
                    TableData.TableCategory.NAME + TEXT_TYPE + " );";

    private static final String SQL_CREATE_ORDER_TABLE =
            "CREATE TABLE IF NOT EXISTS " + TableData.TableSalesOrder.TABLE_NAME + " ( " +
                    TableData.TableSalesOrder._ID + " INTEGER PRIMARY KEY," +
                    TableData.TableSalesOrder.USER_ID + INTEGER_TYPE + COMMA_SEP +
                    TableData.TableSalesOrder.AMOUNT + TEXT_TYPE + COMMA_SEP +
                    TableData.TableSalesOrder.CREATED_DATE + TIMESTAMP + COMMA_SEP +
                    FOREIGN_KEY + OPEN_PAREN + TableData.TableSalesOrder.USER_ID + CLOSE_PAREN +
                    REFERENCES + TableData.TableUser.TABLE_NAME + OPEN_PAREN +
                    TableData.TableUser._ID + CLOSE_PAREN + " );";

    private static final String SQL_CREATE_ORDER_DETAIL_TABLE =
            "CREATE TABLE IF NOT EXISTS " + TableData.TableOrderDetail.TABLE_NAME + " ( " +
                    TableData.TableOrderDetail._ID + " INTEGER PRIMARY KEY," +
                    TableData.TableOrderDetail.PRODUCT_ID + INTEGER_TYPE + COMMA_SEP +
                    TableData.TableOrderDetail.UNIT_PRICE + TEXT_TYPE + COMMA_SEP +
                    TableData.TableOrderDetail.QUANTITY + INTEGER_TYPE + COMMA_SEP +
                    TableData.TableOrderDetail.ORDER_ID + INTEGER_TYPE + COMMA_SEP +
                    TableData.TableOrderDetail.ADDED_DATE + TIMESTAMP + COMMA_SEP +
                    FOREIGN_KEY + OPEN_PAREN + TableData.TableOrderDetail.ORDER_ID + CLOSE_PAREN +
                    REFERENCES + TableData.TableSalesOrder.TABLE_NAME + OPEN_PAREN +
                    TableData.TableSalesOrder._ID + CLOSE_PAREN + COMMA_SEP +
                    FOREIGN_KEY + OPEN_PAREN + TableData.TableOrderDetail.PRODUCT_ID + CLOSE_PAREN +
                    REFERENCES + TableData.TableProduct.TABLE_NAME + OPEN_PAREN +
                    TableData.TableProduct._ID + CLOSE_PAREN + " );";

    private static final String SQL_CREATE_PRODUCT_TABLE =
            "CREATE TABLE IF NOT EXISTS " + TableData.TableProduct.TABLE_NAME + " ( " +
                    TableData.TableProduct._ID + " INTEGER PRIMARY KEY," +
                    TableData.TableProduct.PRODUCT_NAME + TEXT_TYPE + COMMA_SEP +
                    TableData.TableProduct.SELLING_PRICE + TEXT_TYPE + COMMA_SEP +
                    TableData.TableProduct.UNIT_PRICE + TEXT_TYPE + " );";

    private static final String SQL_CREATE_INVENTORY_ITEM_TABLE =
            "CREATE TABLE IF NOT EXISTS " + TableData.TableInventoryItem.TABLE_NAME + " ( " +
                    TableData.TableInventoryItem._ID + " INTEGER PRIMARY KEY," +
                    TableData.TableInventoryItem.PRODUCT_ID + INTEGER_TYPE + COMMA_SEP +
                    TableData.TableInventoryItem.INVENTORY_QUANTITY + INTEGER_TYPE + COMMA_SEP +
                    TableData.TableInventoryItem.ADDED_DATE + TIMESTAMP + COMMA_SEP +
                    TableData.TableInventoryItem.CATEGORY_ID + INTEGER_TYPE + COMMA_SEP +
                    FOREIGN_KEY + OPEN_PAREN + TableData.TableInventoryItem.CATEGORY_ID + CLOSE_PAREN +
                    REFERENCES + TableData.TableCategory.TABLE_NAME + OPEN_PAREN +
                    TableData.TableCategory._ID + CLOSE_PAREN + COMMA_SEP +
                    FOREIGN_KEY + OPEN_PAREN + TableData.TableInventoryItem.PRODUCT_ID + CLOSE_PAREN +
                    REFERENCES + TableData.TableProduct.TABLE_NAME + OPEN_PAREN +
                    TableData.TableProduct._ID + CLOSE_PAREN + " );";

    private static final String SQL_DELETE_ROLE_TABLE =
            "DROP TABLE IF EXISTS " + TableData.TableUser.TABLE_NAME;
    private static final String SQL_DELETE_USER_TABLE =
            "DROP TABLE IF EXISTS " + TableData.TableUser.TABLE_NAME;
    private static final String SQL_DELETE_CATEGORY_TABLE =
            "DROP TABLE IF EXISTS " + TableData.TableCategory.TABLE_NAME;
    private static final String SQL_DELETE_ORDER_TABLE =
            "DROP TABLE IF EXISTS " + TableData.TableSalesOrder.TABLE_NAME;
    private static final String SQL_DELETE_ORDER_DETAIL_TABLE =
            "DROP TABLE IF EXISTS " + TableData.TableOrderDetail.TABLE_NAME;
    private static final String SQL_DELETE_PRODUCT_TABLE =
            "DROP TABLE IF EXISTS " + TableData.TableProduct.TABLE_NAME;
    private static final String SQL_DELETE_INVENTORY_ITEM_TABLE =
            "DROP TABLE IF EXISTS " + TableData.TableInventoryItem.TABLE_NAME;

    private static DatabaseHelper instance;

    @Deprecated
    public static synchronized DatabaseHelper getInstance(Context context) {

        // Use the application context, which will ensure that you
        // don't accidentally leak an Activity's context.
        // See this article for more information: http://bit.ly/6LRzfx
        if (instance == null) {
            instance = new DatabaseHelper(context.getApplicationContext());
        }
        return instance;
    }
    private DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        Log.d("Database Helper", "Create DATABASE " + DATABASE_NAME);
    }

    @Deprecated
    public void onCreate(SQLiteDatabase db) {
        Log.d("Database Helper", "Creating Table ");
        db.execSQL(SQL_CREATE_ROLE_TABLE);
        db.execSQL(SQL_CREATE_USER_TABLE);
        db.execSQL(SQL_CREATE_CATEGORY_TABLE);
        db.execSQL(SQL_CREATE_ORDER_TABLE);
        db.execSQL(SQL_CREATE_ORDER_DETAIL_TABLE);
        db.execSQL(SQL_CREATE_PRODUCT_TABLE);
        db.execSQL(SQL_CREATE_INVENTORY_ITEM_TABLE);
    }
    @Deprecated
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(SQL_DELETE_ROLE_TABLE);
        db.execSQL(SQL_DELETE_USER_TABLE);
        db.execSQL(SQL_DELETE_CATEGORY_TABLE);
        db.execSQL(SQL_DELETE_ORDER_TABLE);
        db.execSQL(SQL_DELETE_ORDER_DETAIL_TABLE);
        db.execSQL(SQL_DELETE_PRODUCT_TABLE);
        db.execSQL(SQL_DELETE_INVENTORY_ITEM_TABLE);
        Log.d("Database Helper", "Delete Tables for UPGRADE");
        onCreate(db);
    }
    @Deprecated
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
}
