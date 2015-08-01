package com.project_one.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

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
    private DatabaseHelper dbHelper;
    private Context context;
    private String[] allColumns = {
            TableData.TableRole._ID,
            TableData.TableRole.TYPE
    };

    public RoleDaoImpl(Context context) throws SQLException {
        this.context = context;
        dbHelper = new DatabaseHelper(context);
        Log.d(TAG, "create role DB:"+context);
        open();
        Log.d(TAG, "open Database:" + context);
    }

    private void open() {
        database = dbHelper.getWritableDatabase();
    }

    private void close() {
        dbHelper.close();
    }

    public Role createRole(String type) {
        ContentValues values = new ContentValues();
        values.put(TableData.TableRole.TYPE, type);
        long insertId = database.insert(TableData.TableRole.TABLE_NAME, null, values);
        Log.d(TAG, "Insert role type " + type);
        Cursor cursor = database.query(TableData.TableRole.TABLE_NAME, allColumns,
                TableData.TableRole._ID + " = " + insertId, null, null, null, null);
        cursor.moveToFirst();
        Role newRole = cursorToRole(cursor);
        cursor.close();
        return newRole;
    }

    public List<Role> fetchAllRoles() {
        List<Role> listOfRoles = new ArrayList<Role>();
        Cursor cursor = database.query(TableData.TableRole.TABLE_NAME, allColumns,
                null, null, null, null, null);

        cursor.moveToFirst();
        while(!cursor.isAfterLast()) {
            Role role = cursorToRole(cursor);
            listOfRoles.add(role);
            cursor.moveToNext();
        }

        cursor.close();
        return listOfRoles;
    }

    public Role fetchRoleByType(String type) {
        Cursor cursor = database.query(TableData.TableRole.TABLE_NAME, allColumns,
                TableData.TableRole.TYPE + " = ?",
                new String[] { String.valueOf(type)}, null, null, null, null);

        cursor.moveToFirst();
        return cursorToRole(cursor);
    }

    private Role cursorToRole(Cursor cursor) {
        Role role = new Role();
        role.setId(cursor.getLong(0));
        role.setType(cursor.getString(1));
        return role;
    }
}
