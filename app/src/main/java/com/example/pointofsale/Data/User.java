package com.example.pointofsale.Data;

public class User {
    private int id;
    private String username;
    private String password;
    private String level;


    public User(){}
    public User(String username, String password, String level){
        this.username = username;
        this.password=password;
        this.level=level;
    }
    public User(int id, String username, String password, String level){
        this.id=id;
        this.username = username;
        this.password = password;
        this.level = level;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }
}
