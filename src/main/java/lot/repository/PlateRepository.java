package lot.repository;


import lot.model.Plate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PlateRepository extends JpaRepository<Plate, Long> {
    Optional<Plate> findOneByPlate(String plate);

    Optional<Plate> findFirstByPlateIn(List<String> plates);
    List<Plate> findAllByActiveIs(Boolean active);
}
