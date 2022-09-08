package com.note.demo.repository;

import com.note.demo.models.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoteRepository extends JpaRepository<Note, Long> {
    List<Note> findByTitleContainingAndCreatedByContaining(String title, String CreatedBy);

    List<Note> findByCreatedByContaining(String CreatedBy);
}
