package rest_2.repository;

import org.springframework.data.repository.CrudRepository;
import rest_2.entity.Person;

public interface PersonRepository extends CrudRepository<Person, Long> {
}
