package fr.openclassrooms.rayane.noteinfos.repository;

import fr.openclassrooms.rayane.noteinfos.entity.Note;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface NoteRepository extends MongoRepository<Note, String> {
    List<Note> findNoteByPatId(int patId);

    List<Note>  findNoteByPatient(String patientName);

    Note findNoteById(String id);

    Note findNoteByNote(String note);
}
