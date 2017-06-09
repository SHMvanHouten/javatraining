package com.github.shmvanhouten.lesson7;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ArtistRowMapper implements RowMapper<Artist> {


    @Override
    public Artist mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        Integer id = resultSet.getInt("ArtistId");
        String name = resultSet.getString("name");

        return new Artist(id, name);
    }
}
