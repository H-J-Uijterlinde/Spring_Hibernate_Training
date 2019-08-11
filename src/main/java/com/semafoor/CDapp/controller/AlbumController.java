package com.semafoor.CDapp.controller;

import com.semafoor.CDapp.dto.AlbumDto;
import com.semafoor.CDapp.service.AlbumService;
import com.semafoor.CDapp.transformer.AlbumTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class AlbumController {

    private AlbumTransformer albumTransformer;
    private AlbumService albumService;

    @Autowired
    public AlbumController(AlbumTransformer albumTransformer, AlbumService albumService) {
        this.albumTransformer = albumTransformer;
        this.albumService = albumService;
    }

    @GetMapping("/api/albums")
    public ResponseEntity<List<AlbumDto>> getAllAlbums() {
        List<AlbumDto> albumDtos = this.albumService.findAllAlbums().stream()
                .map(this.albumTransformer::toDto)
                .collect(Collectors.toList());

        return ResponseEntity.ok(albumDtos);
    }

    @GetMapping("/api/artists/{id}/albums")
    public ResponseEntity<List<AlbumDto>> findAlbumsByArtistId(@PathVariable("id") Long id) {
        List<AlbumDto> albumDtos = this.albumService.findAlbumsByArtistId(id).stream()
                .map(this.albumTransformer::toDto)
                .collect(Collectors.toList());

        return ResponseEntity.ok(albumDtos);
    }
}
