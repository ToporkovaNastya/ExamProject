package com.example.demo.mapping;

import com.example.demo.models.MasterTime;
import com.example.demo.models.Message;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MessageMapper implements RowMapper<Message>
{
    @Override
    public Message mapRow(ResultSet rs, int rowNum) throws SQLException
    {
        var ms = new Message();
        ms.setId(rs.getInt("id"));
        ms.setApp_id(rs.getInt("id_записи"));
        ms.setUser_id(rs.getInt("id_пользователя"));
        ms.setMessage(rs.getString("message"));
        return ms;
    }
}

