package lot.service;


import lot.model.Lot;
import lot.model.LotData;
import lot.model.LotStatus;
import lot.repository.LotRepository;
import lot.repository.LotStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class LotService {

    private LotRepository lotRepository;
    private LotStatusRepository lotStatusRepository;

    @Autowired
    public LotService(LotRepository lotRepository, LotStatusRepository lotStatusRepository) {
        this.lotRepository = lotRepository;
        this.lotStatusRepository = lotStatusRepository;
    }

    public boolean addNewLot(LotData lotData) {
        if (lotRepository.findOneByName(lotData.getName()).isPresent())
            return false;
        String name = lotData.getName();
        String location = lotData.getLocation();
        Integer capacity = lotData.getCapacity();
        Integer occupied = lotData.getOccupied();



        Lot newLot = new Lot();
        newLot.setName(name);
        newLot.setLocation(location);
        newLot.setCapacity(capacity);
        LotStatus status = new LotStatus();

        status.setLastUpdate(OffsetDateTime.now());
        status.setCapacity(capacity);
        status.setOccupied(occupied);

        newLot.setLotStatus(status);

        lotRepository.save(newLot);

        return true;
    }

    public Optional<Lot> getLot(String name) {
        return lotRepository.findOneByName(name);
    }

    public List<Lot> getAllLots() {
        return lotRepository.findAll();
    }

    public void incrementOccupiedCount(Lot lot) {
        LotStatus lotStatus = lot.getLotStatus();
        Integer currentCount = lotStatus.getOccupied();
        lotStatus.setOccupied(currentCount + 1);
        lotStatusRepository.save(lotStatus);
    }

    public void decrementOccupiedLod(Lot lot) {
        LotStatus lotStatus = lot.getLotStatus();
        Integer currentCount = lotStatus.getOccupied();
        if (currentCount > 0) {
            lotStatus.setOccupied(currentCount - 1);
            lotStatusRepository.save(lotStatus);
        }
    }


}
