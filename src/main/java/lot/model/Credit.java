package lot.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Credit {

    @Id
    private Long id;
    private Long value; //todo verify if BigDecimal is better

}
