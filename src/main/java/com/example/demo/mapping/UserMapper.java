package com.example.demo.mapping;

import com.example.demo.models.User;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;
public class UserMapper implements RowMapper<User> {
    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        var user = new User();
        user.setId(rs.getInt("id"));
        user.setSurname(rs.getString("Фамилия"));
        user.setName(rs.getString("Имя"));
        user.setPatronymic(rs.getString("Отчество"));
        user.setGender(rs.getString("Пол"));
        user.setDate(rs.getString("Дата_рождения"));
        user.setTelephone(rs.getString("Телефон"));
        user.setEmail(rs.getString("E-mail"));
        return user;
    }
}