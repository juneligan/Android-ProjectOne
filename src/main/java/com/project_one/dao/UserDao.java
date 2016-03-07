package com.project_one.dao;

import com.project_one.model.Role;
import com.project_one.model.User;

import java.util.List;

/**
 * Created by JenuNagil on 7/28/2015.
 */
public interface UserDao {
    public User createUser(Role role, String username, String password);

    public List<User> fetchAllUsersByRole(Role role);

    public boolean authenticateUser(User user);
}
