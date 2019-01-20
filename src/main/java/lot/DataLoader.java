package lot;

import lot.model.Country;
import lot.model.Lot;
import lot.model.LotStatus;
import lot.model.Plate;
import lot.repository.CountryRepository;
import lot.repository.LotRepository;
import lot.repository.PlateRepository;
import lot.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Component
public class DataLoader implements ApplicationRunner {

    private UserRepository userRepository;
    private CountryRepository countryRepository;
    private PlateRepository plateRepository;
    private LotRepository lotRepository;

    @Autowired
    public DataLoader(UserRepository userRepository, CountryRepository countryRepository, PlateRepository
            plateRepository, LotRepository lotRepository) {
        this.userRepository = userRepository;
        this.countryRepository = countryRepository;
        this.plateRepository = plateRepository;
        this.lotRepository = lotRepository;
    }

    public void run(ApplicationArguments args) {
        Lot lot_1 = new Lot();
        lot_1.setName("Krakow_Pawia_15");
        lot_1.setLocation("Krakow");
        lot_1.setCapacity(50);
        LotStatus lot_status_1 = new LotStatus();
        lot_status_1.setOccupied(0);
        lot_status_1.setLot(lot_1);
        lot_status_1.setLastUpdate(Instant.now());
        lot_1.setLotStatus(lot_status_1);
        lotRepository.save(lot_1);

        Country country_1 = new Country("POL");
        Country country_2 = new Country("GER");

        countryRepository.save(country_1);
        countryRepository.save(country_2);

        List<Plate> plates = new ArrayList<>(List.of(
                new Plate("KRA-12345", country_1, true),
                new Plate("KRA-123451", country_1, true),
                new Plate("KRA-123452", country_1, true),
                new Plate("KRA-123453", country_1, true),
                new Plate("KRA-123454", country_1, true),
                new Plate("KRA-123455", country_1, true),
                new Plate("KRA-123456", country_1, true)));

        for (Plate plate : plates)
            plateRepository.save(plate);

    }
}