package com.github.shmvanhouten.lesson7;

import org.junit.Test;

import java.util.ArrayList;
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
        UnsafeAlbumSearcher searcher = new UnsafeAlbumSearcher();
        List<String> albums = searcher.unsafeGetAlbumsFromArtist("Metallica");
        assertThat(albums.get(0), is("Garage Inc. (Disc 1)"));
        albums = searcher.unsafeGetAlbumsFromArtist("Metallica' UNION(SELECT lastName FROM Customer);-- -");
        printList(albums);
    }

    private void printList(List<String> albums) {
        for (String album : albums) {
            System.out.println(album);
        }
    }
}