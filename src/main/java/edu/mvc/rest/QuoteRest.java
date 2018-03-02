package edu.mvc.rest;

import edu.mvc.controller.form.FormQuote;
import edu.mvc.entity.Quote;
import edu.mvc.repository.QuoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rest/quote")
public class QuoteRest {

    @Autowired
    private QuoteRepository quoteRepository;

    @Autowired
    private ConversionService conversionService;

    @GetMapping("/{id}")
    public FormQuote getQuote(@PathVariable Long id) {

        Quote quote =  quoteRepository.findOne(id);
        return conversionService.convert(quote, FormQuote.class);
    }

    @DeleteMapping("/{id}")
    public void deleteQuote(@PathVariable Long id) {

        quoteRepository.delete(id);
    }

    @PutMapping
    public FormQuote putQuote(@RequestBody FormQuote formQuote) {

        Quote quote = conversionService.convert(formQuote, Quote.class);
        quote = quoteRepository.save(quote);
        return conversionService.convert(quote, FormQuote.class);
    }

    @PatchMapping("/{id}")
    public FormQuote patchQuote(@PathVariable Long id, @RequestBody FormQuote formQuote) {
        if (!id.equals(formQuote.getId()) || quoteRepository.findOne(id) == null) {
            throw new IllegalArgumentException();
        }
        Quote quote = conversionService.convert(formQuote, Quote.class);
        quote = quoteRepository.save(quote);
        return conversionService.convert(quote, FormQuote.class);
    }


}
