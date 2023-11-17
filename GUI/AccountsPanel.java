import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AccountsPanel {
    private JFrame frame;
    private JPanel cardPanel;
    private CardLayout cardLayout;
    private JTextField emailField;

    public AccountsPanel() {
        frame = new JFrame("Boiler Bay Login");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(350, 500); // Increased dimensions

        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

        cardPanel.add(createSignInPanel(), "SignIn");
        cardPanel.add(createNewAccountPanel(), "NewAccount");

        frame.add(cardPanel);

        cardLayout.show(cardPanel, "SignIn");
    }

    private JPanel createSignInPanel() {
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
        emailField.setMaximumSize(new Dimension(300, 60)); // Increased dimensions
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
        signInPanel.add(emailInputLabel);
        signInPanel.add(emailField);
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

    private JPanel createNewAccountPanel() {
        JPanel newAccountPanel = new JPanel();
        newAccountPanel.setLayout(new BoxLayout(newAccountPanel, BoxLayout.Y_AXIS));

        // Add components for creating a new account (similar to the above panel)

        return newAccountPanel;
    }

    public void display() {
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new AccountsPanel().display());
    }
}
