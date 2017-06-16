package com.github.shmvanhouten.lesson8;

import com.github.shmvanhouten.lesson7.Artist;

public class ArtistService {
    public Artist getArtistByName(String artistName) {
        return new Artist(1, "John Doe");
    }
}
