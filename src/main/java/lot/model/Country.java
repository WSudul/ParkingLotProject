package lot.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.Set;

@Entity
public class Country {

    @Id
    private String isoCountryCode;
    @OneToMany(mappedBy = "country")
    private Set<Plate> plateSet;


}
