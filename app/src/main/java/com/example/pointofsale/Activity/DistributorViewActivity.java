package com.example.pointofsale.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.pointofsale.Data.Distributor;
import com.example.pointofsale.DataBase.DataBaseHandler;
import com.example.pointofsale.R;

public class DistributorViewActivity extends AppCompatActivity implements  View.OnClickListener {
    private DataBaseHandler distributorViewHelper;
    private EditText namaDistributorEdt, alamatDistributorEdt, noTelpDistributorEdt;
    private Button updateBtn, deleteBtn;
    public static final String EXTRA_ID_DISTRIBUTOR_VIEW = "extra_id_distributor_view";
    private Distributor distributor;
    private String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_distributor_view);
        distributorViewHelper = new  DataBaseHandler(this);
        namaDistributorEdt = findViewById(R.id.nama_distributor_view_edt);
        alamatDistributorEdt = findViewById(R.id.alamat_view_edt);
        noTelpDistributorEdt = findViewById(R.id.no_telp_view_edt);
        updateBtn = findViewById(R.id.update_distributor);
        deleteBtn = findViewById(R.id.delete_distributor);
        updateBtn.setOnClickListener(this);
        deleteBtn.setOnClickListener(this);

        findId();
    }

    @Override
    public void onClick(View v) {
        String namaDistributorText = namaDistributorEdt.getText().toString().trim();
        String alamatDistributorText = alamatDistributorEdt.getText().toString().trim();
        String noTelpText = noTelpDistributorEdt.getText().toString().trim();
        switch (v.getId()){
            case R.id.update_distributor:
                distributor.setNama_distributor(namaDistributorText);
                distributor.setAlamat(alamatDistributorText);
                distributor.setNo_telp(noTelpText);
                distributorViewHelper.updateDistributor(distributor);
                Intent intent = new Intent(this,HomeDistributorActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.delete_distributor:
                distributor.setId(getIntent().getIntExtra(EXTRA_ID_DISTRIBUTOR_VIEW, 0));
                distributorViewHelper.deleteDistributor(distributor);
                Intent intent1 = new Intent(this,HomeDistributorActivity.class);
                startActivity(intent1);
                finish();
                break;
        }
    }
    void findId(){
        int id = getIntent().getIntExtra(EXTRA_ID_DISTRIBUTOR_VIEW, 0);
        String namaDistributor,alamatDistributor,noTelpDistributor;
        distributor = distributorViewHelper.findOneDistributor(id);
        namaDistributor = distributor.getNama_distributor();
        alamatDistributor = distributor.getAlamat();
        noTelpDistributor = distributor.getNo_telp();
        namaDistributorEdt.setText(namaDistributor);
        alamatDistributorEdt.setText(alamatDistributor);
        noTelpDistributorEdt.setText(noTelpDistributor);
    }
}