package com.alamincse.main.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserService service;

    @GetMapping("/users")
    public String showAllUser(Model m) {
        List<User> listUsers = service.allUser();
        m.addAttribute("listUsers", listUsers);
        return "users";
    }

    @GetMapping("/users/new")
    public String addNewUser(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("pageTitle", "Add new user");
        return "user_form";
    }

    @PostMapping("/users/save")
    public String saveUser(User user , RedirectAttributes ra) {
        service.save(user);
        ra.addFlashAttribute("message", "The user add successfully");
        return "redirect:/users";
    }

    @GetMapping("/users/edit/{id}")
    public String updateUser(@PathVariable("id") Integer id ,Model m, RedirectAttributes ra ) {
        try {
            User user = service.get(id);
            m.addAttribute("user" ,"user");
            m.addAttribute("pageTitle" , "Edit user ( " + id + " )");
            return "user_form";

        } catch (UserNotFoundException e) {
            ra.addFlashAttribute("message", e.getMessage());
            return "redirect:/users";
        }
    }

    @GetMapping("/users/delete/{id}")
    public String deteteUser(@PathVariable("id") Integer id , RedirectAttributes ra)  {
        try {
            service.delete(id);
            ra.addFlashAttribute("message", "The User " + id + " has been deleted successfully.");

        } catch (UserNotFoundException e) {
            ra.addFlashAttribute("message" , e.getMessage());
        }

        return "redirect:/users";
    }

    @GetMapping("/users/search")
    public String searchUsers(@RequestParam("keyword") String keyword, Model model) {
        List<User> searchResults = service.search(keyword);
        model.addAttribute("listUsers", searchResults);
        model.addAttribute("pageTitle", "Search Results for: " + keyword);
        return "users";
    }

}
