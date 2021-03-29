package fr.openclassrooms.rayane.diabetecalculator.service;

import fr.openclassrooms.rayane.diabetecalculator.constant.RiskLevelConstant;
import fr.openclassrooms.rayane.diabetecalculator.entity.Note;
import fr.openclassrooms.rayane.diabetecalculator.entity.Patient;
import fr.openclassrooms.rayane.diabetecalculator.entity.Rapport;

import java.lang.reflect.Array;
import java.util.List;

public interface RiskCalculator {

    RiskLevelConstant calculateRisk(List<Note> file, Patient patient);
}
