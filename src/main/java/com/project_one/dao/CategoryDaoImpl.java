package com.project_one.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.project_one.contract.TableData;
import com.project_one.model.Category;
import com.project_one.utils.DatabaseHelper;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by JenuNagil on 8/3/2015.
 */
public class CategoryDaoImpl implements CategoryDao {

    public static final String TAG = "CategoryDao";

    private SQLiteDatabase database;
    private DatabaseHelper dbHelper;
    private Context context;
    private String[] allColumns = {
            TableData.TableCategory._ID,
            TableData.TableCategory.NAME
    };

    public CategoryDaoImpl(Context context) {
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
    public Category fetchCategoryByName(String name) {
        Cursor cursor = database.query(TableData.TableCategory.TABLE_NAME, allColumns,
                TableData.TableCategory.NAME + " = ?",
                new String[] { String.valueOf(name)}, null, null, null, null);

        cursor.moveToFirst();
        return cursor.getCount() > 0 ? cursorToCategory(cursor) : null;
    }

    private Category cursorToCategory(Cursor cursor) {
        Category category = new Category();
        category.setId(cursor.getLong(0));
        category.setName(cursor.getString(1));
        return category;
    }

    @Override
    public Category create(String name) {
        ContentValues values = new ContentValues();
        values.put(TableData.TableCategory.NAME, name);
        long insertId = database.insert(TableData.TableCategory.TABLE_NAME, null, values);
        Log.d(TAG, "Insert category name " + name);
        Cursor cursor = database.query(TableData.TableCategory.TABLE_NAME, allColumns,
                TableData.TableCategory._ID + " = " + insertId, null, null, null, null);
        cursor.moveToFirst();
        Category newCategory = cursorToCategory(cursor);
        cursor.close();
        return newCategory;
    }

    @Override
    public List<Category> fetchAllCategories() {
        List<Category> categories = new ArrayList<Category>();
        Cursor cursor = database.query(TableData.TableCategory.TABLE_NAME, allColumns,
                null, null, null, null, null);

        cursor.moveToFirst();
        while(!cursor.isAfterLast()) {
            Category category = cursorToCategory(cursor);
            categories.add(category);
            cursor.moveToNext();
        }

        cursor.close();
        return categories;
    }
}
