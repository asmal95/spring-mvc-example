package edu.mvc.controller.form.convert;

import edu.mvc.controller.form.FormQuote;
import edu.mvc.entity.Quote;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class FormQuoteConverter implements Converter<Quote, FormQuote> {


    @Override
    public FormQuote convert(Quote quote) {
        FormQuote formQuote = new FormQuote();
        formQuote.setId(quote.getId());
        formQuote.setText(quote.getText());
        formQuote.setAuthorId(quote.getAuthor().getId());
        formQuote.setThemeId(quote.getTheme().getId());

        return formQuote;
    }
}
