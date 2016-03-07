package com.project_one.service;

import android.content.Context;

import com.project_one.common.type.RoleType;
import com.project_one.dao.UserDao;
import com.project_one.dao.UserDaoImpl;
import com.project_one.model.Role;
import com.project_one.model.User;

import java.util.List;

/**
 * Created by JenuNagil on 7/29/2015.
 */
public class UserServiceImpl implements UserService {

    private UserDao userDaoImpl;
    private RoleService roleServiceImpl;
    public UserServiceImpl() {
        userDaoImpl = new UserDaoImpl();
        roleServiceImpl = new RoleServiceImpl();
    }

    public User createUser(Role role, String username, String password) {
        return userDaoImpl.createUser(role, username, password);
    }

    public List<User> fetchAllCustomer() {
        Role role = roleServiceImpl.fetchRoleByType(RoleType.CUSTOMER);
        return userDaoImpl.fetchAllUsersByRole(role);
    }

    @Override
    public boolean authenticateUser(User user) {
        return userDaoImpl.authenticateUser(user);
    }
}
