package lot.service;

import lot.model.Credit;
import lot.model.Plate;
import lot.model.User;
import lot.model.UserRegistrationData;
import lot.repository.PlateRepository;
import lot.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PlateRepository plateRepository;

    @Autowired
    public UserService(UserRepository userRepository, PlateRepository plateRepository) {
        this.userRepository = userRepository;
        this.plateRepository = plateRepository;
    }

    public User registerNewUserAccount(UserRegistrationData userRegistrationData) {

        if (userRepository.findOneByEmail(userRegistrationData.getEmail()).isPresent()) {
            //log error
            return null;
        }

        String plate = userRegistrationData.getPlateText();
        if (null != plate && plateRepository.findOneByPlate(plate).isPresent()) {
            //log error - existing plate
        }

        User user = buildNewUser(userRegistrationData);
        return userRepository.save(user);


    }

    private User buildNewUser(UserRegistrationData userRegistrationData) {
        User user = new User();
        user.setLogin(userRegistrationData.getEmail());
        user.setNickname(userRegistrationData.getNickname());
        //todo password handling

        user.setPlates(new ArrayList<Plate>());

        Plate plate = new Plate();
        plate.setPlate(userRegistrationData.getPlateText());
        plate.setActive(true);
        plate.setUser(user);
        user.getPlates().add(plate);

        Credit credit = new Credit();
        credit.setUser(user);
        credit.setValue(0L);
        user.setCredit(credit);

        return user;


    }

}
