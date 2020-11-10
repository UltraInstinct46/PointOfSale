package com.example.pointofsale.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.pointofsale.Data.User;
import com.example.pointofsale.DataBase.UserDataBaseHandler;
import com.example.pointofsale.R;

import java.util.List;

public class UserActivity extends AppCompatActivity implements View.OnClickListener {
    String levelText;
    private EditText edtUsername,edtPassword;
    private UserDataBaseHandler userHelper;
    private List<User> listUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        final Spinner level = findViewById(R.id.listLevel);
        edtUsername = findViewById(R.id.username_user_edt);
        edtPassword = findViewById(R.id.password_user_edt);
        Button add = findViewById(R.id.add_user);
        add.setOnClickListener(this);
        userHelper = new UserDataBaseHandler(this);
        listUser = userHelper.findAll();
        level.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                levelText = level.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.add_user:
                boolean isEmpty = false;
                String usernameText = edtUsername.getText().toString().trim();
                String passwordText = edtPassword.getText().toString().trim();
                if(TextUtils.isEmpty(usernameText)){
                    edtUsername.setError("Username must filled");
                }
                if(TextUtils.isEmpty(passwordText)){
                    edtPassword.setError("Password must filled");
                }else {
                    try {
                        userHelper.save(new User(usernameText, passwordText, levelText));
                    }catch(SQLiteConstraintException e){
                            Toast.makeText(this, "Username has been taken", Toast.LENGTH_SHORT).show();
                        }
                }
    }
}
}