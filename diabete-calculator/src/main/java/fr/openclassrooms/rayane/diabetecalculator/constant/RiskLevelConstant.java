package fr.openclassrooms.rayane.diabetecalculator.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum RiskLevelConstant {
    NONE("aucun risque"),
    BORDERLINE("risque limité"),
    DANGER("danger"),
    EARLYONSET("apparition précoce");

    private final String libelle;
}
