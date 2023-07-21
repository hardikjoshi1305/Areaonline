package com.areaonline.user.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.RelativeLayout;

import com.areaonline.R;
import com.areaonline.user.btm_fragment.Shop_Fragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {
    RelativeLayout fragment_container;
    BottomNavigationView bottom_navigation;
    public static DrawerLayout drawer;
    NavigationView navigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializedwidget();
    }

    private void initializedwidget() {
        fragment_container = findViewById(R.id.fragment_container);
        bottom_navigation = findViewById(R.id.bottom_navigation);
        drawer = findViewById(R.id.drawer);
        bottom_navigation.setSelectedItemId(R.id.bottom_shop);

        navigation = findViewById(R.id.navigation);
        initcomponent();
        Fragment fragment;
        fragment = new Shop_Fragment();
        loadFragment(fragment);

        navigation.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.nav_logout :
//                        logout();
                        break;
                }
                return true;
            }
        });

    }

    private void initcomponent() {
        bottom_navigation.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment;
                switch (item.getItemId()) {
                    case R.id.bottom_shop:
                        fragment = new Shop_Fragment();
                        loadFragment(fragment);
                        return true;
                    case R.id.bottom_hotel:
//                            fragment = new Cart_Fragment();
//                            loadFragment(fragment);
                        return true;
                    case R.id.bottom_restaurants:
//                            fragment = new Food_Fragment();
//                            loadFragment(fragment);
                        return true;
                    case R.id.bottom_bar:
                        return true;
                    case R.id.bottom_more:
                        return true;
                }
                return false;
            }
        });
    }
    public  boolean loadFragment(Fragment fragment) {
        if (fragment != null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, fragment).addToBackStack("tag")
                    .commit();
            return false;
        }
        return false;
    }
}