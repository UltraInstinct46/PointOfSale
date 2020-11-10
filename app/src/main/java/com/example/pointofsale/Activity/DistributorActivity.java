package com.example.pointofsale.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.pointofsale.Data.Distributor;
import com.example.pointofsale.DataBase.DataBaseHandler;
import com.example.pointofsale.R;

public class DistributorActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText namaDistributorEdt,alamatEdt,noTelpEdt;
    private DataBaseHandler distributorHelper;
    private Button add;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_distributor);
        distributorHelper = new DataBaseHandler(this);
        namaDistributorEdt = findViewById(R.id.nama_distributor_edt);
        alamatEdt = findViewById(R.id.alamat_edt);
        noTelpEdt = findViewById(R.id.no_telp_edt);
        add = findViewById(R.id.distributor_add);
        add.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        boolean isEmpty = false;
        String namaDistributorText = namaDistributorEdt.getText().toString().trim();
        String alamatText = alamatEdt.getText().toString().trim();
        String noTelpText = noTelpEdt.getText().toString().trim();
        if(TextUtils.isEmpty(namaDistributorText)){
            namaDistributorEdt.setError("This Field must filled");
            isEmpty = true;
        }
        if(TextUtils.isEmpty(alamatText)){
            alamatEdt.setError("This Field must filled");
            isEmpty = true;
        }
        if(TextUtils.isEmpty(noTelpText)){
            noTelpEdt.setError("This Field must filled");
            isEmpty = true;
        }
        if(isEmpty == false){
            distributorHelper.saveDistributor(new Distributor(namaDistributorText,alamatText,noTelpText));
        }
    }
}