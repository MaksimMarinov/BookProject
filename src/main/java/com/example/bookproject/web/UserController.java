package com.example.bookproject.web;

import com.example.bookproject.model.binding.UserRegisterBindingModel;
import com.example.bookproject.model.service.UserServiceModel;
import com.example.bookproject.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.validation.BindingResult;

import javax.validation.Valid;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final ModelMapper modelMapper;

    public UserController(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }


    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/login-error")
    public String failedLogin(
            @ModelAttribute(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_USERNAME_KEY)
                    String userName,
            RedirectAttributes attributes
    ) {

        attributes.addFlashAttribute("bad_credentials", true);
        attributes.addFlashAttribute("username", userName);

        return "redirect:login";
    }

    @GetMapping("/register")
    public String register(Model model) {
        if (!model.containsAttribute("userModel")) {
            model.addAttribute("userModel", new UserRegisterBindingModel());
            model.addAttribute("passwordsNotMatched", false);
            model.addAttribute("userNameOccupied", false);
        }

        return "register";
    }

    @PostMapping("/register")
    public String registerConfirm(@Valid UserRegisterBindingModel userModel,
                                  BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("userModel", userModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userModel",
                    bindingResult);
            return "redirect:register";
        }

        if(!userModel.getPassword().equals(userModel.getConfirmPassword())){
            redirectAttributes.addFlashAttribute("userModel", userModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userModel",
                    bindingResult);
            redirectAttributes.addFlashAttribute("passwordsNotMatched", true);
            return "redirect:register";
        }

        boolean isNameFree = userService.isUserNameFree(userModel.getFirstName());
       if(!isNameFree){
           redirectAttributes.addFlashAttribute("userModel", userModel);
           redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userModel",
                   bindingResult);
           redirectAttributes.addFlashAttribute("userNameOccupied", true);
           return "redirect:register";
       }


        UserServiceModel userServiceModel = modelMapper.map(userModel, UserServiceModel.class);
        userService.registerUser(userServiceModel);

        return "redirect: login";
    }


}
