package com.playlist.api.services;

import com.playlist.api.dtos.PlayListMapper;
import com.playlist.api.dtos.request.ListSongRequestDto;
import com.playlist.api.dtos.request.SongRequestDto;
import com.playlist.api.dtos.response.ListSongResponseDto;
import com.playlist.api.dtos.response.SongResponseDto;
import com.playlist.api.exception.BadRequestException;
import com.playlist.api.exception.ResourceNotFoundException;
import com.playlist.api.models.ListSong;
import com.playlist.api.models.Song;
import com.playlist.api.repositories.ListSongRepository;
import com.playlist.api.repositories.SongRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.module.ResolutionException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Servicio que contiene la lógica de negocio para las listas de reproducción.
 * Implementa los endpoints:
 * - POST /lists
 * - GET /lists
 * - GET /lists/{listName}
 * - DELETE /lists/{listName}
 */
@Service
@RequiredArgsConstructor
@Transactional
public class ListSongService {
    private final ListSongRepository listSongRepository;
    private final SongRepository songRepository;
    private final PlayListMapper mapper;

    public ListSongResponseDto createList(ListSongRequestDto dto) {
        if(listSongRepository.existsByName(dto.getName())) {
            throw new BadRequestException("Ya existe una lista con el nombre: " + dto.getName());
        }

        if(dto.getSongs() == null || dto.getSongs().isEmpty()) {
            throw new BadRequestException("La lista debe contener al menos una canción");
        }

        ListSong newList = ListSong.builder()
                .name(dto.getName())
                .description(dto.getDescription())
                .build();

        for(SongRequestDto songDto : dto.getSongs()) {
            Song song = findOrCreateSong(songDto);
            newList.addSong(song);
        }

        ListSong savedList = listSongRepository.save(newList);
        return mapper.toListSongResponseDTO(savedList);
    }

    @Transactional(readOnly = true)
    public List<ListSongResponseDto> getAllLists() {
        List<ListSong> lists = listSongRepository.findAll();

        return lists.stream()
                .map(ListSongResponseDto::fromEntity)
                .collect(Collectors.toList());
    }

    public void deleteListByName(String listName) {
        if(!listSongRepository.existsByName(listName)) {
            throw new ResourceNotFoundException("Lista", "nombre", listName);
        }

        listSongRepository.deleteByName(listName);
    }

    @Transactional(readOnly = true)
    public boolean existsByName(String listName) {
        return listSongRepository.existsByName(listName);
    }

    private Song findOrCreateSong(SongRequestDto songDto) {
        Optional<Song> existingSong = songRepository
                .findByTitleAndArtist(songDto.getTitle(), songDto.getArtist());

        if (existingSong.isPresent()) {
            return existingSong.get();
        } else {
            Song newSong = mapper.toSongEntity(songDto);
            Song savedSong = songRepository.save(newSong);
            return savedSong;
        }
    }
}
