package org.lessons.springilmiofotoalbum.controller;

import jakarta.validation.Valid;
import org.lessons.springilmiofotoalbum.messages.AlertMessage;
import org.lessons.springilmiofotoalbum.messages.AlertMessageType;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

    /* *
    METODI PER LA CREATE
    * */
    // controller che restituisce la pagina con form di creazione della nuova photo
    @GetMapping("/create")
    public String create(Model model) {
        // aggiungo al model l'attributo photo contenente una Photo vuota
        model.addAttribute("photo", new Photo());
        return "/photos/edit"; // template con form di creazione di una photo
    }

    // controller che gestisce la post del form coi dati della photo
    @PostMapping("/create")
    public String store(@Valid @ModelAttribute("photo") Photo formPhoto, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        // i dati del book sono dentro all'oggetto formPhoto

        // verifico se l'isbn è univoco
        if (!isUniqueTitle(formPhoto)) {
            // aggiungo a mano un errore nella mappa BindingResult
            bindingResult.addError(new FieldError("photo", "title", formPhoto.getTitle(), false, null, null,
                    "Title must be unique"));
        }
        // verifico se in validazione ci sono stati errori
        if (bindingResult.hasErrors()) {
            // ci sono stati errori
            return "/photos/edit"; // ritorno il template del form ma con la photo precaricata
        }

        // setto il timestamp di creazione
        formPhoto.setCreatedAt(LocalDateTime.now());
        // persisto formPhoto su database
        // il metodo save fa una create sql se l'oggetto con quella PK non esiste, altrimenti fa update
        photoRepository.save(formPhoto);
        redirectAttributes.addFlashAttribute("message",
                new AlertMessage(AlertMessageType.SUCCESS, "Photo created!"));
        // se tutto va a buon fine rimando alla lista dei books
        return "redirect:/photos";
    }

    /* *
    METODI PER UPDATE
    * */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        Photo photo = getPhotoById(id);
        // recupero i dati di quella photo da database
        // aggiungo la photo al model
        model.addAttribute("photo", photo);
        // restituisco il template con il form di edit
        return "/photos/edit";
    }

    @PostMapping("/edit/{id}")
    public String doEdit(
            @PathVariable Integer id,
            @Valid @ModelAttribute("photo") Photo formPhoto,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes
    ) {
        // cerco il book per id
        Photo photoToEdit = getPhotoById(id); // vecchia versione della photo
        // nuova versione della photo è formPhoto
        // valido formPhoto
        // se il vecchio title e quello nuovo sono diversi e quello nuovo è già presente su database allora errore
        if (!photoToEdit.getTitle().equals(formPhoto.getTitle()) && !isUniqueTitle(formPhoto)) {
            bindingResult.addError(new FieldError("book", "isbn", formPhoto.getTitle(), false, null, null,
                    "Title must be unique"));
        }
        if (bindingResult.hasErrors()) {
            // se ci sono errori ritorno il template col form
            return "/photos/edit";
        }

        // trasferisco su formBook tutti i valori dei campi che non sono presenti nel form (altrimenti li perdo)
        formPhoto.setId(photoToEdit.getId());
        formPhoto.setCreatedAt(photoToEdit.getCreatedAt());
        // salvo i dati
        photoRepository.save(formPhoto);
        redirectAttributes.addFlashAttribute("message",
                new AlertMessage(AlertMessageType.SUCCESS, "Photo updated!"));
        return "redirect:/photos";
    }

    // METODI CUSTOM

    // metodo per verificare se su database c'è già una con lo stesso title della photo passata come parametro
    private boolean isUniqueTitle(Photo formPhoto) {
        List<Photo> result = photoRepository.findByTitleContainingIgnoreCase(formPhoto.getTitle());
        return result.isEmpty();
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
}
