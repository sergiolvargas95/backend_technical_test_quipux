package com.playlist.api.dtos.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SongRequestDto {
    @NotBlank(message = "El título no puede estar vacío")
    private String title;

    @NotBlank(message = "El artista no puede estar vacío")
    private String artist;

    private String album;

    private String releaseYear;

    private String genre;
}
