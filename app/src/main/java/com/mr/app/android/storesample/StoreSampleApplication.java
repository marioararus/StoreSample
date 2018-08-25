package com.mr.app.android.storesample;

import android.app.Application;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mr.app.android.storesample.data.Company;
import com.mr.app.android.storesample.data.Product;
import com.mr.app.android.storesample.domain.model.local.LocalCompanyModel;
import com.mr.app.android.storesample.domain.model.local.LocalProductModel;

import java.util.List;

import io.realm.Realm;

/**
 * Created by Marioara Rus on 8/25/2018.
 */
public class StoreSampleApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);
        List<Product> products = new Gson().fromJson(FileUtil.readJsonFromRaw(this,
                R.raw.products), new TypeToken<List<Product>>() {
        }.getType());
        List<Company> companies = new Gson().fromJson(FileUtil.readJsonFromRaw(this,
                R.raw.companies), new TypeToken<List<Company>>() {
        }.getType());

        LocalProductModel localProductModel = new LocalProductModel();
        localProductModel.insertProducts(products);

        LocalCompanyModel localCompanyModel = new LocalCompanyModel();
        localCompanyModel.insertCompanies(companies);

    }

}
