package ru.store.service;

import java.util.List;

public interface Validation<T> {

     T add(T value);

    boolean replace(T value);

    boolean delete(T value);

    List<T> findAll();

    T find(T value);
}
