package fr.openclassrooms.rayane.noteinfos.repository;

import fr.openclassrooms.rayane.noteinfos.entity.Note;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface NoteRepository extends MongoRepository<Note, String> {
    List<Note> findNoteByPatId(int patId);

    List<Note>  findNoteByPatient(String patientName);

    Optional<Note> findNoteById(String id);

    Optional<Note> findNoteByNote(String note);
}
