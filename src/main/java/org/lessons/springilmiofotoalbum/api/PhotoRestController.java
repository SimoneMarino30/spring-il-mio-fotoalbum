package org.lessons.springilmiofotoalbum.api;

import jakarta.validation.Valid;
import org.lessons.springilmiofotoalbum.exceptions.NotUniqueTitleException;
import org.lessons.springilmiofotoalbum.exceptions.PhotoNotFoundExceptions;
import org.lessons.springilmiofotoalbum.model.Photo;
import org.lessons.springilmiofotoalbum.repository.PhotoRepository;
import org.lessons.springilmiofotoalbum.service.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("api/album/photos")
public class PhotoRestController {
    @Autowired
    private PhotoRepository photoRepository;

    @Autowired
    private PhotoService photoService;

    // servizio per avere la lista delle photo
    @GetMapping
    public List<Photo> index(@RequestParam Optional<String> keyword) {
        // restituisco la lista di tutti le photo prese da database
        return photoService.getAll(keyword);
    }

    // servizio per vedere il dettaglio della singola photo
    @GetMapping("/{id}")
    public Photo get(@PathVariable Integer id) {
        // cerco la photo per id su DB
        try {
            return photoService.getById(id);
        } catch (PhotoNotFoundExceptions e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    // TRAINING API
    // servizio per creare un nuova photo, che arriva come JSON nel Request Body
    /*@PostMapping
    public Photo create(@Valid @RequestBody Photo photo) {
        try{
            return photoService.create(photo);
        } catch(NotUniqueTitleException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    // servizio per l' update della photo
    @PutMapping("/{id}")
    // bodyrequest passa i dati da modificare, ma devo passare tutti i campi(PATCH fa modifica di un singolo campo)
    public Photo update(@PathVariable Integer id, @Valid @RequestBody Photo photo) {
        photo.setId(id);// setto l'id passato come param
        return photoRepository.save(photo);
    }

    // servizio per cancellare una photo
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        photoRepository.deleteById(id);
    }*/
}
