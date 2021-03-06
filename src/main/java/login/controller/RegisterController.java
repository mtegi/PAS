package login.controller;

import login.model.UserModel;
import login.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class RegisterController {

    private IUserService userService;

    @Autowired
    public RegisterController(IUserService userService) {
        this.userService = userService;
    }

    @GetMapping({"/register"})
    public String handleGetRequest (Model model) {
        model.addAttribute("errorHappened",false);
        return "register";
    }

    @PostMapping("/register")
    public String handlePostRequest (@Valid @ModelAttribute("newUser") UserModel newUser, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("errorHappened", true);
            model.addAttribute("errorMsg", bindingResult.getFieldError().getDefaultMessage());
        } else {
            model.addAttribute("errorHappened", false);
            try {
                userService.addUser(newUser);
            } catch (IllegalArgumentException e) {
                model.addAttribute("errorHappened", true);
                model.addAttribute("errorMsg", e.getMessage());
            } finally {
                return "register";
            }
        }
        return "register";
    }

}
