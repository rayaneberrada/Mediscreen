package fr.openclassrooms.rayane.noteinfos.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.time.LocalDate;
import java.util.Date;

@Data
@NoArgsConstructor
public class Note {
    @Id String id;
    Integer patId;
    String patient;
    String note;
    LocalDate dateWritten;

    public Note(Integer patId, String patient, String note) {
        this.patId = patId;
        this.patient = patient;
        this.note = note;
    }
}
