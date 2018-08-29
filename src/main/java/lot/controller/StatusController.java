package lot.controller;

import lot.model.LotStatus;
import lot.service.EntryService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;

@RestController
@RequestMapping(value = "/status/")
public class StatusController {

    private EntryService entryService;


    @RequestMapping(value = "/lot", method = RequestMethod.GET)
    LotStatus checkLotStatus() {
        LotStatus lotStatus = new LotStatus();

        lotStatus.setOccupied((long) entryService.currentLotStatus().size());
        lotStatus.setCapacity(100L); //todo add method to service
        lotStatus.setLastUpdate(Instant.now());
        lotStatus.setLocation("Location-1"); //todo location
        return lotStatus;
    }


}
