package edu.mvc.controller;

import edu.mvc.entity.Quote;
import edu.mvc.repository.QuoteRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Controller
@RequestMapping("/")
public class IndexController {

    private QuoteRepository quoteRepository;

    public IndexController(QuoteRepository quoteRepository) {
        this.quoteRepository = quoteRepository;
    }

    @GetMapping
    public String index(ModelMap model) {

        Iterable<Quote> quotes = quoteRepository.findAll();
        List<Quote> quoteList = new ArrayList<>();
        quotes.forEach(quoteList::add);

        if (!quoteList.isEmpty()) {
            Quote quote = quoteList.get(new Random().nextInt(quoteList.size()));
            model.addAttribute("quote", quote);
        }

        return "index";
    }
}
