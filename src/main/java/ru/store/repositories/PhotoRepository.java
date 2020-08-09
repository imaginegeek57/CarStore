package ru.store.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.store.models.Photo;

@Repository
public interface PhotoRepository extends CrudRepository <Photo, Integer> {

}
