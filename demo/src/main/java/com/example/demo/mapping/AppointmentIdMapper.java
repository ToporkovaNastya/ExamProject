package com.example.demo.mapping;

import com.example.demo.models.AppointmentId;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AppointmentIdMapper implements RowMapper<AppointmentId>
{
    @Override
    public AppointmentId mapRow(ResultSet rs, int rowNum) throws SQLException {
        var app = new AppointmentId();
        app.setId(rs.getInt("id_времени"));
        return app;
    }
}
