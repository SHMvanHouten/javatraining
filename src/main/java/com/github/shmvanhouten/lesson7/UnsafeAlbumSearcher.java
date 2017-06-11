package com.github.shmvanhouten.lesson7;
// This class is made as an example to show the danger of not using prepared statements,
// It is dangerously bad code, don't use it.

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UnsafeAlbumSearcher {

    private void startInputStream() {
        InputStream in = System.in;
        InputStreamReader isr = new InputStreamReader(in);
        try (BufferedReader reader = new BufferedReader(isr)) {
            String inputLine = reader.readLine();
            while (inputLine != null) {
                List<String> albums = unsafeGetAlbumsFromArtist(inputLine);
                printList(albums);
                inputLine = reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<String> unsafeGetAlbumsFromArtist(String album) {
        List<String> albums = new ArrayList<>();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            String url = "jdbc:mysql://localhost/Chinook?useSSL=false&useLegacyDatetimeCode=false&serverTimezone=GMT";
            String user = "root";
            Connection connection = DriverManager.getConnection(url, user, Password.getPassword());

            String sql = "SELECT Album.title FROM Album JOIN Artist ON Album.ArtistId = Artist.ArtistId WHERE Artist.Name = '"+
                    album + "'";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    String result = resultSet.getString(1);
                    albums.add(result);
                }
            }
            preparedStatement.close();
            connection.close();

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return albums;
    }

    private void printList(List<String> albums) {
        for (String album : albums) {
            System.out.println(album);
        }
    }

    public static void main(String[] args) {
        UnsafeAlbumSearcher searcher = new UnsafeAlbumSearcher();
        System.out.println("Please type from which artist you would like to see the albums");
        searcher.startInputStream();
    }
}
