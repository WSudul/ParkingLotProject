package lot.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotEmpty
    @NotNull
    private String login;
    private String nickname;
    @JsonBackReference
    @OneToMany(mappedBy = "user", cascade = CascadeType.PERSIST)
    private Set<Plate> plates;
    @JoinColumn(name = "user_id")
    @OneToOne(cascade = CascadeType.PERSIST)
    private Credit credit;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Set<Plate> getPlates() {
        return plates;
    }

    public void setPlates(Set<Plate> plates) {
        this.plates = plates;
    }

    public Credit getCredit() {
        return credit;
    }

    public void setCredit(Credit credit) {
        this.credit = credit;
    }
}
