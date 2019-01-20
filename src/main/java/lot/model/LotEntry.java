package lot.model;

import javax.persistence.*;
import java.time.OffsetDateTime;

@Entity
public class LotEntry {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "plate_id")
    private Plate plate;
    private OffsetDateTime dateFrom;
    private OffsetDateTime dateTo;
    @OneToOne(mappedBy = "lotEntry", fetch = FetchType.LAZY)
    private Payment payment;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lot_id")
    private Lot lot;

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

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }


    public Lot getLot() {
        return lot;
    }

    public void setLot(Lot lot) {
        this.lot = lot;
    }
}
