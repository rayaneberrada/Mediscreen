package fr.openclassrooms.rayane.noteinfos.service;

import fr.openclassrooms.rayane.noteinfos.entity.Note;
import fr.openclassrooms.rayane.noteinfos.repository.NoteRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class NoteService {

    @Autowired
    NoteRepository noteRepository;

    /**
     * Method to retrieve a note by it's id
     *
     * @param id
     * @return a Note
     */
    public Note getNoteById(String id) {
        return noteRepository.findNoteById(id).orElseThrow(NoSuchElementException::new);
    }

    /**
     * Method to retrieve all the notes from a patient by it's id
     *
     * @param patientId
     * @return a list of notes
     */
    public List<Note> getNoteByPatientId(int patientId) {
        return noteRepository.findNoteByPatId(patientId);
    }

    /**
     * Method to retrieve all the notes from a patient by it's name
     *
     * @param patientName
     * @return a list of notes
     */
    public List<Note> getNoteByPatientName(String patientName) {
        return noteRepository.findNoteByPatient(patientName);
    }

/*    public Note getNoteByPatientIdAndNoteNumber(int patientId, int noteNumber) {
        return noteRepository.findNoteByPatIdAndNoteNumber(patientId, noteNumber);
    }*/

    /**
     * Method to add a note in db
     *
     * @param note
     * @return Note added
     */
    public Note addNote(Note note) {
        note.setDateWritten(LocalDate.now());
        return noteRepository.save(note);
    }

    /**
     * Method to update a note by it's id
     *
     * @param noteId
     * @param note
     * @return Note updated
     */
    public Note updateNote(String noteId, Note note) {
        Note noteToUpdate = noteRepository.findNoteById(noteId).orElseThrow(NoSuchElementException::new);

        // note modified with informations from new note
        noteToUpdate.setDateWritten(LocalDate.now());
        noteToUpdate.setNote(note.getNote());

        return noteRepository.save(noteToUpdate);
    }


    /**
     * Method to delete a note by it's note text
     *
     * @param note
     */
    public void deleteNote(String note) {
        Note noteToDelete = noteRepository.findNoteByNote(note).orElseThrow(NoSuchElementException::new);
        noteRepository.delete(noteToDelete);
    }
}
