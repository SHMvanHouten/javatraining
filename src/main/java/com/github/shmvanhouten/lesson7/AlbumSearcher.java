package com.github.shmvanhouten.lesson7;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

import javax.sql.DataSource;
import java.sql.Driver;
import java.sql.SQLException;
import java.util.List;


public class AlbumSearcher {

    private JdbcTemplate jdbcTemplate;
    private DataSource dataSource;

    public AlbumSearcher(){
        try {
            Driver driver = new com.mysql.cj.jdbc.Driver();

            String url = "jdbc:mysql://localhost/Chinook?useSSL=false&useLegacyDatetimeCode=false&serverTimezone=GMT&nullNamePatternMatchesAll=true";
            String user = "root";
            dataSource = new SimpleDriverDataSource(driver, url, user, Password.getPassword());

            jdbcTemplate = new JdbcTemplate(dataSource);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public List<Album> getAlbumsFromArtist(String artist) {
        String sql = "SELECT Album.title FROM Album JOIN Artist ON Album.ArtistId = Artist.ArtistId WHERE Artist.Name = ?";
        return jdbcTemplate.query(sql, new AlbumRowMapper(), artist);
    }
}
