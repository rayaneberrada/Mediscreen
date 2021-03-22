package fr.openclassrooms.rayane.patientsinfos.service;

import fr.openclassrooms.rayane.patientsinfos.dto.PatientDto;
import fr.openclassrooms.rayane.patientsinfos.entity.Genre;
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

    @Autowired GenreService genreService;

    @Autowired
    private ModelMapper modelMapper;

    public PatientDto findByNameAndLastName(String firstName, String lastName) {
        PatientDto patient = modelMapper.map(patientRepository.findByPrenomAndNom(firstName, lastName), PatientDto.class);
        return patient;
    }

    public PatientDto updatePatient(String firstName, String lastName, PatientDto patient) {
        // Retrieve patient corresponding to firstName and lastName
        Patient patientToUpdate = patientRepository.findByPrenomAndNom(firstName, lastName);

        // patient informations are updated with new inforamtions transmitted
        patientToUpdate.setAdresse(patient.getAdresse());
        patientToUpdate.setGenre(genreService.getGenreByGenre(patient.getGenre()));
        patientToUpdate.setDate_naissance(patient.getDate_naissance());
        patientToUpdate.setPrenom(patient.getPrenom());
        patientToUpdate.setNom(patient.getNom());
        patientToUpdate.setTelephone(patient.getTelephone());

        // Patient informations in db are updated and it's dto is returned
        patientRepository.save(patientToUpdate);
        return patient;
    }

    public PatientDto addPatient(PatientDto patient) {
        // Retrieve Genre corresponding to it's genre
        Genre genre = genreService.getGenreByGenre(patient.getGenre());

        // Create a Patient object from the dto and set it's genre with a proper Genre object
        // ( PatientDto only contain a String representing the genre, that's why we have to fetch it in db )
        Patient patientToAdd = modelMapper.map(patient, Patient.class);
        patientToAdd.setGenre(genre);

        // Patient is saved in db and it's dto returned
        patientRepository.save(patientToAdd);
        return patient;
    }
}
