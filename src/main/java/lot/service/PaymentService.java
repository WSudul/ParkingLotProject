package lot.service;


import lot.model.LotEntry;
import lot.repository.CreditRepository;
import lot.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

@Service
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final CreditRepository creditRepository;

    @Autowired
    public PaymentService(PaymentRepository paymentRepository, CreditRepository creditRepository) {
        this.paymentRepository = paymentRepository;
        this.creditRepository = creditRepository;
    }

    public boolean processPayment(LotEntry lotEntry) {

        return false;
    }

    public Optional<Long> calculatePaymentValue(LotEntry lotEntry) {

        if (lotEntry.getPaid()) {
            return null;
        }

        OffsetDateTime dateFrom = lotEntry.getDateFrom();
        OffsetDateTime dateNow = OffsetDateTime.now();


        //todo formalize and add configurable units and fares
        ChronoUnit chronoUnit = ChronoUnit.MINUTES;
        long fare = 10; //equivalent to 0.01

        long duration = dateFrom.until(dateNow, chronoUnit);
        Long paymentValue = duration * fare;


        return Optional.ofNullable(paymentValue);

    }


}
