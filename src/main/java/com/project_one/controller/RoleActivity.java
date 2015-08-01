package com.project_one.controller;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.R;
import com.project_one.service.RoleService;
import com.project_one.service.RoleServiceImpl;

import java.sql.SQLException;

public class RoleActivity extends Activity {
    private Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_role);
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

    /** Called when the user clicks the Send button */
    public void create(View view) throws SQLException {
        EditText roleTypeEditText = (EditText) findViewById(R.id.type);
        String roleType = roleTypeEditText.getText().toString();
        RoleService roleServiceImpl = new RoleServiceImpl(context);
        if(roleServiceImpl.fetchRoleByType(roleType) != null) {
            Toast.makeText(context, "Role already exists.", Toast.LENGTH_LONG).show();
        } else {
            roleServiceImpl.createRole(roleType);
            Toast.makeText(context, "Added new role successfully.", Toast.LENGTH_LONG).show();
        }
    }
}
