package edu.mvc.repository;

import edu.mvc.entity.Author;
import org.springframework.data.repository.CrudRepository;

public interface AuthorRepository extends CrudRepository<Author, Long> {

    Author findByName(String name);
}
