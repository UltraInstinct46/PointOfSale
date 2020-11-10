package com.example.pointofsale.DataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.pointofsale.Data.Merek;

import java.util.ArrayList;
import java.util.List;

public class MerkDataBaseHandler extends SQLiteOpenHelper{
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "PointOfSale";
    private static final String TABLE_MEREK ="t_merk";
    private static final String KEY_ID = "key_id";
    private static final String KEY_MERK = "key_merk";

    public MerkDataBaseHandler(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        String CREATE_TABLE_MEREK = "CREATE TABLE " + TABLE_MEREK + "("
                + KEY_ID + " INTEGER PRIMARY KEY,"
                + KEY_MERK + " TEXT NOT NULL UNIQUE"+")";
        db.execSQL(CREATE_TABLE_MEREK);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MEREK);
        onCreate(db);
//        String INSERT_TABLE_USER = "INSERT INTO " + TABLE_USER + "("
//                +KEY_USERNAME + ","
//                +KEY_PASSWORD + ","
//                +KEY_LEVEL + ") "
//                +"VALUES ('Admin','Kasir','Manager') , ('Admin','Kasir','Manager') , ('Admin','Kasir','Manager')";
//        db.execSQL(INSERT_TABLE_USER);
    }

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
}
