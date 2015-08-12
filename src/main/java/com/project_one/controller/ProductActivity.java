package com.project_one.controller;

import android.app.ListActivity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.R;
import com.project_one.model.Category;
import com.project_one.model.InventoryItem;
import com.project_one.model.Product;
import com.project_one.service.CategoryService;
import com.project_one.service.CategoryServiceImpl;
import com.project_one.service.InventoryItemService;
import com.project_one.service.InventoryItemServiceImpl;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductActivity extends ListActivity {

    private Context context = this;
    private Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);
        displayCategories();
        displayProducts();
    }

    private void displayCategories() {
        List<String> typeOfCategories = fetchTypeOfCategory();
        spinner = (Spinner)findViewById(R.id.list_of_categories);
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, typeOfCategories);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);
    }

    private void displayProducts() {
        InventoryItemService inventoryItemServiceImpl;
        List<String> productNames = new ArrayList<String>();
        try {
            inventoryItemServiceImpl = new InventoryItemServiceImpl(context);

            for(InventoryItem inventoryItem : inventoryItemServiceImpl.fetchAllItems()) {
                Log.d("Controller", inventoryItem.getProduct().getName());
                productNames.add(inventoryItem.getProduct().getName());
            }


            CustomAdapterHolder customAdapterHolder = new CustomAdapterHolder(ProductActivity.this, R.layout.list_row, inventoryItemServiceImpl.fetchAllItems());
            customAdapterHolder.setActivity(ProductActivity.this);
            setListAdapter(customAdapterHolder.getCustomInventoryArrayAdapter());
        } catch (SQLException e) {
            e.printStackTrace();
        }

//        thadapter = new CustomThumbnailAdapter(ProductActivity.this, R.layout.list_row, productNames);
//        CustomAdapterHolder.CustomInventoryArrayAdapter customInventoryArrayAdapter;
//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
//                R.layout.list_row, productNames);
    }

    private List<String> fetchTypeOfCategory() {
        CategoryService categoryServiceImpl = new CategoryServiceImpl(context);
        List<String> categories = new ArrayList<String>();
        for(Category category: categoryServiceImpl.fetchAllCategories()) {
            categories.add(category.getName());
        }
        return categories;
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

        EditText productNameText = (EditText) findViewById(R.id.product_name);
        EditText unitPriceText = (EditText) findViewById(R.id.product_unit_price);
        EditText inventoryQuantityText = (EditText) findViewById(R.id.product_quantity);
        Spinner selectedCategorySpinner = (Spinner) findViewById(R.id.list_of_categories);
        String productName = productNameText.getText().toString();
        BigDecimal unitPrice = convertStringPriceToBigDecimal(unitPriceText.getText().toString());
        String selectedCategory = selectedCategorySpinner.getSelectedItem().toString();
        int quantity = convertStringQuantityToInt(inventoryQuantityText.getText().toString());
        InventoryItemService inventoryItemServiceImpl = new InventoryItemServiceImpl(context);

        CategoryService categoryService = new CategoryServiceImpl(context);
        Category category = categoryService.fetchCategoryByName(selectedCategory);
        if(category == null) {
            Toast.makeText(context, "Category is not yet initialize", Toast.LENGTH_LONG).show();
        } else if(productName.equals("")) {
            Toast.makeText(context, "Name field must not be empty", Toast.LENGTH_LONG).show();
            return;
        }

        InventoryItem existingInventoryItem = inventoryItemServiceImpl.fetchInventoryItemByNameAndCategory(productName, category);
        if(existingInventoryItem != null) {
            Toast.makeText(context, "Product already exist in category "+ selectedCategory, Toast.LENGTH_LONG).show();
        } else {
            Product product = new Product();
            product.setName(productName);
            product.setUnitPrice(unitPrice);

            InventoryItem inventoryItem = new InventoryItem();
            inventoryItem.setProduct(product);
            inventoryItem.setCategory(category);
            inventoryItem.setQuantity(quantity);

            inventoryItemServiceImpl.create(inventoryItem);
            Toast.makeText(context, "New item added successfully", Toast.LENGTH_LONG).show();
            displayProducts();
        }
    }

    private int convertStringQuantityToInt(String value) {
        try {
            return value.equals("Quantity") || value.equals("") ?  0 : Integer.parseInt(value);
        } catch (NumberFormatException e) {
            Toast.makeText(context, "Quantity is invalid", Toast.LENGTH_LONG).show();
//            throw new NumberFormatException();
        }
        return 0;
    }

    private BigDecimal convertStringPriceToBigDecimal(String value) {
        try {
            return value.equals("Price") || value.equals("") ? new BigDecimal("0.00") : new BigDecimal(value);
        } catch(NumberFormatException e) {
            Toast.makeText(context, "Price is invalid", Toast.LENGTH_LONG).show();
//            throw new NumberFormatException();
        }
        return null;
    }
}
