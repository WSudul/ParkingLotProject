package lot.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Country {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(unique = true)
    private String isoCountryCode;
    @JsonBackReference
    @OneToMany(mappedBy = "country", fetch = FetchType.LAZY)
    private Set<Plate> plateSet;

    public Country(String isoCountryCode) {
        this.isoCountryCode = isoCountryCode;
    }

    public Country() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
