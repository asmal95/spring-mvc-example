package edu.mvc.controller.form.convert;

import edu.mvc.controller.form.FormQuote;
import edu.mvc.controller.response.ResourceNotFoundException;
import edu.mvc.entity.Author;
import edu.mvc.entity.Quote;
import edu.mvc.entity.Theme;
import edu.mvc.repository.AuthorRepository;
import edu.mvc.repository.ThemeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class QuoteConverter implements Converter<FormQuote, Quote> {

    private final ThemeRepository themeRepository;
    private final AuthorRepository authorRepository;

    @Autowired
    public QuoteConverter(ThemeRepository themeRepository, AuthorRepository authorRepository) {
        this.themeRepository = themeRepository;
        this.authorRepository = authorRepository;
    }

    @Override
    public Quote convert(FormQuote formQuote) {

        Quote quote = new Quote();
        quote.setId(formQuote.getId());
        quote.setText(formQuote.getText());

        Author author = authorRepository.findById(formQuote.getAuthorId())
                .orElseThrow(ResourceNotFoundException::new);

        Theme theme = themeRepository.findById(formQuote.getThemeId())
                .orElseThrow(ResourceNotFoundException::new);

        quote.setAuthor(author);
        quote.setTheme(theme);

        return quote;
    }
}
