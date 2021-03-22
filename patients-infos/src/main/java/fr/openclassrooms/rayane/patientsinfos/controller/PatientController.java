package fr.openclassrooms.rayane.patientsinfos.controller;

import fr.openclassrooms.rayane.patientsinfos.dto.PatientDto;
import fr.openclassrooms.rayane.patientsinfos.entity.Patient;
import fr.openclassrooms.rayane.patientsinfos.repository.PatientRepository;
import fr.openclassrooms.rayane.patientsinfos.service.PatientService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api("Api pour les opérations crud sur la table patient")
@RestController
@RequestMapping(value = "/patient")
public class PatientController {

    private Logger logger = LoggerFactory.getLogger(PatientController.class);

    @Autowired
    PatientService patientService;

    @ApiOperation(value = "Récupère et affiche un patient en le cherchant en bdd via son prénom et son nom")
    @GetMapping(value = "/get")
    public PatientDto getPatientByFirstNameAndLastName(@RequestParam String firstName, @RequestParam String lastName) {
        logger.info(String.format("http://localhost:8080/getPatient?firstName=%s&lastName=%s", firstName, lastName));
        return patientService.findByNameAndLastName(firstName, lastName);
    }

    @ApiOperation(value = "Modifie un patient avec les données fournies dans le body en le cherchant en bdd via son prénom et son nom")
    @PutMapping(value = "/update")
    public PatientDto updatePatient(@RequestParam String firstName, @RequestParam String lastName, @RequestBody PatientDto patient) {
        logger.info(String.format("http://localhost:8080/updatePatient?firstName=%s&lastName=%s", firstName, lastName));
        return patientService.updatePatient(firstName, lastName, patient);
    }

    @ApiOperation(value = "Ajoute un patient en bdd en utilisant les données fournies dans body")
    @PostMapping(value = "/add")
    public PatientDto addPatient(@RequestBody PatientDto patient) {
        logger.info("http://localhost:8080/updatePatient");
        return patientService.addPatient(patient);
    }
}
