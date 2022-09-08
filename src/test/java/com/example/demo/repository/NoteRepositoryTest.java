package com.example.demo.repository;

import com.example.demo.models.EStatus;
import com.example.demo.models.EType;
import com.example.demo.models.Note;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@DataJpaTest
class NoteRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private NoteRepository noteRepository;

    @Test
    void findByTitleContainingAndCreatedByContaining() {
        // given
        Note note = new Note("kent", "kent", "kent", "kent", EType.SIMPLE, EStatus.SAVED, "kent" );
        List<Note> listNote = Arrays.asList(note);
        entityManager.persist(note);
        entityManager.flush();

        // when
        List<Note> noteWhen = noteRepository.findByTitleContainingAndCreatedByContaining(note.getTitle(), note.getCreatedBy());

        // then
        assertThat(listNote.size())
                .isEqualTo(noteWhen.size());

        assertThat(listNote.get(0).getTitle())
                .isEqualTo(noteWhen.get(0).getTitle());
    }

    @Test
    void findByCreatedByContaining() {
        // given
        Note note = new Note("kent", "kent", "kent", "kent", EType.SIMPLE, EStatus.SAVED, "kent" );
        List<Note> listNote = Arrays.asList(note);
        entityManager.persist(note);
        entityManager.flush();

        // when
        List<Note> noteWhen = noteRepository.findByCreatedByContaining(note.getCreatedBy());

        // then
        assertThat(listNote.size())
                .isEqualTo(noteWhen.size());

        assertThat(listNote.get(0).getTitle())
                .isEqualTo(noteWhen.get(0).getTitle());
    }
}