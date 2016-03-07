package com.project_one.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.activeandroid.query.Select;
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

    public CategoryDaoImpl() {
    }

    @Override
    public Category fetchCategoryByName(String name) {
        return new Select().from(Category.class).where("name = ?", name).executeSingle();
    }

    @Override
    public Category create(String name) {
        Category newCategory = new Category(name);
        newCategory.save();
        return newCategory;
    }

    @Override
    public List<Category> fetchAllCategories() {
        return new Select().from(Category.class).execute();
    }
}
