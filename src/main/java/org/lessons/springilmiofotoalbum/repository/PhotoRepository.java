package org.lessons.springilmiofotoalbum.repository;

import org.lessons.springilmiofotoalbum.model.Photo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PhotoRepository extends JpaRepository<Photo, Integer> {

    // metodo per cercare le photo con titolo uguale a quello passato
    List<Photo> findByTitleContainingIgnoreCase(String title);
}
