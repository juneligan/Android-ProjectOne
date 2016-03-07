package com.project_one.model;

import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.project_one.common.type.RoleType;

/**
 * Created by JenuNagil on 7/28/2015.
 */
@Table(name = "User")
public class User extends AbstractDomain {

    @Column(name = "role", index = true)
    public Role role;
    @Column(name = "username", index = true)
    public String username;
    @Column(name = "password", index = true)
    public String password;

    public User(){
        super();
    }

    public User(Role role, String username, String password) {
        this.role = role;
        this.username = username;
        this.password = password;
    }
}
