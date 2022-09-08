package com.note.demo.models;

import org.junit.Assert;
import org.junit.Test;

public class ETypeTest {
    @Test
    public void test_getValue() {
        Assert.assertEquals(0L, (long) EType.SIMPLE.getValue());
        Assert.assertEquals(2L, (long) EType.CHECK_BOX.getValue());
        Assert.assertEquals(1L, (long) EType.IMAGE.getValue());
    }

    @Test
    public void test_toString() {
        Assert.assertEquals("0", EType.SIMPLE.toString());
        Assert.assertEquals("2", EType.CHECK_BOX.toString());
        Assert.assertEquals("1", EType.IMAGE.toString());
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_getEnumThrowException() {
        Assert.assertEquals(EType.SIMPLE, EType.getEnum("0"));
    }
}
