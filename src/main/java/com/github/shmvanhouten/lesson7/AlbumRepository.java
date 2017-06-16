package com.github.shmvanhouten.lesson7;

import java.util.ArrayList;
import java.util.List;


public class AlbumRepository {
    public List<Album> getAllAlbumsForArtist(Artist artist){
        List<Album> albums = new ArrayList<>();

        Album album = new Album("Kinderliedjes");
        albums.add(album);
        // do sql queries etc.
        return albums;
    }
}
