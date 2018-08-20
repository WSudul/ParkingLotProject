package lot.service;


import lot.model.Plate;
import lot.model.User;
import lot.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    private final UserRepository userRepository;

    @Autowired
    public NotificationService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * @param plate licence plate
     */
    void notifyPlateOwner(Plate plate, String notification) {
        User user = userRepository.findOneByPlatesContaining(plate);

        //todo add Firebase Cloud Messaging dependency and use it here (for Android) and/or other service that can
        // handle IOS

    }

}