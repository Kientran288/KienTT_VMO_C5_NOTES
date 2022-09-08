package com.example.demo.payload.request;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CheckboxRequestTest {
    private CheckboxRequest checkboxRequest;

    private CheckboxRequest checkboxRequest1;

    @Before
    public void init() {
        checkboxRequest = new CheckboxRequest();
        checkboxRequest.setChecked(1);
        checkboxRequest.setContent("Eat");

        checkboxRequest1 = new CheckboxRequest("Hello", 2);
    }

    @Test
    public void testSetterAndGetter() {
        Assert.assertEquals(1, checkboxRequest.getChecked());
        Assert.assertEquals("Eat", checkboxRequest.getContent());
        Assert.assertEquals(2, checkboxRequest1.getChecked());
        Assert.assertEquals("Hello", checkboxRequest1.getContent());
    }
}