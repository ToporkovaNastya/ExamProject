package com.example.demo.mapping;

import com.example.demo.models.Admin;
import com.example.demo.models.Registration;
import com.example.demo.models.Role;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminMapper implements RowMapper<Admin>
{
    @Override
    public Admin mapRow(ResultSet rs, int rowNum) throws SQLException
    {
        var admin = new Admin();
        admin.setId(rs.getInt("id"));
        admin.setSurname(rs.getString("Фамилия"));
        admin.setName(rs.getString("Имя"));
        admin.setPatronymic(rs.getString("Отчество"));
        admin.setRegion(rs.getString("Регион"));
        admin.setDate(rs.getString("Дата_рождения"));
        admin.setEmail(rs.getString("E-mail"));
        return admin;
    }
}
