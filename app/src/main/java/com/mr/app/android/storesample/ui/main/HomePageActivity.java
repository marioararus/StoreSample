package com.mr.app.android.storesample.ui.main;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.mr.app.android.storesample.R;
import com.mr.app.android.storesample.data.Company;
import com.mr.app.android.storesample.data.Product;
import com.mr.app.android.storesample.ui.auth.LoginActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HomePageActivity extends AppCompatActivity {

    private SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;
    private ActionBar actionBar;
    private TabLayout tabLayout;
    private HomeViewModel homeViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);


        tabLayout = findViewById(R.id.sts_tab);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        actionBar = getSupportActionBar();
        mViewPager = (ViewPager) findViewById(R.id.container);
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mSectionsPagerAdapter);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mViewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                tabLayout.setScrollPosition(position,positionOffset,true);

            }

            @Override
            public void onPageSelected(int position) {
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel.class);
        homeViewModel.getCompaniesAndProducts()
                .observe(this, new Observer<Map<Company, List<Product>>>() {
                    @Override
                    public void onChanged(@Nullable Map<Company, List<Product>> companyListMap) {
                        if (companyListMap != null) {
                            mSectionsPagerAdapter.addProducts(companyListMap);
                            for (int i = 0; i < mSectionsPagerAdapter.getCount(); i++) {
                                tabLayout.addTab(tabLayout.newTab()
                                        .setText(mSectionsPagerAdapter.getCompanyName(i)));
                            }
                        }
                    }
                });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_log_out) {
            homeViewModel.logOut();
            startActivity(new Intent(HomePageActivity.this, LoginActivity.class));
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        public Map<Company, List<Product>> productMap = new HashMap<>();

        public void addProducts(Map<Company, List<Product>> productsAndCompanies) {
            productMap = productsAndCompanies;
            notifyDataSetChanged();
        }

        @Override
        public Fragment getItem(int position) {
            int i = 0;
            for (Company key : productMap.keySet()) {
                if (i == position) {

                    List<Long> productIds = new ArrayList<>();
                    for(Product product: productMap.get(key)){
                        productIds.add(product.getId());
                    }
                    return ProductsFragment.newInstance(productIds, key.getId());
                }
                i++;
            }
            return null;
        }

        public String getCompanyName(int position){
            int i = 0;
            for (Company key : productMap.keySet()) {
                if (i == position) {
                    return key.getName();
                }
                i++;
            }
            return null;
        }

        @Override
        public int getCount() {
            return productMap.keySet().size();
        }
    }
}
