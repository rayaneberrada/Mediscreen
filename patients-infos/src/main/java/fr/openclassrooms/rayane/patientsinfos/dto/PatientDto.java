package fr.openclassrooms.rayane.patientsinfos.dto;

import fr.openclassrooms.rayane.patientsinfos.entity.Genre;
import lombok.Builder;
import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.Date;

@Data
public class PatientDto {
    String prenom;
    String nom;
    Date date_naissance;
    String genre;
    String adresse;
    String telephone;
}
