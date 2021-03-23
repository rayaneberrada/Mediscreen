package fr.openclassrooms.rayane.diabetecalculator.entity;

import lombok.Data;

@Data
public class Rapport {
    Patient patient;
    String niveauDeRisque;
}
