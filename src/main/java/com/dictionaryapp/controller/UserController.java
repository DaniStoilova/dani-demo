package com.dictionaryapp.controller;

import com.dictionaryapp.model.binding.UserLoginBindingModel;
import com.dictionaryapp.model.binding.UserRegisterBindingModel;
import com.dictionaryapp.model.helper.CurrentUser;
import com.dictionaryapp.service.UserService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
@RequestMapping("/users")
public class UserController {

    private UserService userService;

    private CurrentUser currentUser;

    public UserController(UserService userService, CurrentUser currentUser) {
        this.userService = userService;
        this.currentUser = currentUser;
    }

    @GetMapping("/register")
    public String register(){
        if (currentUser.isLogged()){

            return "redirect:/home";
        }

        return "register";
    }

    @PostMapping("/register")
    public String confirmRegister(@Valid UserRegisterBindingModel userRegisterBindingModel,
                                  BindingResult bindingResult, RedirectAttributes redirectAttributes){

        if (currentUser.isLogged()){

            return "redirect:/home";
        }

        if(bindingResult.hasErrors() || !userRegisterBindingModel.getPassword().equals(userRegisterBindingModel.getConfirmPassword())){

            redirectAttributes
                    .addFlashAttribute("userRegisterBindingModel",userRegisterBindingModel)
                    .addFlashAttribute("org.springframework.validation.BindingResult.userRegisterBindingModel",bindingResult);

            return "redirect:register";

        }


        userService.register(userRegisterBindingModel);

        return "redirect:login";
    }

    @GetMapping("/login")
    public String login(Model model){

        if (currentUser.isLogged()){
            return "redirect:/home";
        }

        if (!model.containsAttribute("isFound")){
            model.addAttribute("isFound",true);
        }

        return "login";
    }


    @PostMapping("/login")
    public String confirmLogin(@Valid UserLoginBindingModel userLoginBindingModel,
                               BindingResult bindingResult,
                               RedirectAttributes redirectAttributes) {

        if (currentUser.isLogged()){
            return "redirect:/home";
        }


        if (bindingResult.hasErrors()) {
            redirectAttributes
                    .addFlashAttribute("userLoginBindingModel", userLoginBindingModel)
                    .addFlashAttribute("org.springframework.validation.BindingResult.userLoginBindingModel", bindingResult);

            return "redirect:login";
        }
        boolean isFound = userService.findByUsernameAndPassword(userLoginBindingModel.getUsername(),userLoginBindingModel.getPassword());

        if(!isFound){
            redirectAttributes
                    .addFlashAttribute("userLoginBindingModel", userLoginBindingModel)
                    .addFlashAttribute("isFound",false);


            return "redirect:login";
        }

        userService.login(userLoginBindingModel);

        return "redirect:/home";
    }

    @GetMapping("/logout")
    public String logout(HttpSession httpSession){
        if (!currentUser.isLogged()){
            return "redirect:/home";
        }

        httpSession.invalidate();

       return "redirect:/";
    }



    @ModelAttribute
    public UserRegisterBindingModel userRegisterBindingModel(){
        return new UserRegisterBindingModel();
    }

    @ModelAttribute
    public UserLoginBindingModel userLoginBindingModel(){
        return new UserLoginBindingModel();
    }







}
