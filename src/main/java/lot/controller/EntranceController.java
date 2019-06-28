package lot.controller;


import lot.model.Lot;
import lot.model.Plate;
import lot.model.PlateValidationRequest;
import lot.model.PlateValidationResponse;
import lot.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.time.Clock;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/gate/")
public class EntranceController {

    private EntryService entryService;
    private NotificationService notificationService;
    private PaymentService paymentService;
    private PlateService plateService;
    private LotService lotService;

    private Clock clock = Clock.systemDefaultZone();
    Logger logger = LoggerFactory.getLogger(EntranceController.class);

    @Autowired
    public EntranceController(EntryService entryService, NotificationService notificationService, PaymentService
            paymentService, PlateService plateService, LotService lotService) {
        this.entryService = entryService;
        this.notificationService = notificationService;
        this.paymentService = paymentService;
        this.plateService = plateService;
        this.lotService = lotService;
    }


    @RequestMapping(value = "entrance", method = RequestMethod.POST)
    public PlateValidationResponse logEntry(@RequestBody
                                                    PlateValidationRequest message) {

        logger.info("Received entrance request from " + message.getRequester());

        Optional<Lot> lot = lotService.getLot(message.getRequester());

        PlateValidationResponse response = new PlateValidationResponse();


        if (!lot.isPresent()) {
            response.setValidation(false);
            response.setDetails(new ArrayList<>(List.of("Requester could not be matched to existing lot")));
            return response;
        }

        Optional<Plate> matchingPlate = plateService.findMatchingPlate(message.getPlates());



        boolean is_plate_found = matchingPlate.isPresent();
        if (is_plate_found) {
            boolean result = entryService.logEntry(matchingPlate.get(), lot.get());

            response.setPlate(matchingPlate.get().getPlate());
            response.setValidation(result);
            if (!result)
                response.setDetails(new ArrayList<>(List.of("Vehicle is already in the lot")));
            else {
                lotService.incrementOccupiedCount(lot.get());
            }
        } else {
            response.setValidation(false);
            response.setDetails(new ArrayList<>(List.of("Plate not recognized by system")));

        }
        return response;
    }

    @RequestMapping(value = "departure", method = RequestMethod.POST)
    public PlateValidationResponse logDeparture(@RequestBody PlateValidationRequest message) {

        logger.info("Received departure request from " + message.getRequester());
        Optional<Lot> lot = lotService.getLot(message.getRequester());


        PlateValidationResponse response = new PlateValidationResponse();

        if (!lot.isPresent()) {
            response.setValidation(false);
            response.setDetails(new ArrayList<>(List.of("Requester could not be matched to existing lot")));
            return response;
        }

        Optional<Plate> matchingPlate = plateService.findMatchingPlate(message.getPlates());

        boolean is_plate_found = matchingPlate.isPresent();
        if (is_plate_found) {
            boolean result = entryService.logDeparture(matchingPlate.get(), lot.get());
            response.setPlate(matchingPlate.get().getPlate());
            response.setValidation(result);
            if (!result) {
                response.setDetails(new ArrayList<>(List.of("Vehicle is not in the lot")));
            } else {
                lotService.decrementOccupiedLod(lot.get());
            }

        } else {
            response.setValidation(false);
            response.setDetails(new ArrayList<>(List.of("Plate not recognized by system")));

        }
        return response;


    }




}
