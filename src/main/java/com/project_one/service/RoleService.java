package com.project_one.service;

import com.project_one.common.type.RoleType;
import com.project_one.model.Role;

import java.util.List;

/**
 * Created by JenuNagil on 8/1/2015.
 */
public interface RoleService {

    public Role createRole(RoleType type);

    public List<Role> fetchAllRoles();

    public Role fetchRoleByType(RoleType roleType);
}
