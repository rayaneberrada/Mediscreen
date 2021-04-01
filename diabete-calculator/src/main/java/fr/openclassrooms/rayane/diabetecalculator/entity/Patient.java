package fr.openclassrooms.rayane.diabetecalculator.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Patient {
    String family;
    LocalDate dob;
    String sex;
    String address;
    String phone;

    public void setDob(String dob) throws ParseException {
        this.dob = LocalDate.parse(dob);
    }
}
