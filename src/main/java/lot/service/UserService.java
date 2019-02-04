package lot.service;

import lot.model.*;
import lot.repository.PlateRepository;
import lot.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
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
        User user = buildNewUser(userRegistrationData);
        user.setPlates(new ArrayList<>());


        List<String> plates = userRegistrationData.getPlates();

        //prune the list
        plates.removeAll(Arrays.asList("", null));
        for (String plateText : plates) {

            Optional<Plate> existingPlate = plateRepository.findOneByPlate(plateText);
            if (existingPlate.isPresent()) {
                if (null != existingPlate.get().getUser()) {
                    //plate already registered to user!
                    continue;
                } else {
                    System.out.println("adding plate to user" + existingPlate.get().getPlate());
                    user.getPlates().add(existingPlate.get());
                    existingPlate.get().setUser(user);
                }

            } else {
                Plate plate = new Plate();
                plate.setPlate(plateText);
                plate.setActive(true);
                plate.setUser(user);
                user.getPlates().add(plate);
                System.out.println("adding plate " + plateText);
            }


            //log error - existing plate
        }

        System.out.println("save user " + user);
        return userRepository.saveAndFlush(user);


    }

    private User buildNewUser(UserRegistrationData userRegistrationData) {
        User user = new User();
        user.setLogin(userRegistrationData.getEmail());
        user.setNickname(userRegistrationData.getNickname());
        //todo password handling



        Credit credit = new Credit();
        credit.setValue(0L);
        user.setCredit(credit);

        return user;


    }

    public Optional<User> findUser(String name) {
        return userRepository.findOneByLogin(name);
    }

    public Optional<User> findUserById(Long id) {
        return userRepository.findById(id);
    }

    public List<User> findAllUsers() {
        return userRepository.findAll();
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
