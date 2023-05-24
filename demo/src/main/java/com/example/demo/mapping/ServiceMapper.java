package com.example.demo.mapping;
import com.example.demo.models.Service;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ServiceMapper implements RowMapper<Service>
{

    @Override
    public Service mapRow(ResultSet rs, int rowNum) throws SQLException {
        var service = new Service();
        service.setId(rs.getInt("id"));
        service.setName(rs.getString("Название"));
        service.setProgram(rs.getString("Программа"));
        service.setLevel(rs.getString("Уровень_сложности"));
        service.setId_master(rs.getInt("id_мастера"));
        service.setDescription(rs.getString("Описание"));
        service.setPrice(rs.getInt("Стоимость"));
        return service;
    }
}
