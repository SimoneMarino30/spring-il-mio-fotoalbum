package org.lessons.springilmiofotoalbum.controller;

import org.lessons.springilmiofotoalbum.model.Photo;
import org.lessons.springilmiofotoalbum.repository.CategoryRepository;
import org.lessons.springilmiofotoalbum.repository.PhotoRepository;
import org.lessons.springilmiofotoalbum.service.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/photos")
public class PhotoController {
    @Autowired
    private PhotoRepository photoRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    PhotoService photoService;

    /* metodo che può ricevere opzionalmente un parametro da query string,
  - se quel parametro c'è
    dobbiamo filtrare la lista delle photos per quel parametro
  - se quel parametro non c'è dobbiamo restituire la lista di tutti le photos
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
}
