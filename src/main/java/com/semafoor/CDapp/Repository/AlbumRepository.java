package com.semafoor.CDapp.Repository;

import com.semafoor.CDapp.model.Album;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class AlbumRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Album> findAllAlbums() {
        return this.entityManager.createQuery("Select a from Album a", Album.class)
                .getResultList();
    }


    public List<Album> findAlbumsByArtistId(long id) {
        return this.entityManager.createQuery("Select a From Album a Join a.singer s where s.id = :id", Album.class)
                .setParameter("id", id)
                .getResultList();
    }
}
