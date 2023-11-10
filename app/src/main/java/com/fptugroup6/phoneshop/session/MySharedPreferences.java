package com.fptugroup6.phoneshop.session;

import android.content.Context;
import android.content.SharedPreferences;

public class MySharedPreferences {
    private static SharedPreferences sharedPreferences;
    private static MySharedPreferences instance;

    private MySharedPreferences(Context context) {
        // Khởi tạo SharedPreferences trong constructor
        sharedPreferences = context.getSharedPreferences("myAppPrefs", Context.MODE_PRIVATE);
    }

    public static synchronized MySharedPreferences getInstance(Context context) {
        if (instance == null) {
            instance = new MySharedPreferences(context);
        }
        return instance;
    }

    public void saveData(String key, String value) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, value);
        editor.apply();
    }

    public String getData(String key, String defaultValue) {
        return sharedPreferences.getString(key, defaultValue);
    }
}
