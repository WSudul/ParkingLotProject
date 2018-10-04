package lot.service;

import lot.model.*;
import lot.repository.PlateRepository;
import lot.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

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

        if (userRepository.findOneByLogin(userRegistrationData.getEmail()).isPresent()) {
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

        user.setPlates(new HashSet<>());

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

    public Optional<User> findUser(String name) {
        return userRepository.findOneByLogin(name);
    }

    public boolean updateUser(User user, UpdateUserData updateUserData) {
        //todo implement nickname change and proper value return
        Set<String> existingUserPlates = user.getPlates().stream().map(p -> p.getPlate()).collect(Collectors.toSet());

        for (String plate : updateUserData.getAddPlates()) {
            if (existingUserPlates.contains(plate)) {
                reactivatePlate(plate);
            } else {
                createNewPlate(user, plate);
            }

        }

        for (String plate : updateUserData.getRemovePlates()) {
            if (existingUserPlates.contains(plate)) {
                deactivatePlate(plate);
            }

        }


        return true;
    }

    private void createNewPlate(User user, String plateText) {
        Plate plate = new Plate();
        plate.setActive(true);
        plate.setPlate(plateText);
        plate.setUser(user);
        user.getPlates().add(plate);

        plateRepository.save(plate);

    }

    private void deactivatePlate(String plate) {
        changePlateStatus(plate, false);
    }

    private void reactivatePlate(String plate) {
        changePlateStatus(plate, true);
    }

    private void changePlateStatus(String plate, boolean status) {
        Optional<Plate> existingPlate = plateRepository.findOneByPlate(plate);
        existingPlate.get().setActive(status);

        plateRepository.save(existingPlate.get());
    }




}
