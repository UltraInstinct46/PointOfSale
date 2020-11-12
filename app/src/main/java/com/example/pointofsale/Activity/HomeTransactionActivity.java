package com.example.pointofsale.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.pointofsale.Adapter.BarangAdapter;
import com.example.pointofsale.Adapter.TransactionAdapter;
import com.example.pointofsale.Data.Barang;
import com.example.pointofsale.Data.Transaction;
import com.example.pointofsale.DataBase.DataBaseHandler;
import com.example.pointofsale.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class HomeTransactionActivity extends AppCompatActivity implements View.OnClickListener{
    RecyclerView rvTransaction;
    private DataBaseHandler transactionHelper;
    private TransactionAdapter transactionAdapter;
    private ArrayList<Transaction> list = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_transaction);
        rvTransaction = findViewById(R.id.rv_transaction);
        transactionHelper = new DataBaseHandler(this);
        list = (ArrayList<Transaction>) transactionHelper.findAllTransaction();
        showRecyclerCardView();
        FloatingActionButton fab = findViewById(R.id.floating_action_button_transaction);
        fab.setOnClickListener(this);
    }
    private void showRecyclerCardView() {
        rvTransaction.setLayoutManager(new LinearLayoutManager(this));
        transactionAdapter = new TransactionAdapter(list);
        rvTransaction.setAdapter(transactionAdapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.floating_action_button_transaction:
                Intent intent = new Intent(this, TransactionActivity.class);
                startActivity(intent);
                break;
        }
    }
}