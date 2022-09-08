package com.note.demo.payload.request;

import com.note.demo.models.ERole;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

public class SignupRequestTest {
    private SignupRequest signupRequest;

    @Before
    public void init() {
        signupRequest = new SignupRequest();
        Set<String> roleSet = new HashSet<>();
        roleSet.add(ERole.ROLE_ADMIN.toString());
        signupRequest.setRole(roleSet);
        signupRequest.setEmail("kientt@vmodev.com");
        signupRequest.setPassword("pass");
        signupRequest.setUsername("kientt");

    }

    @Test
    public void testSetterAndGetter() {
        Assert.assertEquals("kientt@vmodev.com", signupRequest.getEmail());
        Assert.assertEquals("pass", signupRequest.getPassword());
        Assert.assertEquals("kientt", signupRequest.getUsername());
        Assert.assertTrue(1 == signupRequest.getRole().size());
    }
}