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

@Service
public class RisckCalculatorService implements RiskCalculator{

    static  RiskLevelConstant riskLevel;

    @Override
    public RiskLevelConstant calculateRisk(Note note, Patient patient) {
        int symptomsOccurence = 0;
        for (String word: cleanNotes(note.getNote())) {
            for (SymptomsConstant symptom: SymptomsConstant.values()) {
                System.out.println(symptom.toString()); // TO DELETE
                if (word.equals(symptom.toString())) {
                    symptomsOccurence += 1;
                }
            }
        }
        checkPatientisSafe(symptomsOccurence);
        checkPatientIsBorderline(symptomsOccurence, patient);
        checkPatientIsInDanger(symptomsOccurence, patient);
        checkPatientIsEarlyOnSet(symptomsOccurence, patient);

        return riskLevel;
    }

    public String[] cleanNotes(String note) {
        return note.replaceAll("\\p{Punct}", " ").toLowerCase().split(" ");
    }

    static int calculateAge(Date birthdate) {
        LocalDate birthDateLocal = birthdate.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();

        return Period.between(birthDateLocal,  LocalDate.now()).getYears();
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
  static void checkPatientisSafe(int symptomsOccurence) {
        if (symptomsOccurence > 0) {
               riskLevel = RiskLevelConstant.NONE;
        }
    }

    /**
     * This method set riskLevel to BORDERLINE if:
     *   - patient has 2 or more symptoms and is over 30
     *
     * @param symptomsOccurence
     * @param patient
     */
    static void checkPatientIsBorderline(int symptomsOccurence, Patient patient) {
        int patientAge = calculateAge(patient.getDate_naissance());
        if (symptomsOccurence >= 2 && patientAge > 30) {
            riskLevel = RiskLevelConstant.BORDERLINE;
        }
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
    static void checkPatientIsInDanger(int symptomsOccurence, Patient patient) {
        int patientAge = calculateAge(patient.getDate_naissance());
        if (symptomsOccurence >= 3 && patient.getGenre().equals("homme") &&  patientAge < 30) {
            riskLevel = RiskLevelConstant.DANGER;
        } else if (symptomsOccurence >= 4 && patient.getGenre().equals("femme") &&  patientAge < 30) {
            riskLevel = RiskLevelConstant.DANGER;
        } else if (symptomsOccurence >= 6 &&  patientAge > 30) {
            riskLevel = RiskLevelConstant.DANGER;
        }
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
    static void checkPatientIsEarlyOnSet(int symptomsOccurence, Patient patient) {
        int patientAge = calculateAge(patient.getDate_naissance());
        if (symptomsOccurence >= 5 && patient.getGenre().equals("homme") &&  patientAge < 30) {
            riskLevel = RiskLevelConstant.EARLYONSET;
        } else if (symptomsOccurence >= 7 && patient.getGenre().equals("femme") &&  patientAge < 30) {
            riskLevel = RiskLevelConstant.EARLYONSET;
        } else if (symptomsOccurence >= 8 &&  patientAge > 30) {
            riskLevel = RiskLevelConstant.EARLYONSET;
        }
    }
}
