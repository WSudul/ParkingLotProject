package lot.controller;

import com.google.protobuf.Timestamp;
import lot.model.Plate;
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
    public ResponseEntity<PlateValidation.PlateValidationResponse> logEntry(@RequestBody PlateValidation
            .PlateValidationRequest message) {
        Optional<Plate> matchingPlate = plateService.findMatchingPlate(message.getPlate());

        PlateValidation.PlateValidationResponse.Builder messageBuilder = PlateValidation.PlateValidationResponse
                .newBuilder()
                .setPlate(message.getPlate());

        if (matchingPlate.isPresent()) {
            boolean result = entryService.logEntry(matchingPlate.get());
            messageBuilder = addCurrentTimestamp(messageBuilder);

            if (result) {
                messageBuilder.setValidated(true);
                return ResponseEntity.ok().body(messageBuilder.build());
            } else {
                messageBuilder.setValidated(false);
                messageBuilder.addDetails("Vehicle is already in the lot");
                return ResponseEntity.status(HttpStatus.CONFLICT).body(messageBuilder.build());
            }
        } else {
            messageBuilder = addCurrentTimestamp(messageBuilder);
            messageBuilder.addDetails("Plate not recognized by system");
            return ResponseEntity.status(HttpStatus.CONFLICT).body(messageBuilder.build());
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


    @RequestMapping(value = "departure", method = RequestMethod.POST)
    public void logDeparture() {

    }

}
