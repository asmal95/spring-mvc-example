package edu.mvc.controller;

import edu.mvc.controller.response.ResourceNotFoundException;
import edu.mvc.controller.valid.ThemeValidator;
import edu.mvc.entity.Theme;
import edu.mvc.repository.ThemeRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/theme")
public class ThemeController {

    private ThemeRepository themeRepository;

    private ThemeValidator themeValidator;

    public ThemeController(ThemeRepository themeRepository, ThemeValidator themeValidator) {
        this.themeRepository = themeRepository;
        this.themeValidator = themeValidator;
    }

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.addValidators(themeValidator);
    }

    @GetMapping
    public String list(ModelMap model) {
        model.addAttribute("themes", themeRepository.findAll());
        return "theme/list";
    }

    @GetMapping("/{id}")
    public String view(@PathVariable Long id, ModelMap model) {
        Theme theme = themeRepository.findOne(id);
        if (theme == null) {
            throw new ResourceNotFoundException();
        }
        model.addAttribute("theme", theme);
        return "theme/view";
    }

    @GetMapping("/add")
    public String add(ModelMap model) {

        model.addAttribute("theme", new Theme());

        return "theme/add";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable Long id, ModelMap model) {
        Theme theme = themeRepository.findOne(id);
        if (theme == null) {
            throw new ResourceNotFoundException();
        }
        model.addAttribute("theme", theme);

        return "theme/add";
    }

    @PostMapping("/add")
    public String add(@Valid Theme theme, BindingResult result) {

        if (result.hasErrors()) {
            return "theme/add";
        }

        themeRepository.save(theme);

        return "redirect:/theme/" + theme.getId();
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Long id) {

        themeRepository.delete(id);

        return "redirect:/theme";
    }
}
