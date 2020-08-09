package ru.store.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.store.models.Advert;
import ru.store.repositories.AdvertRepository;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ValidateAdvert implements Validation <Advert> {
    private AdvertRepository advertRepository;

    @Autowired
    public ValidateAdvert(AdvertRepository advertRepository) {
        this.advertRepository = advertRepository;
    }

    @Override
    public Advert add(Advert advert) {
        Timestamp timestamp = Timestamp.valueOf(LocalDateTime.now(ZoneId.systemDefault()));
        advert.setAdded(timestamp);
        if (checkModel(advert)) {
            advertRepository.save(advert);
        }
        return advert;
    }

    @Override
    public boolean replace(Advert advert) {
        boolean rs = checkModel(advert);
        if (rs) {
            advertRepository.save(advert);
        }
        return rs;
    }

    @Override
    public boolean delete(Advert advert) {
        boolean rs = checkModel(advert);
        if (rs) {
            advertRepository.delete(advert);
        }
        return rs;
    }

    @Override
    public List <Advert> findAll() {
        return (List <Advert>) advertRepository.findAll();
    }

    public Advert find(Advert advert) {
        List <Advert> rs = findAll();
        Optional <Advert> f = rs.parallelStream().filter(advert1 -> advert1.getId().equals(advert.getId())).findFirst();
        return f.orElseThrow(() -> new UsernameNotFoundException("Advert not found"));
    }

    private boolean checkModel(Advert advert) {
        return Objects.nonNull(advert.getPrice()) && Objects.nonNull(advert.getTitle());
    }

}
