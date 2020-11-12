package com.example.pointofsale.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.pointofsale.Adapter.BarangAdapter;
import com.example.pointofsale.Adapter.DistributorAdapter;
import com.example.pointofsale.Data.Barang;
import com.example.pointofsale.Data.Distributor;
import com.example.pointofsale.DataBase.DataBaseHandler;
import com.example.pointofsale.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class HomeDistributorActivity extends AppCompatActivity implements View.OnClickListener{
    RecyclerView rvDistributor;
    private DataBaseHandler distributorHelper;
    private DistributorAdapter distributorAdapter;
    private ArrayList<Distributor> list = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_distributor);
        rvDistributor = findViewById(R.id.rv_distributor);
        distributorHelper = new DataBaseHandler(this);
        list = (ArrayList<Distributor>) distributorHelper.findAllDistributor();
        showRecyclerCardView();
        FloatingActionButton fab = findViewById(R.id.floating_action_button_distributor);
        fab.setOnClickListener(this);
    }
    private void showRecyclerCardView() {
        rvDistributor.setLayoutManager(new LinearLayoutManager(this));
        distributorAdapter = new DistributorAdapter(list);
        rvDistributor.setAdapter(distributorAdapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.floating_action_button_distributor:
                Intent intent = new Intent(this, DistributorActivity.class);
                startActivity(intent);
                break;
        }
    }
}