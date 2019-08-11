package com.semafoor.CDapp.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
public class Artist {

    @Id
    @GeneratedValue
    private long id;

    @Column(nullable = false, unique = true, name = "first_name")
    private String firstName;

    @Column(nullable = false, unique = true, name = "last_name")
    private String lastName;

    @Column(name = "birth_date")
    private Date birthDate;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "singer")
    private List<Album> albums = new ArrayList<>();

    public Artist(String firstName, String lastName, Date birthDate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
    }

    public void addAlbum(Album album) {
        album.setSinger(this);
        this.albums.add(album);
    }
}
