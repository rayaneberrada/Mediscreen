package fr.openclassrooms.rayane.noteinfos.service;

import fr.openclassrooms.rayane.noteinfos.entity.Note;
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

    @AfterAll
    public void cleanUp() {
        noteService.deleteNote(999, 1);
        noteService.deleteNote(999, 2);
    }

    @Test
    @Order(1)
    public void Add_Note() {
        // GIVEN
        Note firstNote = new Note(1, 999, "test", "Première note pour le test");
        Note secondNote = new Note(2, 999, "test", "Deuxième note pour le test");

        // WHEN
        Note firstNoteAdded = noteService.addNote(firstNote);
        Note secondNoteAdded = noteService.addNote(secondNote);

        // THEN
        assertThat(firstNote.getPatId()).isEqualTo(firstNoteAdded.getPatId());
        assertThat(firstNote.getNoteNumber()).isEqualTo(firstNoteAdded.getNoteNumber());

        assertThat(secondNote.getPatId()).isEqualTo(secondNoteAdded.getPatId());
        assertThat(secondNote.getNoteNumber()).isEqualTo(secondNoteAdded.getNoteNumber());
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
        Note noteUpdate = noteService.getNoteByPatientIdAndNoteNumber(999, 1);
        String newDescription = "Test modifié";

        // WHEN
        noteUpdate.setNote(newDescription);
        Note noteUpdated = noteService.updateNote(999, 1, noteUpdate);

        // THEN
        assertThat(noteUpdated.getNote()).isEqualTo(newDescription);
    }
}
