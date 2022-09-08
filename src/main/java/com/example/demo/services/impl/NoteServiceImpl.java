package com.example.demo.services.impl;

import com.example.demo.models.Checkbox;
import com.example.demo.models.EStatus;
import com.example.demo.models.Note;
import com.example.demo.payload.request.CheckboxRequest;
import com.example.demo.payload.request.NoteRequest;
import com.example.demo.payload.response.NoteResponse;
import com.example.demo.repository.CheckboxRepository;
import com.example.demo.repository.NoteRepository;
import com.example.demo.services.FilesStorageService;
import com.example.demo.services.NoteService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * NoteServiceImpl
 * Arthur: kientt
 */
@Log4j2
@Service
public class NoteServiceImpl implements NoteService {

    @Autowired
    private NoteRepository noteRepository;

    @Autowired
    private CheckboxRepository checkboxRepository;

    @Autowired
    private FilesStorageService filesStorageService;

    @Override
    public ResponseEntity<List<NoteResponse>> getAllNote(String title, Principal principal) {
        String userName = principal.getName();
        try {
            List<Note> notes = new ArrayList<>();

            if (StringUtils.isEmpty(title)) {
                noteRepository.findByCreatedByContaining(userName).forEach(notes::add);
            } else {
                noteRepository.findByTitleContainingAndCreatedByContaining(title, userName).forEach(notes::add);
            }

            if (notes.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            List<NoteResponse> responseList = new ArrayList<>();

            for (Note note : notes) {
                NoteResponse noteRes = new NoteResponse();
                noteRes.setNote(note.getNote());
                noteRes.setCreatedBy(note.getCreatedBy());
                noteRes.setFile(note.getImageLink());
                noteRes.setTitle(note.getTitle());
                noteRes.setStatus(note.getStatus());
                noteRes.setType(note.getType());
                noteRes.setCheckboxList(getCheckBoxList(note.getCheckBoxIds()));
                responseList.add(noteRes);
            }
            return new ResponseEntity<>(responseList, HttpStatus.OK);
        } catch (Exception e) {
            log.error(e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private List<CheckboxRequest> getCheckBoxList(String checkBoxIds) {
        if (StringUtils.isEmpty(checkBoxIds)) {
            return new ArrayList<>();
        }
        List<Long> ids = Arrays.asList(checkBoxIds.split(",")).stream().map(Long::parseLong).collect(Collectors.toList());
        List<Checkbox> listCheckbox = checkboxRepository.findAllById(ids);
        List<CheckboxRequest> checkboxRequestList = new ArrayList<>();
        listCheckbox.forEach(checkbox -> {
            checkboxRequestList.add(new CheckboxRequest(checkbox.getContent(), checkbox.getChecked()));
        });
        return checkboxRequestList;
    }

    @Override
    public ResponseEntity<NoteResponse> detail(long id) {
        Optional<Note> noteData = noteRepository.findById(id);
        if (noteData.isPresent()) {
            NoteResponse noteRes = new NoteResponse();
            Note note = noteData.get();
            noteRes.setNote(note.getNote());
            noteRes.setCreatedBy(note.getCreatedBy());
            noteRes.setFile(note.getImageLink());
            noteRes.setTitle(note.getTitle());
            noteRes.setStatus(note.getStatus());
            noteRes.setType(note.getType());
            noteRes.setCheckboxList(getCheckBoxList(note.getCheckBoxIds()));
            return new ResponseEntity<>(noteRes, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<Note> create(NoteRequest noteRequest, MultipartFile file, Principal principal) {
        try {
            String userName = principal.getName();
            String checkboxIds = "";
            if (!CollectionUtils.isEmpty(noteRequest.getCheckboxList())) {
                List<Checkbox> listCheckbox = noteRequest.getCheckboxList().stream().map(checkboxRequest -> {
                    return new Checkbox(checkboxRequest.getContent(), checkboxRequest.getChecked());
                }).collect(Collectors.toList());
                List<Checkbox> listCheckboxSaved = checkboxRepository.saveAll(listCheckbox);
                checkboxIds = String.join(",", listCheckboxSaved.stream().map(checkbox -> checkbox.getId().toString()).collect(Collectors.toList()));
            }

            String imagePath = filesStorageService.save(file);
            Note note = noteRepository.save(new Note(noteRequest.getTitle(), noteRequest.getNote(), checkboxIds, imagePath, noteRequest.getType(), noteRequest.getStatus(), userName));
            return new ResponseEntity<>(note, HttpStatus.CREATED);
        } catch (Exception e) {
            log.error(e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<Note> edit(long id, NoteRequest noteRequest, MultipartFile file) {
        Optional<Note> NoteData = noteRepository.findById(id);

        if (NoteData.isPresent()) {
            String checkboxIds = "";
            Note note = NoteData.get();
            if (!StringUtils.isEmpty(note.getCheckBoxIds())) {
                List<Long> listCheckBoxId = Arrays.asList(note.getCheckBoxIds().split(",")).stream().map(Long::parseLong).collect(Collectors.toList());
                checkboxRepository.deleteAllById(listCheckBoxId);
            }
            if (!CollectionUtils.isEmpty(noteRequest.getCheckboxList())) {
                List<Checkbox> listCheckbox = noteRequest.getCheckboxList().stream().map(checkboxRequest -> {
                    return new Checkbox(checkboxRequest.getContent(), checkboxRequest.getChecked());
                }).collect(Collectors.toList());
                List<Checkbox> listCheckboxSaved = checkboxRepository.saveAll(listCheckbox);
                checkboxIds = String.join(",", listCheckboxSaved.stream().map(checkbox -> checkbox.getId().toString()).collect(Collectors.toList()));
            }
            if (!StringUtils.isEmpty(note.getImageLink())) {
                filesStorageService.deleteByPath(note.getImageLink());
            }
            if (!ObjectUtils.isEmpty(file)) {
                String imagePath = filesStorageService.save(file);
                note.setImageLink(imagePath);
            }

            note.setTitle(noteRequest.getTitle());
            note.setNote(noteRequest.getNote());
            note.setStatus(EStatus.SAVED);
            note.setType(noteRequest.getType());
            note.setCheckBoxIds(checkboxIds);
            note.setCreatedBy(noteRequest.getCreatedBy());
            return new ResponseEntity<>(noteRepository.save(note), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<HttpStatus> delete(long id) {
        try {
            Optional<Note> NoteData = noteRepository.findById(id);
            if (NoteData.isPresent()) {
                noteRepository.deleteById(id);
                Note note = NoteData.get();
                if (!StringUtils.isEmpty(note.getCheckBoxIds())) {
                    List<Long> listCheckBoxId = Arrays.asList(note.getCheckBoxIds().split(",")).stream().map(Long::parseLong).collect(Collectors.toList());
                    checkboxRepository.deleteAllById(listCheckBoxId);
                }
            }
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            log.error(e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<HttpStatus> deleteAll() {
        try {
            noteRepository.deleteAll();
            checkboxRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            log.error(e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
