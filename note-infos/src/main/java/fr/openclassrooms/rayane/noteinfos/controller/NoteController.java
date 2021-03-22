package fr.openclassrooms.rayane.noteinfos.controller;

import fr.openclassrooms.rayane.noteinfos.entity.Note;
import fr.openclassrooms.rayane.noteinfos.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/patHistory")
public class NoteController {

    @Autowired
    NoteService noteService;

    @GetMapping(value = "/get/{patientId}")
    public List<Note> getNotesByPatientId(@PathVariable int patientId) {
        return noteService.getNoteByPatientId(patientId);
    }

    @GetMapping(value = "/get")
    public Note getSpecificNoteFromPatient(@RequestParam int patientId, @RequestParam int noteNumber) {
        return noteService.getNoteByPatientIdAndNoteNumber(patientId, noteNumber);
    }

    @PostMapping(value = "/add")
    public Note addNote(@RequestBody Note note) {
        return noteService.addNote(note);
    }

    @PutMapping(value = "/update")
    public Note updateNote(@RequestParam int patientId, @RequestParam int noteNumber, @RequestBody Note note) {
        return noteService.updateNote(patientId, noteNumber, note);
    }
}
