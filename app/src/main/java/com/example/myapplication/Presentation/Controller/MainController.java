package com.example.myapplication.Presentation.Controller;

import android.content.SharedPreferences;
import android.widget.Toast;
import com.example.myapplication.Constants;


import com.example.myapplication.Presentation.Modele.Crypto;
import com.example.myapplication.Données.CryptoAPI;
import com.example.myapplication.Presentation.Vue.MainActivity;
import com.example.myapplication.Presentation.Modele.RestAPIRep;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainController {

    private SharedPreferences sharedPreferences;
    private Gson gson;
    private MainActivity view;

    public MainController(MainActivity mainActivity, Gson gson, SharedPreferences sharedPreferences) {
        this.view = mainActivity;
        this.gson = gson;
        this.sharedPreferences = sharedPreferences;
    }

    public void onStart() {

        List<Crypto> cryptoList = getDataFromCache();
        if(cryptoList != null) {
            view.showList(cryptoList);
        } else {
            makeApicall();
        }
    }


    private void makeApicall() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        CryptoAPI cryptoAPI = retrofit.create(CryptoAPI.class);

        Call<RestAPIRep> call = cryptoAPI.getCryptoRep();
        call.enqueue(new Callback<RestAPIRep>() {

            @Override
            public void onResponse(Call<RestAPIRep> call, Response<RestAPIRep> response) {
                if (response.isSuccessful() && response.body() != null){
                    List<Crypto> CryptoList = response.body().getData();
                    saveList(CryptoList);
                    view.showList(CryptoList);
                } else{
                    view.showError();
                }
            }

            @Override
            public void onFailure(Call<RestAPIRep> call, Throwable t) {
                view.showError();
            }
        });

    }
    private void saveList(List<Crypto> CryptoList) {
        String jsonString = gson.toJson(CryptoList);
        sharedPreferences
                .edit()
                .putString(Constants.KEY_CRYPTO_LIST, jsonString)
                .apply();
        Toast.makeText(view.getApplicationContext(), "List Saved", Toast.LENGTH_SHORT).show();
    }


    private List<Crypto> getDataFromCache() {
        String cryptoJson = sharedPreferences.getString(Constants.KEY_CRYPTO_LIST, null);

        if(cryptoJson == null) {
            return null;
        } else {
            Type listType = new TypeToken<ArrayList<Crypto>>(){}.getType();
            return gson.fromJson(cryptoJson, listType);
        }

    }


    public void onItemClick(Crypto crypto) {
        view.navigateToDetails(crypto);
    }

    public void onButtonAClick() {

    }

    public void onButtonBClick() {

    }
}
