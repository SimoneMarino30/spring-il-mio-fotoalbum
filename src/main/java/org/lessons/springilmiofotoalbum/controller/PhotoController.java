package org.lessons.springilmiofotoalbum.controller;

import jakarta.validation.Valid;
import org.lessons.springilmiofotoalbum.model.Photo;
import org.lessons.springilmiofotoalbum.repository.CategoryRepository;
import org.lessons.springilmiofotoalbum.repository.PhotoRepository;
import org.lessons.springilmiofotoalbum.service.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/photos")
public class PhotoController {
    @Autowired
    private PhotoRepository photoRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    PhotoService photoService;

    /* *
    METODI PER LA READ
    * */
    @GetMapping
    public String index(
            @RequestParam(name = "keyword", required = false) String searchString,
            Model model) { // Model è la mappa di attributi che il controller passa alla view
        List<Photo> photos = photoService.getFilteredPhotosByTitle(searchString);

        // passo la lista delle photos alla view
        model.addAttribute("photoList", photos);
        model.addAttribute("searchInput", searchString == null ? "" : searchString);
        // restituisco il nome del template della view
        return "/photos/index";
    }

    @GetMapping("/{id}")
    public String detail(@PathVariable("id") Integer photoId, Model model) {

        Photo photo = getPhotoById(photoId);
        // passa la photo alla view
        model.addAttribute("photo", photo);
        // ritorna il nome del template della view
        return "/photos/show";
    }

    // metodo per selezionare photo da Db o tirare exception
    private Photo getPhotoById(Integer id) {
        // verificare se esiste una pizza con quell' id
        Optional<Photo> result = photoRepository.findById(id);
        // se non esiste, ritorno un http 404
        if (result.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Photo with id " + id + " not found");
            // eccezione 'custom' per richieste http
        }
        return result.get();
    }

    /* *
    METODI PER LA CREATE
    * */
    // controller che restituisce la pagina con form di creazione del nuovo book
    // controller che restituisce la pagina con form di creazione del nuovo book
    @GetMapping("/create")
    public String create(Model model) {
        // aggiungo al model l'attributo photo contenente una Photo vuota
        model.addAttribute("photo", new Photo());
        return "/photos/edit"; // template con form di creazione di un book
    }

    // controller che gestisce la post del form coi dati della photo
    @PostMapping("/create")
    public String store(@Valid @ModelAttribute("photo") Photo formPhoto, BindingResult bindingResult) {
        // i dati del book sono dentro all'oggetto formBook

        // verifico se l'isbn è univoco
        if (!isUniqueTitle(formPhoto)) {
            // aggiungo a mano un errore nella mappa BindingResult
            bindingResult.addError(new FieldError("photo", "title", formPhoto.getTitle(), false, null, null,
                    "Title must be unique"));
        }
        // verifico se in validazione ci sono stati errori
        if (bindingResult.hasErrors()) {
            // ci sono stati errori
            return "/photos/edit"; // ritorno il template del form ma col book precaricato
        }

        // setto il timestamp di creazione
        formPhoto.setCreatedAt(LocalDateTime.now());
        // persisto formBook su database
        // il metodo save fa una create sql se l'oggetto con quella PK non esiste, altrimenti fa update
        photoRepository.save(formPhoto);

        // se tutto va a buon fine rimando alla lista dei books
        return "redirect:/photos";
    }

    // metodo per verificare se su database c'è già una con lo stesso title della photo passata come parametro
    private boolean isUniqueTitle(Photo formPhoto) {
        List<Photo> result = photoRepository.findByTitleContainingIgnoreCase(formPhoto.getTitle());
        return result.isEmpty();
    }
}
