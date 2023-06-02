package com.example.demo.models;

public class MessageUI
{
    private int id;
    private int app_id;
    private int user_id;
    private String message;
    private String status;

    public MessageUI() {
    }

    public MessageUI(int id, int app_id, int user_id, String message, String status) {
        this.id = id;
        this.app_id = app_id;
        this.user_id = user_id;
        this.message = message;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getApp_id() {
        return app_id;
    }

    public void setApp_id(int app_id) {
        this.app_id = app_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
