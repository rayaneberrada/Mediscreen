package fr.openclassrooms.rayane.patientsinfos.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "patient")
@Data
public class Patient {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    Integer id;

    String prenom;
    String nom;
    Date date_naissance;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "genre_id", referencedColumnName = "id")
    Genre genre;
    String adresse;
    String telephone;
}
