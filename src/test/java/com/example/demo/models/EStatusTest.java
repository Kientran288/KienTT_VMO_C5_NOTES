package com.example.demo.models;

import org.junit.Assert;
import org.junit.Test;

import static com.example.demo.models.EStatus.EDITING;
import static com.example.demo.models.EStatus.SAVED;

public class EStatusTest {
    @Test
    public void test_getValue() {
        Assert.assertEquals(0L, (long) EDITING.getValue());
        Assert.assertEquals(1L, (long) SAVED.getValue());
    }

    @Test
    public void test_toString() {
        Assert.assertEquals("0", EDITING.toString());
        Assert.assertEquals("1", SAVED.toString());
    }

    @Test
    public void test_getEnum() {
        Assert.assertEquals(EDITING, EStatus.getEnum(0L));
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_getEnumThrowException() {
        Assert.assertEquals(EDITING, EStatus.getEnum(3L));
    }
}