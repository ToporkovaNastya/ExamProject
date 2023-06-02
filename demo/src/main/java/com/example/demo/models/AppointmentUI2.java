package com.example.demo.models;

import java.util.Date;

public class AppointmentUI2
{
    private int id;
    private Date date;
    private String timeValue;
    private String program;
    private int hallNumber;
    private String level;
    private String stAgr;
    private String serviceName;
    private String masterSurname;
    private String masterName;
    private String masterPatronymic;
    private int price;

    public AppointmentUI2() {
    }

    public AppointmentUI2(int id, Date date, String timeValue, String program, int hallNumber,
                          String level, String stAgr, String serviceName, String masterSurname,
                          String masterName, String masterPatronymic, int price) {
        this.id = id;
        this.date = date;
        this.timeValue = timeValue;
        this.program = program;
        this.hallNumber = hallNumber;
        this.level = level;
        this.stAgr = stAgr;
        this.serviceName = serviceName;
        this.masterSurname = masterSurname;
        this.masterName = masterName;
        this.masterPatronymic = masterPatronymic;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getTimeValue() {
        return timeValue;
    }

    public void setTimeValue(String timeValue) {
        this.timeValue = timeValue;
    }

    public int getHallNumber() {
        return hallNumber;
    }

    public void setHallNumber(int hallNumber) {
        this.hallNumber = hallNumber;
    }



    public String getStAgr() {
        return stAgr;
    }

    public void setStAgr(String stAgr) {
        this.stAgr = stAgr;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getMasterSurname() {
        return masterSurname;
    }

    public void setMasterSurname(String masterSurname) {
        this.masterSurname = masterSurname;
    }

    public String getMasterName() {
        return masterName;
    }

    public void setMasterName(String masterName) {
        this.masterName = masterName;
    }

    public String getMasterPatronymic() {
        return masterPatronymic;
    }

    public void setMasterPatronymic(String masterPatronymic) {
        this.masterPatronymic = masterPatronymic;
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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
