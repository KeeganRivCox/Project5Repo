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

public class SellerPanel {


    private JFrame frame;
    private JPanel cardPanel;

    private String editStoreName;

    private boolean editStoreFlag;

    private String editProductName;
    private String customerMessage;
    private JTextArea selectedCustomerMessageLabel;
    private CardLayout cardLayout;
    private String customerName;
    private String productName;
    private String specificStoreName;
    private String productInShoppingCartName;
    private JLabel selectedCustomerLabel;
    private JLabel selectedStoreLabel;
    private JLabel selectedStoreStatsLabel;

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
    final Color greyButtonColor = new Color(196,191,192);
    final Border customBorder = BorderFactory.createLineBorder(Color.BLACK, 3);


    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new SellerPanel());

    }

    public SellerPanel() {
        frame = new JFrame("Sellers");
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);
        cardPanel.add(createMainPanel(), "Main Page");
        cardPanel.add(createEditStorePanel(), "Edit Store");
        cardPanel.add(createListStorePanel(), "List Store");
        cardPanel.add(createContactCustomerPanel(), "Contact Customers");
        cardPanel.add(createProductListingsPanel(), "Product Listings");
        cardPanel.add(createStorePanel(), "Create Store");
        cardPanel.add(createIndividualProductPanel(), "Individual Products");
        cardPanel.add(createLogOutPanel(), "Log Out");
        cardPanel.add(createProductPanel(), "Create Product");
        cardPanel.add(createStoreStatisticsPanel(), "Store Statistics");
        cardPanel.add(createViewAllStoreStatisticsPanel(), "View All Store Sales");
        cardPanel.add(createViewSpecificStoreStatisticsPanel(), "View Specific Store Sales");
        cardPanel.add(createViewCustomerShoppingCarts(), "View Customer's Shopping Carts");
        cardPanel.add(createSellerInboxPanel(), "View Seller Inbox");
        cardPanel.add(editProductDetailsPanel(), "Edit Product Detail");

        frame.add(cardPanel);

        cardLayout.show(cardPanel, "Seller");
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }

    //method that creates the panel for the main page
    private JPanel createMainPanel() {
        frame.setSize(400, 500);
        JPanel mainPagePanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        mainPagePanel.setLayout(new BoxLayout(mainPagePanel, BoxLayout.Y_AXIS));
        mainPagePanel.setBackground(goldColor);

        ImageIcon imageIcon = new ImageIcon("images/BoilerBay1.png");
        JLabel imageLabel = new JLabel();
        imageLabel.setMinimumSize(new Dimension(100,100));
        imageLabel.setMaximumSize(new Dimension(100,100));
        imageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        imageLabel.setIcon(new ImageIcon(imageIcon.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT)));

//        JLabel applicationLabel = new JLabel("Boiler Bay");
//        applicationLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
//        applicationLabel.setFont(new Font("Arial", Font.BOLD, 24));

        JButton logOutButton = new JButton("Log Out");
        logOutButton.setMinimumSize(new Dimension(70,30));
        logOutButton.setMaximumSize(new Dimension(70,30));
        logOutButton.setFont(new Font("Arial", Font.PLAIN, 14));
        logOutButton.setBackground(greyButtonColor);
        logOutButton.setForeground(Color.BLACK);
        logOutButton.setBorder(customBorder);
        logOutButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "Log Out");
                frame.setSize(400,200);
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

        JButton listStoreButton = createButton("List\nStore");
        listStoreButton.setBackground(greyButtonColor);
        listStoreButton.setForeground(Color.BLACK);
        listStoreButton.setBorder(customBorder);
        listStoreButton.addActionListener(e -> {
            cardLayout.show(cardPanel, "List Store");
            frame.setSize(400, 400);
            frame.setLocationRelativeTo(null);

        });

        JButton createProductButton = createButton("Create\nProduct");
        createProductButton.setBackground(greyButtonColor);
        createProductButton.setForeground(Color.BLACK);
        createProductButton.setBorder(customBorder);
        createProductButton.addActionListener(e -> {
            cardLayout.show(cardPanel, "Create Product");
            frame.setSize(400, 400);
            frame.setLocationRelativeTo(null);

        });

        JButton createStoreButton = createButton("Create\nStore");
        createStoreButton.setBackground(greyButtonColor);
        createStoreButton.setForeground(Color.BLACK);
        createStoreButton.setBorder(customBorder);
        createStoreButton.addActionListener(e -> {
            cardLayout.show(cardPanel, "Create Store");
//            frame.setResizable(true);
            frame.setSize(400, 300);
            frame.setLocationRelativeTo(null);

        });

        JButton storeStatisticsButton = createButton("Store\nStatistics");
        storeStatisticsButton.setBackground(greyButtonColor);
        storeStatisticsButton.setForeground(Color.BLACK);
        storeStatisticsButton.setBorder(customBorder);
        storeStatisticsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "Store Statistics");
                frame.setSize(400, 350);
                frame.setLocationRelativeTo(null);  // Center the frame

            }
        });

        JButton editProductButton = createButton("Edit\nProduct");
        editProductButton.setBackground(greyButtonColor);
        editProductButton.setForeground(Color.BLACK);
        editProductButton.setBorder(customBorder);
        editProductButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "Edit Store");
                editStoreFlag = true;
                frame.setSize(400, 500);
                frame.setLocationRelativeTo(null);

            }
        });
        JButton editStoreButton = createButton("Edit\nStore");
        editStoreButton.setBackground(greyButtonColor);
        editStoreButton.setForeground(Color.BLACK);
        editStoreButton.setBorder(customBorder);
        editStoreButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "Edit Store");
                frame.setSize(400, 500);
                frame.setLocationRelativeTo(null);

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
                cardLayout.show(cardPanel, "Contact Customers");  // Show the "Contact Customers" panel
                frame.setSize(400, 500);  // Set the frame size accordingly
                frame.setLocationRelativeTo(null);  // Center the frame
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
        logOutPanel.setBackground(goldColor);
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
        noButton.setBackground(greyButtonColor);
        noButton.setForeground(Color.BLACK);
        noButton.setBorder(customBorder);
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
        yesButton.setBackground(greyButtonColor);
        yesButton.setForeground(Color.BLACK);
        yesButton.setBorder(customBorder);
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
        buttonPanel.setOpaque(false);
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
        JPanel listStorePanel = new JPanel();
        listStorePanel.setBackground(goldColor);
        listStorePanel.setLayout(new BoxLayout(listStorePanel, BoxLayout.Y_AXIS));

        // Back button
        JButton backButton2 = createBackToMenuButton();
        backButton2.setMaximumSize(new Dimension(50, 30));

        JLabel titleLabel = new JLabel("List of Stores");
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));

        // Panel for back button and title
        JPanel topPanel = new JPanel();
        topPanel.setOpaque(false);
        topPanel.setMaximumSize(new Dimension(400, 50));
        topPanel.setMinimumSize(new Dimension(400, 50));
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.X_AXIS));

        topPanel.add(Box.createHorizontalStrut(40));
        topPanel.add(backButton2);
        topPanel.add(Box.createHorizontalStrut(40));
        topPanel.add(titleLabel);
        topPanel.add(Box.createHorizontalStrut(20));


        JPanel storeNamePanel = new JPanel();
        storeNamePanel.setMinimumSize(new Dimension(250, 250));
        storeNamePanel.setMinimumSize(new Dimension(250,250));

        storeNamePanel.setOpaque(false);
        String[] dummyStores = new String[]{"Store One", "Store Two", "Store Three", "Store Four", "Store Five",
                "Store Six", "Store Seven", "Store Eight", "Store Nine", "Store Ten", "Store Eleven", "Store Twelve"};
        storeNamePanel.setLayout(new BoxLayout(storeNamePanel, BoxLayout.Y_AXIS));

        for (String name : dummyStores) {
            JLabel label = new JLabel(name);
            label.setAlignmentX(Component.CENTER_ALIGNMENT);
            label.setFont(new Font("Arial", Font.PLAIN, 18));

            storeNamePanel.add(label);
            storeNamePanel.add(new JSeparator(JSeparator.HORIZONTAL));
        }

        // Remove the last separator to avoid an extra line at the end
        storeNamePanel.remove(storeNamePanel.getComponentCount() - 1);

        JScrollPane lsp = new JScrollPane(storeNamePanel, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        lsp.setMaximumSize(new Dimension(350, 250));
        lsp.setMinimumSize(new Dimension(350,250));
        lsp.getViewport().setBackground(greyButtonColor);
        lsp.setBorder(customBorder);



        // Increase the height by adjusting the strut values
        listStorePanel.add(Box.createVerticalStrut(20));
        listStorePanel.add(topPanel);
        listStorePanel.add(Box.createVerticalStrut(20));
        listStorePanel.add(lsp);
        listStorePanel.add(Box.createVerticalStrut(20));

        return listStorePanel;
    }

    //method that creates the panel for seller listings page
    private JPanel createEditStorePanel() {
        JPanel editStorePanel = new JPanel();
        editStorePanel.setBackground(goldColor);
        editStorePanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        editStorePanel.setLayout(new BoxLayout(editStorePanel, BoxLayout.Y_AXIS));

        // Back button
        JButton backButton2 = createBackToMenuButton();
        backButton2.setMaximumSize(new Dimension(50, 30));

        JLabel titleLabel = new JLabel("Choose a Store");
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));

        // Panel for back button and title
        JPanel topPanel = new JPanel();
        topPanel.setOpaque(false);
        topPanel.setMaximumSize(new Dimension(400, 50));
        topPanel.setMinimumSize(new Dimension(400, 50));
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.X_AXIS));

        topPanel.add(Box.createHorizontalStrut(40));
        topPanel.add(backButton2);
        topPanel.add(Box.createHorizontalStrut(40));
        topPanel.add(titleLabel);
        topPanel.add(Box.createHorizontalStrut(20));


        JPanel storeNamePanel = new JPanel();
        storeNamePanel.setOpaque(false);
        storeNamePanel.setBackground(greyButtonColor);

        String[] dummyStores = new String[]{"Store One", "Store Two", "Store Three", "Store Four", "Store Five",
                "Store Six", "Store Seven", "Store Eight", "Store Nine", "Store Ten", "Store Eleven", "Store Twelve"};
        storeNamePanel.setLayout(new BoxLayout(storeNamePanel, BoxLayout.Y_AXIS));

        // Display selected store's name
        selectedStoreLabel = new JLabel("Selected Store: ");
        selectedStoreLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        selectedStoreLabel.setFont(new Font("Arial", Font.PLAIN, 18));

        for (String name : dummyStores) {
            JLabel label = new JLabel(name);
            label.setAlignmentX(Component.CENTER_ALIGNMENT);
            label.setFont(new Font("Arial", Font.PLAIN, 18));

            label.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    // Handle click event
                    editStoreName = label.getText();
//
                    selectedStoreLabel.setText("Selected Store: " + editStoreName);

                }
            });

            storeNamePanel.add(label);
            storeNamePanel.add(new JSeparator(JSeparator.HORIZONTAL));
        }

        // Remove the last separator to avoid an extra line at the end
        storeNamePanel.remove(storeNamePanel.getComponentCount() - 1);

        JScrollPane psp = new JScrollPane(storeNamePanel, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        psp.setMaximumSize(new Dimension(350, 250));
        psp.setMinimumSize(new Dimension(350, 250));
        psp.setBorder(customBorder);
        psp.getViewport().setBackground(greyButtonColor);

        // Confirm button

        JButton confirmButton = new JButton("Confirm");
        confirmButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        confirmButton.setMaximumSize(new Dimension(100,40));
        confirmButton.setMinimumSize(new Dimension(100,40));
        confirmButton.setFont(new Font("Arial", Font.PLAIN, 18));
        confirmButton.setBackground(greyButtonColor);
        confirmButton.setBorder(customBorder);
        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (editStoreName == null) {
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
                        frame.setSize(400, 400);
                    }
                }
            }
        });


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
        createStorePanel.setBackground(goldColor);
        createStorePanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        createStorePanel.setLayout(new BoxLayout(createStorePanel, BoxLayout.Y_AXIS));

        // Title
        JLabel titleLabel = new JLabel("Create Store");
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));

        // Back to Menu Button
        JButton backToMenuButton = createBackToMenuButton();

        // Top Panel
        JPanel topPanel = new JPanel();
        topPanel.setOpaque(false);
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.X_AXIS));
        topPanel.setMaximumSize(new Dimension(400,50));
        topPanel.setMinimumSize(new Dimension(400,50));

        topPanel.add(Box.createHorizontalStrut(20));
        topPanel.add(backToMenuButton);
        topPanel.add(Box.createHorizontalStrut(50));
        topPanel.add(titleLabel);
        topPanel.add(Box.createHorizontalStrut(20));

        // Form Panel
        JPanel formPanel = new JPanel(new GridLayout(1, 1, 10, 10));
        formPanel.setOpaque(false);
        formPanel.setMinimumSize(new Dimension(325,100));
        formPanel.setMaximumSize(new Dimension(325,100));

        // Name
        JTextField nameTextField = new JTextField(20);
        nameTextField.setBackground(greyButtonColor);
        nameTextField.setMaximumSize(new Dimension(325,50));
        nameTextField.setMinimumSize(new Dimension(325,50));

        nameTextField.setFont(new Font("Arial", Font.PLAIN, 16));

        formPanel.add(createFieldWithLabel("Name:", nameTextField));

        // Create Store Button
        JButton createStoreButton = new JButton("Create Store");
        createStoreButton.setBorder(customBorder);
        createStoreButton.setBackground(greyButtonColor);
        createStoreButton.setFont(new Font("Arial", Font.PLAIN, 16));
        createStoreButton.setMaximumSize(new Dimension(200,30));
        createStoreButton.setMinimumSize(new Dimension(200,30));
        createStoreButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        createStoreButton.addActionListener(e -> {
            // Handle the creation of the store here
            String name = nameTextField.getText().trim();

            // Validate Name
            if (name.isEmpty()) {
                JOptionPane.showMessageDialog(createStorePanel, "Name cannot be empty", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Set the editStoreName
            editStoreName = name;

            // Open "Create Product" card
            cardLayout.show(cardPanel, "Create Product");
//            frame.setResizable(true);
            frame.setSize(400, 400);
        });

        // Add components to the form panel
        createStorePanel.add(Box.createVerticalStrut(20));
        createStorePanel.add(topPanel);
        createStorePanel.add(Box.createVerticalStrut(20));
        createStorePanel.add(formPanel);
        createStoreButton.add(Box.createVerticalStrut(40));
        createStorePanel.add(createStoreButton);
        createStoreButton.add(Box.createVerticalStrut(20));

        return createStorePanel;
    }

    private JPanel createEditProductPanel() {
        JPanel editProductPanel = new JPanel();
        editProductPanel.setBackground(goldColor);

        editProductPanel.setLayout(new BoxLayout(editProductPanel, BoxLayout.Y_AXIS));

        // Back button
        JButton backButtonProduct = createBackToMenuButton();

        Dimension titleDimension = new Dimension(300, 50);
        JLabel titleLabelProduct = new JLabel("Select a Product from " + editStoreName);
        titleLabelProduct.setMinimumSize(titleDimension);
        titleLabelProduct.setMaximumSize(titleDimension);
        titleLabelProduct.setAlignmentX(Component.CENTER_ALIGNMENT);
        titleLabelProduct.setFont(new Font("Arial", Font.BOLD, 16));

        // Panel for back button and title
        JPanel titlePanelProduct = new JPanel();
        titlePanelProduct.setOpaque(false);
        titlePanelProduct.setLayout(new BoxLayout(titlePanelProduct, BoxLayout.X_AXIS));

        titlePanelProduct.add(Box.createHorizontalStrut(20));
        titlePanelProduct.add(backButtonProduct);  // Align the back button to the left
        titlePanelProduct.add(Box.createHorizontalStrut(20));
        titlePanelProduct.add(titleLabelProduct);  // Center-align the title
        titlePanelProduct.add(Box.createHorizontalStrut(20));

        JPanel storeNamePanelProduct = new JPanel();
        storeNamePanelProduct.setBackground(greyButtonColor);
        storeNamePanelProduct.setOpaque(false);

        String[] dummyStoresItem = new String[]{"Item One", "Item Two", "Item Three", "Item Four", "Item Five",
                "Item Six", "Item Seven", "Item Eight", "Item Nine", "Item Ten", "Item Eleven", "Item Twelve"};
        storeNamePanelProduct.setLayout(new BoxLayout(storeNamePanelProduct, BoxLayout.Y_AXIS));

        JLabel selectedStoreLabelProduct = new JLabel("Selected Product: ");
        selectedStoreLabelProduct.setAlignmentX(Component.CENTER_ALIGNMENT);
        selectedStoreLabelProduct.setFont(new Font("Arial", Font.PLAIN, 18));

        for (String name : dummyStoresItem) {
            JLabel label = new JLabel(name);
            label.setAlignmentX(Component.CENTER_ALIGNMENT);
            label.setFont(new Font("Arial", Font.PLAIN, 18));

            label.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    // Handle click event
                    editProductName = label.getText();
                    // Update the display with the selected product's name
                    selectedStoreLabelProduct.setText("Selected Product: " + editProductName);
                }
            });


            storeNamePanelProduct.add(label);
            storeNamePanelProduct.add(new JSeparator(JSeparator.HORIZONTAL));
        }

        // Remove the last separator to avoid an extra line at the end
        storeNamePanelProduct.remove(storeNamePanelProduct.getComponentCount() - 1);

        JScrollPane pspProduct = new JScrollPane(storeNamePanelProduct, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        pspProduct.setMinimumSize(new Dimension(350, 250));
        pspProduct.setMaximumSize(new Dimension(350, 250));
        pspProduct.getViewport().setBackground(greyButtonColor);
        pspProduct.setBorder(customBorder);


        // Confirm button
        JButton confirmButtonProduct = new JButton("Confirm");
        confirmButtonProduct.setAlignmentX(Component.CENTER_ALIGNMENT);
        confirmButtonProduct.setFont(new Font("Arial", Font.PLAIN, 18));
        confirmButtonProduct.setBorder(customBorder);
        confirmButtonProduct.setBackground(greyButtonColor);
        confirmButtonProduct.setMinimumSize(new Dimension(200,40));
        confirmButtonProduct.setMaximumSize(new Dimension(200,40));
        confirmButtonProduct.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (editProductName == null) {
                    JOptionPane.showMessageDialog(createEditProductPanel(), "Please select a Product first.", "Error", JOptionPane.ERROR_MESSAGE);

                } else {
                    // Handle confirmation action
                    cardLayout.show(cardPanel, "Edit Product Detail");
                    frame.setSize(400, 500);
                }
            }
        });

        // Increase the height by adjusting the strut values
        editProductPanel.add(Box.createVerticalStrut(20));
        editProductPanel.add(titlePanelProduct);
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





    private String editCustName;  // Variable to store the selected seller's name

    private JPanel createContactCustomerPanel() {
        JPanel contactSellerPanel = new JPanel();
        contactSellerPanel.setBackground(goldColor);

        contactSellerPanel.setLayout(new BoxLayout(contactSellerPanel, BoxLayout.Y_AXIS));

        // Back button
        JButton backButton = createBackToMenuButton();

        // title label
        JLabel titleLabel = new JLabel("Message a Customer");
        titleLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));

        // inbox button
        Dimension inboxDimension = new Dimension(85,25);
        JButton inboxButton = new JButton("Inbox");
        inboxButton.setMaximumSize(inboxDimension);
        inboxButton.setMinimumSize(inboxDimension);
        inboxButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        inboxButton.setFont(new Font("Arial", Font.BOLD, 16));
        inboxButton.setBackground(greyButtonColor);
        inboxButton.setBorder(customBorder);
        inboxButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "View Seller Inbox");
                frame.setSize(400, 600);
                frame.setLocationRelativeTo(null);
            }
        });

        // Panel for back button and title
        JPanel titlePanel = new JPanel();
        titlePanel.setOpaque(false);
        titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.X_AXIS));  // Use BorderLayout for the title panel
        titlePanel.add(backButton);  // Align the back button to the left
        titlePanel.add(Box.createHorizontalStrut(30));  // Add space between back button and title
        titlePanel.add(titleLabel);  // Center-align the title
        titlePanel.add(Box.createHorizontalStrut(20));
        titlePanel.add(inboxButton);

        JPanel customerNamePanel = new JPanel();
        customerNamePanel.setOpaque(false);
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
        jsp.setMaximumSize(new Dimension(350, 300));
        jsp.setMinimumSize(new Dimension(350,300));
        selectedCustomerLabel = new JLabel("Selected Customer: ");
        selectedCustomerLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        selectedCustomerLabel.setFont(new Font("Arial", Font.PLAIN, 16));

        // Container for the message components
        JPanel messagePanel = new JPanel();
        messagePanel.setOpaque(false);
        messagePanel.setLayout(new BoxLayout(messagePanel, BoxLayout.Y_AXIS));

        // Label above JTextArea
        JLabel messageLabel = new JLabel("What's your message?");
        messageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        messageLabel.setFont(new Font("Arial", Font.PLAIN, 16));

        // JTextArea for entering the message
        JTextArea messageTextArea = new JTextArea();
        messageTextArea.setLineWrap(true);
        messageTextArea.setWrapStyleWord(true);
        messageTextArea.setFont(new Font("Arial", Font.PLAIN, 16));
        messageTextArea.setBackground(greyButtonColor);

        JScrollPane messageScrollPane = new JScrollPane(messageTextArea);
        messageScrollPane.setMinimumSize(new Dimension(350, 300));
        messageScrollPane.setMaximumSize(new Dimension(350, 300));
        messageScrollPane.getViewport().setBackground(greyButtonColor);
        messageScrollPane.setBorder(customBorder);

        // Button to send the message
        JButton sendMessageButton = new JButton("Send Message");
        sendMessageButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        sendMessageButton.setBorder(customBorder);
        sendMessageButton.setBackground(greyButtonColor);

        sendMessageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle sending the message
                String message = messageTextArea.getText();

                if (editCustName == null ) {
                    JOptionPane.showMessageDialog(null, "Error! Please select a customer before sending a message.");
                } else if (messageTextArea.getText().equals("")){
                    JOptionPane.showMessageDialog(null, "Error! Please write a message before sending to a customer.");
                } else {
                    // testing purposes
                    System.out.println("Sending message to " + editCustName + ": " + message);
                }

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

    private JPanel createSellerInboxPanel() {
        JPanel sellerInboxPanel = new JPanel();
        sellerInboxPanel.setBackground(goldColor);

        sellerInboxPanel.setLayout(new BoxLayout(sellerInboxPanel, BoxLayout.Y_AXIS));

        JButton backToContactCustomersButton = createBackToContactCustomersButton();
        backToContactCustomersButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        backToContactCustomersButton.setFont(new Font("Arial", Font.PLAIN, 18));

        JLabel sellerInboxLabel = new JLabel("Seller Inbox");
        sellerInboxLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        sellerInboxLabel.setFont(new Font("Arial", Font.BOLD, 24));

        Dimension topDimension = new Dimension(500, 75);
        JPanel topPanel = new JPanel();
        topPanel.setOpaque(false);
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.X_AXIS));
        topPanel.setMinimumSize(topDimension);
        topPanel.setMaximumSize(topDimension);
        topPanel.add(Box.createHorizontalStrut(75));
        topPanel.add(backToContactCustomersButton);
        topPanel.add(Box.createHorizontalStrut(30));
        topPanel.add(sellerInboxLabel);
        topPanel.add(Box.createHorizontalStrut(20));

        JPanel customerMessagesPanel = new JPanel();
        customerMessagesPanel.setOpaque(false);
        String[] dummyCustomersMessage = new String[]{"Customer One is a very long paragraph of a man far far away and off he went the galaxy and met a man named joeadfaj;fsifnaenfpianifdnfaidfnasodjfklajdfjasdkmagic in the room tell me what are we gonna do if you feeling the rush can yousay yes or no", "Customer Two", "Customer Three", "Customer Four", "Customer Five",
                "Customer Six", "Customer Seven is a peculiar fellow in the sense that he likes small cats as opposed to big cats largely because of the way they smell for some particular reason and i would hate to say it buts its rateher true in a ll senses long live Josh Hutcherson yall", "Customer Eight", "Customer Nine", "Customer Ten", "Customer Eleven", "Customer Twelve", "Adeetya, Jordan, Robert, Keegan, Natalie"};
        customerMessagesPanel.setLayout(new BoxLayout(customerMessagesPanel, BoxLayout.Y_AXIS));
        customerMessagesPanel.add(Box.createHorizontalStrut(40));
        for (String message : dummyCustomersMessage) {
            JLabel label = new JLabel(message);
            label.setAlignmentX(Component.LEFT_ALIGNMENT);
            label.setFont(new Font("Arial", Font.BOLD, 18));
            label.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    // Handle click event
                    customerMessage = label.getText();
                    label.setFont(new Font("Arial", Font.PLAIN, 18));
                    // Update the display with the selected customer's name
                    selectedCustomerMessageLabel.setText(customerMessage);
                }
            });

            customerMessagesPanel.add(label);
            customerMessagesPanel.add(new JSeparator(JSeparator.HORIZONTAL));
        }

        // Remove the last separator to avoid an extra line at the end
        customerMessagesPanel.remove(customerMessagesPanel.getComponentCount() - 1);

        JScrollPane jsp = new JScrollPane(customerMessagesPanel, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        Dimension jspDimension = new Dimension(350, 300);
        jsp.setMaximumSize(jspDimension);
        jsp.setMinimumSize(jspDimension);
        jsp.getViewport().setBackground(greyButtonColor);
        jsp.setBorder(customBorder);

        JLabel selectedMessageCustomerName = new JLabel("Customer # Message");
        selectedMessageCustomerName.setAlignmentX(Component.CENTER_ALIGNMENT);
        selectedMessageCustomerName.setFont(new Font("Arial", Font.ITALIC, 16));

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

        JScrollPane messageScrollPane = new JScrollPane(selectedCustomerMessageLabel);
        messageScrollPane.setMaximumSize(new Dimension(350, 300));
        messageScrollPane.setMinimumSize(new Dimension(350,300));
        customerFullMessagePanel.add(messageScrollPane);

        sellerInboxPanel.add(Box.createVerticalStrut(20));
        sellerInboxPanel.add(topPanel);
        sellerInboxPanel.add(Box.createVerticalStrut(20));
        sellerInboxPanel.add(jsp);
        sellerInboxPanel.add(Box.createVerticalStrut(20));
        sellerInboxPanel.add(selectedMessageCustomerName);
        sellerInboxPanel.add(Box.createVerticalStrut(15));
        sellerInboxPanel.add(customerFullMessagePanel);
        sellerInboxPanel.add(Box.createVerticalStrut(20));

        return sellerInboxPanel;
    }

    private JPanel createEditStoreDetailsPanel() {
        JPanel editStoreDetailsPanel = new JPanel();
        editStoreDetailsPanel.setBackground(goldColor);
        editStoreDetailsPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        editStoreDetailsPanel.setLayout(new BoxLayout(editStoreDetailsPanel, BoxLayout.Y_AXIS));

        // Back button
        JButton backButton = createBackToEditStoreButton();

        String storeText = "Edit " + editStoreName + "'s Panel";
        JLabel titleLabel = new JLabel(storeText);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));

        // Panel for back button and title
        JPanel titlePanel = new JPanel();
        titlePanel.setOpaque(false);
        titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.X_AXIS));
        titlePanel.setMaximumSize(new Dimension(400,50));
        titlePanel.setMinimumSize(new Dimension(400,50));

        titlePanel.add(Box.createHorizontalStrut(20));
        titlePanel.add(backButton);
        titlePanel.add(Box.createHorizontalStrut(30));
        titlePanel.add(titleLabel);
        titlePanel.add(Box.createHorizontalStrut(20));


        // Buttons for various actions
        JButton addProductButton = createButton("Add Product");
        addProductButton.setBorder(customBorder);
        addProductButton.setBackground(greyButtonColor);
        addProductButton.setMinimumSize(new Dimension(200,40));
        addProductButton.setMaximumSize(new Dimension(200,40));
        addProductButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        addProductButton.addActionListener(e -> {
            cardLayout.show(cardPanel, "Create Product");
//            frame.setResizable(true);
            frame.setSize(400, 400);
        });


        JButton csvImportButton = createButton("CSV Import");
        csvImportButton.setBorder(customBorder);
        csvImportButton.setBackground(greyButtonColor);
        csvImportButton.setMinimumSize(new Dimension(200,40));
        csvImportButton.setMaximumSize(new Dimension(200,40));
        csvImportButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        csvImportButton.addActionListener(e -> handleCSVImport());

        JButton deleteStoreButton = createButton("Delete Store");
        deleteStoreButton.setBorder(customBorder);
        deleteStoreButton.setBackground(greyButtonColor);
        deleteStoreButton.setMinimumSize(new Dimension(200,40));
        deleteStoreButton.setMaximumSize(new Dimension(200,40));
        deleteStoreButton.setAlignmentX(Component.CENTER_ALIGNMENT);
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
        changeStoreNameButton.setBorder(customBorder);
        changeStoreNameButton.setBackground(greyButtonColor);
        changeStoreNameButton.setMinimumSize(new Dimension(200,40));
        changeStoreNameButton.setMaximumSize(new Dimension(200,40));
        changeStoreNameButton.setAlignmentX(Component.CENTER_ALIGNMENT);

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
        editStoreProductsButton.setBorder(customBorder);
        editStoreProductsButton.setBackground(greyButtonColor);
        editStoreProductsButton.setMinimumSize(new Dimension(200,40));
        editStoreProductsButton.setMaximumSize(new Dimension(200,40));
        editStoreProductsButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        editStoreProductsButton.addActionListener(e -> {
            cardPanel.add(createEditProductPanel(), "Edit Product");
            cardLayout.show(cardPanel, "Edit Product");
            frame.setSize(400, 500);
            editStoreFlag = false;
        });

        // Action listeners for the buttons
        addProductButton.addActionListener(e -> System.out.println("Adding product to store: " + editStoreName));
        csvImportButton.addActionListener(e -> System.out.println("CSV Import for store: " + editStoreName));
        changeStoreNameButton.addActionListener(e -> System.out.println("Changing store name for: " + editStoreName));
        editStoreProductsButton.addActionListener(e -> System.out.println("Editing products for store: " + editStoreName));


        // Adding components to the panel
        editStoreDetailsPanel.add(Box.createVerticalStrut(20));
        editStoreDetailsPanel.add(titlePanel);
        editStoreDetailsPanel.add(Box.createVerticalStrut(20));
        editStoreDetailsPanel.add(addProductButton);
        editStoreDetailsPanel.add(Box.createVerticalStrut(10));
        editStoreDetailsPanel.add(csvImportButton);
        editStoreDetailsPanel.add(Box.createVerticalStrut(10));
        editStoreDetailsPanel.add(deleteStoreButton);
        editStoreDetailsPanel.add(Box.createVerticalStrut(10));
        editStoreDetailsPanel.add(changeStoreNameButton);
        editStoreDetailsPanel.add(Box.createVerticalStrut(10));
        editStoreDetailsPanel.add(editStoreProductsButton);
        editStoreDetailsPanel.add(Box.createVerticalStrut(20));


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

    private JPanel createProductPanel() {
        JPanel createProductPanel = new JPanel();
        createProductPanel.setBackground(goldColor);
        createProductPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        createProductPanel.setLayout(new BoxLayout(createProductPanel, BoxLayout.Y_AXIS));

        // Title
        JLabel titleLabel = new JLabel("Create Product");
        titleLabel.setMinimumSize(new Dimension(250,50));
        titleLabel.setMaximumSize(new Dimension(250,50));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));

        // Back to Menu Button
        JButton backToMenuButton = createBackToMenuButton();

        // Top Panel
        JPanel topPanel = new JPanel();
        topPanel.setOpaque(false);
        topPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.X_AXIS));
        topPanel.setMinimumSize(new Dimension(400,50));
        topPanel.setMaximumSize(new Dimension(400,50));

        topPanel.add(Box.createHorizontalStrut(20));
        topPanel.add(backToMenuButton);
        topPanel.add(Box.createHorizontalStrut(50));
        topPanel.add(titleLabel);
        topPanel.add(Box.createHorizontalStrut(20));

        // Form Panel
        JPanel formPanel = new JPanel(new GridLayout(5, 1, 10, 10));
        formPanel.setOpaque(false);

        // Name
        JTextField nameTextField = new JTextField(20);
        nameTextField.setFont(new Font("Arial",Font.PLAIN,16));
        formPanel.add(createFieldWithLabel("Name:", nameTextField));

        // Description
        JTextArea descriptionTextArea = new JTextArea(8, 20); // Increase the number of rows
        descriptionTextArea.setLineWrap(true);
        descriptionTextArea.setWrapStyleWord(true);
        descriptionTextArea.setFont(new Font("Arial",Font.PLAIN,16));
        JScrollPane descriptionScrollPane = new JScrollPane(descriptionTextArea);
//        descriptionScrollPane.setMaximumSize(new Dimension(350, 300)); // Adjust the size
//        descriptionScrollPane.setMinimumSize(new Dimension(350,300));

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
        createProductButton.setFont(new Font("Arial", Font.PLAIN, 16));
        createProductButton.setMinimumSize(new Dimension(200,30));
        createProductButton.setMaximumSize(new Dimension(200,30));
        createProductButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        createProductButton.setBorder(customBorder);
        createProductButton.setBackground(greyButtonColor);
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

            // Confirm creation
            String selectedStore = (String) storeComboBox.getSelectedItem();
            int confirmResult = JOptionPane.showConfirmDialog(createProductPanel,
                    "Create the following product?\n\n" +
                            "Name: " + name + "\n" +
                            "Description: " + description + "\n" +
                            "Price: " + price + "\n" +
                            "Quantity: " + quantity + "\n" +
                            "Store: " + selectedStore,
                    "Confirm Product Creation", JOptionPane.YES_NO_OPTION);

            if (confirmResult == JOptionPane.YES_OPTION) {
                // User clicked 'Yes', process creation
                // testing purposes
                System.out.println("Product created:");
                System.out.println("Name: " + name);
                System.out.println("Description: " + description);
                System.out.println("Price: " + price);
                System.out.println("Quantity: " + quantity);
                System.out.println("Store: " + selectedStore);

                // Reset the create product panel
                nameTextField.setText("");
                descriptionTextArea.setText("");
                priceSpinner.setValue(0.0);
                quantitySpinner.setValue(1);
                storeComboBox.setSelectedIndex(0);
            } else {
                // User clicked 'No', do nothing or handle accordingly
                System.out.println("Product creation cancelled by user.");
            }
        });

        // Add components to the form panel
        createProductPanel.add(Box.createVerticalStrut(10));
        createProductPanel.add(topPanel);
        createProductPanel.add(Box.createVerticalStrut(10));
        createProductPanel.add(formPanel);
        createProductPanel.add(Box.createVerticalStrut(20));
        createProductPanel.add(createProductButton);
        createProductPanel.add(Box.createVerticalStrut(20));


        return createProductPanel;
    }

    private JPanel editProductDetailsPanel() {
        JPanel createProductPanel = new JPanel();
        createProductPanel.setBackground(goldColor);
        createProductPanel.setLayout(new BoxLayout(createProductPanel, BoxLayout.Y_AXIS));

        // Title
        JLabel titleLabel = new JLabel("Edit Product: " + editProductName);
        titleLabel.setMaximumSize(new Dimension(250,50));
        titleLabel.setMinimumSize(new Dimension(250,50));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));

        // Back to Menu Button
        JButton backToMenuButton = createBackToMenuButton();

        // Top Panel
        JPanel topPanel = new JPanel();
        topPanel.setOpaque(false);
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.X_AXIS));
        topPanel.setMinimumSize(new Dimension(400,50));
        topPanel.setMaximumSize(new Dimension(400,50));

        topPanel.add(Box.createHorizontalStrut(20));
        topPanel.add(backToMenuButton);
        topPanel.add(Box.createHorizontalStrut(40));
        topPanel.add(titleLabel);
        topPanel.add(Box.createHorizontalStrut(20));

        // Form Panel
        JPanel formPanel = new JPanel(new GridLayout(5, 1, 10, 10));
        formPanel.setOpaque(false);
        formPanel.setMaximumSize(new Dimension(350,200));
        formPanel.setMinimumSize(new Dimension(350,200));

        // Name
        JTextField nameTextField = new JTextField();
        nameTextField.setText(editProductName); // Autofill the name field
        nameTextField.setFont(new Font("Arial", Font.PLAIN, 16));
        formPanel.add(createFieldWithLabel("Name:", nameTextField));

        // Description
        JTextArea descriptionTextArea = new JTextArea(8, 20); // Increase the number of rows
        descriptionTextArea.setLineWrap(true);
        descriptionTextArea.setWrapStyleWord(true);
        descriptionTextArea.setFont(new Font("Arial", Font.PLAIN, 16));
        JScrollPane descriptionScrollPane = new JScrollPane(descriptionTextArea);
//        descriptionScrollPane.setPreferredSize(new Dimension(250, 120)); // Adjust the size
        formPanel.add(createFieldWithLabel("Description:", descriptionScrollPane));

        // Price
        DoubleSpinner priceSpinner = createDoubleSpinner();
        formPanel.add(createFieldWithLabel("Price:", priceSpinner));

        // Quantity
        SpinnerModel quantityModel = new SpinnerNumberModel(1, 1, Integer.MAX_VALUE, 1);
        JSpinner quantitySpinner = new JSpinner(quantityModel);
        formPanel.add(createFieldWithLabel("Quantity:", quantitySpinner));

        // Store Label
        JLabel storeLabel = new JLabel(" Store:               " + editStoreName);
        storeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        storeLabel.setFont(new Font("Arial", Font.ITALIC, 18));
        formPanel.add(storeLabel);


        // Edit Product Button
        JButton editProductButton = new JButton("Edit Product");
        editProductButton.setFont(new Font("Arial", Font.PLAIN, 18));
        editProductButton.setBorder(customBorder);
        editProductButton.setBackground(greyButtonColor);
        editProductButton.setMaximumSize(new Dimension(200,30));
        editProductButton.setMinimumSize(new Dimension(200,30));
        editProductButton.setAlignmentX(Component.CENTER_ALIGNMENT);
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
                    JOptionPane.showMessageDialog(createProductPanel, "Quantity must be at least 1", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(createProductPanel, "Invalid quantity format", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Confirm creation
            int confirmResult = JOptionPane.showConfirmDialog(createProductPanel,
                    "Create the following product?\n\n" +
                            "Name: " + name + "\n" +
                            "Description: " + description + "\n" +
                            "Price: " + price + "\n" +
                            "Quantity: " + quantity + "\n" +
                            "Store: " + editStoreName,
                    "Confirm Product Creation", JOptionPane.YES_NO_OPTION);

            if (confirmResult == JOptionPane.YES_OPTION) {
                // User clicked 'Yes', process creation
                // testing purposes
                System.out.println("Product created:");
                System.out.println("Name: " + name);
                System.out.println("Description: " + description);
                System.out.println("Price: " + price);
                System.out.println("Quantity: " + quantity);
                System.out.println("Store: " + editStoreName);

                // Reset the create product panel
                nameTextField.setText("");
                descriptionTextArea.setText("");
                priceSpinner.setValue(0.0);
                quantitySpinner.setValue(1);
                cardPanel.add(createEditProductPanel(), "Edit Product");
                cardLayout.show(cardPanel, "Edit Product");
                frame.setSize(400, 500);
                editStoreFlag = false;
            } else {
                // User clicked 'No', do nothing or handle accordingly
                // testing purposes
                System.out.println("Product creation cancelled by user.");
            }
        });

        JButton deleteProductButton = new JButton("Delete Product");
        deleteProductButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        deleteProductButton.setFont(new Font("Arial", Font.PLAIN, 18));
        deleteProductButton.setBackground(greyButtonColor);
        deleteProductButton.setBorder(customBorder);
        deleteProductButton.setMaximumSize(new Dimension(200,30));
        deleteProductButton.setMinimumSize(new Dimension(200,30));
        deleteProductButton.addActionListener(e -> {
            // Handle the deletion of the product here
            int deleteResult = JOptionPane.showConfirmDialog(createProductPanel,
                    "Are you sure you want to delete the product?\n\n" +
                            "Name: " + editProductName + "\n" +
                            "Store: " + editStoreName,
                    "Confirm Product Deletion", JOptionPane.YES_NO_OPTION);

            if (deleteResult == JOptionPane.YES_OPTION) {
                // User clicked 'Yes', process deletion
                String deletionMessage = "Product deleted:\n" +
                        "Name: " + editProductName + "\n" +
                        "Store: " + editStoreName;
                JOptionPane.showMessageDialog(createProductPanel, deletionMessage, "Product Deletion", JOptionPane.INFORMATION_MESSAGE);

                cardLayout.show(cardPanel, "Main Page");
                frame.setSize(400,500);

                // You can add logic to delete the product from your data structure
                // and update the UI accordingly
            } else {
                // User clicked 'No', do nothing or handle accordingly
                System.out.println("Product deletion cancelled by user.");
            }
        });


        // Add components to the form panel
        createProductPanel.add(Box.createVerticalStrut(10));
        createProductPanel.add(topPanel);
        createProductPanel.add(Box.createVerticalStrut(20));
        createProductPanel.add(formPanel);
        createProductPanel.add(Box.createVerticalStrut(20));
        createProductPanel.add(editProductButton);
        createProductPanel.add(Box.createVerticalStrut(20));
        createProductPanel.add(deleteProductButton);
        createProductPanel.add(Box.createVerticalStrut(20));

        return createProductPanel;
    }

    private JPanel createStoreStatisticsPanel() {
        JPanel createStoreStatistics = new JPanel();
        createStoreStatistics.setBackground(goldColor);
        createStoreStatistics.setAlignmentX(Component.CENTER_ALIGNMENT);
        createStoreStatistics.setLayout(new BoxLayout(createStoreStatistics, BoxLayout.Y_AXIS));

        JLabel titleLabel = new JLabel("Select a Statistic to View");
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));

        JPanel topPanel = new JPanel();
        topPanel.setOpaque(false);
        Dimension topPanelDimension = new Dimension(400, 50);
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.X_AXIS));
        topPanel.setMaximumSize(topPanelDimension);
        topPanel.setMinimumSize(topPanelDimension);
        topPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        topPanel.add(Box.createHorizontalStrut(20));
        topPanel.add(createBackToMenuButton());
        topPanel.add(Box.createHorizontalStrut(30));
        topPanel.add(titleLabel);
        topPanel.add(Box.createHorizontalStrut(20));

        Dimension buttonDimension = new Dimension(300, 50);
        JButton storeSalesButton = new JButton(("View All Store Sales"));
        storeSalesButton.setMaximumSize(buttonDimension);
        storeSalesButton.setMinimumSize(buttonDimension);
        storeSalesButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        storeSalesButton.setBackground(greyButtonColor);
        storeSalesButton.setBorder(customBorder);
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
        specificStoreSalesButton.setBackground(greyButtonColor);
        specificStoreSalesButton.setBorder(customBorder);
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
        customerShoppingCartButton.setBackground(greyButtonColor);
        customerShoppingCartButton.setBorder(customBorder);
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
        createViewAllStoreStatistics.setBackground(goldColor);
        createViewAllStoreStatistics.setLayout(new BoxLayout(createViewAllStoreStatistics, BoxLayout.Y_AXIS));
        createViewAllStoreStatistics.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel titleLabel = new JLabel("Sales From All Stores");
        titleLabel.setMaximumSize(new Dimension(250,50));
        titleLabel.setMinimumSize(new Dimension(250,50));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));

        JLabel totalSellerRevenue = new JLabel("Temporary Seller's Total Revenue: " + "$0"); // temporary // needs to sum up total sales of all stores
        totalSellerRevenue.setAlignmentX(Component.CENTER_ALIGNMENT);
        totalSellerRevenue.setFont(new Font("Arial", Font.BOLD, 16));

        JPanel topPanel = new JPanel();
        topPanel.setOpaque(false);
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.X_AXIS));
        Dimension topPanelDimension = new Dimension(400, 50);
        topPanel.setMaximumSize(topPanelDimension);
        topPanel.setMinimumSize(topPanelDimension);
        topPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        topPanel.add(Box.createHorizontalStrut(10));
        topPanel.add(createBackToStoreStatisticsButton());
        topPanel.add(Box.createHorizontalStrut(40));
        topPanel.add(titleLabel);
        topPanel.add(Box.createHorizontalStrut(20));

        JPanel middlePanel = new JPanel();
        middlePanel.setOpaque(false);
        middlePanel.setMaximumSize(topPanelDimension);
        middlePanel.setMinimumSize(topPanelDimension);
        middlePanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        middlePanel.add(totalSellerRevenue);
        middlePanel.add(Box.createHorizontalStrut(10));


        Dimension buttonDimension = new Dimension(300, 70);

        JPanel storeNamePanel = new JPanel();
        storeNamePanel.setBackground(greyButtonColor);
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
        psp.setMinimumSize(new Dimension(350, 250));
        psp.setMaximumSize(new Dimension(350, 250));
        psp.getViewport().setBackground(greyButtonColor);
        psp.setBackground(greyButtonColor);
        psp.setBorder(customBorder);

        selectedAllStoreLabel = new JLabel("Selected Store: ");
        selectedAllStoreLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        selectedAllStoreLabel.setFont(new Font("Arial", Font.PLAIN, 18));

        createViewAllStoreStatistics.add(Box.createVerticalStrut(20));
        createViewAllStoreStatistics.add(topPanel);
        createViewAllStoreStatistics.add(Box.createVerticalStrut(10));
        createViewAllStoreStatistics.add(middlePanel);
        createViewAllStoreStatistics.add(Box.createVerticalStrut(10));
        createViewAllStoreStatistics.add(psp);
        createViewAllStoreStatistics.add(Box.createVerticalStrut(20));
        createViewAllStoreStatistics.add(selectedAllStoreLabel);
        createViewAllStoreStatistics.add(Box.createVerticalStrut(20));

        return createViewAllStoreStatistics;
    }

    // View a specific Store's Statistics (selects from store)
    private JPanel createViewSpecificStoreStatisticsPanel() {
        JPanel createViewSpecificStoreStatistics = new JPanel();
        createViewSpecificStoreStatistics.setBackground(goldColor);

        createViewSpecificStoreStatistics.setLayout(new BoxLayout(createViewSpecificStoreStatistics, BoxLayout.Y_AXIS));

        JLabel titleLabel = new JLabel("Select a Store to View a Statistic");
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));

        JPanel topPanel = new JPanel();
        topPanel.setOpaque(false);
        Dimension topPanelDimension = new Dimension(600, 50);
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.X_AXIS));
        topPanel.setMaximumSize(topPanelDimension);
        topPanel.setMinimumSize(topPanelDimension);
        topPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        topPanel.add(Box.createHorizontalStrut(20));
        topPanel.add(createBackToStoreStatisticsButton());
        topPanel.add(Box.createHorizontalStrut(50));
        topPanel.add(titleLabel);
        topPanel.add(Box.createHorizontalStrut(20));

        Dimension buttonDimension = new Dimension(300, 70);

        JPanel storeNamePanel = new JPanel();
        storeNamePanel.setBackground(greyButtonColor);
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
                    selectedStoreStatsLabel.setText("Selected Store: " + editStoreName);
                }
            });

            storeNamePanel.add(label);
            storeNamePanel.add(new JSeparator(JSeparator.HORIZONTAL));
        }

        // Remove the last separator to avoid an extra line at the end
        storeNamePanel.remove(storeNamePanel.getComponentCount() - 1);

        JScrollPane psp = new JScrollPane(storeNamePanel, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        // Display selected store's name
        psp.setMaximumSize(new Dimension(500, 250));
        psp.setMinimumSize(new Dimension(500, 250));
        psp.getViewport().setBackground(greyButtonColor);
        psp.setBorder(customBorder);

        selectedStoreStatsLabel = new JLabel("Selected Store: ");
        selectedStoreStatsLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        selectedStoreStatsLabel.setFont(new Font("Arial", Font.PLAIN, 18));

        // Confirm button
        JButton confirmButton = new JButton("Confirm");
        confirmButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        confirmButton.setMaximumSize(new Dimension(200,30));
        confirmButton.setMinimumSize(new Dimension(200,30));
        confirmButton.setFont(new Font("Arial", Font.PLAIN, 18));
        confirmButton.setBorder(customBorder);
        confirmButton.setBackground(greyButtonColor);
        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle confirmation action
                // You can add your logic here

//                System.out.println("Store Confirmed: " + editStoreName);
                cardPanel.add(createViewSpecificStoreStatisticsSaleTypePanel(), "Specific Store Statistics Sort");
                cardLayout.show(cardPanel, "Specific Store Statistics Sort");
                frame.setSize(600, 300);

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
        JPanel createViewSpecificStoreStatisticsSaleType = new JPanel();
        createViewSpecificStoreStatisticsSaleType.setBackground(goldColor);
        createViewSpecificStoreStatisticsSaleType.setLayout(new BoxLayout(createViewSpecificStoreStatisticsSaleType, BoxLayout.Y_AXIS));

        JLabel titleLabel = new JLabel("Which Statistic would you Like To View?");
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));

        JPanel topPanel = new JPanel();
        topPanel.setOpaque(false);
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.X_AXIS));
        Dimension topPanelDimension = new Dimension(500, 50);
        topPanel.setMaximumSize(topPanelDimension);
        topPanel.setMinimumSize(topPanelDimension);
        topPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        topPanel.add(Box.createHorizontalStrut(20));
        topPanel.add(createBackToSpecificStoreStatisticsButton());
        topPanel.add(Box.createHorizontalStrut(50));
        topPanel.add(titleLabel);
        topPanel.add(Box.createHorizontalStrut(20));

        Dimension buttonDimension = new Dimension(300, 50);

        // change to view sort
        JButton specificStoreSortByCustomer = new JButton(("Customer Sales"));
        specificStoreSortByCustomer.setMaximumSize(buttonDimension);
        specificStoreSortByCustomer.setMinimumSize(buttonDimension);
        specificStoreSortByCustomer.setAlignmentX(Component.CENTER_ALIGNMENT);
        specificStoreSortByCustomer.setBackground(greyButtonColor);
        specificStoreSortByCustomer.setBorder(customBorder);
        specificStoreSortByCustomer.setFont(new Font("Arial", Font.PLAIN, 18));
        specificStoreSortByCustomer.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardPanel.add(createViewSpecificStoreCustomerSalesPanel(), "View Specific Store Customer Sales");
                cardLayout.show(cardPanel, "View Specific Store Customer Sales");
                frame.setSize(600, 500);
                frame.setLocationRelativeTo(null);
            }
        });

        JButton specificStoreSortByProduct = new JButton(("Product Sales"));
        specificStoreSortByProduct.setMaximumSize(buttonDimension);
        specificStoreSortByProduct.setMinimumSize(buttonDimension);
        specificStoreSortByProduct.setAlignmentX(Component.CENTER_ALIGNMENT);
        specificStoreSortByProduct.setBackground(greyButtonColor);
        specificStoreSortByProduct.setBorder(customBorder);
        specificStoreSortByProduct.setFont(new Font("Arial", Font.PLAIN, 18));
        specificStoreSortByProduct.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardPanel.add(createViewSpecificStoreProductSalesPanel(), "View Specific Store Product Sales");
                cardLayout.show(cardPanel, "View Specific Store Product Sales");
                frame.setSize(600, 500);
                frame.setLocationRelativeTo(null);
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

    // TODO: implement sort by quantity sold and revenue generated (low to high, high to low);
    private JPanel createViewSpecificStoreCustomerSalesPanel() {
        JPanel createViewSpecificStoreCustomerSales = new JPanel();
        createViewSpecificStoreCustomerSales.setBackground(goldColor);
        createViewSpecificStoreCustomerSales.setLayout(new BoxLayout(createViewSpecificStoreCustomerSales, BoxLayout.Y_AXIS));

        JLabel titleLabel = new JLabel(editStoreName + " Customer Sales");
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));

        JPanel topPanel = new JPanel();
        topPanel.setOpaque(false);
        Dimension topPanelDimension = new Dimension(600, 50);
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.X_AXIS));
        topPanel.setMaximumSize(topPanelDimension);
        topPanel.setMinimumSize(topPanelDimension);
        topPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        topPanel.add(Box.createHorizontalStrut(20));
        topPanel.add(createBackToSpecificStoreStatisticsSaleTypeButton());
        topPanel.add(Box.createHorizontalStrut(80));
        topPanel.add(titleLabel);
        topPanel.add(Box.createHorizontalStrut(20));

        Dimension buttonDimension = new Dimension(300, 70);

        JPanel storeNamePanel = new JPanel();
        storeNamePanel.setBackground(greyButtonColor);
        String[] dummyCustomers= new String[]{"Customer One", "Customer Two", "Customer Three", "Customer Four", "Customer Five",
                "Customer Six", "Customer Seven", "Customer Eight", "Customer Nine", "Customer Ten", "Customer Eleven", "Customer Twelve"};
        storeNamePanel.setLayout(new BoxLayout(storeNamePanel, BoxLayout.Y_AXIS));

        for (String name : dummyCustomers) {
            JLabel label = new JLabel(name);
            label.setAlignmentX(Component.CENTER_ALIGNMENT);
            label.setFont(new Font("Arial", Font.PLAIN, 18));

            label.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    // Handle click event
                    customerName = label.getText();
                    // Update the display with the selected store's name
                    selectedCustomerLabel.setText(customerName + " Sales: " + "$0");
                }
            });

            storeNamePanel.add(label);
            storeNamePanel.add(new JSeparator(JSeparator.HORIZONTAL));
        }

        // Remove the last separator to avoid an extra line at the end
        storeNamePanel.remove(storeNamePanel.getComponentCount() - 1);

        JScrollPane psp = new JScrollPane(storeNamePanel, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        // Display selected store's name
        psp.setMaximumSize(new Dimension(500, 275));
        psp.setMinimumSize(new Dimension(500, 275));
        psp.setBorder(customBorder);
        psp.getViewport().setBackground(greyButtonColor);

        selectedCustomerLabel = new JLabel("Selected Customer: ");
        selectedCustomerLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        selectedCustomerLabel.setFont(new Font("Arial", Font.PLAIN, 18));

        // TODO: sorting needs to be worked on
        // sorts the customer sales by quanitity sold or revenue generated
        String[] sortingOptions = {"Quantity Sold (Low to High)", "Quantity Sold (High to Low)",
                "Revenue Generated (Low to High)", "Revenue Generated (High to Low)"};
        JComboBox<String> sortingComboBox = new JComboBox<>(sortingOptions);
        sortingComboBox.setAlignmentX(Component.CENTER_ALIGNMENT);
        sortingComboBox.setBackground(greyButtonColor);
        sortingComboBox.setBorder(customBorder);
        sortingComboBox.setFont(new Font("Arial",Font.PLAIN,16));
        sortingComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle sorting here based on the selected option
                String selectedOption = (String) sortingComboBox.getSelectedItem();
            }
        });

        // displays the different ways to sort the customer sales by
        JPanel sortingPanel = new JPanel();
        sortingPanel.setOpaque(false);
        Dimension sortingPanelDimension = new Dimension(350, 30);
        sortingPanel.setMinimumSize(sortingPanelDimension);
        sortingPanel.setMaximumSize(sortingPanelDimension);
        sortingPanel.setLayout(new BoxLayout(sortingPanel, BoxLayout.X_AXIS));

        sortingPanel.add(new JLabel("Sort by: "));
        sortingPanel.add(sortingComboBox);

        createViewSpecificStoreCustomerSales.add(Box.createVerticalStrut(20));
        createViewSpecificStoreCustomerSales.add(topPanel);
        createViewSpecificStoreCustomerSales.add(Box.createVerticalStrut(10));
        createViewSpecificStoreCustomerSales.add(sortingPanel);
        createViewSpecificStoreCustomerSales.add(Box.createVerticalStrut(15));
        createViewSpecificStoreCustomerSales.add(psp);
        createViewSpecificStoreCustomerSales.add(Box.createVerticalStrut(20));
        createViewSpecificStoreCustomerSales.add(selectedCustomerLabel);
        createViewSpecificStoreCustomerSales.add(Box.createVerticalStrut(20));

        return createViewSpecificStoreCustomerSales;
    }


    private JPanel createViewSpecificStoreProductSalesPanel() {
        JPanel createViewSpecificStoreProductSales = new JPanel();
        createViewSpecificStoreProductSales.setBackground(goldColor);
        createViewSpecificStoreProductSales.setLayout(new BoxLayout(createViewSpecificStoreProductSales, BoxLayout.Y_AXIS));

        JLabel titleLabel = new JLabel(editStoreName + " Product Sales");
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));

        JPanel topPanel = new JPanel();
        topPanel.setOpaque(false);
        Dimension topPanelDimension = new Dimension(600, 50);
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.X_AXIS));
        topPanel.setMaximumSize(topPanelDimension);
        topPanel.setMinimumSize(topPanelDimension);
        topPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        topPanel.add(Box.createHorizontalStrut(20));
        topPanel.add(createBackToSpecificStoreStatisticsSaleTypeButton());
        topPanel.add(Box.createHorizontalStrut(80));
        topPanel.add(titleLabel);
        topPanel.add(Box.createHorizontalStrut(20));

        Dimension buttonDimension = new Dimension(300, 70);

        JPanel storeNamePanel = new JPanel();
        storeNamePanel.setBackground(greyButtonColor);
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
                    productName = label.getText();
                    // Update the display with the selected store's name
                    selectedProductLabel.setText(productName + " Sales: " + "$0");
                }
            });

            storeNamePanel.add(label);
            storeNamePanel.add(new JSeparator(JSeparator.HORIZONTAL));
        }

        // Remove the last separator to avoid an extra line at the end
        storeNamePanel.remove(storeNamePanel.getComponentCount() - 1);

        JScrollPane psp = new JScrollPane(storeNamePanel, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        // Display selected store's name
        psp.setMaximumSize(new Dimension(500, 275));
        psp.setMinimumSize(new Dimension(500, 275));
        psp.setBorder(customBorder);
        psp.getViewport().setBackground(greyButtonColor);

        selectedProductLabel = new JLabel("Selected Product: ");
        selectedProductLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        selectedProductLabel.setFont(new Font("Arial", Font.PLAIN, 18));

        // sorts the customer sales by quanitity sold or revenue generated
        String[] sortingOptions = {"Quantity Sold (Low to High)", "Quantity Sold (High to Low)",
                "Revenue Generated (Low to High)", "Revenue Generated (High to Low)"};
        JComboBox<String> sortingComboBox = new JComboBox<>(sortingOptions);
        sortingComboBox.setAlignmentX(Component.CENTER_ALIGNMENT);
        sortingComboBox.setBorder(customBorder);
        sortingComboBox.setBackground(greyButtonColor);
        sortingComboBox.setFont(new Font("Arial",Font.PLAIN,16));
        sortingComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle sorting here based on the selected option
                String selectedOption = (String) sortingComboBox.getSelectedItem();
            }
        });

        // displays the different ways to sort the customer sales by
        JPanel sortingPanel = new JPanel();
        sortingPanel.setOpaque(false);
        Dimension sortingPanelDimension = new Dimension(350, 50);
        sortingPanel.setMinimumSize(sortingPanelDimension);
        sortingPanel.setMaximumSize(sortingPanelDimension);
        sortingPanel.setLayout(new BoxLayout(sortingPanel, BoxLayout.X_AXIS));

        sortingPanel.add(new JLabel("Sort by: "));
        sortingPanel.add(sortingComboBox);

        createViewSpecificStoreProductSales.add(Box.createVerticalStrut(20));
        createViewSpecificStoreProductSales.add(topPanel);
        createViewSpecificStoreProductSales.add(Box.createVerticalStrut(10));
        createViewSpecificStoreProductSales.add(sortingPanel);
        createViewSpecificStoreProductSales.add(Box.createVerticalStrut(15));
        createViewSpecificStoreProductSales.add(psp);
        createViewSpecificStoreProductSales.add(Box.createVerticalStrut(20));
        createViewSpecificStoreProductSales.add(selectedProductLabel);
        createViewSpecificStoreProductSales.add(Box.createVerticalStrut(20));

        return createViewSpecificStoreProductSales;
    }

    private JPanel createViewCustomerShoppingCarts() {
        JPanel createViewCustomerShoppingCarts = new JPanel();
        createViewCustomerShoppingCarts.setBackground(goldColor);
        createViewCustomerShoppingCarts.setLayout(new BoxLayout(createViewCustomerShoppingCarts, BoxLayout.Y_AXIS));

        JLabel titleLabel = new JLabel("Select a Product to View in Customer's Shopping Carts");
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));

        JPanel topPanel = new JPanel();
        topPanel.setOpaque(false);
        Dimension topPanelDimension = new Dimension(600, 50);
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.X_AXIS));
        topPanel.setMaximumSize(topPanelDimension);
        topPanel.setMinimumSize(topPanelDimension);
        topPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        topPanel.add(Box.createHorizontalStrut(20));
        topPanel.add(createBackToStoreStatisticsButton());
        topPanel.add(Box.createHorizontalStrut(40));
        topPanel.add(titleLabel);
        topPanel.add(Box.createHorizontalStrut(20));

        Dimension buttonDimension = new Dimension(300, 70);

        // dummy products list in a store
        JPanel storeNamePanel = new JPanel();
        storeNamePanel.setBackground(greyButtonColor);
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
        psp.setMaximumSize(new Dimension(500, 300));
        psp.setMinimumSize(new Dimension(500, 300));
        psp.setBorder(customBorder);
        psp.getViewport().setBackground(greyButtonColor);

        selectedProductInShoppingCartLabel = new JLabel("Selected Product in Shopping Cart: ");
        selectedProductInShoppingCartLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        selectedProductInShoppingCartLabel.setFont(new Font("Arial", Font.PLAIN, 18));


        createViewCustomerShoppingCarts.add(Box.createVerticalStrut(20));
        createViewCustomerShoppingCarts.add(topPanel);
        createViewCustomerShoppingCarts.add(Box.createVerticalStrut(20));
        createViewCustomerShoppingCarts.add(psp);
        createViewCustomerShoppingCarts.add(Box.createVerticalStrut(20));
        createViewCustomerShoppingCarts.add(selectedProductInShoppingCartLabel);
        createViewCustomerShoppingCarts.add(Box.createVerticalStrut(10));

        return createViewCustomerShoppingCarts;
    }



    private static JPanel createFieldWithLabel(String labelText, JComponent field) {
        JPanel panel = new JPanel();
        panel.setOpaque(false);
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
        panel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel label = new JLabel(labelText);
        label.setFont(new Font("Arial", Font.ITALIC, 18));
        label.setPreferredSize(new Dimension(100, 20));

        panel.add(Box.createHorizontalStrut(10));
        panel.add(label);
        panel.add(Box.createHorizontalStrut(10));
        panel.add(field);
        panel.add(Box.createHorizontalStrut(10));


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
        backButton.setBackground(greyButtonColor);
        backButton.setForeground(Color.BLACK);
        backButton.setBorder(customBorder);
        Dimension buttonSize = new Dimension(60, 30);
        backButton.setMaximumSize(buttonSize);
        backButton.setMinimumSize(buttonSize);
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
        backButton.setBackground(greyButtonColor);
        backButton.setForeground(Color.BLACK);
        backButton.setBorder(customBorder);
        Dimension buttonSize = new Dimension(60, 30);
        backButton.setMaximumSize(buttonSize);
        backButton.setMinimumSize(buttonSize);
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
        backButton.setBackground(greyButtonColor);
        backButton.setForeground(Color.BLACK);
        backButton.setBorder(customBorder);
        Dimension buttonSize = new Dimension(60, 30);
        backButton.setMaximumSize(buttonSize);
        backButton.setMinimumSize(buttonSize);

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "Store Statistics");
                frame.setSize(400, 350);
                frame.setLocationRelativeTo(null);
            }
        });
        return backButton;
    }

    private JButton createBackToSpecificStoreStatisticsButton() {
        JButton backButton = new JButton("<");
        Font largeFont = new Font("Arial", Font.PLAIN, 18);
        backButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        backButton.setFont(largeFont);
        backButton.setBackground(greyButtonColor);
        backButton.setForeground(Color.BLACK);
        backButton.setBorder(customBorder);

        Dimension buttonSize = new Dimension(60, 30);
        backButton.setMaximumSize(buttonSize);
        backButton.setMinimumSize(buttonSize);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "View Specific Store Sales");
                frame.setSize(600, 500);
                frame.setLocationRelativeTo(null);
            }
        });
        return backButton;
    }

    private JButton createBackToSpecificStoreStatisticsSaleTypeButton() {
        JButton backButton = new JButton("<");
        Font largeFont = new Font("Arial", Font.PLAIN, 18);
        backButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        backButton.setFont(largeFont);
        backButton.setBackground(greyButtonColor);
        backButton.setForeground(Color.BLACK);
        backButton.setBorder(customBorder);
        Dimension buttonSize = new Dimension(60, 30);
        backButton.setMaximumSize(buttonSize);
        backButton.setMinimumSize(buttonSize);

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "Specific Store Statistics Sort");
                frame.setSize(600, 300);
                frame.setLocationRelativeTo(null);
            }
        });
        return backButton;
    }

    private JButton createBackToContactCustomersButton() {
        JButton backButton = new JButton("<");
        Font largeFont = new Font("Arial", Font.PLAIN, 18);
        backButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        backButton.setFont(largeFont);
        backButton.setBackground(greyButtonColor);
        backButton.setForeground(Color.BLACK);
        backButton.setBorder(customBorder);
        Dimension buttonSize = new Dimension(60, 30);
        backButton.setMaximumSize(buttonSize);
        backButton.setMinimumSize(buttonSize);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "Contact Customers");
                frame.setSize(400, 500);
                frame.setLocationRelativeTo(null);
            }
        });
        return backButton;
    }
}
