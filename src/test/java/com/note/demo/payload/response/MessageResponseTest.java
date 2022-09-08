package com.note.demo.payload.response;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class MessageResponseTest {
    private MessageResponse messageResponse;

    private MessageResponse messageResponse1;

    @Before
    public void init() {
        messageResponse = new MessageResponse();
        messageResponse.setMessage("Eat");

        messageResponse1 = new MessageResponse("Hello");
    }

    @Test
    public void testSetterAndGetter() {
        Assert.assertEquals("Eat", messageResponse.getMessage());
        Assert.assertEquals("Hello", messageResponse1.getMessage());
    }
}