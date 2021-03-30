package fr.openclassrooms.rayane.diabetecalculator.controller;

import fr.openclassrooms.rayane.diabetecalculator.entity.Rapport;
import fr.openclassrooms.rayane.diabetecalculator.service.RapportService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@Api("Pour les opérations CRUD sur les notes")
@RestController
@RequestMapping(value = "/assess")
public class RapportController {

    @Autowired
    RapportService rapportService;

  @ApiOperation(
      value = "Crée et renvoie le rapport lié au patient d'id patId",
      notes = "Exemple d'appel: curl -d \"patId=1\" -X POST http://localhost:8080/assess/id")
  @PostMapping(
      value = "/id",
      consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public Rapport getRapportByUserId(int patId) {
        return rapportService.createRapportByPatientId(patId);
    }

  @ApiOperation(
      value = "Crée et renvoie le rapport lié au patient de nom de famille familyName",
      notes = "Exemple d'appel: curl -d \"familyName=TestNone\" -X POST http://localhost:8080/assess/familyName")
  @PostMapping(
      value = "/familyName",
      consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public Rapport getRapportByUserFamilyName(String familyName) {
        return rapportService.createRapportByFamilyName(familyName);
    }
}
