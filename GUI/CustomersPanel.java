import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class CustomersPanel {
    private JFrame frame;
    private JPanel cardPanel;

    private CardLayout cardLayout;

    private String sellerName;

    private JLabel sellerSelectedLabel;

    private JLabel selectedProductLabel;
    private String selectedProduct;

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
        SwingUtilities.invokeLater(() -> new CustomersPanel());

    }

    // public void run() {

    //   loginFrame = new JFrame("Welcome!");
    //   Container loginFrameContent = loginFrame.getContentPane();
    //   BoxLayout boxLayout = new BoxLayout(loginFrameContent, BoxLayout.Y_AXIS);
    //   loginFrameContent.setLayout(boxLayout);
    //   loginFrame.setDefaultCloseOperation((JFrame.DISPOSE_ON_CLOSE));

    //   JPanel formatPanel = new JPanel(new BorderLayout());
    //   nextButton = new JButton("Next");
    //   nextButton.addActionListener(actionListener);
    //   formatPanel.add(nextButton, BorderLayout.SOUTH);

    //   customerNameLabel = new JLabel("Customer"); // need implementation to return the name of the customer
    //   // customerNameLabel = new JLabel("Customer " + account.getName());
    //   customerNameLabel.setFont(new Font("Arial", Font.PLAIN, 20));
    //   customerNameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
    //   successfulLoginMessage = new JLabel("Successful Login!");
    //   successfulLoginMessage.setFont(new Font("Arial", Font.PLAIN, 35));
    //   successfulLoginMessage.setAlignmentX(Component.CENTER_ALIGNMENT);

    //   loginFrame.add(customerNameLabel);
    //   loginFrame.add(Box.createVerticalStrut(20));
    //   loginFrame.add(successfulLoginMessage);
    //   loginFrame.add(Box.createVerticalStrut(30));
    //   loginFrame.add(formatPanel);

    //   loginFrame.setSize(400, 200);
    //   loginFrame.setLocationRelativeTo(null);
    //   loginFrame.setVisible(true);

    //   customerMainPageFrame = new JFrame("Customer Main Page");
    //   Container customerMainPageContent = customerMainPageFrame.getContentPane();
    //   customerMainPageFrame.setLayout(new CardLayout());
    //   JPanel mainPagePanel = new JPanel(new GridLayout(4, 0, 50, 50));
    //   mainPagePanel.setAlignmentX(Component.CENTER_ALIGNMENT);
    //   mainPagePanel.setSize(500, 500);

    //   JPanel panelOne = new JPanel(new BorderLayout());
    //   JLabel programTitle = new JLabel("Boiler Bay");
    //   programTitle.setFont(new Font("Arial", Font.PLAIN, 20));
    //   panelOne.add(programTitle, BorderLayout.CENTER);
    //   mainPagePanel.add(panelOne);

    //   customerMainPageFrame.add(mainPagePanel);
    //   customerMainPageFrame.setSize(500, 500);
    //   customerMainPageFrame.setLocationRelativeTo(null);
    //   customerMainPageFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

    // }

    public CustomersPanel(){
        frame = new JFrame("Customers");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);
        cardPanel.add(createMainPanel(), "Main Page");
        cardPanel.add(createAccountPanel(), "Account Options");
        cardPanel.add(createSellerListingsPanel(), "Seller Listings");
        cardPanel.add(createProductListingsPanel(), "Product Listings");
        cardPanel.add(createCustomerPurchaseHistoryPanel(), "Customer Purchase History");
        cardPanel.add(createSellerListingSortPanel(), "Seller Listing Sort");
        cardPanel.add(createLogOutPanel(), "Log Out");
        cardPanel.add(createContactSellerPanel(), "Contact Sellers");
        cardPanel.add(createSellerListingsPanel(), "Seller Listings");

        frame.add(cardPanel);

        cardLayout.show(cardPanel, "Customer");
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }

    //method that creates the panel for the main page
    private JPanel createMainPanel() {
        frame.setSize(400,500);
        frame.setResizable(false);
        JPanel mainPagePanel = new JPanel();
        mainPagePanel.setLayout(new BoxLayout(mainPagePanel, BoxLayout.Y_AXIS));
        //mainPagePanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel applicationLabel = new JLabel("Boiler Bay");
        applicationLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        applicationLabel.setFont(new Font("Arial", Font.BOLD, 24));

        JButton switchToAccountPanelButton = new JButton("Account Page");
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
        searchButton.setPreferredSize(new Dimension(300, 50));
        searchButton.setFont(new Font("Arial", Font.PLAIN, 30));
        searchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "No products were found.",
                        "Customers", JOptionPane.ERROR_MESSAGE);
            }
        });


        //JTextField searchField = new JTextField();
        //searchField.setPreferredSize(new Dimension(300, 30));

        JPanel searchPanel = new JPanel(new FlowLayout());
        Dimension searchPanelDimension = new Dimension(400, 40);
        searchPanel.setPreferredSize(searchPanelDimension);
        //searchPanel.add(searchField);
        searchPanel.add(searchButton);

        Dimension buttonDimension = new Dimension(100,100);
        JButton seeAllProductsButton = new JButton("Products");
        seeAllProductsButton.setPreferredSize(buttonDimension);
        //seeAllProductsButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        seeAllProductsButton.setFont(new Font("Arial", Font.PLAIN, 16));
        seeAllProductsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                cardLayout.show(cardPanel, "Product Listings");

            }
        });

        JButton seeAllStoresButton = new JButton("Stores");
        seeAllStoresButton.setPreferredSize(buttonDimension);
        //seeAllStoresButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        seeAllStoresButton.setFont(new Font("Arial", Font.PLAIN, 16));

        JButton seeAllSellersButton = new JButton("Sellers");
        seeAllSellersButton.setPreferredSize(buttonDimension);
        //seeAllSellersButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        seeAllSellersButton.setFont(new Font("Arial", Font.PLAIN, 16));
        seeAllSellersButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "Seller Listing Sort");
                frame.setSize(400,200);
                frame.setLocationRelativeTo(null);
            }
        });

        JPanel buttonPanel = new JPanel(new FlowLayout());
        Dimension buttonPanelDimension = new Dimension(400, 80);
        buttonPanel.setPreferredSize(buttonPanelDimension);
        buttonPanel.add(seeAllProductsButton);
        buttonPanel.add(seeAllStoresButton);
        buttonPanel.add(seeAllSellersButton);

        JLabel bestSellersLabel  = new JLabel("Best Sellers");
        bestSellersLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        bestSellersLabel.setFont(new Font("Arial", Font.BOLD, 18));

        JLabel bestSellerOneLabel = new JLabel("Product One Seller One Store One");
        bestSellerOneLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        bestSellerOneLabel.setFont(new Font("Arial", Font.PLAIN, 18));

        JLabel bestSellerTwoLabel = new JLabel("Product Two Seller Two Store Two");
        bestSellerTwoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        bestSellerTwoLabel.setFont(new Font("Arial", Font.PLAIN, 18));

        JLabel bestSellerThreeLabel = new JLabel("Product Three Seller Three Store Three");
        bestSellerThreeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        bestSellerThreeLabel.setFont(new Font("Arial", Font.PLAIN, 18));

        JPanel bestSellersPanel = new JPanel();
        bestSellersPanel.setLayout(new BoxLayout(bestSellersPanel, BoxLayout.Y_AXIS));
        bestSellersPanel.add(bestSellerOneLabel);
        bestSellersPanel.add(bestSellerTwoLabel);
        bestSellersPanel.add(bestSellerThreeLabel);

        mainPagePanel.add(Box.createVerticalStrut(20));
        mainPagePanel.add(topPanel);

        mainPagePanel.add(searchPanel);
        mainPagePanel.add(Box.createVerticalStrut(10));

        mainPagePanel.add(buttonPanel);

        mainPagePanel.add(bestSellersLabel);
        mainPagePanel.add(Box.createVerticalStrut(10));
        mainPagePanel.add(bestSellersPanel);
        mainPagePanel.add(Box.createVerticalStrut(20));
        mainPagePanel.add(Box.createVerticalStrut(20));

        return mainPagePanel;
    }

    //method that creates the panel for the account page
    private JPanel createAccountPanel() {
        frame.setSize(400, 500);
        JPanel createAccountPanel = new JPanel();
        createAccountPanel.setLayout(new BoxLayout(createAccountPanel, BoxLayout.Y_AXIS));

        JLabel titleLabel = new JLabel("Customer Name's Account");
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));

				/*
				JButton switchToMainPageButton = new JButton("<");
				switchToMainPageButton.setFont(new Font("Arial", Font.PLAIN, 18));
				switchToMainPageButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
								cardLayout.first(cardPanel);
						}
				});
				 */

        //JButton logOutButton = new JButton(("X"));
        //logOutButton.setFont(new Font("Arial", Font.PLAIN, 18));
        //Dimension logOutButtonDimension = new Dimension(10, 10);
        //logOutButton.setPreferredSize(logOutButtonDimension);

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
                frame.setSize(400,200);
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
    private JPanel createSellerListingsPanel() {
        JPanel sellerListingsPanel = new JPanel();
        sellerListingsPanel.setLayout(new BoxLayout(sellerListingsPanel, BoxLayout.Y_AXIS));

        JLabel titleLabel = new JLabel("           All Sellers          ");
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.X_AXIS));
        titlePanel.add(createBackToMenuButton());
        //titlePanel.add(Box.createHorizontalStrut(20));
        titlePanel.add(titleLabel);

        JPanel allSellersPanel = new JPanel();
        allSellersPanel.setLayout(new BoxLayout(allSellersPanel, BoxLayout.Y_AXIS));
        //method that returns all sellers in an arraylist
        String[] dummySellers = new String[]{"Seller One", "Seller Two", "Seller Three", "Seller Four", "Seller Five",
                "Seller Six", "Seller Seven", "Seller Eight", "Seller Nine", "Seller Ten", "Seller Eleven", "Seller Twelve", "Seller Thirteen", "Seller Fourteen", "Seller Fifteen", "Seller Sixteen"};
        for (String name: dummySellers) {
            JLabel label = new JLabel(name);
            label.setAlignmentX(Component.CENTER_ALIGNMENT);
            label.setFont(new Font("Arial", Font.PLAIN, 18));

            label.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e) {
                    sellerName = label.getText();
                    sellerSelectedLabel.setText("Selected Seller: " + sellerName);
                    for (int i = 0; i < dummySellers.length; i++) {
                        if (sellerName.equals(dummySellers[i])) {
                            //selectedSeller = allSellers[i];
                            //selectedSeller should be declared outside this method
                        }
                    }
                    //match the name in the array list and gets it index
                    //use a method that creates a panel with the index of the object that should match with the name
                    // Update the display with the selected seller's name
                }
            });
            allSellersPanel.add(label);
            allSellersPanel.add(new JSeparator(JSeparator.HORIZONTAL));
        }
        JScrollPane jsp = new JScrollPane(allSellersPanel,ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        allSellersPanel.remove(allSellersPanel.getComponentCount()-1);

        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new BoxLayout(bottomPanel,BoxLayout.Y_AXIS));

        sellerSelectedLabel = new JLabel("Selected Seller: ");
        sellerSelectedLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        sellerSelectedLabel.setFont(new Font("Arial", Font.PLAIN, 18));

        JButton goToSellerPageButton = new JButton("Go to Seller Page");
        goToSellerPageButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        goToSellerPageButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JPanel panel = createSellerPanel();
                //Jpanel panel = createSellerPanel(selectedSeller);
                cardPanel.add(panel, "Seller Page");
                cardLayout.show(cardPanel, "Seller Page");
            }
        });

        bottomPanel.add(sellerSelectedLabel);






        sellerListingsPanel.add(Box.createVerticalStrut(20));
        sellerListingsPanel.add(titlePanel);
        sellerListingsPanel.add(Box.createVerticalStrut(20));
        sellerListingsPanel.add(jsp);
        sellerListingsPanel.add(bottomPanel);
        sellerListingsPanel.add(goToSellerPageButton);
        return sellerListingsPanel;

    }

    private JPanel createProductPanel(Product product) {
        JPanel productPanel = new JPanel();
        productPanel.setLayout(new BoxLayout(productPanel, BoxLayout.Y_AXIS));


        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.X_AXIS));
        JLabel productTitle = new JLabel(product.getName());
        titlePanel.add(productTitle);
        productPanel.add(titlePanel);


        return productPanel;
    }



    // method that creates the panel for the product listings page
    private JPanel createProductListingsPanel() { //not entirely sure how to implement the refresh feature based on what is selected in the dropdown
        JPanel productsListingPanel = new JPanel();
        productsListingPanel.setLayout(new BoxLayout(productsListingPanel, BoxLayout.Y_AXIS));
        JLabel titleLabel = new JLabel("            All Products               "); //not completely centered
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        JButton accountPageButton = new JButton("Account");
        accountPageButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.X_AXIS));
        titlePanel.add(createBackToMenuButton());
        titlePanel.add(titleLabel);
        titlePanel.add(accountPageButton);

        String [] dropdownOptions = new String[]{"Price High To Low", "Price Low to High", "Quantity Least to Greatest", "Quantity Greatest to Least"};
        JComboBox sortByDropdown = new JComboBox<>(dropdownOptions);
        JPanel dropDownPanel = new JPanel();
        dropDownPanel.setPreferredSize(new Dimension(400, 40));
        dropDownPanel.add(sortByDropdown);

        JPanel productPanel = new JPanel();
        productPanel.setLayout((new BoxLayout(productPanel, BoxLayout.Y_AXIS)));

        JScrollPane jsp = new JScrollPane(productPanel, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        //dummy data
        String[] products = new String[]{"Product One", "Product Two", "Product Three", "Product Four", "Product Five", "Product Six", "Product Seven", "Product Eight", "Product Nine", "Product Ten", "Product Eleven", "Product Twelve", "Product Thirteen", "Product Fourteen", "Product Fifteen", "Product Sixteen",};
        double[] prices = new double[]{3.99, 14.99, 5.99, 78.99, 100.99, 1450.99, 45.99, 2.99, 3.99, 14.99, 51.99, 46.99, 32.99, 40.99, 15.99, 14.99};
        for (int i = 0; i < products.length; i++) {
            JLabel label = new JLabel(products[i] + " $" + prices[i]);
            label.setAlignmentX(Component.CENTER_ALIGNMENT);
            label.setFont(new Font("Arial", Font.PLAIN, 18));
            label.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e) {
                    //JPanel panel = createProductPanel();
                    //cardPanel.add(panel, "Product Page");
                    cardLayout.show(cardPanel, "Product Page");
                }
            });
            productPanel.add(label);
            productPanel.add(new JSeparator(JSeparator.HORIZONTAL));

        }
        productPanel.remove(productPanel.getComponentCount()-1);

        selectedProductLabel = new JLabel("Selected Product: ");
        selectedProductLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        selectedProductLabel.setFont(new Font("Arial", Font.PLAIN, 18));

        JLabel instructionLabel = new JLabel("Click a product to view its page.");
        instructionLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        instructionLabel.setFont(new Font("Arial", Font.PLAIN, 18));


        JPanel bottomPanel = new JPanel();
        bottomPanel.add(selectedProductLabel);


        productsListingPanel.add(Box.createVerticalStrut(20));
        productsListingPanel.add(titlePanel);
        productsListingPanel.add(dropDownPanel);
        productsListingPanel.add(Box.createVerticalStrut(20));
        productsListingPanel.add(jsp);
        productsListingPanel.add(Box.createVerticalStrut(20));
        productsListingPanel.add(instructionLabel);
        productsListingPanel.add(Box.createVerticalStrut(20));
        return productsListingPanel;
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
                frame.setSize(400,500);
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
                frame.setSize(400,500);
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

    private String messageSellerName;  // Variable to store the selected seller's name

    private JPanel createContactSellerPanel() {
        JPanel contactSellerPanel = new JPanel();
        contactSellerPanel.setLayout(new BoxLayout(contactSellerPanel, BoxLayout.Y_AXIS));

        // Back button
        JButton backButton = createBackToMenuButton();

        JLabel titleLabel = new JLabel("Message a Seller");
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));

        // Panel for back button and title
        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.X_AXIS));
        titlePanel.add(backButton);
        titlePanel.add(Box.createHorizontalStrut(20)); // Add some spacing
        titlePanel.add(titleLabel);

        JPanel sellerNamePanel = new JPanel();
        //use a method that returns an arraylist of all sellers
        //make a mirror array of seller names that lines up with the arraylist of sellers
        String[] dummySellerNames = new String[]{"Seller 1", "Seller 2", "Seller 3", "Seller 4", "Seller 5", "Seller 6", "Seller 7"
                ,"Seller 8", "Seller 9", "Seller 10", "Seller 11", "Seller 12", "Seller 13", "Seller 14", "Seller 15", "Seller 16"};
        sellerNamePanel.setLayout(new BoxLayout(sellerNamePanel, BoxLayout.Y_AXIS));

        for (String name : dummySellerNames) {
            JLabel label = new JLabel(name);
            label.setAlignmentX(Component.CENTER_ALIGNMENT);
            label.setFont(new Font("Arial", Font.PLAIN, 18));
            label.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    // Handle click event
                    messageSellerName = label.getText();
                    selectedSellerLabel.setText("Selected Seller: " + messageSellerName);
                }
            });

            sellerNamePanel.add(label);
            sellerNamePanel.add(new JSeparator(JSeparator.HORIZONTAL));
        }

        // Remove the last separator to avoid an extra line at the end
        sellerNamePanel.remove(sellerNamePanel.getComponentCount() - 1);

        JScrollPane jsp = new JScrollPane(sellerNamePanel, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        // Display selected seller's name
        selectedSellerLabel = new JLabel("Selected Seller: ");
        selectedSellerLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        selectedSellerLabel.setFont(new Font("Arial", Font.PLAIN, 18));

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
                System.out.println("Sending message to " + messageSellerName + ": " + message);
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
        contactSellerPanel.add(selectedSellerLabel);
        contactSellerPanel.add(Box.createVerticalStrut(10));
        contactSellerPanel.add(messagePanel);

        return contactSellerPanel;
    }

    private JPanel createSellerPanel() { //should have an object parameter that takes a Seller object
        JPanel sellerPanel = new JPanel();
        sellerPanel.setLayout(new BoxLayout(sellerPanel, BoxLayout.Y_AXIS));
        return sellerPanel;
    }

    private JPanel createShopPanel() {
        JPanel shopPanel = new JPanel();
        shopPanel.setLayout(new BoxLayout(shopPanel, BoxLayout.Y_AXIS));
        return shopPanel;
    }




    private JLabel selectedSellerLabel;

    private JButton createBackToMenuButton() {
        JButton backButton = new JButton("<");
        Font largeFont = new Font("Arial", Font.PLAIN, 18);
        backButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        backButton.setFont(largeFont);
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
}