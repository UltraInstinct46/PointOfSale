package com.example.pointofsale.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.pointofsale.Adapter.BarangAdapter;
import com.example.pointofsale.Data.Barang;
import com.example.pointofsale.DataBase.DataBaseHandler;
import com.example.pointofsale.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class HomeBarangActivity extends AppCompatActivity implements View.OnClickListener {
    RecyclerView rvBarang;
    private DataBaseHandler barangHelper;
    private BarangAdapter barangAdapter;
    private ArrayList<Barang> list = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_barang);
        rvBarang = findViewById(R.id.rv_barang);
        barangHelper = new DataBaseHandler(this);
        list = (ArrayList<Barang>) barangHelper.findAllBarang();
        showRecyclerCardView();
        FloatingActionButton fab = findViewById(R.id.floating_action_button_barang);
        fab.setOnClickListener(this);
    }
    private void showRecyclerCardView() {
        rvBarang.setLayoutManager(new LinearLayoutManager(this));
        barangAdapter = new BarangAdapter(list);
        rvBarang.setAdapter(barangAdapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.floating_action_button_barang:
                Intent intent = new Intent(this, StuffActivity.class);
                startActivity(intent);
                break;
        }
    }
}