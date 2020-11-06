package com.example.pointofsale.Data;

public class Barang {
    private int id;
    private String nama_barang;
    private int kd_merek;
    private int kd_distributor;
    private String tanggal_masuk;
    private int harga_barang;
    private int stok_barang;
    private String keterangan;

    public Barang(){}
    public Barang(String nama_barang, int kd_merek, int kd_distributor, String tanggal_masuk, int harga_barang, int stok_barang, String keterangan){
        this.nama_barang=nama_barang;
        this.kd_merek=kd_merek;
        this.kd_distributor=kd_distributor;
        this.tanggal_masuk=tanggal_masuk;
        this.harga_barang=harga_barang;
        this.stok_barang=stok_barang;
        this.keterangan=keterangan;
    }
    public Barang(int id, String nama_barang, int kd_merek, int kd_distributor, String tanggal_masuk, int harga_barang, int stok_barang, String keterangan){
        this.id=id;
        this.nama_barang=nama_barang;
        this.kd_merek=kd_merek;
        this.kd_distributor=kd_distributor;
        this.tanggal_masuk=tanggal_masuk;
        this.harga_barang=harga_barang;
        this.stok_barang=stok_barang;
        this.keterangan=keterangan;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNama_barang() {
        return nama_barang;
    }

    public void setNama_barang(String nama_barang) {
        this.nama_barang = nama_barang;
    }

    public int getKd_merek() {
        return kd_merek;
    }

    public void setKd_merek(int kd_merek) {
        this.kd_merek = kd_merek;
    }

    public int getKd_distributor() {
        return kd_distributor;
    }

    public void setKd_distributor(int kd_distributor) {
        this.kd_distributor = kd_distributor;
    }

    public String getTanggal_masuk() {
        return tanggal_masuk;
    }

    public void setTanggal_masuk(String tanggal_masuk) {
        this.tanggal_masuk = tanggal_masuk;
    }

    public int getHarga_barang() {
        return harga_barang;
    }

    public void setHarga_barang(int harga_barang) {
        this.harga_barang = harga_barang;
    }

    public int getStok_barang() {
        return stok_barang;
    }

    public void setStok_barang(int stok_barang) {
        this.stok_barang = stok_barang;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }
}
