package com.project_one.service;

import android.content.Context;

import com.project_one.dao.UserDao;
import com.project_one.dao.UserDaoImpl;
import com.project_one.model.User;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by JenuNagil on 7/29/2015.
 */
public class UserServiceImpl implements UserService {

    private UserDao userDaoImpl;
    private Context context;

    public UserServiceImpl(Context context) {
        try {
            userDaoImpl = new UserDaoImpl(context);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public User createUser(Long roleId, String username, String password) {
        return userDaoImpl.createUser(roleId, username, password);
    }

    public List<User> fetchAllUsers() {
        return userDaoImpl.fetchAllUsers();
    }
}
