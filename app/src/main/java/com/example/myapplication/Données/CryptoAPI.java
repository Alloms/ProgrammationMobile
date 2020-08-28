package com.example.myapplication.Données;

import com.example.myapplication.Presentation.Modele.RestAPIRep;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CryptoAPI {
    @GET("api/tickers/?start=0&limit=100")
        // J'ai imposé une limite à 100 car il existe des milliers de crytpomonnaies. Ici on s'interesse au top 25

    Call<RestAPIRep> getCryptoRep();


}
