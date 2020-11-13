package com.example.pointofsale.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.pointofsale.Adapter.LaporanBarangAdapter;
import com.example.pointofsale.Adapter.LaporanTransactionAdapter;
import com.example.pointofsale.Data.Barang;
import com.example.pointofsale.Data.Transaction;
import com.example.pointofsale.DataBase.DataBaseHandler;
import com.example.pointofsale.R;

import java.util.ArrayList;

public class LaporanTransactionActivity extends AppCompatActivity {
    RecyclerView rvLaporanTransaction;
    private DataBaseHandler laporanTransactionHelper;
    private LaporanTransactionAdapter laporanTransactionAdapter;
    private ArrayList<Transaction> list = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_laporan_transaction);
        rvLaporanTransaction = findViewById(R.id.rv_laporan_transaction);
        laporanTransactionHelper = new DataBaseHandler(this);
        list = (ArrayList<Transaction>) laporanTransactionHelper.findAllTransaction();
        showRecyclerCardView();
    }
    private void showRecyclerCardView() {
        rvLaporanTransaction.setLayoutManager(new LinearLayoutManager(this));
        laporanTransactionAdapter = new LaporanTransactionAdapter(list);
        rvLaporanTransaction.setAdapter(laporanTransactionAdapter);
    }
}