package edu.mvc.controller.form.convert;

import edu.mvc.controller.form.FormQuote;
import edu.mvc.entity.Quote;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class QuoteConverter implements Converter<FormQuote, Quote> {

    @Override
    public Quote convert(FormQuote quoteTransport) {
        return null;
    }
}
