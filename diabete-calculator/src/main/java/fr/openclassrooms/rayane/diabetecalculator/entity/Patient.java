package fr.openclassrooms.rayane.diabetecalculator.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Patient {
    String prenom;
    String nom;
    Date date_naissance;
    String genre;
    String adresse;
    String telephone;
}
