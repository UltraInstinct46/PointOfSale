package com.example.pointofsale.DataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.pointofsale.Data.Distributor;
import com.example.pointofsale.Data.Merek;
import com.example.pointofsale.Data.User;

import java.util.ArrayList;
import java.util.List;

public class DataBaseHandler extends SQLiteOpenHelper{
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "PointOfSale";
    private static final String TABLE_USER ="t_user";
    private static final String KEY_ID = "key_id";
    private static final String KEY_USERNAME = "username";
    private static final String KEY_PASSWORD = "password";
    private static final String KEY_LEVEL = "level";

    //TABLE MEREK
    private static final String TABLE_MEREK ="t_merk";
    private static final String KEY_MERK = "key_merk";

    //TABLE DISTRIBUTOR
    private static final String TABLE_DISTRIBUTOR ="t_merk";
    private static final String KEY_NAMA_DISTRIBUTOR = "key_nama_distributor";
    private static final String KEY_ALAMAT ="key_alamat";
    private static final String KEY_NO_TELP ="key_no_telp";
    public DataBaseHandler(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        String CREATE_TABLE_USER= "CREATE TABLE " + TABLE_USER + "("
                + KEY_ID + " INTEGER PRIMARY KEY,"
                + KEY_USERNAME + " TEXT NOT NULL UNIQUE,"
                + KEY_PASSWORD + " TEXT,"
                + KEY_LEVEL + " TEXT" + ")";

        //TABLE MEREK
        String CREATE_TABLE_MEREK = "CREATE TABLE " + TABLE_MEREK + "("
                + KEY_ID + " INTEGER PRIMARY KEY,"
                + KEY_MERK + " TEXT NOT NULL UNIQUE"+")";

        //TABLE DISTRIBUTOR
        String CREATE_TABLE_DISTRIBUTOR = "CREATE TABLE " + TABLE_DISTRIBUTOR + "("
                + KEY_ID + " INTEGER PRIMARY KEY,"
                + KEY_NAMA_DISTRIBUTOR + " TEXT NOT NULL UNIQUE,"
                + KEY_ALAMAT + " TEXT,"
                + KEY_NO_TELP + " TEXT"+")";
        db.execSQL(CREATE_TABLE_USER);
        db.execSQL(CREATE_TABLE_MEREK);
        db.execSQL(CREATE_TABLE_DISTRIBUTOR);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MEREK);
        onCreate(db);
//        String INSERT_TABLE_USER = "INSERT INTO " + TABLE_USER + "("
//                +KEY_USERNAME + ","
//                +KEY_PASSWORD + ","
//                +KEY_LEVEL + ") "
//                +"VALUES ('Admin','Kasir','Manager') , ('Admin','Kasir','Manager') , ('Admin','Kasir','Manager')";
//        db.execSQL(INSERT_TABLE_USER);
    }

    public void saveUser(User user){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_USERNAME, user.getUsername());
        values.put(KEY_PASSWORD, user.getPassword());
        values.put(KEY_LEVEL, user.getLevel());
        db.insert(TABLE_USER, null, values);
        db.close();
    }
    public User findOneUser(String username){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_USER,new String[]{KEY_ID,KEY_USERNAME,KEY_PASSWORD,KEY_LEVEL},
                KEY_USERNAME+"=?", new String[]{String.valueOf(username)},null, null, null);

        if(cursor!=null){
            cursor.moveToFirst();
        }
        return new User(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1),
                cursor.getString(2),
                cursor.getString(3));
    }

    public List<User> findAllUser(){
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
    public void updateUser(User user){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_USERNAME, user.getUsername());
        values.put(KEY_PASSWORD, user.getPassword());
        values.put(KEY_LEVEL, user.getLevel());
        db.update(TABLE_USER,values,KEY_ID+"=?", new String[]{String.valueOf(user.getId())});
        db.close();
    }
    public void deleteUser(User user){
        SQLiteDatabase db=this.getWritableDatabase();
        db.delete(TABLE_USER, KEY_ID+"=?", new String[]{String.valueOf(user.getId())});
        db.close();
    }
    public boolean checkuser(String username, String password) {
        String[] columns = { KEY_ID} ;
        SQLiteDatabase db = getReadableDatabase();
        String selection = KEY_USERNAME + "=?" + " and " + KEY_PASSWORD + "=?";
        String[] selectionArgs = { username, password };
        Cursor cursor = db.query(TABLE_USER, columns, selection, selectionArgs, null, null, null);
        int count = cursor.getCount();
        cursor.close();
        db.close();


        if (count>0)
            return true;
        else
            return false;
    }



    //Table Merek
    public void saveMerek(Merek merek){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_MERK, merek.getMerek());
        db.insert(TABLE_MEREK, null, values);
        db.close();
    }
    public Merek findOneMerek(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_MEREK,new String[]{KEY_ID,KEY_MERK},
                KEY_ID+"=?", new String[]{String.valueOf(id)},null, null, null);

        if(cursor!=null){
            cursor.moveToFirst();
        }
        return new Merek(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1));
    }

    public List<Merek> findAllMerek(){
        List<Merek> listMerek = new ArrayList<>();
        String query = "SELECT * FROM "+ TABLE_MEREK;
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery(query, null);

        if(cursor.moveToFirst()){
            do{
                Merek merek = new Merek();
                merek.setId(Integer.valueOf(cursor.getString(0)));
                merek.setMerek(cursor.getString(1));
                listMerek.add(merek);
            }while(cursor.moveToNext());
        }
        return listMerek;
    }
    public void updateMerek(Merek merek){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_MERK, merek.getMerek());
        db.update(TABLE_MEREK,values,KEY_ID+"=?", new String[]{String.valueOf(merek.getId())});
        db.close();
    }
    public void deleteMerek(Merek merek){
        SQLiteDatabase db=this.getWritableDatabase();
        db.delete(TABLE_MEREK, KEY_ID+"=?", new String[]{String.valueOf(merek.getId())});
        db.close();
    }


    //TABLE DISTRIBUTOR
    public void saveDistributor(Distributor distributor){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAMA_DISTRIBUTOR, distributor.getNama_distributor());
        values.put(KEY_ALAMAT, distributor.getAlamat());
        values.put(KEY_NO_TELP, distributor.getNo_telp());
        db.insert(TABLE_DISTRIBUTOR, null, values);
        db.close();
    }
    public Distributor findOneDistributor(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_DISTRIBUTOR,new String[]{KEY_ID,KEY_NAMA_DISTRIBUTOR,KEY_ALAMAT,KEY_NO_TELP},
                KEY_ID+"=?", new String[]{String.valueOf(id)},null, null, null);

        if(cursor!=null){
            cursor.moveToFirst();
        }
        return new Distributor(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1),
                cursor.getString(2),
                cursor.getString(3));
    }

    public List<Distributor> findAllDistributor(){
        List<Distributor> listDistributor = new ArrayList<>();
        String query = "SELECT * FROM "+ TABLE_DISTRIBUTOR;
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery(query, null);

        if(cursor.moveToFirst()){
            do{
                Distributor distributor = new Distributor();
                distributor.setId(Integer.valueOf(cursor.getString(0)));
                distributor.setNama_distributor(cursor.getString(1));
                distributor.setNama_distributor(cursor.getString(2));
                distributor.setNama_distributor(cursor.getString(3));
                listDistributor.add(distributor);
            }while(cursor.moveToNext());
        }
        return listDistributor;
    }
    public void updateDistributor(Distributor distributor){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAMA_DISTRIBUTOR, distributor.getNama_distributor());
        values.put(KEY_ALAMAT, distributor.getAlamat());
        values.put(KEY_NO_TELP, distributor.getNo_telp());
        db.update(TABLE_DISTRIBUTOR,values,KEY_ID+"=?", new String[]{String.valueOf(distributor.getId())});
        db.close();
    }
    public void deleteDistributor(Distributor distributor){
        SQLiteDatabase db=this.getWritableDatabase();
        db.delete(TABLE_DISTRIBUTOR, KEY_ID+"=?", new String[]{String.valueOf(distributor.getId())});
        db.close();
    }
}
