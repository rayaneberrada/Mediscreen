package fr.openclassrooms.rayane.patientsinfos.service;

import fr.openclassrooms.rayane.patientsinfos.dto.PatientDto;
import fr.openclassrooms.rayane.patientsinfos.entity.Patient;
import fr.openclassrooms.rayane.patientsinfos.repository.PatientRepository;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

/**
 * Service to manage patient
 */
@Service
public class PatientService {

    private Logger logger = LoggerFactory.getLogger(PatientService.class);

    @Autowired
    PatientRepository patientRepository;

    @Autowired
    private ModelMapper modelMapper;

    /**
     * Method to get a patient by it's last name
     *
     * @param lastName
     * @return PatientDto object mapped with the infos from a Patient object
     */
    public PatientDto findByLastName(String lastName) {
        Patient patient = patientRepository.findByFamily(lastName).orElseThrow(NoSuchElementException::new);
        PatientDto patientDto = modelMapper.map(patient, PatientDto.class);
        return patientDto;
    }

    /**
     * Method to update a patient by it's last name
     *
     * @param lastName
     * @param patient
     * @return PatientDto
     */
    public PatientDto updatePatient(String lastName, PatientDto patient) {
        // Retrieve patient corresponding to firstName and lastName
        Patient patientToUpdate = patientRepository.findByFamily(lastName).orElseThrow(NoSuchElementException::new);

        // patient informations are updated with new inforamtions transmitted
        patientToUpdate.setAddress(patient.getAddress());
        patientToUpdate.setPhone(patient.getPhone());

        // Patient informations in db are updated and it's dto is returned
        patientRepository.save(patientToUpdate);
        return patient;
    }

    /**
     * Method to add a new patient in database
     *
     * @param patient
     * @return
     */
    public PatientDto addPatient(PatientDto patient) {
        Patient patientToAdd = modelMapper.map(patient, Patient.class);
        System.out.println(patient.getDob().toString());

        // Patient is saved in db and it's dto returned
        patientRepository.save(patientToAdd);
        return patient;
    }

    /**
     * Method to delete a patient from database
     *
     * @param lastName
     */
    public void deletePatient(String lastName) {
        Patient patient = patientRepository.findByFamily(lastName).orElseThrow(NoSuchElementException::new);
        patientRepository.delete(patient);
    }
}
