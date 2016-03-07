package com.project_one.service;

import com.project_one.dao.ProductDao;
import com.project_one.dao.ProductDaoImpl;
import com.project_one.model.Product;

/**
 * Created by JenuNagil on 3/6/2016.
 */
public class ProductServiceImpl implements ProductService {

    private ProductDao productDao;
    public ProductServiceImpl() {
        productDao = new ProductDaoImpl();
    }

    @Override
    public Product create(Product product) {
        return productDao.create(product);
    }

    @Override
    public Product findByName(String name) {
        return productDao.findByName(name);
    }
}
