package com.example.demo.models;

public class Registration
{
    private int Id;
    private String login;
    private String password;
    private int Id_role;
    private int login_In;


    public Registration()
    {
    }

    public Registration(int id, String login, String password, int id_role, int login_In) {
        this.Id = id;
        this.login = login;
        this.password = password;
        this.Id_role = id_role;
        this.login_In = login_In;
    }

    public int getLogin_In() {
        return login_In;
    }
    public void setLogin_In(int login_In) {
        this.login_In = login_In;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        this.Id = id;
    }

    public int getId_role() {
        return Id_role;
    }

    public void setId_role(int id_role) {
        this.Id_role = id_role;
    }

    @Override
    public String toString() {
        return "Registration{" +
                "Id=" + Id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", Id_role=" + Id_role +
                ", login_In=" + login_In +
                '}';
    }
}

