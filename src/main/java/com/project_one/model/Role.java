package com.project_one.model;

/**
 * Created by JenuNagil on 7/30/2015.
 */
public class Role {
    private Long id;
    private String type;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
