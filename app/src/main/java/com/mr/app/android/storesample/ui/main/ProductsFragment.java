package com.mr.app.android.storesample.ui.main;

import android.annotation.SuppressLint;
import android.arch.lifecycle.Observer;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mr.app.android.storesample.R;
import com.mr.app.android.storesample.data.Company;
import com.mr.app.android.storesample.data.Product;

import java.util.List;
import java.util.Map;

/**
 * Created by Marioara Rus on 8/25/2018.
 */
public class ProductsFragment extends Fragment {

    private static final String ARG_PRODUCTS = "key_products";
    private static final String ARG_COMPANY = "key_company";
    private Long companyId;
    private List<Long> productsIds;
    private RecyclerView rvProducts;
    private ProductsAdapter productsAdapter;
    private HomeViewModel homeViewModel;

    @SuppressLint("ValidFragment")
    private ProductsFragment() {
    }

    public static ProductsFragment newInstance(List<Long> productIdList, Long companyId) {
        String productsJson = new Gson().toJson(productIdList);
        String companyJson = new Gson().toJson(companyId);
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
        productsIds = new Gson().fromJson(productsJson, new TypeToken<List<Long>>() {
        }.getType());
        companyId = new Gson().fromJson(companyJson, Long.class);
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
        homeViewModel = new HomeViewModel();
        homeViewModel.getCompaniesAndProducts(companyId, productsIds).observe(this, new Observer<Pair<Company, List<Product>>>() {
                    @Override
                    public void onChanged(@Nullable Pair<Company, List<Product>> companyListPair) {
                        if(companyListPair!=null){
                            productsAdapter.setProducts(companyListPair.second);
                        }
                    }
                });
                rvProducts.setLayoutManager(new LinearLayoutManager(getContext()));
        rvProducts.setAdapter(productsAdapter);
    }
}
