package fr.openclassrooms.rayane.noteinfos.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.Date;

@Data
public class Note {
    @Id String id;
    Integer noteNumber;
    Integer patId;
    String patient;
    String note;
    Date dateWritten = new Date();
}
