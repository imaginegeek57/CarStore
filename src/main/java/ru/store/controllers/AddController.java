package ru.store.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import ru.store.models.Advert;
import ru.store.models.Photo;
import ru.store.models.User;
import ru.store.repositories.UserRepository;
import ru.store.service.Validation;
import org.apache.commons.io.IOUtils;
import javax.servlet.annotation.MultipartConfig;
import java.io.IOException;
import java.io.InputStream;
import java.security.Principal;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping(value = "add")
@MultipartConfig(location = "/tmp", maxFileSize = 1000L)
public class AddController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AddController.class);

    private Validation <Advert> advertValidation;
    private UserRepository userRepository;

    public AddController(Validation<Advert> advertValidation, UserRepository userRepository) {
        this.advertValidation = advertValidation;
        this.userRepository = userRepository;
    }

    @GetMapping(value = "newadv")
    public ModelAndView getAddAdvertView() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("newadvert");
        LOGGER.info("LOG: enter newadv view");
        return new ModelAndView("newadvert");
    }

    @PostMapping(value = "newadv")
    public void addNewAdvert(Advert advert, @RequestParam(name = "file_upload", required = false) MultipartFile file, Principal principal) {
        User user = userRepository.findUserByLogin(principal.getName());
        Optional <Photo> optionalPhoto = saveFile(file);
        optionalPhoto.ifPresent(advert::setPhoto);
        advert.setUser(user);
        advertValidation.add(advert);
        LOGGER.info("LOG: add new advert in system {} {}", advert, file);
    }

    private Optional<Photo> saveFile(MultipartFile multipartFile) {
        if (Objects.nonNull(multipartFile)) {
            try (InputStream inputStream = multipartFile.getInputStream()) {
                Photo photo = new Photo();
                byte[] fileContent = IOUtils.toByteArray(inputStream);
                photo.setFilename(multipartFile.getOriginalFilename());
                photo.setContentType(multipartFile.getContentType());
                photo.setData(fileContent);
                return Optional.of(photo);
            } catch (IOException e) {
                LOGGER.error("Exception on save file_data in db", e);
            }
        }
        return Optional.empty();
    }
}
