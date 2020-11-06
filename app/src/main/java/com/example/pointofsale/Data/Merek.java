package com.example.pointofsale.Data;

public class Merek {
    private int id;
    private String merek;

    public Merek(){}
    public Merek(String merek){
        this.merek = merek;
    }
    public Merek(int id, String merek){
        this.id = id;
        this.merek = merek;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMerek() {
        return merek;
    }

    public void setMerek(String merek) {
        this.merek = merek;
    }
}
