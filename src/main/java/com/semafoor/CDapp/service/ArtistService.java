package com.semafoor.CDapp.service;

import com.semafoor.CDapp.Repository.ArtistRepository;
import com.semafoor.CDapp.model.Artist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class ArtistService {

    private ArtistRepository artistRepository;

    @Autowired
    public ArtistService(ArtistRepository artistRepository) {
        this.artistRepository = artistRepository;
    }

    public List<Artist> getAllArtists() {
        List<Artist> artists = this.artistRepository.getArtists();
        return artists;
    }

    public Artist getArtistById(long id) {
        Artist artist = this.artistRepository.getArtistById(id);
        return artist;
    }

    public List<Artist> getArtistsByLastName(String lastName) {
        List<Artist> artists = this.artistRepository.getArtistsByLastName(lastName);
        return artists;
    }

    public Artist getArtistByAlbumId(long id) {
        return this.artistRepository.getArtistByAlbumId(id);
    }

    @Transactional
    public boolean createArtist(Artist artist) {
        return this.artistRepository.createArtist(artist);
    }

    @Transactional
    public int updateArtist(long id, Artist artist) {
        return this.artistRepository.updateArtist(id, artist);
    }

    @Transactional
    public int deleteArtistById(long id) {
        return this.artistRepository.deleteArtistById(id);
    }
}
