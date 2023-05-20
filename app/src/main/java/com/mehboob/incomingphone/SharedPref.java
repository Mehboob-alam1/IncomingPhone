package com.mehboob.incomingphone;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPref {

    private SharedPreferences sharedPreferences;
    private static final String API = "api";
    private static final String RESTAURANT_ID="restaurant_id";


    public SharedPref(Context context) {

        sharedPreferences = context.getSharedPreferences(API, MODE_PRIVATE);
        sharedPreferences=context.getSharedPreferences(RESTAURANT_ID,MODE_PRIVATE);
    }

    public void saveApi(String api) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(API, api);
        editor.apply();
    }
    
    public String fetchApi()
    {
        
        return sharedPreferences.getString(API,"https://google.com");
    }
    
    public void saveRestaurantId(String restaurantId)
    {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(RESTAURANT_ID,restaurantId);
        editor.apply();
    }
    public String fetchRestaurantId()
    {
        return sharedPreferences.getString(RESTAURANT_ID,"");
    }
}
