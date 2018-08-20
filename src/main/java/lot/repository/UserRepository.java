package lot.repository;


import lot.model.Plate;
import lot.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findOneByPlatesContaining(Plate plate);

}
