package com.example.orders.views.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.example.orders.R;
import com.example.orders.utils.SharedPreferencesUtils;

public abstract class MenuActivity extends AppCompatActivity {

    Menu menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_layout, menu);
        this.menu = menu;
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.logout_item:
                startActivity(new Intent(MenuActivity.this, LoginActivity.class));
                SharedPreferencesUtils.clearAll(this);
                break;
            case R.id.homepage_item:
                startActivity(new Intent(MenuActivity.this, HomePageMangerActivity.getHomePageClass(this)));
        }
        return super.onOptionsItemSelected(item);
    }
}