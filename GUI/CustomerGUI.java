import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CustomerGUI extends JComponent implements Runnable{
    JFrame loginFrame;

    JFrame customerMainPageFrame;
    JButton nextButton;
    JLabel customerNameLabel;

    JLabel successfulLoginMessage;


    ActionListener actionListener = new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == nextButton) {
                loginFrame.dispose();
                customerMainPageFrame.setVisible(true);
            }

        }
    };
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new CustomerGUI());

    }
    public void run() {

        loginFrame = new JFrame("Welcome!");
        Container loginFrameContent = loginFrame.getContentPane();
        BoxLayout boxLayout = new BoxLayout(loginFrameContent, BoxLayout.Y_AXIS);
        loginFrameContent.setLayout(boxLayout);
        loginFrame.setDefaultCloseOperation((JFrame.DISPOSE_ON_CLOSE));



        JPanel formatPanel = new JPanel(new BorderLayout());
        nextButton = new JButton("Next");
        nextButton.addActionListener(actionListener);
        formatPanel.add(nextButton, BorderLayout.SOUTH);



        customerNameLabel = new JLabel("Customer"); //need implementation to return the name of the customer
        // customerNameLabel = new JLabel("Customer " +  account.getName());
        customerNameLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        customerNameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        successfulLoginMessage = new JLabel("Successful Login!");
        successfulLoginMessage.setFont(new Font("Arial", Font.PLAIN, 35));
        successfulLoginMessage.setAlignmentX(Component.CENTER_ALIGNMENT);

        loginFrame.add(customerNameLabel);
        loginFrame.add(Box.createVerticalStrut(20));
        loginFrame.add(successfulLoginMessage);
        loginFrame.add(Box.createVerticalStrut(30));
        loginFrame.add(formatPanel);


        loginFrame.setSize(400,200);
        loginFrame.setLocationRelativeTo(null);
        loginFrame.setVisible(true);





        customerMainPageFrame = new JFrame("Customer Main Page");
        Container customerMainPageContent = customerMainPageFrame.getContentPane();
        customerMainPageFrame.setLayout(new CardLayout());
        JPanel mainPagePanel = new JPanel(new GridLayout(4, 0, 50, 50));
        mainPagePanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPagePanel.setSize(500,500);

        JPanel panelOne = new JPanel(new BorderLayout());
        JLabel programTitle = new JLabel("Boiler Buy");
        programTitle.setFont(new Font("Arial", Font.PLAIN, 20));
        panelOne.add(programTitle,BorderLayout.CENTER);
        mainPagePanel.add(panelOne);



        customerMainPageFrame.add(mainPagePanel);
        customerMainPageFrame.setSize(500, 500);
        customerMainPageFrame.setLocationRelativeTo(null);
        customerMainPageFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);



    }

}
