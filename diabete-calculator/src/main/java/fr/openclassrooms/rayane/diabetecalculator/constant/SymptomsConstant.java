package fr.openclassrooms.rayane.diabetecalculator.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum SymptomsConstant {
    HEMOGLOBINE("hémoglobine A1C"),
    MICROALBUMINE("microalbumine"),
    TAILLE("taille"),
    POIDS("poids"),
    FUMEUR("fumeur"),
    ANORMAL("anormal"),
    CHOLESTEROL("cholestérol"),
    VERTIGE("vertige"),
    RECHUTE("rechute"),
    REACTION("réaction"),
    ANTICORPS("anticorps");

    private final String libelle;
}
