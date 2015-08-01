package com.project_one.dao;

import com.project_one.model.User;

import java.util.List;

/**
 * Created by JenuNagil on 7/28/2015.
 */
public interface UserDao {
    public User createUser(int roleId, String username, String password);

    public List<User> fetchAllUsers();
}
