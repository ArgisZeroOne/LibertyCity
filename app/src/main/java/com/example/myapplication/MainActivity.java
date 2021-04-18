package com.example.myapplication;

import android.app.Fragment;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.androidadvance.topsnackbar.TSnackbar;
import com.example.myapplication.ui.goverment.GovFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.ContentView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

public class MainActivity extends AppCompatActivity {
    SharedPreferences mySettings;

    public class Settings {
        public static final String APP_PREFERENCES = "mysettings";
        public static final String APP_PREFERENCES_cityhappy = "cityhappy";
        public static final String APP_PREFERENCES_citycondition = "citycondition";
        public static final String APP_PREFERENCES_money = "money";
        public static final String APP_PREFERENCES_salary = "salary";
        public static final String APP_PREFERENCES_suspension = "suspension";
        public static final String APP_PREFERENCES_cityprogress = "cityprogress";
        public static final String APP_PREFERENCES_taxessalary = "taxessalary";
        public static final String APP_PREFERENCES_centerpays = "centerpays";
        public static final String APP_PREFERENCES_constantpays = "constantpays";
    }

    public static class Values {
        public static boolean fabactive = false;
        public static float cityhappy = 50;
        public static float citycondition = 50;
        public static int money = 10000;
        public static int salary = 5;
        public static int suspension = 0;
        public static int cityprogress = 0;
        public static int taxessalary = 1;
        public static int centerpays = 1;
        public static int constantpays = 2;
    }

    public void Timer() {

        new CountDownTimer(10000, 1000) {

            @Override
            public void onTick(long l) {
                Values.money += (Values.salary + Values.taxessalary - Values.constantpays);
            }

            @Override
            public void onFinish() {
                Timer();
                Values.money -= (Values.centerpays + Values.constantpays);
            }
        }.start();
    }

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
        Timer();
    }

    public void EventHandler(View navView) {

        if (Values.citycondition >= 100) {
            Values.citycondition = 100;
        }
        if (Values.cityhappy >= 100) {
            Values.cityhappy = 100;
        }
        if (Values.cityprogress >= 100) {
            TSnackbar.make(navView, R.string.full_progress, TSnackbar.LENGTH_LONG).show();
            Values.cityprogress = 100;
        }
        if (Values.suspension >= 100) {
            Values.suspension = 100;
        }
        if (Values.suspension >= 75) {
            Values.suspension = 75;
            TSnackbar.make(navView, R.string.high_susp, TSnackbar.LENGTH_LONG).show();
        }
        if (Values.cityhappy <= 25) {
            TSnackbar.make(navView, R.string.low_happy, TSnackbar.LENGTH_LONG).show();
        }
        if (Values.citycondition <= 25) {
            TSnackbar.make(navView, R.string.low_cond, TSnackbar.LENGTH_LONG).show();
        }

        if (Values.citycondition <= 0) {
            TSnackbar.make(navView, R.string.dead, TSnackbar.LENGTH_LONG).show();
            Values.citycondition = 50;
            Values.cityprogress = 0;
            Values.cityhappy = 50;
            Values.suspension = 0;
        }
        if (Values.cityhappy <= 0) {
            TSnackbar.make(navView, R.string.dead, TSnackbar.LENGTH_LONG).show();
            Values.cityhappy = 50;
            Values.suspension = 0;
            Values.citycondition = 50;
            Values.cityprogress = 0;
        }
        if (Values.suspension >= 100) {
            TSnackbar.make(navView, R.string.dead, TSnackbar.LENGTH_LONG).show();
            Values.suspension = 0;
            Values.cityhappy = 50;
            Values.citycondition = 50;
            Values.cityprogress = 0;
        }
    }

    public void Click(View navView) {
        class ClickObj {
            FloatingActionButton frst_secondfab = (FloatingActionButton) findViewById(R.id.minifab1);
            FloatingActionButton sec_secondfab = (FloatingActionButton) findViewById(R.id.minifab2);
            Button upgr_btn = (Button) findViewById(R.id.upgr_btn);
            ProgressBar city_progress = (ProgressBar) findViewById(R.id.cityprgs_prgsbar);
        }

        ClickObj objctsClick = new ClickObj();
        switch (navView.getId()) {
            case R.id.upgr_btn:

                if(objctsClick.city_progress.getProgress() >= 99){
                    TSnackbar.make(navView,"Ваш город улучшен",TSnackbar.LENGTH_SHORT).show();
                    Values.suspension = 0;
                    Values.cityprogress = 0;
                    Values.taxessalary += 5;
                    Values.centerpays += 3;
                } else {
                    TSnackbar.make(navView,"Недостаточно прогресса", TSnackbar.LENGTH_LONG).show();
                }
                break;
            case R.id.care_houses:
                Timer();
                Values.cityhappy += 15;
                Values.citycondition += 10;
                Values.money -= 1500;
                Values.cityprogress += 3;
                EventHandler(navView);
                break;
            case R.id.care_roads:
                Values.cityprogress += 3;
                Values.cityhappy += 5;
                Values.citycondition += 5;
                Values.money -= 500;
                EventHandler(navView);
                break;
            case R.id.care_street:
                Values.cityprogress += 3;
                Values.cityhappy += 5;
                Values.citycondition += 2;
                Values.money -= 150;
                EventHandler(navView);
                break;
            case R.id.care_water:
                Values.cityprogress += 3;
                Values.cityhappy += 5;
                Values.citycondition += 3;
                Values.money -= 200;
                EventHandler(navView);
                break;
            case R.id.contract_houses:
                Values.cityprogress += 3;
                Values.cityhappy += 25;
                Values.citycondition += 15;
                Values.money -= 25000;
                Values.salary += 18;
                Values.constantpays += 2;
                EventHandler(navView);
                break;
            case R.id.contract_lunapark:
                Values.cityprogress += 3;
                Values.cityhappy += 20;
                Values.citycondition += 5;
                Values.money -= 20000;
                Values.salary += 15;
                Values.constantpays += 2;
                EventHandler(navView);
                break;
            case R.id.contract_shopcenter:
                Values.cityprogress += 3;
                Values.cityhappy += 15;
                Values.citycondition += 5;
                Values.money -= 10000;
                Values.salary += 8;
                Values.constantpays += 1;
                EventHandler(navView);
                break;
            case R.id.contract_zk:
                Values.cityprogress += 3;
                Values.cityhappy += 17;
                Values.citycondition += 10;
                Values.money -= 18000;
                Values.salary += 12;
                Values.constantpays += 1;
                EventHandler(navView);
                break;
            case R.id.finance_nalog:
                Values.cityprogress -= 5;
                Values.cityhappy -= 5;
                Values.money += 500;
                break;
            case R.id.finance_optimazation:
                Values.cityprogress -= 5;
                Values.cityhappy -= 10;
                Values.money += 1500;
                Values.suspension += 15;
                EventHandler(navView);
                break;
            case R.id.finance_pin:
                Values.cityprogress -= 5;
                Values.cityhappy -= 20;
                Values.money += 5000;
                Values.suspension += 40;
                EventHandler(navView);
                break;
            case R.id.finance_pile:
                Values.cityprogress -= 5;
                Values.cityhappy -= 35;
                Values.money += 1000;
                Values.suspension += 70;
                EventHandler(navView);
                break;
            case R.id.create_park:
                Values.cityprogress += 3;
                Values.cityhappy += 15;
                Values.citycondition += 3;
                Values.money -= 700;
                EventHandler(navView);
                break;
            case R.id.create_roads:
                Values.cityprogress += 3;
                Values.cityhappy += 7;
                Values.citycondition += 10;
                Values.money -= 1200;
                EventHandler(navView);
                break;
            case R.id.create_vokzal:
                Values.cityprogress += 3;
                Values.cityhappy += 10;
                Values.citycondition += 20;
                Values.money -= 5000;
                Values.salary += 5;
                EventHandler(navView);
                break;
            case R.id.create_zdroads:
                Values.cityprogress += 3;
                Values.cityhappy += 5;
                Values.citycondition += 2;
                Values.money -= 2500;
                Values.salary += 2;
                EventHandler(navView);
                break;
            case R.id.mainfab:
                if (Values.fabactive == true) {
                    objctsClick.frst_secondfab.setVisibility(View.GONE);
                    objctsClick.sec_secondfab.setVisibility(View.GONE);
                    Values.fabactive = false;
                } else if (Values.fabactive == false) {
                    objctsClick.frst_secondfab.setVisibility(View.VISIBLE);
                    objctsClick.sec_secondfab.setVisibility(View.VISIBLE);
                    Values.fabactive = true;
                }
                break;
            default:
                break;
        }
    }
}