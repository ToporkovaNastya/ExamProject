package com.example.demo.mapping;

import com.example.demo.models.Role;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RoleMapper implements RowMapper<Role>
{
    @Override
    public Role mapRow(ResultSet rs, int rowNum) throws SQLException {
        var role = new Role();
        role.setId(rs.getInt("id"));
        role.setName(rs.getString("Название"));
        return role;
    }
}
