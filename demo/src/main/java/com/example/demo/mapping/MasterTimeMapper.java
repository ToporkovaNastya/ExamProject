package com.example.demo.mapping;

import com.example.demo.models.Admin;
import com.example.demo.models.MasterTime;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MasterTimeMapper implements RowMapper<MasterTime>
{
    @Override
    public MasterTime mapRow(ResultSet rs, int rowNum) throws SQLException
    {
        var mt = new MasterTime();
        mt.setId(rs.getInt("id"));
        mt.setMaster_id(rs.getInt("id_мастера"));
        mt.setService_id(rs.getInt("id_услуги"));
        mt.setDate(rs.getDate("Дата"));
        mt.setValue(rs.getString("value"));
        return mt;
    }
}
