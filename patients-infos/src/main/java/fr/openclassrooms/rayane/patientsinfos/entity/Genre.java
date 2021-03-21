package fr.openclassrooms.rayane.patientsinfos.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "genre")
public class Genre {
    @Id int id;
    String genre;
    @OneToOne(mappedBy = "genre")
    Patient patient;
}
