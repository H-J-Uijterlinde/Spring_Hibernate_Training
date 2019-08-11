package com.semafoor.CDapp.transformer;

import com.semafoor.CDapp.dto.AlbumDto;
import com.semafoor.CDapp.model.Album;
import com.semafoor.CDapp.model.Artist;
import org.junit.Before;
import org.junit.Test;

import java.sql.Date;
import java.time.LocalDate;

import static org.junit.Assert.*;

public class AlbumTransformerTest {

    private AlbumTransformer albumTransformer;

    @Before
    public void setUp() {
        this.albumTransformer = new AlbumTransformer();
    }

    @Test
    public void toModel() {
        AlbumDto dto = new AlbumDto("John", "Lennon", "TestAlbum",
                Date.valueOf("2001-01-02"));

        Album model = albumTransformer.toModel(dto);
        assertEquals(dto.getTitle(), model.getTitle());
        assertEquals(dto.getReleaseDate(), model.getReleaseDate());
        assertEquals("John", model.getSinger().getFirstName());
        assertEquals("Lennon", model.getSinger().getLastName());
    }

    @Test
    public void toDto() {
        Album model = new Album();
        model.setSinger(new Artist("Sam", "Smith", Date.valueOf("2001-01-02")));
        model.setTitle("TestAlbum");
        model.setReleaseDate(Date.valueOf("2001-01-02"));

        AlbumDto dto = albumTransformer.toDto(model);
        assertEquals(model.getTitle(), dto.getTitle());
        assertEquals(model.getReleaseDate(), dto.getReleaseDate());
        assertEquals(model.getSinger().getFirstName(), dto.getSingerFirstName());
        assertEquals(model.getSinger().getLastName(), dto.getSingerLastname());
    }
}