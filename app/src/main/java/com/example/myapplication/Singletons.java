package com.example.myapplication;


import android.content.Context;
import android.content.SharedPreferences;

import com.example.myapplication.Données.CryptoAPI;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Singletons {
    private static Gson gsonInstance;
    private static CryptoAPI CryptoApiInstance;
    private static SharedPreferences sharedPreferencesInstance;

    public static Gson getGson() {
        if(gsonInstance == null) {
            gsonInstance = new GsonBuilder()
                    .setLenient()
                    .create();
        }
        return gsonInstance;
    }

    public static CryptoAPI getMatchAPI() {
        if(CryptoApiInstance == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(Constants.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(getGson()))
                    .build();
            CryptoApiInstance = retrofit.create(CryptoAPI.class);
        }
        return CryptoApiInstance;
    }

    public static SharedPreferences getSharedPreferences(Context context) {
        if(sharedPreferencesInstance == null) {
            sharedPreferencesInstance = context.getSharedPreferences(Constants.KEY_CRYPTO_STORAGE, Context.MODE_PRIVATE);
        }
        return sharedPreferencesInstance;
    }
}
