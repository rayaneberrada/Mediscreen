package fr.openclassrooms.rayane.diabetecalculator.service;

import fr.openclassrooms.rayane.diabetecalculator.constant.RiskLevelConstant;
import fr.openclassrooms.rayane.diabetecalculator.constant.SymptomsConstant;
import fr.openclassrooms.rayane.diabetecalculator.entity.Note;
import fr.openclassrooms.rayane.diabetecalculator.entity.Patient;
import fr.openclassrooms.rayane.diabetecalculator.entity.Rapport;
import org.springframework.stereotype.Service;

import javax.xml.crypto.Data;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service
public class RisckCalculatorService implements RiskCalculator{

    /**
     * Take a list of notes and a patient and calculate the risk, depending of the informations contained in those notes,
     * the risk the patient get diabete
     *
     * @param file
     * @param patient
     * @return a RiskLevelConstant level
     */
    @Override
    public RiskLevelConstant calculateRisk(List<Note> file, Patient patient) {
        int patientFileSymptomsOccurences = 0;
        RiskLevelConstant riskLevel = RiskLevelConstant.NONE;

        for (Note note: file) {
            patientFileSymptomsOccurences += calculateNoteSymptomsOccurence(note);
        }

        if (checkPatientIsEarlyOnSet(patientFileSymptomsOccurences, patient)) {
            riskLevel = RiskLevelConstant.EARLYONSET;
        } else if (checkPatientIsInDanger(patientFileSymptomsOccurences, patient)) {
            riskLevel = RiskLevelConstant.DANGER;
        } else if (checkPatientIsBorderline(patientFileSymptomsOccurences, patient)) {
                riskLevel = RiskLevelConstant.BORDERLINE;
        } else if (checkPatientisSafe(patientFileSymptomsOccurences)) {
                riskLevel = RiskLevelConstant.NONE;
        }
        
        return riskLevel;
    }

    /**
     * Calculate the amount of occurence of symptoms defined in SymptomsConstant in a note
     *
     * @param note
     * @return the amount of symptoms found in a note
     */
    public int calculateNoteSymptomsOccurence(Note note) {
        int symptomsOccurence = 0;
        for (String word: cleanNotes(note.getNote())) {
            for (SymptomsConstant symptom: SymptomsConstant.values()) {
                if (word.equals(symptom.getLibelle())) {
                    symptomsOccurence += 1;
                }
            }
        }

        return symptomsOccurence;
    }

    /**
     * Retrieve punctuation and separate each word
     *
     * @param note
     * @return a list containing each word retrieve from the note
     */
    private String[] cleanNotes(String note) {
        return note.replaceAll("\\p{Punct}", " ").toLowerCase().split(" ");
    }

    private int calculateAge(LocalDate birthdate) {
        return Period.between(birthdate,  LocalDate.now()).getYears();
    }

  /***********************************************************************************************
   *
   * Methods to know which level of danger the patient is to get diabete
   *
   **********************************************************************************************/

  /**
   * This method set riskLevel to SAFE if:
   *    - patient has no symptoms
   *
   * @param symptomsOccurence
   */
  private Boolean checkPatientisSafe(int symptomsOccurence) {
               return symptomsOccurence == 0;
    }

    /**
     * This method set riskLevel to BORDERLINE if:
     *   - patient has 2 or more symptoms and is over 30
     *
     * @param symptomsOccurence
     * @param patient
     */
    private Boolean checkPatientIsBorderline(int symptomsOccurence, Patient patient) {
        int patientAge = calculateAge(patient.getDob());
        return symptomsOccurence >= 2 && patientAge > 30;
    }

    /**
     * This method set riskLevel to DANGER if:
     *   - patient has 3 or more symptoms, is a man and is under 30
     *   - patient has 4 or more symptoms, is a woman and is under 30
     *   - patient has 6 or more symptoms and is over 30
     *
     * @param symptomsOccurence
     * @param patient
     */
    private Boolean checkPatientIsInDanger(int symptomsOccurence, Patient patient) {
        int patientAge = calculateAge(patient.getDob());
        return symptomsOccurence >= 3 && patient.getSex().equals("H") &&  patientAge < 30 ||
                symptomsOccurence >= 4 && patient.getSex().equals("F") &&  patientAge < 30 ||
                symptomsOccurence >= 6 &&  patientAge > 30;
    }

    /**
     * This method set riskLevel to EARLYONSET if:
     *   - patient has 5 or more symptoms, is a man and is under 30
     *   - patient has 7 or more symptoms, is a woman and is under 30
     *   - patient has 8 or more symptoms and is over 30
     *
     * @param symptomsOccurence
     * @param patient
     */
    private Boolean checkPatientIsEarlyOnSet(int symptomsOccurence, Patient patient) {
        int patientAge = calculateAge(patient.getDob());
        return symptomsOccurence >= 5 && patient.getSex().equals("H") &&  patientAge < 30 ||
                symptomsOccurence >= 7 && patient.getSex().equals("F") &&  patientAge < 30 ||
                symptomsOccurence >= 8 &&  patientAge > 30;
    }
}
