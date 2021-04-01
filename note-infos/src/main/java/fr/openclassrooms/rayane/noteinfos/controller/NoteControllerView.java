package fr.openclassrooms.rayane.noteinfos.controller;

import fr.openclassrooms.rayane.noteinfos.entity.Note;
import fr.openclassrooms.rayane.noteinfos.service.NoteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping(value = "/note")
public class NoteControllerView {

    private Logger logger = LoggerFactory.getLogger(NoteControllerView.class);

    @Autowired
    NoteService noteService;

    @GetMapping(value = "/infos")
    public String getPatientByLastName(@RequestParam("lastName") String lastName, Model model) {
        logger.info(String.format("http://localhost:8080/getPatient?lastName=%s", lastName));
        model.addAttribute("notes", noteService.getNoteByPatientName(lastName));
        return "infos";
    }

    @GetMapping(
            value = "/update")
    public String showUpdateForm(@RequestParam String id, Note note, Model model) {
        logger.info(String.format("http://localhost:8080/note/update?id=%s", id));
        Note noteToUpdate = noteService.getNoteById(id);
        logger.info(String.valueOf(noteToUpdate));
        model.addAttribute("noteDto", noteToUpdate);
        return "update";
    }

    @PostMapping(
            value = "/update")
    public String updateNote(@RequestParam String id, @Valid Note note, BindingResult result) {
        logger.info(String.format("http://localhost:8080/note/update?id=%s", id));
        if (result.hasErrors()) {
            return "update";
        }

        noteService.updateNote(id, note);
        return "redirect:/";
    }

    @GetMapping(
            value = "/add")
    public String showAddForm(Note note) {
        logger.info("http://localhost:8080/note/add");
        return "add";
    }

    @PostMapping(
            value = "/validate")
    public String validate(@Valid Note note, BindingResult result, Model model) {
        logger.info("http://localhost:8080/validate/add");
        if (!result.hasErrors()) {
            noteService.addNote(note);
            return "redirect:/";
        }
        return "add";
    }
}
