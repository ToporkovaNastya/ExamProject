package com.example.demo.models;

public class WorkingTime
{
    private int id;
    private String value;

    public WorkingTime() {
    }

    public WorkingTime(int id, String value) {
        this.id = id;
        this.value = value;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "WorkingTime{" +
                "id=" + id +
                ", value='" + value + '\'' +
                '}';
    }
}
