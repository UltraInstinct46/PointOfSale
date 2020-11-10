package com.example.pointofsale.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.service.autofill.UserData;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.pointofsale.Data.User;
import com.example.pointofsale.DataBase.UserDataBaseHandler;
import com.example.pointofsale.R;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText usernameEdt;
    private EditText passwordEdt;
    private UserDataBaseHandler userHelper;
    private List<User> listUser = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        usernameEdt = findViewById(R.id.username_edt);
        passwordEdt = findViewById(R.id.password_edt);
        Button lgnBtn = findViewById(R.id.login_btn);
        userHelper = new UserDataBaseHandler(this);
        lgnBtn.setOnClickListener(this);
        listUser = userHelper.findAll();
        if(listUser != null){
            userHelper.save(new User("Admin","Admin","Admin"));
            userHelper.save(new User("Kasir","Kasir","Kasir"));
            userHelper.save(new User("Manager","Manager","Manager"));
        }
    }

    @Override
    public void onClick(View v) {
        String username = usernameEdt.getText().toString().trim();
        String password = passwordEdt.getText().toString().trim();
        Boolean res = userHelper.checkuser(username,password);

        if(res==true){
            User level = userHelper.findOne(username);
            Toast.makeText(this, level.getLevel(), Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this,UserActivity.class);
            startActivity(intent);
        }
        else{
            Toast.makeText(this, "Username Or Password is incorrect ", Toast.LENGTH_SHORT).show();
        }
    }
}