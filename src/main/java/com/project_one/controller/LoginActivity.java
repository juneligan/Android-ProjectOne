package com.project_one.controller;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.R;
import com.project_one.controller.api.v1.fragment.order.OrderViewPagerActivity;
import com.project_one.controller.api.v1.fragment.product.ProductViewPagerActivity;
import com.project_one.model.User;
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

    public void authenticateUser(View view) {
        EditText usernameText = (EditText) findViewById(R.id.username);
        EditText passwordText = (EditText) findViewById(R.id.password);
        User user = new User();
        user.setUsername(usernameText.getText().toString());
        user.setPassword(passwordText.getText().toString());

        UserService userService = new UserServiceImpl(context);
        if(!userService.authenticateUser(user)) {
            Toast.makeText(context, "Invalid username/password", Toast.LENGTH_LONG).show();
        } else {
            Intent intent = new Intent(this, HomeActivity.class);
            startActivity(intent);
        }
    }

    public void roleActivity(View view) {
        Intent intent = new Intent(this, RoleActivity.class);
        startActivity(intent);
    }

    public void userActivity(View view) {
        Intent intent = new Intent(this, UserActivity.class);
        startActivity(intent);
    }

    public void categoryActivity(View view) {
        Intent intent = new Intent(this, CategoryActivity.class);
        startActivity(intent);
    }

    public void productActivity(View view) {
        Intent intent = new Intent(this, ProductViewPagerActivity.class);
        startActivity(intent);
    }

    public void orderActivity(View view) {
        Intent intent = new Intent(this, OrderViewPagerActivity.class);
        startActivity(intent);
    }
}
