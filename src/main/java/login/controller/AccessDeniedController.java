package login.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AccessDeniedController {
    @RequestMapping("/access-denied")
    public String handleRequest(){
        return "access-denied";
    }
}
