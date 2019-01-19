package lot.repository;


import lot.model.Lot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LotRepository extends JpaRepository<Lot, String> {

    Lot findFirstByLocationNotNull();

    Optional<Lot> findFirstByName(String name);


}
