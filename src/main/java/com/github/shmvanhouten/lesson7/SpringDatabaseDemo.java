package com.github.shmvanhouten.lesson7;


import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

import java.sql.Driver;
import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SpringDatabaseDemo {

    private JdbcTemplate jdbcTemplate;
    private DataSource dataSource;
    private SimpleJdbcInsert insert;

    public SpringDatabaseDemo() {
        try {
            Driver driver = new com.mysql.cj.jdbc.Driver();

            String url = "jdbc:mysql://localhost/Chinook?useSSL=false&useLegacyDatetimeCode=false&serverTimezone=GMT&nullNamePatternMatchesAll=true";
            String user = "root";
            dataSource = new SimpleDriverDataSource(driver, url, user, Password.getPassword());

            jdbcTemplate = new JdbcTemplate(dataSource);

            insert = new SimpleJdbcInsert(dataSource)
                    .withTableName("Artist")
                    .usingColumns("ArtistId", "Name");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private List<Artist> queryArtists() {
        String sql = "SELECT * FROM Artist";
        return jdbcTemplate.query(sql, new ArtistRowMapper());
    }

    private void addNewArtist(String name) {
        Integer newArtistId = getNewArtistId();

        Map<String, Object> values = new HashMap<>();
        values.put("ArtistId", newArtistId);
        values.put("Name", name);

        insert.execute(values);
    }

    private Integer getNewArtistId() {
        String sql = "SELECT MAX(ArtistId) FROM Artist";
        return jdbcTemplate.queryForObject(sql, Integer.class) + 1;
    }

    private void deleteArtist(int artistId) {
        String sql = "DELETE FROM Artist WHERE ArtistId = ?";// to prevent sql injection: prepared statement
        int numberOfDeletedRows = jdbcTemplate.update(sql, artistId);
        System.out.println("Number of deleted rows = " + numberOfDeletedRows);
    }

    public static void main(String[] args) {
        SpringDatabaseDemo demo = new SpringDatabaseDemo();

        demo.addNewArtist("Marco Beelen");
        demo.deleteArtist(278);
        List<Artist> artists = demo.queryArtists();
        for (Artist artist : artists) {
            System.out.println(artist.getArtistId() + " --> " + artist.getName());
        }
    }

}
