package org.lessons.springilmiofotoalbum.service;

import org.lessons.springilmiofotoalbum.exceptions.NotUniqueTitleException;
import org.lessons.springilmiofotoalbum.exceptions.PhotoNotFoundExceptions;
import org.lessons.springilmiofotoalbum.model.Photo;
import org.lessons.springilmiofotoalbum.repository.CategoryRepository;
import org.lessons.springilmiofotoalbum.repository.PhotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class PhotoService {
    @Autowired
    PhotoRepository photoRepository;

    @Autowired
    CategoryRepository categoryRepository;

    /* *
    METODI PER LA READ
    * */

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

    // metodo per verificare se su database c'è già una con lo stesso title della photo passata come parametro
    public boolean isUniqueTitle(Photo formPhoto) {
        List<Photo> result = photoRepository.findByTitleContainingIgnoreCase(formPhoto.getTitle());
        return result.isEmpty();
    }

    /* *
    METODI PER LA CREATE
    * */
    // metodo che salva un nuovo libro a partire da quello passato come parametro
    public Photo create(Photo photo) throws NotUniqueTitleException {
        // valido l'isbn del book
        if (!isUniqueTitle(photo)) {
            throw new NotUniqueTitleException(photo.getTitle());
        }
        // creo il book da salvare
        Photo photoToPersist = new Photo();
        // genero il timestamp di createdAt
        photoToPersist.setCreatedAt(LocalDateTime.now());
        // copio tutti i campi di book che mi interessano
        photoToPersist.setTitle(photo.getTitle());
        photoToPersist.setDescription(photo.getDescription());
        photoToPersist.setUrlPic(photo.getUrlPic());
        photoToPersist.setVisible(photo.isVisible());
        photoToPersist.setCreatedAt(photo.getCreatedAt());
        photoToPersist.setCategories(photo.getCategories());

        // persisto la photo
        return photoRepository.save(photoToPersist);
    }

    /* *
    METODI PER LE API
    * */

    // metodo che restituisce la lista di tutti i libri filtrata o no a seconda del parametro
    public List<Photo> getAll(Optional<String> keywordOpt) {
        if (keywordOpt.isEmpty()) {
            return photoRepository.findAll();
        } else {
            return photoRepository.findByTitleContainingIgnoreCase(keywordOpt.get());
        }
    }

    // metodo che restituisce il Pizza preso per id o un'eccezione se non lo trova
    public Photo getById(Integer id) throws PhotoNotFoundExceptions {
        Optional<Photo> photoOpt = photoRepository.findById(id);
        if (photoOpt.isPresent()) {
            return photoOpt.get();
        } else {
            throw new PhotoNotFoundExceptions("Photo with id " + id);
        }
    }
}
