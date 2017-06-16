package com.github.shmvanhouten.lesson8;

import com.github.shmvanhouten.lesson7.Album;
import com.github.shmvanhouten.lesson7.Artist;

import java.util.List;

public class AlbumService {
    private final AlbumRepository albumRepository;

    public AlbumService(AlbumRepository albumRepository) {
        this.albumRepository = albumRepository;
    }

    public List<Album> getAllAlbumsForArtist(Artist artist){
        return albumRepository.getAllAlbumsForArtist(artist);
    }
}
