package fr.openclassrooms.rayane.patientsinfos.dto;

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
public class PatientDto {
    String given;
    String family;
    Date dob;
    String sex;
    String address;
    String phone;

    public void setDob(String dob) throws ParseException {
        this.dob = new SimpleDateFormat("yyyy-MM-dd").parse(dob); ;
    }
}
