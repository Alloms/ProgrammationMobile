package com.example.myapplication.Presentation.Vue;


import android.app.Activity;
import android.content.Intent;
import android.widget.Toast;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Presentation.Controller.MainController;
import com.example.myapplication.Presentation.Modele.Crypto;
import com.example.myapplication.R;
import com.example.myapplication.Singletons;

import java.util.List;


public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ListAdapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private MainController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        controller = new MainController(
                this,
                Singletons.getGson(),
                Singletons.getSharedPreferences(getApplicationContext()));

        controller.onStart();

    }

    public void showList(List<Crypto> cryptoList)  {
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        // use a linear layout manager
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);


        // define an adapter
        mAdapter = new ListAdapter(cryptoList);

        recyclerView.setAdapter(mAdapter);
    }


    public void showError() {
        Toast.makeText(getApplicationContext(), "API Error", Toast.LENGTH_SHORT).show();
    }
    public void navigateToDetails(Crypto crypto) {
        Intent myIntent = new Intent(MainActivity.this, Activity.class);
        myIntent.putExtra("cryptoKey", Singletons.getGson().toJson(crypto));
        MainActivity.this.startActivity(myIntent);
    }
}