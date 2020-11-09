package all.hiber.controller;

import all.hiber.model.User;
import all.hiber.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/users")
public class UserController {
    @Autowired
    public UserService userService;


    @GetMapping()
    public String allUser (Model model){
        model.addAttribute("users", userService.allUsers());
        return "users/users";
    }

    @GetMapping("/{id}")
    public String Show(@PathVariable("id") long id, Model model){
        model.addAttribute("user", userService.getById(id));
        return"users/user";
    }
    @GetMapping("/new")
    public String newUser(@ModelAttribute("user") User user){
        return "users/new";
    }

    @PostMapping
    public String create(@ModelAttribute("user") User user){
        userService.add(user);
        return "redirect:/users";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") long id){
        model.addAttribute("user", userService.getById(id));
        return "users/edit";
    }

    @PatchMapping("/{id}")
    public String udgate(@ModelAttribute("user") User user, @PathVariable("id") long id){
        userService.edit(id, user);
        return "redirect:/users";
    }

    @DeleteMapping("/{id}")
    public String delete(@ModelAttribute("user") User user, @PathVariable("id") long id){
        userService.delete(id);
        return "redirect:/users";
    }


}
