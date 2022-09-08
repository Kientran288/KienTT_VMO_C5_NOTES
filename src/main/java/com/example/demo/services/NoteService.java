package com.example.demo.services;

import com.example.demo.models.Note;
import com.example.demo.payload.request.NoteRequest;
import com.example.demo.payload.response.NoteResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;
import java.util.List;

/**
 * NoteService
 * Arthur: kientt
 */
public interface NoteService {
    ResponseEntity<List<NoteResponse>> getAllNote(String title, Principal principal);

    ResponseEntity<NoteResponse> detail(long id);

    ResponseEntity<Note> create(NoteRequest Note, MultipartFile file, Principal principal);

    ResponseEntity<Note> edit(long id, NoteRequest Note, MultipartFile file);

    ResponseEntity<HttpStatus> delete(long id);

    ResponseEntity<HttpStatus> deleteAll();
}
