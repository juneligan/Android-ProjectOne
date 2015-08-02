package com.project_one.controller;

import android.app.Activity;
import android.content.Context;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.R;
import com.project_one.model.Role;
import com.project_one.model.User;
import com.project_one.service.RoleService;
import com.project_one.service.RoleServiceImpl;
import com.project_one.service.UserService;
import com.project_one.service.UserServiceImpl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserActivity extends Activity {

    private Context context = this;
    private Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        displayRoles();
    }

    private void displayRoles() {
        List<String> typeOfRoles = fetchTypeOfRoles();
        spinner = (Spinner)findViewById(R.id.list_of_roles);
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,typeOfRoles);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);
    }

    private List<String> fetchTypeOfRoles() {
        RoleService roleService = new RoleServiceImpl(context);
        List<String> typeOfRoles = new ArrayList<String>();
        for(Role role : roleService.fetchAllRoles()) {
            typeOfRoles.add(role.getType());
        }
        return typeOfRoles;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    public void create(View view) throws SQLException {
        EditText usernameEditText = (EditText) findViewById(R.id.username);
        EditText passwordEditText = (EditText) findViewById(R.id.password);
        EditText confirmPasswordEditText = (EditText) findViewById(R.id.confirmPassword);
        Spinner selectedRoleSpinner = (Spinner) findViewById(R.id.list_of_roles);
        String username = usernameEditText.getText().toString();
        String password = passwordEditText.getText().toString();
        String selectedRole = selectedRoleSpinner.getSelectedItem().toString();
        String confirmPassword = confirmPasswordEditText.getText().toString();
        UserService userServiceImpl = new UserServiceImpl(context);

        RoleService roleService = new RoleServiceImpl(context);
        Role role = roleService.fetchRoleByType(selectedRole);
        if(!password.equals(confirmPassword)) {
            Toast.makeText(context, "Password does not matched", Toast.LENGTH_LONG).show();
        } else if(selectedRole == null && role == null) {
            Toast.makeText(context, "Role is not valid", Toast.LENGTH_LONG).show();
        } else {
            userServiceImpl.createUser(role.getId(), username, password);
            Toast.makeText(context, "New user added successfully", Toast.LENGTH_LONG).show();
        }
    }

    public void fetchAllUsers(View view) {
        UserService userServiceImpl = new UserServiceImpl(context);
        List<User> users = new ArrayList<User>();
        users = userServiceImpl.fetchAllUsers();
        for(User user : users) {
            Log.d("User Controller", user.getUsername());
            Log.d("User Controller", user.getRole().getType());
        }
    }
}
