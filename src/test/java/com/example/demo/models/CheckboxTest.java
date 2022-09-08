package com.example.demo.models;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CheckboxTest {
    private Checkbox checkbox;

    private Checkbox checkbox1;

    @Before
    public void init() {
        checkbox = new Checkbox();
        checkbox.setChecked(1);
        checkbox.setContent("Eat");

        checkbox1 = new Checkbox("Hello", 2);
    }

    @Test
    public void testSetterAndGetter() {
        Assert.assertEquals(1, checkbox.getChecked());
        Assert.assertEquals("Eat", checkbox.getContent());
        Assert.assertEquals(2, checkbox1.getChecked());
        Assert.assertEquals("Hello", checkbox1.getContent());
        Assert.assertNull(checkbox1.getId());
    }
}