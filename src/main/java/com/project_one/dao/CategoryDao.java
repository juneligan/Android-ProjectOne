package com.project_one.dao;

import com.project_one.model.Category;

import java.util.List;

/**
 * Created by JenuNagil on 8/3/2015.
 */
public interface CategoryDao {

    public Category fetchCategoryByName(String name);

    public Category create(String name);

    public List<Category> fetchAllCategories();
}
