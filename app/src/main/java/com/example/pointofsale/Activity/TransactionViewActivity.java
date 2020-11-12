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
import com.example.pointofsale.Data.Transaction;
import com.example.pointofsale.Data.User;
import com.example.pointofsale.DataBase.DataBaseHandler;
import com.example.pointofsale.R;

import java.util.ArrayList;
import java.util.List;

public class TransactionViewActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener, View.OnClickListener{
    private List<Barang> listBarang;
    private List<User> listUser;
    private DataBaseHandler transactionViewHelper;
    private EditText jumlahBeliEdt,totalHargaEdt,tanggalBeliEdt;
    private Spinner barangSpn, userSpn;
    private Button updateBtn, deleteBtn;
    public static final String EXTRA_ID_TRANSACTION_VIEW = "extra_id_transaction_view";
    private Transaction transaction;
    private String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction_view);
        transactionViewHelper = new  DataBaseHandler(this);
        jumlahBeliEdt = findViewById(R.id.qty_view_edt);
        totalHargaEdt = findViewById(R.id.totalcost_view_edt);
        tanggalBeliEdt = findViewById(R.id.tanggal_beli_view_edt);
        barangSpn = findViewById(R.id.spn_barang_view);
        userSpn = findViewById(R.id.spn_user_view);
        updateBtn = findViewById(R.id.update_transaction);
        deleteBtn = findViewById(R.id.delete_transaction);
        barangSpn.setOnItemSelectedListener(this);
        userSpn.setOnItemSelectedListener(this);
        updateBtn.setOnClickListener(this);
        deleteBtn.setOnClickListener(this);

        listBarang = new ArrayList<>();
        listUser = new ArrayList<>();
        listBarang = transactionViewHelper.findAllBarang();
        listUser = transactionViewHelper.findAllUser();
        List<Integer> listUserItem = new ArrayList<Integer>();
        List<Integer> listBarangItem = new ArrayList<Integer>();
        findId();
        for(User b: listUser){
            listUserItem.add(b.getId());
        }
        for(Barang b: listBarang){
            listBarangItem.add(b.getId());
        }
        ArrayAdapter<Integer> dataAdapterUser = new ArrayAdapter<Integer>(this, android.R.layout.simple_spinner_item, listUserItem);
        dataAdapterUser.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        userSpn.setAdapter(dataAdapterUser);

        ArrayAdapter<Integer> dataAdapterBarang = new ArrayAdapter<Integer>(this, android.R.layout.simple_spinner_item, listBarangItem);
        dataAdapterBarang.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        barangSpn.setAdapter(dataAdapterBarang);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onClick(View v) {
        int jumlahBeliText = Integer.parseInt(jumlahBeliEdt.getText().toString().trim());
        int totalHargaText = Integer.parseInt(totalHargaEdt.getText().toString().trim());
        switch (v.getId()){
            case R.id.update_transaction:
                transaction.setJumlah_beli(jumlahBeliText);
                transaction.setTotal_harga(totalHargaText);
                transactionViewHelper.updateTransaction(transaction);
                Intent intent = new Intent(this,HomeTransactionActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.delete_transaction:
                transaction.setId(getIntent().getIntExtra(EXTRA_ID_TRANSACTION_VIEW, 0));
                transactionViewHelper.deleteTransaction(transaction);
                Intent intent1 = new Intent(this,HomeTransactionActivity.class);
                startActivity(intent1);
                finish();
                break;
        }
    }
    void findId(){
        int id = getIntent().getIntExtra(EXTRA_ID_TRANSACTION_VIEW, 0);
        int jumlahBeli,totalHarga,idBarang,idUser;
        String tanggalBeli;
        transaction = transactionViewHelper.findOneTransaction(id);
        jumlahBeli = transaction.getJumlah_beli();
        totalHarga = transaction.getTotal_harga();
        idBarang = transaction.getKd_barang();
        idUser = transaction.getKd_user();
        tanggalBeli = transaction.getDate();
        jumlahBeliEdt.setText(String.valueOf(jumlahBeli));
        totalHargaEdt.setText(String.valueOf(totalHarga));
        tanggalBeliEdt.setText(tanggalBeli);
    }
}