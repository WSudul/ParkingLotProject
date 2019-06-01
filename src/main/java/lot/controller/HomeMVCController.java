package lot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/")
public class HomeMVCController {
    @RequestMapping(value = {"", "/index", "home"}, method = RequestMethod.GET)
    String returnIndex() {
        return "index";
    }
}
