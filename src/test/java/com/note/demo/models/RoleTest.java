package com.note.demo.models;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class RoleTest {
    private Role role;

    private Role role1;

    @Before
    public void init() {
        role = new Role();
        role.setId(1);
        role.setName(ERole.ROLE_ADMIN);

        role1 = new Role(ERole.ROLE_USER);
    }

    @Test
    public void testSetterAndGetter() {
        Assert.assertTrue(1 == role.getId());
        Assert.assertEquals(ERole.ROLE_ADMIN, role.getName());
        Assert.assertEquals(ERole.ROLE_USER, role1.getName());
        Assert.assertNull(role1.getId());
    }
}