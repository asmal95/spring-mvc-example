package edu.mvc.controller.valid;

import edu.mvc.entity.Theme;
import edu.mvc.repository.ThemeRepository;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Objects;

@Component
public class ThemeValidator implements Validator {

    private ThemeRepository repository;

    public ThemeValidator(ThemeRepository repository) {
        this.repository = repository;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Theme.class.equals(clazz);
    }

    @Override
    public void validate(Object o, Errors errors) {

        Theme theme = (Theme) o;
        Theme savedTheme = repository.findByTitle(theme.getTitle());
        if (Objects.nonNull(savedTheme) && !savedTheme.getId().equals(theme.getId())) {
            errors.rejectValue("title", "theme.title.nonUnique");
        }
    }
}
