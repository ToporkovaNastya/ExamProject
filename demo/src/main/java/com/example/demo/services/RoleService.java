package com.example.demo.services;

import com.example.demo.configs.SqlServerJdbcConfig;
import com.example.demo.mapping.RoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class RoleService
{
    @Autowired
    private SqlServerJdbcConfig connection;
    public void getRoleName(String name){
        var jdbc = new JdbcTemplate(connection.mysqlDataSource());
        var role = jdbc.queryForObject("SELECT * FROM [Role] WHERE [Role].[name] = "+name+";",  new RoleMapper());
        System.out.println(role.getName());
    }
    public void getRoleId(int id){
        var jdbc = new JdbcTemplate(connection.mysqlDataSource());
        var role = jdbc.queryForObject("SELECT * FROM [Role] WHERE [Role].[id] = "+id+";",  new RoleMapper());
        System.out.println(role.getId());
    }
}
