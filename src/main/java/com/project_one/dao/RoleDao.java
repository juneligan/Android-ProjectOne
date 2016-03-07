package com.project_one.dao;

import com.project_one.common.type.RoleType;
import com.project_one.model.Role;
import com.project_one.model.User;

import java.util.List;

/**
 * Created by JenuNagil on 7/28/2015.
 */
public interface RoleDao {
    public Role createRole(RoleType type);

    public List<Role> fetchAllRoles();

    public Role fetchRoleByType(RoleType type);
}
