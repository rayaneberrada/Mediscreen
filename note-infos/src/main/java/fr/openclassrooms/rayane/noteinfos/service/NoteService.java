package fr.openclassrooms.rayane.noteinfos.service;

import fr.openclassrooms.rayane.noteinfos.entity.Note;
import fr.openclassrooms.rayane.noteinfos.repository.NoteRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Service
public class NoteService {

    @Autowired
    NoteRepository noteRepository;

    public Note getNoteById(String id) {
        return noteRepository.findNoteById(id);
    }

    public List<Note> getNoteByPatientId(int patientId) {
        return noteRepository.findNoteByPatId(patientId);
    }

    public List<Note> getNoteByPatientName(String patientName) {
        return noteRepository.findNoteByPatient(patientName);
    }

/*    public Note getNoteByPatientIdAndNoteNumber(int patientId, int noteNumber) {
        return noteRepository.findNoteByPatIdAndNoteNumber(patientId, noteNumber);
    }*/

    public Note addNote(Note note) {
        note.setDateWritten(LocalDate.now());
        return noteRepository.save(note);
    }

    public Note updateNote(String noteId, Note note) {
        Note noteToUpdate = noteRepository.findNoteById(noteId);

        // note modified with informations from new note
        noteToUpdate.setDateWritten(LocalDate.now());
        noteToUpdate.setNote(note.getNote());

        return noteRepository.save(noteToUpdate);
    }

    public void deleteNote(String note) {
        Note noteToDelete = noteRepository.findNoteByNote(note);
        noteRepository.delete(noteToDelete);
    }
}
