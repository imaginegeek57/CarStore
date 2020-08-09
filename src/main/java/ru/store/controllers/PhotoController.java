package ru.store.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.store.models.Photo;
import ru.store.repositories.PhotoRepository;

import java.util.NoSuchElementException;

@RestController
@RequestMapping("photo")
public class PhotoController {
    private static final Logger LOGGER = LoggerFactory.getLogger(PhotoController.class);
    private PhotoRepository repository;

    @Autowired
    public PhotoController(PhotoRepository photoRepository) {
        this.repository = photoRepository;
    }

    @GetMapping("{id}")
    public ResponseEntity <byte[]> getPhotoById(@PathVariable String id) {
        Photo photo = new Photo();
        if (!id.equals("null")) {
            photo.setId(Integer.parseInt(id));
            photo = repository.findById(photo.getId()).orElseThrow(()-> new NoSuchElementException("photo not found"));
        }
        return photo.getData() == null ? ResponseEntity.status(404).build() : ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(photo.getContentType()))
                .body(photo.getData());
    }
}