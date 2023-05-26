package com.example.demo.mapping;

import com.example.demo.models.Appointment;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AppointmentMapper implements RowMapper<Appointment>
{
    @Override
    public Appointment mapRow(ResultSet rs, int rowNum) throws SQLException
    {
        var app = new Appointment();
        app.setId(rs.getInt("id"));
        app.setId_service(rs.getInt("id_услуги"));
        app.setId_master(rs.getInt("id_мастера"));
        app.setDate(rs.getDate("Дата"));
        app.setId_time(rs.getInt("id_времени"));
        app.setId_user(rs.getInt("id_пользователя"));
        app.setHallNumber(rs.getInt("Номер_зала"));
        return app;
    }
}
