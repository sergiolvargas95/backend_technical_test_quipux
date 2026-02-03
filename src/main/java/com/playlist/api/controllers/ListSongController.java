package com.playlist.api.controllers;

import com.playlist.api.dtos.request.ListSongRequestDto;
import com.playlist.api.dtos.response.ListSongResponseDto;
import com.playlist.api.services.ListSongService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/lists")
@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
public class ListSongController {
    private final ListSongService listSongService;

    @PostMapping
    public ResponseEntity<ListSongResponseDto> createList(@Valid @RequestBody ListSongRequestDto listRequestDto) {
        ListSongResponseDto createdList = listSongService.createList(listRequestDto);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{name}")
                .buildAndExpand(createdList.getName())
                .toUri();


        return ResponseEntity
                .created(location)
                .body(createdList);
    }

    @GetMapping
    public ResponseEntity<List<ListSongResponseDto>> getAllLists() {
        List<ListSongResponseDto> lists = listSongService.getAllLists();
        return ResponseEntity.ok(lists);
    }

    @GetMapping("/{listName}")
    public ResponseEntity<ListSongResponseDto> getListByName(@PathVariable String listName) {
        ListSongResponseDto list = listSongService.getListByName(listName);
        return ResponseEntity.ok(list);
    }

    @DeleteMapping("/{listName}")
    public ResponseEntity<Void> deleteListByName(@PathVariable String listName) {
        listSongService.deleteListByName(listName);
        return ResponseEntity.noContent().build();
    }
}
