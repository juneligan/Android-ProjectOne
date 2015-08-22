package com.project_one.controller.api.v1.fragment;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.R;
import com.project_one.controller.api.v1.fragment.product.CustomAdapterHolder;
import com.project_one.model.InventoryItem;
import com.project_one.service.InventoryItemService;
import com.project_one.service.InventoryItemServiceImpl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * A placeholder fragment containing a simple view.
 */
public class ProductViewPagerFragment extends ListFragment {

    public ProductViewPagerFragment() {
    }

    public static ProductViewPagerFragment newInstance(int position, int productViewPage) {
        ProductViewPagerFragment productViewPagerFragment = new ProductViewPagerFragment();

        Bundle bundle = new Bundle();
        bundle.putInt("viewPosition", position);
        bundle.putInt("productViewPage", productViewPage);
        productViewPagerFragment.setArguments(bundle);

        return productViewPagerFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_product_view_pager, container, false);

        int position = getArguments().getInt("productViewPage");

//        ImageView iv = (ImageView)rootView.findViewById(R.id.imageView);
//        iv.setImageResource(R.drawable.jellyfish);

        Toast.makeText(getActivity(), "Number "+ position, Toast.LENGTH_SHORT).show();
        displayProducts();
        return rootView;
    }

    private void displayProducts() {
        InventoryItemService inventoryItemServiceImpl;
        List<String> productNames = new ArrayList<String>();
        try {
            inventoryItemServiceImpl = new InventoryItemServiceImpl(getActivity());

            for(InventoryItem inventoryItem : inventoryItemServiceImpl.fetchAllItems()) {
                Log.d("Controller", inventoryItem.getProduct().getName());
                productNames.add(inventoryItem.getProduct().getName());
            }


            CustomAdapterHolder customAdapterHolder = new CustomAdapterHolder(getActivity(), R.layout.inventory_items_row_for_manage_product_inventory_quantity, inventoryItemServiceImpl.fetchAllItems());
            customAdapterHolder.setActivity(getActivity());
            setListAdapter(customAdapterHolder.getCustomInventoryArrayAdapter());
        } catch (SQLException e) {
            e.printStackTrace();
        }

//        thadapter = new CustomThumbnailAdapter(ProductActivity.this, R.layout.inventory_items_row_for_manage_product_inventory_quantity, productNames);
//        CustomAdapterHolder.CustomInventoryArrayAdapter customInventoryArrayAdapter;
//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
//                R.layout.inventory_items_row_for_manage_product_inventory_quantity, productNames);
    }


}
