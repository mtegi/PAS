package login.controller;

import login.model.IUserRepository;
import login.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
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
        return "register";
    }

    @PostMapping("/register")
    public String handlePostRequest (@Valid @ModelAttribute("newUser") UserModel newUser, BindingResult bindingResult, Model model) {
        if(bindingResult.hasErrors()){
            System.out.println(bindingResult.getFieldErrorCount());
            for (FieldError err:bindingResult.getFieldErrors()){
                System.out.println(err.getDefaultMessage()); // Output: must be greater than or equal to 10
            }
        }else{
            newUser.setRoles("USER");
            userRepository.addUser(newUser);
            return "redirect/:login";
        }
        return "register";
    }

}
