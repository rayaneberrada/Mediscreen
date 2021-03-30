package fr.openclassrooms.rayane.diabetecalculator.service;

import fr.openclassrooms.rayane.diabetecalculator.entity.Rapport;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class TestRapportService {

    @Autowired RapportService rapportService;


    @Test
    public void CreateRapportByPatientIdSucceed() {
        // GIVEN
        int patientId = 1;

        // WHEN
        Rapport rapport = rapportService.createRapportByPatientId(patientId);

        // THEN
        assertThat(rapport.getPatientNameAndAge()).isEqualTo("Ferguson (age 52)");
        assertThat(rapport.getNiveauDeRisque()).isEqualTo("diabetes assessment is: BORDERLINE");
    }
}
