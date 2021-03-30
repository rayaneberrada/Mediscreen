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
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@Controller
@RequestMapping(value = "/patient")
public class PatientController {

    private Logger logger = LoggerFactory.getLogger(PatientController.class);

    @Autowired
    PatientService patientService;

    @GetMapping(value = "/infos")
    public String getPatientByLastName(@RequestParam("lastName") String lastName, Model model) {
        logger.info(String.format("http://localhost:8080/getPatient?lastName=%s", lastName));
        model.addAttribute("patientInfos", patientService.findByLastName(lastName));
        return "infos";
    }

    @GetMapping(
            value = "/update")
    public String showUpdateForm(@RequestParam String lastName, PatientDto patient, Model model) {
        logger.info(String.format("http://localhost:8080/patient/update?lastName=%s", lastName));
        PatientDto patientToUpdate = patientService.findByLastName(lastName);
        model.addAttribute("patientDto", patientToUpdate);
        return "update";
    }

    @PostMapping(
            value = "/update")
    public String updatePatient(@RequestParam String lastName, @Valid PatientDto patient, BindingResult result) {
        logger.info(String.format("http://localhost:8080/patient/update?lastName=%s", lastName));
        if (result.hasErrors()) {
            return "update";
        }

        patientService.updatePatient(lastName, patient);
        return "redirect:/";
    }

    @GetMapping(
            value = "/add")
    public String showAddForm(PatientDto patient) {
        logger.info("http://localhost:8080/patient/add");
        return "add";
    }

    @PostMapping(
            value = "/validate")
    public String validate(@Valid PatientDto patient, BindingResult result, Model model) {
        logger.info("http://localhost:8080/validate/add");
        if (!result.hasErrors()) {
            patientService.addPatient(patient);
            return "redirect:/";
        }
        return "add";
    }
}
