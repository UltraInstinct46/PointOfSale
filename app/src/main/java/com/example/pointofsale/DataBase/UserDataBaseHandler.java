package com.example.pointofsale.DataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.pointofsale.Data.User;

import java.util.ArrayList;
import java.util.List;

public class UserDataBaseHandler extends SQLiteOpenHelper{
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "User";
    private static final String TABLE_USER="t_user";
    private static final String KEY_ID = "key_id";
    private static final String KEY_USERNAME = "username";
    private static final String KEY_PASSWORD = "password";
    private static final String KEY_LEVEL = "level";

    public UserDataBaseHandler(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        String CREATE_TABLE_USER= "CREATE TABLE " + TABLE_USER + "("
                + KEY_ID + " INTEGER PRIMARY KEY,"
                + KEY_USERNAME + " TEXT,"
                + KEY_PASSWORD + " TEXT,"
                + KEY_LEVEL + " TEXT" + ")";
        db.execSQL(CREATE_TABLE_USER);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
        onCreate(db);
    }

    public void save(User user){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_USERNAME, user.getUsername());
        values.put(KEY_PASSWORD, user.getPassword());
        values.put(KEY_LEVEL, user.getLevel());
        db.insert(TABLE_USER, null, values);
        db.close();
    }
    public User findOne(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_USER,new String[]{KEY_ID,KEY_USERNAME,KEY_PASSWORD,KEY_LEVEL},
                KEY_ID+"=?", new String[]{String.valueOf(id)},null, null, null);

        if(cursor!=null){
            cursor.moveToFirst();
        }
        return new User(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1),
                cursor.getString(2),
                cursor.getString(3));
    }

    public List<User> findAll(){
        List<User> listUser = new ArrayList<>();
        String query = "SELECT * FROM "+ TABLE_USER;
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery(query, null);

        if(cursor.moveToFirst()){
            do{
                User user = new User();
                user.setId(Integer.valueOf(cursor.getString(0)));
                user.setUsername(cursor.getString(1));
                user.setPassword(cursor.getString(2));
                user.setLevel(cursor.getString(3));
                listUser.add(user);
            }while(cursor.moveToNext());
        }
        return listUser;
    }
    public void update(User user){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_USERNAME, user.getUsername());
        values.put(KEY_PASSWORD, user.getPassword());
        values.put(KEY_LEVEL, user.getLevel());
        db.update(TABLE_USER,values,KEY_ID+"=?", new String[]{String.valueOf(user.getId())});
        db.close();
    }
    public void delete(User user){
        SQLiteDatabase db=this.getWritableDatabase();
        db.delete(TABLE_USER, KEY_ID+"=?", new String[]{String.valueOf(user.getId())});
        db.close();
    }
}
