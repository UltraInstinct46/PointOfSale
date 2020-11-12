package com.example.pointofsale.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteConstraintException;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.pointofsale.Data.Merek;
import com.example.pointofsale.DataBase.DataBaseHandler;
import com.example.pointofsale.R;

public class MerkActivity extends AppCompatActivity implements View.OnClickListener {
    private DataBaseHandler merkHelper;
    private EditText merkEdt;
    private Button addMerk;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_merk);
        merkHelper = new DataBaseHandler(this);
        merkEdt = findViewById(R.id.merk);
        addMerk = findViewById(R.id.add_merk);
        addMerk.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        boolean isEmpty = false;
        String merkText = merkEdt.getText().toString().trim();
        if(TextUtils.isEmpty(merkText)){
            merkEdt.setError("This Field must filled");
            isEmpty=true;
        }
        if(isEmpty==false){
                merkHelper.saveMerek(new Merek(merkText));
                Intent intent = new Intent(this,HomeMerekActivity.class);
                startActivity(intent);
                finish();
        }
    }
}