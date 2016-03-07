package com.project_one.model;

import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.project_one.common.type.RoleType;

/**
 * Created by JenuNagil on 7/30/2015.
 */
@Table(name = "Role")
public class Role extends AbstractDomain {
    @Column(name = "type", index = true)
    public RoleType type;

    public Role() {
        super();
    }


    public Role(RoleType type){
        this.type = type;
    }

}
