package login.controller;

import login.model.IUserRepository;
import login.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;

@Controller
public class UserManagementController {

    private IUserRepository userRepository;

    @Autowired
    public UserManagementController(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping({"/admin/manage-users"})
    public String handleGetRequest (Model model) {
        ArrayList<UserModel> users = (ArrayList<UserModel>) userRepository.getAll();
        System.out.println(users.size());
        model.addAttribute("users", users);
        return "usermanagement";
    }

}
