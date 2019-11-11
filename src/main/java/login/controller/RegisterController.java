package login.controller;

import login.model.IUserRepository;
import login.model.UserModel;
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

    private IUserRepository userRepository;

    @Autowired
    public RegisterController(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping({"/register"})
    public String handleGetRequest (Model model) {
        model.addAttribute("errorHappened",false);
        return "register";
    }

    @PostMapping("/register")
    public String handlePostRequest (@Valid @ModelAttribute("newUser") UserModel newUser, BindingResult bindingResult, Model model) {
        if(bindingResult.hasErrors()){
            model.addAttribute("errorHappened",true);
            model.addAttribute("errorMsg", bindingResult.getFieldError().getDefaultMessage());
        }else{
            model.addAttribute("errorHappened",false);
            newUser.setRoles("USER");
            if(userRepository.addUser(newUser))
                return "redirect:/login";
            else{
                model.addAttribute("errorHappened",true);
                String usernameTaken = "Username is already taken";
                model.addAttribute("errorMsg", usernameTaken);
                return "register";
            }
        }
        return "register";
    }

}
