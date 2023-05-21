package com.example.demo.models;

public class Ticket
{
    private int Id;
    private int Id_service;
    private String days;
    private String content;
    private String type;
    private String comment;

    public Ticket() {
    }

    public Ticket(int id, int id_service, String days, String content, String type, String comment) {
        Id = id;
        Id_service = id_service;
        this.days = days;
        this.content = content;
        this.type = type;
        this.comment = comment;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public int getId_service() {
        return Id_service;
    }

    public void setId_service(int id_service) {
        Id_service = id_service;
    }

    public String getDays() {
        return days;
    }

    public void setDays(String days) {
        this.days = days;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "Id=" + Id +
                ", Id_service=" + Id_service +
                ", days='" + days + '\'' +
                ", content='" + content + '\'' +
                ", type='" + type + '\'' +
                ", comment='" + comment + '\'' +
                '}';
    }
}
