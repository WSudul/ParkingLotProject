package lot.service;


import lot.model.LotEntry;
import lot.model.Plate;
import lot.repository.LotEntryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class EntryService {
    private LotEntryRepository lotEntryRepository;

    @Autowired
    public EntryService(LotEntryRepository lotEntryRepository) {
        this.lotEntryRepository = lotEntryRepository;
    }


    /**
     * @param plate licence plate
     * @return false if vehicle is already in lot otherwise true
     */
    public boolean logEntry(Plate plate) {
        Optional<LotEntry> entry = lotEntryRepository.findOneByPlateAndDateToIsNull(plate);

        if (entry.isPresent()) {
            //log error
            return false;
        } else {
            LotEntry lotEntry = new LotEntry();
            lotEntry.setDateFrom(OffsetDateTime.now());
            lotEntry.setPayment(null);
            lotEntry.setPlate(plate);

            lotEntryRepository.save(lotEntry);

            return true;
        }

    }

    /**
     * @param plate licence plate
     * @return false if vehicle was not in lot otherwise true
     */
    public boolean logDeparture(Plate plate) {
        Optional<LotEntry> entry = lotEntryRepository.findOneByPlateAndDateToIsNull(plate);

        if (entry.isPresent()) {

            LotEntry lotEntry = entry.get();
            lotEntry.setDateTo(OffsetDateTime.now());
            lotEntryRepository.save(lotEntry);

            return true;

        } else {
            //todo log invalid state
            return false;
        }


    }

    public boolean isVehicleInLot(Plate plate) {

        return lotEntryRepository.findOneByPlateAndDateToIsNull(plate).isPresent();
    }

    public List<LotEntry> currentLotStatus() {
        return lotEntryRepository.findAllByDateToIsNull();
    }

}
