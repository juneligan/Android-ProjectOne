package com.project_one.controller;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.R;
import com.example.jenunagil.myfirstapp.DisplayMessageActivity;
import com.project_one.service.UserService;
import com.project_one.service.UserServiceImpl;

public class LoginActivity extends Activity {
    public final static String EXTRA_MESSAGE = "com.example.jenunagil.myfirstapp.MESSAGE";
    private Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
        return true;
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
    public void create(View view) {
        Intent intent = new Intent(this, DisplayMessageActivity.class);
        EditText usernameEditText = (EditText) findViewById(R.id.username);
        EditText passwordEditText = (EditText) findViewById(R.id.password);
        String username = usernameEditText.getText().toString();
        String password = passwordEditText.getText().toString();
        UserService userServiceImpl = new UserServiceImpl(context);
        userServiceImpl.setContext(context);
        userServiceImpl.createUser(2, username, password);
        intent.putExtra(EXTRA_MESSAGE, username);
        startActivity(intent);
    }

    public void roleActivity(View view) {
        Intent intent = new Intent(this, RoleActivity.class);
        startActivity(intent);
    }

    public void userActivity(View view) {
        Intent intent = new Intent(this, UserActivity.class);
        startActivity(intent);
    }
}
