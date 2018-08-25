package com.mr.app.android.storesample.domain.model.local;

import com.mr.app.android.storesample.data.Company;
import com.mr.app.android.storesample.data.Product;
import com.mr.app.android.storesample.domain.model.local.entity.RealmCompany;
import com.mr.app.android.storesample.domain.model.local.entity.RealmProduct;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Marioara Rus on 8/25/2018.
 */
public class EntityParser {
    public static Product parseRealmProductToProduct(RealmProduct realmProduct) {
        return new Product(
                realmProduct.getId(),
                realmProduct.getUrl(),
                realmProduct.getSellerId());
    }

    public static Company parseRealmCompanyToCompany(RealmCompany realmCompany) {
        return new Company(
                realmCompany.getId(),
                realmCompany.getName());
    }

    public static List<Product> parseRealmProductsToProducts(List<RealmProduct> realmProducts) {
        List<Product> products = new ArrayList<>();
        for (RealmProduct product : realmProducts) {
            products.add(parseRealmProductToProduct(product));
        }
        return products;
    }
    public static List<Company> parseRealmCompaniesToCompanies(List<RealmCompany> realmCompanies) {
        List<Company> companies = new ArrayList<>();
        for (RealmCompany company : realmCompanies) {
            companies.add(parseRealmCompanyToCompany(company));
        }
        return companies;
    }

    public static RealmProduct parseProductToRealmProduct(Product product) {
        return new RealmProduct(
                product.getId(),
                product.getUrl(),
                product.getSellerId());
    }

    private static RealmCompany parseCompanyToRealmCompany(Company seller) {
        return new RealmCompany(
                seller.getId(),
                seller.getName());
    }

    public static List<RealmProduct> parseProductsToRealmProducts(List<Product> products) {
        List<RealmProduct> realmProducts = new ArrayList<>();
        for (Product product : products) {
            realmProducts.add(parseProductToRealmProduct(product));
        }
        return realmProducts;
    }

    public static List<RealmCompany> parseCompaniesToRealmCompanies(List<Company> companies) {
        List<RealmCompany> realmCompanies = new ArrayList<>();
        for (Company company : companies) {
            realmCompanies.add(parseCompanyToRealmCompany(company));
        }
        return realmCompanies;
    }

}
