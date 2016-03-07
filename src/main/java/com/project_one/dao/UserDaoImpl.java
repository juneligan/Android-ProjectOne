package com.project_one.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.activeandroid.query.Select;
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

    public User createUser(Role role, String username, String password) {
        User user = new User(role, username, password);
        user.save();
        return user;
    }

    public List<User> fetchAllUsersByRole(Role role) {
        List<User> listOfUsers = new Select().from(User.class).where("role = ?", role.getId()).execute();

//        List<User> listOfUsers = new ArrayList<User>();
////        Cursor cursor = database.query(TableData.TableUser.TABLE_NAME, allColumns,
////                null, null, null, null, null);
//
//        String query = "SELECT User._id, User.username, User.role_id, Role.type " +
//                "FROM User INNER JOIN Role ON User.role_id=Role._id;";
//        Cursor cursor = database.rawQuery(query, null);
//
//        cursor.moveToFirst();
//        while(!cursor.isAfterLast()) {
//            User user = cursorToUserWithRole(cursor);
//            listOfUsers.add(user);
//            cursor.moveToNext();
//        }
//
//        cursor.close();
        return listOfUsers;
    }

    @Override
    public boolean authenticateUser(User user) {

        User existingUser = new Select().from(User.class).where("username = ? AND password = ?", user.username, user.password).executeSingle();
        System.out.println("----------------------");
        System.out.println(existingUser);
        System.out.println("----------------------");
        return isNotEmpty(user);
    }

    private boolean isNotEmpty(User user) {
        return user != null;
    }
}
