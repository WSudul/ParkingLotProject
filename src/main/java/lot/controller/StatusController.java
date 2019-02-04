package lot.controller;

import lot.model.Lot;
import lot.model.LotEntry;
import lot.model.LotStatus;
import lot.service.EntryService;
import lot.service.LotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping(value = "/status/rest/")
public class StatusController {

    private EntryService entryService;
    private LotService lotService;

    @Autowired
    public StatusController(EntryService entryService, LotService lotService) {
        this.entryService = entryService;
        this.lotService = lotService;
    }

    @RequestMapping(value = "/capacity", method = RequestMethod.GET)
    LotStatus checkLotStatus(@RequestParam String name) {
        Optional<Lot> lot = lotService.getLot(name);
        if (lot.isPresent())
            return lot.get().getLotStatus();
        else {
            return null;
        }

    }

    @RequestMapping(value = "/current", method = RequestMethod.GET)
    Set<LotEntry> currentLotStatus() {
        return entryService.currentActiveEntries();
    }
    
    @RequestMapping(value = "/entries", method = RequestMethod.GET)
    Set<LotEntry> entryHistory(@RequestParam String plate) {
        return entryService.entryHistory(plate);
    }


}
