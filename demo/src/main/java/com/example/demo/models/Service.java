package com.example.demo.models;

public class Service
{
    private int Id;
    private String name;
    private String program;
    private String level;
    private int Id_master;
    private String description;
    private int price;


    public Service() {
    }

    public Service(int id, String name, String program, String level, String description, int price, int id_master) {
        Id = id;
        this.name = name;
        this.program = program;
        this.level = level;
        this.description = description;
        this.price = price;
        Id_master = id_master;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getId_master() {
        return Id_master;
    }

    public void setId_master(int id_master) {
        Id_master = id_master;
    }

    @Override
    public String toString() {
        return "Service{" +
                "Id=" + Id +
                ", name='" + name + '\'' +
                ", program='" + program + '\'' +
                ", level='" + level + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", Id_master=" + Id_master +
                '}';
    }
}
