package com.mr.app.android.storesample.domain.model.local;

import com.mr.app.android.storesample.data.Company;
import com.mr.app.android.storesample.domain.model.local.entity.RealmCompany;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmQuery;
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

    public List<Company> getCompanies(List<Long> companyIds) {
        RealmQuery<RealmCompany> query = realm.where(RealmCompany.class);
        for (Long id : companyIds) {
            query.or().equalTo("id", id);
        }
        RealmResults<RealmCompany> results = query.findAll();
        List<RealmCompany> realmCompanies = realm.copyFromRealm(results);

        return EntityParser.parseRealmCompaniesToCompanies(realmCompanies);
    }

    public Company getCompany(Long companyId) {
        RealmQuery<RealmCompany> query = realm.where(RealmCompany.class).equalTo("id", companyId);
        RealmCompany results = query.findFirst();

        return EntityParser.parseRealmCompanyToCompany(results);
    }

    public void insertCompanies(List<Company> companies){
        realm.beginTransaction();
        RealmList<RealmCompany> realmProductRealmList = new RealmList<>();
        realmProductRealmList.addAll(EntityParser.parseCompaniesToRealmCompanies(companies));
        realm.insertOrUpdate(realmProductRealmList);
        realm.commitTransaction();
    }
}
