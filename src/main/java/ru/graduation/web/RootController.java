package ru.graduation.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ru.graduation.repository.RestaurantRepository;
import ru.graduation.repository.UserRepository;

import javax.servlet.http.HttpServletRequest;

@Controller
public class RootController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RestaurantRepository restaurantRepository;

    @GetMapping("/")
    public String root() {
        return "index";
    }

//    @GetMapping("/users")
//    public String getUsers(Model model) {
//        model.addAttribute("users", userService.getAll());
//        return "users";
//    }

    @PostMapping("/users")
    public String setUser(HttpServletRequest request) {
        int userId = Integer.parseInt(request.getParameter("userId"));
        SecurityUtil.setAuthUserId(userId);
        return "redirect:restaurants";
    }

    @GetMapping("/restaurants")
    public String getMeals(Model model) {
        model.addAttribute("restaurants",
                restaurantRepository.getAll());
        return "restaurants";
    }
}
