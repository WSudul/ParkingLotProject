package lot.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.Set;

@Entity
public class Country {

    @Id
    private String isoCountryCode;
    @OneToMany(mappedBy = "country", fetch = FetchType.LAZY)
    private Set<Plate> plateSet;

    public Country(String isoCountryCode) {
        this.isoCountryCode = isoCountryCode;
    }

    public Country() {
    }

    public String getIsoCountryCode() {
        return isoCountryCode;
    }

    public void setIsoCountryCode(String isoCountryCode) {
        this.isoCountryCode = isoCountryCode;
    }

    public Set<Plate> getPlateSet() {
        return plateSet;
    }

    public void setPlateSet(Set<Plate> plateSet) {
        this.plateSet = plateSet;
    }
}
