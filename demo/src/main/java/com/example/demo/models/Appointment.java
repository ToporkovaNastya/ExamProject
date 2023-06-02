package com.example.demo.models;

import java.util.Date;

public class Appointment {
    private int id;
    private int id_service;
    private int id_master;
    private int id_user;
    private int id_time;
    private Date date;
    private int hallNumber;
    private String stDone;
    private String stAgr;


    public Appointment() {
    }

    public Appointment(int id, int id_service, int id_master, int id_user,
                       int id_time, Date date, int hallNumber, String stDone, String stAgr) {
        this.id = id;
        this.id_service = id_service;
        this.id_master = id_master;
        this.id_user = id_user;
        this.id_time = id_time;
        this.date = date;
        this.hallNumber = hallNumber;
        this.stDone = stDone;
        this.stAgr = stAgr;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_service() {
        return id_service;
    }

    public void setId_service(int id_service) {
        this.id_service = id_service;
    }

    public int getId_master() {
        return id_master;
    }

    public void setId_master(int id_master) {
        this.id_master = id_master;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public int getId_time() {
        return id_time;
    }

    public void setId_time(int id_time) {
        this.id_time = id_time;
    }

    public java.sql.Date getDate() {
        return (java.sql.Date) date;
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

    public String getStDone() {
        return stDone;
    }

    public void setStDone(String stDone) {
        this.stDone = stDone;
    }

    public String getStAgr() {
        return stAgr;
    }

    public void setStAgr(String stAgr) {
        this.stAgr = stAgr;
    }

    @Override
    public String toString() {
        return "Appointment{" +
                "id=" + id +
                ", id_service=" + id_service +
                ", id_master=" + id_master +
                ", id_user=" + id_user +
                ", id_time=" + id_time +
                ", date=" + date +
                ", hallNumber=" + hallNumber +
                ", stDone='" + stDone + '\'' +
                ", stAgr='" + stAgr + '\'' +
                '}';
    }
}