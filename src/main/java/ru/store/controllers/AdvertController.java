package ru.store.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.store.models.Advert;
import ru.store.repositories.AdvertRepository;
import java.util.List;

@RestController
@RequestMapping("data")
public class AdvertController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AdvertController.class);

    private AdvertRepository advertRepository;

    @Autowired
    public AdvertController(AdvertRepository advertRepository) {
        this.advertRepository = advertRepository;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List <Advert> allData() {
        LOGGER.info("LOG: get all data");
        return (List <Advert>) advertRepository.findAll();
    }

    @GetMapping(value = "/brand/{brandname}")
    @ResponseBody
    public List <Advert> findByCarBrand(@PathVariable String brandname) {
        LOGGER.info("LOG: get with brandname {}", brandname);
        return (List <Advert>) advertRepository.findAdvertsByCarTitleIs(brandname);
    }

    @GetMapping(value = "/havephoto")
    @ResponseBody
    public List <Advert> findWithPhoto() {
        LOGGER.info("LOG: get with photo");
        return (List <Advert>) advertRepository.findAdvertsByPhotoDataIsNotNull();
    }

    @GetMapping(value = "/today")
    @ResponseBody
    public List <Advert> findToday() {
        LOGGER.info("LOG: get today added");
        return (List <Advert>) advertRepository.findAll();
    }

    @GetMapping(value = "/all")
    public List <Advert> findaAll() {
        LOGGER.info("LOG: get all");
        return allData();
    }
}
