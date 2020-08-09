package ru.store.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.store.models.Car;

public interface CarRepository extends CrudRepository <Car, Integer> {
}
