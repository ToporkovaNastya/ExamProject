package com.example.demo.models;

import java.sql.Date;

public class AppointmentUI
{
    private int id;
    private String name;
    private String program;
    private String level;
    private int master_id;
    private String masterName;
    private Date date;
    private int hallNumber;
    private int id_time;

    public AppointmentUI() {
    }


    public AppointmentUI(int id,String name, String program, String level,
                         int master_id, String masterName, Date date, int hallNumber, int id_time)
    {
        this.id = id;
        this.name = name;
        this.program = program;
        this.level = level;
        this.master_id = master_id;
        this.masterName = masterName;
        this.date = date;
        this.hallNumber = hallNumber;
        this.id_time = id_time;
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

    public int getMaster_id() {
        return master_id;
    }

    public void setMaster_id(int master_id) {
        this.master_id = master_id;
    }

    public int getId_time() {
        return id_time;
    }

    public void setId_time(int id_time) {
        this.id_time = id_time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
