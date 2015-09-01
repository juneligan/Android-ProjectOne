package com.project_one.controller.api.v1.fragment.product;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.R;
import com.project_one.model.Category;
import com.project_one.service.CategoryService;
import com.project_one.service.CategoryServiceImpl;
import com.viewpagerindicator.TitlePageIndicator;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by JenuNagil on 8/22/2015.
 */
public class ManageProductFragment extends Fragment {

    private Spinner spinner;

    public ManageProductFragment() {}

    public static ManageProductFragment newInstance(int position, String viewTitle) {
        ManageProductFragment manageProductFragment = new ManageProductFragment();

        Bundle bundle = new Bundle();
        bundle.putInt("viewPosition", position);
        bundle.putString("viewTitle", viewTitle);
        manageProductFragment.setArguments(bundle);

        return manageProductFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_manage_product, container, false);

        int position = getArguments().getInt("viewPosition");
        String title = getArguments().getString("viewTitle");

        Toast.makeText(getActivity(), "title: " + title, Toast.LENGTH_SHORT).show();
        displayCategories(rootView);
        return rootView;
    }



    private void displayCategories(View view) {
        List<String> typeOfCategories = fetchTypeOfCategory();
        spinner = (Spinner)view.findViewById(R.id.list_of_categories);
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, typeOfCategories);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);
    }

    private List<String> fetchTypeOfCategory() {
        CategoryService categoryServiceImpl = new CategoryServiceImpl(getActivity());
        List<String> categories = new ArrayList<String>();
        for(Category category: categoryServiceImpl.fetchAllCategories()) {
            categories.add(category.getName());
        }
        return categories;
    }
}
