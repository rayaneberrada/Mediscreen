package fr.openclassrooms.rayane.diabetecalculator.controller;

import fr.openclassrooms.rayane.diabetecalculator.service.RapportService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(value = "/assess")
public class RapportControllerView {

    private Logger logger = LoggerFactory.getLogger(RapportControllerView.class);

    @Autowired
    RapportService rapportService;

    @GetMapping(value = "/infos")
    public String getPatientByLastName(@RequestParam("lastName") String lastName, Model model) {
        logger.info(String.format("http://localhost:8080/getPatient?lastName=%s", lastName));
        model.addAttribute("rapport", rapportService.createRapportByFamilyName(lastName));
        return "infos";
    }
}
