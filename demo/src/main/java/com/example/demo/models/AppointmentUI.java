package com.example.demo.models;

import java.sql.Date;

public class AppointmentUI
{
    private String name;
    private String program;
    private String level;
    private String masterName;
    private Date date;
    private int hallNumber;

    public AppointmentUI() {
    }

    public AppointmentUI(String name, String program, String level, String masterName, Date date, int hallNumber) {
        this.name = name;
        this.program = program;
        this.level = level;
        this.masterName = masterName;
        this.date = date;
        this.hallNumber = hallNumber;
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

    public String getMasterName() {
        return masterName;
    }

    public void setMasterName(String masterName) {
        this.masterName = masterName;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getHallNumber() {
        return hallNumber;
    }

    public void setHallNumber(int hallNumber) {
        this.hallNumber = hallNumber;
    }
}
