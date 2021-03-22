package fr.openclassrooms.rayane.noteinfos.repository;

import fr.openclassrooms.rayane.noteinfos.entity.Note;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface NoteRepository extends MongoRepository<Note, String> {
    public List<Note> findNoteByPatId(int patId);

    public Note findNoteByPatIdAndNoteNumber(int patId, int noteNumber);
}
