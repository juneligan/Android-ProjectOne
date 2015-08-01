package com.project_one.service;

import android.content.Context;

import com.project_one.dao.RoleDao;
import com.project_one.dao.RoleDaoImpl;
import com.project_one.model.Role;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by JenuNagil on 8/1/2015.
 */
public class RoleServiceImpl implements RoleService {

    private RoleDao roleDaoImpl;
    private Context context;

    public RoleServiceImpl(Context context) {
        try {
            roleDaoImpl = new RoleDaoImpl(context);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Role createRole(String type) {

        return roleDaoImpl.createRole(type);
    }

    public List<Role> fetchAllRoles() {
        return roleDaoImpl.fetchAllRoles();
    }

    public Role fetchRoleByType(String roleType) {
        return roleDaoImpl.fetchRoleByType(roleType);
    }
}
