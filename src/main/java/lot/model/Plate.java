package lot.model;


import javax.persistence.*;
import java.util.List;

@Entity
public class Plate {

    @Id
    private Long id;
    private String plate;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "country_id")
    private Country country;
    private Credit credit; //todo credits mapping to plate or user ?
    @OneToMany(mappedBy = "plate")
    private List<Payment> payments;
    @ManyToOne
    private User user;
    @OneToMany(mappedBy = "plate")
    private List<LotEntry> lotEntries;
    private Boolean isActive;


}
