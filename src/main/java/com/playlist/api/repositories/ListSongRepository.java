package com.playlist.api.repositories;

import com.playlist.api.models.ListSong;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ListSongRepository extends JpaRepository<ListSong, Long> {
    boolean existsByName(String name);

    @Query("SELECT DISTINCT l FROM ListSong l LEFT JOIN FETCH l.songs WHERE l.name = :name")
    Optional<ListSong> findByNameWithSongs(@Param("name") String name);

    Optional<ListSong> findByName(String name);

    @Modifying
    @Query("DELETE FROM ListSong l WHERE l.name = :name")
    void deleteByName(@Param("name") String name);
}
