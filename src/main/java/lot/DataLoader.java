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
import java.util.Random;

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
        lot_1.setName("Parking-Krk-1");
        lot_1.setLocation("Krakow, Pawia 15");
        lot_1.setCapacity(50);
        LotStatus lot_status_1 = new LotStatus();
        lot_status_1.setOccupied(3);
        lot_status_1.setCapacity(50);
        lot_status_1.setLastUpdate(OffsetDateTime.now());
        lot_1.setLotStatus(lot_status_1);
        lot_1 = lotRepository.save(lot_1);

        Lot lot_2 = new Lot();
        lot_2.setName("Parking-Krk-2");
        lot_2.setLocation("Krakow, Warszawska 1");
        lot_2.setCapacity(70);
        LotStatus lot_status_2 = new LotStatus();
        lot_status_2.setOccupied(1);
        lot_status_2.setCapacity(70);
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
                new Plate("KR447KA", country_1, true)));

        System.out.println("--------------------\n\n\n------------------------");
        for (Plate plate : plates)
            plate = plateRepository.save(plate);

        User user = new User();

        String[] plateTexts = new String[]{"LU6449U", "KBC35688", "KR3X629", "KR8N861", "WW42ABC", "WZ455F", "WI027H"};
        for (String plateText : plateTexts) {
            Plate userPlate = new Plate();
            userPlate.setPlate(plateText);
            userPlate.setActive(true);
            userPlate.setUser(user);
            user.getPlates().add(userPlate);
        }
        user.setNickname("Trans Company");
        user.setLogin("Company@TransComp.com");

        user = userRepository.saveAndFlush(user);

        List<Plate> user1Plates = user.getPlates();

        User user2 = new User();
        Plate plate_1 = new Plate("KRAL12", country_1, true);
        Plate plate_2 = new Plate("ZU5678", country_1, true);
        Plate plate_3 = new Plate("BER123BF", country_2, true);
        plate_1.setUser(user2);
        plate_2.setUser(user2);
        plate_3.setUser(user2);

        user2.getPlates().add(plate_1);
        user2.getPlates().add(plate_2);
        user2.getPlates().add(plate_3);

        plates.get(0).setUser(user2);
        plates.get(1).setUser(user2);


        user2.setNickname("Company Lorem Ipsum");
        user2.setLogin("contact@loremipsum.com");
        user2 = userRepository.saveAndFlush(user2);

        List<Plate> user2Plates = user2.getPlates();


        int lot_counter = 0;
        Random generator = new Random();
        for (Lot lot : new Lot[]{lot_1, lot_2}) {
            for (Plate plate : user1Plates) {
                Long[] offsets = new Long[]{1L, 2L, 4L, 5L, 6L, 7L, 8L, 9L};
                for (long offset : offsets) {
                    LotEntry lotEntry = new LotEntry();
                    OffsetDateTime now = OffsetDateTime.now();
                    OffsetDateTime from = now.minusDays(offset).minusWeeks(lot_counter)
                            .minusMinutes(generator.nextInt(30));
                    OffsetDateTime to = from.plusHours(generator.nextInt(6)).plusMinutes(generator.nextInt(30));
                    lotEntry.setDateFrom(from);
                    lotEntry.setDateTo(to);
                    lotEntry.setPayment(null);
                    lotEntry.setPlate(plate);
                    lotEntry.setLot(lot);
                    lotEntryRepository.saveAndFlush(lotEntry);
                }
            }
            lot_counter++;
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

        User private_user = new User();
        Plate private_plate1 = new Plate("KR4E999", country_1, true);
        Plate private_plate2 = new Plate("KR2ABC1", country_1, true);
        private_plate1.setUser(private_user);
        private_plate2.setUser(private_user);
        private_plate1.setActive(true);
        private_plate2.setActive(true);


        private_user.getPlates().add(private_plate1);
        private_user.getPlates().add(private_plate2);


        private_user.setNickname("Kowalski");
        private_user.setLogin("kowalski@test.com");

        user = userRepository.saveAndFlush(private_user);


        LotEntry lotEntry = new LotEntry();
        lotEntry.setDateFrom(OffsetDateTime.now());
        lotEntry.setDateTo(null);
        lotEntry.setPayment(null);
        lotEntry.setPlate(private_plate1);
        lotEntry.setLot(lot_2);
        lotEntryRepository.saveAndFlush(lotEntry);



        System.out.println(plateRepository.findAll());
        System.out.println(lotRepository.findAll());
    }


}