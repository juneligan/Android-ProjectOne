package com.project_one.service;

import android.content.Context;

import com.project_one.model.User;

import java.util.List;

/**
 * Created by JenuNagil on 7/29/2015.
 */
public interface UserService {

    public void setContext(Context context);

    public User createUser(Long roleId, String username, String password);

    public List<User> fetchAllUsers();
}
