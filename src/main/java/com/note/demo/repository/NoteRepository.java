package com.note.demo.repository;

import com.note.demo.models.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoteRepository extends JpaRepository<Note, Long> {
    List<Note> findByTitleContainingAndCreatedByContaining(String title, String CreatedBy);

    List<Note> findByCreatedByContaining(String CreatedBy);
    @Query("SELECT COUNT(u) FROM Note n WHERE n.createdBy=:name AND n.status = 0")
    long countUnfinished(@Param("name") String name);
}
