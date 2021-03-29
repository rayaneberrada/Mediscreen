package fr.openclassrooms.rayane.patientsinfos.controller;

import fr.openclassrooms.rayane.patientsinfos.service.PatientService;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class PatientControllerIt {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    PatientService patientService;

    @AfterAll
    public void cleanUp() {
        patientService.deletePatient("cash");
    }

    @Test
    @Order(1)
    public void Get_GetPatient_ToRespond() throws Exception {
        mockMvc.perform(get("/patient/get?lastName=Ferguson")).andExpect(status().isOk());
    }

    @Test
    @Order(2)
    public void Get_AddPatient_ToRespond() throws Exception {
    mockMvc
        .perform(
            post("/patient/add")
                    .param("given", "johnny")
                    .param("family", "cash")
                    .param("dob", "1968-12-06T00:00:00.000+00:00")
                    .param("sex", "H")
                    .param("address", "14 boulevard saint-jean")
                    .param("phone", "0651436590")
                    .contentType(MediaType.APPLICATION_FORM_URLENCODED))
        .andExpect(status().isOk());
    }
}
