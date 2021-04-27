package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.androidadvance.topsnackbar.TSnackbar;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    SharedPreferences mySettings;

    private void saveArrayList(String name, ArrayList<String> list) {
        SharedPreferences prefs = getSharedPreferences("Records", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        StringBuilder sb = new StringBuilder();
        for (String s : list) sb.append(s).append("<s>");
        sb.delete(sb.length() - 3, sb.length());
        editor.putString(name, sb.toString()).apply();
    }

    private ArrayList<String> loadArrayList(String name) {
        SharedPreferences prefs = getSharedPreferences("Records", MODE_PRIVATE);
        String[] strings = prefs.getString(name, "").split("<s>");
        ArrayList<String> list = new ArrayList<>();
        list.addAll(Arrays.asList(strings));
        return list;
    }

    public class Settings {
        public static final String APP_PREFERENCES = "mysettings";
        public static final String APP_PREFERENCES_globalscore = "globalscore";
        public static final String APP_PREFERENCES_cityhappy = "cityhappy";
        public static final String APP_PREFERENCES_citycondition = "citycondition";
        public static final String APP_PREFERENCES_money = "money";
        public static final String APP_PREFERENCES_salary = "salary";
        public static final String APP_PREFERENCES_suspension = "suspension";
        public static final String APP_PREFERENCES_cityprogress = "cityprogress";
        public static final String APP_PREFERENCES_taxessalary = "taxessalary";
        public static final String APP_PREFERENCES_centerpays = "centerpays";
        public static final String APP_PREFERENCES_constantpays = "constantpays";
        public static final String APP_PREFERENCES_citylevel = "citylevel";
        public static final String APP_PREFERENCES_scores = "scores";
        public static final String APP_PREFERENCES_Interval = "Interval";
        public static final String APP_PREFERENCES_hungry = "hungry";
        public static final String APP_PREFERENCES_angry = "angry";

    }

    Settings settings = new Settings();

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
        public static int citylevel = 1;
        public static int scores = 0;
        public static int Interval = 1000;
        public static boolean hungry = false;
        public static boolean angry = false;
        public static int globalscore = 0;
        public static int tempscore = 0;
    }

    public void SaveValues() {
        SharedPreferences.Editor editor = this.mySettings.edit();
        editor.putString(settings.APP_PREFERENCES_cityhappy, String.valueOf(Values.cityhappy));
        editor.putString(settings.APP_PREFERENCES_globalscore, String.valueOf(Values.globalscore));
        editor.putString(settings.APP_PREFERENCES_citycondition, String.valueOf(Values.citycondition));
        editor.putString(settings.APP_PREFERENCES_money, String.valueOf(Values.money));
        editor.putString(settings.APP_PREFERENCES_salary, String.valueOf(Values.salary));
        editor.putString(settings.APP_PREFERENCES_suspension, String.valueOf(Values.suspension));
        editor.putString(settings.APP_PREFERENCES_cityprogress, String.valueOf(Values.cityprogress));
        editor.putString(settings.APP_PREFERENCES_taxessalary, String.valueOf(Values.taxessalary));
        editor.putString(settings.APP_PREFERENCES_centerpays, String.valueOf(Values.centerpays));
        editor.putString(settings.APP_PREFERENCES_constantpays, String.valueOf(Values.constantpays));
        editor.putString(settings.APP_PREFERENCES_citylevel, String.valueOf(Values.citylevel));
        editor.putString(settings.APP_PREFERENCES_scores, String.valueOf(Values.scores));
        editor.putString(settings.APP_PREFERENCES_Interval, String.valueOf(Values.Interval));
        editor.putString(settings.APP_PREFERENCES_angry, String.valueOf(Values.angry));
        editor.putString(settings.APP_PREFERENCES_hungry, String.valueOf(Values.hungry));
        editor.apply();
    }

    public void LoadValues() {
        Values.cityhappy = Float.parseFloat(mySettings.getString(settings.APP_PREFERENCES_cityhappy, "50"));
        Values.globalscore = Integer.parseInt(mySettings.getString(settings.APP_PREFERENCES_globalscore, "0"));
        Values.citycondition = Float.parseFloat(mySettings.getString(settings.APP_PREFERENCES_citycondition, "50"));
        Values.money = Integer.parseInt(mySettings.getString(settings.APP_PREFERENCES_money, "10000"));
        Values.salary = Integer.parseInt(mySettings.getString(settings.APP_PREFERENCES_salary, "5"));
        Values.suspension = Integer.parseInt(mySettings.getString(settings.APP_PREFERENCES_suspension, "0"));
        Values.cityprogress = Integer.parseInt(mySettings.getString(settings.APP_PREFERENCES_cityprogress, "0"));
        Values.taxessalary = Integer.parseInt(mySettings.getString(settings.APP_PREFERENCES_taxessalary, "1"));
        Values.centerpays = Integer.parseInt(mySettings.getString(settings.APP_PREFERENCES_centerpays, "1"));
        Values.constantpays = Integer.parseInt(mySettings.getString(settings.APP_PREFERENCES_constantpays, "2"));
        Values.citylevel = Integer.parseInt(mySettings.getString(settings.APP_PREFERENCES_citylevel, "1"));
        Values.scores = Integer.parseInt(mySettings.getString(settings.APP_PREFERENCES_scores, "0"));
        Values.Interval = Integer.parseInt(mySettings.getString(settings.APP_PREFERENCES_Interval, "1000"));
        Values.angry = Boolean.parseBoolean(mySettings.getString(settings.APP_PREFERENCES_angry, "false"));
        Values.hungry = Boolean.parseBoolean(mySettings.getString(settings.APP_PREFERENCES_hungry, "false"));
    }

    public void suspTime() {

        new CountDownTimer(10000, 1000) {

            @Override
            public void onTick(long l) {
                Values.suspension -= 1;
            }

            @Override
            public void onFinish() {
                suspTime();
            }
        }.start();
    }

    public void Timer(View navView) {

        new CountDownTimer(10000, Values.Interval) {

            @Override
            public void onTick(long l) {
                EventHandler(navView);
                Values.money += (Values.salary + Values.taxessalary - Values.constantpays);
                Values.scores += 1 * Values.citylevel;
                Values.globalscore += 1 * Values.citylevel;
                Values.cityhappy -= 0.5;
                Values.citycondition -= 0.5;
            }

            @Override
            public void onFinish() {
                Values.Interval -= 50;
                Values.money -= (Values.centerpays + Values.constantpays);
                Values.scores += 2 * Values.citylevel;
                Values.globalscore += 2 * Values.citylevel;
                Timer(navView);
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
        Timer(navView);
        suspTime();
        SharedPreferences sharedPreferences = getSharedPreferences(settings.APP_PREFERENCES, 0);
        mySettings = sharedPreferences;
        LoadValues();

    }

    protected void onPause() {
        super.onPause();
        SaveValues();
    }

    protected void onDestroy() {

        SaveValues();
        super.onDestroy();
    }

    protected void onResume() {
        super.onResume();
        LoadValues();
    }


    public void dead(View navView) {
        Values.tempscore = Values.scores;
        Values.cityhappy = 50;
        Values.citycondition = 50;
        Values.money = 10000;
        Values.salary = 5;
        Values.suspension = 0;
        Values.cityprogress = 0;
        Values.taxessalary = 1;
        Values.centerpays = 1;
        Values.constantpays = 2;
        Values.citylevel = 1;
        Values.scores = 0;
        Values.Interval = 1000;
        Values.angry = false;
        Values.hungry = false;
        SaveValues();
        Intent intent = new Intent(MainActivity.this, DeadActivity.class);
        startActivity(intent);

    }

    public void EventHandler(View navView) {
        if (Values.suspension <= 0) {
            Values.suspension = 0;
        }
        if (Values.citycondition >= 100) {
            Values.citycondition = 100;
        }
        if (Values.cityhappy >= 100) {
            Values.cityhappy = 100;
        }
        if (Values.cityprogress >= 100) {
            Values.cityprogress = 100;
        }
        if (Values.cityhappy <= 25) {
            Values.angry = true;
            Values.suspension += 2;
        }
        if (Values.cityhappy > 25) {
            Values.angry = false;
        }
        if (Values.citycondition <= 25) {
            Values.hungry = true;
            Values.suspension += 2;
        }
        if (Values.citycondition > 25) {
            Values.hungry = false;
        }
        if (Values.citycondition <= 0) {
            dead(navView);
        }
        if (Values.cityhappy <= 0) {
            dead(navView);
        }
        if (Values.suspension >= 100) {
            dead(navView);
        }
        if (Values.money <= 0) {
            dead(navView);
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
            case R.id.back_button:
                this.onBackPressed();
                break;
            case R.id.upgr_btn:

                if (objctsClick.city_progress.getProgress() >= 99) {
                    TSnackbar.make(navView, "Ваш город улучшен", TSnackbar.LENGTH_SHORT).show();
                    Values.suspension = 0;
                    Values.citylevel += 1;
                    Values.cityprogress = 0;
                    Values.taxessalary += 1;
                    Values.centerpays += 3;
                    Values.suspension = 0;
                    Values.Interval += 100;
                } else {
                    TSnackbar.make(navView, "Недостаточно прогресса", TSnackbar.LENGTH_LONG).show();
                }
                break;
            case R.id.care_houses:
                Values.cityhappy += 15;
                Values.citycondition += 10;
                Values.money -= 1500;
                Values.suspension -= 1;
                Values.cityprogress += 3;
                EventHandler(navView);
                break;
            case R.id.care_roads:
                Values.cityprogress += 3;
                Values.cityhappy += 5;
                Values.citycondition += 5;
                Values.suspension -= 1;
                Values.money -= 500;
                EventHandler(navView);
                break;
            case R.id.care_street:
                Values.cityprogress += 3;
                Values.cityhappy += 5;
                Values.suspension -= 1;
                Values.citycondition += 2;
                Values.money -= 150;
                EventHandler(navView);
                break;
            case R.id.care_water:
                Values.cityprogress += 3;
                Values.cityhappy += 5;
                Values.citycondition += 3;
                Values.suspension -= 1;
                Values.money -= 200;
                EventHandler(navView);
                break;
            case R.id.contract_houses:
                Values.cityprogress += 3;
                Values.cityhappy += 25;
                Values.citycondition += 15;
                Values.money -= 25000;
                Values.salary += 18;
                Values.suspension -= 1;
                Values.constantpays += 2;
                EventHandler(navView);
                break;
            case R.id.contract_lunapark:
                Values.cityprogress += 3;
                Values.cityhappy += 20;
                Values.citycondition += 5;
                Values.money -= 20000;
                Values.salary += 15;
                Values.suspension -= 1;
                Values.constantpays += 2;
                EventHandler(navView);
                break;
            case R.id.contract_shopcenter:
                Values.cityprogress += 3;
                Values.cityhappy += 15;
                Values.citycondition += 5;
                Values.money -= 10000;
                Values.suspension -= 1;
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
                Values.suspension -= 1;
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
                Values.suspension -= 1;
                EventHandler(navView);
                break;
            case R.id.create_roads:
                Values.cityprogress += 3;
                Values.cityhappy += 7;
                Values.citycondition += 10;
                Values.money -= 1200;
                Values.suspension -= 1;
                EventHandler(navView);
                break;
            case R.id.create_vokzal:
                Values.cityprogress += 3;
                Values.cityhappy += 10;
                Values.citycondition += 20;
                Values.money -= 5000;
                Values.salary += 5;
                Values.suspension -= 1;
                EventHandler(navView);
                break;
            case R.id.create_zdroads:
                Values.cityprogress += 3;
                Values.cityhappy += 5;
                Values.citycondition += 2;
                Values.money -= 2500;
                Values.salary += 2;
                Values.suspension -= 1;
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

    public void onSettingsMenuClick(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.toolbar_exit:
                Intent intent = new Intent(MainActivity.this, MenuActivity.class);
                startActivity(intent);
                break;
            case R.id.toolbar_load:
                LoadValues();
                TSnackbar.make(findViewById(android.R.id.content), "Настройки загружены", TSnackbar.LENGTH_SHORT).show();
                break;
            case R.id.toolbar_save:
                SaveValues();
                TSnackbar.make(findViewById(android.R.id.content), "Настройки сохранены", TSnackbar.LENGTH_SHORT).show();
                break;
            default:
                break;
        }


    }
}