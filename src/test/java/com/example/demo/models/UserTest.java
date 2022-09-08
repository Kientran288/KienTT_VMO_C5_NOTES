package com.example.demo.models;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

public class UserTest {
    private User user;

    private User user1;

    @Before
    public void init() {
        user = new User();
        user.setId(1L);
        Set<Role> roleSet = new HashSet<>();
        roleSet.add(new Role(ERole.ROLE_ADMIN));
        user.setRoles(roleSet);
        user.setEmail("kientt@vmodev.com");
        user.setPassword("pass");
        user.setUsername("kientt");

        user1 = new User("kent", "kent@vmodev.com", "pass");
    }

    @Test
    public void testSetterAndGetter() {
        Assert.assertTrue(1 == user.getId());
        Assert.assertEquals("kientt@vmodev.com", user.getEmail());
        Assert.assertEquals("pass", user.getPassword());
        Assert.assertEquals("kientt", user.getUsername());
        Assert.assertTrue(1 == user.getRoles().size());

        Assert.assertEquals("kent@vmodev.com", user1.getEmail());
        Assert.assertEquals("kent", user1.getUsername());
        Assert.assertEquals("pass", user1.getPassword());
        Assert.assertNull(user1.getId());
    }
}