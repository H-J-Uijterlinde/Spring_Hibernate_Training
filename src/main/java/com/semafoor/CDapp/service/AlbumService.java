package com.semafoor.CDapp.service;

import com.semafoor.CDapp.Repository.AlbumRepository;
import com.semafoor.CDapp.model.Album;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlbumService {

    private AlbumRepository albumRepository;

    @Autowired
    public AlbumService(AlbumRepository albumRepository) {
        this.albumRepository = albumRepository;
    }

    public List<Album> findAllAlbums() {
        return this.albumRepository.findAllAlbums();
    }

    public List<Album> findAlbumsByArtistId(long id) {
        return this.albumRepository.findAlbumsByArtistId(id);
    }
}
