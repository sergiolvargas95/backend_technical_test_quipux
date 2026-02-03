package com.playlist.api.dtos;

import com.playlist.api.dtos.request.ListSongRequestDto;
import com.playlist.api.dtos.request.SongRequestDto;
import com.playlist.api.dtos.response.ListSongResponseDto;
import com.playlist.api.models.ListSong;
import com.playlist.api.models.Song;
import org.springframework.stereotype.Component;

@Component
public class PlayListMapper {
    public ListSong toListSongEntity(ListSongRequestDto dto) {
        ListSong list = ListSong.builder()
                .name(dto.getName())
                .description(dto.getDescription())
                .build();

        if (dto.getSongs() != null) {
            dto.getSongs().forEach(songDTO -> {
                Song song = toSongEntity(songDTO);
                list.addSong(song);
            });
        }

        return list;
    }

    public Song toSongEntity(SongRequestDto dto) {
        return Song.builder()
                .title(dto.getTitle())
                .artist(dto.getArtist())
                .album(dto.getAlbum())
                .releaseYear(dto.getReleaseYear())
                .genre(dto.getGenre())
                .build();
    }


    public ListSongResponseDto toListSongResponseDTO(ListSong list) {
        return ListSongResponseDto.fromEntity(list);
    }

}
