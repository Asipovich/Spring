package pl.iasipovich.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;
import pl.iasipovich.dto.UserRegistrationDto;
import pl.iasipovich.model.Dish;
import pl.iasipovich.repository.DishRepository;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
public class MainController {
    @Autowired
    private DishRepository dishRepository;
    @GetMapping("/login")
    public String login() {
        return "login";
    }
    List<String> placeholders = Arrays.asList("", "", "");

    @GetMapping("/")
    public String home(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();
        Iterable<Dish> allDishes = dishRepository.findAll();
        List<Dish> dishes = new ArrayList<>();
        allDishes.forEach(dish -> {
            if(dish.getUser_name().equals(name))
                dishes.add(dish);
        });
        model.addAttribute("dishes",dishes);
        model.addAttribute("name", name);
        return "index";
    }

    @GetMapping("/add")
    public String get(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();
        model.addAttribute("placeholders", placeholders);
        model.addAttribute("name", name);
        return "add";
    }

    @PostMapping("/add")
    public String addNewDish(@RequestParam String dish_name, @RequestParam String dish_desc,
                             @RequestParam String dish_recipe, Model model) {
        List<String> errors = new ArrayList<>();
        Iterable<Dish> dishes = dishRepository.findAll();
        Iterable<Dish> allDishes = dishRepository.findAll();
        List<String> placeholders = new ArrayList<>();
        placeholders.add(dish_name);
        placeholders.add(dish_desc);
        placeholders.add(dish_recipe);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String user_name = auth.getName();
        dishes.forEach(dish -> {
            if(dish.getUser_name().equals(user_name)&&dish.getDish_name().equals(dish_name))
                errors.add("A dish with that name already exists");
        });
        if(dish_name.length()>40)errors.add("Fields Dish Name is too long (max 40 symbols)");
        if(dish_name.contains(";"))errors.add("Fields Dish Name contains bad symbols (;)");
        if(dish_desc.length()>150)errors.add("Fields Dish description is too long (max 150 symbols)");
        if(dish_recipe.length()>1500)errors.add("Fields Dish recipe is too long (max 1500 symbols)");
        if(dish_desc.isEmpty()||dish_name.isEmpty()||dish_recipe.isEmpty())errors.add("All fields must be complete");
        if(errors.isEmpty() && !dish_desc.isEmpty() && !dish_name.isEmpty() && !dish_recipe.isEmpty()){
            Dish dish = new Dish(user_name,dish_name,dish_desc,dish_recipe);
            dishRepository.save(dish);
            List<Dish> dishes_1 = new ArrayList<>();
            allDishes.forEach(dish_1 -> {
                if(dish.getUser_name().equals(user_name))
                    dishes_1.add(dish_1);
            });
            model.addAttribute("dishes",dishes);
            model.addAttribute("name", user_name);
            return "redirect: ";
        }
        else {
            model.addAttribute("placeholders", placeholders);
            model.addAttribute("errors", errors);
            return "add";
        }
    }

    @RequestMapping("/delete{dish_name}")
    public String deleteDish(@PathVariable String dish_name, Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();
        Iterable<Dish> allDishes = dishRepository.findAll();
        Long id;
        allDishes.forEach(dish -> {
            if(dish.getUser_name().equals(name)&&dish.getDish_name().equals(dish_name)){
                dishRepository.deleteById(dish.getId());
            }
        });
        List<Dish> dishes = new ArrayList<>();
        allDishes.forEach(dish -> {
            if(dish.getUser_name().equals(name))
                dishes.add(dish);
        });
        model.addAttribute("dishes",dishes);
        model.addAttribute("name", name);
        return "redirect: ";

    }

    @RequestMapping("/edit{dish_name}")
    public String editDish(@PathVariable String dish_name, Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();
        Iterable<Dish> allDishes = dishRepository.findAll();
        List<String> placeholders = new ArrayList<>();
        allDishes.forEach(dish -> {
            if(dish.getUser_name().equals(name)&&dish.getDish_name().equals(dish_name)){
                placeholders.add(dish_name);
                placeholders.add(dish.getDish_desc());
                placeholders.add(dish.getDish_recipe());
            }
        });
        model.addAttribute("placeholders", placeholders);
        model.addAttribute("name", name);
        return "edit";

    }
    @GetMapping("/edit")
    public String edit(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();
        model.addAttribute("placeholders", placeholders);
        model.addAttribute("name", name);
        return "edit";
    }

    @PostMapping("/edit{dish_name}")
    public String saveEditedDish(@RequestParam String dish_name, @RequestParam String dish_desc,
                             @RequestParam String dish_recipe, Model model) {
        List<String> errors = new ArrayList<>();
        Iterable<Dish> dishes = dishRepository.findAll();
        Iterable<Dish> allDishes = dishRepository.findAll();
        List<String> placeholders = new ArrayList<>();
        placeholders.add(dish_name);
        placeholders.add(dish_desc);
        placeholders.add(dish_recipe);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String user_name = auth.getName();
        if(dish_desc.length()>150)errors.add("Fields Dish description is too long (max 150 symbols)");
        if(dish_recipe.length()>1500)errors.add("Fields Dish recipe is too long (max 1500 symbols)");
        if(dish_desc.isEmpty()||dish_name.isEmpty()||dish_recipe.isEmpty())errors.add("All fields must be complete");
        if(errors.isEmpty() && !dish_desc.isEmpty() && !dish_name.isEmpty() && !dish_recipe.isEmpty()){
            dishes.forEach(dish -> {
                if(dish.getUser_name().equals(user_name)&&dish.getDish_name().equals(dish_name)){
                   dishRepository.deleteById(dish.getId());
                }
            });
            Dish dish = new Dish(user_name,dish_name,dish_desc,dish_recipe);
            dishRepository.save(dish);
            List<Dish> dishes_1 = new ArrayList<>();
            allDishes.forEach(dish_1 -> {
                if(dish.getUser_name().equals(user_name))
                    dishes_1.add(dish_1);
            });
            model.addAttribute("dishes",dishes_1);
            model.addAttribute("name", user_name);
            return "redirect: ";
        }
        else {
            model.addAttribute("placeholders", placeholders);
            model.addAttribute("errors", errors);
            return "edit";
        }
    }

    @RequestMapping("/view{dish_name}")
    public String viewDish(@PathVariable String dish_name, Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();
        Iterable<Dish> allDishes = dishRepository.findAll();
        List<String> placeholders = new ArrayList<>();
        allDishes.forEach(dish -> {
            if(dish.getUser_name().equals(name)&&dish.getDish_name().equals(dish_name)){
                placeholders.add(dish_name);
                placeholders.add(dish.getDish_desc());
                placeholders.add(dish.getDish_recipe());
            }
        });
        model.addAttribute("placeholders", placeholders);
        model.addAttribute("name", name);
        return "view";

    }

}
