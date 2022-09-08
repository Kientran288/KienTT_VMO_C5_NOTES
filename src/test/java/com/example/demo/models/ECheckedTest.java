package com.example.demo.models;

import org.junit.Assert;
import org.junit.Test;

import static com.example.demo.models.EChecked.*;

public class ECheckedTest {
    @Test
    public void test_getValue() {
        Assert.assertEquals(0L, (long) CHECKED.getValue());
    }

    @Test
    public void test_toString() {
        Assert.assertEquals("0", CHECKED.toString());
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_getEnum() {
        Assert.assertEquals(CHECKED, EChecked.getEnum("CHECKED"));
    }
}