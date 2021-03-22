package fr.openclassrooms.rayane.patientsinfos.repository;

import fr.openclassrooms.rayane.patientsinfos.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Integer> {
    Patient findByPrenomAndNom(String firstName, String lastName);
}
