package org.lessons.springilmiofotoalbum.service;

import org.lessons.springilmiofotoalbum.model.Photo;
import org.lessons.springilmiofotoalbum.repository.PhotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PhotoService {
    @Autowired
    PhotoRepository photoRepository;

    public List<Photo> getFilteredPhotosByTitle (String searchString) {
        if (searchString == null || searchString.isBlank()) {
            // se non ho il parametro searchString faccio la query generica
            // recupero la lista di libri dal database
            return photoRepository.findAll();
        } else {
            // se ho il parametro searchString faccio la query con filtro
            // books = bookRepository.findByTitle(searchString);
            return photoRepository.findByTitleContainingIgnoreCase(
                    searchString);
        }
    }

}
