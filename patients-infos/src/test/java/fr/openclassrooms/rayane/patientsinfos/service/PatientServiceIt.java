package fr.openclassrooms.rayane.patientsinfos.service;

import fr.openclassrooms.rayane.patientsinfos.dto.PatientDto;
import fr.openclassrooms.rayane.patientsinfos.entity.Patient;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;
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

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

import static org.assertj.core.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class PatientServiceIt {

    @Autowired PatientService patientService;

    @AfterAll
    public void cleanUp() {
        patientService.deletePatient("mairedelille");
    }

    @Test
    @Order(1)
    public void Add_NewPatient_ThenReturnIt() {
        // GIVEN
        PatientDto patientDto = new PatientDto("alphonse", "mairedelille",new Date(), "F","quelue part", "0654378290");

        // WHEN
        PatientDto patient = patientService.addPatient(patientDto);

        // THEN
        assertThat(patient.getGiven()).isEqualTo("alphonse");
        assertThat(patient.getFamily()).isEqualTo("mairedelille");
    }

    @Test
    @Order(2)
    public void Get_FromFirstNameAndLastName_CorrespondingPatient() {
        // GIVEN
        String lastName = "mairedelille";

        // WHEN
        PatientDto patient = patientService.findByLastName(lastName);

        // THEN
        assertThat(patient.getFamily()).isEqualTo(lastName);
    }

    @Test
    @Order(3)
    public void Update_FromFirstNameAndLastName_CorrespondingPatient() {
        // GIVEN
        String lastName = "mairedelille";
        PatientDto patientDto = new PatientDto("alphonse", "mairedelille", new Date(), "femme","Lille", "000");

        // WHEN
        PatientDto patient = patientService.updatePatient(lastName, patientDto);

        // THEN
        assertThat(patient.getAddress()).isEqualTo("Lille");
        assertThat(patient.getPhone()).isEqualTo("000");
    }

}
