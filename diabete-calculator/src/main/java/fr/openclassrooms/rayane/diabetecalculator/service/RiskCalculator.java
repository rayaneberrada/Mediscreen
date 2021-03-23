package fr.openclassrooms.rayane.diabetecalculator.service;

import fr.openclassrooms.rayane.diabetecalculator.constant.RiskLevelConstant;
import fr.openclassrooms.rayane.diabetecalculator.entity.Note;
import fr.openclassrooms.rayane.diabetecalculator.entity.Patient;
import fr.openclassrooms.rayane.diabetecalculator.entity.Rapport;

import java.lang.reflect.Array;

public interface RiskCalculator {

    RiskLevelConstant calculateRisk(Note note, Patient patient);
}
