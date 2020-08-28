package com.example.myapplication;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.widget.Toast;

import java.lang.reflect.Type;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ListAdapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private SharedPreferences sharedPreferences;
    private Gson gson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedPreferences = getSharedPreferences("appli_crypto", Context.MODE_PRIVATE);

        gson = new GsonBuilder()
                .setLenient()
                .create();

        List<Crypto> cryptoList = getDataFromCache();

        if(cryptoList !=null){
            showList(cryptoList);
        }else {
            makeApicall();
        }

        makeApicall();


    }

    private List<Crypto> getDataFromCache() {
        String jsonCrypto = sharedPreferences.getString(Constants.KEY_CRYPTO_LIST,null);

        if(jsonCrypto == null){
            return null;
        }else{
            Type listType = new TypeToken<List<Crypto>>(){}.getType();
            return gson.fromJson(jsonCrypto, listType);
        }
    }

    private static final String BASE_URL = "https://api.coinlore.net/";

    private void showList(List<Crypto> cryptoList)  {
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        // use a linear layout manager
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);


        // define an adapter
        mAdapter = new ListAdapter(cryptoList);
        recyclerView.setAdapter(mAdapter);
    }

    private void makeApicall() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
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
                    showList(CryptoList);
                } else{
                    showError();
                }
            }

            @Override
            public void onFailure(Call<RestAPIRep> call, Throwable t) {
                showError();
            }
        });

    }

    private void saveList (List<Crypto> CryptoList){

        String jsonString = gson.toJson(CryptoList);
        sharedPreferences
                .edit()
                .putString(Constants.KEY_CRYPTO_LIST, jsonString)
                .apply();

        Toast.makeText(getApplicationContext(), "List saved", Toast.LENGTH_SHORT).show();
    }

    private void showError() {
        Toast.makeText(getApplicationContext(), "API Error", Toast.LENGTH_SHORT).show();
    }
}