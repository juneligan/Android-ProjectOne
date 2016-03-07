package com.project_one.dao;

import com.activeandroid.query.Select;
import com.project_one.model.Product;

/**
 * Created by JenuNagil on 3/6/2016.
 */
public class ProductDaoImpl implements ProductDao {

    @Override
    public Product create(Product product) {
        product.save();
        return product;
    }

    @Override
    public Product findByName(String name) {
        return new Select().from(Product.class).where("name = ?", name).executeSingle();
    }
}
