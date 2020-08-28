package com.example.myapplication;


import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;


import androidx.appcompat.app.AppCompatActivity;



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
    private Intent intent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);

        intent = getIntent();
        String cryptoJson = intent.getStringExtra("cryptoKey");
        Crypto crypto = Singletons.getGson().fromJson(cryptoJson, Crypto.class);

        Name = findViewById(R.id.nom);
        Rang = findViewById(R.id.Rank);
        symb = findViewById(R.id.Symbol);
        usd = findViewById(R.id.prixusd);
        btc = findViewById(R.id.prixbtc);
        pc1 = findViewById(R.id.change1h);
        pc7 = findViewById(R.id.change7d);
        pc24 = findViewById(R.id.change24h);
        marketcap = findViewById(R.id.marketcap);


        showDetail(crypto);
    }


    private void showDetail(Crypto crypto) {
        Name.setText(crypto.getName());
        Rang.setText(crypto.getRank() + "#");
        symb.setText(crypto.getSymbol());
        usd.setText(crypto.getPrice_usd() + "$");
        btc.setText(crypto.getPrice_btc() + "BTC");
        pc1.setText(crypto.getPercent_change_1h() + "%");
        pc7.setText(crypto.getPercent_change_7d() + "%");
        pc24.setText(crypto.getPercent_change_24h() + "%");
        marketcap.setText(crypto.getMarket_cap_usd() + "$");


    }


}

