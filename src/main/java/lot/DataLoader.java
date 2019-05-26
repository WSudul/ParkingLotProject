package lot;

import lot.model.*;
import lot.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class DataLoader implements ApplicationRunner {

    private UserRepository userRepository;
    private CountryRepository countryRepository;
    private PlateRepository plateRepository;
    private LotRepository lotRepository;
    private LotEntryRepository lotEntryRepository;

    @Autowired
    public DataLoader(UserRepository userRepository, CountryRepository countryRepository, PlateRepository
            plateRepository, LotRepository lotRepository, LotEntryRepository lotEntryRepository) {
        this.userRepository = userRepository;
        this.countryRepository = countryRepository;
        this.plateRepository = plateRepository;
        this.lotRepository = lotRepository;
        this.lotEntryRepository = lotEntryRepository;
    }

    public void run(ApplicationArguments args) {
        Lot lot_1 = new Lot();
        lot_1.setName("Krak-1");
        lot_1.setLocation("Krakow Pawia 15");
        lot_1.setCapacity(50);
        LotStatus lot_status_1 = new LotStatus();
        lot_status_1.setOccupied(0);
        lot_status_1.setLastUpdate(OffsetDateTime.now());
        lot_1.setLotStatus(lot_status_1);
        lot_1 = lotRepository.save(lot_1);

        Lot lot_2 = new Lot();
        lot_2.setName("Krak-2");
        lot_2.setLocation("Krakow Warszawska 1");
        lot_2.setCapacity(100);
        LotStatus lot_status_2 = new LotStatus();
        lot_status_2.setOccupied(30);
        lot_status_2.setLastUpdate(OffsetDateTime.now());
        lot_2.setLotStatus(lot_status_2);
        lot_2 = lotRepository.save(lot_2);

        Country country_1 = new Country("POL");
        Country country_2 = new Country("GER");

        countryRepository.save(country_1);
        countryRepository.save(country_2);

        List<Plate> plates = new ArrayList<>(List.of(
                new Plate("KR12345", country_1, true),
                new Plate("KR45678", country_1, true),
                new Plate("KR9ABC", country_1, true),
                new Plate("KR4CG7", country_1, true),
                new Plate("KR48CY", country_1, true),
                new Plate("KR4CG9", country_1, true),
                new Plate("KR447KAL", country_1, true)));

        System.out.println("--------------------\n\n\n------------------------");
        for (Plate plate : plates)
            plate = plateRepository.save(plate);

        User user = new User();

        String[] plateTexts = new String[]{"Custom-123", "WAW123", "WAW456", "WAW789", "WAWABC", "WAWDEF", "WAWGHI"};
        for (String plateText : plateTexts) {
            Plate userPlate = new Plate();
            userPlate.setPlate(plateText);
            userPlate.setActive(true);
            userPlate.setUser(user);
            user.getPlates().add(userPlate);
        }
        user.setNickname("user1");
        user.setLogin("log@log.com");

        user = userRepository.saveAndFlush(user);

        List<Plate> user1Plates = user.getPlates();

        User user2 = new User();
        Plate plate_1 = new Plate("KR-AL12", country_1, true);
        Plate plate_2 = new Plate("ZU-5678", country_1, true);
        plate_1.setUser(user2);
        plate_2.setUser(user2);

        user2.getPlates().add(plate_1);
        user2.getPlates().add(plate_2);

        plates.get(0).setUser(user2);
        plates.get(1).setUser(user2);

        user2.setNickname("user2");
        user2.setLogin("log@log.com");
        user2 = userRepository.saveAndFlush(user2);

        List<Plate> user2Plates = user2.getPlates();

        for (Lot lot : new Lot[]{lot_1, lot_2})
            for (Plate plate : user1Plates) {
                Long[] offsets = new Long[]{1L, 2L, 4L, 5L, 6L, 7L, 8L, 9L};
                for (long offset : offsets) {
                    LotEntry lotEntry = new LotEntry();
                    lotEntry.setDateFrom(OffsetDateTime.now().plusSeconds(offset));
                    lotEntry.setDateTo(OffsetDateTime.now().plusHours(offset));
                    lotEntry.setPayment(null);
                    lotEntry.setPlate(plate);
                    lotEntry.setLot(lot);
                    lotEntryRepository.saveAndFlush(lotEntry);
                }
            }

        for (Plate plate : user2Plates) {
            LotEntry lotEntry = new LotEntry();
            lotEntry.setDateFrom(OffsetDateTime.now());
            lotEntry.setDateTo(null);
            lotEntry.setPayment(null);
            lotEntry.setPlate(plate);
            lotEntry.setLot(lot_1);
            lotEntryRepository.saveAndFlush(lotEntry);

        }



        System.out.println(plateRepository.findAll());
        System.out.println(lotRepository.findAll());
    }


}