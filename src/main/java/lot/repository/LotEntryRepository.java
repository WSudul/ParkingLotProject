package lot.repository;


import lot.model.Lot;
import lot.model.LotEntry;
import lot.model.Plate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface LotEntryRepository extends JpaRepository<LotEntry, Long> {

    Optional<LotEntry> findOneByPlateAndDateToIsNull(Plate plate);

    Optional<LotEntry> findOneByPlateAndLotAndDateToIsNull(Plate plate, Lot lot);
    Set<LotEntry> findAllByDateToIsNull();

    Set<LotEntry> findAllByDateToIsNullAndLotName(String name);

    Set<LotEntry> findAllByPlate_Plate(String plate);

    Set<LotEntry> findAllByLotAndDateToIsNull(Lot lot);

    Set<LotEntry> findAllByLotName(String name);
}
