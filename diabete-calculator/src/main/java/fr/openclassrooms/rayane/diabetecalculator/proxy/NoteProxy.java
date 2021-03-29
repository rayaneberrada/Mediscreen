package fr.openclassrooms.rayane.diabetecalculator.proxy;

import fr.openclassrooms.rayane.diabetecalculator.entity.Note;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@FeignClient("note")
@RequestMapping(value = "/patHistory")
public interface NoteProxy {

    @GetMapping(value = "/get/{patientId}")
    public List<Note> getNotesByPatientId(@PathVariable int patientId);
}
