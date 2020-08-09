package ru.store.repositories;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.store.models.Advert;

@Repository
public interface AdvertRepository extends CrudRepository<Advert, Integer> {

    Iterable<Advert> findAdvertsByCarTitleIs(String brandname);

    Iterable<Advert> findAdvertsByPhotoDataIsNotNull();


}
