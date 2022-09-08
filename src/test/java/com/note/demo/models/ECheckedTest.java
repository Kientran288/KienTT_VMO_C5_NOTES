package com.note.demo.models;

import org.junit.Assert;
import org.junit.Test;

public class ECheckedTest {
    @Test
    public void test_getValue() {
        Assert.assertEquals(0L, (long) EChecked.CHECKED.getValue());
    }

    @Test
    public void test_toString() {
        Assert.assertEquals("0", EChecked.CHECKED.toString());
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_getEnum() {
        Assert.assertEquals(EChecked.CHECKED, EChecked.getEnum("CHECKED"));
    }
}