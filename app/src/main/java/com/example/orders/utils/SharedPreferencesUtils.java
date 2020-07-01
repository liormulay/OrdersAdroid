package com.example.orders.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferencesUtils {

    private static final String ROLE = "ROLE";

    private static String ORDERS = "Orders";

    private static String TOKEN = "TOKEN";

    public static void saveToken(Context context, String token) {
        SharedPreferences preferences = context.getSharedPreferences(ORDERS, Context.MODE_PRIVATE);
        preferences.edit().putString(TOKEN, token).apply();
    }

    public static String retrieveToken(Context context) {
        SharedPreferences preferences = context.getSharedPreferences(ORDERS, Context.MODE_PRIVATE);
        return preferences.getString(TOKEN, null);
    }

    public static void saveRole(Context context, String role) {
        SharedPreferences preferences = context.getSharedPreferences(ORDERS, Context.MODE_PRIVATE);
        preferences.edit().putString(ROLE, role).apply();
    }
}
