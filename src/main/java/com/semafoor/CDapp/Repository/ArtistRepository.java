package com.semafoor.CDapp.Repository;

import com.semafoor.CDapp.model.Artist;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class ArtistRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Artist> getArtists() {
        return this.entityManager.createQuery("Select a From Artist a", Artist.class).getResultList();
    }

    public Artist getArtistById(long id) {
        return this.entityManager.find(Artist.class, id);
    }

    public List<Artist> getArtistsByLastName(String lastName) {
        return this.entityManager.createQuery("Select a From Artist a Where a.lastName Like :lastName", Artist.class)
                .setParameter("lastName", lastName)
                .getResultList();
    }

    public Artist getArtistByAlbumId(long albumId) {
        return this.entityManager.createQuery("Select s From Artist s Join s.albums a where a.id = :id", Artist.class)
                .setParameter("id", albumId)
                .getSingleResult();
    }

    public int updateArtist(long id, Artist artist) {
        artist.setId(id);
        this.entityManager.merge(artist);

        return 1;

//        return this.entityManager.createQuery("Update Artist a Set a = :artist Where a.id = :id")
//                .setParameter("artist", artist)
//                .setParameter("id", id)
//                .executeUpdate();
    }

    public boolean createArtist(Artist artist) {
        this.entityManager.persist(artist);
        return this.entityManager.contains(artist);
    }

    public int deleteArtistById(long id) {
//        return this.entityManager.createQuery("Delete From Artist a Where a.id = :id")
//                .setParameter("id", id)
//                .executeUpdate();

        Artist artist = this.entityManager.find(Artist.class, id);
        this.entityManager.remove(artist);
        return 1;
    }
}
