package pl.iasipovich.composition;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("")
    public String toHomePage(){
        return"home";
    }
}

