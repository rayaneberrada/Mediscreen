package fr.openclassrooms.rayane.diabetecalculator.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Note {
    Integer noteNumber;
    Integer patId;
    String patient;
    String note;
    Date dateWritten;
}
