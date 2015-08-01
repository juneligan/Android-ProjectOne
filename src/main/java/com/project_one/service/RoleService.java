package com.project_one.service;

import com.project_one.model.Role;

import java.util.List;

/**
 * Created by JenuNagil on 8/1/2015.
 */
public interface RoleService {

    public Role createRole(String type);

    public List<Role> fetchAllRoles();

    public Role fetchRoleByType(String roleType);
}
