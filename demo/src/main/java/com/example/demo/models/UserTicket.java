package com.example.demo.models;

public class UserTicket
{
    private int Id;
    private int ticketId;
    private String status;

    public UserTicket() {
    }

    public UserTicket(int id, int ticketId, String status) {
        Id = id;
        this.ticketId = ticketId;
        this.status = status;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public int getTicketId() {
        return ticketId;
    }

    public void setTicketId(int ticketId) {
        this.ticketId = ticketId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "UserTicket{" +
                "Id=" + Id +
                ", ticketId=" + ticketId +
                ", status='" + status + '\'' +
                '}';
    }
}
