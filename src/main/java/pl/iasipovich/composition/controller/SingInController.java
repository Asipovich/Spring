package pl.iasipovich.composition.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SingInController {

    @GetMapping("/singin")
    public String get(Model model){

        return"singin";
    }
}

