package com.example.demo.mapping;

import com.example.demo.models.Registration;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RegistrationMapper implements RowMapper<Registration>
{
    @Override
    public Registration mapRow(ResultSet rs, int rowNum) throws SQLException {
        var reg = new Registration();
        reg.setId(rs.getInt("id"));
        reg.setLogin(rs.getString("login"));
        reg.setPassword(rs.getString("password"));
        reg.setId_role(rs.getInt("id_role"));
        return reg;
    }
}
