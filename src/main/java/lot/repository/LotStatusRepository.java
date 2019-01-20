package lot.repository;


import lot.model.LotStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LotStatusRepository extends JpaRepository<LotStatus, Long> {

}
