package com.note.demo.services;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
public class AuthServiceImplTest {

    @InjectMocks
    NoteService noteService;

    @Test
    void authenticateUser() {
    }

    @Test
    void registerUser() {
    }
}