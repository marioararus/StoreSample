package com.mr.app.android.storesample.domain.model.local;

import com.mr.app.android.storesample.data.Company;
import com.mr.app.android.storesample.domain.model.local.entity.RealmCompany;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmResults;

/**
 * Created by Marioara Rus on 8/21/2018.
 */
public class LocalCompanyModel {
    Realm realm = Realm.getDefaultInstance();

    public List<Company> getAllCompanies() {
        RealmResults<RealmCompany> companyRealmResults = realm.where(RealmCompany.class).findAll();
        List<RealmCompany> realmCompanies = realm.copyFromRealm(companyRealmResults);
        return EntityParser.parseRealmCompaniesToCompanies(realmCompanies);
    }

    public void insertCompanies(List<Company> companies){
        realm.beginTransaction();
        RealmList<RealmCompany> realmProductRealmList = new RealmList<>();
        realmProductRealmList.addAll(EntityParser.parseCompaniesToRealmCompanies(companies));
        realm.insertOrUpdate(realmProductRealmList);
        realm.commitTransaction();
    }
}
