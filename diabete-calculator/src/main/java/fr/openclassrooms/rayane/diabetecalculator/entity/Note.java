package fr.openclassrooms.rayane.diabetecalculator.entity;

import lombok.Data;

import java.time.LocalDate;
import java.util.Date;

@Data
public class Note {
    Integer patId;
    String patient;
    String note;
    LocalDate dateWritten;
}
