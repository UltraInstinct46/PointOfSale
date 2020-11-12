package com.example.pointofsale.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.pointofsale.Data.Barang;
import com.example.pointofsale.Data.Distributor;
import com.example.pointofsale.Data.Merek;
import com.example.pointofsale.DataBase.DataBaseHandler;
import com.example.pointofsale.R;

import java.util.ArrayList;
import java.util.List;

public class StuffActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener, View.OnClickListener {
    private List<Distributor> listDistributor;
    private List<Merek> listMerek;
    private DataBaseHandler barangHelper;
    private Spinner merekSpn,distributorSpn;
    private int merekText,distributorText;
    private Button add;
    private EditText namaBarangEdt,tanggalMasukEdt,hargaBarangEdt,stockBarangEdt,keteranganEdt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stuff);
        namaBarangEdt = findViewById(R.id.nama_barang_edt);
        tanggalMasukEdt = findViewById(R.id.tanggal_masuk_edt);
        hargaBarangEdt = findViewById(R.id.harga_barang_edt);
        stockBarangEdt = findViewById(R.id.stock_barang_edt);
        keteranganEdt = findViewById(R.id.keterangan_edt);
        merekSpn = findViewById(R.id.spn_merek);
        distributorSpn = findViewById(R.id.spn_distributor);
        barangHelper = new DataBaseHandler(this);
        listDistributor = new ArrayList<>();
        listMerek = new ArrayList<>();
        listDistributor = barangHelper.findAllDistributor();
        listMerek = barangHelper.findAllMerek();
        List<Integer> listMerekItem = new ArrayList<Integer>();
        List<Integer> listDistributorItem = new ArrayList<Integer>();
        for(Merek b:listMerek){
            listMerekItem.add(b.getId());
        }
        for(Distributor b:listDistributor){
            listDistributorItem.add(b.getId());
        }
        merekSpn.setOnItemSelectedListener(this);
        distributorSpn.setOnItemSelectedListener(this);

        ArrayAdapter<Integer> dataAdapterMerek = new ArrayAdapter<Integer>(this, android.R.layout.simple_spinner_item, listMerekItem);
        dataAdapterMerek.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        merekSpn.setAdapter(dataAdapterMerek);

        ArrayAdapter<Integer> dataAdapterDistributor = new ArrayAdapter<Integer>(this, android.R.layout.simple_spinner_item, listDistributorItem);
        dataAdapterDistributor.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        distributorSpn.setAdapter(dataAdapterDistributor);
        add = findViewById(R.id.add_stuff);
        add.setOnClickListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch(parent.getId()){
            case R.id.spn_merek:
                merekText = Integer.parseInt(merekSpn.getSelectedItem().toString());
                break;
            case R.id.spn_distributor:
                distributorText = Integer.parseInt(distributorSpn.getSelectedItem().toString());
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.add_stuff:
                boolean isEmpty = false;
                String namaBarang = namaBarangEdt.getText().toString().trim();
                String tanggalMasuk = tanggalMasukEdt.getText().toString().trim();
                int hargaBarang = Integer.parseInt(hargaBarangEdt.getText().toString().trim());
                int stockBarang = Integer.parseInt(stockBarangEdt.getText().toString().trim());
                String keteranganBarang = keteranganEdt.getText().toString().trim();
                String hargaBarangString = hargaBarangEdt.getText().toString().trim();
                String stockBarangString = stockBarangEdt.getText().toString().trim();
                if(TextUtils.isEmpty(namaBarang)){
                    namaBarangEdt.setError("This field must filled");
                    isEmpty = true;
                }
                if(TextUtils.isEmpty(tanggalMasuk)){
                    tanggalMasukEdt.setError("This field must filled");
                    isEmpty = true;
                }
                if(TextUtils.isEmpty(hargaBarangString)){
                    hargaBarangEdt.setError("This field must filled");
                    isEmpty = true;
                }
                if(TextUtils.isEmpty(stockBarangString)){
                    stockBarangEdt.setError("This field must filled");
                    isEmpty = true;
                }
                if(TextUtils.isEmpty(keteranganBarang)){
                    keteranganEdt.setError("This field must filled");
                    isEmpty = true;
                }
                if(isEmpty == false) {
                    barangHelper.saveBarang(new Barang(namaBarang,merekText,distributorText,tanggalMasuk,hargaBarang,stockBarang,keteranganBarang));
                }
                break;
        }
    }
}