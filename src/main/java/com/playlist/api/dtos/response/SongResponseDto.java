package com.playlist.api.dtos.response;

import com.playlist.api.models.Song;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SongResponseDto {
    private Long id;
    private String title;
    private String artist;
    private String album;
    private String releaseYear;
    private String genre;

    public static SongResponseDto fromEntity(Song song) {
        return SongResponseDto.builder()
                .id(song.getId())
                .title(song.getTitle())
                .artist(song.getArtist())
                .album(song.getAlbum())
                .releaseYear(song.getReleaseYear())
                .genre(song.getGenre())
                .build();
    }
}
