package com.example.demo.mapping;

import com.example.demo.models.Appointment;
import com.example.demo.models.WorkingTime;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class WorkingTimeMapper implements RowMapper<WorkingTime>
{
    @Override
    public WorkingTime mapRow(ResultSet rs, int rowNum) throws SQLException
    {
        var wt = new WorkingTime();
        wt.setId(rs.getInt("id"));
        wt.setValue(rs.getString("value"));
        return wt;
    }
}
