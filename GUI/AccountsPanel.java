import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.Scanner;

public class AccountsPanel {
    private JFrame frame;
    private JPanel cardPanel;
    private CardLayout cardLayout;
    private JPanel messagePanel;
    private JLabel messageLabel;
    private JTextField emailField;

    private String resetEmail;
    private String resetUsername;
    private JPasswordField passwordField; // Moved the password field to the class level

    // File path for user data
    private static final String USER_DATA_FILE = "user_data.txt";

    private boolean authenticatedCustomer = false;
    private boolean authenticatedSeller = false;

    public AccountsPanel() {
        frame = new JFrame("Boiler Bay Login");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

        cardPanel.add(createSignInPanel(), "SignIn");
        cardPanel.add(createNewAccountPanel(), "NewAccount");
        cardPanel.add(createResetPasswordPanel(), "ResetPassword");
        cardPanel.add(createPasswordPanel(), "PasswordPanel");
        cardPanel.add(createDeleteAccountRequestPanel(), "DeleteAccountRequest");
        cardPanel.add(createDeleteAccountPasswordPanel(), "DeleteAccount");



        // Create message panel
        messagePanel = new JPanel(new BorderLayout());
        messageLabel = new JLabel();
        messageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        messagePanel.add(messageLabel, BorderLayout.CENTER);
        cardPanel.add(messagePanel, "Message");

        frame.add(cardPanel);

        cardLayout.show(cardPanel, "SignIn");
    }

    private JPanel createSignInPanel() {
        frame.setSize(400,500);
        frame.setResizable(false);
        JPanel signInPanel = new JPanel();
        signInPanel.setLayout(new BoxLayout(signInPanel, BoxLayout.Y_AXIS));
        signInPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Increased font size
        Font largeFont = new Font("Arial", Font.PLAIN, 18);

        JLabel welcomeLabel = new JLabel("Welcome to Boiler Bay!");
        welcomeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        welcomeLabel.setFont(largeFont);

        JLabel signInLabel = new JLabel("Sign In:");
        signInLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        signInLabel.setFont(largeFont);

        JLabel emailInputLabel = new JLabel("Email");
        emailInputLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        emailInputLabel.setFont(largeFont);
        emailField = new JTextField();
        emailField.setMaximumSize(new Dimension(300, 30)); // Adjusted dimensions
        emailField.setAlignmentX(Component.CENTER_ALIGNMENT);

        passwordField = new JPasswordField();
        passwordField.setMaximumSize(new Dimension(300, 30)); // Adjusted dimensions
        passwordField.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton forgotPasswordButton = new JButton("Forgot Password");
        forgotPasswordButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        forgotPasswordButton.setFont(largeFont);
        forgotPasswordButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "ResetPassword");
                frame.setSize(400, 200);
            }
        });

        JButton deleteAccountButton = new JButton("Delete Account");
        deleteAccountButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        deleteAccountButton.setFont(largeFont);
        deleteAccountButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "DeleteAccountRequest");
                frame.setSize(400, 200);
            }
        });

        JButton createAccountButton = new JButton("Create a New Account");
        createAccountButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        createAccountButton.setFont(largeFont);

        // Increase button size
        Dimension buttonSize = new Dimension(170, 35);
        createAccountButton.setPreferredSize(buttonSize);
        forgotPasswordButton.setPreferredSize(buttonSize);
        deleteAccountButton.setPreferredSize(buttonSize);

        createAccountButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "NewAccount");
                frame.setSize(500, 300);
                frame.setResizable(false);
            }
        });

        JButton submitEmailButton = new JButton("Submit");
        submitEmailButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        submitEmailButton.setFont(largeFont);
        submitEmailButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                char[] passwordChars = passwordField.getPassword();
                String password = new String(passwordChars);

                try {
                    String userRole = authenticateUser(emailField.getText().trim(), password);

                    if (userRole != null) {
                        // If authenticated, set the appropriate flag based on the role
                        if ("Customer".equalsIgnoreCase(userRole)) {
                            authenticatedCustomer = true;
                        } else if ("Seller".equalsIgnoreCase(userRole)) {
                            authenticatedSeller = true;
                        }

                        showMessage("Authentication successful. Redirecting...", "Success","");
                        // Add logic to redirect to the appropriate panel based on the role
                        // For now, let's redirect to a common panel
                    } else {
                        showMessage("Authentication failed. Please check your email and password.", "Error","");
                        // Clear the password field on authentication failure
                        passwordField.setText("");
                        // Add logic to handle authentication failure (e.g., show an error message)
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                    showMessage("Error authenticating user. Please try again later.", "Error","");
                }
            }
        });


        JLabel needHelpLabel = new JLabel("Need Help?");
        needHelpLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        needHelpLabel.setFont(largeFont);

        JLabel newToBoilerBayLabel = new JLabel("New to Boiler Bay?");
        newToBoilerBayLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        newToBoilerBayLabel.setFont(largeFont);

        signInPanel.add(Box.createVerticalStrut(20));
        signInPanel.add(welcomeLabel);
        signInPanel.add(Box.createVerticalStrut(15));
        signInPanel.add(new JSeparator(SwingConstants.HORIZONTAL));
        signInPanel.add(Box.createVerticalStrut(10));
        signInPanel.add(signInLabel);
        signInPanel.add(Box.createVerticalStrut(15));

        // Add text labels before fields
        signInPanel.add(createFieldWithLabel("       Email", emailField));
        signInPanel.add(Box.createVerticalStrut(10));
        signInPanel.add(createFieldWithLabel("Password", passwordField));
        signInPanel.add(Box.createVerticalStrut(10));
        signInPanel.add(submitEmailButton);
        signInPanel.add(Box.createVerticalStrut(20));
        signInPanel.add(new JSeparator(SwingConstants.HORIZONTAL));
        signInPanel.add(Box.createVerticalStrut(10));
        signInPanel.add(needHelpLabel);
        signInPanel.add(Box.createVerticalStrut(10));
        signInPanel.add(forgotPasswordButton);
        signInPanel.add(deleteAccountButton);
        signInPanel.add(Box.createVerticalStrut(20));
        signInPanel.add(new JSeparator(SwingConstants.HORIZONTAL));
        signInPanel.add(Box.createVerticalStrut(20));
        signInPanel.add(newToBoilerBayLabel);
        signInPanel.add(Box.createVerticalStrut(10));
        signInPanel.add(createAccountButton);
        signInPanel.add(Box.createVerticalStrut(20));

        return signInPanel;
    }

    private JPanel createFieldWithLabel(String labelText, JComponent field) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
        JLabel label = new JLabel(labelText);
        label.setFont(new Font("Arial", Font.PLAIN, 18)); // Set font size
        label.setPreferredSize(new Dimension(100, 20)); // Adjusted label size
        panel.add(label);
        panel.add(field);
        return panel;
    }


    private String authenticateUser(String email, String password) throws IOException {
        try (Scanner scanner = new Scanner(new FileReader(USER_DATA_FILE))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] userData = line.split(",");
                if (userData.length >= 4 && userData[1].equalsIgnoreCase(email) && userData[3].equals(password)) {
                    return userData[4]; // Email and password match found, return the role
                }
            }
        }
        return null; // No match found, return null
    }


    private String getUserRole(String email) throws IOException {
        try (Scanner scanner = new Scanner(new FileReader(USER_DATA_FILE))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] userData = line.split(",");
                if (userData.length >= 5 && userData[1].equalsIgnoreCase(email)) {
                    return userData[4]; // Return the role associated with the email
                }
            }
        }
        return null; // No role found
    }
    private JPanel createNewAccountPanel() {
        JPanel newAccountPanel = new JPanel(new GridLayout(0, 2, 10, 10)); // GridLayout with 2 columns and 10x10 spacing
        newAccountPanel.setAlignmentX(Component.CENTER_ALIGNMENT);


        Font largeFont = new Font("Arial", Font.PLAIN, 18);

        JLabel createAccountLabel = new JLabel("Create a New Account");
        createAccountLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        createAccountLabel.setFont(largeFont);

        JTextField nameField = new JTextField();
        JTextField usernameField = new JTextField();
        JTextField emailField = new JTextField();
        JPasswordField passwordField = new JPasswordField();

        Dimension fieldSize = new Dimension(150, 10);

        nameField.setMaximumSize(fieldSize);
        usernameField.setMaximumSize(fieldSize);
        emailField.setMaximumSize(fieldSize);
        passwordField.setMaximumSize(fieldSize);


        String[] roles = {"Customer", "Seller"};
        JComboBox<String> roleDropdown = new JComboBox<>(roles);
        roleDropdown.setAlignmentX(Component.CENTER_ALIGNMENT);
        roleDropdown.setFont(largeFont);

        JButton submitButton = new JButton("Submit");
        submitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        submitButton.setFont(largeFont);
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText().trim();
                String username = usernameField.getText().trim();
                String email = emailField.getText().trim();
                char[] passwordChars = passwordField.getPassword();
                String password = new String(passwordChars);
                String role = (String) roleDropdown.getSelectedItem();

                if (name.isEmpty() || username.isEmpty() || email.isEmpty() || password.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "All fields must be filled out.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (!email.endsWith("@purdue.edu")) {
                    JOptionPane.showMessageDialog(frame, "Email must end with @purdue.edu.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (password.length() < 8 || !password.matches(".*\\d.*") || !password.matches(".*[!@#$%^&*()-_=+\\[\\]{};:'\",.<>/?].*")) {
                    JOptionPane.showMessageDialog(frame, "Password must be at least 8 characters long, include 1 digit, and 1 special character.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                try {
                    if (isDuplicateEmailOrUsername(username, email)) {
                        showMessage("An account with the same email or username already exists.", "Error","NewAccount");
                    } else {
                        if (writeAccountDataToFile(name, email, username, password, role)) {
                            showMessage("Account created successfully. Redirecting to Sign In.", "Success","");
                            cardLayout.show(cardPanel, "SignIn");
                            frame.setSize(400, 500);
                        } else {
                            showMessage("Failed to create the account.", "Error","NewAccount");
                        }
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                    showMessage("Error saving user data. Please try again later.", "Error","NewAccount");
                    cardLayout.show(cardPanel, "SignIn");
                    frame.setSize(400, 500);

                }
            }


        });

        JButton backButton = new JButton("Back to Sign In");
        backButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        backButton.setFont(largeFont);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "SignIn");
                frame.setSize(400, 500);
            }
        });

        newAccountPanel.add(Box.createVerticalStrut(20));
        newAccountPanel.add(createAccountLabel);

        // Add text labels and fields in a table-like structure
        addFieldWithLabel(newAccountPanel, "Name", nameField);
        addFieldWithLabel(newAccountPanel, "Username", usernameField);
        addFieldWithLabel(newAccountPanel, "Email", emailField);
        addFieldWithLabel(newAccountPanel, "Password", passwordField);
        addFieldWithLabel(newAccountPanel, "Role", roleDropdown);

        newAccountPanel.add(submitButton);
        newAccountPanel.add(backButton);

        return newAccountPanel;
    }

    private JPanel createResetPasswordPanel() {
        JPanel resetPasswordPanel = new JPanel();
        resetPasswordPanel.setLayout(new BoxLayout(resetPasswordPanel, BoxLayout.Y_AXIS));
        resetPasswordPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        Font largeFont = new Font("Arial", Font.PLAIN, 18);

        JLabel resetPasswordLabel = new JLabel("Reset Password");
        resetPasswordLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        resetPasswordLabel.setFont(largeFont);

        JTextField resetEmailField = new JTextField();
        resetEmailField.setMaximumSize(new Dimension(300, 30)); // Adjusted dimensions
        resetEmailField.setAlignmentX(Component.CENTER_ALIGNMENT);

        JTextField resetUsernameField = new JTextField();
        resetUsernameField.setMaximumSize(new Dimension(300, 30)); // Adjusted dimensions
        resetUsernameField.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton resetSubmitButton = new JButton("Submit");
        resetSubmitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        resetSubmitButton.setFont(largeFont);
        resetSubmitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetEmail = resetEmailField.getText().trim();
                resetUsername = resetUsernameField.getText().trim();

                try {
                    if (isMatchingEmailAndUsername(resetEmail, resetUsername)) {
                        // If email and username match, show the PasswordPanel
                        showMessage("Email and username found!", "Message", "PasswordPanel");
                    } else {
                        showMessage("Email and username do not match.", "Error", "ResetPassword");
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                    showMessage("Error checking email and username. Please try again later.", "Error", "ResetPassword");
                }
            }
        });

        resetPasswordPanel.add(Box.createVerticalStrut(20));
        resetPasswordPanel.add(resetPasswordLabel);
        resetPasswordPanel.add(Box.createVerticalStrut(15));

        resetPasswordPanel.add(createFieldWithLabel("        Email", resetEmailField));
        resetPasswordPanel.add(Box.createVerticalStrut(10));
        resetPasswordPanel.add(createFieldWithLabel("Username", resetUsernameField));
        resetPasswordPanel.add(Box.createVerticalStrut(10));

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
        buttonPanel.add(resetSubmitButton);
        buttonPanel.add(Box.createHorizontalStrut(10)); // Add some spacing between buttons
        buttonPanel.add(createBackToMenuButton());

        resetPasswordPanel.add(buttonPanel);
        resetPasswordPanel.add(Box.createVerticalStrut(20));

        return resetPasswordPanel;
    }

    private JPanel createPasswordPanel() {
        JPanel passwordPanel = new JPanel();
        passwordPanel.setLayout(new BoxLayout(passwordPanel, BoxLayout.Y_AXIS));
        passwordPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        Font largeFont = new Font("Arial", Font.PLAIN, 18);

        JLabel newPasswordLabel = new JLabel("Enter New Password");
        newPasswordLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        newPasswordLabel.setFont(largeFont);

        JPasswordField newPasswordField = new JPasswordField();
        newPasswordField.setMaximumSize(new Dimension(300, 30)); // Adjusted dimensions
        newPasswordField.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPasswordField confirmNewPasswordField = new JPasswordField();
        confirmNewPasswordField.setMaximumSize(new Dimension(300, 30)); // Adjusted dimensions
        confirmNewPasswordField.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton resetPasswordButton = new JButton("Reset Password");
        resetPasswordButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        resetPasswordButton.setFont(largeFont);
        resetPasswordButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Use the stored email and username
                char[] newPasswordChars = newPasswordField.getPassword();
                String newPassword = new String(newPasswordChars);

                char[] confirmNewPasswordChars = confirmNewPasswordField.getPassword();
                String confirmNewPassword = new String(confirmNewPasswordChars);

                if (newPassword.equals(confirmNewPassword)) {
                    // Check for password complexity requirements
                    if (newPassword.length() < 8 || !newPassword.matches(".*\\d.*") || !newPassword.matches(".*[!@#$%^&*()-_=+\\[\\]{};:'\",.<>/?].*")) {
                        showMessage("Password must be at least 8 characters long, include 1 digit, and 1 special character.", "Error", "PasswordPanel");
                        return;
                    }

                    // Passwords match and meet complexity requirements, update the password in the file
                    if (updatePasswordInFile(resetEmail, resetUsername, newPassword)) {
                        showMessage("Password reset successfully. Redirecting to Sign In.", "Success", "SignIn");
                    } else {
//                        System.out.println(resetEmail+" "+resetUsername);
                        showMessage("Failed to update the password.", "Error", "PasswordPanel");
                    }
                } else {
                    showMessage("Passwords do not match. Please try again.", "Error", "PasswordPanel");
                }
            }
        });


        passwordPanel.add(Box.createVerticalStrut(20));
        passwordPanel.add(newPasswordLabel);
        passwordPanel.add(Box.createVerticalStrut(15));

        passwordPanel.add(createFieldWithLabel("             New Password", newPasswordField));
        passwordPanel.add(Box.createVerticalStrut(10));
        passwordPanel.add(createFieldWithLabel("Confirm New Password", confirmNewPasswordField));
        passwordPanel.add(Box.createVerticalStrut(10));

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
        buttonPanel.add(resetPasswordButton);
        buttonPanel.add(Box.createHorizontalStrut(10)); // Add some spacing between buttons
        buttonPanel.add(createBackToMenuButton());

        passwordPanel.add(buttonPanel);
        passwordPanel.add(Box.createVerticalStrut(20));

        return passwordPanel;
    }

    private boolean updatePasswordInFile(String email, String username, String newPassword) {
        try (BufferedReader reader = new BufferedReader(new FileReader(USER_DATA_FILE))) {
            StringBuilder updatedData = new StringBuilder();
            String line;
            boolean userUpdated = false;

            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length == 5) {
                    String storedUsername = data[2];
                    String storedEmail = data[1];

                    if (storedUsername.equalsIgnoreCase(username) && storedEmail.equalsIgnoreCase(email)) {
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
                resetEmail = null;
                resetUsername = null;  // Reset the email and username fields
                return true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }


    private JPanel createDeleteAccountRequestPanel() {
        JPanel deleteAccountRequestPanel = new JPanel();
        deleteAccountRequestPanel.setLayout(new BoxLayout(deleteAccountRequestPanel, BoxLayout.Y_AXIS));
        deleteAccountRequestPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        Font largeFont = new Font("Arial", Font.PLAIN, 18);

        JLabel deleteAccountRequestLabel = new JLabel("Delete Account");
        deleteAccountRequestLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        deleteAccountRequestLabel.setFont(largeFont);

        JTextField deleteAccountEmailField = new JTextField();
        deleteAccountEmailField.setMaximumSize(new Dimension(300, 30));
        deleteAccountEmailField.setAlignmentX(Component.CENTER_ALIGNMENT);

        JTextField deleteAccountUsernameField = new JTextField();
        deleteAccountUsernameField.setMaximumSize(new Dimension(300, 30));
        deleteAccountUsernameField.setAlignmentX(Component.CENTER_ALIGNMENT);

        deleteAccountEmailField.setText(""); // Clear the email field
        deleteAccountUsernameField.setText(""); // Clear the username field

        JButton deleteAccountRequestButton = new JButton("Submit");
        deleteAccountRequestButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        deleteAccountRequestButton.setFont(largeFont);
        deleteAccountRequestButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetEmail = deleteAccountEmailField.getText().trim();
                resetUsername = deleteAccountUsernameField.getText().trim();

                try {
                    if (isMatchingEmailAndUsername(resetEmail, resetUsername)) {
                        // If email and username match, show the Delete Password Panel
                        showMessage("Email and username found!", "Message", "DeleteAccount");
                    } else {
                        showMessage("Email and username do not match.", "Error", "DeleteAccountRequest");
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                    showMessage("Error checking email and username. Please try again later.", "Error", "DeleteAccountRequest");
                }
            }
        });

        deleteAccountRequestPanel.add(Box.createVerticalStrut(20));
        deleteAccountRequestPanel.add(deleteAccountRequestLabel);
        deleteAccountRequestPanel.add(Box.createVerticalStrut(15));

        deleteAccountRequestPanel.add(createFieldWithLabel("        Email", deleteAccountEmailField));
        deleteAccountRequestPanel.add(Box.createVerticalStrut(10));
        deleteAccountRequestPanel.add(createFieldWithLabel("Username", deleteAccountUsernameField));
        deleteAccountRequestPanel.add(Box.createVerticalStrut(10));

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
        buttonPanel.add(deleteAccountRequestButton);
        buttonPanel.add(Box.createHorizontalStrut(10)); // Add some spacing between buttons
        buttonPanel.add(createBackToMenuButton());

        deleteAccountRequestPanel.add(buttonPanel);
        deleteAccountRequestPanel.add(Box.createVerticalStrut(20));

        return deleteAccountRequestPanel;
    }

    private JPanel createDeleteAccountPasswordPanel() {
        JPanel deleteAccountPasswordPanel = new JPanel();
        deleteAccountPasswordPanel.setLayout(new BoxLayout(deleteAccountPasswordPanel, BoxLayout.Y_AXIS));
        deleteAccountPasswordPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        Font largeFont = new Font("Arial", Font.PLAIN, 18);

        JLabel deleteAccountPasswordLabel = new JLabel("Delete Account");
        deleteAccountPasswordLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        deleteAccountPasswordLabel.setFont(largeFont);

        JPasswordField deleteAccountPasswordField = new JPasswordField();
        deleteAccountPasswordField.setMaximumSize(new Dimension(300, 30));
        deleteAccountPasswordField.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPasswordField confirmDeleteAccountPasswordField = new JPasswordField();
        confirmDeleteAccountPasswordField.setMaximumSize(new Dimension(300, 30));
        confirmDeleteAccountPasswordField.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton deleteAccountButton = new JButton("Delete Account");
        deleteAccountButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        deleteAccountButton.setFont(largeFont);
        deleteAccountButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                char[] deleteAccountPasswordChars = deleteAccountPasswordField.getPassword();
                String deleteAccountPassword = new String(deleteAccountPasswordChars);

                char[] confirmDeleteAccountPasswordChars = confirmDeleteAccountPasswordField.getPassword();
                String confirmDeleteAccountPassword = new String(confirmDeleteAccountPasswordChars);

                if (deleteAccountPassword.equals(confirmDeleteAccountPassword)) {
                    // Check for password complexity requirements
                    if (deleteAccountPassword.length() < 8 || !deleteAccountPassword.matches(".*\\d.*") || !deleteAccountPassword.matches(".*[!@#$%^&*()-_=+\\[\\]{};:'\",.<>/?].*")) {
                        showMessage("Password must be at least 8 characters long, include 1 digit, and 1 special character.", "Error", "DeleteAccount");
                        return;
                    }

                    // Passwords match and meet complexity requirements, proceed with account deletion
                    if (deleteAccount(resetEmail, deleteAccountPassword)) {
                        showMessage("Account deleted successfully. Redirecting to Sign In.", "Success", "SignIn");
                    } else {
                        showMessage("Failed to delete the account.", "Error", "DeleteAccount");
                    }
                } else {
                    showMessage("Passwords do not match. Please try again.", "Error", "DeleteAccount");
                }
            }
        });

        deleteAccountPasswordPanel.add(Box.createVerticalStrut(20));
        deleteAccountPasswordPanel.add(deleteAccountPasswordLabel);
        deleteAccountPasswordPanel.add(Box.createVerticalStrut(15));

        deleteAccountPasswordPanel.add(createFieldWithLabel("             Password", deleteAccountPasswordField));
        deleteAccountPasswordPanel.add(Box.createVerticalStrut(10));
        deleteAccountPasswordPanel.add(createFieldWithLabel("Confirm Password", confirmDeleteAccountPasswordField));
        deleteAccountPasswordPanel.add(Box.createVerticalStrut(10));

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
        buttonPanel.add(deleteAccountButton);
        buttonPanel.add(Box.createHorizontalStrut(10)); // Add some spacing between buttons
        buttonPanel.add(createBackToMenuButton());

        deleteAccountPasswordPanel.add(buttonPanel);
        deleteAccountPasswordPanel.add(Box.createVerticalStrut(20));

        return deleteAccountPasswordPanel;
    }

    private boolean deleteAccount(String email, String password) {
        try (BufferedReader reader = new BufferedReader(new FileReader(USER_DATA_FILE))) {
            StringBuilder updatedData = new StringBuilder();
            String line;
            boolean userDeleted = false;

            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length == 5) {
                    String storedUsername = data[2];
                    String storedEmail = data[1];

                    // Check if email, username, and password match
                    if (storedUsername.equalsIgnoreCase(resetUsername)
                            && storedEmail.equalsIgnoreCase(resetEmail)
                            && data[3].equals(password)) {
                        userDeleted = true;
                    } else {
                        updatedData.append(line).append("\n");
                    }
                } else {
                    updatedData.append(line).append("\n");
                }
            }

            if (userDeleted) {
                try (BufferedWriter writer = new BufferedWriter(new FileWriter(USER_DATA_FILE, false))) {
                    writer.write(updatedData.toString());
                }
                resetEmail = null;
                resetUsername = null;  // Reset the email and username fields
                return true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }


    private boolean isMatchingEmailAndUsername(String email, String username) throws IOException {
        try (Scanner scanner = new Scanner(new FileReader(USER_DATA_FILE))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] userData = line.split(",");
                if (userData.length >= 3 && userData[1].equalsIgnoreCase(email) && userData[2].equalsIgnoreCase(username)) {
                    return true; // Email and username match found
                }
            }
        }
        return false; // No match found
    }

    // Helper method to add text labels before fields
    private void addFieldWithLabel(JPanel panel, String labelText, JComponent field) {
        JLabel label = new JLabel(labelText);
        label.setFont(new Font("Arial", Font.PLAIN, 18)); // Set font size
        panel.add(label);
        panel.add(field);
    }



    private boolean isDuplicateEmailOrUsername(String username, String email) throws IOException {
        try (Scanner scanner = new Scanner(new FileReader(USER_DATA_FILE))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] userData = line.split(",");
                if (userData.length >= 3 && (userData[1].equalsIgnoreCase(email) || userData[2].equalsIgnoreCase(username))) {
                    return true; // Duplicate email or username found
                }
            }
        }
        return false; // No duplicate found
    }


    private boolean writeAccountDataToFile(String name, String email, String username, String password, String role) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(USER_DATA_FILE, true))) {
            // Append the new account data to the file using a comma as the delimiter
            writer.write(name + "," + email + "," + username + "," + password + "," + role);
            writer.newLine();
            return true;
        }
    }



    private void showMessage(String message, String title, String redirectToPanel) {
        messageLabel.setText(message);
        cardLayout.show(cardPanel, "Message");
        JOptionPane.showMessageDialog(frame, message, title, JOptionPane.INFORMATION_MESSAGE);

        switch (redirectToPanel) {
            case "NewAccount":
                cardLayout.show(cardPanel, "NewAccount");
                frame.setSize(500, 300);
                break;
            case "ResetPassword":
                cardLayout.show(cardPanel, "ResetPassword");
                frame.setSize(400, 200);
                break;
            case "PasswordPanel":
                cardLayout.show(cardPanel, "PasswordPanel");
                frame.setSize(600, 200);
                break;
            case "DeleteAccount":
                cardLayout.show(cardPanel, "DeleteAccount");
                frame.setSize(600, 200);
                break;
            case "DeleteAccountRequest":
                cardLayout.show(cardPanel, "DeleteAccountRequest");
                frame.setSize(400, 200);
                break;
            default:
                cardLayout.show(cardPanel, "SignIn");
                frame.setSize(400, 500);
                break;
        }
    }

    private JButton createBackToMenuButton() {
        JButton backButton = new JButton("Back to Menu");
        Font largeFont = new Font("Arial", Font.PLAIN, 18);
        backButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        backButton.setFont(largeFont);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "SignIn");
                frame.setSize(400, 500);
            }
        });
        return backButton;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new AccountsPanel().display());
    }

    private void display() {
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}