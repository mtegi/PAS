package controller;

import model.DataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller //mowimy springowi ze to controller
public class ShowBooksController {

    private DataRepository dao;

    @Autowired //znajdz dao i wstrzyknij
    public ShowBooksController(DataRepository dao) {
        this.dao = dao;
    }

    @GetMapping({"/books"}) //przypisanie kontekstu
    public String handleRequest (Model model) {
        System.out.println(dao.getBooks().get(0));
        model.addAttribute("books", dao.getBooks()); //przekaz nasza liste jako 'books' do widoku
        return "books";//odwolanie do pliku htmla
    }


}
