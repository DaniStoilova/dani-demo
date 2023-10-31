package com.dictionaryapp.controller;

import com.dictionaryapp.model.binding.AddWordBindingModel;
import com.dictionaryapp.model.helper.CurrentUser;
import com.dictionaryapp.service.WordService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class WordController {

    private final WordService wordService;

    private final CurrentUser currentUser;


    public WordController(WordService wordService, CurrentUser currentUser) {
        this.wordService = wordService;
        this.currentUser = currentUser;
    }

    @GetMapping("/add")
    public String add() {
        if (!currentUser.isLogged()) {
            return "redirect:/login";
        }
        return "word-add";
    }

    @PostMapping("/add")
    public String confirmWord(@Valid AddWordBindingModel addWordBindingModel,
                              BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (!currentUser.isLogged()) {
            return "redirect:/login";
        }

        if (bindingResult.hasErrors()) {

            redirectAttributes
                    .addFlashAttribute("addWordBindingModel", addWordBindingModel)
                    .addFlashAttribute("org.springframework.validation.BindingResult.addWordBindingModel", bindingResult);

            return "redirect:add";
        }


        wordService.addWord(addWordBindingModel);

        return "redirect:home";
    }

    @GetMapping("/remove/{id}")
    public String remove(@PathVariable("id") Long id) {
        if (!currentUser.isLogged()) {
            return "redirect:/login";
        }

        wordService.remove(id);

        return "redirect:/home";

    }

    @GetMapping("/removeAll")
    public String removeAllWord() {
        if (!currentUser.isLogged()) {
            return "redirect:/login";
        }

        wordService.removeAll();

        return "redirect:/home";
    }


    @ModelAttribute
    public AddWordBindingModel addWordBindingModel() {
        return new AddWordBindingModel();
    }


}
