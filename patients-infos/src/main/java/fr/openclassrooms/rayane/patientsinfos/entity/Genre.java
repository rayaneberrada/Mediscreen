package fr.openclassrooms.rayane.patientsinfos.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "genre")
@Data
public class Genre {
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Id Integer id;

    String genre;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "genre", fetch = FetchType.LAZY)
    @JsonIgnore
    Set<Patient> patient;
}
