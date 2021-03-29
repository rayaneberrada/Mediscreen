package fr.openclassrooms.rayane.noteinfos.service;

import fr.openclassrooms.rayane.noteinfos.entity.Note;
import fr.openclassrooms.rayane.noteinfos.repository.NoteRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class NoteServiceIt {

    @Autowired NoteService noteService;

    @Autowired
    NoteRepository noteRepository;

    @AfterAll
    public void cleanUp() {
        noteService.deleteNote("Test modifié");
        noteService.deleteNote("Deuxième note pour le test");
    }

    @Test
    @Order(1)
    public void Add_Note() {
        // GIVEN
        Note firstNote = new Note(999, "test", "Première note pour le test");
        Note secondNote = new Note(999, "test", "Deuxième note pour le test");

        // WHEN
        Note firstNoteAdded = noteService.addNote(firstNote);
        Note secondNoteAdded = noteService.addNote(secondNote);

        // THEN
        assertThat(firstNote.getPatId()).isEqualTo(firstNoteAdded.getPatId());

        assertThat(secondNote.getPatId()).isEqualTo(secondNoteAdded.getPatId());
    }

    @Test
    @Order(2)
    public void Get_NotesByPatientId() {
        // GIVEN
        int patientId = 999;

        // WHEN
        List<Note> notes = noteService.getNoteByPatientId(patientId);

        // THEN
        assertThat(notes.size()).isGreaterThan(1);
    }

    @Test
    @Order(3)
    public void Update_NoteDescription() {
    // GIVEN
    Note noteUpdate = noteRepository.findNoteByNote("Première note pour le test");
        String newDescription = "Test modifié";

        // WHEN
        noteUpdate.setNote(newDescription);
        Note noteUpdated = noteService.updateNote(noteUpdate.getId(), noteUpdate);

        // THEN
        assertThat(noteUpdated.getNote()).isEqualTo(newDescription);
    }
}
