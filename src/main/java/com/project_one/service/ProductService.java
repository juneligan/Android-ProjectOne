package com.project_one.service;

import com.project_one.model.Product;

/**
 * Created by JenuNagil on 3/6/2016.
 */
public interface ProductService {

    Product create(Product product);

    Product findByName(String name);
}
