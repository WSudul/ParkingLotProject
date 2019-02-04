package lot.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.List;

@Entity
public class Plate {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(unique = true)
    private String plate;
    @JsonManagedReference
    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "country_id")
    private Country country;
    @JsonManagedReference
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "user_id")
    private User user;
    @JsonBackReference
    @OneToMany(mappedBy = "plate", fetch = FetchType.LAZY)
    private List<LotEntry> lotEntries;
    private Boolean active;

    public Plate(String plate, Country country, Boolean active) {
        this.plate = plate;
        this.country = country;
        this.active = active;
    }

    public Plate() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPlate() {
        return plate;
    }

    public void setPlate(String plate) {
        this.plate = plate;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<LotEntry> getLotEntries() {
        return lotEntries;
    }

    public void setLotEntries(List<LotEntry> lotEntries) {
        this.lotEntries = lotEntries;
    }

    public Boolean isActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return "Plate{" +
                "plate='" + plate + '\'' +
                ", active=" + active +
                '}';
    }
}
