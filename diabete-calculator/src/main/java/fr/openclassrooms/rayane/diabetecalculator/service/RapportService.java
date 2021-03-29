package fr.openclassrooms.rayane.diabetecalculator.service;

import fr.openclassrooms.rayane.diabetecalculator.constant.RiskLevelConstant;
import fr.openclassrooms.rayane.diabetecalculator.entity.Note;
import fr.openclassrooms.rayane.diabetecalculator.entity.Patient;
import fr.openclassrooms.rayane.diabetecalculator.entity.Rapport;
import fr.openclassrooms.rayane.diabetecalculator.proxy.NoteProxy;
import fr.openclassrooms.rayane.diabetecalculator.proxy.PatientProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RapportService {
    @Autowired RisckCalculatorService risckCalculatorService;

    @Autowired
    NoteProxy noteProxy;

    @Autowired
    PatientProxy patientProxy;

    public Rapport createRapport(int patientId) {
        List<Note> patientFile = noteProxy.getNotesByPatientId(patientId);
        Patient patient = patientProxy.getPatientByLastName(patientFile.get(1).getFamilyName());
        RiskLevelConstant riskLevel = risckCalculatorService.calculateRisk(patientFile, patient);
        return new Rapport(patient, riskLevel);
    }


}
