package com.github.shmvanhouten.lesson7;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AlbumRowMapper implements RowMapper<Album> {

    @Override
    public Album mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        String title = resultSet.getString("title");
        return new Album(title);
    }
}
