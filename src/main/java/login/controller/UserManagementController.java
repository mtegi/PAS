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
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Arrays;

@Controller
public class UserManagementController {

    private IUserService userService;
    private final String ROLE_USER;
    private final String ROLE_DEACTIVATED;

    {
        ROLE_USER = "USER";
        ROLE_DEACTIVATED = "DEACTIVATED";
    }

    @Autowired
    public UserManagementController(IUserService userService) {
        this.userService = userService;
    }

    @GetMapping({"/admin/manage-users"})
    public String handleGetRequest (Model model) {
        ArrayList<UserModel> users = (ArrayList<UserModel>) userService.getAll();
        model.addAttribute("users", users);
        return "usermanagement";
    }

    @PostMapping({"/admin/manage-users"})
    public String handlePostRequest (@ModelAttribute("username") String userToDeactivate, Model model) {
        UserModel user = userService.findByUsername(userToDeactivate);
        if(user == null){
            model.addAttribute("errorHappened",true);
            model.addAttribute("errorMsg", "User not found");
        }
        else if(Arrays.stream(user.getRoles()).filter(s -> s.contentEquals(ROLE_DEACTIVATED)).findAny().orElse(null) != null){
            model.addAttribute("errorHappened",false);
            user.setRoles(ROLE_USER);
        }
        else{
            model.addAttribute("errorHappened",false);
            user.setRoles(ROLE_DEACTIVATED);
        }
        return handleGetRequest(model);
    }

    @GetMapping({"/account-settings"})
    public String accountSettingsGet (Model model, Principal principal) { ;
        model.addAttribute("username", principal.getName());
        return "account-settings";
    }

    @PostMapping({"/change-password"})
    public String handlePostRequest (@Valid @ModelAttribute("User") UserModel user,
                                     @RequestParam("oldpassword") String oldpassword,
                                     @RequestParam("password2") String password2,
                                     BindingResult bindingResult, Model model, Principal principal) {
        System.out.println(user.getUsername() + user.getPassword());
        if(bindingResult.hasErrors()){
            model.addAttribute("passwordChanged",false);
            model.addAttribute("errorHappened",true);
            model.addAttribute("errorMsg", bindingResult.getFieldError().getDefaultMessage());
        }else if(!user.getPassword().contentEquals(password2) || !oldpassword.contentEquals(userService.findByUsername(principal.getName()).getPassword())){
            model.addAttribute("passwordChanged",false);
            model.addAttribute("errorHappened",true);
            model.addAttribute("errorMsg", "passwords are not the same");

        }
        else{
            model.addAttribute("passwordChanged",true);
            model.addAttribute("errorHappened",false);
           UserModel currentUser = userService.findByUsername(principal.getName());
           currentUser.setPassword(user.getPassword());
           model.addAttribute("username", currentUser.getUsername());
        }
        return "account-settings";
    }
}
