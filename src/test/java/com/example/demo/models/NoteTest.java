package com.example.demo.models;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class NoteTest {
    private Note note;

    private Note note1;

    @Before
    public void init() {
        note = new Note();
        note.setCheckBoxIds("1");
        note.setCreatedBy("kientt");
        note.setImageLink("/upload/demo/anime.jpg");
        note.setNote("Hello");
        note.setStatus(EStatus.SAVED);
        note.setTitle("Hello world");
        note.setType(EType.SIMPLE);

        // Note(String title, String note, String checkBoxIds, String imageLink, EType type, EStatus status, String createdBy)
        note1 = new Note("XXX", "Click", "1", "xxxx", EType.IMAGE, EStatus.SAVED, "Hello");
    }

    @Test
    public void testSetterAndGetter() {
        Assert.assertEquals("Hello world", note.getTitle());
        Assert.assertEquals("1", note.getCheckBoxIds());
        Assert.assertEquals("kientt", note.getCreatedBy());
        Assert.assertEquals("/upload/demo/anime.jpg", note.getImageLink());
        Assert.assertEquals("Hello", note.getNote());
        Assert.assertEquals(EStatus.SAVED, note.getStatus());
        Assert.assertEquals(EType.SIMPLE, note.getType());
        Assert.assertNull(note.getId());

        Assert.assertEquals("XXX", note1.getTitle());
        Assert.assertEquals("1", note1.getCheckBoxIds());
        Assert.assertEquals("Hello", note1.getCreatedBy());
        Assert.assertEquals("xxxx", note1.getImageLink());
        Assert.assertEquals("Click", note1.getNote());
        Assert.assertEquals(EStatus.SAVED, note1.getStatus());
        Assert.assertEquals(EType.IMAGE, note1.getType());
        Assert.assertNull(note1.getId());
    }
}