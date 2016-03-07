package com.project_one.model;

import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

/**
 * Created by JenuNagil on 7/30/2015.
 */
@Table(name="Category")
public class Category extends AbstractDomain {
    @Column(name = "name", index = true)
    public String name;

    public Category() {
        super();
    }

    public Category(String name) {
        this.name = name;
    }
}
