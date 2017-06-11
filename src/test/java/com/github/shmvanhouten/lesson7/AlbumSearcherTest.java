package com.github.shmvanhouten.lesson7;

// This class is made as an example to show the danger of not using prepared statements,
// It is dangerously bad code, don't use it.

import org.junit.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class AlbumSearcherTest {
    @Test
    public void itShouldSearchForAllTheAlbumsByASingleArtist() throws Exception {
        AlbumSearcher searcher = new AlbumSearcher();
        List<Album> albums = searcher.getAlbumsFromArtist("Metallica");
        assertThat(albums.get(0).getTitle(), is("Garage Inc. (Disc 1)"));
    }

    @Test
    public void itShouldAllowASqlInjection() throws Exception {

    }
}