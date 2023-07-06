package org.lessons.springilmiofotoalbum.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotBlank(message = "Name must not be null or blank")
    @Column(nullable = false, unique = true)
    private String name;

    // relazione N:N
    @JsonIgnore
    @ManyToMany(mappedBy = "categories")
    // lista photos associabili alle categories
    private List<Photo> photos = new ArrayList<>();

    //GETTERS & SETTERS

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    //GETTERS & SETTERS RELATIONS
    public List<Photo> getPhotos() {
        return photos;
    }

    public void setPhotos(List<Photo> photos) {
        this.photos = photos;
    }
}
