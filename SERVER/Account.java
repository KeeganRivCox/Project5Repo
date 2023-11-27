import java.io.Serial;
import java.io.Serializable;

public class Account implements Serializable {

    private String username;
    private String email;
    private String password;
    private String role;

    public Account(String username, String email, String password, String role) {

        this.username = username;
        this.email = email;
        this.password = password;
        this.role = role;

    }

    public String getEmail() {
        return email;
    }
}
