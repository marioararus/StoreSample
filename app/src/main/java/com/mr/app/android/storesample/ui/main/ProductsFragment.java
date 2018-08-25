package com.mr.app.android.storesample.ui.main;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mr.app.android.storesample.R;
import com.mr.app.android.storesample.data.Company;
import com.mr.app.android.storesample.data.Product;

import java.util.List;

/**
 * Created by Marioara Rus on 8/25/2018.
 */
public class ProductsFragment extends Fragment {

    private static final String ARG_PRODUCTS = "key_products";
    private static final String ARG_COMPANY = "key_company";
    private Company company;
    private List<Product> products;
    private RecyclerView rvProducts;
    private ProductsAdapter productsAdapter;

    @SuppressLint("ValidFragment")
    private ProductsFragment() {
    }

    public static ProductsFragment newInstance(List<Product> productList, Company company) {
        String productsJson = new Gson().toJson(productList);
        String companyJson = new Gson().toJson(company);
        ProductsFragment fragment = new ProductsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_COMPANY, companyJson);
        args.putString(ARG_PRODUCTS, productsJson);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        productsAdapter = new ProductsAdapter();
        String companyJson = getArguments().getString(ARG_COMPANY);
        String productsJson = getArguments().getString(ARG_PRODUCTS);
        products = new Gson().fromJson(productsJson, new TypeToken<List<Product>>() {
        }.getType());
        company = new Gson().fromJson(companyJson, Company.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_products, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rvProducts = view.findViewById(R.id.rv_products);
        rvProducts.setLayoutManager(new LinearLayoutManager(getContext()));
        rvProducts.setAdapter(productsAdapter);
        productsAdapter.setProducts(products);
    }
}
