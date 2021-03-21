package fr.openclassrooms.rayane.patientsinfos.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "patient")
public class Patient {
    @Id int id;

    String prenom;
    String nom;
    Date dateDeNaissance;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "genre_id", referencedColumnName = "id")
    Genre genre;
    String adresse;
    String telephone;
}
