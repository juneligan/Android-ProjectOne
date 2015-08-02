package com.project_one.service;

import android.content.Context;

import com.project_one.dao.CategoryDao;
import com.project_one.dao.CategoryDaoImpl;
import com.project_one.model.Category;

import java.util.List;

/**
 * Created by JenuNagil on 8/3/2015.
 */
public class CategoryServiceImpl implements CategoryService {
    private Context context;
    private CategoryDao categoryDaoImpl;
    public CategoryServiceImpl(Context context) {
        categoryDaoImpl = new CategoryDaoImpl(context);
    }

    @Override
    public Category fetchCategoryByName(String name) {
        return categoryDaoImpl.fetchCategoryByName(name);
    }

    @Override
    public Category create(String name) {
        return categoryDaoImpl.create(name);
    }

    @Override
    public List<Category> fetchAllCategories() {
        return categoryDaoImpl.fetchAllCategories();
    }
}
