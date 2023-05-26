package com.example.demo.models;

public class WorkingTimeUI
{
    private int id_hour;
    private String value;
    private boolean disabled;

    public WorkingTimeUI() {
    }

    public WorkingTimeUI(int id_hour, String value, boolean disabled) {
        this.id_hour = id_hour;
        this.value = value;
        this.disabled = disabled;
    }
    public WorkingTimeUI(int id_hour, String value) {
        this.id_hour = id_hour;
        this.value = value;
    }
    public int getId_hour() {
        return id_hour;
    }

    public void setId_hour(int id_hour) {
        this.id_hour = id_hour;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public boolean isDisabled() {
        return disabled;
    }

    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
    }
}
