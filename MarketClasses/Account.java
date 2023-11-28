import java.io.*;
import java.util.Scanner;

public class Account implements Serializable {
    private static final int MAX_SIGN_IN_ATTEMPTS = 3;
    private String name;
    protected String email;
    private String username;
    private String role;

    //Constructor for creating Account objects with name, email, and username
    public Account(String name, String email, String username, String role) {
        this.name = name;
        this.email = email;
        this.username = username;
        this.role = role;
    }

    // empty Account constructor
    public Account(Account account) {
        this.name = account.name;
        this.email = account.email;
        this.username = account.username;
        this.role = account.role;
    }

    public String getRole() {
        return role;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }

}

