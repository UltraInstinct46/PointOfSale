package com.example.pointofsale.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
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
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class TransactionActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener, View.OnClickListener, TextWatcher {
    private Spinner idBarangSpn, idUserSpn;
    private EditText jumlahBeliEdt,totalHargaEdt;
    private Button addBtn;
    private DataBaseHandler transactionHelper;
    private Date date;
    private String dateText;
    private List<Barang> listBarang;
    private List<User> listUser;
    private int barangText, userText;
    private int price = 0;
    private int stock = 0;
    private Barang barang;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction);
        date = Calendar.getInstance().getTime();
        dateText = date.toString();
        idBarangSpn = findViewById(R.id.spn_barang);
        idUserSpn = findViewById(R.id.spn_user);
        jumlahBeliEdt = findViewById(R.id.qty_edt);
        totalHargaEdt = findViewById(R.id.totalcost_edt);
        addBtn = findViewById(R.id.add_transaction);
        transactionHelper = new DataBaseHandler(this);
        idBarangSpn.setOnItemSelectedListener(this);
        idUserSpn.setOnItemSelectedListener(this);

        jumlahBeliEdt.addTextChangedListener(this);
        List<Integer> listBarangItem = new ArrayList<Integer>();
        List<Integer> listUserItem = new ArrayList<Integer>();
        listBarang = new ArrayList<>();
        listUser = new ArrayList<>();
        listBarang = transactionHelper.findAllBarang();
        listUser = transactionHelper.findAllUser();
        for(Barang b:listBarang){
            listBarangItem.add(b.getId());
        }
        for(User b:listUser){
            listUserItem.add(b.getId());
        }
        ArrayAdapter<Integer> dataAdapterBarang = new ArrayAdapter<Integer>(this, android.R.layout.simple_spinner_item, listBarangItem);
        dataAdapterBarang.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        idBarangSpn.setAdapter(dataAdapterBarang);

        ArrayAdapter<Integer> dataAdapterUser = new ArrayAdapter<Integer>(this, android.R.layout.simple_spinner_item, listUserItem);
        dataAdapterUser.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        idUserSpn.setAdapter(dataAdapterUser);

        addBtn.setOnClickListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch (parent.getId()){
            case R.id.spn_barang:
                barangText = Integer.valueOf(idBarangSpn.getSelectedItem().toString());
                barang = transactionHelper.findOneBarang(barangText);
                price = barang.getHarga_barang();
                stock = barang.getStok_barang();
                break;
            case R.id.spn_user:
                userText = Integer.valueOf(idUserSpn.getSelectedItem().toString());
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.add_transaction:
                boolean isEmpty = false;
                int jumlahBeliInt = Integer.valueOf(jumlahBeliEdt.getText().toString().trim());
                int totalHargaInt = Integer.valueOf(totalHargaEdt.getText().toString().trim());

                String totalHargaText = totalHargaEdt.getText().toString().trim();
                String jumlahBeliText = jumlahBeliEdt.getText().toString().trim();
                if(TextUtils.isEmpty(jumlahBeliText)){
                    jumlahBeliEdt.setError("This field must filled");
                    isEmpty = true;
                }
                if(TextUtils.isEmpty(totalHargaText)){
                    totalHargaEdt.setError("This field must filled");
                    isEmpty = true;
                }
                if(isEmpty == false) {
                    transactionHelper.saveTransaction(new Transaction(barangText,userText, jumlahBeliInt, totalHargaInt,dateText));
                    int totalStock = stock - jumlahBeliInt;
                    barang.setStok_barang(totalStock);
                    transactionHelper.updateBarang(barang);
                    Intent intent = new Intent(this,HomeTransactionActivity.class);
                    startActivity(intent);
                    finish();
                }
                break;
        }
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
    }

    @Override
    public void afterTextChanged(Editable s) {
        boolean isEmpty = false;
        int jumlahBeliInt;
        try {
            jumlahBeliInt = Integer.valueOf(jumlahBeliEdt.getText().toString().trim());
        }catch (NumberFormatException e){
            jumlahBeliInt = 0;
        }
        if(TextUtils.isEmpty(jumlahBeliEdt.getText().toString().trim())){
            totalHargaEdt.setText("0");
            jumlahBeliInt = 0;
            isEmpty = true;
        }
        if(jumlahBeliInt  > stock){
            jumlahBeliEdt.setError("The stock doesn't enough");
            jumlahBeliInt = 0;
            isEmpty = true;
        }
        if(isEmpty == false) {
            int formula = price * jumlahBeliInt;
            totalHargaEdt.setText(String.valueOf(formula));

        }
    }
}