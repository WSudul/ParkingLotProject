package lot.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class UserRegistrationData {

    @NotBlank
    private String email;
    private String nickname;
    @Size(min = 1)
    private String plateText;
    @NotBlank
    private String password;

    public UserRegistrationData(@NotBlank String email, String nickname, String plateText, @NotBlank String password) {
        this.email = email;
        this.nickname = nickname;
        this.plateText = plateText;
        this.password = password;
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

    public String getPlateText() {
        return plateText;
    }

    public void setPlateText(String plateText) {
        this.plateText = plateText;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
