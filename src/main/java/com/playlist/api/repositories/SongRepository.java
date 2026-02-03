package com.playlist.api.repositories;

import com.playlist.api.models.Song;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface SongRepository extends JpaRepository<Song, Long> {
    Optional<Song> findByTitleAndArtist(String title, String artist);

    List<Song> findByTitleContainingIgnoreCase(String title);

    List<Song> findByArtistContainingIgnoreCase(String artist);

    boolean existsByTitleAndArtist(String title, String artist);

    @Query("SELECT COUNT(s) FROM Song s JOIN s.list l WHERE l.id = :listSongId")
    Long countSongsInList(@Param("listSongId") Long listSongId);
}
