package com.example.demo.payload.request;

import com.example.demo.models.EStatus;
import com.example.demo.models.EType;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class NoteRequestTest {
    private NoteRequest noteRequest;

    @Before
    public void init() {
        noteRequest = new NoteRequest();
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
    }

    @Test
    public void testSetterAndGetter() {
        Assert.assertEquals("Hello world", noteRequest.getTitle());
        Assert.assertEquals("kientt", noteRequest.getCreatedBy());
        Assert.assertEquals("Hello", noteRequest.getNote());
        Assert.assertEquals(EStatus.SAVED, noteRequest.getStatus());
        Assert.assertEquals(EType.SIMPLE, noteRequest.getType());
        Assert.assertNotNull(noteRequest.getFile());
        Assert.assertEquals(2, noteRequest.getCheckboxList().get(0).getChecked());
        Assert.assertEquals("Hello", noteRequest.getCheckboxList().get(0).getContent());
    }
}