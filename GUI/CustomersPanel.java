import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class CustomersPanel {
    private JFrame frame;
    private JPanel cardPanel;

    private CardLayout cardLayout;

    private String sellerName;

    private JLabel sellerSelectedLabel;

    private JLabel selectedProductLabel;
    private String selectedProduct;

    private String custShoppingCartName;

    private String searchInputProductName;

    private String searchInputProductDescription;

    private int customerShoppingCartQuantity = 0;

    private JPanel shoppingCartPanel;

    private Product bestSellerOne;
    private Product bestSellerTwo;
    private Product bestSellerThree;
    private String storeName;

    private Store selectedStore;

    private int cStore = 0;

    private JLabel selectedStoreLabel = new JLabel("");

    private String productName;

    private Seller selectedSeller;


    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> new CustomersPanel());

    }


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
        cardPanel.add(createStoreListingsPagePanel(), "Store Listings");
        cardPanel.add(createSearchOptionsPanel(), "Search Options");
        cardPanel.add(searchProductNamePanel(), "Search Product Input");
        cardPanel.add(createShoppingCartPanel(), "Shopping Cart");

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

                cardLayout.show(cardPanel, "Search Options");
                frame.setSize(400,300);
                frame.setLocationRelativeTo(null);
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
        seeAllStoresButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "Store Listings");
                frame.setSize(400,500);
                frame.setLocationRelativeTo(null);
            }
        });

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
        shoppingCartButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                cardLayout.show(cardPanel, "Shopping Cart");
                frame.setSize(400,300);
                frame.setLocationRelativeTo(null);
            }
        });

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
        titlePanel.add(titleLabel);

        JPanel allSellersPanel = new JPanel();
        allSellersPanel.setLayout(new BoxLayout(allSellersPanel, BoxLayout.Y_AXIS));

        ArrayList<Seller> allSellers = new Request().getAllSellers();

        for (Seller seller: allSellers) {
            JLabel label = new JLabel(seller.getUsername());
            label.setAlignmentX(Component.CENTER_ALIGNMENT);
            label.setFont(new Font("Arial", Font.PLAIN, 18));

            label.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e) {
                    selectedSeller = seller;
                    JPanel sellerPanel = createSellerPanel(seller);
                    cardPanel.add(sellerPanel, "Seller Page");
                    cardLayout.show(cardPanel, "Seller Page");
                }
            });
            allSellersPanel.add(label);
            allSellersPanel.add(new JSeparator(JSeparator.HORIZONTAL));
        }
        JScrollPane jsp = new JScrollPane(allSellersPanel,ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        allSellersPanel.remove(allSellersPanel.getComponentCount()-1);

        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new BoxLayout(bottomPanel,BoxLayout.Y_AXIS));

        JLabel instructions = new JLabel("Click on a seller to view its page.");
        instructions.setAlignmentX(Component.CENTER_ALIGNMENT);
        instructions.setFont(new Font("Arial", Font.BOLD, 18));
        bottomPanel.add(instructions);

        sellerListingsPanel.add(Box.createVerticalStrut(20));
        sellerListingsPanel.add(titlePanel);
        sellerListingsPanel.add(Box.createVerticalStrut(20));
        sellerListingsPanel.add(jsp);
        sellerListingsPanel.add(bottomPanel);
        return sellerListingsPanel;

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


        ArrayList<Product> allProducts = getAllProducts();

        for (Product product: allProducts) {
            if (allProducts.isEmpty()) {
                break;
            }
            JLabel label = new JLabel( product.getName()+ " $" + product.getPrice());
            label.setAlignmentX(Component.CENTER_ALIGNMENT);
            label.setFont(new Font("Arial", Font.PLAIN, 18));
            label.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e) {
                    JPanel panel = createProductPanel(product);
                    cardPanel.add(panel, "Product Page");
                    cardLayout.show(cardPanel, "Product Page");
                }
            });
            productPanel.add(label);
            productPanel.add(new JSeparator(JSeparator.HORIZONTAL));

        }
        if (!allProducts.isEmpty()) {
            productPanel.remove(productPanel.getComponentCount() - 1);
        }

        /*
        selectedProductLabel = new JLabel("Selected Product: ");
        selectedProductLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        selectedProductLabel.setFont(new Font("Arial", Font.PLAIN, 18));
         */

        JLabel instructionLabel = new JLabel("Click a product to view its page.");
        instructionLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        instructionLabel.setFont(new Font("Arial", Font.PLAIN, 18));


        JPanel bottomPanel = new JPanel();
        //bottomPanel.add(selectedProductLabel);


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
        JPanel allStoresPanel = new JPanel();
        //JPanel topPanel = new JPanel();



        //topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS));
        //topPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        storeListingsPanel.setLayout(new BoxLayout(storeListingsPanel, BoxLayout.Y_AXIS));
        allStoresPanel.setLayout(new BoxLayout(allStoresPanel, BoxLayout.Y_AXIS));


        JLabel selectStore = new JLabel("Select a Store");
        selectStore.setAlignmentX(Component.CENTER_ALIGNMENT);
        selectStore.setFont(new Font("Arial", Font.PLAIN, 18));

        JButton backButton = createBackToMenuButton();
        backButton.setAlignmentX(Component.LEFT_ALIGNMENT);
        //topPanel.add(backButton);
        storeListingsPanel.add(backButton);

        ArrayList<Store> sellerStores = getAllStores();


       // String [] dummyStores = new String[]{"Store One" ,"Store Two","Store Three","Store Four", "Store Five", "Store Six", "Store Seven", "Store Eight",
               // "Store Nine", "Store Ten", "Store Eleven", "Store Twelve","Store Thirst","Store Fourteen","Store Fifth-teener","Store Sixty"};



        for(int i =0; i < sellerStores.size();i++){

            cStore = i;

            String name = sellerStores.get(i).getName();
            JLabel label = new JLabel(name);
            label.setAlignmentX(Component.CENTER_ALIGNMENT);
            label.setFont(new Font("Arial", Font.PLAIN, 18));

            label.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    // Handle click event
                    selectedStore = sellerStores.get(cStore);
                    storeName = label.getText();
                    //System.out.println(storeName);
                    // Update the display with the selected seller's name
                    //selectedStoreLabel.setText("Selected Store: " + storeName);

                    //selectedStore = currentStore;
                    JPanel storePanel = createIndividualStoreListingsPanel(selectedStore);
                    cardPanel.add(storePanel, "Current Store");
                    cardLayout.show(cardPanel, "Current Store");

                    //cardLayout.show(cardPanel,"Individual Store");


                }
            });

            allStoresPanel.add(label);
            allStoresPanel.add(new JSeparator(JSeparator.HORIZONTAL));
        }


        /*
        for (String name : dummyStores) {
            JLabel label = new JLabel(name);
            label.setAlignmentX(Component.CENTER_ALIGNMENT);
            label.setFont(new Font("Arial", Font.PLAIN, 18));

            label.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    // Handle click event
                    storeName = label.getText();
                    selectedStoreLabel.setText(storeName);
                    //currentStoreName = storeName;
                    //currentName = label.getText();
                    //System.out.println(storeName);
                    // Update the display with the selected seller's name
                    //selectedStoreLabel.setText("Selected Store: " + storeName);



                    cardLayout.show(cardPanel,"Individual Store");
                    //frame.setTitle(storeName +  " Page");


                }
            });

            allStoresPanel.add(label);
            allStoresPanel.add(new JSeparator(JSeparator.HORIZONTAL));

        }
        */


        if(!(sellerStores.size()==0)) {
            allStoresPanel.remove(allStoresPanel.getComponentCount() - 1);
        }
        JScrollPane jsp = new JScrollPane(allStoresPanel, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        // Display selected Store name
        //selectedStoreLabel = new JLabel("Selected Store: ");
        //selectedStoreLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        //selectedStoreLabel.setFont(new Font("Arial", Font.PLAIN, 18));

        storeListingsPanel.add(selectStore);
        storeListingsPanel.add(Box.createVerticalStrut(20));
        storeListingsPanel.add(jsp);
        storeListingsPanel.add(Box.createVerticalStrut(20));
        //storeListingsPanel.add(selectedStoreLabel);


        return storeListingsPanel;
    }

    // method that creates panel for the store listings page
    private JPanel createIndividualStoreListingsPanel(Store store) {
        //frame.setTitle("Store Page");
        JPanel individualStorePanel = new JPanel();
        JPanel listStoreProductsPanel = new JPanel();
        //frame.setTitle("Store Page");
        //System.out.println(storeName);
        JLabel sName = selectedStoreLabel;
        sName.setFont(new Font("Arial", Font.PLAIN, 18));
        //JLabel totalSales = new JLabel("TotalSales: ");
        individualStorePanel.setLayout(new BoxLayout(individualStorePanel, BoxLayout.Y_AXIS));
        listStoreProductsPanel.setLayout(new BoxLayout(listStoreProductsPanel, BoxLayout.Y_AXIS));
        individualStorePanel.add(sName);
        //individualStorePanel.add(totalSales);


        JButton backButton = createBackToMenuButton();
        individualStorePanel.add(backButton);

        String [] dummyProducts = new String[]{"Product One" ,"Product Two","Product Three","Product Four", "Product Five", "Product Six", "Product Seven", "Product Eight",
                "Product Nine", "Product Ten", "Product Eleven", "Product Twelve", "Product Thriteen", "Product Fourteen"};

        ArrayList<Product> storeProducts = store.getProducts();

        for(int i =0; i < storeProducts.size();i++){

            cStore = i;

            String name = storeProducts.get(i).getName();
            JLabel label = new JLabel(name);
            label.setAlignmentX(Component.CENTER_ALIGNMENT);
            label.setFont(new Font("Arial", Font.PLAIN, 18));

            label.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {

                    productName = label.getText();
                    /*
                    // Handle click event
                    selectedStore = sellerStores.get(cStore);
                    storeName = label.getText();
                    //System.out.println(storeName);
                    // Update the display with the selected seller's name
                    //selectedStoreLabel.setText("Selected Store: " + storeName);

                    //selectedStore = currentStore;
                    JPanel storePanel = createIndividualStoreListingsPanel(selectedStore);
                    cardPanel.add(storePanel, "Current Store");
                    cardLayout.show(cardPanel, "Current Store");

                    //cardLayout.show(cardPanel,"Individual Store");
                    */

                }
            });

            listStoreProductsPanel.add(label);
            listStoreProductsPanel.add(new JSeparator(JSeparator.HORIZONTAL));
        }

        /*
        for (String name : dummyProducts) {
            JLabel label = new JLabel(name);
            label.setAlignmentX(Component.CENTER_ALIGNMENT);
            label.setFont(new Font("Arial", Font.PLAIN, 18));

            label.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    // Handle click event
                    productName = label.getText();
                    //System.out.println(storeName);
                    // Update the display with the selected seller's name
                    //selectedStoreLabel.setText("Selected Store: " + storeName);
                    //cardLayout.show(cardPanel,"Selected Store Page");


                }
            });

            listStoreProductsPanel.add(label);
            listStoreProductsPanel.add(new JSeparator(JSeparator.HORIZONTAL));

        }
*/
        if(!(storeProducts.size()==0)) {
            listStoreProductsPanel.remove(listStoreProductsPanel.getComponentCount() - 1);
        }
        JScrollPane jsp = new JScrollPane(listStoreProductsPanel, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        //individualStorePanel.add(selectStore);
        individualStorePanel.add(Box.createVerticalStrut(20));
        individualStorePanel.add(jsp);
        individualStorePanel.add(Box.createVerticalStrut(20));




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
        ArrayList <Seller> allSellers = new Request().getAllSellers();

        sellerNamePanel.setLayout(new BoxLayout(sellerNamePanel, BoxLayout.Y_AXIS));

        for (Seller seller : allSellers) {
            JLabel label = new JLabel(seller.getUsername());
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
                JOptionPane.showMessageDialog(null,"Sending message to " + messageSellerName + ": " + message, "Customers", JOptionPane.INFORMATION_MESSAGE);
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

    private JPanel createSellerPanel(Seller seller) { //should have an object parameter that takes a Seller object
        JPanel sellerPanel = new JPanel();
        sellerPanel.setLayout(new BoxLayout(sellerPanel, BoxLayout.Y_AXIS));

        JPanel titlePanel = new JPanel();


        JLabel sellerUsername = new JLabel(seller.getUsername());
        sellerUsername.setAlignmentX(Component.CENTER_ALIGNMENT);
        sellerUsername.setFont(new Font("Arial", Font.PLAIN, 18));

        //titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.Y_AXIS));
        titlePanel.setLayout(new FlowLayout());
        titlePanel.setPreferredSize(new Dimension(400, 10));
        titlePanel.add(createBackToMenuButton());
        titlePanel.add(sellerUsername);
        titlePanel.add(createBackToAccountPageButton());

        ArrayList<Store> allStores = getAllStores();

        JPanel storesPanel = new JPanel();
        storesPanel.setLayout(new BoxLayout(storesPanel, BoxLayout.Y_AXIS));
        for (Store store: allStores) {
            JLabel storeLabel = new JLabel(store.getName() + ", " + store.getProductsSold());
            storeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            storeLabel.setFont(new Font("Arial", Font.PLAIN, 18));
            storeLabel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    JPanel panel = createShopPanel(store);
                    cardPanel.add(panel, "Store Page");
                    cardLayout.show(cardPanel, "Store Page");

                }
            });




            storesPanel.add(storeLabel);
            storesPanel.add(new JSeparator(JSeparator.HORIZONTAL));
        }
        if (!allStores.isEmpty()) {
            storesPanel.remove(storesPanel.getComponentCount() - 1);
        }

        JScrollPane jsp = new JScrollPane(storesPanel, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jsp.setPreferredSize(new Dimension(400, 250));
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new BoxLayout(bottomPanel,BoxLayout.Y_AXIS));

        JLabel instructions = new JLabel("Click on a store to view its page.");
        instructions.setAlignmentX(Component.CENTER_ALIGNMENT);
        instructions.setFont(new Font("Arial", Font.BOLD, 18));
        bottomPanel.add(instructions);

        JPanel dropdownPanel = new JPanel();
        dropdownPanel.setPreferredSize(new Dimension(400, 40));
        String[] options = new String[]{"Most Products", "Least Products"};
        JComboBox sortDropdown = new JComboBox(options);
        sortDropdown.setPreferredSize(new Dimension(200, 40));
        sortDropdown.setAlignmentX(Component.CENTER_ALIGNMENT);
        dropdownPanel.add(sortDropdown);

        sellerPanel.add(titlePanel);
        sellerPanel.add(sortDropdown);
        sellerPanel.add(Box.createVerticalStrut(20));
        sellerPanel.add(jsp);
        sellerPanel.add(Box.createVerticalStrut(20));
        sellerPanel.add(bottomPanel);





        return sellerPanel;
    }
    private JPanel createProductPanel(Product product) {
        JPanel productPanel = new JPanel();
        productPanel.setLayout(new BoxLayout(productPanel, BoxLayout.Y_AXIS));
        JLabel productName = new JLabel(product.getName());
        productName.setAlignmentX(Component.CENTER_ALIGNMENT);
        productName.setFont(new Font("Arial", Font.PLAIN, 18));


        JLabel productDescription = new JLabel(product.getDescription());
        productDescription.setAlignmentX(Component.CENTER_ALIGNMENT);
        productDescription.setFont(new Font("Arial", Font.PLAIN, 18));

        JLabel productStock = new JLabel(Integer.toString(product.getQuantity()));
        productStock.setAlignmentX(Component.CENTER_ALIGNMENT);
        productStock.setFont(new Font("Arial", Font.PLAIN, 18));

        JButton goToStoreButton = new JButton("Go To Product's Store");
        goToStoreButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        goToStoreButton.setFont(new Font("Arial", Font.PLAIN, 18));
        goToStoreButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JPanel panel = createIndividualStoreListingsPanel(product.getStore());
                cardPanel.add(panel, "Individual Store");
                cardLayout.show(cardPanel, "Individual Store");

            }
        });


        JButton addToCartButton = new JButton("Add to Cart");
        addToCartButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        addToCartButton.setFont(new Font("Arial", Font.PLAIN, 18));
        addToCartButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (customerShoppingCartQuantity > product.getQuantity()) {
                    JOptionPane.showMessageDialog(null, "Please choose a smaller quantity.", "Customers", JOptionPane.ERROR_MESSAGE);
                }
                else {
                    JOptionPane.showMessageDialog(null, "Added " + product.getName() + "x" + customerShoppingCartQuantity + "to your shopping cart!" , "Customers", JOptionPane.ERROR_MESSAGE);
                }

            }
        });

        JPanel quantityPanel = new JPanel();
        JTextField quantityTextField = new JTextField("       0       ");
        quantityTextField.setAlignmentX(Component.CENTER_ALIGNMENT);
        quantityTextField.setFont(new Font("Arial", Font.PLAIN, 18));

        quantityPanel.setLayout(new BoxLayout(quantityPanel, BoxLayout.Y_AXIS));
        JButton addButton = new JButton("+");
        addButton.setFont(new Font("Arial", Font.PLAIN, 18));
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                customerShoppingCartQuantity = Integer.parseInt(quantityTextField.getText());
                customerShoppingCartQuantity++;
                quantityTextField.setText("       " + customerShoppingCartQuantity + "       ");

            }
        });

        addButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        JButton minusButton = new JButton("-");
        minusButton.setFont(new Font("Arial", Font.PLAIN, 18));
        minusButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        minusButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                customerShoppingCartQuantity = Integer.parseInt(quantityTextField.getText());
                customerShoppingCartQuantity--;
                quantityTextField.setText("       " + customerShoppingCartQuantity + "       ");

            }
        });
        quantityPanel.add(minusButton);
        quantityPanel.add(quantityTextField);
        quantityPanel.add(addButton);

        productPanel.add(quantityPanel);
        productPanel.add(addToCartButton);
        return productPanel;
    }

    private JPanel createShopPanel(Store store) {
        JPanel shopPanel = new JPanel();
        shopPanel.setLayout(new BoxLayout(shopPanel, BoxLayout.Y_AXIS));
        JLabel storeName = new JLabel(store.getName());
        shopPanel.add(Box.createVerticalStrut(20));
        shopPanel.add(storeName);
        return shopPanel;
    }

    private JPanel createSearchOptionsPanel() {
        JPanel searchOptionsPanel = new JPanel();
        searchOptionsPanel.setLayout(new BoxLayout(searchOptionsPanel, BoxLayout.Y_AXIS));
        searchOptionsPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel questionLabel = new JLabel("What would you like to search by?");
        questionLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        questionLabel.setFont(new Font("Arial", Font.BOLD, 18));

        Dimension buttonDimension = new Dimension(225, 40);
        JButton productNamesButton = new JButton("Product");
        productNamesButton.setMaximumSize(buttonDimension);
        productNamesButton.setMinimumSize(buttonDimension);
        productNamesButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        productNamesButton.setFont(new Font("Arial", Font.PLAIN, 18));
        productNamesButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ArrayList<Product> products = getAllProducts();
                /*
                if (products.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "There are no currently no products.", "Customers", JOptionPane.ERROR_MESSAGE);

                }
                else {
                    cardLayout.show(cardPanel, "Search Product Input");
                    frame.setSize(400, 200);
                    frame.setLocationRelativeTo(null);
                }

                 */
                cardLayout.show(cardPanel, "Search Product Input");
                frame.setSize(400, 200);
                frame.setLocationRelativeTo(null);
            }
        });

        JButton storeNamesButton = new JButton("Store");
        storeNamesButton.setMaximumSize(buttonDimension);
        storeNamesButton.setMinimumSize(buttonDimension);
        storeNamesButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        storeNamesButton.setFont(new Font("Arial", Font.PLAIN, 18));

        JButton productDescriptionsButton = new JButton("Product Description");
        productDescriptionsButton.setMaximumSize(buttonDimension);
        productDescriptionsButton.setMinimumSize(buttonDimension);
        productDescriptionsButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        productDescriptionsButton.setFont(new Font("Arial", Font.PLAIN, 18));
        productDescriptionsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                ArrayList<Product> products = getAllProducts();
                /*
                if (products.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "There are no currently no products.", "Customers", JOptionPane.ERROR_MESSAGE);

                }
                else {
                    cardLayout.show(cardPanel, "Search Product Description Input");
                    frame.setSize(400, 200);
                    frame.setLocationRelativeTo(null);
                }

                 */
                cardLayout.show(cardPanel, "Search Product Description Input");
                frame.setSize(400, 200);
                frame.setLocationRelativeTo(null);
            }
        });

        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.X_AXIS));
        bottomPanel.add(createBackToMenuButton());
        bottomPanel.add(Box.createHorizontalStrut(300));

        searchOptionsPanel.add(Box.createVerticalStrut(20));
        searchOptionsPanel.add(questionLabel);
        searchOptionsPanel.add(Box.createVerticalStrut(20));
        searchOptionsPanel.add(productNamesButton);
        searchOptionsPanel.add(Box.createVerticalStrut(20));
        searchOptionsPanel.add(storeNamesButton);
        searchOptionsPanel.add(Box.createVerticalStrut(20));
        searchOptionsPanel.add(productDescriptionsButton);
        searchOptionsPanel.add(Box.createVerticalStrut(10));
        searchOptionsPanel.add(bottomPanel);
        searchOptionsPanel.add(Box.createVerticalStrut(20));


        return searchOptionsPanel;
    }

    private JPanel searchProductNamePanel () {
        JPanel searchedProductNameInput = new JPanel();
        searchedProductNameInput.setLayout(new BoxLayout(searchedProductNameInput, BoxLayout.Y_AXIS));
        searchedProductNameInput.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel title = new JLabel("Enter the name of the product.");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        title.setFont(new Font("Arial", Font.BOLD, 18));

        JTextField search = new JTextField();
        search.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton searchButton = new JButton("Search");
        searchButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        searchButton.setFont(new Font("Arial", Font.PLAIN, 18));

        searchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                boolean matchFound = false;
                ArrayList<Product> products = getAllProducts();
                for (Product product : products) {
                    if (product.getName().equalsIgnoreCase(search.getText())) {
                        matchFound = true;
                    }
                }
                /*
                if (matchFound) {
                    searchInputProductName = search.getText();
                    JPanel panel = createSearchProductsPanel(searchInputProductName);
                    cardPanel.add(panel, "Search Results Product Name");
                    cardLayout.show(cardPanel, "Search Results Product Name");
                    frame.setSize(400, 500);
                    frame.setLocationRelativeTo(null);
                } else {
                    JOptionPane.showMessageDialog(null, "There are no matching products", "Customers", JOptionPane.ERROR_MESSAGE);
                }
                */
                searchInputProductName = search.getText();
                JPanel panel = createSearchProductsPanel(searchInputProductName);
                cardPanel.add(panel, "Search Results for Product Name");
                cardLayout.show(cardPanel, "Search Results for Product Name");
                frame.setSize(400, 500);
                frame.setLocationRelativeTo(null);

            }
        });

        searchedProductNameInput.add(Box.createVerticalStrut(20));
        searchedProductNameInput.add(title);
        searchedProductNameInput.add(Box.createVerticalStrut(20));
        searchedProductNameInput.add(search);
        searchedProductNameInput.add(Box.createVerticalStrut(20));
        searchedProductNameInput.add(searchButton);
        return searchedProductNameInput;

    }

    private JPanel createSearchProductsPanel (String productName) {
        JPanel searchedProductsPanel = new JPanel();
        searchedProductsPanel.setLayout(new BoxLayout(searchedProductsPanel, BoxLayout.Y_AXIS));
        searchedProductsPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.Y_AXIS));

        JLabel title = new JLabel("Searched Products");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        title.setFont(new Font("Arial", Font.BOLD, 18));

        titlePanel.add(createBackToMenuButton());
        titlePanel.add(title);
        titlePanel.add(createBackToAccountPageButton());

        JPanel allProductsPanel = new JPanel();
        allProductsPanel.setLayout(new BoxLayout(allProductsPanel, BoxLayout.Y_AXIS));

        ArrayList<Product> allProducts = getAllProducts();

        for (Product product: allProducts) {
            if (product.getName().equalsIgnoreCase(productName)) {
                JLabel label = new JLabel(product.getName());
                label.setAlignmentX(Component.CENTER_ALIGNMENT);
                label.setFont(new Font("Arial", Font.PLAIN, 18));

                label.addMouseListener(new MouseAdapter() {
                    public void mouseClicked(MouseEvent e) {
                        //code to go to product page
                    }
                });
                allProductsPanel.add(label);
                allProductsPanel.add(new JSeparator(JSeparator.HORIZONTAL));
            }
        }
        JScrollPane jsp = new JScrollPane(allProductsPanel,ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        if (!allProducts.isEmpty()) {
            allProductsPanel.remove(allProductsPanel.getComponentCount() - 1);
        }


        JPanel bottomPanel = new JPanel();
        JLabel instructions = new JLabel("Select a product to view its page.");
        instructions.setFont(new Font("Arial", Font.PLAIN, 18));
        bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.Y_AXIS));
        bottomPanel.add(instructions);





        searchedProductsPanel.add(Box.createVerticalStrut(20));
        searchedProductsPanel.add(titlePanel);
        searchedProductsPanel.add(Box.createVerticalStrut(20));
        searchedProductsPanel.add(jsp);
        searchedProductsPanel.add(instructions);
        searchedProductsPanel.add(Box.createVerticalStrut(20));
        return searchedProductsPanel;
    }

    private JPanel searchProductDescriptionPanel() {
        JPanel searchProductDescriptionPanel = new JPanel();
        searchProductDescriptionPanel.setLayout(new BoxLayout(searchProductDescriptionPanel, BoxLayout.Y_AXIS));
        searchProductDescriptionPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel title = new JLabel("Enter the product's description.");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        title.setFont(new Font("Arial", Font.BOLD, 18));

        JTextField searchDescription = new JTextField();
        searchDescription.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton searchButton = new JButton("Search");
        searchButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        searchButton.setFont(new Font("Arial", Font.PLAIN, 18));

        searchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                boolean matchFound = false;
                ArrayList<Product> products = getAllProducts();
                for (Product product : products) {
                    if (product.getDescription().equalsIgnoreCase(searchDescription.getText())) {
                        matchFound = true;
                    }
                }
                /*
                if (matchFound) {
                    searchInputProductDescription = searchDescription.getText();
                    JPanel panel = createSearchProductDescriptionsPanel(searchInputProductName);
                    cardPanel.add(panel, "Search Results Product Description");
                    cardLayout.show(cardPanel, "Search Results Product Description");
                    frame.setSize(400, 500);
                    frame.setLocationRelativeTo(null);
                } else {
                    JOptionPane.showMessageDialog(null, "There are no matching products", "Customers", JOptionPane.ERROR_MESSAGE);
                }
                */
                searchInputProductDescription = searchDescription.getText();
                JPanel panel = createSearchProductDescriptionsPanel(searchInputProductDescription);
                cardPanel.add(panel, "Search Results Product Description");
                cardLayout.show(cardPanel, "Search Results Product Description");
                frame.setSize(400, 500);
                frame.setLocationRelativeTo(null);

            }
        });
        searchProductDescriptionPanel.add(Box.createVerticalStrut(20));
        searchProductDescriptionPanel.add(title);
        searchProductDescriptionPanel.add(Box.createVerticalStrut(20));
        searchProductDescriptionPanel.add(searchDescription);
        searchProductDescriptionPanel.add(Box.createVerticalStrut(20));
        searchProductDescriptionPanel.add(searchButton);


        return searchProductDescriptionPanel;
    }

    private JPanel createSearchProductDescriptionsPanel(String productDescription) {
        JPanel productDescriptions = new JPanel();
        productDescriptions.setLayout(new BoxLayout(productDescriptions, BoxLayout.Y_AXIS));
        productDescriptions.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.Y_AXIS));

        JLabel title = new JLabel("Searched Products");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        title.setFont(new Font("Arial", Font.BOLD, 18));

        titlePanel.add(createBackToMenuButton());
        titlePanel.add(title);
        titlePanel.add(createBackToAccountPageButton());

        JPanel allProductsPanel = new JPanel();
        allProductsPanel.setLayout(new BoxLayout(allProductsPanel, BoxLayout.Y_AXIS));

        ArrayList<Product> allProducts = getAllProducts();

        for (Product product: allProducts) {
            if (product.getDescription().equalsIgnoreCase(productDescription)) {
                JLabel label = new JLabel(product.getName());
                label.setAlignmentX(Component.CENTER_ALIGNMENT);
                label.setFont(new Font("Arial", Font.PLAIN, 18));

                label.addMouseListener(new MouseAdapter() {
                    public void mouseClicked(MouseEvent e) {
                        //code to go to product page
                    }
                });
                allProductsPanel.add(label);
                allProductsPanel.add(new JSeparator(JSeparator.HORIZONTAL));
            }
        }
        JScrollPane jsp = new JScrollPane(allProductsPanel,ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        if (!allProducts.isEmpty()) {
            allProductsPanel.remove(allProductsPanel.getComponentCount() - 1);
        }


        JPanel bottomPanel = new JPanel();
        JLabel instructions = new JLabel("Select a product to view more info.");
        instructions.setFont(new Font("Arial", Font.PLAIN, 18));
        bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.Y_AXIS));
        bottomPanel.add(instructions);

        productDescriptions.add(Box.createVerticalStrut(20));
        productDescriptions.add(titlePanel);
        productDescriptions.add(Box.createVerticalStrut(20));
        productDescriptions.add(jsp);
        productDescriptions.add(Box.createVerticalStrut(20));
        productDescriptions.add(bottomPanel);
        return productDescriptions;
    }

    private JPanel createShoppingCartPanel() {
        custShoppingCartName = "Robert";
        JPanel shoppingCartPanel = new JPanel();
        shoppingCartPanel.setLayout(new BoxLayout(shoppingCartPanel, BoxLayout.Y_AXIS));

        // Customer Name Label


        JButton backButton = createBackToMenuButton();

        JLabel customerNameLabel = new JLabel(custShoppingCartName+"'s Shopping Cart");
        customerNameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        customerNameLabel.setFont(new Font("Arial", Font.BOLD, 20));

        // Panel for back button and title
        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.X_AXIS));
        titlePanel.add(backButton);
        titlePanel.add(Box.createHorizontalStrut(20)); // Add some spacing
        titlePanel.add(customerNameLabel);

        // Items Label
        JLabel itemsLabel = new JLabel("Items currently in cart:");
        itemsLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        itemsLabel.setFont(new Font("Arial", Font.PLAIN, 16));

        // Sample Data (Replace with dynamic data)
        String[] storeData = {"Store One", "Store Two", "Store Three"};
        String[] productData = {"Product A", "Product B", "Product C"};
        double[] priceData = {10.0, 20.0, 15.0};

        // Create a JPanel to hold the cart items
        JPanel cartItemsPanel = new JPanel();
        cartItemsPanel.setLayout(new BoxLayout(cartItemsPanel, BoxLayout.Y_AXIS));

        // Headers
        JPanel headersPanel = new JPanel();
        headersPanel.setLayout(new GridLayout(1, 5, 10, 5)); // 1 row, 5 columns
        headersPanel.add(new JLabel("Store"));
        headersPanel.add(new JLabel("Product"));
        headersPanel.add(new JLabel("Price"));
        headersPanel.add(new JLabel("Quantity"));
        headersPanel.add(new JLabel("Delete"));

        cartItemsPanel.add(headersPanel);

        // Set a fixed height for each row
        int rowHeight = 30;

        // Populate the cart with sample data (replace with dynamic data)
        for (int i = 0; i < storeData.length; i++) {
            JPanel rowPanel = new JPanel();
            rowPanel.setLayout(new GridLayout(1, 5, 10, 5)); // 1 row, 5 columns
            rowPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, rowHeight));
            rowPanel.setPreferredSize(new Dimension(0, rowHeight));
            JLabel storeLabel = new JLabel(storeData[i]);
            JLabel productLabel = new JLabel(productData[i]);
            JLabel priceLabel = new JLabel(String.valueOf(priceData[i]));

            SpinnerModel quantityModel = new SpinnerNumberModel(1, 0, Integer.MAX_VALUE, 1);
            JSpinner quantitySpinner = new JSpinner(quantityModel);
            Dimension spinnerSize = new Dimension(60, 20);
            quantitySpinner.setMaximumSize(spinnerSize);
            quantitySpinner.setMinimumSize(spinnerSize);

            JButton deleteButton = new JButton("Delete");
            deleteButton.addActionListener(e -> {
                int deleteConfirmation = JOptionPane.showConfirmDialog(shoppingCartPanel,
                        "Are you sure you want to delete this item?\n\n" +
                                "Store: " + storeLabel.getText() + "\n" +
                                "Product: " + productLabel.getText(),
                        "Confirm Deletion", JOptionPane.YES_NO_OPTION);

                if (deleteConfirmation == JOptionPane.YES_OPTION) {
                    // User confirmed deletion, remove all components from the rowPanel
                    rowPanel.removeAll();

                    // Revalidate and repaint the panel
                    cartItemsPanel.revalidate();
                    cartItemsPanel.repaint();

                    // Resize the frame by reducing its height by 80 pixels
                    JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(shoppingCartPanel);
                    Dimension currentSize = frame.getSize();
                    frame.setSize(new Dimension(currentSize.width, currentSize.height - 80));
                }
            });

            quantitySpinner.addChangeListener(e -> {
                int quantity = (int) quantitySpinner.getValue();

                if (quantity < 1) {
                    int deleteConfirmation = JOptionPane.showConfirmDialog(shoppingCartPanel,
                            "Are you sure you want to delete this item?\n\n" +
                                    "Store: " + storeLabel.getText() + "\n" +
                                    "Product: " + productLabel.getText(),
                            "Confirm Deletion", JOptionPane.YES_NO_OPTION);

                    if (deleteConfirmation == JOptionPane.YES_OPTION) {
                        // User confirmed deletion, remove all components from the rowPanel
                        rowPanel.removeAll();

                        // Revalidate and repaint the panel
                        cartItemsPanel.revalidate();
                        cartItemsPanel.repaint();

                        // Resize the frame by reducing its height by 80 pixels
                        JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(shoppingCartPanel);
                        Dimension currentSize = frame.getSize();
                        frame.setSize(new Dimension(currentSize.width, currentSize.height - 80));
                    } else {
                        // Reset quantity to 1 if the user chooses not to delete
                        quantitySpinner.setValue(1);
                    }
                }
            });

            rowPanel.add(storeLabel);
            rowPanel.add(productLabel);
            rowPanel.add(priceLabel);
            rowPanel.add(quantitySpinner);
            rowPanel.add(deleteButton);

            cartItemsPanel.add(rowPanel);
        }

        // Checkout Button
        JButton checkoutButton = new JButton("Checkout");
        checkoutButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        checkoutButton.setFont(new Font("Arial", Font.PLAIN, 16));
        checkoutButton.setForeground(Color.RED);

        checkoutButton.addActionListener(e -> {
            int confirmCheckout = JOptionPane.showConfirmDialog(shoppingCartPanel,
                    "Are you sure you want to proceed to checkout?",
                    "Confirm Checkout", JOptionPane.YES_NO_OPTION);

            if (confirmCheckout == JOptionPane.YES_OPTION) {
                // Add checkout logic here
                // ...
            }
        });

        // Add components to the shopping cart panel
        shoppingCartPanel.add(titlePanel);
        shoppingCartPanel.add(Box.createVerticalStrut(10));
        shoppingCartPanel.add(itemsLabel);
        shoppingCartPanel.add(cartItemsPanel);
        shoppingCartPanel.add(Box.createVerticalStrut(20));
        shoppingCartPanel.add(checkoutButton);
        shoppingCartPanel.add(Box.createVerticalStrut(20));

        return shoppingCartPanel;
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


    private JButton createBackToAccountPageButton() {
        JButton accountButton = new JButton("Account");
        accountButton.setFont(new Font("Arial", Font.PLAIN, 18));
        accountButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        accountButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "Account Page");
                frame.setSize(400, 500);
                frame.setLocationRelativeTo(null);
            }
        });
        return accountButton;
    }
    private static ArrayList<Store> getAllStores() {
        ArrayList<Seller> allSellers = new Request().getAllSellers();
        ArrayList<Store> allStores = new ArrayList<>();
        for (Seller seller: allSellers) {
            allStores.addAll(seller.getSellerStores());
        }
        return allStores;
    }

    private static ArrayList<Product> getAllProducts() {
        ArrayList<Seller> allSellers = new Request().getAllSellers();
        ArrayList<Product> allProducts = new ArrayList<>();
        for (Seller seller : allSellers) {
            for (Store store : seller.getSellerStores()) {
                allProducts.addAll(store.getProducts());
            }
        }
        return allProducts;
    }

    private static ArrayList<String> getAllProductDescriptions() {
        ArrayList<Seller> allSellers = new Request().getAllSellers();
        ArrayList<String> allProductDescriptions = new ArrayList<>();
        for (Seller seller : allSellers) {
            for (Store store : seller.getSellerStores()) {
                for (Product product : store.getProducts()) {

                    allProductDescriptions.add(product.getDescription());
                }
            }
        }
        return allProductDescriptions;
    }

    /*
    private static Product getBestSellerOne() {
        ArrayList<Integer> allProductsSoldCount = new ArrayList<>();
        ArrayList<Store> allStores = getAllStores();
        for (Store store : allStores) {
            allProductsSoldCount.add(store.getProductsSold());
        }
        int[] listOfProductCounts = new int[allProductsSoldCount.size()];
        for (int i = 0; i < listOfProductCounts.length; i++){
            listOfProductCounts[i] = allProductsSoldCount.get(i);
        }
        int temp;
        for (int i = 0; i < listOfProductCounts.length; i++) {
            for (int j = i; j < listOfProductCounts.length; j++) {

            }
        }
        return
    }

     */


}