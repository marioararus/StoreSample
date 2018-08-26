package com.mr.app.android.storesample.ui.splash;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.mr.app.android.storesample.R;
import com.mr.app.android.storesample.ui.auth.LoginActivity;
import com.mr.app.android.storesample.ui.main.HomePageActivity;

import java.util.Arrays;

/**
 * Created by Marioara Rus on 8/26/2018.
 */
public class SplashScreen extends AppCompatActivity {

    private CallbackManager callbackManager;
    private SplashViewModel splashViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        splashViewModel = ViewModelProviders.of(this).get(SplashViewModel.class);
        callbackManager = CallbackManager.Factory.create();
        LoginManager.getInstance().registerCallback(callbackManager,
                new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        startActivity(new Intent(SplashScreen.this, HomePageActivity.class));
                        finish();

                    }

                    @Override
                    public void onCancel() {
                        Toast.makeText(SplashScreen.this, "Login Manager OnCancel", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onError(FacebookException exception) {
                        Log.e("Marioara", exception.getMessage());
                        if (splashViewModel.hasLoggedInUser()){
                            startActivity(new Intent(SplashScreen.this, HomePageActivity.class));
                        } else {
                            startActivity(new Intent(SplashScreen.this, LoginActivity.class));
                        }
                    }
                });

        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        if (accessToken == null) {
            startActivity(new Intent(SplashScreen.this, LoginActivity.class));
            finish();
        } else if (accessToken.isExpired()) {
            LoginManager.getInstance().logInWithReadPermissions(this, Arrays.asList("public_profile"));
        } else {
            startActivity(new Intent(SplashScreen.this, HomePageActivity.class));
            finish();
        }
    }
}
