package lot.model;


import javax.persistence.*;
import java.util.List;

@Entity
public class Plate {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String plate;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "country_id")
    private Country country;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "user_id")
    private User user;
    @OneToMany(mappedBy = "plate", fetch = FetchType.LAZY)
    private List<LotEntry> lotEntries;
    private Boolean isActive;

    public Plate(String plate, Country country, Boolean isActive) {
        this.plate = plate;
        this.country = country;
        this.isActive = isActive;
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

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }
}
