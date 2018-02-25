package edu.mvc.controller;

import edu.mvc.controller.form.FormQuote;
import edu.mvc.controller.response.ResourceNotFoundException;
import edu.mvc.entity.Quote;
import edu.mvc.repository.AuthorRepository;
import edu.mvc.repository.QuoteRepository;
import edu.mvc.repository.ThemeRepository;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/quote")
public class QuoteController {

    private QuoteRepository quoteRepository;

    private ThemeRepository themeRepository;

    private AuthorRepository authorRepository;

    private ConversionService conversionService;

    public QuoteController(QuoteRepository quoteRepository, ThemeRepository themeRepository,
                           AuthorRepository authorRepository, ConversionService conversionService) {
        this.quoteRepository = quoteRepository;
        this.themeRepository = themeRepository;
        this.authorRepository = authorRepository;
        this.conversionService = conversionService;
    }

    @GetMapping
    public String list(ModelMap model) {
        model.addAttribute("quotes", quoteRepository.findAll());
        return "quote/list";
    }

    @GetMapping("/{id}")
    public String view(@PathVariable Long id, ModelMap model) {
        Quote quote = quoteRepository.findOne(id);
        if (quote == null) {
            throw new ResourceNotFoundException();
        }
        model.addAttribute("quote", quote);
        return "quote/view";
    }

    @GetMapping("/add")
    public String add(ModelMap model) {

        model.addAttribute("quote", new FormQuote());
        model.addAttribute("authors", authorRepository.findAll());
        model.addAttribute("themes", themeRepository.findAll());

        return "quote/add";
    }

    @GetMapping("/{id}/edit")
    public String add(@PathVariable Long id, ModelMap model) {

        Quote quote = quoteRepository.findOne(id);
        if (quote == null) {
            throw new ResourceNotFoundException();
        }
        FormQuote formQuote = conversionService.convert(quote, FormQuote.class);
        model.addAttribute("quote", formQuote);
        model.addAttribute("authors", authorRepository.findAll());
        model.addAttribute("themes", themeRepository.findAll());

        return "quote/add";
    }

    @PostMapping("/add")
    public String add(@Valid @ModelAttribute("quote") FormQuote formQuote,
                      BindingResult result, ModelMap model) {
        if (result.hasErrors()) {

            model.addAttribute("authors", authorRepository.findAll());
            model.addAttribute("themes", themeRepository.findAll());
            return "quote/add";
        }

        Quote quote = conversionService.convert(formQuote, Quote.class);

        quoteRepository.save(quote);

        return "redirect:/quote/" + quote.getId();
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Long id) {

        quoteRepository.delete(id);

        return "redirect:/quote";
    }


}
