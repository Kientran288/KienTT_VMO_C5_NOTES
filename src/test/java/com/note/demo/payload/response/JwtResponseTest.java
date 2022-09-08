package com.note.demo.payload.response;

import com.note.demo.models.ERole;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class JwtResponseTest {
    private JwtResponse jwtResponse;

    private JwtResponse jwtResponse1;

    @Before
    public void init() {
        jwtResponse = new JwtResponse();
        Set<String> roleSet = new HashSet<>();
        roleSet.add(ERole.ROLE_ADMIN.toString());
        jwtResponse.setAccessToken("token");
        jwtResponse.setEmail("kientt@vmodev.com");
        jwtResponse.setTokenType("type");
        jwtResponse.setUsername("kientt");
        jwtResponse.setId(1L);

        // JwtResponse(String accessToken, Long id, String username, String email, List<String> roles)
        jwtResponse1 = new JwtResponse("token", 1L, "kientt", "kientt@vmodev.com", new ArrayList<>());
    }

    @Test
    public void testSetterAndGetter() {
        Assert.assertEquals("kientt@vmodev.com", jwtResponse.getEmail());
        Assert.assertEquals("type", jwtResponse.getTokenType());
        Assert.assertEquals(1, (long) jwtResponse.getId());
        Assert.assertEquals("token", jwtResponse.getAccessToken());
        Assert.assertNull(jwtResponse.getRoles());
        Assert.assertEquals("kientt", jwtResponse.getUsername());

        Assert.assertEquals("kientt@vmodev.com", jwtResponse1.getEmail());
        Assert.assertEquals("Bearer", jwtResponse1.getTokenType());
        Assert.assertEquals("token", jwtResponse1.getAccessToken());
        Assert.assertNotNull(jwtResponse1.getRoles());
        Assert.assertEquals("kientt", jwtResponse1.getUsername());
    }
}