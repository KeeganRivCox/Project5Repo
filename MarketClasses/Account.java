import java.io.*;
import java.util.Scanner;

public class Account implements Serializable {
    private String name;
    private String email;
    private String password;
    private String username;
    private String role;

    //Constructor for creating Account objects with name, email, and username
    public Account(String name, String email, String password, String username, String role) {
        this.name = name;
        this.email = email;
        this.username = username;
        this.role = role;
        this.password = password;
    }

    // empty Account constructor
    public Account(Account account) {
        this.name = account.name;
        this.email = account.email;
        this.password = account.password;
        this.username = account.username;
        this.role = account.role;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setRole(String role) {
        this.role = role;
    }

    //

    public String getRole() {
        return role;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

}

