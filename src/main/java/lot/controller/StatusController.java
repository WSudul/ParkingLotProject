package lot.controller;

import lot.model.LotEntry;
import lot.model.LotStatus;
import lot.service.EntryService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.List;

@RestController
@RequestMapping(value = "/status/")
public class StatusController {

    private EntryService entryService;


    @RequestMapping(value = "/capacity", method = RequestMethod.GET)
    LotStatus checkLotStatus() {
        LotStatus lotStatus = new LotStatus();

        lotStatus.setOccupied((long) entryService.currentLotStatus().size());
        lotStatus.setCapacity(100L); //todo add method to service
        lotStatus.setLastUpdate(Instant.now());
        lotStatus.setLocation("Location-1"); //todo location
        return lotStatus;
    }

    @RequestMapping(value = "/current", method = RequestMethod.GET)
    List<LotEntry> currentLotStatus() {
        return entryService.currentLotStatus();
    }


    @RequestMapping(value = "/entries", method = RequestMethod.GET)
    List<LotEntry> entryHistory(@RequestParam String plate) {
        return entryService.entryHistory(plate);
    }


}
