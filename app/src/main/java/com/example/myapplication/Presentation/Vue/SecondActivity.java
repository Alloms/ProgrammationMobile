package com.example.myapplication.Presentation.Vue;


import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;


import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.Presentation.Modele.Crypto;
import com.example.myapplication.R;
import com.example.myapplication.Singletons;


public class SecondActivity extends AppCompatActivity {

    private TextView Name;
    private TextView Rang;
    private TextView symb;
    private TextView usd;
    private TextView btc;
    private TextView pc1;
    private TextView pc7;
    private TextView pc24;
    private TextView marketcap;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);

        Intent intent = getIntent();

        Name = findViewById(R.id.nom);
        Rang = findViewById(R.id.Rank);
        symb = findViewById(R.id.Symbol);
        usd = findViewById(R.id.prixusd);
        btc = findViewById(R.id.prixbtc);
        pc1 = findViewById(R.id.change1h);
        pc7 = findViewById(R.id.change7d);
        pc24 = findViewById(R.id.change24h);
        marketcap = findViewById(R.id.marketcap);


        Name.setText(intent.getStringExtra("Name"));
        Rang.setText(intent.getStringExtra("Rank") + "#");
        symb.setText(intent.getStringExtra("symb"));
        usd.setText("Prix en USD : " + intent.getStringExtra("usd") + "$");
        btc.setText("Prix en BTC : " + intent.getStringExtra("btc") + "BTC");
        pc1.setText("Taux d'échange 1H : "+ intent.getStringExtra("pc1") + "%");
        pc7.setText("Taux d'échange 7j : " + intent.getStringExtra("pc7") + "%");
        pc24.setText("Taux d'échange 24H : " + intent.getStringExtra("pc24") + "%");
        marketcap.setText("Market Cap : " + intent.getStringExtra("marketcap") + "$");


    }
}



