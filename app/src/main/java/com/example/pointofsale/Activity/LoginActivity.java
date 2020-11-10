package com.example.pointofsale.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.pointofsale.Data.User;
import com.example.pointofsale.DataBase.DataBaseHandler;
import com.example.pointofsale.R;

import java.util.ArrayList;
import java.util.List;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText usernameEdt;
    private EditText passwordEdt;
    private DataBaseHandler userHelper;

    private List<User> listUser = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        usernameEdt = findViewById(R.id.username_edt);
        passwordEdt = findViewById(R.id.password_edt);
        Button lgnBtn = findViewById(R.id.login_btn);
        userHelper = new DataBaseHandler(this);
        lgnBtn.setOnClickListener(this);
        listUser = userHelper.findAllUser();
        if(listUser != null){
            userHelper.saveUser(new User("Admin","Admin","Admin"));
            userHelper.saveUser(new User("Kasir","Kasir","Kasir"));
            userHelper.saveUser(new User("Manager","Manager","Manager"));
        }
    }

    @Override
    public void onClick(View v) {
        String username = usernameEdt.getText().toString().trim();
        String password = passwordEdt.getText().toString().trim();
        boolean isEmpty = false;
        boolean res = userHelper.checkuser(username, password);
        String levelText;
        Intent intent;
        if (TextUtils.isEmpty(username)) {
            usernameEdt.setError("This field must filled");
            isEmpty = true;
        }
        if (TextUtils.isEmpty(password)) {
            passwordEdt.setError("This field must filled");
            isEmpty = true;
        }
        if (isEmpty == false) {
            if (res == true) {
                User level = userHelper.findOneUser(username);
                levelText = level.getLevel();
                switch (levelText) {
                    case "Admin":
                        intent = new Intent(this, HomeActivity.class);
                        intent.putExtra(HomeActivity.EXTRA_LEVEL,"Admin");
                        startActivity(intent);
                        break;
                    case "Kasir":
                        intent = new Intent(this, HomeActivity.class);
                        intent.putExtra(HomeActivity.EXTRA_LEVEL,"Kasir");
                        startActivity(intent);
                        break;
                    case "Manager":
                        intent = new Intent(this, HomeActivity.class);
                        intent.putExtra(HomeActivity.EXTRA_LEVEL,"Manager");
                        startActivity(intent);
                        break;
                }
            } else {
                Toast.makeText(this, "Username or Password is incorrect ", Toast.LENGTH_SHORT).show();
            }
        }
    }
}