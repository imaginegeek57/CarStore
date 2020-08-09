package ru.store.service;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.store.models.Car;
import ru.store.repositories.CarRepository;
import java.util.List;
import java.util.Objects;

@Service
public class ValidateCar implements Validation <Car> {

    private CarRepository carsRepository;

    @Override
    public Car add(Car car) {
        if (checkModel(car)) {
            carsRepository.save(car);
        }
        return car;
    }

    @Override
    public boolean replace(Car car) {
        boolean rs = checkModel(car);
        if (rs) {
            carsRepository.save(car);
        }
        return rs;
    }

    @Override
    public boolean delete(Car car) {
        boolean rs = checkModel(car);
        if (rs) {
            carsRepository.delete(car);
        }
        return rs;
    }

    @Override
    public List <Car> findAll() {
        return (List <Car>) carsRepository.findAll();
    }

    @Override
    public Car find(Car car) {
        return carsRepository.findById(car.getId()).orElseThrow(() -> new UsernameNotFoundException("Car not found"));
    }

    private boolean checkModel(Car car) {
        return Objects.nonNull(car.getTitle()) && Objects.nonNull(car.getCarBody());
    }

}
