package fr.openclassrooms.rayane.noteinfos.service;

import fr.openclassrooms.rayane.noteinfos.entity.Note;
import fr.openclassrooms.rayane.noteinfos.repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class NoteService {

    @Autowired
    NoteRepository noteRepository;

    public List<Note> getNoteByPatientId(int patientId) {
        return noteRepository.findNoteByPatId(patientId);
    }

    public Note getNoteByPatientIdAndNoteNumber(int patientId, int noteNumber) {
        return noteRepository.findNoteByPatIdAndNoteNumber(patientId, noteNumber);
    }

    public Note addNote(Note note) {
        return noteRepository.save(note);
    }

    public Note updateNote(int patientId, int noteNumber, Note note) {
        Note noteToUpdate = noteRepository.findNoteByPatIdAndNoteNumber(patientId, noteNumber);

        // note modified with informations from new note
        noteToUpdate.setNoteNumber(note.getNoteNumber());
        noteToUpdate.setDateWritten(new Date());
        noteToUpdate.setPatient(note.getPatient());
        noteToUpdate.setNote(note.getNote());

        return noteRepository.save(noteToUpdate);
    }
}
