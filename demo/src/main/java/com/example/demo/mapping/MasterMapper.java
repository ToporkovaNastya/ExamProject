package com.example.demo.mapping;

import com.example.demo.models.Master;
import com.example.demo.models.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MasterMapper implements RowMapper<Master>
{

    @Override
    public Master mapRow(ResultSet rs, int rowNum) throws SQLException {
        var master = new Master();
        master.setId(rs.getInt("id"));
        master.setSurname(rs.getString("Фамилия"));
        master.setName(rs.getString("Имя"));
        master.setPatronymic(rs.getString("Отчество"));
        master.setDate(rs.getDate("Дата_рождения"));
        master.setGender(String.valueOf(rs.getString("Пол").charAt(0)));
        master.setTelephone(rs.getString("Телефон"));
        master.setGrade(rs.getString("Грейд"));
        master.setExperience(rs.getString("Стаж"));
        master.setPost(rs.getString("Должность"));
        master.setEducation(rs.getString("Образование"));
        master.setDesc(rs.getString("Описание"));
        master.setEmail(rs.getString("Почта"));
        return master;
    }
}
