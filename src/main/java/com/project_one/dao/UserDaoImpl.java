package com.project_one.dao;

import android.app.ActionBar;
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
public class UserDaoImpl implements UserDao {

    public static final String TAG = "UserDao";

    private SQLiteDatabase database;
    private DatabaseHelper dbHelper;
    private Context context;
    private String[] allColumns = {
            TableData.TableUser._ID,
            TableData.TableUser.USERNAME,
            TableData.TableUser.ROLE_ID,
            TableData.TableUser.PASSWORD
    };

    public UserDaoImpl(Context context) throws SQLException {
        this.context = context;
        dbHelper = new DatabaseHelper(context);
        open();
    }

    private void open() {
        database = dbHelper.getWritableDatabase();
    }

    private void close() {
        dbHelper.close();
    }

    public User createUser(Long roleId, String username, String password) {
        ContentValues values = new ContentValues();
        values.put(TableData.TableUser.USERNAME, username);
        values.put(TableData.TableUser.PASSWORD, password);
        values.put(TableData.TableUser.ROLE_ID, roleId);
        long insertId = database.insert(TableData.TableUser.TABLE_NAME, null, values);
        Log.d(TAG, "Insert user data " + username + " and " + password);
        String query = "SELECT User._id, User.username, User.role_id, Role.type " +
                "FROM User INNER JOIN Role ON User.role_id=Role._id " +
                "WHERE User._id = "+insertId + ";";
        Cursor cursor = database.rawQuery(query, null);
//        Cursor cursor = database.query(TableData.TableUser.TABLE_NAME, allColumns,
//                TableData.TableUser._ID + " = " + insertId, null, null, null, null);
        cursor.moveToFirst();
        User newUser = cursorToUser(cursor);
        cursor.close();
        return newUser;
    }

    public List<User> fetchAllUsers() {
        List<User> listOfUsers = new ArrayList<User>();
//        Cursor cursor = database.query(TableData.TableUser.TABLE_NAME, allColumns,
//                null, null, null, null, null);

        String query = "SELECT User._id, User.username, User.role_id, Role.type " +
                "FROM User INNER JOIN Role ON User.role_id=Role._id;";
        Cursor cursor = database.rawQuery(query, null);

        cursor.moveToFirst();
        while(!cursor.isAfterLast()) {
            User user = cursorToUser(cursor);
            listOfUsers.add(user);
            cursor.moveToNext();
        }

        cursor.close();
        return listOfUsers;
    }

    private User cursorToUser(Cursor cursor) {
        User user = new User();
        user.setId(cursor.getLong(0));
        user.setUsername(cursor.getString(1));
        Role role = new Role();
        role.setId(cursor.getLong(2));
        role.setType(cursor.getString(3));
        user.setRole(role);
        return user;
    }
}
