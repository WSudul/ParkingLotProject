package lot.controller;


import lot.model.LotData;
import lot.service.LotService;
import lot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping(value = "/lot")
public class LotMVCController {


    @Autowired
    private UserService userService;
    @Autowired
    private LotService lotService;


    @GetMapping("/newlot")
    public String showNewLotForm(LotData lotData) {

        return "add-lot";
    }

    @PostMapping("/addlot")
    public String addLot(@Valid LotData lotData, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("allLots", lotService.getAllLots());
            return "add-lot";
        }

        if (!lotService.addNewLot(lotData)) ;
        {
            model.addAttribute("message", "Lot could not be created");
        }
        model.addAttribute("allLots", lotService.getAllLots());

        return "lots";

    }

    @GetMapping("/all")
    public String showLots(Model model) {

        model.addAttribute("allLots", lotService.getAllLots());

        return "lots";

    }

}