package com.semafoor.CDapp.controller;

import com.semafoor.CDapp.dto.ArtistDto;
import com.semafoor.CDapp.model.Artist;
import com.semafoor.CDapp.service.ArtistService;
import com.semafoor.CDapp.transformer.ArtistTransformer;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.sql.Date;
import java.util.Collections;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebAppConfiguration
public class ArtistControllerTest {

    private MockMvc mockMvc;

    @Mock
    private ArtistTransformer transformer;

    @Mock
    private ArtistService service;

    private ArtistController controller;

    private Artist artist;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
        this.controller = new ArtistController(transformer, service);
        this.mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
        String firstName = "John";
        String lastName = "Doe";
        String birthDate = "1980-04-03";
        artist = new Artist(firstName, lastName, Date.valueOf(birthDate));
    }

    @Test
    public void getAllArtists() throws Exception {
        Artist artist = new Artist();
        artist.setFirstName("John");
        artist.setLastName("Smith");
        when(service.getAllArtists()).thenReturn(Collections.singletonList(artist));
        when(transformer.toDto(artist)).thenReturn(new ArtistDto(artist.getFirstName(), artist.getLastName(),
                Date.valueOf("2001-01-02")));

        mockMvc.perform(get("/api/artists"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(artist.getFirstName())));

        verify(this.service, times(1)).getAllArtists();
    }

    @Test
    public void getArtistById() throws Exception {
        Artist artist = new Artist();
        artist.setFirstName("John");
        artist.setLastName("Smith");
        when(service.getArtistById(1)).thenReturn(artist);
        when(transformer.toDto(artist)).thenReturn(new ArtistDto(artist.getFirstName(), artist.getLastName(),
                Date.valueOf("2001-01-02")));

        mockMvc.perform(get("/api/artists/1"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(artist.getFirstName())));

        verify(this.service, times(1)).getArtistById(1);
    }

    @Test
    public void findArtistsByLastName() throws Exception {
        Artist artist = new Artist();
        artist.setFirstName("John");
        artist.setLastName("Smith");
        when(service.getArtistsByLastName("Smith")).thenReturn(Collections.singletonList(artist));
        when(transformer.toDto(artist)).thenReturn(new ArtistDto(artist.getFirstName(), artist.getLastName(),
                Date.valueOf("2001-01-02")));

        mockMvc.perform(get("/api/artists/lastName=Smith"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(artist.getFirstName())));

        verify(this.service, times(1)).getArtistsByLastName("Smith");
    }

    @Test
    public void findArtistByAlbumId() throws Exception {
        when(service.getArtistByAlbumId(2)).thenReturn(artist);
        when(transformer.toDto(artist)).thenReturn(new ArtistDto(artist.getFirstName(), artist.getLastName(),
                Date.valueOf("2001-01-02")));

        mockMvc.perform(get("/api/albums/2/artist"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(artist.getLastName())));

        verify(this.service, times(1)).getArtistByAlbumId(2);
        verify(this.transformer, times(1)).toDto(artist);
    }

    @Test
    public void createArtist() throws Exception {
        String firstName = "John";
        String lastName = "Doe";
        String birthDate = "1980-04-03";
        Artist artist = new Artist(firstName, lastName, Date.valueOf(birthDate));
        when(transformer.toModel(any(ArtistDto.class))).thenReturn(artist);

        mockMvc.perform(post("/api/artists")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\": \"" + firstName + "\" }," +
                        "{\"name\": \"" + lastName + "\" }," +
                        "{\"name\": \"" + birthDate + "\" }"))
                .andExpect(status().isCreated());

        verify(this.service, times(1)).createArtist(artist);
    }

    @Test
    public void updateArtist() throws Exception {
        String firstName = "John";
        String lastName = "Doe";
        String birthDate = "1980-04-03";
        Artist artist = new Artist(firstName, lastName, Date.valueOf(birthDate));
        when(transformer.toModel(any(ArtistDto.class))).thenReturn(artist);

        mockMvc.perform(put("/api/artists/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\": \"" + firstName + "\" }," +
                        "{\"name\": \"" + lastName + "\" }," +
                        "{\"name\": \"" + birthDate + "\" }"))
                .andExpect(status().isCreated());

        verify(this.service, times(1)).updateArtist(1, artist);
    }

    @Test
    public void deleteArtist() throws Exception {
        mockMvc.perform(delete("/api/artists/1"))
                .andExpect(status().isCreated());

        verify(this.service, times(1)).deleteArtistById(1);
    }
}