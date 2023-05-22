package com.example.demo.models;

public class Registration
{
    private int Id;
    private String login;
    private String password;
    private int Id_role;


    public Registration()
    {
    }

    public Registration(int id, String login, String password, int Id_role) {
        Id = id;
        this.login = login;
        this.password = password;
        this.Id_role = Id_role;
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
        Id = id;
    }

    public int getId_role() {
        return Id_role;
    }

    public void setId_role(int id_role) {
        Id_role = id_role;
    }

    @Override
    public String toString() {
        return "Registration{" +
                "Id=" + Id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", Id_role=" + Id_role +
                '}';
    }
}

