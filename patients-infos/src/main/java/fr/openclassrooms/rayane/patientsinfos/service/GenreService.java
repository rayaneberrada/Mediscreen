package fr.openclassrooms.rayane.patientsinfos.service;

import fr.openclassrooms.rayane.patientsinfos.entity.Genre;
import fr.openclassrooms.rayane.patientsinfos.repository.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service to manage Genre objects
 */
@Service
public class GenreService {

    @Autowired
    GenreRepository genreRepository;

    /**
     * Method to retrieve a Genre by it's genre.
     * Example:
     *      getGenreByGenre("homme")
     * @param genre
     * @return Genre object corresponding to genre
     */
    Genre getGenreByGenre(String genre) {
        return genreRepository.findByGenre(genre);
    }
}
