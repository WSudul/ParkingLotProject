package lot.controller;

import com.google.protobuf.Timestamp;
import lot.model.Plate;
import lot.model.PlateValidationRequest;
import lot.model.PlateValidationResponse;
import lot.service.EntryService;
import lot.service.NotificationService;
import lot.service.PaymentService;
import lot.service.PlateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import plate.PlateValidation;

import java.time.Clock;
import java.time.Instant;
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

    private Clock clock = Clock.systemDefaultZone();

    @Autowired
    public EntranceController(EntryService entryService, NotificationService notificationService, PaymentService
            paymentService, PlateService plateService) {
        this.entryService = entryService;
        this.notificationService = notificationService;
        this.paymentService = paymentService;
        this.plateService = plateService;
    }


    @RequestMapping(value = "entrance", method = RequestMethod.POST)
    public PlateValidationResponse logEntry(@RequestBody
                                                    PlateValidationRequest message) {
        Optional<Plate> matchingPlate = plateService.findMatchingPlate(message.getPlate());


        PlateValidationResponse response = new PlateValidationResponse();
        response.setPlate(message.getPlate());

        boolean is_plate_found = matchingPlate.isPresent();
        if (is_plate_found) {
            boolean result = entryService.logEntry(matchingPlate.get());
            response.setValidation(result);
            if (!result)
                response.setDetails(new ArrayList<>(List.of("Vehicle is already in the lot")));



        } else {
            response.setValidation(false);
            response.setDetails(new ArrayList<>(List.of("Plate not recognized by system")));

        }
        return response;
    }

    @RequestMapping(value = "departure", method = RequestMethod.POST)
    public PlateValidationResponse logDeparture(@RequestBody PlateValidationRequest message) {

        Optional<Plate> matchingPlate = plateService.findMatchingPlate(message.getPlate());


        PlateValidationResponse response = new PlateValidationResponse();
        response.setPlate(message.getPlate());

        boolean is_plate_found = matchingPlate.isPresent();
        if (is_plate_found) {
            boolean result = entryService.logDeparture(matchingPlate.get());
            response.setValidation(result);
            if (!result)
                response.setDetails(new ArrayList<>(List.of("Vehicle is not in the lot")));
        } else {
            response.setValidation(false);
            response.setDetails(new ArrayList<>(List.of("Plate not recognized by system")));

        }
        return response;


    }

    private ResponseEntity<PlateValidation.PlateValidationResponse> buildResponse(PlateValidation
                                                                                          .PlateValidationResponse
                                                                                          .Builder builder, boolean
            result, List<String> details) {
        if (result) {
            builder.setValidated(true);
            return ResponseEntity.ok().body(builder.build());
        } else {
            builder.setValidated(false);
            return ResponseEntity.status(HttpStatus.CONFLICT).body(builder.build());

        }
    }

    private PlateValidation.PlateValidationResponse.Builder addCurrentTimestamp(PlateValidation
                                                                                        .PlateValidationResponse
                                                                                        .Builder builder) {
        Instant instant = clock.instant();
        Timestamp timestamp = Timestamp.newBuilder().setSeconds(instant.getEpochSecond()).setNanos(instant.getNano())
                .build();
        builder.setTimestamp(timestamp);

        return builder;
    }



}
