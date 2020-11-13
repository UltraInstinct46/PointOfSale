package com.example.pointofsale.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.pointofsale.Adapter.LaporanBarangAdapter;
import com.example.pointofsale.Adapter.TransactionAdapter;
import com.example.pointofsale.Data.Barang;
import com.example.pointofsale.Data.Transaction;
import com.example.pointofsale.DataBase.DataBaseHandler;
import com.example.pointofsale.R;

import java.util.ArrayList;

public class LaporanBarangActivity extends AppCompatActivity {
    RecyclerView rvLaporanBarang;
    private DataBaseHandler laporanBarangHelper;
    private LaporanBarangAdapter laporanBarangAdapter;
    private ArrayList<Barang> list = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_laporan_barang);
        rvLaporanBarang = findViewById(R.id.rv_laporan_barang);
        laporanBarangHelper = new DataBaseHandler(this);
        list = (ArrayList<Barang>) laporanBarangHelper.findAllBarang();
        showRecyclerCardView();
    }
    private void showRecyclerCardView() {
        rvLaporanBarang.setLayoutManager(new LinearLayoutManager(this));
        laporanBarangAdapter = new LaporanBarangAdapter(list);
        rvLaporanBarang.setAdapter(laporanBarangAdapter);
    }
}