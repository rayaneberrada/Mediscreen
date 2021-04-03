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

    /**
     * Method to create a rapport using the id of a patient
     *
     * @param patientId
     * @return a Rapport
     */
    public Rapport createRapportByPatientId(int patientId) {
        Rapport rapport = new Rapport();
        List<Note> patientFile = noteProxy.getNotesByPatientId(patientId);
        Patient patient = patientProxy.getPatientByLastName(patientFile.get(1).getPatient());

        rapport.setPatientNameAndAge(patient);
        rapport.setNiveauDeRisque(risckCalculatorService.calculateRisk(patientFile, patient));

        return rapport;
    }

    /**
     * Method to create a rapport using the last name of a patient
     *
     * @param familyName
     * @return a Rapport
     */
    public Rapport createRapportByFamilyName(String familyName) {
        Rapport rapport = new Rapport();
        List<Note> patientFile = noteProxy.getNotesFromPatientFamilyName(familyName);
        Patient patient = patientProxy.getPatientByLastName(patientFile.get(1).getPatient());

        rapport.setPatientNameAndAge(patient);
        rapport.setNiveauDeRisque(risckCalculatorService.calculateRisk(patientFile, patient));

        return rapport;
    }

}
