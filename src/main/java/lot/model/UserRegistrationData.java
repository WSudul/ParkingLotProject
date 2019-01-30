package lot.model;

import javax.validation.constraints.NotBlank;
import java.util.List;

public class UserRegistrationData {

    @NotBlank
    private String email;
    private String nickname;
    private List<String> plates;

    public UserRegistrationData(@NotBlank String email, String nickname, List<String> plates, @NotBlank String
            password) {
        this.email = email;
        this.nickname = nickname;
        this.plates = plates;
    }

    public UserRegistrationData() {
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public List<String> getPlates() {
        return plates;
    }

    public void setPlates(List<String> plates) {
        this.plates = plates;
    }

    @Override
    public String toString() {
        return "UserRegistrationData{" +
                "email='" + email + '\'' +
                ", nickname='" + nickname + '\'' +
                ", plates=" + plates +
                '}';
    }
}
