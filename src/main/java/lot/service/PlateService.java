package lot.service;

import lot.model.Plate;
import lot.repository.PlateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import plate.PlateValidation;

import java.util.Optional;

@Service
public class PlateService {

    private PlateRepository plateRepository;

    @Autowired
    public PlateService(PlateRepository plateRepository) {
        this.plateRepository = plateRepository;
    }

    public Optional<Plate> findMatchingPlate(PlateValidation.Plate plate) {
        return null;
    }


}
