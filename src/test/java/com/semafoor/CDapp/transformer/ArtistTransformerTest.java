package com.semafoor.CDapp.transformer;

import com.semafoor.CDapp.dto.AlbumDto;
import com.semafoor.CDapp.dto.ArtistDto;
import com.semafoor.CDapp.model.Album;
import com.semafoor.CDapp.model.Artist;
import org.junit.Before;
import org.junit.Test;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class ArtistTransformerTest {

    private ArtistTransformer artistTransformer;

    @Before
    public void setUp() {
        artistTransformer = new ArtistTransformer(new AlbumTransformer());

    }

    @Test
    public void toModel() {
        ArtistDto dto = new ArtistDto("John", "Lennon", Date.valueOf("2001-01-02"));
        AlbumDto dto1 = new AlbumDto(dto.getFirstName(), dto.getLastName(), "Beatle", Date.valueOf("2001-01-02"));
        AlbumDto dto2 = new AlbumDto(dto.getFirstName(), dto.getLastName(), "Something", Date.valueOf("2001-01-02"));
        dto.setAlbumDtos(Arrays.asList( dto1, dto2));

        Artist model = artistTransformer.toModel(dto);
        assertEquals(dto.getFirstName(), model.getFirstName());
        assertEquals(dto.getLastName(), model.getLastName());
        assertEquals(dto.getBirthDate(), model.getBirthDate());

        Album album1 = model.getAlbums().get(0);
        Album album2 = model.getAlbums().get(1);
        assertSame(album1.getSinger(), model);
        assertSame(album2.getSinger(), model);

        //Todo: practice test cases
        List<Album> albumList = model.getAlbums();
        for (Album album: albumList) {
            assertSame(album.getSinger(), model);
        }

    }

    @Test
    public void toDto() {
        Artist singer = new Artist("John", "Lennon", Date.valueOf("2001-01-02"));
        Album album1 = new Album(singer, "TestTitle", Date.valueOf("2001-01-02"));
        Album album2 = new Album(singer, "AnotherTitle", Date.valueOf("2001-01-02"));
        List<Album> albums = Arrays.asList(album1, album2);
        singer.setAlbums(albums);

        ArtistDto artistDto = artistTransformer.toDto(singer);

        assertEquals(singer.getFirstName(), artistDto.getFirstName());
        assertEquals(singer.getLastName(), artistDto.getLastName());
        assertEquals(singer.getBirthDate(), artistDto.getBirthDate());

        List<AlbumDto> albumDtos = artistDto.getAlbumDtos();
        assertTrue(albums.size() == albumDtos.size());

        for (int i = 0; i < albums.size(); i++) {
            Album album = albums.get(i);
            AlbumDto dto = albumDtos.get(i);
            assertEquals(album.getSinger().getFirstName(), dto.getSingerFirstName());
            assertEquals(album.getSinger().getLastName(), dto.getSingerLastname());
            assertEquals(album.getTitle(), dto.getTitle());
            assertEquals(album.getReleaseDate(), dto.getReleaseDate());
        }
    }
}