package lot.model;


import javax.persistence.*;

@Entity
public class Payment {

    @Id
    private Long id;
    private Integer value;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "plate_id")
    private Plate plate;
}
