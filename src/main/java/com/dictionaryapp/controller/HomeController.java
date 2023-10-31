package com.dictionaryapp.controller;

import com.dictionaryapp.model.enums.LanguageEnum;
import com.dictionaryapp.model.helper.CurrentUser;
import com.dictionaryapp.service.WordService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
public class HomeController {

    private final CurrentUser currentUser;
    private final WordService wordService;

    public HomeController(CurrentUser currentUser, WordService wordService) {
        this.currentUser = currentUser;
        this.wordService = wordService;
    }

    @GetMapping("/")
    public String index() {
        if (!currentUser.isLogged()) {
            return "index";
        }
        return "redirect:/home";
    }


    @GetMapping("/home")
    public String home(Model model) {

        if (!currentUser.isLogged()) {
            return "redirect:/";
        }


        model.addAttribute("german",wordService.findWordByLanguageName(LanguageEnum.GERMAN));
        model.addAttribute("spanish",wordService.findWordByLanguageName(LanguageEnum.SPANISH));
        model.addAttribute("italian",wordService.findWordByLanguageName(LanguageEnum.ITALIAN));
        model.addAttribute("french",wordService.findWordByLanguageName(LanguageEnum.FRENCH));
        model.addAttribute("total",wordService.getAllTotal());



        return "home";
    }


}
