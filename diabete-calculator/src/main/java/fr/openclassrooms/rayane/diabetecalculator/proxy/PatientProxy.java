package fr.openclassrooms.rayane.diabetecalculator.proxy;

import fr.openclassrooms.rayane.diabetecalculator.entity.Note;
import fr.openclassrooms.rayane.diabetecalculator.entity.Patient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient("patient")
@RequestMapping(value = "/patient")
public interface PatientProxy {

    @GetMapping(value = "/get")
    public Patient getPatientByLastName(@RequestParam String lastName);
}
