package com.semafoor.CDapp.controller;

import com.semafoor.CDapp.dto.AlbumDto;
import com.semafoor.CDapp.model.Album;
import com.semafoor.CDapp.model.Artist;
import com.semafoor.CDapp.service.AlbumService;
import com.semafoor.CDapp.transformer.AlbumTransformer;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.sql.Date;
import java.util.Collections;

import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class AlbumControllerTest {

    private MockMvc mockMvc;

    @Mock
    private AlbumTransformer transformer;

    @Mock
    private AlbumService service;

    private AlbumController controller;

    private Album mockAlbum;
    private AlbumDto mockDto;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
        this.controller = new AlbumController(transformer, service);
        this.mockMvc = MockMvcBuilders.standaloneSetup(controller).build();

        this.mockAlbum = new Album(new Artist("John", "Lennon", Date.valueOf("1970-01-2")),
                "The Beatle", Date.valueOf("2000-01-01"));

        this.mockDto = new AlbumDto(mockAlbum.getSinger().getFirstName(),
                mockAlbum.getSinger().getLastName(),
                mockAlbum.getTitle(),
                mockAlbum.getReleaseDate());
    }

    @Test
    public void getAllAlbums() throws Exception {
        when(service.findAllAlbums()).thenReturn(Collections.singletonList(mockAlbum));
        when(transformer.toDto(mockAlbum)).thenReturn(mockDto);

        mockMvc.perform(get("/api/albums"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(mockDto.getSingerFirstName())));

        verify(this.service, times(1)).findAllAlbums();
    }

    @Test
    public void findAlbumsByArtistId() throws Exception {
        when(service.findAlbumsByArtistId(1)).thenReturn(Collections.singletonList(mockAlbum));
        when(transformer.toDto(mockAlbum)).thenReturn(mockDto);

        mockMvc.perform(get("/api/artists/1/albums"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(mockDto.getSingerLastname())));

        verify(this.service, times(1)).findAlbumsByArtistId(1);
    }
}