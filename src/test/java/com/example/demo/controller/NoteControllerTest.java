package com.example.demo.controller;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@WebMvcTest(NoteController.class)
@AutoConfigureMockMvc
@TestPropertySource(
        locations = "classpath:application-integrationtest.properties")
class NoteControllerTest {



    @Test
    void getAllNote() {
//        Mockito.when(authService.authenticateUser(Mockito.any(LoginRequest.class)))
//                .thenReturn(Mockito.any());
    }

    @Test
    void getNoteById() {
    }

    @Test
    void createNote() {
    }

    @Test
    void updateNote() {
    }

    @Test
    void deleteNote() {
    }

    @Test
    void deleteAllNotes() {
    }
}