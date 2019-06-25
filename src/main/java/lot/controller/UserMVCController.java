package lot.controller;


import lot.model.UpdateUserData;
import lot.model.User;
import lot.model.UserRegistrationData;
import lot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping(value = "/users")
public class UserMVCController {


    private UserService userService;

    @Autowired
    public UserMVCController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = {"/index", "", "all"})
    public String showAllusers(Model model) {
        //System.out.println("\n\n-----------\n" + userService.findAllUsers().size());
        model.addAttribute("users", userService.findAllUsers());
        return "index";
    }

    @GetMapping("/signup")
    public String showSignUpForm(UserRegistrationData userRegistrationData) {

        return "add-user";
    }

    @PostMapping("/adduser")
    public String addUser(@Valid UserRegistrationData userRegistrationData, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("users", userService.findAllUsers());
            return "add-user";
        }
        System.out.println(userRegistrationData);
        userService.registerNewUserAccount(userRegistrationData);
        System.out.println("\n\n-----------\n" + userService.findAllUsers());
        model.addAttribute("users", userService.findAllUsers());
        return "index";
    }

    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
        User user = userService.findUserById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        model.addAttribute("user", user);
        return "update-user";
    }

    @PostMapping("/update/{id}")
    public String updateUser(@PathVariable("id") long id, @Valid UpdateUserData userData, BindingResult result, Model
            model) {
        if (result.hasErrors()) {
            return "update-user";
        }
        User user = userService.findUserById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));

        userService.findUserById(id);
        userService.updateUser(user, userData);

        model.addAttribute("users", userService.findAllUsers());
        return "index";
    }

}