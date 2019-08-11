package com.semafoor.CDapp.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class ArtistDto {

    private String firstName;
    private String lastName;
    private Date birthDate;
    private List<AlbumDto> albumDtos;

    public ArtistDto(String firstName, String lastName, Date birthDate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.albumDtos = new ArrayList<>();
    }
}
