package com.semafoor.CDapp.controller;

import com.semafoor.CDapp.dto.ArtistDto;
import com.semafoor.CDapp.model.Artist;
import com.semafoor.CDapp.service.ArtistService;
import com.semafoor.CDapp.transformer.ArtistTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class ArtistController {

    private ArtistTransformer artistTransformer;
    private ArtistService artistService;

    @Autowired
    public ArtistController(ArtistTransformer artistTransformer,
                            ArtistService artistService) {
        this.artistTransformer =artistTransformer;
        this.artistService = artistService;
    }

    @GetMapping("/api/artists")
    public ResponseEntity<List<ArtistDto>> getAllArtists() {
        List<ArtistDto> artistDtos = new ArrayList<>();
        for (Artist artist: artistService.getAllArtists()) {
            ArtistDto artistDto = artistTransformer.toDto(artist);
            artistDtos.add(artistDto);
        }
        return ResponseEntity.ok(artistDtos);
    }

    @GetMapping("/api/artists/{id}")
    public ResponseEntity<ArtistDto> findArtistById(@PathVariable("id") Long id) {
        ArtistDto dto = artistTransformer.toDto(artistService.getArtistById(id));
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/api/artists/lastName={lastName}")
    public ResponseEntity<List<ArtistDto>> findArtistsByLastName(@PathVariable("lastName") String lastName) {
        List<ArtistDto> artistDtos = this.artistService.getArtistsByLastName(lastName).stream()
                .map(this.artistTransformer::toDto)
                .collect(Collectors.toList());

        return ResponseEntity.ok(artistDtos);
    }

    @GetMapping("/api/albums/{id}/artist")
    public ResponseEntity<ArtistDto> findArtistByAlbumId(@PathVariable("id") Long id) {
        ArtistDto dto = artistTransformer.toDto(artistService.getArtistByAlbumId(id));
        return ResponseEntity.ok(dto);
    }

    @PostMapping("/api/artists")
    public ResponseEntity createArtist(@RequestBody ArtistDto dto) {
        Artist model = artistTransformer.toModel(dto);
        artistService.createArtist(model);

        return ResponseEntity.created(URI.create("")).build();
    }

    @PutMapping("api/artists/{id}")
    public ResponseEntity updateArtist(@PathVariable("id") Long id, @RequestBody ArtistDto dto) {
        Artist model = this.artistTransformer.toModel(dto);
        artistService.updateArtist(id, model);

        return ResponseEntity.created(URI.create("")).build();
    }

    @DeleteMapping("api/artists/{id}")
    public ResponseEntity deleteArtist(@PathVariable("id") Long id) {
        artistService.deleteArtistById(id);
        return ResponseEntity.created(URI.create("")).build();
    }
}
