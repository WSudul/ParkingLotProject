package lot.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.List;

@Entity
public class User {

    @Id
    private Long id;
    private String login;
    @OneToMany(mappedBy = "user")
    private List<Plate> plates;
    @OneToOne(mappedBy = "user")
    private Credit credit; //todo credits mapping to plate or user ?

}
