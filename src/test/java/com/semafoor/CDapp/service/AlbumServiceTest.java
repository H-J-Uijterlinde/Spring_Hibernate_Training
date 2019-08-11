package com.semafoor.CDapp.service;

import com.semafoor.CDapp.Repository.AlbumRepository;
import com.semafoor.CDapp.model.Album;
import com.semafoor.CDapp.model.Artist;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

public class AlbumServiceTest {

    @Mock
    private AlbumRepository albumRepository;

    private AlbumService albumService;

    private List<Album> mockAlbums;

    private Album mockAlbum;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
        this.albumService = new AlbumService(albumRepository);
        this.mockAlbums = new ArrayList<>();
        this.mockAlbum = new Album(new Artist("John", "Lennon", Date.valueOf("1970-01-2")),
                "The Beatle", Date.valueOf("2000-01-01"));
        mockAlbums.add(mockAlbum);
    }

    @Test
    public void findAllAlbums() {
        when(this.albumRepository.findAllAlbums()).thenReturn(this.mockAlbums);

        List<Album> albums = this.albumService.findAllAlbums();
        verify(this.albumRepository, times(1)).findAllAlbums();
        assertThat(albums, hasSize(1));
        assertTrue(albums.contains(mockAlbum));
    }

    @Test
    public void findAlbumsByArtistId() {
        when(this.albumRepository.findAlbumsByArtistId(1)).thenReturn(this.mockAlbums);

        List<Album> albums = this.albumService.findAlbumsByArtistId(1);
        verify(this.albumRepository, times(1)).findAlbumsByArtistId(1);
        assertThat(albums, hasSize(1));
        assertTrue(albums.contains(mockAlbum));
    }
}