package login.controller;

import login.model.UserModel;
import login.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
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
    return viewAll(model);
    }

    private String viewAll(Model model)
    {
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
            userService.expireUserSessions(user.getUsername());
        }
        else{
            model.addAttribute("errorHappened",false);
            user.setRoles(ROLE_DEACTIVATED);
            userService.expireUserSessions(user.getUsername());
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

    @PostMapping({"/admin/adduser"})
    public String  addUser (@RequestParam("username") String username,
                                     @RequestParam("password") String password,
                                     @RequestParam("roles") String role, Model model) {
        model.addAttribute("errorHappened", false);
        try {
            if(role.equalsIgnoreCase("USER"))
                throw new IllegalArgumentException(role);

            UserModel user = new UserModel(username,password,role);

            userService.addUser(user);
        } catch (IllegalArgumentException e) {
            model.addAttribute("errorHappened", true);
            model.addAttribute("errorMsg", e.getMessage());
        } finally {
            return viewAll(model);
        }
    }

    @GetMapping({"/admin/edituser"})
    public String showEditView( @RequestParam("username") String username, Model model)
    {
        UserModel user = userService.findByUsername(username);
        model.addAttribute("user",user);
        return "userEdit";
    }

    @PostMapping({"/admin/edituser"})
    public String editUser(@RequestParam("username") String username, @RequestParam("password") String password,
          @RequestParam("roles") String roles,  @RequestParam("originalUsername") String originalUsername, Model model){

        model.addAttribute("errorHappened",false);
        UserModel user = userService.findByUsername(originalUsername);
        try {
            if (user == null) {
                throw new NullPointerException("Requested user does not exist");
            }

            if(username!="") {
                if (userService.findByUsername(username) == null)
                    user.setUsername(username);
                else
                    throw new IllegalArgumentException("username already taken");
            }

            if(password!="")
            user.setPassword(password);

            user.setRoles(roles);

        } catch (NullPointerException|IllegalArgumentException e)
        {
            model.addAttribute("errorHappened",true);
            model.addAttribute("errorMsg", e.getMessage());
        }
        finally
        {
            return viewAll(model);
        }

    }


}
