package fr.openclassrooms.rayane.patientsinfos.repository;

import fr.openclassrooms.rayane.patientsinfos.entity.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GenreRepository extends JpaRepository<Genre, Integer> {

    Genre findByGenre(String genre);
}
