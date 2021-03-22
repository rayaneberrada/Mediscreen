package fr.openclassrooms.rayane.patientsinfos.dto;

import fr.openclassrooms.rayane.patientsinfos.entity.Genre;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PatientDto {
    String prenom;
    String nom;
    Date date_naissance;
    String genre;
    String adresse;
    String telephone;
}
