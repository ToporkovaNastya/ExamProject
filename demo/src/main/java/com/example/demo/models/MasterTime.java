package com.example.demo.models;

import java.sql.Date;

public class MasterTime
{
    private Integer id;
    private int master_id;
    private int service_id;
    private Date date;
    private String value;


    public MasterTime() {
    }

    public MasterTime(Integer id, int master_id, int service_id, Date date, String value)
    {
        this.id = id;
        this.master_id = master_id;
        this.service_id = service_id;
        this.date = date;
        this.value = value;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMaster_id() {
        return master_id;
    }

    public void setMaster_id(int master_id) {
        this.master_id = master_id;
    }

    public int getService_id() {
        return service_id;
    }

    public void setService_id(int service_id) {
        this.service_id = service_id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "MasterTime{" +
                "id=" + id +
                ", master_id=" + master_id +
                ", service_id=" + service_id +
                ", date=" + date +
                ", value='" + value + '\'' +
                '}';
    }
}
