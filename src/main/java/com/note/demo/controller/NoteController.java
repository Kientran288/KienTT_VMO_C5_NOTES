package com.note.demo.controller;

import com.note.demo.models.Note;
import com.note.demo.payload.request.NoteRequest;
import com.note.demo.payload.response.NoteResponse;
import com.note.demo.services.NoteService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;
import java.util.List;

@CrossOrigin("*")
@RestController
@Log4j2
@RequestMapping("/api-kientt-note")
public class NoteController {

    @Autowired
    NoteService noteService;

    @GetMapping("/notes")
    public ResponseEntity<List<NoteResponse>> getAllNote(@RequestParam(required = false) String title, Principal principal) {
        return noteService.getAllNote(title, principal);
    }


    @GetMapping("/note/{id}")
    public ResponseEntity<NoteResponse> getNoteById(@PathVariable("id") long id) {
        return noteService.detail(id);
    }

    @PostMapping(value = "/notes", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<Note> createNote(@RequestPart("note") NoteRequest noteRequest, @RequestPart("file") MultipartFile file, Principal principal) {
        return noteService.create(noteRequest, file, principal);
    }

    @PutMapping(value = "/notes/{id}", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<Note> updateNote(@PathVariable("id") long id, @RequestPart("note") NoteRequest noteRequest, @RequestPart("file") MultipartFile file) {
        return noteService.edit(id, noteRequest, file);
    }

    @DeleteMapping("/notes/{id}")
    public ResponseEntity<HttpStatus> deleteNote(@PathVariable("id") long id) {
        return noteService.delete(id);
    }

    @DeleteMapping("/notes")
    public ResponseEntity<HttpStatus> deleteAllNotes() {
        return noteService.deleteAll();
    }

}
