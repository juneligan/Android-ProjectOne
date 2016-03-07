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
import com.project_one.common.type.RoleType;
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
        RoleService roleService = new RoleServiceImpl();
        List<String> typeOfRoles = new ArrayList<String>();
        for(Role role : roleService.fetchAllRoles()) {
            typeOfRoles.add(role.type.toString());
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
        RoleType selectedRoleType = RoleType.valueOf(selectedRole);
        String confirmPassword = confirmPasswordEditText.getText().toString();
        UserService userServiceImpl = new UserServiceImpl();

        RoleService roleService = new RoleServiceImpl();
        Role role = roleService.fetchRoleByType(selectedRoleType);
        if(!password.equals(confirmPassword)) {
            Toast.makeText(context, "Password does not matched", Toast.LENGTH_LONG).show();
        } else if(selectedRoleType == null && role == null) {
            Toast.makeText(context, "Role is not valid", Toast.LENGTH_LONG).show();
        } else {
            userServiceImpl.createUser(role, username, password);
            Toast.makeText(context, "New user added successfully", Toast.LENGTH_LONG).show();
        }
    }

    public void fetchAllCustomers(View view) {
        UserService userServiceImpl = new UserServiceImpl();
        List<User> users = userServiceImpl.fetchAllCustomer();
        for(User user : users) {
            Log.d("User Controller", user.username);
            Log.d("User Controller", user.role.type.toString());
        }
    }
}
