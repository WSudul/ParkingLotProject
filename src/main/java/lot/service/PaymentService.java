package lot.service;


import lot.model.Plate;
import lot.repository.CreditRepository;
import lot.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final CreditRepository creditRepository;

    @Autowired
    public PaymentService(PaymentRepository paymentRepository, CreditRepository creditRepository) {
        this.paymentRepository = paymentRepository;
        this.creditRepository = creditRepository;
    }


    /**
     * @param plate licence plate
     * @return false if vehicle is already in lot otherwise true
     */
    public boolean logEntry(Plate plate) {
        //todo implementation
        return false;
    }

    /**
     * @param plate licence plate
     * @return false if vehicle was not in lot otherwise true
     */
    public boolean logDeparture(Plate plate) {
        //todo implementation
        return false;
    }

    public boolean isVehicleInLot(Plate plate) {
        //todo implementation
        return false;
    }

    public List<Plate> currentLotStatus() {
        return new ArrayList<>();
    }

}
