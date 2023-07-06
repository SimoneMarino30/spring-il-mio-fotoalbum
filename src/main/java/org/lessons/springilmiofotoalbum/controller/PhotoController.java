package org.lessons.springilmiofotoalbum.controller;

import org.lessons.springilmiofotoalbum.model.Photo;
import org.lessons.springilmiofotoalbum.repository.CategoryRepository;
import org.lessons.springilmiofotoalbum.repository.PhotoRepository;
import org.lessons.springilmiofotoalbum.service.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;

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
}
