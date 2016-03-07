package com.project_one.controller;

import android.app.Activity;
import android.content.Context;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.R;
import com.project_one.service.CategoryService;
import com.project_one.service.CategoryServiceImpl;

public class CategoryActivity extends Activity {

    private Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
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

    public void create(View view) {
        EditText categoryNameText = (EditText) findViewById(R.id.category_name);
        CategoryService categoryService = new CategoryServiceImpl();
        if(categoryService.fetchCategoryByName(categoryNameText.getText().toString()) != null) {
            Toast.makeText(context, "Category already exist", Toast.LENGTH_LONG).show();
        } else {
            categoryService.create(categoryNameText.getText().toString());
            Toast.makeText(context, "New category is successfully added", Toast.LENGTH_LONG).show();
        }

    }
}
