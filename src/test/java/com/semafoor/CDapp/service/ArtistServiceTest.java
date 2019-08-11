package com.semafoor.CDapp.service;

import com.semafoor.CDapp.Repository.ArtistRepository;
import com.semafoor.CDapp.model.Artist;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

public class ArtistServiceTest {

    @Mock
    private ArtistRepository artistRepository;

    private ArtistService artistService;

    private List<Artist> mockArtists;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
        this.artistService = new ArtistService(this.artistRepository);
        this.mockArtists = new ArrayList<>();
        this.mockArtists.add(new Artist("John", "Lennon", Date.valueOf("1970-06-07")));
    }

    @Test
    public void getAllArtists() {
        when(this.artistRepository.getArtists()).thenReturn(this.mockArtists);

        List<Artist> artists = this.artistService.getAllArtists();
        verify(this.artistRepository, times(1)).getArtists();
        assertThat(artists, hasSize(1));
    }

    @Test
    public void getArtistById() {
        when(this.artistRepository.getArtistById(1)).thenReturn(this.mockArtists.get(0));
        Artist artist = this.artistService.getArtistById(1);
        verify(this.artistRepository, times(1)).getArtistById(1);
        assertSame(this.mockArtists.get(0), artist);
    }

    @Test
    public void getArtistByLastName() {
        when(this.artistRepository.getArtistsByLastName("Lennon")).thenReturn(this.mockArtists);
        List<Artist> artists = this.artistService.getArtistsByLastName("Lennon");
        verify(this.artistRepository, times(1)).getArtistsByLastName("Lennon");
        assertEquals(artists.size(), 1);
    }

    @Test
    public void getArtistByAlbumId() {
        when(this.artistRepository.getArtistByAlbumId(2)).thenReturn(this.mockArtists.get(0));
        Artist artist = this.artistService.getArtistByAlbumId(2);
        verify(this.artistRepository, times(1)).getArtistByAlbumId(2);
        assertSame(this.mockArtists.get(0), artist);
    }

    @Test
    public void createArtist() {
        Artist artist = new Artist("Sam", "Smith", Date.valueOf("1980-06-07"));
        when(this.artistRepository.createArtist(artist)).thenReturn(mockArtists.add(artist));
        boolean succes = artistService.createArtist(artist);
        verify(this.artistRepository, times(1)).createArtist(artist);
        assertTrue(succes);
        assertTrue(this.mockArtists.contains(artist));
    }

    @Test
    public void updateArtist() {
        Artist artist = new Artist("Sam", "Smith", Date.valueOf("1980-06-07"));
        when(this.artistRepository.updateArtist(1, artist)).thenReturn(1);

        int i = this.artistService.updateArtist(1, artist);
        verify(this.artistRepository, times(1)).updateArtist(1, artist);
        assertEquals(1, i);
    }

    @Test
    public void deleteArtistById() {
        when(this.artistRepository.deleteArtistById(1)).thenReturn(1);

        int i = this.artistService.deleteArtistById(1);
        verify(this.artistRepository, times(1)).deleteArtistById(1);
        assertEquals(1, i);
    }
}