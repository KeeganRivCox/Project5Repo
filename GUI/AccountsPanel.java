import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.Scanner;
import java.util.concurrent.CountDownLatch;

public class AccountsPanel {
    public static CountDownLatch countDownLatch = new CountDownLatch(1); // made for redirecting

    private JFrame frame;
    private JPanel cardPanel;
    private CardLayout cardLayout;
    private JPanel messagePanel;
    private JLabel messageLabel;
    private JTextField emailField;

    private String resetEmail;
    private String resetUsername;
    private JPasswordField passwordField; // Moved the password field to the class level
    Color customColor = new Color(206, 184, 136);
    Color greyButtonColor = new Color(196, 191, 192);
    Border customBorder = BorderFactory.createLineBorder(Color.BLACK,3);

    public String accountRole = "";

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
        frame.setSize(450,550);
        frame.setResizable(false);

        JPanel signInPanel = new JPanel();
        signInPanel.setBackground(customColor);
        signInPanel.setLayout(new BoxLayout(signInPanel, BoxLayout.Y_AXIS));
        signInPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        ImageIcon boilerBayIcon = new ImageIcon("images/boilerBayLogo.png");
        JLabel boilerBayLabel = new JLabel();
        boilerBayLabel.setMinimumSize(new Dimension(100, 100));
        boilerBayLabel.setMaximumSize(new Dimension(100, 100));
        boilerBayLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        boilerBayLabel.setIcon(new ImageIcon(boilerBayIcon.getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT)));


        // Increased font size
        Font largeFont = new Font("Arial", Font.BOLD, 20);

        JLabel welcomeLabel = new JLabel("Welcome to Boiler Bay!");
        welcomeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        welcomeLabel.setFont(largeFont);

        JPanel topPanel = new JPanel();
        topPanel.setOpaque(false);
        topPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.X_AXIS));
        topPanel.setMinimumSize(new Dimension(350, 50));
        topPanel.setMaximumSize(new Dimension(350, 50));

        topPanel.add(Box.createHorizontalStrut(30));

        topPanel.add(welcomeLabel);
        topPanel.add(Box.createHorizontalStrut(20));
        topPanel.add(boilerBayLabel);

        JLabel signInLabel = new JLabel("Sign In:");
        signInLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        signInLabel.setFont(largeFont);


//        JLabel emailInputLabel = new JLabel("Email:     ");
//        emailInputLabel.setOpaque(false);
//        emailInputLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
//        emailInputLabel.setFont(largeFont);
        emailField = new JTextField();
        emailField.setMaximumSize(new Dimension(250, 30)); // Adjusted dimensions
        emailField.setMinimumSize(new Dimension(250, 30)); // Adjusted dimensions
        emailField.setAlignmentX(Component.CENTER_ALIGNMENT);
        emailField.setFont(new Font("Arial", Font.PLAIN, 16));

        passwordField = new JPasswordField();
        passwordField.setMaximumSize(new Dimension(250, 30)); // Adjusted dimensions
        passwordField.setMinimumSize(new Dimension(250, 30)); // Adjusted dimensions
        passwordField.setAlignmentX(Component.CENTER_ALIGNMENT);
        passwordField.setFont(new Font("Arial", Font.PLAIN, 16));
        passwordField.setColumns(15);

        JButton forgotPasswordButton = new JButton("Forgot Password");
        forgotPasswordButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        forgotPasswordButton.setBackground(greyButtonColor);
        forgotPasswordButton.setBorder(customBorder);
        forgotPasswordButton.setFont(largeFont);
        forgotPasswordButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "ResetPassword");
                frame.setSize(400, 250);
            }
        });

        JButton deleteAccountButton = new JButton("Delete Account");
        deleteAccountButton.setBackground(greyButtonColor);
        deleteAccountButton.setBorder(customBorder);
        deleteAccountButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        deleteAccountButton.setFont(largeFont);
        deleteAccountButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "DeleteAccountRequest");
                frame.setSize(400, 250);
            }
        });

        JButton createAccountButton = new JButton("Create a New Account");
        createAccountButton.setBackground(greyButtonColor);
        createAccountButton.setBorder(customBorder);
        createAccountButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        createAccountButton.setFont(largeFont);
        createAccountButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "NewAccount");
                frame.setSize(500, 300);
                frame.setResizable(false);
            }
        });

        JButton submitEmailButton = new JButton("Submit");
        submitEmailButton.setBackground(greyButtonColor);
        submitEmailButton.setBorder(customBorder);
        submitEmailButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        submitEmailButton.setFont(largeFont);
        submitEmailButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String accountEmail = emailField.getText();
                char[] passwordChars = passwordField.getPassword();
                String accountPassword = new String(passwordChars);

                // Returns null if no account found under email
                // Returns account object if found from account hash map
                Account accountRetrieved = new Request().getAccount(accountEmail);

                if (accountRetrieved == null) {

                    showMessage("Authentication failed. Please check your email and password.", "Error","");
                    // Clear the password field on authentication failure
                    passwordField.setText("");

                } else {

                    String retrievedAccountPassword = accountRetrieved.getPassword();

                    if (retrievedAccountPassword.equals(accountPassword)) {

                        showMessage("Authentication successful. Redirecting...", "Success","");
                        emailField.setText("");
                        passwordField.setText("");
                        // Add logic to redirect to the appropriate panel based on the role
                        // Keegan - In progress...

                        accountRole = accountRetrieved.getRole().toLowerCase();

                        frame.dispose();

                        switch (accountRole) {

                            case "seller" -> {

                                new SellerPanel(accountRetrieved.getEmail());

                            }
                            case "customer" -> {

                                new CustomersPanel(accountRetrieved.getEmail());

                            }

                        }


                    } else {

                        showMessage("Authentication failed. Please check your email and password.", "Error","");
                        // Clear the password field on authentication failure
                        passwordField.setText("");

                    }

                }

            }
        });

        // Increase button size
        Dimension buttonSize = new Dimension(300, 30);

        createAccountButton.setMinimumSize(buttonSize);
        createAccountButton.setMaximumSize(buttonSize);
        forgotPasswordButton.setMaximumSize(buttonSize);
        forgotPasswordButton.setMinimumSize(buttonSize);
        deleteAccountButton.setMaximumSize(buttonSize);
        deleteAccountButton.setMinimumSize(buttonSize);
        submitEmailButton.setMinimumSize(buttonSize);
        submitEmailButton.setMaximumSize(buttonSize);

        JLabel needHelpLabel = new JLabel("Need Help?");
        needHelpLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        needHelpLabel.setFont(largeFont);

        JLabel newToBoilerBayLabel = new JLabel("New to Boiler Bay?");
        newToBoilerBayLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        newToBoilerBayLabel.setFont(largeFont);

        JSeparator sep = new JSeparator();
        sep.setBackground(Color.BLACK);
        sep.setForeground(Color.BLACK);

        JSeparator sep2 = new JSeparator();
        sep2.setBackground(Color.BLACK);
        sep2.setForeground(Color.BLACK);

        JSeparator sep3 = new JSeparator();
        sep3.setBackground(Color.BLACK);
        sep3.setForeground(Color.BLACK);

        signInPanel.add(Box.createVerticalStrut(20));
        signInPanel.add(topPanel);
        signInPanel.add(Box.createVerticalStrut(15));
        signInPanel.add(sep3);
        signInPanel.add(Box.createVerticalStrut(5));
        signInPanel.add(signInLabel);
        signInPanel.add(Box.createVerticalStrut(15));

        // Add text labels before fields
        signInPanel.add(createFieldWithLabel("Email:         ", emailField));
        signInPanel.add(Box.createVerticalStrut(10));
        signInPanel.add(createFieldWithLabel("Password: ", passwordField));
        signInPanel.add(Box.createVerticalStrut(15));
        signInPanel.add(submitEmailButton);
        signInPanel.add(Box.createVerticalStrut(15));
        signInPanel.add(sep);
        signInPanel.add(Box.createVerticalStrut(10));
        signInPanel.add(needHelpLabel);
        signInPanel.add(Box.createVerticalStrut(15));
        signInPanel.add(forgotPasswordButton);
        signInPanel.add(Box.createVerticalStrut(10));
        signInPanel.add(deleteAccountButton);
        signInPanel.add(Box.createVerticalStrut(20));
        signInPanel.add(sep2);
        signInPanel.add(Box.createVerticalStrut(15));
        signInPanel.add(newToBoilerBayLabel);
        signInPanel.add(Box.createVerticalStrut(15));
        signInPanel.add(createAccountButton);
        signInPanel.add(Box.createVerticalStrut(40));

        return signInPanel;
    }

    private JPanel createFieldWithLabel(String labelText, JComponent field) {
        JPanel panel = new JPanel();
        panel.setOpaque(false);
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));

        JLabel label = new JLabel(labelText);
        label.setFont(new Font("Arial", Font.PLAIN, 18)); // Set font size
        label.setPreferredSize(new Dimension(100, 20)); // Adjusted label size

        panel.add(label);
        panel.add(field);

        return panel;
    }

    private JPanel createNewAccountPanel() {
        JPanel newAccountPanel = new JPanel(new GridLayout(0, 2, 10, 10)); // GridLayout with 2 columns and 10x10 spacing
        newAccountPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        newAccountPanel.setBackground(customColor);

        Font largeFont = new Font("Arial", Font.PLAIN, 18);
        Font fieldFont = new Font("Arial", Font.PLAIN,16);

        JLabel createAccountLabel = new JLabel("Create a New Account");
        createAccountLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        createAccountLabel.setFont(new Font("Arial", Font.BOLD, 18));

        JTextField nameField = new JTextField();
        nameField.setFont(fieldFont);

        JTextField usernameField = new JTextField();
        usernameField.setFont(fieldFont);

        JTextField emailField = new JTextField();
        emailField.setFont(fieldFont);

        JPasswordField passwordField = new JPasswordField();
        passwordField.setFont(fieldFont);


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
        submitButton.setBackground(greyButtonColor);
        submitButton.setBorder(customBorder);
        submitButton.addActionListener(new ActionListener() {
            @Override

            // Implementing server methods: finished - Keegan

            public void actionPerformed(ActionEvent e) {
                String accountName = nameField.getText().trim();
                String accountUsername = usernameField.getText().trim();
                String accountEmail = emailField.getText().trim();
                char[] passwordChars = passwordField.getPassword();
                String accountPassword = new String(passwordChars);
                String accountRole = (String) roleDropdown.getSelectedItem();

                if (accountName.isEmpty() || accountUsername.isEmpty() || accountEmail.isEmpty() || accountPassword.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "All fields must be filled out.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (!accountEmail.endsWith("@purdue.edu")) {
                    JOptionPane.showMessageDialog(frame, "Email must end with @purdue.edu.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (accountPassword.length() < 8 || !accountPassword.matches(".*\\d.*") || !accountPassword.matches(".*[!@#$%^&*()-_=+\\[\\]{};:'\",.<>/?].*")) {
                    JOptionPane.showMessageDialog(frame, "Password must be at least 8 characters long, include 1 digit, and 1 special character.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                int response = new Request().createAccount(new Account(accountName, accountEmail, accountPassword, accountUsername, accountRole));

                // Returns 0 if account successfully created
                // Returns 1 if email is already taken
                // Returns 2 if username is already taken
                // Returns 3 if an unexpected error occurred

                switch (response) {

                    case 0 -> { // Account created successfully

                        showMessage("Account created successfully. Redirecting to Sign In.", "Success","");
                        nameField.setText("");
                        usernameField.setText("");
                        emailField.setText("");
                        passwordField.setText("");
                        cardLayout.show(cardPanel, "SignIn");
                        frame.setSize(400, 550);

                    }
                    case 1 -> { // Email already taken

                        showMessage("An account with the same email already exists.", "Error","NewAccount");

                    }
                    case 2 -> {

                        showMessage("An account with the same username already exists.", "Error","NewAccount");

                    }
                    case 3 -> { // Unexpected error

                        showMessage("Failed to create the account.", "Error","NewAccount");

                    }

                }

            }


        });

        JButton backButton = new JButton("Back to Sign In");
        backButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        backButton.setFont(largeFont);
        backButton.setBackground(greyButtonColor);
        backButton.setBorder(customBorder);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "SignIn");
                frame.setSize(400, 550);
            }
        });

        newAccountPanel.add(Box.createVerticalStrut(20));
        newAccountPanel.add(createAccountLabel);

        // Add text labels and fields in a table-like structure
        addFieldWithLabel(newAccountPanel, "         Name:", nameField);
        addFieldWithLabel(newAccountPanel, "         Username", usernameField);
        addFieldWithLabel(newAccountPanel, "         Email", emailField);
        addFieldWithLabel(newAccountPanel, "         Password", passwordField);
        addFieldWithLabel(newAccountPanel, "         Role", roleDropdown);

        newAccountPanel.add(submitButton);
        newAccountPanel.add(backButton);

        return newAccountPanel;
    }

    // Implementing server methods: In progress... - Keegan
    private JPanel createResetPasswordPanel() {
        JPanel resetPasswordPanel = new JPanel();
        resetPasswordPanel.setBackground(customColor);
        resetPasswordPanel.setLayout(new BoxLayout(resetPasswordPanel, BoxLayout.Y_AXIS));
        resetPasswordPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        Font largeFont = new Font("Arial", Font.PLAIN, 16);

        JLabel resetPasswordLabel = new JLabel("Reset Password");
        resetPasswordLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        resetPasswordLabel.setFont(new Font("Arial", Font.BOLD,20));

        JTextField resetEmailField = new JTextField();
        resetEmailField.setMaximumSize(new Dimension(250, 30)); // Adjusted dimensions
        resetEmailField.setMinimumSize(new Dimension(250, 30)); // Adjusted dimensions
        resetEmailField.setFont(largeFont);
        resetEmailField.setAlignmentX(Component.CENTER_ALIGNMENT);

        JTextField resetUsernameField = new JTextField();
        resetUsernameField.setMaximumSize(new Dimension(250, 30)); // Adjusted dimensions
        resetUsernameField.setMinimumSize(new Dimension(250, 30)); // Adjusted dimensions
        resetUsernameField.setFont(largeFont);
        resetUsernameField.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton resetSubmitButton = new JButton("Submit");
        resetSubmitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        resetSubmitButton.setBorder(customBorder);
        resetSubmitButton.setBackground(greyButtonColor);
        resetSubmitButton.setFont(new Font("Arial", Font.PLAIN,18));
        resetSubmitButton.setMaximumSize(new Dimension(150,30));
        resetSubmitButton.setMinimumSize(new Dimension(150,30));
        resetSubmitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                resetEmail = resetEmailField.getText().trim();
                resetUsername = resetUsernameField.getText().trim();

                Account accountRetrieved = new Request().getAccount(resetEmail);

                if (accountRetrieved == null) {

                    showMessage("Email not found.", "Error", "ResetPassword");

                } else if (!accountRetrieved.getUsername().equals(resetUsername)) {

                    showMessage("Email and username do not match.", "Error", "ResetPassword");

                } else {

                    showMessage("Email and username found!", "Message", "PasswordPanel");
                    resetEmailField.setText("");
                    resetUsernameField.setText("");

                }

            }
        });

        resetPasswordPanel.add(Box.createVerticalStrut(20));
        resetPasswordPanel.add(resetPasswordLabel);
        resetPasswordPanel.add(Box.createVerticalStrut(15));
        resetPasswordPanel.add(createFieldWithLabel("Email:           ", resetEmailField));
        resetPasswordPanel.add(Box.createVerticalStrut(15));
        resetPasswordPanel.add(createFieldWithLabel("Username:  ", resetUsernameField));
        resetPasswordPanel.add(Box.createVerticalStrut(15));

        JPanel buttonPanel = new JPanel();
        buttonPanel.setOpaque(false);
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
        buttonPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        buttonPanel.add(Box.createHorizontalStrut(10)); // Add some spacing between buttons

        buttonPanel.add(resetSubmitButton);
        buttonPanel.add(Box.createHorizontalStrut(20)); // Add some spacing between buttons
        buttonPanel.add(createBackToMenuButton());

        resetPasswordPanel.add(buttonPanel);
        resetPasswordPanel.add(Box.createVerticalStrut(20));

        return resetPasswordPanel;
    }

    private JPanel createPasswordPanel() {
        JPanel passwordPanel = new JPanel();
        passwordPanel.setOpaque(false);
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

                if (!newPassword.equals(confirmNewPassword)) {

                    showMessage("Passwords do not match. Please try again.", "Error", "PasswordPanel");
                    return;

                } else {

                    // Check for password complexity requirements
                    if (newPassword.length() < 8 || !newPassword.matches(".*\\d.*") || !newPassword.matches(".*[!@#$%^&*()-_=+\\[\\]{};:'\",.<>/?].*")) {
                        showMessage("Password must be at least 8 characters long, include 1 digit, and 1 special character.", "Error", "PasswordPanel");
                        return;

                    }

                }

                boolean response = new Request().updateAccountPassword(resetEmail, newPassword);

                if (response) {

                    showMessage("Password reset successfully. Redirecting to Sign In.", "Success", "SignIn");
                    newPasswordField.setText("");
                    confirmNewPasswordField.setText("");

                } else {

                    showMessage("Failed to update the password.", "Error", "PasswordPanel");

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

    private JPanel createDeleteAccountRequestPanel() {
        JPanel deleteAccountRequestPanel = new JPanel();
        deleteAccountRequestPanel.setBackground(customColor);
        deleteAccountRequestPanel.setLayout(new BoxLayout(deleteAccountRequestPanel, BoxLayout.Y_AXIS));
        deleteAccountRequestPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        Font largeFont = new Font("Arial", Font.PLAIN, 18);

        JLabel deleteAccountRequestLabel = new JLabel("Delete Account");
        deleteAccountRequestLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        deleteAccountRequestLabel.setFont(new Font("Arial", Font.BOLD,20));

        JTextField deleteAccountEmailField = new JTextField();
        deleteAccountEmailField.setMaximumSize(new Dimension(250, 30));
        deleteAccountEmailField.setMinimumSize(new Dimension(250, 30));

        deleteAccountEmailField.setAlignmentX(Component.CENTER_ALIGNMENT);

        JTextField deleteAccountUsernameField = new JTextField();
        deleteAccountUsernameField.setMaximumSize(new Dimension(250, 30));
        deleteAccountUsernameField.setMinimumSize(new Dimension(250, 30));
        deleteAccountUsernameField.setAlignmentX(Component.CENTER_ALIGNMENT);

        deleteAccountEmailField.setText(""); // Clear the email field
        deleteAccountUsernameField.setText(""); // Clear the username field

        JButton deleteAccountRequestButton = new JButton("Submit");
        deleteAccountRequestButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        deleteAccountRequestButton.setBackground(greyButtonColor);
        deleteAccountRequestButton.setBorder(customBorder);
        deleteAccountRequestButton.setFont(largeFont);
        deleteAccountRequestButton.setMaximumSize(new Dimension(150,30));
        deleteAccountRequestButton.setMinimumSize(new Dimension(150,30));
        deleteAccountRequestButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetEmail = deleteAccountEmailField.getText().trim();
                resetUsername = deleteAccountUsernameField.getText().trim();

                Account accountRetrieved = new Request().getAccount(resetEmail);

                if (accountRetrieved == null) {

                    showMessage("Email not found.", "Error", "DeleteAccountRequest");


                } else if (!accountRetrieved.getUsername().equals(resetUsername)) {

                    showMessage("Email and username do not match.", "Error", "DeleteAccountRequest");

                } else {

                    showMessage("Email and username found!", "Message", "DeleteAccount");
                    deleteAccountEmailField.setText("");
                    deleteAccountUsernameField.setText("");

                }

              }
        });

        deleteAccountRequestPanel.add(Box.createVerticalStrut(20));
        deleteAccountRequestPanel.add(deleteAccountRequestLabel);
        deleteAccountRequestPanel.add(Box.createVerticalStrut(15));

        deleteAccountRequestPanel.add(createFieldWithLabel("Email:         ", deleteAccountEmailField));
        deleteAccountRequestPanel.add(Box.createVerticalStrut(10));
        deleteAccountRequestPanel.add(createFieldWithLabel("Username: ", deleteAccountUsernameField));
        deleteAccountRequestPanel.add(Box.createVerticalStrut(10));

        JPanel buttonPanel = new JPanel();
        buttonPanel.setOpaque(false);
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
        deleteAccountButton.setOpaque(false);
        deleteAccountButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        deleteAccountButton.setMaximumSize(new Dimension(150,30));
        deleteAccountButton.setMinimumSize(new Dimension(150,30));
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

                    boolean deleteResponse = new Request().deleteAccount(resetEmail);

                    // Passwords match and meet complexity requirements, proceed with account deletion
                    if (deleteResponse) {

                        showMessage("Account deleted successfully. Redirecting to Sign In.", "Success", "SignIn");
                        deleteAccountPasswordField.setText("");
                        confirmDeleteAccountPasswordField.setText("");

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
        buttonPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        buttonPanel.add(deleteAccountButton);
        buttonPanel.add(Box.createHorizontalStrut(10)); // Add some spacing between buttons
        buttonPanel.add(createBackToMenuButton());

        deleteAccountPasswordPanel.add(buttonPanel);
        deleteAccountPasswordPanel.add(Box.createVerticalStrut(20));

        return deleteAccountPasswordPanel;
    }

    // Helper method to add text labels before fields
    private void addFieldWithLabel(JPanel panel, String labelText, JComponent field) {

        JLabel label = new JLabel(labelText);
        label.setFont(new Font("Arial", Font.PLAIN, 18)); // Set font size

        panel.add(label);
        panel.add(field);
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
        backButton.setBorder(customBorder);
        backButton.setBackground(greyButtonColor);
        backButton.setMaximumSize(new Dimension(150,30));
        backButton.setMinimumSize(new Dimension(150,30));

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "SignIn");
                frame.setSize(400, 550);
            }
        });
        return backButton;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new AccountsPanel().display());
    }

    public void display() {
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

}