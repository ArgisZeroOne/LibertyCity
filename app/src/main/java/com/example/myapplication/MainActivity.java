  package com.example.myapplication;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.androidadvance.topsnackbar.TSnackbar;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.ContentView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        BottomNavigationView navView = findViewById(R.id.toolbar);
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(R.id.navigation_care, R.id.navigation_create, R.id.navigation_gov, R.id.navigation_contract, R.id.navigation_finance).build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);
    }


    public void Click(View navView){
        switch (navView.getId()) {
            case R.id.care_houses:
                Snackbar.make(navView, "Поиграл в телефон", Snackbar.LENGTH_LONG).show();
                break;
            case R.id.care_roads:
                Snackbar.make(navView, "Поиграл в турнир", Snackbar.LENGTH_LONG).show();
                break;
            case R.id.care_street:
                Snackbar.make(navView, "Поиграл в компьютер", Snackbar.LENGTH_LONG).show();
                break;
            case R.id.care_water:
                Snackbar.make(navView, "Поиграл в настолку", Snackbar.LENGTH_LONG).show();
                break;
            case R.id.toolbar:
                Snackbar.make(navView, "Назад", Snackbar.LENGTH_LONG).show();
                break;
            case R.id.mainfab:
                Snackbar.make(navView, "Че, решил взяться за FAB?1", Snackbar.LENGTH_LONG).show();
                break;
            default:
                TSnackbar.make(findViewById(R.id.nestedScrollView),"Hello from TSnackBar.",TSnackbar.LENGTH_LONG).show();
                break;


        }  
    }
}