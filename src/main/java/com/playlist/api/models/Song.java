package com.playlist.api.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "songs")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Song {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El título de la canción no puede estar vacío")
    @Column(nullable = false)
    private String title;

    @NotBlank(message = "El artista no puede estar vacío")
    @Column(nullable = false)
    private String artist;

    @Column(nullable = true)
    private String album;

    @Column(nullable = true)
    private String releaseYear;

    @Column(nullable = true)
    private String genre;

    @ManyToMany(mappedBy = "songs", fetch = FetchType.LAZY)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<ListSong> list = new HashSet<>();
}
