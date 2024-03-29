package lot.controller;

import lot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/")
public class HomeMVCController {

    @Autowired
    UserService userService;


    @RequestMapping(value = {"", "/index", "home"}, method = RequestMethod.GET)
    String returnIndex(Model model) {
        model.addAttribute("users", userService.findAllUsers());
        return "index";
    }
}
