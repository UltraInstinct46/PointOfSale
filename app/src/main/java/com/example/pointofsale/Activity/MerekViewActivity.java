package com.example.pointofsale.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;

import com.example.pointofsale.Data.Barang;
import com.example.pointofsale.Data.Merek;
import com.example.pointofsale.Data.User;
import com.example.pointofsale.DataBase.DataBaseHandler;
import com.example.pointofsale.R;

import java.util.ArrayList;
import java.util.List;

public class MerekViewActivity extends AppCompatActivity implements View.OnClickListener{
    private DataBaseHandler merekViewHelper;
    private EditText namaMerekEdt;
    private Button updateBtn, deleteBtn;
    public static final String EXTRA_ID_MEREK_VIEW = "extra_id_merek_view";
    private Merek merek;
    private String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_merek_view);
        merekViewHelper = new  DataBaseHandler(this);
        namaMerekEdt = findViewById(R.id.merk_view);
        updateBtn = findViewById(R.id.update_merek);
        deleteBtn = findViewById(R.id.delete_merek);
        updateBtn.setOnClickListener(this);
        deleteBtn.setOnClickListener(this);
        findId();
    }

    @Override
    public void onClick(View v) {
        String namaMerekText = namaMerekEdt.getText().toString().trim();
        switch (v.getId()){
            case R.id.update_merek:
                merek.setMerek(namaMerekText);
                merekViewHelper.updateMerek(merek);
                Intent intent = new Intent(this,HomeMerekActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.delete_merek:
                merek.setId(getIntent().getIntExtra(EXTRA_ID_MEREK_VIEW, 0));
                merekViewHelper.deleteMerek(merek);
                Intent intent1 = new Intent(this,HomeMerekActivity.class);
                startActivity(intent1);
                finish();
                break;
        }
    }
    void findId(){
        int id = getIntent().getIntExtra(EXTRA_ID_MEREK_VIEW, 0);
        String namaMerek;
        merek = merekViewHelper.findOneMerek(id);
        namaMerek = merek.getMerek();
        namaMerekEdt.setText(namaMerek);
    }
}