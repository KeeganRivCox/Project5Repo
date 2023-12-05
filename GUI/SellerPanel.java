import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;
import java.awt.*;
import java.awt.event.*;

public class SellerPanel {
    private final JFrame frame;
    private final JPanel cardPanel;

    private String editStoreName;
    private final CardLayout cardLayout;

    JFrame loginFrame; //probably delete this stuff below
    JFrame customerMainPageFrame;
    JButton nextButton;
    JLabel customerNameLabel;
    JLabel successfulLoginMessage;

    // Customer Main page
    JButton searchButton;
    JButton seeAllProductsButton;
    JButton seeAllStoresButton;
    JButton seeAllSellersButton;
    JButton viewAccountPageButton; // top right button

    // Search By Page
    JButton searchByNameButton;
    JButton searchByStoreButton;
    JButton searchByDescriptionButton;
    JButton returnToMainPageButton;


    //ActionListener actionListener = new ActionListener() {
    //public void actionPerformed(ActionEvent e) {
    // if (e.getSource() == nextButton) {
    //loginFrame.dispose();
    //customerMainPageFrame.setVisible(true);
    //}

    //}
    //};

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> new SellerPanel());

    }

    public SellerPanel() {
        frame = new JFrame("Sellers");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);
        cardPanel.add(createMainPanel(), "Main Page");
        cardPanel.add(createAccountPanel(), "Account Options");
        cardPanel.add(createEditStorePanel(), "Edit Store");
        cardPanel.add(createContactCustomerPanel(), "Contact Customers");
        cardPanel.add(createProductListingsPanel(), "Product Listings");
        cardPanel.add(createIndividualProductPanel(), "Individual Products");
        cardPanel.add(createCustomerPurchaseHistoryPanel(), "Customer Purchase History");
        cardPanel.add(createSellerListingSortPanel(), "Seller Listing Sort");
        cardPanel.add(createLogOutPanel(), "Log Out");
        cardPanel.add(createProductPanel(), "Create Product");


        frame.add(cardPanel);

        cardLayout.show(cardPanel, "Main Page");
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }

    //method that creates the panel for the main page
    private JPanel createMainPanel() {
        frame.setSize(400, 500);
        frame.setResizable(false);
        JPanel mainPagePanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        mainPagePanel.setLayout(new BoxLayout(mainPagePanel, BoxLayout.Y_AXIS));

        JLabel applicationLabel = new JLabel("Boiler Bay");
        applicationLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        applicationLabel.setFont(new Font("Arial", Font.BOLD, 24));

        JButton switchToAccountPanelButton = new JButton("Accounts");
        switchToAccountPanelButton.setFont(new Font("Arial", Font.PLAIN, 10));
        switchToAccountPanelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "Account Options");
            }
        });

        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.X_AXIS));
        topPanel.setAlignmentY(Component.CENTER_ALIGNMENT);
        topPanel.add(Box.createHorizontalStrut(130));
        topPanel.add(applicationLabel);
        topPanel.add(Box.createHorizontalStrut(20));
        topPanel.add(switchToAccountPanelButton);

        JButton searchButton = new JButton("Search");
        searchButton.setFont(new Font("Arial", Font.PLAIN, 18));
        searchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "No products were found.",
                        "Seller", JOptionPane.ERROR_MESSAGE);
            }
        });

        JTextField searchField = new JTextField();
        searchField.setPreferredSize(new Dimension(300, 30));

        JPanel searchPanel = new JPanel(new FlowLayout());
        Dimension searchPanelDimension = new Dimension(400, 40);
        searchPanel.setPreferredSize(searchPanelDimension);
        searchPanel.add(searchField);
        searchPanel.add(searchButton);

        JButton listStoreButton = createButton("List\nStore");
        JButton createProductButton = createButton("Create\nProduct");
        createProductButton.addActionListener(e -> {
            cardLayout.show(cardPanel, "Create Product");
//            frame.setResizable(true);
            frame.setSize(400, 300);
        });
        JButton createStoreButton = createButton("Create\nStore");
        JButton storeStatisticsButton = createButton("Store\nStatistics");
        JButton editProductButton = createButton("Edit\nProduct");
        JButton editStoreButton = createButton("Edit\nStore");
        editStoreButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "Edit Store");
                frame.setSize(400, 500);
            }
        });

        JButton messageCustomerButton = new JButton("Message Customer");
        messageCustomerButton.setPreferredSize(new Dimension(310, 35));
        messageCustomerButton.setFont(new Font("Arial", Font.PLAIN, 16));
        messageCustomerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Add your action for the "Message Customer" button here
                cardLayout.show(cardPanel, "Contact Customers");  // Show the "Contact Customers" panel
                frame.setSize(400, 500);  // Set the frame size accordingly
                frame.setLocationRelativeTo(null);  // Center the frame
            }
        });

        JPanel buttonPanel = new JPanel(new FlowLayout());
        Dimension buttonPanelDimension = new Dimension(400, 80);
        buttonPanel.setPreferredSize(buttonPanelDimension);
        buttonPanel.add(listStoreButton);
        buttonPanel.add(createProductButton);
        buttonPanel.add(createStoreButton);
        buttonPanel.add(storeStatisticsButton);
        buttonPanel.add(editProductButton);
        buttonPanel.add(editStoreButton);
        buttonPanel.add(messageCustomerButton);

        topPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, topPanel.getPreferredSize().height + 40));
        searchPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, searchPanel.getPreferredSize().height + 50));
        buttonPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, buttonPanel.getPreferredSize().height + 250));


        mainPagePanel.add(Box.createVerticalStrut(20));
        mainPagePanel.add(topPanel);
        mainPagePanel.add(searchPanel);
        mainPagePanel.add(Box.createVerticalStrut(10));
        mainPagePanel.add(buttonPanel);
        mainPagePanel.add(Box.createVerticalStrut(20));

        return mainPagePanel;
    }

    private JButton createButton(String buttonText) {
        Dimension buttonDimension = new Dimension(100, 100);
        JButton button = new JButton("<html><center>" + buttonText.replaceAll("\n", "<br>") + "</center></html>");
        button.setPreferredSize(buttonDimension);
        button.setFont(new Font("Arial", Font.PLAIN, 16));
        // Additional customization or actions can be added here if needed
        return button;
    }

    //method that creates the panel for the account page
    private JPanel createAccountPanel() {
        frame.setSize(400, 500);
        JPanel createAccountPanel = new JPanel();
        createAccountPanel.setLayout(new BoxLayout(createAccountPanel, BoxLayout.Y_AXIS));

        JLabel titleLabel = new JLabel("Seller Name's Account");
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));


        JPanel topPanel = new JPanel();
        Dimension topPanelDimension = new Dimension(400, 20);
        //topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.X_AXIS));
        topPanel.setPreferredSize(topPanelDimension);
        topPanel.setAlignmentY(Component.CENTER_ALIGNMENT);
        topPanel.add(createBackToMenuButton());
        topPanel.add(Box.createHorizontalStrut(10));
        topPanel.add(titleLabel);
        topPanel.add(Box.createHorizontalStrut(10));
        //topPanel.add(logOutButton);

        Dimension buttonDimension = new Dimension(300, 50);
        JButton shoppingCartButton = new JButton(("View Shopping Cart"));
        shoppingCartButton.setPreferredSize(buttonDimension);
        shoppingCartButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        shoppingCartButton.setFont(new Font("Arial", Font.PLAIN, 18));

        JButton purchaseHistoryButton = new JButton(("My Purchase History"));
        purchaseHistoryButton.setPreferredSize(buttonDimension);
        purchaseHistoryButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        purchaseHistoryButton.setFont(new Font("Arial", Font.PLAIN, 18));

        //JButton deleteAccountButton = new JButton(("Delete Account"));
        //deleteAccountButton.setPreferredSize(buttonDimension);
        //deleteAccountButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        //deleteAccountButton.setFont(new Font("Arial", Font.PLAIN, 18));

        JButton logOutButton = new JButton(("Log Out"));
        logOutButton.setPreferredSize(buttonDimension);
        logOutButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        logOutButton.setFont(new Font("Arial", Font.PLAIN, 18));
        logOutButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "Log Out");
                frame.setSize(400, 200);
                frame.setLocationRelativeTo(null);
            }
        });


        createAccountPanel.add(Box.createVerticalStrut(20));
        createAccountPanel.add(topPanel);
        createAccountPanel.add(Box.createVerticalStrut(30));
        createAccountPanel.add(shoppingCartButton);
        createAccountPanel.add(Box.createVerticalStrut(30));
        createAccountPanel.add(purchaseHistoryButton);
        createAccountPanel.add(Box.createVerticalStrut(30));
        //createAccountPanel.add(deleteAccountButton);
        createAccountPanel.add(Box.createVerticalStrut(80));
        createAccountPanel.add(logOutButton);
        createAccountPanel.add(Box.createVerticalStrut(20));
        return createAccountPanel;
    }

    //method that creates the panel for the search by .... page
    private JPanel createSearchByPanel() {
        JPanel createSearchByPanel = new JPanel();
        return createSearchByPanel;

    }

    //method that creates the panel for seller listings page
    private JPanel createEditStorePanel() {
        JPanel editStorePanel = new JPanel();
        editStorePanel.setLayout(new BoxLayout(editStorePanel, BoxLayout.Y_AXIS));

        // Back button
        JButton backButton2 = createBackToMenuButton();
        backButton2.setMaximumSize(new Dimension(70, 10));


        JLabel titleLabel = new JLabel("               Edit Store");
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));

        // Panel for back button and title
        JPanel titlePanel2 = new JPanel();
        titlePanel2.setLayout(new BorderLayout());  // Use BorderLayout for the title panel
        titlePanel2.add(backButton2, BorderLayout.WEST);  // Align the back button to the left
        titlePanel2.add(Box.createHorizontalStrut(10));  // Add space between back button and title
        titlePanel2.add(titleLabel, BorderLayout.CENTER);  // Center-align the title


        JPanel storeNamePanel = new JPanel();
        String[] dummyStores = new String[]{"Store One", "Store Two", "Store Three", "Store Four", "Store Five",
                "Store Six", "Store Seven", "Store Eight", "Store Nine", "Store Ten", "Store Eleven", "Store Twelve"};
        storeNamePanel.setLayout(new BoxLayout(storeNamePanel, BoxLayout.Y_AXIS));

        for (String name : dummyStores) {
            JLabel label = new JLabel(name);
            label.setAlignmentX(Component.CENTER_ALIGNMENT);
            label.setFont(new Font("Arial", Font.PLAIN, 18));

            label.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    // Handle click event
                    editStoreName = label.getText();
                    // Update the display with the selected store's name
                    selectedStoreLabel.setText("Selected Store: " + editStoreName);
                }
            });

            storeNamePanel.add(label);
            storeNamePanel.add(new JSeparator(JSeparator.HORIZONTAL));
        }

        // Remove the last separator to avoid an extra line at the end
        storeNamePanel.remove(storeNamePanel.getComponentCount() - 1);

        JScrollPane psp = new JScrollPane(storeNamePanel, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        // Display selected store's name
        psp.setPreferredSize(new Dimension(300, 320));
        selectedStoreLabel = new JLabel("Selected Store: ");
        selectedStoreLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        selectedStoreLabel.setFont(new Font("Arial", Font.PLAIN, 18));

        // Confirm button
        JButton confirmButton = new JButton("Confirm");
        confirmButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        confirmButton.setFont(new Font("Arial", Font.PLAIN, 18));
        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle confirmation action
                // You can add your logic here

//                System.out.println("Store Confirmed: " + editStoreName);
                cardPanel.add(createEditStoreDetailsPanel(), "Edit Store Details");
                cardLayout.show(cardPanel, "Edit Store Details");
                frame.setSize(400, 300);

            }
        });

        editStorePanel.setLayout(new BoxLayout(editStorePanel, BoxLayout.Y_AXIS));

        // Increase the height by adjusting the strut values
        editStorePanel.add(Box.createVerticalStrut(20));
        editStorePanel.add(titlePanel2);
        editStorePanel.add(Box.createVerticalStrut(20));
        editStorePanel.add(psp);
        editStorePanel.add(Box.createVerticalStrut(20));
        editStorePanel.add(selectedStoreLabel);
        editStorePanel.add(Box.createVerticalStrut(10));
        editStorePanel.add(confirmButton);

        return editStorePanel;
    }


    // method that creates the panel for the product listings page
    private JPanel createProductListingsPanel() {
        JPanel productsListingPanel = new JPanel();
        return productsListingPanel;
    }

    // method that creates panel for the individual product page
    private JPanel createIndividualProductPanel() {
        JPanel individualProductPanel = new JPanel();
        return individualProductPanel;
    }

    // method that creates panel for the store listings page
    private JPanel createStoreListingsPagePanel() {
        JPanel storeListingsPanel = new JPanel();
        return storeListingsPanel;
    }

    // method that creates panel for the store listings page
    private JPanel createIndividualStoreListingsPanel() {
        JPanel individualStorePanel = new JPanel();
        return individualStorePanel;
    }

    // method that creates panel for the customer shopping cart page
    private JPanel createCustomerShoppingCartPanel() {
        JPanel customerShoppingCartPanel = new JPanel();
        return customerShoppingCartPanel;
    }

    // method that creates panel for the customer's purchase history page
    private JPanel createCustomerPurchaseHistoryPanel() {
        JPanel customerPurchaseHistoryPanel = new JPanel();
        return customerPurchaseHistoryPanel;
    }

    private JPanel createLogOutPanel() {
        JPanel logOutPanel = new JPanel();
        logOutPanel.setLayout(new BoxLayout(logOutPanel, BoxLayout.Y_AXIS));

        JLabel confirmLabel = new JLabel("     Are you sure you want to log out?     ");
        //Border border = BorderFactory.createRaisedBevelBorder();
        //confirmLabel.setBorder(border);
        confirmLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        confirmLabel.setFont(new Font("Arial", Font.BOLD, 18));

        Dimension buttonDimension = new Dimension(100, 50);
        Dimension labelDimension = new Dimension(50, 50);
        JButton noButton = new JButton("No");
        noButton.setFont(new Font("Arial", Font.PLAIN, 18));
        noButton.setPreferredSize(buttonDimension);
        noButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "Account Options");
                frame.setSize(400, 500);
                frame.setLocationRelativeTo(null);
            }
        });

        JButton yesButton = new JButton("Yes");
        yesButton.setFont(new Font("Arial", Font.PLAIN, 18));
        yesButton.setPreferredSize(buttonDimension);
        yesButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new AccountsPanel().display();
            }
        });


        JLabel fillerLabel = new JLabel();
        fillerLabel.setPreferredSize(labelDimension);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setPreferredSize(new Dimension(200, 100));
        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.add(yesButton);
        buttonPanel.add(fillerLabel);
        buttonPanel.add(noButton);

        logOutPanel.add(Box.createVerticalStrut(20));
        logOutPanel.add(confirmLabel);
        logOutPanel.add(Box.createVerticalStrut(20));
        logOutPanel.add(buttonPanel);
        return logOutPanel;
    }

    private JPanel createSellerListingSortPanel() {

        JPanel sellerListingSortPanel = new JPanel();
        sellerListingSortPanel.setLayout(new BoxLayout(sellerListingSortPanel, BoxLayout.Y_AXIS));

        JLabel questionLabel = new JLabel("What would you like to do?");
        questionLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        questionLabel.setFont(new Font("Arial", Font.BOLD, 18));

        Dimension buttonDimension = new Dimension(300, 40);
        JButton sellerListingButton = new JButton("Show All Sellers");
        sellerListingButton.setPreferredSize(buttonDimension);
        sellerListingButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        sellerListingButton.setFont(new Font("Arial", Font.PLAIN, 18));
        sellerListingButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "Seller Listings");
                frame.setSize(400, 500);
                frame.setLocationRelativeTo(null);
            }
        });

        JButton contactSellersButton = new JButton("Contact Sellers");
        contactSellersButton.setPreferredSize(buttonDimension);
        contactSellersButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        contactSellersButton.setFont(new Font("Arial", Font.PLAIN, 18));
        contactSellersButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "Contact Sellers");
                frame.setSize(400, 500);
                frame.setLocationRelativeTo(null);
            }
        });
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.X_AXIS));
        bottomPanel.add(createBackToMenuButton());
        bottomPanel.add(Box.createHorizontalStrut(300));

        sellerListingSortPanel.add(Box.createVerticalStrut(10));
        sellerListingSortPanel.add(questionLabel);
        sellerListingSortPanel.add(Box.createVerticalStrut(5));
        sellerListingSortPanel.add(sellerListingButton);
        sellerListingSortPanel.add(Box.createVerticalStrut(5));
        sellerListingSortPanel.add(contactSellersButton);
        sellerListingSortPanel.add(bottomPanel);
        return sellerListingSortPanel;
    }

    private String editCustName;  // Variable to store the selected seller's name

    private JPanel createContactCustomerPanel() {
        JPanel contactSellerPanel = new JPanel();
        contactSellerPanel.setLayout(new BoxLayout(contactSellerPanel, BoxLayout.Y_AXIS));

        // Back button
        JButton backButton = createBackToMenuButton();

        JLabel titleLabel = new JLabel("        Message a Customer");
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));

        // Panel for back button and title
        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(new BorderLayout());  // Use BorderLayout for the title panel
        titlePanel.add(backButton, BorderLayout.WEST);  // Align the back button to the left
        titlePanel.add(Box.createHorizontalStrut(20));  // Add space between back button and title
        titlePanel.add(titleLabel, BorderLayout.CENTER);  // Center-align the title

        JPanel customerNamePanel = new JPanel();
        String[] dummyCustomers = new String[]{"Customer One", "Customer Two", "Customer Three", "Customer Four", "Customer Five",
                "Customer Six", "Customer Seven", "Customer Eight", "Customer Nine", "Customer Ten", "Customer Eleven", "Customer Twelve"};
        customerNamePanel.setLayout(new BoxLayout(customerNamePanel, BoxLayout.Y_AXIS));
        for (String name : dummyCustomers) {
            JLabel label = new JLabel(name);
            label.setAlignmentX(Component.CENTER_ALIGNMENT);
            label.setFont(new Font("Arial", Font.PLAIN, 18));

            label.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    // Handle click event
                    editCustName = label.getText();
                    // Update the display with the selected seller's name
                    selectedCustomerLabel.setText("Selected Customer: " + editCustName);
                }
            });

            customerNamePanel.add(label);
            customerNamePanel.add(new JSeparator(JSeparator.HORIZONTAL));
        }

        // Remove the last separator to avoid an extra line at the end
        customerNamePanel.remove(customerNamePanel.getComponentCount() - 1);

        JScrollPane jsp = new JScrollPane(customerNamePanel, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        // Display selected seller's name
        jsp.setPreferredSize(new Dimension(300, 300));
        selectedCustomerLabel = new JLabel("Selected Customer: ");
        selectedCustomerLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        selectedCustomerLabel.setFont(new Font("Arial", Font.PLAIN, 18));

        // Container for the message components
        JPanel messagePanel = new JPanel();
        messagePanel.setLayout(new BoxLayout(messagePanel, BoxLayout.Y_AXIS));

        // Label above JTextArea
        JLabel messageLabel = new JLabel("What's your message?");
        messageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        messageLabel.setFont(new Font("Arial", Font.PLAIN, 14));

        // JTextArea for entering the message
        JTextArea messageTextArea = new JTextArea();
        messageTextArea.setLineWrap(true);
        messageTextArea.setWrapStyleWord(true);
        JScrollPane messageScrollPane = new JScrollPane(messageTextArea);
        messageScrollPane.setPreferredSize(new Dimension(250, 80));

        // Button to send the message
        JButton sendMessageButton = new JButton("Send Message");
        sendMessageButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        sendMessageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle sending the message
                String message = messageTextArea.getText();
                // You can use the message as needed
                System.out.println("Sending message to " + editCustName + ": " + message);

                // Save the message to file
                // Clear the message text area
                messageTextArea.setText("");
            }
        });

        messagePanel.add(messageLabel);
        messagePanel.add(messageScrollPane);
        messagePanel.add(sendMessageButton);

        contactSellerPanel.setLayout(new BoxLayout(contactSellerPanel, BoxLayout.Y_AXIS));

        contactSellerPanel.add(Box.createVerticalStrut(20));
        contactSellerPanel.add(titlePanel);
        contactSellerPanel.add(Box.createVerticalStrut(20));
        contactSellerPanel.add(jsp);
        contactSellerPanel.add(Box.createVerticalStrut(20));
        contactSellerPanel.add(selectedCustomerLabel);
        contactSellerPanel.add(Box.createVerticalStrut(10));
        contactSellerPanel.add(messagePanel);

        return contactSellerPanel;

    }

    private JPanel createEditStoreDetailsPanel() {
//        frame.setResizable(true);
        JPanel editStoreDetailsPanel = new JPanel();
        editStoreDetailsPanel.setLayout(new BoxLayout(editStoreDetailsPanel, BoxLayout.Y_AXIS));

        // Back button
        JButton backButton = createBackToEditStoreButton();
        String storeText = "    Edit " + editStoreName + "'s Panel";
        JLabel titleLabel = new JLabel(storeText);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));

        // Panel for back button and title
        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(new BorderLayout());
        titlePanel.add(backButton, BorderLayout.WEST);
        titlePanel.add(Box.createHorizontalStrut(20));
        titlePanel.add(titleLabel, BorderLayout.CENTER);

        // Buttons for various actions
        JButton addProductButton = createButton("Add Product");
        addProductButton.addActionListener(e -> {
            cardLayout.show(cardPanel, "Create Product");
            frame.setSize(400, 350);
        });


        JButton csvImportButton = createButton("CSV Import");
        csvImportButton.addActionListener(e -> handleCSVImport());

        JButton deleteStoreButton = createButton("Delete Store");
        deleteStoreButton.addActionListener(e -> {
            int dialogResult = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete the store: " + editStoreName + "?", "Confirmation", JOptionPane.YES_NO_OPTION);

            if (dialogResult == JOptionPane.YES_OPTION) {
                // Perform the store deletion logic here
                // For demonstration purposes, let's assume the store is deleted successfully
                boolean storeDeletedSuccessfully = true;

                if (storeDeletedSuccessfully) {
                    JOptionPane.showMessageDialog(null, editStoreName + " has been deleted successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
                    System.out.println("Store deleted: " + editStoreName);
                    // Add your deletion logic here
                } else {
                    JOptionPane.showMessageDialog(null, "Failed to delete the store.", "Error", JOptionPane.ERROR_MESSAGE);
                    System.out.println("Failed to delete the store: " + editStoreName);
                    // Add your error handling logic here
                }
            } else {
                System.out.println("Deletion canceled.");
            }
        });

        JButton changeStoreNameButton = createButton("Change Store Name");

        changeStoreNameButton.addActionListener(e -> {
            // Prompt user for the new store name
            JTextField newNameField = new JTextField();
            Object[] message = {"Enter new store name:", newNameField};

            int option = JOptionPane.showConfirmDialog(null, message, "Change Store Name", JOptionPane.OK_CANCEL_OPTION);

            if (option == JOptionPane.OK_OPTION) {
                // User clicked OK, get the new name
                String newName = newNameField.getText();

                // Prompt for confirmation
                int confirmation = JOptionPane.showConfirmDialog(null, "Are you sure you want to change the store name to \"" + newName + "\"?", "Confirmation", JOptionPane.YES_NO_OPTION);

                if (confirmation == JOptionPane.YES_OPTION) {
                    // Store name changed successfully
                    JOptionPane.showMessageDialog(null, "Name changed to \"" + newName + "\" successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
                    System.out.println("Store name changed to: " + newName);
                    // Add your logic to update the store name here
                } else {
                    // User canceled the name change
                    JOptionPane.showMessageDialog(null, "Name change canceled.", "Cancelled", JOptionPane.WARNING_MESSAGE);
                    System.out.println("Name change canceled.");
                }
            } else {
                // User canceled the new name entry
                System.out.println("New name entry canceled.");
            }
        });
        JButton editStoreProductsButton = createButton("Edit Store Products");

        // Action listeners for the buttons
        addProductButton.addActionListener(e -> System.out.println("Adding product to store: " + editStoreName));
        csvImportButton.addActionListener(e -> System.out.println("CSV Import for store: " + editStoreName));


        changeStoreNameButton.addActionListener(e -> System.out.println("Changing store name for: " + editStoreName));
        editStoreProductsButton.addActionListener(e -> System.out.println("Editing products for store: " + editStoreName));

        // Container for the buttons
        JPanel buttonPanel = new JPanel(new GridLayout(0, 1, 0, 2));  // 1 column, as many rows as needed, reduced vertical spacing
        buttonPanel.add(addProductButton);
        buttonPanel.add(csvImportButton);
        buttonPanel.add(deleteStoreButton);
        buttonPanel.add(changeStoreNameButton);
        buttonPanel.add(editStoreProductsButton);

        // Adding components to the panel
        editStoreDetailsPanel.add(Box.createVerticalStrut(10));
        editStoreDetailsPanel.add(titlePanel);
        editStoreDetailsPanel.add(buttonPanel);
        editStoreDetailsPanel.add(Box.createVerticalStrut(10));

        return editStoreDetailsPanel;
    }

    private void handleCSVImport() {
        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("CSV Files", "csv");
        fileChooser.setFileFilter(filter);

        int option = fileChooser.showOpenDialog(null);

        if (option == JFileChooser.APPROVE_OPTION) {
            // User selected a file
            String filePath = fileChooser.getSelectedFile().getAbsolutePath();
            System.out.println("Selected file: " + filePath);

            // Add your logic to handle the CSV file import
            // For example, you can pass the filePath to a method that reads and processes the CSV file
            processCSVFile(filePath);
        } else {
            // User canceled file selection
            System.out.println("CSV import canceled.");
        }
    }

    private void processCSVFile(String filePath) {
        // Implement your logic to read and process the CSV file
        System.out.println("Processing CSV file: " + filePath);
        // Your CSV file processing logic here
    }

    public JPanel createSellerPanel() {
        JPanel sellerPanel = new JPanel();
        return sellerPanel;
    }


    private JPanel createProductPanel() {
        JPanel createProductPanel = new JPanel();
        createProductPanel.setLayout(new BoxLayout(createProductPanel, BoxLayout.Y_AXIS));

        // Title
        JLabel titleLabel = new JLabel("      Create Product");
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));

        // Back to Menu Button
        JButton backToMenuButton = createBackToMenuButton();

        // Top Panel
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.X_AXIS));
        topPanel.add(backToMenuButton);
        topPanel.add(Box.createHorizontalStrut(10));
        topPanel.add(titleLabel);
        topPanel.add(Box.createHorizontalGlue()); // Add glue to push the title to the right

        createProductPanel.add(topPanel);
        createProductPanel.add(Box.createVerticalStrut(20));

        // Form Panel
        JPanel formPanel = new JPanel(new GridLayout(6, 1, 10, 10));

        // Name
        JTextField nameTextField = new JTextField(20);
        formPanel.add(createFieldWithLabel("Name:", nameTextField));

        // Description
        JTextArea descriptionTextArea = new JTextArea(8, 20); // Increase the number of rows
        JScrollPane descriptionScrollPane = new JScrollPane(descriptionTextArea);
        descriptionScrollPane.setPreferredSize(new Dimension(250, 120)); // Adjust the size
        formPanel.add(createFieldWithLabel("Description:", descriptionScrollPane));

        // Price
        DoubleSpinner priceSpinner = createDoubleSpinner();
        formPanel.add(createFieldWithLabel("Price:", priceSpinner));

        // Quantity
        SpinnerModel quantityModel = new SpinnerNumberModel(1, 1, Integer.MAX_VALUE, 1);
        JSpinner quantitySpinner = new JSpinner(quantityModel);
        formPanel.add(createFieldWithLabel("Quantity:", quantitySpinner));

        // Store Dropdown
        String[] storeOptions = new String[]{"Store One", "Store Two", "Store Three", "Store Four", "Store Five",
                "Store Six", "Store Seven", "Store Eight", "Store Nine", "Store Ten", "Store Eleven", "Store Twelve"};
        JComboBox<String> storeComboBox = new JComboBox<>(storeOptions);
        formPanel.add(createFieldWithLabel("Store:", storeComboBox));

        // Create Product Button
        JButton createProductButton = new JButton("Create Product");
        createProductButton.addActionListener(e -> {
            // Handle the creation of the product here
            String name = nameTextField.getText().trim();
            String description = descriptionTextArea.getText().trim();

            // Validate Name
            if (name.isEmpty()) {
                JOptionPane.showMessageDialog(createProductPanel, "Name cannot be empty", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Validate Description
            if (description.isEmpty()) {
                JOptionPane.showMessageDialog(createProductPanel, "Description cannot be empty", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Validate Price
            double price;
            try {
                price = ((Double) priceSpinner.getValue()).doubleValue();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(createProductPanel, "Invalid price format", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Validate Quantity
            int quantity;
            try {
                quantity = (int) quantitySpinner.getValue();
                if (quantity < 1) {
                    JOptionPane.showMessageDialog(createProductPanel, "Quantity must be at least 1", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(createProductPanel, "Invalid quantity format", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Process creation
            String selectedStore = (String) storeComboBox.getSelectedItem();
            System.out.println("Product created:");
            System.out.println("Name: " + name);
            System.out.println("Description: " + description);
            System.out.println("Price: " + price);
            System.out.println("Quantity: " + quantity);
            System.out.println("Store: " + selectedStore);
        });

        // Add components to the form panel
        formPanel.add(createProductButton);
        createProductPanel.add(formPanel);

        return createProductPanel;
    }






    private static JPanel createFieldWithLabel(String labelText, JComponent field) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
        JLabel label = new JLabel(labelText);
        label.setFont(new Font("Arial", Font.PLAIN, 18));
        label.setPreferredSize(new Dimension(100, 20));
        panel.add(label);
        panel.add(field);
        return panel;
    }

    private static DoubleSpinner createDoubleSpinner() {
        JSpinner doubleSpinner = new JSpinner();

        // Model setup with step size set to 0.5
        SpinnerNumberModel model = new SpinnerNumberModel(0.0, 0.0, 1000.0, 0.5);
        doubleSpinner.setModel(model);

        // Step recalculation
        doubleSpinner.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                // Set the step size directly to 0.5
                model.setStepSize(0.5);
            }
        });

        return new DoubleSpinner(doubleSpinner, model);
    }



    private static Double getDouble(JSpinner doubleSpinner) {
        return (Double) doubleSpinner.getValue();
    }

    private static class DoubleSpinner extends JSpinner {

        private static final long serialVersionUID = 1L;

        private DoubleSpinner(JSpinner spinner, SpinnerNumberModel model) {
            super(model);
            this.setModel(model);
        }
    }


    private JLabel selectedCustomerLabel;
    private JLabel selectedStoreLabel;

    private JButton createBackToMenuButton() {
        JButton backButton = new JButton("<");
        Font largeFont = new Font("Arial", Font.PLAIN, 18);
        backButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        backButton.setFont(largeFont);
//        Dimension buttonSize = new Dimension(70, 10);
//        backButton.setPreferredSize(buttonSize);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.first(cardPanel);
                frame.setSize(400, 500);
                frame.setLocationRelativeTo(null);
            }
        });
        return backButton;
    }

    private JButton createBackToEditStoreButton() {
        JButton backButton = new JButton("<");
        Font largeFont = new Font("Arial", Font.PLAIN, 18);
        backButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        backButton.setFont(largeFont);
//        Dimension buttonSize = new Dimension(70, 10);
//        backButton.setPreferredSize(buttonSize);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "Edit Store");
                frame.setSize(400, 500);
                frame.setLocationRelativeTo(null);
            }
        });
        return backButton;
    }
}



