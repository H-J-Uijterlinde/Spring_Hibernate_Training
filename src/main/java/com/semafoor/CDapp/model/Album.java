package com.semafoor.CDapp.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@Entity
public class Album {

    @Id
    @GeneratedValue
    private long id;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "singer_id")
    private Artist singer;

    @Column(nullable = false)
    private String title;

    @Column(name = "release_date")
    private Date releaseDate;

    public Album(Artist singer, String title, Date releaseDate) {
        this.singer = singer;
        this.title = title;
        this.releaseDate = releaseDate;
    }
}
