package lot.model;

import javax.persistence.*;

@Entity
public class Credit {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long value; //todo verify if BigDecimal is better
    @OneToOne
    private User user;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getValue() {
        return value;
    }

    public void setValue(Long value) {
        this.value = value;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
