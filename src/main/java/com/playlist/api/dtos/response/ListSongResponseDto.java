package com.playlist.api.dtos.response;

import com.playlist.api.models.ListSong;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ListSongResponseDto {
    private Long id;
    private String name;
    private String description;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Integer totalSongs;
    private List<SongResponseDto> songs;

    public static ListSongResponseDto fromEntity(ListSong list) {
        return ListSongResponseDto.builder()
                .id(list.getId())
                .name(list.getName())
                .description(list.getDescription())
                .createdAt(list.getCreatedAt())
                .updatedAt(list.getUpdatedAt())
                .totalSongs(list.getSongs().size())
                .songs(list.getSongs().stream()
                        .map(SongResponseDto::fromEntity)
                        .collect(Collectors.toList()))
                .build();
    }
}
