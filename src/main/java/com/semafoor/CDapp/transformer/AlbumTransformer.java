package com.semafoor.CDapp.transformer;

import com.semafoor.CDapp.model.Album;
import com.semafoor.CDapp.dto.AlbumDto;
import com.semafoor.CDapp.model.Artist;
import org.springframework.stereotype.Component;

@Component
public class AlbumTransformer implements Transformer<Album, AlbumDto> {

    public AlbumTransformer() {
    }

    @Override
    public Album toModel(AlbumDto dto) {
        Album album = new Album();

        //Todo: set singer through repository?
        Artist singer = new Artist();
        singer.setFirstName(dto.getSingerFirstName());
        singer.setLastName(dto.getSingerLastname());
        album.setSinger(singer);

        album.setTitle(dto.getTitle());
        album.setReleaseDate(dto.getReleaseDate());
        return album;
    }

    @Override
    public AlbumDto toDto(Album model) {
        AlbumDto dto = new AlbumDto();
        dto.setSingerFirstName(model.getSinger().getFirstName());
        dto.setSingerLastname(model.getSinger().getLastName());
        dto.setTitle(model.getTitle());
        dto.setReleaseDate(model.getReleaseDate());
        return dto;
    }
}
