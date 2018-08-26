package com.mr.app.android.storesample.domain.model.local;

import com.mr.app.android.storesample.data.User;
import com.mr.app.android.storesample.domain.model.local.entity.RealmUser;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by Marioara Rus on 8/21/2018.
 */
public class LocalUserModel {
    Realm realm = Realm.getDefaultInstance();

    public User getLoggedInUser() {
        RealmResults<RealmUser> userRealmResults = realm.where(RealmUser.class).findAll();
        RealmUser realmUser = userRealmResults.first();
        if(realmUser!=null) {
            return EntityParser.parseRealmUserToUser(realmUser);
        }
        return null;
    }

    public void insertUser(User user, boolean isLoggedIn){
        realm.beginTransaction();
        realm.insertOrUpdate(EntityParser.parseUserToRealmUser(user, isLoggedIn));
        realm.commitTransaction();
    }

    public void logOutUser (){
        User user = getLoggedInUser();
        insertUser(user, false);
    }
}
