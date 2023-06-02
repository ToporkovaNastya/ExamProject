package com.example.demo.models;

public class Message
{
    private int id;
    private int app_id;
    private int user_id;
    private String message;

    public Message() {
    }

    public Message(int id, int app_id, int user_id, String message) {
        this.id = id;
        this.app_id = app_id;
        this.user_id = user_id;
        this.message = message;
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

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", app_id=" + app_id +
                ", user_id=" + user_id +
                ", message='" + message + '\'' +
                '}';
    }
}
