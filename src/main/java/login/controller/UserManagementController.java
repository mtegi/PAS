package login.controller;

import login.model.UserModel;
import login.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

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
}
