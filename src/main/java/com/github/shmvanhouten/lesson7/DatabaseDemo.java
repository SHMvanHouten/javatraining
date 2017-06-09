package com.github.shmvanhouten.lesson7;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseDemo {
    public static void main(String[] args) {
        DatabaseDemo demo = new DatabaseDemo();
        List<Artist> artists = demo.queryArtists();
        for (Artist artist : artists) {
            System.out.println(artist.getArtistId() + " --> " + artist.getName());
        }
    }

    private List<Artist> queryArtists() {

        List<Artist> artists = new ArrayList<>();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            String url = "jdbc:mysql://localhost/Chinook?useSSL=false&useLegacyDatetimeCode=false&serverTimezone=GMT";
            String user = "root";
            Connection connection = DriverManager.getConnection(url, user, Password.getPassword());

            String sql = "SELECT * FROM Artist";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Integer id = resultSet.getInt("ArtistId");
                    String name = resultSet.getString("name");// de String Name is dus niet hoofdletter gevoelig.
                    Artist artist = new Artist(id, name);
                    artists.add(artist);
                }
            }
            preparedStatement.close();//Put this in a try with resources too
            connection.close();//idem

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        return artists;
    }
}
