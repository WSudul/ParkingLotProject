package lot.repository;


import lot.model.LotEntry;
import lot.model.Plate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface LotEntryRepository extends JpaRepository<LotEntry, Long> {

    Optional<LotEntry> findOneByPlateAndDateToIsNull(Plate plate);


    Set<LotEntry> findAllByDateToIsNull();

    Set<LotEntry> findAllByPlate_Plate(String plate);


}
