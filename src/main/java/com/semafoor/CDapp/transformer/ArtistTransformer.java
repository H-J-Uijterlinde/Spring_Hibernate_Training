package com.semafoor.CDapp.transformer;

import com.semafoor.CDapp.dto.AlbumDto;
import com.semafoor.CDapp.dto.ArtistDto;
import com.semafoor.CDapp.model.Album;
import com.semafoor.CDapp.model.Artist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ArtistTransformer implements Transformer<Artist, ArtistDto> {

    private AlbumTransformer albumTransformer;

    @Autowired
    public ArtistTransformer(AlbumTransformer albumTransformer) {
        this.albumTransformer = albumTransformer;
    }

    @Override
    public Artist toModel(ArtistDto dto) {
        Artist artist = new Artist();
        artist.setFirstName(dto.getFirstName());
        artist.setLastName(dto.getLastName());
        artist.setBirthDate(dto.getBirthDate());

        if (dto.getAlbumDtos() != null) {
            List<Album> albums = dto.getAlbumDtos().stream()
                    .map(albumTransformer::toModel)
                    .peek(artist::addAlbum)
                    .collect(Collectors.toList());

            artist.setAlbums(albums);
        }

        return artist;
    }

    @Override
    public ArtistDto toDto(Artist model) {
        ArtistDto dto = new ArtistDto();
        dto.setFirstName(model.getFirstName());
        dto.setLastName(model.getLastName());
        dto.setBirthDate(model.getBirthDate());

        List<AlbumDto> albumDtos = model.getAlbums().stream()
                .map(albumTransformer::toDto)
                .collect(Collectors.toList());

        dto.setAlbumDtos(albumDtos);

        return dto;
    }
}
