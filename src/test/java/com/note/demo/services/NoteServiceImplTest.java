package com.note.demo.services;

import com.note.demo.models.Checkbox;
import com.note.demo.models.EStatus;
import com.note.demo.models.EType;
import com.note.demo.models.Note;
import com.note.demo.payload.request.CheckboxRequest;
import com.note.demo.payload.request.NoteRequest;
import com.note.demo.payload.response.NoteResponse;
import com.note.demo.repository.CheckboxRepository;
import com.note.demo.repository.NoteRepository;
import com.note.demo.services.impl.NoteServiceImpl;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.attribute.UserPrincipal;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class NoteServiceImplTest {

    @InjectMocks
    private NoteServiceImpl noteService;

    @Mock
    private NoteRepository noteRepository;

    @Mock
    private CheckboxRepository checkboxRepository;

    @Mock
    private FilesStorageService filesStorageService;

    /**
     * START: getAllNote
     */

    // Call noteRepository.findByCreatedByContaining(userName).forEach(notes::add);
    @Test
    public void getAllNoteWhenTitleIsNull() {
        Note note = new Note("XXX", "Click", "1", "xxxx", EType.IMAGE, EStatus.SAVED, "Hello");

        List<Note> noteList = Arrays.asList(note);
        when(noteRepository.findByCreatedByContaining("kientt")).thenReturn(noteList);

        Principal principal = new UserPrincipal() {
            @Override
            public String getName() {
                return "kientt";
            }
        };
        ResponseEntity<List<NoteResponse>> result = noteService.getAllNote("", principal);
        Assert.assertEquals(HttpStatus.OK, result.getStatusCode());
    }

    // Call noteRepository.findByTitleContainingAndCreatedByContaining(title, userName).forEach(notes::add);
    @Test
    public void getAllNoteWhenTitleIsNotNull() {
        Note note = new Note("XXX", "Click", "1", "xxxx", EType.IMAGE, EStatus.SAVED, "Hello");

        List<Note> noteList = Arrays.asList(note);
        when(noteRepository.findByTitleContainingAndCreatedByContaining("abc", "kientt")).thenReturn(noteList);

        Principal principal = new UserPrincipal() {
            @Override
            public String getName() {
                return "kientt";
            }
        };
        ResponseEntity<List<NoteResponse>> result = noteService.getAllNote("abc", principal);
        Assert.assertEquals(HttpStatus.OK, result.getStatusCode());
    }

    // Return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    @Test
    public void getAllNoteReturnStatusEqualsNO_CONTENT() {
        List<Note> noteList = new ArrayList<>();
        when(noteRepository.findByCreatedByContaining("kientt")).thenReturn(noteList);

        Principal principal = new UserPrincipal() {
            @Override
            public String getName() {
                return "kientt";
            }
        };
        ResponseEntity<List<NoteResponse>> result = noteService.getAllNote("", principal);
        Assert.assertEquals(HttpStatus.NO_CONTENT, result.getStatusCode());
    }

    // Return new ResponseEntity<>(responseList, HttpStatus.OK);
    @Test
    public void getAllNoteReturnStatusEqualsOK() {
        Note note = new Note();
        note.setCheckBoxIds("1");
        note.setCreatedBy("kientt");
        note.setImageLink("/upload/demo/anime.jpg");
        note.setNote("Hello");
        note.setStatus(EStatus.SAVED);
        note.setTitle("Hello world");
        note.setType(EType.SIMPLE);
        List<Note> noteList = new ArrayList<>();
        noteList.add(note);
        when(noteRepository.findByCreatedByContaining("kientt")).thenReturn(noteList);

        List<Checkbox> checkboxList = new ArrayList<>();
        Checkbox checkbox = new Checkbox("Hello", 2);
        checkboxList.add(checkbox);
        when(checkboxRepository.findAllById(Arrays.asList(1L))).thenReturn(checkboxList);

        Principal principal = new UserPrincipal() {
            @Override
            public String getName() {
                return "kientt";
            }
        };
        ResponseEntity<List<NoteResponse>> result = noteService.getAllNote("", principal);
        Assert.assertEquals(HttpStatus.OK, result.getStatusCode());
    }

    @Test
    public void getAllNoteReturnStatusEqualsOKWhenCheckboxNull() {
        Note note = new Note();
        note.setCheckBoxIds("");
        note.setCreatedBy("kientt");
        note.setImageLink("/upload/demo/anime.jpg");
        note.setNote("Hello");
        note.setStatus(EStatus.SAVED);
        note.setTitle("Hello world");
        note.setType(EType.SIMPLE);
        List<Note> noteList = new ArrayList<>();
        noteList.add(note);
        when(noteRepository.findByCreatedByContaining("kientt")).thenReturn(noteList);

        List<Checkbox> checkboxList = new ArrayList<>();

        Principal principal = new UserPrincipal() {
            @Override
            public String getName() {
                return "kientt";
            }
        };
        ResponseEntity<List<NoteResponse>> result = noteService.getAllNote("", principal);
        Assert.assertEquals(HttpStatus.OK, result.getStatusCode());
    }

    /**
     * END: getAllNote
     */

    /**
     * START: detail
     */

    @Test
    void detailReturnStatusOK() {
        Note note = new Note();
        note.setCheckBoxIds("1");
        note.setCreatedBy("kientt");
        note.setImageLink("/upload/demo/anime.jpg");
        note.setNote("Hello");
        note.setStatus(EStatus.SAVED);
        note.setTitle("Hello world");
        note.setType(EType.SIMPLE);
        when(noteRepository.findById(1L)).thenReturn(Optional.of(note));
        ResponseEntity<NoteResponse> result = noteService.detail(1L);
        Assert.assertEquals(HttpStatus.OK, result.getStatusCode());
    }

    @Test
    void detailReturnStatusNOT_FOUND() {
        when(noteRepository.findById(1L)).thenReturn(Optional.empty());
        ResponseEntity<NoteResponse> result = noteService.detail(1L);
        Assert.assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
    }

    /**
     * END: detail
     */

    /**
     * START: create
     */

    @Test
    void createReturnStatusCREATED() {
        NoteRequest noteRequest = new NoteRequest();
        noteRequest.setCreatedBy("kientt");
        noteRequest.setNote("Hello");
        noteRequest.setStatus(EStatus.SAVED);
        noteRequest.setTitle("Hello world");
        noteRequest.setType(EType.SIMPLE);
        noteRequest.setFile(new MultipartFile() {
            @Override
            public String getName() {
                return null;
            }

            @Override
            public String getOriginalFilename() {
                return null;
            }

            @Override
            public String getContentType() {
                return null;
            }

            @Override
            public boolean isEmpty() {
                return false;
            }

            @Override
            public long getSize() {
                return 0;
            }

            @Override
            public byte[] getBytes() throws IOException {
                return new byte[0];
            }

            @Override
            public InputStream getInputStream() throws IOException {
                return null;
            }

            @Override
            public void transferTo(File dest) throws IOException, IllegalStateException {

            }
        });
        List<CheckboxRequest> checkboxList = new ArrayList<>();
        CheckboxRequest checkboxRequest = new CheckboxRequest("Hello", 2);
        checkboxList.add(checkboxRequest);
        noteRequest.setCheckboxList(checkboxList);

        Principal principal = new UserPrincipal() {
            @Override
            public String getName() {
                return "kientt";
            }
        };

        ResponseEntity<Note> result = noteService.create(noteRequest, noteRequest.getFile(), principal);

        Assert.assertEquals(HttpStatus.CREATED, result.getStatusCode());
    }

    @Ignore
    @Test
    void createThrowException() {
        NoteRequest noteRequest = new NoteRequest();
        noteRequest.setCreatedBy("kientt");
        noteRequest.setNote("Hello");
        noteRequest.setStatus(EStatus.SAVED);
        noteRequest.setTitle("Hello world");
        noteRequest.setType(EType.SIMPLE);
        noteRequest.setFile(null);
        List<CheckboxRequest> checkboxList = new ArrayList<>();
        CheckboxRequest checkboxRequest = new CheckboxRequest("Hello", 2);
        checkboxList.add(checkboxRequest);
        noteRequest.setCheckboxList(checkboxList);

        Principal principal = new UserPrincipal() {
            @Override
            public String getName() {
                return "kientt";
            }
        };

        ResponseEntity<Note> result = noteService.create(noteRequest, null, principal);
    }

    /**
     * END: create
     */

    /**
     * START: edit
     */

    @Test
    void editReturnStatusCREATED() {
        NoteRequest noteRequest = new NoteRequest();
        noteRequest.setCreatedBy("kientt");
        noteRequest.setNote("Hello");
        noteRequest.setStatus(EStatus.SAVED);
        noteRequest.setTitle("Hello world");
        noteRequest.setType(EType.SIMPLE);
        noteRequest.setFile(new MultipartFile() {
            @Override
            public String getName() {
                return null;
            }

            @Override
            public String getOriginalFilename() {
                return null;
            }

            @Override
            public String getContentType() {
                return null;
            }

            @Override
            public boolean isEmpty() {
                return false;
            }

            @Override
            public long getSize() {
                return 0;
            }

            @Override
            public byte[] getBytes() throws IOException {
                return new byte[0];
            }

            @Override
            public InputStream getInputStream() throws IOException {
                return null;
            }

            @Override
            public void transferTo(File dest) throws IOException, IllegalStateException {

            }
        });
        List<CheckboxRequest> checkboxList = new ArrayList<>();
        CheckboxRequest checkboxRequest = new CheckboxRequest("Hello", 2);
        checkboxList.add(checkboxRequest);
        noteRequest.setCheckboxList(checkboxList);

        Principal principal = new UserPrincipal() {
            @Override
            public String getName() {
                return "kientt";
            }
        };

        Note note = new Note();
        note.setCheckBoxIds("1");
        note.setCreatedBy("kientt");
        note.setImageLink("/upload/demo/anime.jpg");
        note.setNote("Hello");
        note.setStatus(EStatus.SAVED);
        note.setTitle("Hello world");
        note.setType(EType.SIMPLE);
        when(noteRepository.findById(1L)).thenReturn(Optional.of(note));

        ResponseEntity<Note> result = noteService.edit(1L, noteRequest, noteRequest.getFile());

        Assert.assertEquals(HttpStatus.OK, result.getStatusCode());
    }

    /**
     * END: edit
     */

    @Test
    void editReturnStatusNOT_FOUND() {
        NoteRequest noteRequest = new NoteRequest();
        noteRequest.setCreatedBy("kientt");
        noteRequest.setNote("Hello");
        noteRequest.setStatus(EStatus.SAVED);
        noteRequest.setTitle("Hello world");
        noteRequest.setType(EType.SIMPLE);
        noteRequest.setFile(new MultipartFile() {
            @Override
            public String getName() {
                return null;
            }

            @Override
            public String getOriginalFilename() {
                return null;
            }

            @Override
            public String getContentType() {
                return null;
            }

            @Override
            public boolean isEmpty() {
                return false;
            }

            @Override
            public long getSize() {
                return 0;
            }

            @Override
            public byte[] getBytes() throws IOException {
                return new byte[0];
            }

            @Override
            public InputStream getInputStream() throws IOException {
                return null;
            }

            @Override
            public void transferTo(File dest) throws IOException, IllegalStateException {

            }
        });
        List<CheckboxRequest> checkboxList = new ArrayList<>();
        CheckboxRequest checkboxRequest = new CheckboxRequest("Hello", 2);
        checkboxList.add(checkboxRequest);
        noteRequest.setCheckboxList(checkboxList);

        Principal principal = new UserPrincipal() {
            @Override
            public String getName() {
                return "kientt";
            }
        };

        when(noteRepository.findById(1L)).thenReturn(Optional.empty());

        ResponseEntity<Note> result = noteService.edit(1L, noteRequest, noteRequest.getFile());

        Assert.assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
    }

    /**
     * START: delete
     */

    @Test
    void deleteReturnStatusNO_CONTENT() {
        Note note = new Note();
        note.setCheckBoxIds("1");
        note.setCreatedBy("kientt");
        note.setImageLink("/upload/demo/anime.jpg");
        note.setNote("Hello");
        note.setStatus(EStatus.SAVED);
        note.setTitle("Hello world");
        note.setType(EType.SIMPLE);
        when(noteRepository.findById(1L)).thenReturn(Optional.of(note));
        ResponseEntity<HttpStatus> result = noteService.delete(1L);
        Assert.assertEquals(HttpStatus.NO_CONTENT, result.getStatusCode());
    }

    @Test
    void deleteReturnStatusNO_CONTENT2() {
        when(noteRepository.findById(1L)).thenReturn(Optional.empty());
        ResponseEntity<HttpStatus> result = noteService.delete(1L);
        Assert.assertEquals(HttpStatus.NO_CONTENT, result.getStatusCode());
    }

    /**
     * END: delete
     */

    /**
     * START: deleteAll
     */

    @Test
    void deleteAllReturnStatusNO_CONTENT() {
        ResponseEntity<HttpStatus> result = noteService.deleteAll();
        Assert.assertEquals(HttpStatus.NO_CONTENT, result.getStatusCode());
    }

    /**
     * END: deleteAll
     */
}