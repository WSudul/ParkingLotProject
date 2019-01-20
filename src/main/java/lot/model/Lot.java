package lot.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Lot {

    @Id
    private Long id;
    @Column(unique = true)
    private String name;
    private String location;
    private Integer capacity;
    @OneToMany(mappedBy = "lot", fetch = FetchType.LAZY)
    private List<LotEntry> lotEntries;
    @OneToOne(mappedBy = "lot", fetch = FetchType.LAZY)
    private LotStatus lotStatus;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<LotEntry> getLotEntries() {
        return lotEntries;
    }

    public void setLotEntries(List<LotEntry> lotEntries) {
        this.lotEntries = lotEntries;
    }


    public LotStatus getLotStatus() {
        return lotStatus;
    }

    public void setLotStatus(LotStatus lotStatus) {
        this.lotStatus = lotStatus;
    }

}
