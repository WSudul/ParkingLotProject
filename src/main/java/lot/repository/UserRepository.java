package lot.repository;


import lot.model.Plate;
import lot.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findOneByPlatesContaining(Plate plate);

    Optional<User> findOneByLogin(String login);
}
