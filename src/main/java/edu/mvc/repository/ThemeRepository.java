package edu.mvc.repository;

import edu.mvc.entity.Theme;
import org.springframework.data.repository.CrudRepository;

public interface ThemeRepository extends CrudRepository<Theme, Long> {

    Theme findByTitle(String name);
}
