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


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Plate getPlate() {
        return plate;
    }

    public void setPlate(Plate plate) {
        this.plate = plate;
    }

    public OffsetDateTime getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(OffsetDateTime dateFrom) {
        this.dateFrom = dateFrom;
    }

    public OffsetDateTime getDateTo() {
        return dateTo;
    }

    public void setDateTo(OffsetDateTime dateTo) {
        this.dateTo = dateTo;
    }

    public Boolean getPaid() {
        return isPaid;
    }

    public void setPaid(Boolean paid) {
        isPaid = paid;
    }
}
