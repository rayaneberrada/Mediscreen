package fr.openclassrooms.rayane.noteinfos.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.util.Date;

@Data
@NoArgsConstructor
public class Note {
    @Id String id;
    Integer noteNumber;
    Integer patId;
    String patient;
    String note;
    Date dateWritten = new Date();

    public Note(Integer noteNumber, Integer patId, String patient, String note) {
        this.noteNumber = noteNumber;
        this.patId = patId;
        this.patient = patient;
        this.note = note;
    }
}
