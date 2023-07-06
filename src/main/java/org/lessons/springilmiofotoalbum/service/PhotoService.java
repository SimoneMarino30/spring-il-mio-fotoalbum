package org.lessons.springilmiofotoalbum.service;

import org.lessons.springilmiofotoalbum.model.Photo;
import org.lessons.springilmiofotoalbum.repository.PhotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class PhotoService {
    @Autowired
    PhotoRepository photoRepository;

    // metodo che restituisce la lista di tutti i libri filtrata o no a seconda del parametro

    public List<Photo> getFilteredPhotosByTitle (String searchString) {
        if (searchString == null || searchString.isBlank()) {
            // se non ho il parametro searchString faccio la query generica
            // recupero la lista delle foto dal database
        return photoRepository.findAll();
        } else {
            // se ho il parametro searchString faccio la query con filtro
            return photoRepository.findByTitleContainingIgnoreCase(
                    searchString);
        }
    }
}
