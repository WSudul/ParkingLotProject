package lot.controller;

import lot.model.Lot;
import lot.model.LotEntry;
import lot.service.EntryService;
import lot.service.LotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Controller
@RequestMapping(value = "/status/")
public class StatusMVCController {

    private EntryService entryService;
    private LotService lotService;

    @Autowired
    public StatusMVCController(EntryService entryService, LotService lotService) {
        this.entryService = entryService;
        this.lotService = lotService;
    }

    @RequestMapping(value = "/lotstatus", method = RequestMethod.GET)
    String checkLotStatus(Optional<String> name, Model model) {

        if (name.isPresent()) {
            model.addAttribute("searchedName", name.get());
            Optional<Lot> lot = lotService.getLot(name.get());

            if (lot.isPresent()) {
                model.addAttribute("foundLot", lot.get());
            }
        }

        model.addAttribute("allLots", lotService.getAllLots());
        return "lots";
    }


    @RequestMapping(value = "/capacity", method = RequestMethod.GET)
    String checkLotStatus(Model model) {

        List<Lot> allLots = lotService.getAllLots();

        model.addAttribute("lots", allLots);

        return "capacity";
    }


    @RequestMapping(value = "/entries/plate", method = RequestMethod.GET)
    String getEntryHistoryForPlate(@RequestParam String plate, Model model) {

        Set<LotEntry> lotEntries = entryService.entryHistory(plate);
        model.addAttribute("entryHistory", lotEntries);
        return "plates";
    }


    @RequestMapping(value = "/entries/lot", method = RequestMethod.GET)
    String getEntryHistoryForLot(@RequestParam String name, @RequestParam Optional<Boolean> active, Model model) {
        Set<LotEntry> lotEntries = entryService.GetEntriesInLot(name, active.orElse(true));

        model.addAttribute("entryHistory", lotEntries);
        return "plates";
    }


}
