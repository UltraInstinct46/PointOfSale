package com.example.pointofsale.Activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;

import com.example.pointofsale.R;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {
    private Button distributorBtn,barangBtn,merekBtn,transactionBtn,userBtn, laporanBarangBtn, laporanTransactionBtn;
    private View distributorView,barangView,merekView,transactionView,userView, laporanBarangView, laporanTransactionView;
    public static final String EXTRA_LEVEL = "extra_level";

    @Nullable
    @Override
    public View onCreateView(@Nullable View parent, @NonNull String name, @NonNull Context context, @NonNull AttributeSet attrs) {
        return super.onCreateView(parent, name, context, attrs);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        levelChecker();
        distributorBtn = findViewById(R.id.distributor_activity);
        barangBtn = findViewById(R.id.barang_activity);
        merekBtn = findViewById(R.id.merek_activity);
        transactionBtn = findViewById(R.id.transaction_activity);
        userBtn = findViewById(R.id.user_activity);
        laporanBarangBtn = findViewById(R.id.laporan_barang);
        laporanTransactionBtn = findViewById(R.id.laporan_transaction);

        distributorBtn.setOnClickListener(this);
        barangBtn.setOnClickListener(this);
        merekBtn.setOnClickListener(this);
        transactionBtn.setOnClickListener(this);
        userBtn.setOnClickListener(this);
        laporanBarangBtn.setOnClickListener(this);
        laporanTransactionBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()){
            case R.id.distributor_activity:
                intent = new Intent(this,HomeDistributorActivity.class);
                startActivity(intent);
                break;
            case R.id.barang_activity:
                intent = new Intent(this,HomeBarangActivity.class);
                startActivity(intent);
                break;
            case R.id.merek_activity:
                intent = new Intent(this,HomeMerekActivity.class);
                startActivity(intent);
                break;
            case R.id.transaction_activity:
                intent = new Intent(this,HomeTransactionActivity.class);
                startActivity(intent);
                break;
            case R.id.user_activity:
                intent = new Intent(this,UserActivity.class);
                startActivity(intent);
                break;
            case R.id.laporan_barang:
                intent = new Intent(this, LaporanBarangActivity.class);
                startActivity(intent);
                break;
            case R.id.laporan_transaction:
                intent = new Intent(this, LaporanTransactionActivity.class);
                startActivity(intent);
                break;
        }
    }
    void levelChecker(){
        String levelExtra = getIntent().getStringExtra(EXTRA_LEVEL);
        transactionView = findViewById(R.id.transaction_activity);
        laporanBarangView = findViewById(R.id.laporan_barang);
        barangView = findViewById(R.id.barang_activity);
        distributorView = findViewById(R.id.distributor_activity);
        merekView = findViewById(R.id.merek_activity);
        laporanBarangView = findViewById(R.id.laporan_barang);
        userView = findViewById(R.id.user_activity);
        laporanTransactionView = findViewById(R.id.laporan_transaction);
        switch (levelExtra){
            case "Admin":
                transactionView.setVisibility(View.GONE);
                laporanBarangView.setVisibility(View.GONE);
                laporanTransactionView.setVisibility(View.GONE);
                break;
            case "Kasir":
                barangView.setVisibility(View.GONE);
                distributorView.setVisibility(View.GONE);
                merekView.setVisibility(View.GONE);
                laporanBarangView.setVisibility(View.GONE);
                userView.setVisibility(View.GONE);
                laporanTransactionView.setVisibility(View.GONE);
                break;
            case "Manager":
                barangView.setVisibility(View.GONE);
                distributorView.setVisibility(View.GONE);
                merekView.setVisibility(View.GONE);
                userView.setVisibility(View.GONE);
                transactionView.setVisibility(View.GONE);
                break;
        }
    }
}