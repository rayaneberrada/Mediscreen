package fr.openclassrooms.rayane.patientsinfos.repository;

import fr.openclassrooms.rayane.patientsinfos.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.Optional;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Integer> {
   Optional<Patient> findByFamily(String lastName);
}
