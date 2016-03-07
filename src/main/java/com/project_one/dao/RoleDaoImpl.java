package com.project_one.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.activeandroid.query.Select;
import com.project_one.common.type.RoleType;
import com.project_one.contract.TableData;
import com.project_one.model.Role;
import com.project_one.model.User;
import com.project_one.utils.DatabaseHelper;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by JenuNagil on 7/28/2015.
 */
public class RoleDaoImpl implements RoleDao {

    public static final String TAG = "RoleDao";

    private SQLiteDatabase database;
    public RoleDaoImpl() {}

    @Override
    public Role createRole(RoleType type) {
        Role role = new Role(type);
        //TODO create abstracted validation
        role.save();
        return role;
    }

    public List<Role> fetchAllRoles() {
        return new Select().from(Role.class).execute();
    }

    public Role fetchRoleByType(RoleType type) {
        return new Select().from(Role.class).where("type = ?", type).executeSingle();
    }
}
