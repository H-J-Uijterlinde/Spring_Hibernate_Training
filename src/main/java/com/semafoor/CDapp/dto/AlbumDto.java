package com.semafoor.CDapp.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.time.LocalDate;

@Data
@NoArgsConstructor
public class AlbumDto {

    private String singerFirstName;
    private String singerLastname;
    private String title;
    private Date releaseDate;

    public AlbumDto(String singerFirstName, String singerLastname, String title, Date releaseDate) {
        this.singerFirstName = singerFirstName;
        this.singerLastname = singerLastname;
        this.title = title;
        this.releaseDate = releaseDate;
    }
}
