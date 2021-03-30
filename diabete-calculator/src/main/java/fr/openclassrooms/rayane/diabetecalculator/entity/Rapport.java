package fr.openclassrooms.rayane.diabetecalculator.entity;

import fr.openclassrooms.rayane.diabetecalculator.constant.RiskLevelConstant;
import lombok.*;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;

@NoArgsConstructor
@Getter
public class Rapport {
    String patientNameAndAge;
    String niveauDeRisque;

    public void setPatientNameAndAge(Patient patient) {
        LocalDate birthDateLocal = patient.dob.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();

        int age = Period.between(birthDateLocal,  LocalDate.now()).getYears();
        this.patientNameAndAge = patient.family + " (age " + age + ")";
    }

    public void setNiveauDeRisque(RiskLevelConstant riskLevel) {
        this.niveauDeRisque = "diabetes assessment is: " + riskLevel.toString();
    }
}
