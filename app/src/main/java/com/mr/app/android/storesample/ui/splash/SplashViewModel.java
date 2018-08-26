package com.mr.app.android.storesample.ui.splash;

import android.arch.lifecycle.ViewModel;

import com.mr.app.android.storesample.domain.model.local.LocalUserModel;

/**
 * Created by Marioara Rus on 8/26/2018.
 */
public class SplashViewModel extends ViewModel {
    LocalUserModel localUserModel;

    public SplashViewModel() {
        localUserModel = new LocalUserModel();
    }

    public boolean hasLoggedInUser() {
        return localUserModel.getLoggedInUser() != null;
    }
}
