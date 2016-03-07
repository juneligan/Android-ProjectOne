package com.project_one.dao;

import com.project_one.model.Product;

/**
 * Created by JenuNagil on 3/6/2016.
 */
public interface ProductDao {

    Product create(Product product);

    Product findByName(String name);
}
