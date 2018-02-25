package edu.mvc.controller.valid;

import edu.mvc.entity.Author;
import edu.mvc.repository.AuthorRepository;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Objects;

@Component
public class AuthorValidator implements Validator {

    private AuthorRepository repository;

    public AuthorValidator(AuthorRepository repository) {
        this.repository = repository;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Author.class.equals(clazz);
    }

    @Override
    public void validate(Object o, Errors errors) {

        Author author = (Author) o;
        if (Objects.nonNull(repository.findByName(author.getName()))) {
            errors.rejectValue("name", "author.name.nonUnique");
        }
    }
}
