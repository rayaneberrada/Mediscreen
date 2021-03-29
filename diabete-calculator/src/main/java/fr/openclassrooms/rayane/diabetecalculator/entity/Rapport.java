package fr.openclassrooms.rayane.diabetecalculator.entity;

import fr.openclassrooms.rayane.diabetecalculator.constant.RiskLevelConstant;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Getter
public class Rapport {
    Patient patient;
    String niveauDeRisque;

    public Rapport(Patient patient, RiskLevelConstant niveauDeRisque) {
        this.patient = patient;
        this.niveauDeRisque = niveauDeRisque.toString();
    }
}
