package pl.iasipovich.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import pl.iasipovich.service.UserService;
import pl.iasipovich.dto.UserRegistrationDto;

import pl.iasipovich.model.User;

import pl.iasipovich.repository.UserRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/registration")
public class UserRegistrationController {
    @Autowired
    private UserRepository userRepository;
    private UserService userService;
    List<String> placeholders = Arrays.asList("", "", "", "");

    public UserRegistrationController(UserService userService) {
        super();
        this.userService = userService;
    }

    @ModelAttribute("user")
    public UserRegistrationDto userRegistrationDto() {
        return new UserRegistrationDto();
    }

    @GetMapping
    public String showRegistrationForm() {
        return "registration";
    }

    @PostMapping
    public String registerUserAccount(@ModelAttribute("user") UserRegistrationDto registrationDto,Model model) {
        Iterable<User> users = userRepository.findAll();
        List<String> errors = new ArrayList<>();
        users.forEach(user -> {
            if(user.getEmail().equals(registrationDto.getEmail()))
                errors.add("Email is already exist");
        });
        if(registrationDto.getFirstName().length()>255)errors.add("Fields First Name is too long,(max 255 symbols)");
        if(registrationDto.getLastName().length()>255)errors.add("Fields Last Name is too long,(max 255 symbols)");
        if(registrationDto.getPassword().length()>255)errors.add("Fields password is too long,(max 255 symbols)");
        if(registrationDto.getEmail().length()>255)errors.add("Fields Email is too long,(max 255 symbols)");
        if(registrationDto.getPassword().length()<8&&registrationDto.getPassword().length()>0)errors.add("Password may have at least 8 characters");
        if(registrationDto.getEmail().isEmpty()||registrationDto.getFirstName().isEmpty()||registrationDto.getLastName().isEmpty()||registrationDto.getPassword().isEmpty())errors.add("All fields must be complete");

        if(errors.isEmpty() && !registrationDto.getEmail().isEmpty() && !registrationDto.getFirstName().isEmpty() && !registrationDto.getLastName().isEmpty() && !registrationDto.getPassword().isEmpty()){
            userService.save(registrationDto);
            return "redirect:/registration?success";
        }
        else {
            model.addAttribute("errors",errors);
            return"/registration";
        }

    }
}

