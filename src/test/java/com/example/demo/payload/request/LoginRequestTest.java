package com.example.demo.payload.request;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class LoginRequestTest {
    private LoginRequest loginRequest;

    private LoginRequest loginRequest1;

    @Before
    public void init() {
        loginRequest = new LoginRequest();
        loginRequest.setPassword("pass");
        loginRequest.setUsername("kientt");

        loginRequest1 = new LoginRequest("kent", "pass");
    }

    @Test
    public void testSetterAndGetter() {
        Assert.assertEquals("pass", loginRequest.getPassword());
        Assert.assertEquals("kientt", loginRequest.getUsername());

        Assert.assertEquals("kent", loginRequest1.getUsername());
        Assert.assertEquals("pass", loginRequest1.getPassword());
    }
}