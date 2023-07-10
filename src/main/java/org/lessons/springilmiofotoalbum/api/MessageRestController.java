package org.lessons.springilmiofotoalbum.api;

import jakarta.validation.Valid;
import org.lessons.springilmiofotoalbum.exceptions.NotUniqueTitleException;
import org.lessons.springilmiofotoalbum.model.Message;
import org.lessons.springilmiofotoalbum.model.Photo;
import org.lessons.springilmiofotoalbum.repository.MessageRepository;
import org.lessons.springilmiofotoalbum.repository.PhotoRepository;
import org.lessons.springilmiofotoalbum.service.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;

@RestController
@CrossOrigin
@RequestMapping("api/album/messages")
public class MessageRestController {
    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private PhotoService photoService;

    @PostMapping
    public Message create(@Valid @RequestBody Message message) {
        Message messageToPersist = new Message();
        // genero il timestamp di createdAt
        //photoToPersist.setCreatedAt(LocalDateTime.now());
        // copio tutti i campi di book che mi interessano
        messageToPersist.setFirstName(message.getFirstName());
        messageToPersist.setSurname(message.getSurname());
        messageToPersist.setMessage(message.getMessage());
        messageToPersist.setEmail(message.getEmail());


        // persisto la photo
        return messageRepository.save(messageToPersist);
    }
}
