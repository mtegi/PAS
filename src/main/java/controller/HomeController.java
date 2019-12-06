package controller;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

@Controller //mowimy springowi ze to controller
public class HomeController {
    @GetMapping({"/"}) //przypisanie kontekstu
    public String handleRequest (Model model) {
        Date date = new Date();
        DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, Locale.UK);
        //dodaj atrybuty, mozemy je potem odczytac w htmlu
        model.addAttribute("date", dateFormat.format(date));
        model.addAttribute("msg", "Siemka");
        return "home";
    }
}
