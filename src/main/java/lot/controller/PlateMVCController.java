package lot.controller;

import lot.model.Plate;
import lot.service.PlateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(value = "/plates")
public class PlateMVCController {

    @Autowired
    PlateService plateService;


    @RequestMapping(value = {"/all"}, method = RequestMethod.GET)
    String getAllActivePlates(Model model) {
        boolean active = true;
        List<Plate> plates = plateService.findAll(active);
        model.addAttribute("plates", plates);
        model.addAttribute("title", "Plate list");
        return "plates_list";
    }

    @RequestMapping(value = {""}, method = RequestMethod.GET)
    String getPlate(String plate, Model model) {
        System.out.println("get plate called " + plate);
        boolean active = true;
        Optional<Plate> foundPlate = plateService.findMatchingPlate(plate);
        if (foundPlate.isPresent()) {
            List<Plate> plates = new ArrayList<>();
            plates.add(foundPlate.get());
            model.addAttribute("plates", plates);
            model.addAttribute("title", "Plate: " + plate);
        }
        return "plates_list";
    }
}
