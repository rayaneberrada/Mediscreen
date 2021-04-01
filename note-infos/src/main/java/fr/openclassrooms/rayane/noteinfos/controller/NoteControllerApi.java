package fr.openclassrooms.rayane.noteinfos.controller;

import fr.openclassrooms.rayane.noteinfos.entity.Note;
import fr.openclassrooms.rayane.noteinfos.service.NoteService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api("Pour les opérations CRUD sur les notes")
@RestController
@RequestMapping(value = "/patHistory")
public class NoteControllerApi {

    @Autowired
    NoteService noteService;

    @ApiOperation(
            value = "Récupère et affiche la liste des notes en fonction de l'id d'un patient",
            notes = "Exemple d'appel: curl GET http://localhost:8082/patHistory/get/1")
    @GetMapping(value = "/get/{patientId}")
    public List<Note> getNotesByPatientId(@PathVariable("patientId") int patientId) {
        return noteService.getNoteByPatientId(patientId);
    }

    @ApiOperation(
            value = "Récupère et affiche la liste des notes en fonction du nom d'un patient",
            notes = "Exemple d'appel: curl GET http://localhost:8082/patHistory/get?patientName=TestNone")
    @GetMapping(value = "/get")
    List<Note> getNotesFromPatientFamilyName(@RequestParam("patientName") String patientName) {
        return noteService.getNoteByPatientName(patientName);
    }

  @ApiOperation(
      value = "Ajoute une nouvelle note dans la base de données",
      notes = "Exemple d'appel: curl -d \"patId=1&patient=TestNone&note=Patient states that they are 'feeling terrific' Weight at or below recommended level\" -X POST http://localhost:8082/patHistory/add")
  @PostMapping(
      value = "/add",
      consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public Note addNote(Note note) {
        return noteService.addNote(note);
    }

    @ApiOperation(
            value = "Récupère une note via son id et la modifie par la nouvelle note fournie",
            notes = "Exemple d'appel: curl -d \"patId=1&patient=TestNone&note=Patient states that they are 'feeling great' Weight at or below recommended level\" -X PUT http://localhost:8082/patHistory/update?noteId=6060e7245614fc71e86a09dc")
    @PutMapping(value = "/update",
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Note updateNote(@RequestParam String noteId, Note note) {
        return noteService.updateNote(noteId, note);
    }
}
