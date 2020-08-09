package ru.store.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.store.models.Role;

@Repository
public interface RoleRepository extends CrudRepository <Role, Integer> {

}
