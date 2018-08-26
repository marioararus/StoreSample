package com.mr.app.android.storesample.ui.auth;

import android.arch.lifecycle.ViewModel;

import com.mr.app.android.storesample.data.User;
import com.mr.app.android.storesample.domain.model.local.LocalUserModel;

/**
 * Created by Marioara Rus on 8/26/2018.
 */
public class LoginViewModel extends ViewModel {
    LocalUserModel localUserModel;

    public LoginViewModel() {
        localUserModel = new LocalUserModel();
    }

    public void saveUser(User user){
        localUserModel.insertUser(user,true);
    }
}
