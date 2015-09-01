package com.project_one.controller.api.v1.fragment.product;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.R;
import com.project_one.model.Category;
import com.project_one.model.InventoryItem;
import com.project_one.model.Product;
import com.project_one.service.CategoryService;
import com.project_one.service.CategoryServiceImpl;
import com.project_one.service.InventoryItemService;
import com.project_one.service.InventoryItemServiceImpl;
import com.viewpagerindicator.TitlePageIndicator;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductViewPagerActivity extends ActionBarActivity {

    ViewPager pager;
    ProductViewPagerAdapter adapter;
    private Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_view_pager);

        pager = (ViewPager) findViewById(R.id.pager);
        adapter = new ProductViewPagerAdapter(getSupportFragmentManager());
        pager.setAdapter(adapter);

    }

    public void create(View view) throws SQLException {

        EditText productNameText = (EditText) findViewById(R.id.product_name);
        EditText unitPriceText = (EditText) findViewById(R.id.product_unit_price);
        EditText inventoryQuantityText = (EditText) findViewById(R.id.product_quantity);
        Spinner selectedCategorySpinner = (Spinner) findViewById(R.id.list_of_categories);
        String productName = productNameText.getText().toString();
        BigDecimal unitPrice = convertStringPriceToBigDecimal(unitPriceText.getText().toString());
        String selectedCategory = selectedCategorySpinner.getSelectedItem().toString();
        Integer quantity = convertStringQuantityToInt(inventoryQuantityText.getText().toString());

        if(quantity == null) return;

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
            unitPriceText.setText("");
            inventoryQuantityText.setText("");
            productNameText.setText("");
            adapter.notifyDataSetChanged();
        }
    }

    private Integer convertStringQuantityToInt(String value) {
        try {
            return value.equals("Quantity") || value.equals("") ?  0 : Integer.parseInt(value);
        } catch (NumberFormatException e) {
            Toast.makeText(context, "Quantity is invalid", Toast.LENGTH_LONG).show();
//            throw new NumberFormatException();
        }
        return null;
    }

    private BigDecimal convertStringPriceToBigDecimal(String value) {
        try {
            BigDecimal defaultUnitPrice = new BigDecimal("0.00");
            return value.equals("Price") || value.equals("") ? defaultUnitPrice : (new BigDecimal(value)).add(defaultUnitPrice);
        } catch(NumberFormatException e) {
            Toast.makeText(context, "Price is invalid", Toast.LENGTH_LONG).show();
//            throw new NumberFormatException();
        }
        return null;
    }

}
