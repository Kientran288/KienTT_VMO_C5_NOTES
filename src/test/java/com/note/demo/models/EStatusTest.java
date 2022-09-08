package com.note.demo.models;

import org.junit.Assert;
import org.junit.Test;

public class EStatusTest {
    @Test
    public void test_getValue() {
        Assert.assertEquals(0L, (long) EStatus.EDITING.getValue());
        Assert.assertEquals(1L, (long) EStatus.SAVED.getValue());
    }

    @Test
    public void test_toString() {
        Assert.assertEquals("0", EStatus.EDITING.toString());
        Assert.assertEquals("1", EStatus.SAVED.toString());
    }

    @Test
    public void test_getEnum() {
        Assert.assertEquals(EStatus.EDITING, EStatus.getEnum(0L));
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_getEnumThrowException() {
        Assert.assertEquals(EStatus.EDITING, EStatus.getEnum(3L));
    }
}