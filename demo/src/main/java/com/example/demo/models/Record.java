package com.example.demo.models;

public class Record
{
    private int Id;
    private String name;
    private String program;
    private String level;
    private int Id_master;
    private String date;
    private int hallNumber;

    public Record() {
    }

    public Record(int id, String name, String program, String level, int id_master, String date, int hallNumber) {
        Id = id;
        this.name = name;
        this.program = program;
        this.level = level;
        Id_master = id_master;
        this.date = date;
        this.hallNumber = hallNumber;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        this.Id = id;
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

    public int getId_master() {
        return Id_master;
    }

    public void setId_master(int id_master) {
        this.Id_master = id_master;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getHallNumber() {
        return hallNumber;
    }

    public void setHallNumber(int hallNumber) {
        this.hallNumber = hallNumber;
    }

    @Override
    public String toString() {
        return "Record{" +
                "Id=" + Id +
                ", name='" + name + '\'' +
                ", program='" + program + '\'' +
                ", level='" + level + '\'' +
                ", Id_master='" + Id_master + '\'' +
                ", date='" + date + '\'' +
                ", hallNumber=" + hallNumber +
                '}';
    }
}
