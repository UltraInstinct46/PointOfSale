package com.example.pointofsale.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
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

public class BarangViewActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener, View.OnClickListener {
    private List<Distributor> listDistributor;
    private List<Merek> listMerek;
    private DataBaseHandler barangViewHelper;
    private EditText namaBarangEdt,tanggalMasukEdt,hargaBarangEdt,stockBarangEdt,keteranganEdt;
    private Spinner merekSpn,distributorSpn;
    private Button updateBtn, deleteBtn;
    public static final String EXTRA_ID_BARANG_VIEW = "extra_id_barang_view";
    private Barang barang;
    private String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_barang_view);
        barangViewHelper = new  DataBaseHandler(this);
        namaBarangEdt = findViewById(R.id.nama_barang_view_edt);
        tanggalMasukEdt = findViewById(R.id.tanggal_masuk_view_edt);
        hargaBarangEdt = findViewById(R.id.harga_barang_view_edt);
        stockBarangEdt = findViewById(R.id.stock_barang_view_edt);
        keteranganEdt = findViewById(R.id.keterangan_view_edt);
        merekSpn = findViewById(R.id.spn_merek_view);
        distributorSpn = findViewById(R.id.spn_distributor_view);
        updateBtn = findViewById(R.id.update_stuff);
        deleteBtn = findViewById(R.id.delete_stuff);
        merekSpn.setOnItemSelectedListener(this);
        distributorSpn.setOnItemSelectedListener(this);
        updateBtn.setOnClickListener(this);
        deleteBtn.setOnClickListener(this);

        listDistributor = new ArrayList<>();
        listMerek = new ArrayList<>();
        listDistributor = barangViewHelper.findAllDistributor();
        listMerek = barangViewHelper.findAllMerek();
        List<Integer> listMerekItem = new ArrayList<Integer>();
        List<Integer> listDistributorItem = new ArrayList<Integer>();
        findId();
        for(Merek b:listMerek){
            listMerekItem.add(b.getId());
        }
        for(Distributor b:listDistributor){
            listDistributorItem.add(b.getId());
        }
        ArrayAdapter<Integer> dataAdapterMerek = new ArrayAdapter<Integer>(this, android.R.layout.simple_spinner_item, listMerekItem);
        dataAdapterMerek.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        merekSpn.setAdapter(dataAdapterMerek);

        ArrayAdapter<Integer> dataAdapterDistributor = new ArrayAdapter<Integer>(this, android.R.layout.simple_spinner_item, listDistributorItem);
        dataAdapterDistributor.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        distributorSpn.setAdapter(dataAdapterDistributor);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onClick(View v) {
        String namaBarangText = namaBarangEdt.getText().toString().trim();
        String tanggalMasukText = tanggalMasukEdt.getText().toString().trim();
        int hargaText = Integer.parseInt(hargaBarangEdt.getText().toString().trim());
        int stockText = Integer.parseInt(stockBarangEdt.getText().toString().trim());
        String keteranganText = keteranganEdt.getText().toString().trim();
        switch (v.getId()){
            case R.id.update_stuff:
                barang.setNama_barang(namaBarangText);
                barang.setTanggal_masuk(tanggalMasukText);
                barang.setHarga_barang(hargaText);
                barang.setStok_barang(stockText);
                barang.setKeterangan(keteranganText);
                barangViewHelper.updateBarang(barang);
                Intent intent = new Intent(this,HomeBarangActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.delete_stuff:
                break;
        }
    }
    void findId(){
        int id = getIntent().getIntExtra(EXTRA_ID_BARANG_VIEW, 0);
        String namaBarang,tanggalMasukBarang,keteranganBarang;
        int idMerek,idDistributor,hargaBarang,stockBarang;
        barang = barangViewHelper.findOneBarang(id);
        namaBarang = barang.getNama_barang();
        tanggalMasukBarang = barang.getTanggal_masuk();
        keteranganBarang = barang.getKeterangan();
        idMerek = barang.getKd_merek();
        idDistributor = barang.getKd_distributor();
        hargaBarang = barang.getHarga_barang();
        stockBarang = barang.getStok_barang();
        namaBarangEdt.setText(namaBarang);
        tanggalMasukEdt.setText(tanggalMasukBarang);
        hargaBarangEdt.setText(String.valueOf(hargaBarang));
        stockBarangEdt.setText(String.valueOf(stockBarang));
        keteranganEdt.setText(keteranganBarang);
    }
}