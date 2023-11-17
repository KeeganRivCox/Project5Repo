import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class AccountsPanel {
    private JFrame frame;
    private JPanel cardPanel;
    private CardLayout cardLayout;
    private JPanel messagePanel;
    private JLabel messageLabel;
    private JTextField emailField;  // Declare emailField as a class member

    // File path for user data
    private static final String USER_DATA_FILE = "user_data.txt";

    public AccountsPanel() {
        frame = new JFrame("Boiler Bay Login");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

        cardPanel.add(createSignInPanel(), "SignIn");
        cardPanel.add(createNewAccountPanel(), "NewAccount");

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
        frame.setSize(400, 450);
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

        JButton forgotPasswordButton = new JButton("Forgot Password");
        forgotPasswordButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        forgotPasswordButton.setFont(largeFont);
        forgotPasswordButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(frame, "Forgot Password clicked!");
            }
        });

        JButton deleteAccountButton = new JButton("Delete Account");
        deleteAccountButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        deleteAccountButton.setFont(largeFont);
        deleteAccountButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(frame, "Delete Account clicked!");
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
        signInPanel.add(createFieldWithLabel("Email", emailField));

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

                if (password.length() < 8 || !password.matches(".*\\d.*") || !password.matches(".*[!@#$%^&*()-_=+\\[\\]{};:'\",.<>/?].*")) {
                    JOptionPane.showMessageDialog(frame, "Password must be at least 8 characters long, include 1 digit, and 1 special character.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                try {
                    if (isDuplicateEmailOrUsername(username, email)) {
                        showMessage("An account with the same email or username already exists.", "Error");
                    } else {
                        if (writeAccountDataToFile(name, email, username, password, role)) {
                            showMessage("Account created successfully. Redirecting to Sign In.", "Success");
                            cardLayout.show(cardPanel, "SignIn");
                        } else {
                            showMessage("Failed to create the account.", "Error");
                        }
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                    showMessage("Error saving user data. Please try again later.", "Error");
                    cardLayout.show(cardPanel, "SignIn");
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
                frame.setSize(400, 450);
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
                String[] userData = line.split(";");
                if (userData[1].equalsIgnoreCase(email) || userData[2].equalsIgnoreCase(username)) {
                    return true; // Duplicate email or username found
                }
            }
        }
        return false; // No duplicate found
    }

    private boolean writeAccountDataToFile(String name, String email, String username, String password, String role) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(USER_DATA_FILE, true))) {
            // Append the new account data to the file
            writer.write(name + ";" + email + ";" + username + ";" + password + ";" + role);
            writer.newLine();
            return true;
        }
    }

    private void showMessage(String message, String title) {
        messageLabel.setText(message);
        cardLayout.show(cardPanel, "Message");
        JOptionPane.showMessageDialog(frame, message, title, JOptionPane.INFORMATION_MESSAGE);
        cardLayout.show(cardPanel, "NewAccount"); // switch back to the new account panel after showing the message
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new AccountsPanel().display());
    }

    private void display() {
        frame.setVisible(true);
    }
}
