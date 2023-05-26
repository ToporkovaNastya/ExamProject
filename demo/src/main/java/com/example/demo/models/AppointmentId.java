package com.example.demo.models;

public class AppointmentId
{
    private int id;


    public AppointmentId() {
    }

    public AppointmentId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "AppointmentId{" +
                "id=" + id +
                '}';
    }
}
