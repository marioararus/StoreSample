package com.mr.app.android.storesample.ui.main;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.util.Pair;

import com.facebook.login.LoginManager;
import com.mr.app.android.storesample.data.Company;
import com.mr.app.android.storesample.data.Product;
import com.mr.app.android.storesample.domain.model.local.LocalCompanyModel;
import com.mr.app.android.storesample.domain.model.local.LocalProductModel;
import com.mr.app.android.storesample.domain.model.local.LocalUserModel;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Marioara Rus on 8/25/2018.
 */
public class HomeViewModel extends ViewModel {
    private LocalProductModel localProductModel;
    private LocalCompanyModel localCompanyModel;
    private LocalUserModel localUserModel;

    private MutableLiveData<Map<Company, List<Product>>> mapLiveData = new MutableLiveData<>();
    private MutableLiveData<Pair<Company, List<Product>>> pairMutableLiveData = new MutableLiveData<>();

    public HomeViewModel() {
        this.localProductModel = new LocalProductModel();
        this.localCompanyModel = new LocalCompanyModel();
        this.localUserModel = new LocalUserModel();
    }

    public LiveData<Map<Company, List<Product>>> getCompaniesAndProducts() {
        Map<Company,List<Product>> productsCompany = new HashMap<>();
        List<Company> companies = localCompanyModel.getAllCompanies();
        for (Company company : companies) {
            productsCompany.put(company,localProductModel.getProductsBySeller(company));
        }
        mapLiveData.setValue(productsCompany);

        return mapLiveData;
    }

    public LiveData<Pair<Company, List<Product>>> getCompaniesAndProducts(Long id, List<Long> idsList){
        Pair<Company,List<Product>> productsCompany;

        Company company = localCompanyModel.getCompany(id);
        productsCompany = new Pair<>(company,localProductModel.getProductsByIds(idsList));
        pairMutableLiveData.setValue(productsCompany);

        return pairMutableLiveData;
    }

    public void logOut() {
        LoginManager.getInstance().logOut();
        localUserModel.logOutUser();
    }

}
