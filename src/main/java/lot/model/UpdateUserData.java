package lot.model;


import javax.validation.constraints.NotEmpty;
import java.util.Set;

public class UpdateUserData {

    @NotEmpty
    private String nickname;
    private Set<String> addPlates;
    private Set<String> removePlates;
    private Boolean active;

    public UpdateUserData() {
    }

    public UpdateUserData(@NotEmpty String nickname, Set<String> addPlates, Set<String> removePlates) {
        this.nickname = nickname;
        this.addPlates = addPlates;
        this.removePlates = removePlates;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Set<String> getAddPlates() {
        return addPlates;
    }

    public void setAddPlates(Set<String> addPlates) {
        this.addPlates = addPlates;
    }

    public Set<String> getRemovePlates() {
        return removePlates;
    }

    public void setRemovePlates(Set<String> removePlates) {
        this.removePlates = removePlates;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}
