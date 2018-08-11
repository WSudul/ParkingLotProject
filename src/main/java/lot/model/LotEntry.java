package lot.model;

import javax.persistence.*;
import java.time.OffsetDateTime;

@Entity
public class LotEntry {

    @Id
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "plate_id")
    private Plate plate;
    private OffsetDateTime dateFrom;
    private OffsetDateTime dateTo;
    private Boolean isPaid;


}
