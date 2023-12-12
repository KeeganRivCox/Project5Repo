import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;
import java.awt.*;
import java.awt.event.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class SellerPanel {

    String userEmail;


    private final JFrame frame;
    private final JPanel cardPanel;

    private Store selectedStore;

    private boolean editStoreFlag;

    private Product selectedProduct;
    private String customerMessage;
    private JTextArea selectedCustomerMessageLabel;
    private final CardLayout cardLayout;
    private String customerName;
    private String productName;
    private String specificStoreName;
    private String productInShoppingCartName;
    private JLabel selectedCustomerLabel;
    private JLabel selectedStoreLabel;
    private JLabel selectedStoreStatsLabel;
    private JLabel selectedCustomerStatsLabel;
    private JLabel selectedProductStatsLabel;

    private JLabel selectedProductLabel;
    private JLabel selectedAllStoreLabel;
    private JLabel selectedProductInShoppingCartLabel;

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
    private JPanel mainSellerPanel;
    // Purdue Color formatting
    final Color goldColor = new Color(206, 184, 136);
    final Color greyButtonColor = new Color(196, 191, 192);
    final Border customBorder = BorderFactory.createLineBorder(Color.BLACK, 3);


    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new SellerPanel("cox442@purdue.edu"));

    }

    public SellerPanel(String userEmail) {
        this.userEmail = userEmail;

        frame = new JFrame("Sellers");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);
        cardPanel.add(createMainPanel(), "Main Page");
        // In progress...
//        cardPanel.add(createEditStorePanel(), "Edit Store");
//        cardPanel.add(createListStorePanel(), "List Store");
//        cardPanel.add(createContactCustomerPanel(), "Contact Customers");
//        cardPanel.add(createProductListingsPanel(), "Product Listings");
//        cardPanel.add(createStorePanel(), "Create Store");
//        cardPanel.add(createIndividualProductPanel(), "Individual Products");
//        cardPanel.add(createLogOutPanel(), "Log Out");
//        cardPanel.add(createProductPanel(), "Create Product");
//        cardPanel.add(createStoreStatisticsPanel(), "Store Statistics");
//        cardPanel.add(createViewAllStoreStatisticsPanel(), "View All Store Sales");
//        cardPanel.add(createViewSpecificStoreStatisticsPanel(), "View Specific Store Sales");
//        cardPanel.add(createViewCustomerShoppingCarts(), "View Customer's Shopping Carts");
//        cardPanel.add(createSellerInboxPanel(), "View Seller Inbox");

        frame.add(cardPanel);

        cardLayout.show(cardPanel, "Main Page");
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }

    //method that creates the panel for the main page

    // Keegan - Server implementation: in progress..
    private JPanel createMainPanel() {
        frame.setSize(400, 500);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        JPanel mainPagePanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        mainPagePanel.setLayout(new BoxLayout(mainPagePanel, BoxLayout.Y_AXIS));
        mainPagePanel.setBackground(goldColor);

        ImageIcon imageIcon = new ImageIcon("images/boilerBayLogo.png");
        JLabel imageLabel = new JLabel();
        imageLabel.setMinimumSize(new Dimension(100, 100));
        imageLabel.setMaximumSize(new Dimension(100, 100));
        imageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        imageLabel.setIcon(new ImageIcon(imageIcon.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT)));

//        JLabel applicationLabel = new JLabel("Boiler Bay");
//        applicationLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
//        applicationLabel.setFont(new Font("Arial", Font.BOLD, 24));

        JButton logOutButton = new JButton("Log Out");
        logOutButton.setMinimumSize(new Dimension(70, 30));
        logOutButton.setMaximumSize(new Dimension(70, 30));
        logOutButton.setFont(new Font("Arial", Font.PLAIN, 14));
        logOutButton.setBackground(greyButtonColor);
        logOutButton.setForeground(Color.BLACK);
        logOutButton.setBorder(customBorder);        logOutButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardPanel.removeAll();
                cardPanel.add(createLogOutPanel(), "Log Out");
                cardLayout.show(cardPanel, "Log Out");
            }
        });

        JPanel topPanel = new JPanel();
        topPanel.setOpaque(false);
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.X_AXIS));
        topPanel.setAlignmentY(Component.CENTER_ALIGNMENT);
        topPanel.add(Box.createHorizontalStrut(140));
        topPanel.add(imageLabel);
        topPanel.add(Box.createHorizontalStrut(40));
        topPanel.add(logOutButton);

        JLabel toDoLabel = new JLabel("What would you like to do?");
        toDoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        toDoLabel.setFont(new Font("Arial", Font.BOLD, 16));

        JButton listStoreButton = createButton("List\nStores");
        listStoreButton.setBackground(greyButtonColor);
        listStoreButton.setForeground(Color.BLACK);
        listStoreButton.setBorder(customBorder);
        listStoreButton.addActionListener(e -> {
//            cardPanel.removeAll();
            cardPanel.add(createListStorePanel(), "List Store");
            cardLayout.show(cardPanel, "List Store");
        });

        JButton createProductButton = createButton("Create\nProduct");
        createProductButton.setBackground(greyButtonColor);
        createProductButton.setForeground(Color.BLACK);
        createProductButton.setBorder(customBorder);
        createProductButton.addActionListener(e -> {
            cardPanel.removeAll();
            cardPanel.add(createProductPanel(), "Create Product");
            cardLayout.show(cardPanel, "Create Product");
        });

        JButton createStoreButton = createButton("Create\nStore");
        createStoreButton.setBackground(greyButtonColor);
        createStoreButton.setForeground(Color.BLACK);
        createStoreButton.setBorder(customBorder);
        createStoreButton.addActionListener(e -> {
            cardPanel.removeAll();
            cardPanel.add(createStorePanel(), "Create Store");
            cardLayout.show(cardPanel, "Create Store");
        });

        JButton storeStatisticsButton = createButton("Store\nStatistics");
        storeStatisticsButton.setBackground(greyButtonColor);
        storeStatisticsButton.setForeground(Color.BLACK);
        storeStatisticsButton.setBorder(customBorder);
        storeStatisticsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardPanel.removeAll();
                cardPanel.add(createViewSpecificStoreStatisticsPanel(), "Store Statistics");
                cardLayout.show(cardPanel, "Store Statistics");

            }
        });

        JButton editProductButton = createButton("Edit\nProduct");
        editProductButton.setBackground(greyButtonColor);
        editProductButton.setForeground(Color.BLACK);
        editProductButton.setBorder(customBorder);
        editProductButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardPanel.add(createEditStorePanel(), "Edit Store");
                cardLayout.show(cardPanel, "Edit Store");
                editStoreFlag = true;
            }
        });

        JButton editStoreButton = createButton("Edit\nStore");
        editStoreButton.setBackground(greyButtonColor);
        editStoreButton.setForeground(Color.BLACK);
        editStoreButton.setBorder(customBorder);
        editStoreButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardPanel.add(createEditStorePanel(), "Edit Store");
                cardLayout.show(cardPanel, "Edit Store");

            }
        });

        JButton messageCustomerButton = new JButton("Message Customer");
        messageCustomerButton.setPreferredSize(new Dimension(310, 35));
        messageCustomerButton.setFont(new Font("Arial", Font.PLAIN, 16));
        messageCustomerButton.setBackground(greyButtonColor);
        messageCustomerButton.setForeground(Color.BLACK);
        messageCustomerButton.setBorder(customBorder);
        messageCustomerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Add your action for the "Message Customer" button here
                cardPanel.add(createContactCustomerPanel(), "Contact Customers");
                cardLayout.show(cardPanel, "Contact Customers");  // Show the "Contact Customers" panel
            }
        });

        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.setOpaque(false);
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
//        searchPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, searchPanel.getPreferredSize().height + 50));
        buttonPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, buttonPanel.getPreferredSize().height + 250));

        mainPagePanel.add(Box.createVerticalStrut(10));
        mainPagePanel.add(topPanel);
        mainPagePanel.add(toDoLabel);
        mainPagePanel.add(Box.createVerticalStrut(20));
        mainPagePanel.add(buttonPanel);
        mainPagePanel.add(Box.createVerticalStrut(20));
        mainSellerPanel = mainPagePanel;

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
                cardLayout.show(cardPanel, "Main Page");
                frame.setSize(400,500);
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


    //method that creates the panel for the search by .... page
    private JPanel createSearchByPanel() {
        JPanel createSearchByPanel = new JPanel();
        return createSearchByPanel;

    }


    private JPanel createListStorePanel() {
        frame.setSize(400, 400);
        frame.setLocationRelativeTo(null);
        JPanel listStorePanel = new JPanel();
        listStorePanel.setLayout(new BoxLayout(listStorePanel, BoxLayout.Y_AXIS));

        // Back button
        JButton backButton2 = createBackToMenuButton();
        backButton2.setMaximumSize(new Dimension(50, 30));

        JLabel titleLabel = new JLabel("List of Stores");
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));

        // Panel for back button and title
        JPanel topPanel = new JPanel();
        topPanel.setMaximumSize(new Dimension(400, 75));
        topPanel.setMinimumSize(new Dimension(400, 75));
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.X_AXIS));
        topPanel.add(Box.createHorizontalStrut(40));
        topPanel.add(backButton2);
        topPanel.add(Box.createHorizontalStrut(40));
        topPanel.add(titleLabel);

        // Increase the size of the JScrollPane
        Dimension scrollPaneDimension = new Dimension(320, 320); // Increase the height to 400 or your desired value

        ArrayList<Store> sellerStores = new Request().getSeller(userEmail).getSellerStores();

        // Create a JPanel to hold vertically listed JLabels
        JPanel storeNamePanel = new JPanel();
        storeNamePanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        storeNamePanel.setLayout(new BoxLayout(storeNamePanel, BoxLayout.Y_AXIS));

        // Create helper panel to help center store labels
        JPanel helperPanel = new JPanel();
        helperPanel.setLayout(new BorderLayout());

        for (Store store : sellerStores) {
            JLabel storeLabel = new JLabel(store.getName());
            storeLabel.setFont(new Font("Arial", Font.PLAIN, 18));
            storeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            storeNamePanel.add(storeLabel);
            storeNamePanel.add(Box.createVerticalStrut(1));
            storeNamePanel.add(new JSeparator(SwingConstants.HORIZONTAL));
        }

        if (!sellerStores.isEmpty()) {
            storeNamePanel.remove(storeNamePanel.getComponentCount() - 1);
        }

        helperPanel.add(storeNamePanel, BorderLayout.NORTH);

        JScrollPane psp = new JScrollPane(helperPanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        psp.setPreferredSize(scrollPaneDimension);
        psp.setMaximumSize(scrollPaneDimension);

        listStorePanel.setLayout(new BoxLayout(listStorePanel, BoxLayout.Y_AXIS));

        // Increase the height by adjusting the strut values
        listStorePanel.add(Box.createVerticalStrut(20));
        listStorePanel.add(topPanel);
        listStorePanel.add(Box.createVerticalStrut(20));
        listStorePanel.add(psp);
        listStorePanel.add(Box.createVerticalStrut(20));

        return listStorePanel;
    }






    //method that creates the panel for seller listings page
    private JPanel createEditStorePanel() {
        selectedStore = null;
        JPanel editStorePanel = new JPanel();
        editStorePanel.setLayout(new BoxLayout(editStorePanel, BoxLayout.Y_AXIS));

        // Back button
        JButton backButton2 = createBackToMenuButton();
        backButton2.setMaximumSize(new Dimension(50, 30));

        JLabel titleLabel = new JLabel("Choose a Store");
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));

        // Panel for back button and title
        JPanel topPanel = new JPanel();
        topPanel.setMaximumSize(new Dimension(400, 75));
        topPanel.setMinimumSize(new Dimension(400, 75));
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.X_AXIS));
        topPanel.add(Box.createHorizontalStrut(40));
        topPanel.add(backButton2);
        topPanel.add(Box.createHorizontalStrut(30));
        topPanel.add(titleLabel);

        JPanel storeNamePanel = new JPanel();
        ArrayList<Store> sellerStores = new Request().getSeller(userEmail).getSellerStores();
        storeNamePanel.setLayout(new BoxLayout(storeNamePanel, BoxLayout.Y_AXIS));

        // Display selected store's name
        selectedStoreLabel = new JLabel("Selected Store: ");
        selectedStoreLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        selectedStoreLabel.setFont(new Font("Arial", Font.PLAIN, 18));

        for (Store store : sellerStores) {
            JLabel label = new JLabel(store.getName());
            label.setAlignmentX(Component.CENTER_ALIGNMENT);
            label.setFont(new Font("Arial", Font.PLAIN, 18));

            label.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    // Handle click event
                    selectedStore = store;
//
                    selectedStoreLabel.setText("Selected Store: " + selectedStore.getName());

                }
            });

            storeNamePanel.add(label);
            storeNamePanel.add(new JSeparator(JSeparator.HORIZONTAL));
        }

        // Remove the last separator to avoid an extra line at the end
        if (!sellerStores.isEmpty()) {
            storeNamePanel.remove(storeNamePanel.getComponentCount() - 1);
        }

        JScrollPane psp = new JScrollPane(storeNamePanel, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        psp.setPreferredSize(new Dimension(300, 320));


        // Confirm button

        JButton confirmButton = new JButton("Confirm");
        confirmButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        confirmButton.setFont(new Font("Arial", Font.PLAIN, 18));
        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (selectedStore == null) {
                    JOptionPane.showMessageDialog(createEditStorePanel(), "Please select a Store first.", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    if (editStoreFlag) {
                        cardPanel.add(createEditProductPanel(), "Edit Product");
                        cardLayout.show(cardPanel, "Edit Product");
                        frame.setSize(400, 500);
                        editStoreFlag = false;
                    } else {
                        cardPanel.add(createEditStoreDetailsPanel(), "Edit Store Details");
                        cardLayout.show(cardPanel, "Edit Store Details");
                        frame.setSize(400, 300);
                    }
                }
            }
        });

        editStorePanel.setLayout(new BoxLayout(editStorePanel, BoxLayout.Y_AXIS));

        // Increase the height by adjusting the strut values
        editStorePanel.add(Box.createVerticalStrut(20));
        editStorePanel.add(topPanel);
        editStorePanel.add(Box.createVerticalStrut(20));
        editStorePanel.add(psp);
        editStorePanel.add(Box.createVerticalStrut(20));
        editStorePanel.add(selectedStoreLabel);
        editStorePanel.add(Box.createVerticalStrut(20));
        editStorePanel.add(confirmButton);
        editStorePanel.add(Box.createVerticalStrut(20));

        return editStorePanel;
    }

    private JPanel createStorePanel() {
        JPanel createStorePanel = new JPanel();
        createStorePanel.setLayout(new BoxLayout(createStorePanel, BoxLayout.Y_AXIS));

        // Title
        JLabel titleLabel = new JLabel("Create Store");
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));

        // Back to Menu Button
        JButton backToMenuButton = createBackToMenuButton();

        // Top Panel
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.X_AXIS));
        topPanel.add(backToMenuButton);
        topPanel.add(Box.createHorizontalStrut(90));
        topPanel.add(titleLabel);
        topPanel.add(Box.createHorizontalGlue());

        createStorePanel.add(topPanel);
        createStorePanel.add(Box.createVerticalStrut(20));

        // Form Panel
        JPanel formPanel = new JPanel(new GridLayout(2, 1, 10, 10));

        // Name
        JTextField nameTextField = new JTextField(20);

        formPanel.add(createFieldWithLabel("Name:", nameTextField));

        // Create Store Button
        JButton createStoreButton = new JButton("Create Store");
        createStoreButton.setFont(new Font("Arial", Font.PLAIN, 16));
        createStoreButton.addActionListener(e -> {
            // Handle the creation of the store here
            String name = nameTextField.getText().trim();

            // Validate Name
            if (name.isEmpty()) {
                JOptionPane.showMessageDialog(createStorePanel, "Name cannot be empty", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Seller currentSeller = new Request().getSeller(userEmail);
            Store newStore = new Store(name, currentSeller, new ArrayList<>(), new ArrayList<>());
            currentSeller.getSellerStores().add(newStore);
            new Request().updateSeller(currentSeller);

            // Set the editStoreName
            selectedStore = newStore;

            // Open "Create Product" card
            cardPanel.removeAll();
            cardPanel.add(createProductPanel(), "Create Product");
            cardLayout.show(cardPanel, "Create Product");
//            frame.setResizable(true);
            frame.setSize(400, 300);
        });

        // Add components to the form panel
        formPanel.add(createStoreButton);
        createStorePanel.add(formPanel);
        createStoreButton.add(Box.createVerticalStrut(10));

        return createStorePanel;
    }

    private JPanel createEditProductPanel() {
        selectedProduct = null;
        JPanel editProductPanel = new JPanel();
        editProductPanel.setLayout(new BoxLayout(editProductPanel, BoxLayout.Y_AXIS));

        Dimension titleDimension = new Dimension(400, 50);
        JLabel titleLabelProduct = new JLabel("Select a Product from " + selectedStore.getName());
        titleLabelProduct.setMinimumSize(titleDimension);
        titleLabelProduct.setMaximumSize(titleDimension);
        titleLabelProduct.setAlignmentX(Component.CENTER_ALIGNMENT);
        titleLabelProduct.setFont(new Font("Arial", Font.BOLD, 16));

        // Panel for back button and title
        JPanel titlePanelProduct = new JPanel();
        titlePanelProduct.setLayout(new BoxLayout(titlePanelProduct, BoxLayout.X_AXIS));  // Use BorderLayout for the title panel
        titlePanelProduct.add(Box.createHorizontalStrut(15));  // Add space between back button and title
        titlePanelProduct.add(createBackToMenuButton());  // Align the back button to the left
        titlePanelProduct.add(Box.createHorizontalStrut(20));  // Add space between back button and title
        titlePanelProduct.add(titleLabelProduct);  // Center-align the title

        JPanel storeNamePanelProduct = new JPanel();
        ArrayList<Product> storeProducts = new Request().getSeller(userEmail).getStore(selectedStore).getProducts();
        storeNamePanelProduct.setLayout(new BoxLayout(storeNamePanelProduct, BoxLayout.Y_AXIS));

        JLabel selectedStoreLabelProduct = new JLabel("Selected Product: ");
        selectedStoreLabelProduct.setAlignmentX(Component.CENTER_ALIGNMENT);
        selectedStoreLabelProduct.setFont(new Font("Arial", Font.PLAIN, 18));

        for (Product product : storeProducts) {
            JLabel label = new JLabel(product.getName());
            label.setAlignmentX(Component.CENTER_ALIGNMENT);
            label.setFont(new Font("Arial", Font.PLAIN, 18));

            label.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    // Handle click event
                    selectedProduct = product;
                    // Update the display with the selected product's name
                    selectedStoreLabelProduct.setText("Selected Product: " + selectedProduct.getName());
                }
            });


            storeNamePanelProduct.add(label);
            storeNamePanelProduct.add(new JSeparator(JSeparator.HORIZONTAL));
        }

        // Remove the last separator to avoid an extra line at the end
        if (!storeProducts.isEmpty()) {
            storeNamePanelProduct.remove(storeNamePanelProduct.getComponentCount() - 1);
        }

        JScrollPane pspProduct = new JScrollPane(storeNamePanelProduct, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        pspProduct.setPreferredSize(new Dimension(300, 320));


        // Confirm button
        JButton confirmButtonProduct = new JButton("Confirm");
        confirmButtonProduct.setAlignmentX(Component.CENTER_ALIGNMENT);
        confirmButtonProduct.setFont(new Font("Arial", Font.PLAIN, 18));
        confirmButtonProduct.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (selectedProduct == null) {
                    JOptionPane.showMessageDialog(createEditProductPanel(), "Please select a Product first.", "Error", JOptionPane.ERROR_MESSAGE);

                } else {
                    // Handle confirmation action
                    cardPanel.add(editProductDetailsPanel(), "Edit Product Detail");
                    cardLayout.show(cardPanel, "Edit Product Detail");
                    frame.setSize(400, 400);
                }
            }
        });

        editProductPanel.setLayout(new BoxLayout(editProductPanel, BoxLayout.Y_AXIS));

        // Increase the height by adjusting the strut values
        editProductPanel.add(Box.createVerticalStrut(20));
        editProductPanel.add(titlePanelProduct);
//        editProductPanel.add(titleLabelStore);
        editProductPanel.add(Box.createVerticalStrut(20));
        editProductPanel.add(pspProduct);
        editProductPanel.add(Box.createVerticalStrut(20));
        editProductPanel.add(selectedStoreLabelProduct);
        editProductPanel.add(Box.createVerticalStrut(20));
        editProductPanel.add(confirmButtonProduct);
        editProductPanel.add(Box.createVerticalStrut(20));


        return editProductPanel;
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





    private Customer recievingCustomer;  // Variable to store the selected seller's name

    private JPanel createContactCustomerPanel() {
        recievingCustomer = null;
        frame.setSize(400, 500);  // Set the frame size accordingly
        frame.setLocationRelativeTo(null);  // Center the frame
        JPanel contactSellerPanel = new JPanel();
        contactSellerPanel.setLayout(new BoxLayout(contactSellerPanel, BoxLayout.Y_AXIS));

        // Back button
        JButton backButton = createBackToMenuButton();

        // title label
        JLabel titleLabel = new JLabel("Message a Customer", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));

        // inbox button
        JButton messagesButton = new JButton("Messages");
        //messagesButton.setFont(new Font("Arial", Font.BOLD, 10));
        messagesButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardPanel.removeAll();
                cardPanel.add(createSellerMessagesPanel(), "View Seller Messages");
                cardLayout.show(cardPanel, "View Seller Messages");
            }
        });

        JPanel titlePanel = new JPanel();
        titlePanel.setMaximumSize(new Dimension(400, 100));
        titlePanel.setPreferredSize(new Dimension(400, 100));
        GridBagLayout layout = new GridBagLayout();
        titlePanel.setLayout(layout);
        GridBagConstraints gbc = new GridBagConstraints();

        Insets insets = new Insets(20,10,10,10);
        gbc.insets = insets;

        gbc.gridx = 0;
        //gbc.weightx = 10;
        titlePanel.add(backButton, gbc);

        gbc.gridx = 1;
        //gbc.weightx = 10;
        titlePanel.add(titleLabel, gbc);

        gbc.gridx = 2;
        //gbc.weightx = 10;
        titlePanel.add(messagesButton, gbc);
        //titlePanel.add(Box.createHorizontalStrut(20));



        JPanel customerNamePanel = new JPanel();
        ArrayList<Customer> allCustomers = new Request().getAllCustomers();
        customerNamePanel.setLayout(new BoxLayout(customerNamePanel, BoxLayout.Y_AXIS));
        for (Customer customer : allCustomers) {
            JLabel label = new JLabel(customer.getUsername());
            label.setAlignmentX(Component.CENTER_ALIGNMENT);
            label.setFont(new Font("Arial", Font.PLAIN, 18));

            label.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    // Handle click event
                    recievingCustomer = customer;
                    // Update the display with the selected seller's name
                    selectedCustomerLabel.setText("Selected Customer: " + recievingCustomer.getUsername());

                }
            });

            customerNamePanel.add(label);
            customerNamePanel.add(Box.createVerticalStrut(1));
            customerNamePanel.add(new JSeparator(JSeparator.HORIZONTAL));
        }

        JPanel helperPanel = new JPanel();
        helperPanel.setLayout(new BorderLayout());
        helperPanel.add(customerNamePanel, BorderLayout.NORTH);

        // Remove the last separator to avoid an extra line at the end
        if (!allCustomers.isEmpty()) {customerNamePanel.remove(customerNamePanel.getComponentCount() - 1);}

        JScrollPane jsp = new JScrollPane(helperPanel, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        // Display selected seller's name
        jsp.setPreferredSize(new Dimension(250, 300));
        jsp.setMaximumSize(new Dimension(250,300));
        selectedCustomerLabel = new JLabel("Selected Customer: ");
        selectedCustomerLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        selectedCustomerLabel.setFont(new Font("Arial", Font.PLAIN, 16));

        // Container for the message components
        JPanel messagePanel = new JPanel();
        messagePanel.setLayout(new BoxLayout(messagePanel, BoxLayout.Y_AXIS));

        // Label above JTextArea
        JLabel messageLabel = new JLabel("What's your message?");
        messageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        messageLabel.setFont(new Font("Arial", Font.PLAIN, 16));

        // JTextArea for entering the message
        JTextArea messageTextArea = new JTextArea();
        messageTextArea.setAlignmentX(Component.CENTER_ALIGNMENT);
        messageTextArea.setLineWrap(true);
        messageTextArea.setWrapStyleWord(true);
        messageTextArea.setFont(new Font("Arial", Font.PLAIN, 16));


        JScrollPane messageScrollPane = new JScrollPane(messageTextArea);
        messageScrollPane.setPreferredSize(new Dimension(250, 100));
        messageScrollPane.setMaximumSize(new Dimension(250, 100));

        // Button to send the message
        JButton sendMessageButton = new JButton("Send Message");
        sendMessageButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        sendMessageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle sending the message
                String message = messageTextArea.getText();

                if (recievingCustomer == null ) {
                    JOptionPane.showMessageDialog(null, "Error! Please select a customer before sending a message.");
                } else if (messageTextArea.getText().equals("")){
                    JOptionPane.showMessageDialog(null, "Error! Please write a message before sending to a customer.");
                } else {
                    // testing purposes
                    String messageSent = messageTextArea.getText();

                    Account senderAccount = new Request().getAccount(userEmail);

                    Account recievingAccount = recievingCustomer.getAccount();

                    boolean response = new Request().updateMessages(senderAccount, recievingAccount, messageSent);

                    if (response) {
                        JOptionPane.showMessageDialog(null, "Message successfully sent to " + recievingCustomer.getUsername() + ".", "Messaging", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null, "Message failed to send.", "Messaging", JOptionPane.ERROR_MESSAGE);
                    }
                }

                // Clear the message text area
                messageTextArea.setText("");
            }
        });

        messagePanel.add(messageLabel);
        messagePanel.add(messageScrollPane);
        messagePanel.add(sendMessageButton);

        contactSellerPanel.setLayout(new BoxLayout(contactSellerPanel, BoxLayout.Y_AXIS));

        contactSellerPanel.add(titlePanel);
        contactSellerPanel.add(jsp);
        contactSellerPanel.add(Box.createVerticalStrut(10));
        contactSellerPanel.add(selectedCustomerLabel);
        contactSellerPanel.add(Box.createVerticalStrut(10));
        contactSellerPanel.add(messagePanel);
        contactSellerPanel.add(Box.createVerticalStrut(20));

        return contactSellerPanel;

    }

    private JPanel createSellerMessagesPanel() {
        frame.setSize(400, 500);
        frame.setLocationRelativeTo(null);
        JPanel sellerMessagesPanel = new JPanel();
        sellerMessagesPanel.setLayout(new BoxLayout(sellerMessagesPanel, BoxLayout.Y_AXIS));

        JButton backToContactCustomersButton = createBackToContactCustomersButton();
        backToContactCustomersButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        backToContactCustomersButton.setFont(new Font("Arial", Font.PLAIN, 18));

        JLabel sellerMessagesLabel = new JLabel("Seller Messages");
        sellerMessagesLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        sellerMessagesLabel.setFont(new Font("Arial", Font.BOLD, 24));

        Dimension topDimension = new Dimension(400, 75);
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.X_AXIS));
        topPanel.setMinimumSize(topDimension);
        topPanel.setMaximumSize(topDimension);
        topPanel.add(Box.createHorizontalStrut(20));
        topPanel.add(backToContactCustomersButton);
        topPanel.add(Box.createHorizontalStrut(40));
        topPanel.add(sellerMessagesLabel);
        topPanel.add(Box.createHorizontalStrut(20));

        JPanel messagesPanel = new JPanel();
        ArrayList<String> messageLines = new Request().getMessages(new Request().getAccount(userEmail));
        messagesPanel.setLayout(new BoxLayout(messagesPanel, BoxLayout.Y_AXIS));
        messagesPanel.add(Box.createHorizontalStrut(40));
        for (String messageLine : messageLines) {
            String[] messageParts = messageLine.split(",");
            String labelText = "";
            switch (messageParts[0]) {

                case "r" -> {

                    labelText = String.format("Received from [%s-%s]", messageParts[1], messageParts[2]);

                }
                case "s" -> {

                    labelText = String.format("Sent to [%s-%s]", messageParts[1], messageParts[2]);

                }

            }
            JLabel label = new JLabel(labelText);
            label.setAlignmentX(Component.CENTER_ALIGNMENT);
            label.setFont(new Font("Arial", Font.BOLD, 18));
            label.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    // Handle click event
                    customerMessage = messageParts[3];
                    label.setFont(new Font("Arial", Font.PLAIN, 18));
                    // Update the display with the selected customer's name
                    selectedCustomerMessageLabel.setText(customerMessage);
                }
            });

            messagesPanel.add(label);
            messagesPanel.add(Box.createVerticalStrut(1));
            messagesPanel.add(new JSeparator(JSeparator.HORIZONTAL));
        }

        JPanel helperPanel = new JPanel();
        helperPanel.setLayout(new BorderLayout());

        helperPanel.add(messagesPanel, BorderLayout.NORTH);

        // Remove the last separator to avoid an extra line at the end
        if (messageLines.isEmpty()) {messagesPanel.remove(messagesPanel.getComponentCount() - 1);}

        JScrollPane jsp = new JScrollPane(helperPanel, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        Dimension jspDimension = new Dimension(350, 250);
        jsp.setMaximumSize(jspDimension);
        jsp.setPreferredSize(jspDimension);

        Dimension customerFullMessagePanelDimension = new Dimension(400, 400);
        JPanel customerFullMessagePanel = new JPanel(new FlowLayout(FlowLayout.CENTER)); // Use FlowLayout with left alignment
//        customerFullMessagePanel.setMaximumSize(customerFullMessagePanelDimension);
//        customerFullMessagePanel.setMinimumSize(customerFullMessagePanelDimension);

        selectedCustomerMessageLabel = new JTextArea("Please select a Message to Expand");
        selectedCustomerMessageLabel.setLineWrap(true);
        selectedCustomerMessageLabel.setWrapStyleWord(true);
        selectedCustomerMessageLabel.setEditable(false); // Prevent editing in JTextArea
        selectedCustomerMessageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        selectedCustomerMessageLabel.setFont(new Font("Arial", Font.PLAIN, 18));

        JScrollPane messageScrollPane = new JScrollPane(selectedCustomerMessageLabel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        messageScrollPane.setPreferredSize(new Dimension(350, 100));
        customerFullMessagePanel.add(messageScrollPane);

        sellerMessagesPanel.add(Box.createVerticalStrut(20));
        sellerMessagesPanel.add(topPanel);
        sellerMessagesPanel.add(Box.createVerticalStrut(20));
        sellerMessagesPanel.add(jsp);
        sellerMessagesPanel.add(Box.createVerticalStrut(20));
        sellerMessagesPanel.add(customerFullMessagePanel);
        sellerMessagesPanel.add(Box.createVerticalStrut(20));

        return sellerMessagesPanel;
    }

    private JPanel createEditStoreDetailsPanel() {
        JPanel editStoreDetailsPanel = new JPanel();
        editStoreDetailsPanel.setLayout(new BoxLayout(editStoreDetailsPanel, BoxLayout.Y_AXIS));

        // Back button
        JButton backButton = createBackToEditStoreButton();
        String storeText = "    Edit " + selectedStore.getName() + "'s Panel";
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
            cardPanel.removeAll();
            cardPanel.add(createProductPanel(), "Create Product");
            cardLayout.show(cardPanel, "Create Product");
//            frame.setResizable(true);
            frame.setSize(400, 300);
        });


        JButton csvImportButton = createButton("CSV Import");
        csvImportButton.addActionListener(e -> handleCSVImport());

        JButton deleteStoreButton = createButton("Delete Store");
        deleteStoreButton.addActionListener(e -> {
            int dialogResult = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete the store: " + selectedStore.getName() + "?", "Confirmation", JOptionPane.YES_NO_OPTION);

            if (dialogResult == JOptionPane.YES_OPTION) {
                // Perform the store deletion logic here

                Seller storeSeller = selectedStore.getSellerOwner();
                storeSeller.getSellerStores().remove(selectedStore);
                Boolean response = new Request().updateSeller(storeSeller);

                if (response) {
                    JOptionPane.showMessageDialog(null, selectedStore.getName() + " has been deleted successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
                    // Add your deletion logic here
                    cardPanel.removeAll();
                    cardPanel.add(createMainPanel(), "Main Page");
                    cardLayout.show(cardPanel, "Main Page");
                } else {
                    JOptionPane.showMessageDialog(null, "Failed to delete the store.", "Error", JOptionPane.ERROR_MESSAGE);
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
                    selectedStore.setName(newName);
                    new Request().updateSeller(selectedStore.getSellerOwner());
                    JOptionPane.showMessageDialog(null, "Name changed to \"" + newName + "\" successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
                    // Add your logic to update the store name here
                    cardPanel.removeAll();
                    cardPanel.add(createMainPanel(), "Main Page");
                    cardLayout.show(cardPanel, "Main Page");

                } else {
                    // User canceled the name change
                    JOptionPane.showMessageDialog(null, "Name change canceled.", "Cancelled", JOptionPane.WARNING_MESSAGE);
                }
            } else {
                // User canceled the new name entry
            }
        });
        JButton editStoreProductsButton = createButton("Edit Store Products");
        editStoreProductsButton.addActionListener(e -> {
            cardPanel.add(createEditProductPanel(), "Edit Product");
            cardLayout.show(cardPanel, "Edit Product");
            frame.setSize(400, 500);
            editStoreFlag = false;
        });

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
        frame.setSize(400, 300);
        frame.setLocationRelativeTo(null);

        JPanel createProductPanel = new JPanel();
        createProductPanel.setLayout(new BoxLayout(createProductPanel, BoxLayout.Y_AXIS));

        // Title
        JLabel titleLabel = new JLabel("Create Product");
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));

        // Back to Menu Button
        JButton backToMenuButton = createBackToMenuButton();

        // Top Panel
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.X_AXIS));
        topPanel.add(backToMenuButton);
        topPanel.add(Box.createHorizontalStrut(85));
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
        ArrayList<Store> sellerStores = new Request().getSeller(userEmail).getSellerStores();
        Store[] stores = new Store[sellerStores.size()];
        stores = sellerStores.toArray(stores);
        JComboBox<Store> storeComboBox = new JComboBox<>(stores);
        formPanel.add(createFieldWithLabel("Store:", storeComboBox));

        // Create Product Button
        JButton createProductButton = new JButton("Create Product");
        createProductButton.setFont(new Font("Arial", Font.PLAIN, 16));
        createProductButton.addActionListener(e -> {
            // Handle the creation of the product here
            if (sellerStores.isEmpty()) {

                JOptionPane.showMessageDialog(createProductPanel, "Please create a store first", "Error", JOptionPane.ERROR_MESSAGE);
                return;

            }

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

            // Confirm creation
            Store selectedStore = (Store) storeComboBox.getSelectedItem();
            int confirmResult = JOptionPane.showConfirmDialog(createProductPanel,
                    ("Create the following product?\n" +
                            "Store: %s\n" +
                            "Name: %s\n" +
                            "Description: %s\n" +
                            "Price: $%.2f\n" +
                            "Quantity: %d\n"
                    ).formatted(selectedStore.getName(), name, description, price, quantity),
                    "Confirm Product Creation", JOptionPane.YES_NO_OPTION);

            if (confirmResult == JOptionPane.YES_OPTION) {
                // User clicked 'Yes', process creation
                // testing purposes

                Product newProudct = new Product(name, price, quantity, description, selectedStore);
                selectedStore.addProduct(newProudct);
                new Request().updateSeller(selectedStore.getSellerOwner());

                // Reset the create product panel
                nameTextField.setText("");
                descriptionTextArea.setText("");
                priceSpinner.setValue(0.0);
                quantitySpinner.setValue(1);
                storeComboBox.setSelectedIndex(0);
            } else {
                // User clicked 'No', do nothing or handle accordingly
            }
        });


        // Add components to the form panel
        formPanel.add(createProductButton);
        createProductButton.add(Box.createVerticalStrut(10));
        createProductPanel.add(formPanel);
        createProductButton.add(Box.createVerticalStrut(10));

        return createProductPanel;
    }

    private JPanel editProductDetailsPanel() {
        frame.setResizable(true);
        JPanel createProductPanel = new JPanel();
        createProductPanel.setLayout(new BoxLayout(createProductPanel, BoxLayout.Y_AXIS));

        // Title
        JLabel titleLabel = new JLabel("    Edit Product: "+ selectedProduct.getName());
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));

        // Top Panel
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.X_AXIS));
        topPanel.add(createBackToEditProductButton());
        topPanel.add(Box.createHorizontalStrut(10));
        topPanel.add(titleLabel);
        topPanel.add(Box.createHorizontalGlue()); // Add glue to push the title to the right

        createProductPanel.add(topPanel);
        createProductPanel.add(Box.createVerticalStrut(20));

        // Form Panel
        JPanel formPanel = new JPanel(new GridLayout(7, 1, 10, 10));

        // Name
        JTextField nameTextField = new JTextField(20);
        nameTextField.setText(selectedProduct.getName()); // Autofill the name field
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

        // Store Label
        JLabel storeLabel = new JLabel("Store:          " + selectedStore.getName());
        storeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        storeLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        formPanel.add(storeLabel);



        // Edit Product Button
        JButton editProductButton = new JButton("Edit Product");
        editProductButton.setFont(new Font("Arial", Font.PLAIN, 18));
        editProductButton.addActionListener(e -> {
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
                    JOptionPane.showMessageDialog(createProductPanel, "Quantity must be greater than 0.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(createProductPanel, "Invalid quantity format.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Confirm creation
            int confirmResult = JOptionPane.showConfirmDialog(createProductPanel,
                    "Confirm Edit?\n\n" +
                            "Name: " + name + "\n" +
                            "Description: " + description + "\n" +
                            "Price: " + price + "\n" +
                            "Quantity: " + quantity + "\n" +
                            "Store: " + selectedStore.getName(),
                    "Confirm Product Edit", JOptionPane.YES_NO_OPTION);

            if (confirmResult == JOptionPane.YES_OPTION) {
                // User clicked 'Yes', process creation
                // Reset the create product panel
                nameTextField.setText("");
                descriptionTextArea.setText("");
                priceSpinner.setValue(0.0);
                quantitySpinner.setValue(1);

                Product productToUpdate = new Request().getSeller(userEmail).getStore(selectedStore).getProduct(selectedProduct);

                productToUpdate.setName(name);
                productToUpdate.setDescription(description);
                productToUpdate.setPrice(price);
                productToUpdate.setQuantity(quantity);

                new Request().updateSeller(productToUpdate.getStore().getSellerOwner());

            } else {
                // User clicked 'No', do nothing or handle accordingly
            }
        });

        JButton deleteProductButton = new JButton("Delete Product");
        deleteProductButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        deleteProductButton.setFont(new Font("Arial", Font.PLAIN, 18));
        deleteProductButton.addActionListener(e -> {
            // Handle the deletion of the product here
            int deleteResult = JOptionPane.showConfirmDialog(createProductPanel,
                    "Are you sure you want to delete the product?\n\n" +
                            "Name: " + selectedProduct.getName() + "\n" +
                            "Store: " + selectedStore.getName(),
                    "Confirm Product Deletion", JOptionPane.YES_NO_OPTION);

            if (deleteResult == JOptionPane.YES_OPTION) {
                // User clicked 'Yes', process deletion
                String deletionMessage = "Product deleted:\n" +
                        "Name: " + selectedProduct.getName() + "\n" +
                        "Store: " + selectedStore.getName();
                JOptionPane.showMessageDialog(createProductPanel, deletionMessage, "Product Deletion", JOptionPane.INFORMATION_MESSAGE);

                selectedStore.removeProduct(selectedProduct);

                new Request().updateSeller(selectedStore.getSellerOwner());

                cardPanel.removeAll();
                cardPanel.add(createEditProductPanel(), "Edit Product");
                cardLayout.show(cardPanel, "Edit Product");
                frame.setSize(400,500);

                // You can add logic to delete the product from your data structure
                // and update the UI accordingly
            } else {
                // User clicked 'No', do nothing or handle accordingly
            }
        });


        // Add components to the form panel

        formPanel.add(editProductButton);
        formPanel.add(deleteProductButton);
        createProductPanel.add(formPanel);
        deleteProductButton.add(Box.createVerticalStrut(10));
        createProductPanel.add(Box.createVerticalStrut(10));

        return createProductPanel;
    }

    private Component createBackToEditProductButton() {

        JButton backButton = new JButton("<");
        Font largeFont = new Font("Arial", Font.PLAIN, 18);
        backButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        backButton.setFont(largeFont);
//        Dimension buttonSize = new Dimension(70, 10);
//        backButton.setPreferredSize(buttonSize);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardPanel.removeAll();
                cardPanel.add(createEditProductPanel(), "Edit Product");
                cardLayout.show(cardPanel,"Edit Product");
            }
        });
        return backButton;
    }

    private JPanel createStoreStatisticsPanel() {
        JPanel createStoreStatistics = new JPanel();
        createStoreStatistics.setLayout(new BoxLayout(createStoreStatistics, BoxLayout.Y_AXIS));
        JLabel titleLabel = new JLabel("What Statistics would you like to View?");
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));


        JPanel topPanel = new JPanel();
        Dimension topPanelDimension = new Dimension(400, 50);
        //topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.X_AXIS));
        topPanel.setMaximumSize(topPanelDimension);
        topPanel.setMinimumSize(topPanelDimension);
        topPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        topPanel.add(createBackToMenuButton());
        topPanel.add(Box.createHorizontalStrut(10));
        topPanel.add(titleLabel);
        topPanel.add(Box.createHorizontalStrut(10));

        Dimension buttonDimension = new Dimension(300, 70);

        JButton storeSalesButton = new JButton(("View All Store Sales"));
        storeSalesButton.setMaximumSize(buttonDimension);
        storeSalesButton.setMinimumSize(buttonDimension);
        storeSalesButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        storeSalesButton.setFont(new Font("Arial", Font.PLAIN, 18));
        storeSalesButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "View All Store Sales");
                frame.setSize(400, 500);
                frame.setLocationRelativeTo(null);
            }
        });


        JButton specificStoreSalesButton = new JButton(("View Specific Store Sales"));
        specificStoreSalesButton.setMaximumSize(buttonDimension);
        specificStoreSalesButton.setMinimumSize(buttonDimension);
        specificStoreSalesButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        specificStoreSalesButton.setFont(new Font("Arial", Font.PLAIN, 18));
        specificStoreSalesButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "View Specific Store Sales");
                frame.setSize(600, 500);
                frame.setLocationRelativeTo(null);
            }
        });

        JButton customerShoppingCartButton = new JButton(("View Customer's Shopping Cart"));
        customerShoppingCartButton.setMaximumSize(buttonDimension);
        customerShoppingCartButton.setMinimumSize(buttonDimension);
        customerShoppingCartButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        customerShoppingCartButton.setFont(new Font("Arial", Font.PLAIN, 18));
        customerShoppingCartButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "View Customer's Shopping Carts");
                frame.setSize(600, 500);
                frame.setLocationRelativeTo(null);
            }
        });

        createStoreStatistics.add(Box.createVerticalStrut(20));
        createStoreStatistics.add(topPanel);
        createStoreStatistics.add(Box.createVerticalStrut(20));
        createStoreStatistics.add(storeSalesButton);
        createStoreStatistics.add(Box.createVerticalStrut(20));
        createStoreStatistics.add(specificStoreSalesButton);
        createStoreStatistics.add(Box.createVerticalStrut(20));
        createStoreStatistics.add(customerShoppingCartButton);
        createStoreStatistics.add(Box.createVerticalStrut(20));

        return createStoreStatistics;
    }

    private JPanel createViewAllStoreStatisticsPanel() {
        JPanel createViewAllStoreStatistics = new JPanel();
        createViewAllStoreStatistics.setLayout(new BoxLayout(createViewAllStoreStatistics, BoxLayout.Y_AXIS));
        JLabel titleLabel = new JLabel("Sales From All Stores");
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        double totalRevenue = getTotalRevenue(new Request().getSeller(userEmail));
        JLabel totalSellerRevenue = new JLabel("Temporary Seller's Total Revenue: " + "$0"); // temporary // needs to sum up total sales of all stores
        totalSellerRevenue.setAlignmentX(Component.CENTER_ALIGNMENT);
        totalSellerRevenue.setFont(new Font("Arial", Font.BOLD, 16));


        JPanel topPanel = new JPanel();
        Dimension topPanelDimension = new Dimension(500, 50);
        topPanel.setMaximumSize(topPanelDimension);
        topPanel.setMinimumSize(topPanelDimension);
        topPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        topPanel.add(createBackToStoreStatisticsButton());
        topPanel.add(Box.createHorizontalStrut(10));
        topPanel.add(titleLabel);
        topPanel.add(Box.createHorizontalStrut(10));


        JPanel middlePanel = new JPanel();
        topPanel.setMaximumSize(topPanelDimension);
        topPanel.setMinimumSize(topPanelDimension);
        topPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        topPanel.add(Box.createHorizontalStrut(10));
        middlePanel.add(totalSellerRevenue);
        middlePanel.add(Box.createHorizontalStrut(10));


        Dimension buttonDimension = new Dimension(300, 70);

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
                    specificStoreName = label.getText();
                    // Update the display with the selected store's name
                    selectedAllStoreLabel.setText(specificStoreName + " Total Sales:" + " $0"); // temporary
                }
            });

            storeNamePanel.add(label);
            storeNamePanel.add(new JSeparator(JSeparator.HORIZONTAL));
        }

        // Remove the last separator to avoid an extra line at the end
        storeNamePanel.remove(storeNamePanel.getComponentCount() - 1);

        JScrollPane psp = new JScrollPane(storeNamePanel, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        // Display selected store's name
        psp.setPreferredSize(new Dimension(500, 320));
        selectedAllStoreLabel = new JLabel("Selected Store: ");
        selectedAllStoreLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        selectedAllStoreLabel.setFont(new Font("Arial", Font.PLAIN, 18));

        createViewAllStoreStatistics.add(Box.createVerticalStrut(20));
        createViewAllStoreStatistics.add(topPanel);
        createViewAllStoreStatistics.add(Box.createVerticalStrut(10));
        createViewAllStoreStatistics.add(middlePanel);
        createViewAllStoreStatistics.add(Box.createVerticalStrut(20));
        createViewAllStoreStatistics.add(psp);
        createViewAllStoreStatistics.add(Box.createVerticalStrut(20));
        createViewAllStoreStatistics.add(selectedAllStoreLabel);
        createViewAllStoreStatistics.add(Box.createVerticalStrut(20));

        return createViewAllStoreStatistics;
    }

    private double getTotalRevenue(Seller seller) {

        double totalRevenue = 0;

        for (Store store : seller.getSellerStores()) {

            for (Purchaser purchaser : store.getPurchasers()) {

                totalRevenue += purchaser.getRevenueGenerated();

            }

        }

        return totalRevenue;

    }

    // View a specific Store's Statistics (selects from store)
    private JPanel createViewSpecificStoreStatisticsPanel() {
        frame.setSize(450,450);
        frame.setLocationRelativeTo(null);
        selectedStore = null;

        JPanel createViewSpecificStoreStatistics = new JPanel();
        createViewSpecificStoreStatistics.setLayout(new BoxLayout(createViewSpecificStoreStatistics, BoxLayout.Y_AXIS));

        JLabel titleLabel = new JLabel("Which Store's Statistics Would You Like to View?");
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 13));

        JPanel topPanel = new JPanel();
        Dimension topPanelDimension = new Dimension(450, 50);
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.X_AXIS));
        topPanel.setMaximumSize(topPanelDimension);
        topPanel.setMinimumSize(topPanelDimension);
        topPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        topPanel.add(Box.createHorizontalStrut(10));
        topPanel.add(createBackToMenuButton());
        topPanel.add(Box.createHorizontalStrut(10));
        topPanel.add(titleLabel);
        topPanel.add(Box.createHorizontalStrut(10));


        Dimension scrollPaneDimension = new Dimension(300,300);

        // Create a JPanel to hold vertically listed JLabels
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setPreferredSize(new Dimension(280, 300));

        // Create helper panel to help center store labels
        JPanel helperPanel = new JPanel();
        helperPanel.setLayout(new BorderLayout());
        helperPanel.setSize(new Dimension(280, 300));

        ArrayList<Store> sellerStores = new Request().getSeller(userEmail).getSellerStores();

        for (Store store : sellerStores) {
            JLabel storeLabel = new JLabel(store.getName());
            storeLabel.setFont(new Font("Arial", Font.PLAIN, 18));
            storeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            storeLabel.setAlignmentY(Component.CENTER_ALIGNMENT);
            storeLabel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    selectedStore = store;

                    selectedStoreStatsLabel.setText("Selected Store: " + selectedStore.getName());
                }
            });
            panel.add(storeLabel);
            panel.add(new JSeparator(SwingConstants.HORIZONTAL));
        }

        if (!sellerStores.isEmpty()) {
            panel.remove(panel.getComponentCount() - 1);
        }

        helperPanel.add(panel);

        JScrollPane psp = new JScrollPane(helperPanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        psp.setPreferredSize(scrollPaneDimension);
        psp.setMaximumSize(scrollPaneDimension);

        selectedStoreStatsLabel = new JLabel("Selected Store: ");
        selectedStoreStatsLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        selectedStoreStatsLabel.setFont(new Font("Arial", Font.PLAIN, 18));

        // Confirm button
        JButton confirmButton = new JButton("Confirm");
        confirmButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        confirmButton.setFont(new Font("Arial", Font.PLAIN, 18));
        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (selectedStore == null) {

                    JOptionPane.showMessageDialog(createViewSpecificStoreStatistics, "Please select a store first", "Error", JOptionPane.ERROR_MESSAGE);
                    return;

                }
                cardPanel.removeAll();
                cardPanel.add(createViewSpecificStoreStatisticsSaleTypePanel(), "Specific Store Statistics Sort");
                cardLayout.show(cardPanel, "Specific Store Statistics Sort");

            }
        });

        createViewSpecificStoreStatistics.add(Box.createVerticalStrut(20));
        createViewSpecificStoreStatistics.add(topPanel);
        createViewSpecificStoreStatistics.add(Box.createVerticalStrut(20));
        createViewSpecificStoreStatistics.add(psp);
        createViewSpecificStoreStatistics.add(Box.createVerticalStrut(20));
        createViewSpecificStoreStatistics.add(selectedStoreStatsLabel);
        createViewSpecificStoreStatistics.add(Box.createVerticalStrut(20));
        createViewSpecificStoreStatistics.add(confirmButton);
        createViewSpecificStoreStatistics.add(Box.createVerticalStrut(20));

        return createViewSpecificStoreStatistics;
    }

    // selects a specific Store to view Statistics (selects how to sort the statistics sales type by)
    private JPanel createViewSpecificStoreStatisticsSaleTypePanel() {
        frame.setSize(450, 300);
        frame.setLocationRelativeTo(null);
        JPanel createViewSpecificStoreStatisticsSaleType = new JPanel();
        createViewSpecificStoreStatisticsSaleType.setLayout(new BoxLayout(createViewSpecificStoreStatisticsSaleType, BoxLayout.Y_AXIS));
        JLabel titleLabel = new JLabel("Which Statistics would you Like To View?");
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));

        JPanel topPanel = new JPanel();
        Dimension topPanelDimension = new Dimension(450, 50);
        topPanel.setMaximumSize(topPanelDimension);
        topPanel.setMinimumSize(topPanelDimension);
        topPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        topPanel.add(Box.createHorizontalStrut(10));
        topPanel.add(createBackToSpecificStoreStatisticsButton());
        topPanel.add(Box.createHorizontalStrut(10));
        topPanel.add(titleLabel);
        topPanel.add(Box.createHorizontalStrut(10));

        Dimension buttonDimension = new Dimension(300, 50);

        // change to view sort
        JButton specificStoreSortByCustomer = new JButton(("Customer Sales"));
        specificStoreSortByCustomer.setMaximumSize(buttonDimension);
        specificStoreSortByCustomer.setMinimumSize(buttonDimension);
        specificStoreSortByCustomer.setAlignmentX(Component.CENTER_ALIGNMENT);
        specificStoreSortByCustomer.setFont(new Font("Arial", Font.PLAIN, 18));
        specificStoreSortByCustomer.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardPanel.removeAll();
                cardPanel.add(createViewSpecificStoreCustomerSalesPanel(), "View Specific Store Customer Sales");
                cardLayout.show(cardPanel, "View Specific Store Customer Sales");
            }
        });

        JButton specificStoreSortByProduct = new JButton(("Product Sales"));
        specificStoreSortByProduct.setMaximumSize(buttonDimension);
        specificStoreSortByProduct.setMinimumSize(buttonDimension);
        specificStoreSortByProduct.setAlignmentX(Component.CENTER_ALIGNMENT);
        specificStoreSortByProduct.setFont(new Font("Arial", Font.PLAIN, 18));
        specificStoreSortByProduct.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardPanel.removeAll();
                cardPanel.add(createViewSpecificStoreProductSalesPanel(), "View Specific Store Product Sales");
                cardLayout.show(cardPanel, "View Specific Store Product Sales");
            }
        });

        createViewSpecificStoreStatisticsSaleType.add(Box.createVerticalStrut(20));
        createViewSpecificStoreStatisticsSaleType.add(topPanel);
        createViewSpecificStoreStatisticsSaleType.add(Box.createVerticalStrut(20));
        createViewSpecificStoreStatisticsSaleType.add(specificStoreSortByCustomer);
        createViewSpecificStoreStatisticsSaleType.add(Box.createVerticalStrut(20));
        createViewSpecificStoreStatisticsSaleType.add(specificStoreSortByProduct);
        createViewSpecificStoreStatisticsSaleType.add(Box.createVerticalStrut(20));


        return createViewSpecificStoreStatisticsSaleType;
    }

    private JPanel createViewSpecificStoreCustomerSalesPanel() {
        frame.setSize(450, 450);
        frame.setLocationRelativeTo(null);

        JPanel createViewSpecificStoreCustomerSales = new JPanel();
        createViewSpecificStoreCustomerSales.setSize(new Dimension(450,450));
        createViewSpecificStoreCustomerSales.setLayout(new BoxLayout(createViewSpecificStoreCustomerSales, BoxLayout.Y_AXIS));

        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        titlePanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        titlePanel.setPreferredSize(new Dimension(450, 50));

        JLabel titleLabel = new JLabel(selectedStore.getName() + " Customer Sales");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));

        titlePanel.add(Box.createHorizontalStrut(10));
        titlePanel.add(createBackToSpecificStoreStatisticsSaleTypeButton());
        titlePanel.add(Box.createHorizontalStrut(10));
        titlePanel.add(titleLabel);
        titlePanel.add(Box.createHorizontalStrut(20));

        createViewSpecificStoreCustomerSales.add(Box.createVerticalStrut(10));
        createViewSpecificStoreCustomerSales.add(titlePanel);
        createViewSpecificStoreCustomerSales.add(Box.createVerticalStrut(10));

        JPanel sortingPanel = new JPanel();
        sortingPanel.setLayout(new BoxLayout(sortingPanel, BoxLayout.X_AXIS));
        sortingPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        sortingPanel.setSize(new Dimension(450,25));
        JLabel sortingLabel = new JLabel("Sort by ");
        sortingPanel.add(sortingLabel);
        String[] sortingOptions = {"Quantity Sold (Low to High)", "Quantity Sold (High to Low)",
                                    "Revenue Generated (Low to High)", "Revenue Generated (High to Low)"};
        JComboBox<String> sortOptions = new JComboBox<>(sortingOptions);
        sortOptions.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedOption = (String) sortOptions.getSelectedItem();

                // Saving Implementation for when customers is complete to test with data
                switch (selectedOption) {

                    case "Quantity Sold (Low to High)" -> {



                    }
                    case "Quantity Sold (High to Low)" -> {



                    }
                    case "Revenue Generated (Low to High)" -> {



                    }
                    case "Revenue Generated (High to Low)" -> {



                    }
                }
            }
        });
        sortOptions.setMinimumSize(new Dimension(200,25));
        sortOptions.setMaximumSize(new Dimension(200,25));
        sortingPanel.add(sortOptions);
        createViewSpecificStoreCustomerSales.add(sortingPanel);

        JPanel helperPanel = new JPanel();
        helperPanel.setLayout(new BorderLayout());

        JPanel customerPanel = new JPanel();
        customerPanel.setLayout(new BoxLayout(customerPanel, BoxLayout.Y_AXIS));

        ArrayList<Purchaser> purchasers = selectedStore.getPurchasers();
        for (Purchaser customer : purchasers) {
            JLabel customerLabel = new JLabel(customer.getUsername());
            customerLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            customerLabel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {

                }
            });
            customerPanel.add(customerLabel);
            customerPanel.add(new JSeparator(SwingConstants.HORIZONTAL));
        }

        if (!purchasers.isEmpty()) {
            customerPanel.remove(customerPanel.getComponentCount() - 1);
        }

        helperPanel.add(customerPanel);

        JScrollPane jScrollPane = new JScrollPane(helperPanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane.setPreferredSize(new Dimension(300,300));
        jScrollPane.setMaximumSize(new Dimension(300,300));

        createViewSpecificStoreCustomerSales.add(Box.createVerticalStrut(20));
        createViewSpecificStoreCustomerSales.add(jScrollPane);

        selectedCustomerStatsLabel = new JLabel("Product: Quantity Sold: Revenue Generated:" );
        selectedCustomerStatsLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        createViewSpecificStoreCustomerSales.add(selectedCustomerStatsLabel);
        createViewSpecificStoreCustomerSales.add(Box.createVerticalStrut(20));

        return createViewSpecificStoreCustomerSales;
    }


    private JPanel createViewSpecificStoreProductSalesPanel() {
        frame.setSize(450, 450);
        frame.setLocationRelativeTo(null);

        JPanel createViewSpecificStoreProductSalesPanel = new JPanel();
        createViewSpecificStoreProductSalesPanel.setLayout(new BoxLayout(createViewSpecificStoreProductSalesPanel, BoxLayout.Y_AXIS));

        JLabel titleLabel = new JLabel(selectedStore.getName() + "'s Product Sales");
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 15));

        JPanel topPanel = new JPanel();
        Dimension topPanelDimension = new Dimension(450, 50);
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.X_AXIS));
        topPanel.setMaximumSize(topPanelDimension);
        topPanel.setMinimumSize(topPanelDimension);
        topPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        topPanel.add(Box.createHorizontalStrut(10));
        topPanel.add(createBackToSpecificStoreStatisticsSaleTypeButton());
        topPanel.add(Box.createHorizontalStrut(10));
        topPanel.add(titleLabel);
        topPanel.add(Box.createHorizontalStrut(10));

        JPanel sortingPanel = new JPanel();
        sortingPanel.setLayout(new BoxLayout(sortingPanel, BoxLayout.X_AXIS));
        sortingPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        sortingPanel.setSize(new Dimension(450,25));
        JLabel sortingLabel = new JLabel("Sort by ");
        sortingPanel.add(sortingLabel);
        String[] sortingOptions = {"Quantity Sold (Low to High)", "Quantity Sold (High to Low)",
                "Revenue Generated (Low to High)", "Revenue Generated (High to Low)"};
        JComboBox<String> sortOptions = new JComboBox<>(sortingOptions);
        sortOptions.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedOption = (String) sortOptions.getSelectedItem();

                // Saving Implementation for when customers is complete to test with data
                switch (selectedOption) {

                    case "Quantity Sold (Low to High)" -> {



                    }
                    case "Quantity Sold (High to Low)" -> {



                    }
                    case "Revenue Generated (Low to High)" -> {



                    }
                    case "Revenue Generated (High to Low)" -> {



                    }
                }
            }
        });
        sortOptions.setMinimumSize(new Dimension(200,25));
        sortOptions.setMaximumSize(new Dimension(200,25));
        sortingPanel.add(sortOptions);


        Dimension scrollPaneDimension = new Dimension(300,300);

        // Create a JPanel to hold vertically listed JLabels
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setPreferredSize(new Dimension(280, 300));

        // Create helper panel to help center store labels
        JPanel helperPanel = new JPanel();
        helperPanel.setLayout(new BorderLayout());
        helperPanel.setSize(new Dimension(280, 300));

        ArrayList<Product> storeProducts = selectedStore.getProducts();

        for (Product product : storeProducts) {
            JLabel productLabel = new JLabel(product.getName());
            productLabel.setFont(new Font("Arial", Font.PLAIN, 18));
            productLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            productLabel.setAlignmentY(Component.CENTER_ALIGNMENT);
            productLabel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    selectedProduct = product;

                    String statLine = String.format("Quantity Sold: %d, Revenue Generated: $%.2f", product.getQuantitySold(), product.getRevenueGenerated());

                    selectedProductStatsLabel.setText(statLine);
                }
            });
            panel.add(productLabel);
            panel.add(new JSeparator(SwingConstants.HORIZONTAL));
        }

        if (!storeProducts.isEmpty()) {
            panel.remove(panel.getComponentCount() - 1);
        }

        helperPanel.add(panel);

        JScrollPane psp = new JScrollPane(helperPanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        psp.setPreferredSize(scrollPaneDimension);
        psp.setMaximumSize(scrollPaneDimension);

        selectedProductStatsLabel = new JLabel("Quantity Sold: , Revenue Generated: ");
        selectedProductStatsLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        createViewSpecificStoreProductSalesPanel.add(Box.createVerticalStrut(10));
        createViewSpecificStoreProductSalesPanel.add(topPanel);
        createViewSpecificStoreProductSalesPanel.add(Box.createVerticalStrut(10));
        createViewSpecificStoreProductSalesPanel.add(sortingPanel);
        createViewSpecificStoreProductSalesPanel.add(Box.createVerticalStrut(10));
        createViewSpecificStoreProductSalesPanel.add(psp);
        createViewSpecificStoreProductSalesPanel.add(Box.createVerticalStrut(10));
        createViewSpecificStoreProductSalesPanel.add(selectedProductStatsLabel);
        createViewSpecificStoreProductSalesPanel.add(Box.createVerticalStrut(10));

        return createViewSpecificStoreProductSalesPanel;
    }

    private JPanel createViewCustomerShoppingCarts() {
        JPanel createViewCustomerShoppingCarts = new JPanel();
        createViewCustomerShoppingCarts.setLayout(new BoxLayout(createViewCustomerShoppingCarts, BoxLayout.Y_AXIS));
        JLabel titleLabel = new JLabel("Select Product to view Number in Customer's Shopping Carts");
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));

        JPanel topPanel = new JPanel();
        Dimension topPanelDimension = new Dimension(600, 100);
        topPanel.setMaximumSize(topPanelDimension);
        topPanel.setMinimumSize(topPanelDimension);
        topPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        topPanel.add(createBackToStoreStatisticsButton());
        topPanel.add(Box.createHorizontalStrut(10));
        topPanel.add(titleLabel);
        topPanel.add(Box.createHorizontalStrut(10));

        Dimension buttonDimension = new Dimension(300, 70);
        createViewCustomerShoppingCarts.add(Box.createVerticalStrut(20));
        createViewCustomerShoppingCarts.add(topPanel);

        // dummy products list in a store
        JPanel storeNamePanel = new JPanel();
        String[] dummyProducts= new String[]{"Product One", "Product Two", "Product Three", "Product Four", "Product Five",
                "Product Six", "Product Seven", "Product Eight", "Product Nine", "Product Ten", "Product Eleven", "Product Twelve"};
        storeNamePanel.setLayout(new BoxLayout(storeNamePanel, BoxLayout.Y_AXIS));


        for (String name : dummyProducts) {
            JLabel label = new JLabel(name);
            label.setAlignmentX(Component.CENTER_ALIGNMENT);
            label.setFont(new Font("Arial", Font.PLAIN, 18));


            label.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    // Handle click event
                    productInShoppingCartName = label.getText();
                    // Update the display with the selected store's name
                    selectedProductInShoppingCartLabel.setText(productInShoppingCartName + " is currently in " + 2 + " Customers Shopping Carts"); // dummy number of products temporarily
                }
            });

            storeNamePanel.add(label);
            storeNamePanel.add(new JSeparator(JSeparator.HORIZONTAL));
        }

        // Remove the last separator to avoid an extra line at the end
        storeNamePanel.remove(storeNamePanel.getComponentCount() - 1);

        JScrollPane psp = new JScrollPane(storeNamePanel, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        // Display selected store's name
        psp.setPreferredSize(new Dimension(500, 320));
        selectedProductInShoppingCartLabel = new JLabel("Selected Product in Shopping Cart: ");
        selectedProductInShoppingCartLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        selectedProductInShoppingCartLabel.setFont(new Font("Arial", Font.PLAIN, 18));


        createViewCustomerShoppingCarts.add(Box.createVerticalStrut(20));
        createViewCustomerShoppingCarts.add(topPanel);
        createViewCustomerShoppingCarts.add(Box.createVerticalStrut(20));
        createViewCustomerShoppingCarts.add(psp);
        createViewCustomerShoppingCarts.add(Box.createVerticalStrut(20));
        createViewCustomerShoppingCarts.add(selectedProductInShoppingCartLabel);
        createViewCustomerShoppingCarts.add(Box.createVerticalStrut(20));

        return createViewCustomerShoppingCarts;
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

    public JPanel getMainSellerPanel() {
        return mainSellerPanel;
    }

    public void switchToAccountPanelButton(ActionListener actionListener) {
        //Work here

    }

    private static class DoubleSpinner extends JSpinner {

        private static final long serialVersionUID = 1L;

        private DoubleSpinner(JSpinner spinner, SpinnerNumberModel model) {
            super(model);
            this.setModel(model);
        }
    }

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
                cardPanel.add(createMainPanel(), "Main Page");
                cardLayout.show(cardPanel,"Main Page");
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

    private JButton createBackToStoreStatisticsButton() {
        JButton backButton = new JButton("<");
        Font largeFont = new Font("Arial", Font.PLAIN, 18);
        backButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        backButton.setFont(largeFont);

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardPanel.removeAll();
                cardPanel.add(createViewSpecificStoreStatisticsPanel(), "Store Statistics");
                cardLayout.show(cardPanel, "Store Statistics");
                frame.setSize(400, 450);
                frame.setLocationRelativeTo(null);
            }
        });
        return backButton;
    }

    private JButton createBackToSpecificStoreStatisticsButton() {
        JButton backButton = new JButton("<");
        backButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        Font largeFont = new Font("Arial", Font.PLAIN, 18);
        backButton.setFont(largeFont);

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardPanel.removeAll();
                cardPanel.add(createViewSpecificStoreStatisticsPanel(), "View Specific Store Statistics");
                cardLayout.show(cardPanel, "View Specific Store Statistics");
            }
        });
        return backButton;
    }

    private JButton createBackToSpecificStoreStatisticsSaleTypeButton() {
        JButton backButton = new JButton("<");
        Font largeFont = new Font("Arial", Font.PLAIN, 18);
        backButton.setAlignmentX(Component.LEFT_ALIGNMENT);
        backButton.setFont(largeFont);

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardPanel.add(createViewSpecificStoreStatisticsSaleTypePanel(), "Specific Store Statistics Sort");
                cardLayout.show(cardPanel, "Specific Store Statistics Sort");
            }
        });
        return backButton;
    }

    private JButton createBackToContactCustomersButton() {
        JButton backButton = new JButton("<");
        Font largeFont = new Font("Arial", Font.PLAIN, 18);
        backButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        backButton.setFont(largeFont);

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardPanel.add(createContactCustomerPanel(), "Contact Customers");
                cardLayout.show(cardPanel, "Contact Customers");
            }
        });
        return backButton;
    }
}




