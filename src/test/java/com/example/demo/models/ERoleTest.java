package com.example.demo.models;

import org.junit.Assert;
import org.junit.Test;

public class ERoleTest {
    @Test
    public void test_getValue() {
        Assert.assertEquals("ROLE_USER", ERole.ROLE_USER.name());
        Assert.assertEquals("ROLE_MODERATOR", ERole.ROLE_MODERATOR.name());
        Assert.assertEquals("ROLE_ADMIN", ERole.ROLE_ADMIN.name());
    }
}