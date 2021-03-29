package fr.openclassrooms.rayane.patientsinfos.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "patient")
@Data
public class Patient {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    Integer id;

    String family;
    String given;
    Date dob;
    String sex;
    String address;
    String phone;
}
