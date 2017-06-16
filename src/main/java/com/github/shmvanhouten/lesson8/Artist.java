package com.github.shmvanhouten.lesson8;


public class Artist {
    private final Integer artistId;
    private final String name;

    public Artist(Integer artistId, String name){
        this.artistId = artistId;
        this.name = name;
    }

    public Integer getArtistId() {
        return artistId;
    }

    public String getName() {
        return name;
    }
}
