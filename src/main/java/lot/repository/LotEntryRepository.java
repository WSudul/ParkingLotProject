package lot.repository;


import lot.model.LotEntry;
import lot.model.Plate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LotEntryRepository extends JpaRepository<LotEntry, Long> {

    Optional<LotEntry> findOneByPlateAndDateFromIsNull(Plate plate);

    List<LotEntry> findAllByDateFromIsNull();


}
