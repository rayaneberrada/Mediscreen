package fr.openclassrooms.rayane.patientsinfos.service;

import fr.openclassrooms.rayane.patientsinfos.dto.PatientDto;
import fr.openclassrooms.rayane.patientsinfos.entity.Patient;
import fr.openclassrooms.rayane.patientsinfos.repository.PatientRepository;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PatientService {

    private Logger logger = LoggerFactory.getLogger(PatientService.class);

    @Autowired
    PatientRepository patientRepository;

    @Autowired
    private ModelMapper modelMapper;

    public PatientDto findByLastName(String lastName) {
        PatientDto patient = modelMapper.map(patientRepository.findByFamily(lastName), PatientDto.class);
        return patient;
    }

    public PatientDto updatePatient(String lastName, PatientDto patient) {
        // Retrieve patient corresponding to firstName and lastName
        Patient patientToUpdate = patientRepository.findByFamily(lastName);

        // patient informations are updated with new inforamtions transmitted
        patientToUpdate.setAddress(patient.getAddress());
        patientToUpdate.setPhone(patient.getPhone());

        // Patient informations in db are updated and it's dto is returned
        patientRepository.save(patientToUpdate);
        return patient;
    }

    public PatientDto addPatient(PatientDto patient) {
        Patient patientToAdd = modelMapper.map(patient, Patient.class);
        System.out.println(patient.getDob().toString());

        // Patient is saved in db and it's dto returned
        patientRepository.save(patientToAdd);
        return patient;
    }

    public void deletePatient(String lastName) {
        Patient patient = patientRepository.findByFamily(lastName);
        patientRepository.delete(patient);
    }
}
