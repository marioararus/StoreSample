package com.mr.app.android.storesample.domain.model.local;

import com.mr.app.android.storesample.data.Company;
import com.mr.app.android.storesample.data.Product;
import com.mr.app.android.storesample.domain.model.local.entity.RealmProduct;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmQuery;
import io.realm.RealmResults;

/**
 * Created by Marioara Rus on 8/21/2018.
 */
public class LocalProductModel {
    Realm realm = Realm.getDefaultInstance();

    public void insertProducts(List<Product> products){
        realm.beginTransaction();
        RealmList<RealmProduct> realmProductRealmList = new RealmList<>();
        realmProductRealmList.addAll(EntityParser.parseProductsToRealmProducts(products));
        realm.insertOrUpdate(realmProductRealmList);
        realm.commitTransaction();
    }

    public List<Product> getProductsBySeller(Company seller){
        RealmResults<RealmProduct> productRealmResults = realm.where(RealmProduct.class)
                .equalTo("sellerId", seller.getId()).findAll();
        List<RealmProduct> realmProducts = realm.copyFromRealm(productRealmResults);
        return EntityParser.parseRealmProductsToProducts(realmProducts);
    }

    public List<Product> getProductsByIds(List<Long> productIds) {
        RealmQuery<RealmProduct> query = realm.where(RealmProduct.class);
        for (Long id : productIds) {
            query.or().equalTo("id", id);
        }
        RealmResults<RealmProduct> results = query.findAll();
        List<RealmProduct> realmProducts = realm.copyFromRealm(results);

        return EntityParser.parseRealmProductsToProducts(realmProducts);
    }
}
