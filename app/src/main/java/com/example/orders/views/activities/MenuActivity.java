package com.example.orders.views.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.orders.R;
import com.example.orders.utils.SharedPreferencesUtils;

public abstract class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_layout, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.logout_item:
                startActivity(new Intent(MenuActivity.this, LoginActivity.class));
                SharedPreferencesUtils.clearAll(this);
                finishAffinity();
                return true;
            case R.id.homepage_item:
                startActivity(new Intent(MenuActivity.this, HomePageMangerActivity.getHomePageClass(this)));
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}