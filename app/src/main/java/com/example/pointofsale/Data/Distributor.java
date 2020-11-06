package com.example.pointofsale.Data;

public class Distributor {
    private int id;
    private String nama_distributor;
    private String alamat;
    private String no_telp;


    public Distributor(){}
    public Distributor(String nama_distributor, String alamat, String no_telp){
        this.nama_distributor = nama_distributor;
        this.alamat = alamat;
        this.no_telp = no_telp;
    }
    public Distributor(int id,String nama_distributor, String alamat, String no_telp){
        this.id = id;
        this.nama_distributor = nama_distributor;
        this.alamat = alamat;
        this.no_telp = no_telp;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNama_distributor() {
        return nama_distributor;
    }

    public void setNama_distributor(String nama_distributor) {
        this.nama_distributor = nama_distributor;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getNo_telp() {
        return no_telp;
    }

    public void setNo_telp(String no_telp) {
        this.no_telp = no_telp;
    }
}
