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
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@Api("Api pour les opérations crud sur la table patient")
@RestController
@RequestMapping(value = "/patient")
public class PatientController {

    private Logger logger = LoggerFactory.getLogger(PatientController.class);

    @Autowired
    PatientService patientService;

  @ApiOperation(
      value = "Récupère et affiche un patient en le cherchant en bdd via son son nom",
      notes =
          "Exemple d'appel: curl GET http://localhost:8081/patient/get?lastName=TestNone")
  @GetMapping(value = "/get",
              produces = MediaType.APPLICATION_JSON_VALUE)
  public PatientDto getPatientByLastName(@RequestParam("lastName") String lastName) {
        logger.info(String.format("http://localhost:8080/getPatient?lastName=%s", lastName));
        return patientService.findByLastName(lastName);
    }

  @ApiOperation(
      value = "Modifie un patient avec les données fournies dans le body en le cherchant en bdd via son prénom et son nom",
      notes = "Exemple d'appel: curl -d \"family=TestNone&given=Test&dob=1966-12-31&sex=F&address=1 Brookside St&phone=100-222-7777\" -X PUT http://localhost:8081/patient/update?lastName=TestNone")
  @PutMapping(
      value = "/update",
      consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public PatientDto updatePatient(@RequestParam String lastName, PatientDto patient) {
        logger.info(String.format("http://localhost:8080/patient/update?lastName=%s", lastName));
        return patientService.updatePatient(lastName, patient);
    }

  @ApiOperation(
      value = "Ajoute un patient en bdd en utilisant les données fournies dans body",
      notes =
          "\"Exemple d'appel: curl -d \\\"family=TestNone&given=Test&dob=1966-12-31&sex=F&address=1 Brookside St&phone=100-222-3333\\\" -X POST http://localhost:8081/patient/add")
  @PostMapping(
      value = "/add",
      consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public PatientDto addPatient(PatientDto patient) {
        logger.info("http://localhost:8080/updatePatient");
        return patientService.addPatient(patient);
    }
}
