package com.example.passwordkeeper.models;

public class Account {
    private long id;
    private String Name;
    private String Login;
    private String Password;
    private String Description;

    public Account(){};

    public Account(long id, String name, String login, String password, String description) {
        this.id = id;
        Name = name;
        Login = login;
        Password = password;
        Description = description;
    }

    public Account(String name, String login, String password, String description) {
        Name = name;
        Login = login;
        Password = password;
        Description = description;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return Name;
    }

    public String getLogin() {
        return Login;
    }

    public String getPassword() {
        return Password;
    }

    public String getDescription() {
        return Description;
    }

}
