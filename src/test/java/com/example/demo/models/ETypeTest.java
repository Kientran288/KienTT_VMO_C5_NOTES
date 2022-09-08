package com.example.demo.models;

import org.junit.Assert;
import org.junit.Test;

import static com.example.demo.models.EType.CHECK_BOX;
import static com.example.demo.models.EType.IMAGE;
import static com.example.demo.models.EType.SIMPLE;

public class ETypeTest {
    @Test
    public void test_getValue() {
        Assert.assertEquals(0L, (long) SIMPLE.getValue());
        Assert.assertEquals(2L, (long) CHECK_BOX.getValue());
        Assert.assertEquals(1L, (long) IMAGE.getValue());
    }

    @Test
    public void test_toString() {
        Assert.assertEquals("0", SIMPLE.toString());
        Assert.assertEquals("2", CHECK_BOX.toString());
        Assert.assertEquals("1", IMAGE.toString());
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_getEnumThrowException() {
        Assert.assertEquals(SIMPLE, EType.getEnum("0"));
    }
}
