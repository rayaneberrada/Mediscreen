package fr.openclassrooms.rayane.diabetecalculator.controller;

import fr.openclassrooms.rayane.diabetecalculator.entity.Rapport;
import fr.openclassrooms.rayane.diabetecalculator.service.RapportService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/assess")
public class RapportController {

    @Autowired
    RapportService rapportService;

    @GetMapping(value = "/{id}")
    public Rapport getRapportByUserId(@PathVariable int id) {
        return rapportService.createRapport(id);
    }

    @GetMapping(value = "/{familyName}")
    public Rapport getRapportByUserFamilyName(@PathVariable String familyName) {
        return null;
    }
}
