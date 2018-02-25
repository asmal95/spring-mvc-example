package edu.mvc.controller;

import edu.mvc.controller.response.ResourceNotFoundException;
import edu.mvc.controller.valid.AuthorValidator;
import edu.mvc.entity.Author;
import edu.mvc.repository.AuthorRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/author")
public class AuthorController {

    private AuthorRepository authorRepository;

    private AuthorValidator authorValidator;

    public AuthorController(AuthorRepository authorRepository, AuthorValidator authorValidator) {
        this.authorRepository = authorRepository;
        this.authorValidator = authorValidator;
    }

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.addValidators(authorValidator);
    }


    @GetMapping
    public String list(ModelMap model) {
        model.addAttribute("authors", authorRepository.findAll());
        return "author/list";
    }

    @GetMapping("/{id}")
    public String view(@PathVariable Long id, ModelMap model) {
        Author author = authorRepository.findOne(id);
        if (author == null) {
            throw new ResourceNotFoundException();
        }
        model.addAttribute("author", author);
        return "author/view";
    }

    @GetMapping("/add")
    public String add(ModelMap model) {

        model.addAttribute("author", new Author());

        return "author/add";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable Long id, ModelMap model) {

        Author author = authorRepository.findOne(id);
        if (author == null) {
            throw new ResourceNotFoundException();
        }
        model.addAttribute("author", author);

        return "author/add";
    }

    @PostMapping("/add")
    public String add(@Valid Author author, BindingResult result) {

        if (result.hasErrors()) {
            return "author/add";
        }

        authorRepository.save(author);

        return "redirect:/author/" + author.getId();
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Long id) {

        authorRepository.delete(id);

        return "redirect:/author";
    }

}