package fr.openclassrooms.rayane.patientsinfos.service;

import fr.openclassrooms.rayane.patientsinfos.dto.PatientDto;
import fr.openclassrooms.rayane.patientsinfos.entity.Patient;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.Table;

import java.util.Date;

import static org.assertj.core.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PatientServiceIt {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired PatientService patientService;

    @Test
    @Order(1)
    public void Get_FromFirstNameAndLastName_CorrespondingPatient() {
        // GIVEN
        String firstName = "jean";
        String lastName = "rochefaible";

        // WHEN
        PatientDto patient = patientService.findByNameAndLastName(firstName, lastName);

        // THEN
        assertThat(patient.getPrenom()).isEqualTo(firstName);
        assertThat(patient.getNom()).isEqualTo(lastName);
    }

    @Test
    @Order(2)
    public void Update_FromFirstNameAndLastName_CorrespondingPatient() {
        // GIVEN
        String firstName = "jean";
        String lastName = "rochefaible";
        PatientDto patientDto = new PatientDto("jean", "rochefort", new Date(), "homme","20 avenue du cadi", "000");

        // WHEN
        PatientDto patient = patientService.updatePatient(firstName, lastName, patientDto);

        // THEN
        assertThat(patient.getNom()).isEqualTo("rochefort");
        assertThat(patient.getTelephone()).isEqualTo("000");
    }

    @Test
    @Order(3)
    public void Update_FromFirstNameAndLastName_CorrespondingPatientBack() {
        // GIVEN
        String firstName = "jean";
        String lastName = "rochefort";
        PatientDto patientDto = new PatientDto("jean", "rochefaible", new Date(), "homme","20 avenue du cadi", "0654378290");

        // WHEN
        PatientDto patient = patientService.updatePatient(firstName, lastName, patientDto);

        // THEN
        assertThat(patient.getNom()).isEqualTo("rochefaible");
        assertThat(patient.getTelephone()).isEqualTo("0654378290");
    }

    @Test
    @Order(4)
    public void Add_NewPatient_ThenReturn() {
        // GIVEN
        PatientDto patientDto = new PatientDto("alphonse", "mairedelille", new Date(), "femme","quelue part", "0654378290");

        // WHEN
        PatientDto patient = patientService.addPatient(patientDto);

        // THEN
        assertThat(patient.getPrenom()).isEqualTo("alphonse");
        assertThat(patient.getNom()).isEqualTo("mairedelille");
    }
}
