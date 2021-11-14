package pl.iasipovich.composition.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {

    @GetMapping("/home")
    public String get(Model model){

        return"dashboard";
    }
}