package lot.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Credit {

    @Id
    private Long id;
    private Long value; //todo verify if BigDecimal is better
    @OneToOne
    private User user;

}
