package com.example.pointofsale.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.pointofsale.Adapter.MerekAdapter;
import com.example.pointofsale.Adapter.TransactionAdapter;
import com.example.pointofsale.Data.Merek;
import com.example.pointofsale.Data.Transaction;
import com.example.pointofsale.DataBase.DataBaseHandler;
import com.example.pointofsale.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class HomeMerekActivity extends AppCompatActivity implements View.OnClickListener{
    RecyclerView rvMerek;
    private DataBaseHandler merekHelper;
    private MerekAdapter merekAdapter;
    private ArrayList<Merek> list = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_merek);
        rvMerek = findViewById(R.id.rv_merek);
        merekHelper = new DataBaseHandler(this);
        list = (ArrayList<Merek>) merekHelper.findAllMerek();
        showRecyclerCardView();
        FloatingActionButton fab = findViewById(R.id.floating_action_button_merek);
        fab.setOnClickListener(this);
    }
    private void showRecyclerCardView() {
        rvMerek.setLayoutManager(new LinearLayoutManager(this));
        merekAdapter = new MerekAdapter(list);
        rvMerek.setAdapter(merekAdapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.floating_action_button_merek:
                Intent intent = new Intent(this, MerkActivity.class);
                startActivity(intent);
                break;
        }
    }
}