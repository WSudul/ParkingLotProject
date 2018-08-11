package lot.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Country {

    @Id
    private String isoCountryCode;
    @OneToMany(mappedBy = "country")
    private Plate plate;


}
