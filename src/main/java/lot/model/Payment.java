package lot.model;


import javax.persistence.*;

@Entity
public class Payment {

    @Id
    private Long id;
    private Integer value;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lotentry_id")
    private LotEntry lotEntry;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public LotEntry getLotEntry() {
        return lotEntry;
    }

    public void setLotEntry(LotEntry lotEntry) {
        this.lotEntry = lotEntry;
    }




}
