package lot.service;

import lot.model.Plate;
import lot.repository.PlateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlateService {

    private PlateRepository plateRepository;

    @Autowired
    public PlateService(PlateRepository plateRepository) {
        this.plateRepository = plateRepository;
    }

    public Optional<Plate> findMatchingPlate(String plateText) {
        return plateRepository.findOneByPlate(plateText);
    }

    public boolean addNewPlate(String plateText) {
        Optional<Plate> plate = plateRepository.findOneByPlate(plateText);
        if (plate.isPresent()) {
            return false;
        } else {
            Plate newPlate = new Plate();
            newPlate.setPlate(plateText);
            newPlate.setActive(true);
            plateRepository.saveAndFlush(plate.get());
            return true;
        }
    }

    public boolean deactivatePlate(String plateText) {
        Optional<Plate> plate = plateRepository.findOneByPlate(plateText);
        if (plate.isPresent()) {
            plate.get().setActive(false);
            plateRepository.saveAndFlush(plate.get());
            return true;
        } else
            return false;


    }


    public List<Plate> findAll(boolean onlyActive) {
        if (onlyActive)
            return plateRepository.findAllByActiveIs(onlyActive);
        else
            return plateRepository.findAll();
    }

    public Optional<Plate> findMatchingPlate(List<String> plates) {
        return plateRepository.findFirstByPlateIn(plates);

    }
}
