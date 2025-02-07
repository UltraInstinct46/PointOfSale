package com.example.pointofsale.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.database.CursorIndexOutOfBoundsException;
import android.database.sqlite.SQLiteConstraintException;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.pointofsale.Data.User;
import com.example.pointofsale.DataBase.DataBaseHandler;
import com.example.pointofsale.R;

import java.util.List;

public class UserActivity extends AppCompatActivity implements View.OnClickListener {
    String levelText;
    private EditText edtUsername,edtPassword;
    private DataBaseHandler userHelper;
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
        userHelper = new DataBaseHandler(this);
        listUser = userHelper.findAllUser();
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
                    isEmpty=true;
                }
                if(TextUtils.isEmpty(passwordText)){
                    edtPassword.setError("Password must filled");
                    isEmpty=true;
                }if(isEmpty==false) {
                    User user;
                    try{
                        user = userHelper.findOneUser(usernameText);
                        String checkUser = user.getUsername();
                        if(checkUser!=null) {
                            Toast.makeText(this, "Username has been taken", Toast.LENGTH_SHORT).show();
                        }
                    }catch (CursorIndexOutOfBoundsException e){
                        userHelper.saveUser(new User(usernameText, passwordText, levelText));
                        onBackPressed();
                    }
                }
    }
}
}