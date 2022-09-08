package com.example.demo.payload.response;

import com.example.demo.models.EStatus;
import com.example.demo.models.EType;
import com.example.demo.payload.request.CheckboxRequest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class NoteResponseTest {
    private NoteResponse noteResponse;

    @Before
    public void init() {
        noteResponse = new NoteResponse();
        noteResponse.setCreatedBy("kientt");
        noteResponse.setNote("Hello");
        noteResponse.setStatus(EStatus.SAVED);
        noteResponse.setTitle("Hello world");
        noteResponse.setType(EType.SIMPLE);
        noteResponse.setFile("/upload/img.png");

        List<CheckboxRequest> checkboxList = new ArrayList<>();
        CheckboxRequest checkboxRequest = new CheckboxRequest("Hello", 2);
        checkboxList.add(checkboxRequest);
        noteResponse.setCheckboxList(checkboxList);
    }

    @Test
    public void testSetterAndGetter() {
        Assert.assertEquals("Hello world", noteResponse.getTitle());
        Assert.assertEquals("kientt", noteResponse.getCreatedBy());
        Assert.assertEquals("Hello", noteResponse.getNote());
        Assert.assertEquals(EStatus.SAVED, noteResponse.getStatus());
        Assert.assertEquals(EType.SIMPLE, noteResponse.getType());
        Assert.assertNotNull(noteResponse.getFile());
        Assert.assertEquals(2, noteResponse.getCheckboxList().get(0).getChecked());
        Assert.assertEquals("Hello", noteResponse.getCheckboxList().get(0).getContent());
    }
}