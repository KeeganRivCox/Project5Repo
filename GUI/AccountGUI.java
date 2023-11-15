import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class AccountGUI extends JComponent implements Runnable {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new AccountGUI());

    }

    public void run() {
        JFrame accountLogInScreen = new JFrame();
        accountLogInScreen.setTitle("Boiler Bay");
        Container content = accountLogInScreen.getContentPane();
        content.setLayout(new BorderLayout());
        JPanel createAccountPanel = new JPanel();
        JButton createAccountButton = new JButton("Create your Boiler Bay Account");
        createAccountPanel.add(createAccountButton);

    }
}
