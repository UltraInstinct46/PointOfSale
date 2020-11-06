package com.example.pointofsale.Data;

public class Transaction {
    private int id;
    private int kd_barang;
    private int kd_user;
    private String jumlah_beli;
    private String total_harga;
    private String date;

    public Transaction(){}
    public Transaction(int kd_barang, int kd_user, String jumlah_beli, String total_harga, String date){
        this.kd_barang= kd_barang;
        this.kd_user=kd_user;
        this.jumlah_beli=jumlah_beli;
        this.total_harga=total_harga;
        this.date=date;
    }
    public Transaction(int id, int kd_barang, int kd_user, String jumlah_beli, String total_harga, String date){
        this.id = id;
        this.kd_barang= kd_barang;
        this.kd_user=kd_user;
        this.jumlah_beli=jumlah_beli;
        this.total_harga=total_harga;
        this.date=date;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getKd_barang() {
        return kd_barang;
    }

    public void setKd_barang(int kd_barang) {
        this.kd_barang = kd_barang;
    }

    public int getKd_user() {
        return kd_user;
    }

    public void setKd_user(int kd_user) {
        this.kd_user = kd_user;
    }

    public String getJumlah_beli() {
        return jumlah_beli;
    }

    public void setJumlah_beli(String jumlah_beli) {
        this.jumlah_beli = jumlah_beli;
    }

    public String getTotal_harga() {
        return total_harga;
    }

    public void setTotal_harga(String total_harga) {
        this.total_harga = total_harga;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
