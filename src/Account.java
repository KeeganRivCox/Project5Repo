import java.io.*;
import java.util.Scanner;

/**
 Project 4 -- Account CLass

 This class controls all the methods associated with making/editing an Account and
 is crucial for the function of the Seller and Customer Classes

 @author Adeetya, Lab Section 39

 @version November 13th, 2023


 */

public class Account implements Serializable {
    private static final String USER_DATA_FILE = "user_data.txt";
    private static final int MAX_SIGN_IN_ATTEMPTS = 3;
    private String name;
    protected String email;
    private String username;
    private boolean authenticatedSeller;
    private boolean authenticatedCustomer;

    //Constructor for creating Account objects with name, email, and username
    public Account(String name, String email, String username) {
        this.name = name;
        this.email = email;
        this.username = username;
        this.authenticatedSeller = false;
        this.authenticatedCustomer = false;
    }

    // empty Account constructor
    public Account(Account account) {
        this.name = account.name;
        this.email = account.email;
        this.username = account.username;
        this.authenticatedSeller = false;
        this.authenticatedCustomer = false;
    }

    // returns the seller authentication status
    public boolean isAuthenticatedSeller() {
        return authenticatedSeller;
    }

    // returns the customer authentication status
    public boolean isAuthenticatedCustomer() {
        return authenticatedCustomer;
    }

    // a method creating a new Account with new name, username, email, and password
    public static void createAccount(Scanner scanner) {

        // check is name is valid
        String name;

        while (true) {

            System.out.println("Enter your name: ");
            name = scanner.nextLine();
            //line to get rid of spaces before and after name;
            name.trim();

            if (name.matches(".*[a-zA-Z]+.*")) {

                break;

            } else {

                System.out.println("Name cannot be empty. Please try again...");

            }

        }

        // checks if username is valid
        String username;

        while (true) {

            System.out.println("Enter your username: ");
            username = scanner.nextLine();

            if (username.matches(".*[a-zA-Z]+.*")) {

                break;

            } else {

                System.out.println("Username cannot be empty. Please try again...");

            }

        }

        // checks if email is valid and in the correct format
        String email;
        while (true) {
            System.out.println("Enter your email: ");
            email = scanner.nextLine();
            email = email.trim();
            email = email.replaceAll(" ", "_");

            if (isValidEmail(email)) {
                break;
            } else {
                System.out.println("Email does not meet the format requirements. Please try again...");
            }
        }

        // checks if an account with the same email or username already exists
        if (isDuplicateEmailOrUsername(username, email)) {
            System.out.println("An account with the same email or username already exists.");
            System.out.println("Please try the 'Sign In' feature or use the 'Forgot Password' feature.");
            return;
        }

        // checks if password is valid and in the right format
        String password;
        while (true) {
            System.out.println("Enter your password: (Min. 8-Characters, 1 Special, 1 Numerical)");
            password = scanner.nextLine();

            if (isValidPassword(password)) {
                break;
            } else {
                System.out.println("Password does not meet the requirements. Please try again...");
            }
        }

        // verifies whether a user has successfully made an Account
        String role = selectRole(scanner);
        Account account = new Account(name, email, username);

        if (writeAccountData(account, password, role)) {
            System.out.println("Account created successfully.");
        } else {
            System.out.println("Failed to create the account.");
        }
    }

    // a method asking the user to verify their account exists and if they want to delete it
    public static void deleteAccount(Scanner scanner) {
        System.out.println("Enter your username: ");
        String username = scanner.nextLine();
        System.out.println("Enter your email: ");
        String email = scanner.nextLine();

        // Asks for the password to confirm deletion
        System.out.println("Enter your password to confirm deletion: ");
        String password = scanner.nextLine();

        // Check if the password correctly matches the User's password
        if (!isValidPasswordForDeletion(username, email, password)) {
            System.out.println("Incorrect password. Account deletion failed.");
            return;
        }

        String accountRole = retrieveAccountRole(email);
        System.out.println(accountRole);

        // Confirmation of account deletion
        System.out.println("Are you sure you want to delete your account? (y/n): ");
        String confirmation = scanner.nextLine();
        if (confirmation.equalsIgnoreCase("y")) {
            if (confirmAccountDeletion(username, email)) {
                System.out.println("Account deleted successfully.");
                if (accountRole.equals("Seller")) {
                    Seller.removeSeller(email);
                } else if (accountRole.equals("Customer")) {
                    Customer.removeCustomer(email);
                }
            } else {
                System.out.println("Failed to delete the account. Invalid username or email.");
            }
        } else {
            System.out.println("Account deletion canceled.");
        }
    }

    // a method returning the current User's account role from the USER_DATA_FILE if it exists
    private static String retrieveAccountRole(String email) {

        try (BufferedReader br = new BufferedReader(new FileReader(USER_DATA_FILE))) {

            String line;

            while ((line = br.readLine()) != null) {

                String[] accountData = line.split(",");

                if (accountData[1].equals(email)) {

                    return accountData[4];

                }

            }

        } catch (IOException e) {

            System.out.println("Error reading user data.");
            return "";

        }

        return "";

    }

    // a method checking if the provided password matches the current User password to access Account deletion
    private static boolean isValidPasswordForDeletion(String username, String email, String password) {
        try (BufferedReader reader = new BufferedReader(new FileReader(USER_DATA_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length == 5) {
                    String storedUsername = data[2];
                    String storedEmail = data[1];
                    String storedPassword = data[3];
                    if (storedUsername.equals(username) && storedEmail.equals(email)) {
                        return storedPassword.equals(password); // Password is correct for the specific account
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    //method that takes in the users username and email to confirm their account deletion

    // a method checking if the current User's data exists, so it can be deleted
    // returns whether an Account has been confirmed to be deleted and updates the USER_DATA file
    public static boolean confirmAccountDeletion(String username, String email) {
        try (BufferedReader reader = new BufferedReader(new FileReader(USER_DATA_FILE))) {
            StringBuilder updatedData = new StringBuilder();
            String line;
            boolean userDeleted = false;

            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length == 5) {
                    String storedUsername = data[2];
                    String storedEmail = data[1];

                    if (storedUsername.equals(username) && storedEmail.equals(email)) {
                        userDeleted = true;
                    } else {
                        updatedData.append(line).append("\n");
                    }
                }
            }

            if (userDeleted) {
                try (BufferedWriter writer = new BufferedWriter(new FileWriter(USER_DATA_FILE, false))) {
                    writer.write(updatedData.toString());
                }
                return true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }




    // a method writing the current User's Account information to the USER_DATA file

    static boolean writeAccountData(Account account, String password, String role) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(USER_DATA_FILE, true))) {
            writer.write(account.name + "," + account.email + "," + account.username + "," + password + "," + role + "\n");
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }




    // a method checking if the User's provided email or password  already exists in the USER_DATA file

    private static boolean isDuplicateEmailOrUsername(String username, String email) {
        try (BufferedReader reader = new BufferedReader(new FileReader(USER_DATA_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length == 5) {
                    String storedUsername = data[2];
                    String storedEmail = data[1];
                    // Check if the provided username or email already exists
                    if (storedUsername.equals(username) || storedEmail.equals(email)) {
                        return true; // Duplicate email or username found
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }



    //method that checks to see if the password the user entered follows the password format


    private static boolean isValidPassword(String password) {
        if (password.length() < 8) {
            System.out.println("Password length must be at least 8 characters.");
            return false;
        }

        boolean hasNumber = false;
        boolean hasSpecialCharacter = false;

        for (char c : password.toCharArray()) {
            if (Character.isDigit(c)) {
                hasNumber = true;
            } else if (isSpecialCharacter(c)) {
                hasSpecialCharacter = true;
            }
        }

        if (!hasNumber) {
            System.out.println("Password must contain at least 1 numerical character.");
        }

        if (!hasSpecialCharacter) {
            System.out.println("Password must contain at least 1 special character.");
        }

        return hasNumber && hasSpecialCharacter;
    }


    // a method checking if the email is in the correct format of an email (includes @ and . )
    private static boolean isValidEmail(String email) {

        return email.contains("@") && email.contains(".");

    }



    // a method checking if a certain character is considered a special character

    private static boolean isSpecialCharacter(char c) {
        return "!@#$%^&*()-+=_".indexOf(c) != -1;
    }


    // signInLoop returns an Account object to instantiate an Account object in Market
    // a method allowing a user a given amount of attempts to sign in
    public static Account signInLoop(Scanner scanner) {
        for (int attempt = 1; attempt <= MAX_SIGN_IN_ATTEMPTS; attempt++) {
            System.out.println("Sign-In Attempt " + attempt);
            System.out.println("Enter your email: ");
            String email = scanner.nextLine();
            System.out.println("Enter your password: ");
            String password = scanner.nextLine();

            Account user = attemptToSignIn(email, password);
            if (user != null) {
                return user; // Successful sign-in, exit loop, Keegan - and return user account
            }

            if (attempt < MAX_SIGN_IN_ATTEMPTS) {
                System.out.println("Invalid email or password. Try again.");
            } else {
                System.out.println("You've exceeded the maximum sign-in attempts.");
                forgotPassword(scanner);
                return null;
            }
        }
        return null;
    }

    // attemptToSignIn returns an Account object to be sent to signInLoop for checks
    // a method allowing a User to use their information to sign in
    static Account attemptToSignIn(String email, String password) {
        try (BufferedReader reader = new BufferedReader(new FileReader(USER_DATA_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length == 5) {
                    String storedEmail = data[1];
                    String storedPassword = data[3];
                    if (storedEmail.equals(email) && storedPassword.equals(password)) {
                        Account account = new Account(data[0], data[1], data[2]);
                        if (data[4].equals("Seller")) {
                            account.authenticatedSeller = true;
                        } else if (data[4].equals("Customer")) {
                            account.authenticatedCustomer = true;
                        }
                        System.out.println("Sign-in successful. Welcome, " + data[2] + "! Your role is: " + data[4]);
                        return account;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    // Keegan - Says I made a change to this method but I cant remember what :(

    //a method that allows a user to change their password if they remember their username and email


    public static void forgotPassword(Scanner scanner) {
        while (true) {
            System.out.println("Forgot your password? (y/n): ");
            String choice = scanner.nextLine();
            if (choice.equalsIgnoreCase("y")) {
                System.out.println("Enter your username: ");
                String username = scanner.nextLine();
                System.out.println("Enter your email: ");
                String email = scanner.nextLine();
                System.out.println("Enter your new password: ");
                String newPassword = scanner.nextLine();

                if (updatePassword(username, email, newPassword)) {
                    System.out.println("Password reset successful. Your new password has been updated.");
                    return; // Go back to the main page
                } else {
                    System.out.println("Password reset failed. Invalid username or email.");
                }

            } else if (choice.equalsIgnoreCase("n")) {
                return;
            } else {
                System.out.println("Invalid Choice! Please choose either yes or no.");
            }
        }
    }





    // a method updating the USER_DATA file if an existing User has updated their password

    static boolean updatePassword(String username, String email, String newPassword) {
        try (BufferedReader reader = new BufferedReader(new FileReader(USER_DATA_FILE))) {
            StringBuilder updatedData = new StringBuilder();
            String line;
            boolean userUpdated = false;

            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length == 5) {
                    String storedUsername = data[2];
                    String storedEmail = data[1];

                    if (storedUsername.equals(username) && storedEmail.equals(email)) {
                        data[3] = newPassword;  // Update the password
                        userUpdated = true;
                    }
                    updatedData.append(String.join(",", data)).append("\n");
                } else {
                    updatedData.append(line).append("\n");
                }
            }

            if (userUpdated) {
                try (BufferedWriter writer = new BufferedWriter(new FileWriter(USER_DATA_FILE, false))) {
                    writer.write(updatedData.toString());
                }
                return true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }




    // a method allowing a User to select their role as either Customer or Seller

    private static String selectRole(Scanner scanner) {

        boolean success = false;
        String userRole = null;

        while (!success) {

            System.out.println("Select your role: ");
            System.out.println("1. Customer");
            System.out.println("2. Seller");

            String choice = scanner.nextLine();

            if (choice.equals("1")) {
                success = true;
                userRole = "Customer";
                //return "Customer";
            } else if (choice.equals("2")) {
                success = true;
                userRole = "Seller";
                //return "Seller";
            } else {
                System.out.println("Invalid role selection. Please try again.");
            }

        }

        return userRole;

    }

    // return the username of the current User
    public String getUsername() {
        return username;
    }

    // returns the email of the current User
    public String getEmail() {
        return this.email;
    }

}
//Updated, yay!
