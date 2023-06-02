package com.example.demo.models;

public class ServiceUI2
{
    private int Id;
    private String name;
    private String program;
    private String level;
    private int Id_master;
    private String surname;
    private String masterName;
    private String patronymic;
    private String masterGrade;
    private String description;
    private int price;

    public ServiceUI2() {
    }

    public ServiceUI2(int id, String name, String program, String level, int id_master,
                      String description, int price) {
        Id = id;
        this.name = name;
        this.program = program;
        this.level = level;
        Id_master = id_master;
        this.description = description;
        this.price = price;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProgram() {
        return program;
    }

    public void setProgram(String program) {
        this.program = program;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public int getId_master() {
        return Id_master;
    }

    public void setId_master(int id_master) {
        Id_master = id_master;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getMasterName() {
        return masterName;
    }

    public void setMasterName(String masterName) {
        this.masterName = masterName;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public String getMasterGrade() {
        return masterGrade;
    }

    public void setMasterGrade(String masterGrade) {
        this.masterGrade = masterGrade;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
