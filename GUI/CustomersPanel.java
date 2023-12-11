import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.lang.reflect.Array;
import java.time.Year;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Collections;

public class CustomersPanel {

    String userEmail;
    Account customerAccount;

    private JFrame frame;
    private JPanel cardPanel;
    private JPanel productPanel;

    private String custShoppingCartName;
    private String pastPurchasesProductName;

    private String pastPurchasesStoreName;

    private CardLayout cardLayout;

    private String sellerName;

    private JLabel sellerSelectedLabel;

    private JLabel selectedProductLabel;
    private Product selectedProduct;

    private String searchInputProductName;

    private String searchInputStoreName;

    private String searchInputProductDescription;

    private int customerShoppingCartQuantity = 0;

    private Product bestSellerOne;
    private Product bestSellerTwo;
    private Product bestSellerThree;
    private String storeName;

    private String searchStoreName;

    private JLabel selectedStoreLabel = new JLabel("");;

    private String productName;

    private Seller selectedSeller;

    private Store sStore;

    private Product pProduct;

    private int currentS = 0;

    private int currentP = 0;

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> new CustomersPanel("cox44@purdue.edu"));

    }


    public CustomersPanel(String userEmail){
        this.userEmail = userEmail;
        this.customerAccount = new Request().getAccount(userEmail);

        frame = new JFrame("Customers");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);
        cardPanel.add(createMainPanel(), "Main Page");
//        cardPanel.add(createAccountPanel(), "Account Options");
//        cardPanel.add(createSellerListingsPanel(), "Seller Listings");
//        cardPanel.add(createProductListingsPanel(), "Product Listings");
//        cardPanel.add(createCustomerPurchaseHistoryPanel(), "Customer Purchase History");
//        cardPanel.add(createSellerListingSortPanel(), "Seller Listing Sort");
//        cardPanel.add(createLogOutPanel(), "Log Out");
//        cardPanel.add(createContactSellerPanel(), "Contact Sellers");
//        cardPanel.add(createSellerListingsPanel(), "Seller Listings");
//        cardPanel.add(createStoreListingsPagePanel(), "Store Listings");
//        cardPanel.add(createSearchOptionsPanel(), "Search Options");
//        cardPanel.add(searchProductNamePanel(), "Search Product Input");
//        cardPanel.add(searchStoreNamePanel(), "Search Store Input");
//        cardPanel.add(searchProductDescriptionPanel(), "Search Product Description Input");


        frame.add(cardPanel);

        cardLayout.show(cardPanel, "Main Page");
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }

    //method that creates the panel for the main page
    private JPanel createMainPanel() {
        frame.setSize(400,500);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        JPanel mainPagePanel = new JPanel();
        mainPagePanel.setLayout(new BoxLayout(mainPagePanel, BoxLayout.Y_AXIS));
        //mainPagePanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel applicationLabel = new JLabel("Boiler Bay");
        applicationLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        applicationLabel.setFont(new Font("Arial", Font.BOLD, 24));

        JButton switchToAccountPanelButton = new JButton("Account");
        switchToAccountPanelButton.setFont(new Font("Arial", Font.PLAIN, 10));
        switchToAccountPanelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                cardPanel.add(createAccountPanel(), "Account Options");
                cardLayout.show(cardPanel, "Account Options");

            }
        });

        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.X_AXIS));
        topPanel.setAlignmentY(Component.CENTER_ALIGNMENT);
        topPanel.add(Box.createHorizontalStrut(100));
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

                cardPanel.add(createProductListingsPanel(getAllProducts()), "Product Listings");
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
        frame.setLocationRelativeTo(null);
        JPanel createAccountPanel = new JPanel();
        createAccountPanel.setLayout(new BoxLayout(createAccountPanel, BoxLayout.Y_AXIS));

        String customerUsername = customerAccount.getUsername();
        JLabel titleLabel = new JLabel(String.format("%s's Account", customerUsername));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));


        JPanel topPanel = new JPanel();
        Dimension topPanelDimension = new Dimension(400, 20);
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.X_AXIS));
        topPanel.setPreferredSize(topPanelDimension);
        topPanel.setAlignmentY(Component.CENTER_ALIGNMENT);
        topPanel.add(createBackToMenuButton());
        topPanel.add(Box.createHorizontalStrut(20));
        topPanel.add(titleLabel);
        topPanel.add(Box.createHorizontalStrut(60));
        //topPanel.add(logOutButton);

        Dimension buttonDimension = new Dimension(300, 50);
        JButton shoppingCartButton = new JButton(("View Shopping Cart"));
        shoppingCartButton.setPreferredSize(buttonDimension);
        shoppingCartButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        shoppingCartButton.setFont(new Font("Arial", Font.PLAIN, 18));

        shoppingCartButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                cardPanel.add(createShoppingCartPanel(), "Shopping Cart");
                cardLayout.show(cardPanel, "Shopping Cart");
            }
        });

        JButton purchaseHistoryButton = new JButton(("My Purchase History"));
        purchaseHistoryButton.setPreferredSize(buttonDimension);
        purchaseHistoryButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        purchaseHistoryButton.setFont(new Font("Arial", Font.PLAIN, 18));
        purchaseHistoryButton.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {

            cardPanel.add(createPastPurchasesPanel(), "Past Purchases");
            cardLayout.show(cardPanel, "Past Purchases");
            frame.setSize(400, 500);
        }
    });


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
    private JPanel createProductListingsPanel(ArrayList<Product> products) { //not entirely sure how to implement the refresh feature based on what is selected in the dropdow
        frame.setSize(400,500);
        frame.setLocationRelativeTo(null);

        JPanel productsListingPanel = new JPanel();
        productsListingPanel.setLayout(new BoxLayout(productsListingPanel, BoxLayout.Y_AXIS));
        JLabel titleLabel = new JLabel("All Products"); //not completely centered
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
//
        JPanel titlePanel = new JPanel();
        titlePanel.setPreferredSize(new Dimension(400, 50));
        titlePanel.setMaximumSize(new Dimension(400, 50));
        titlePanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        titlePanel.add(createBackToMenuButton());
        titlePanel.add(Box.createHorizontalStrut(50));
        titlePanel.add(titleLabel);
        titlePanel.add(Box.createHorizontalStrut(20));
        titlePanel.add(createBackToAccountPageButton());

        String [] dropdownOptions = new String[]{"Price High To Low", "Price Low to High", "Quantity Least to Greatest", "Quantity Greatest to Least"};
        JComboBox sortByDropdown = new JComboBox<>(dropdownOptions);

        sortByDropdown.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String selection = sortByDropdown.getSelectedItem().toString();

                ArrayList<Product> sortedProducts = new ArrayList<>();

                switch (selection) {

                    case "Price High To Low" -> {

                        sortedProducts = Product.sortByPrice(products, "high");

                    }
                    case "Price Low to High" -> {



                    }
                    case "Quantity Least to Greatest" -> {



                    }
                    case "Quantity Greatest to Least" -> {



                    }

                }

                productPanel.removeAll();

                for (Product product : sortedProducts) {

                    JLabel productLine = new JLabel(String.format("Product: %s | $%.2f | %d", product.getName(), product.getPrice(), product.getQuantity()));
                    productLine.setAlignmentX(Component.CENTER_ALIGNMENT);
                    productLine.setFont(new Font("Arial", Font.PLAIN, 15));

                    productLine.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(MouseEvent e) {

                            selectedProduct = product;
                            cardPanel.add(createProductPanel(), "Product Page");
                            cardLayout.show(cardPanel, "Product Page");

                        }
                    });

                    productPanel.add(productLine);
                    productPanel.add(Box.createVerticalStrut(1));
                    productPanel.add(new JSeparator(JSeparator.HORIZONTAL));

                }

                productPanel.revalidate();
                productPanel.repaint();

            }
        });


        JPanel dropDownPanel = new JPanel();
        dropDownPanel.setPreferredSize(new Dimension(400, 40));
        dropDownPanel.setMaximumSize(new Dimension(400,40));
        dropDownPanel.add(sortByDropdown);
      
        JPanel helperPanel = new JPanel();
        helperPanel.setLayout(new BorderLayout());

        productPanel = new JPanel();
        productPanel.setLayout((new BoxLayout(productPanel, BoxLayout.Y_AXIS)));

        for (Product product : products) {

            JLabel productLine = new JLabel(String.format("Product: %s | $%.2f | %d", product.getName(), product.getPrice(), product.getQuantity()));
            productLine.setAlignmentX(Component.CENTER_ALIGNMENT);
            productLine.setFont(new Font("Arial", Font.PLAIN, 15));

            productLine.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {

                    selectedProduct = product;
                    cardPanel.add(createProductPanel(), "Product Page");
                    cardLayout.show(cardPanel, "Product Page");

                }
            });

            productPanel.add(productLine);
            productPanel.add(Box.createVerticalStrut(1));
            productPanel.add(new JSeparator(JSeparator.HORIZONTAL));

        }

        helperPanel.add(productPanel, BorderLayout.NORTH);

        JScrollPane jsp = new JScrollPane(helperPanel, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jsp.setPreferredSize(new Dimension(300, 300));
        jsp.setMaximumSize(new Dimension(300,300));


        if (!products.isEmpty()) {
            productPanel.remove(productPanel.getComponentCount() - 1);
        }


        JLabel instructionLabel = new JLabel("Click a product to view its page.");
        instructionLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        instructionLabel.setFont(new Font("Arial", Font.PLAIN, 18));

        productsListingPanel.add(Box.createVerticalStrut(20));
        productsListingPanel.add(titlePanel);
        productsListingPanel.add(new JSeparator(JSeparator.HORIZONTAL));
        productsListingPanel.add(Box.createVerticalStrut(5));
        productsListingPanel.add(dropDownPanel);
        productsListingPanel.add(Box.createVerticalStrut(5));
        productsListingPanel.add(jsp);
        productsListingPanel.add(Box.createVerticalStrut(20));
        productsListingPanel.add(instructionLabel);
        productsListingPanel.add(Box.createVerticalStrut(20));
        return productsListingPanel;
    }



    // method that creates panel that lists all created stores
    private JPanel createStoreListingsPagePanel() {

        JPanel storeListingsPanel = new JPanel();
        JPanel allStoresPanel = new JPanel();
        //JPanel topPanel = new JPanel();
        //topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS));
        //topPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        storeListingsPanel.setLayout(new BoxLayout(storeListingsPanel, BoxLayout.Y_AXIS));
        allStoresPanel.setLayout(new BoxLayout(allStoresPanel, BoxLayout.Y_AXIS));
        allStoresPanel.setBackground(greyButtonColor);


        JLabel selectStore = new JLabel("Select a Store");
        selectStore.setAlignmentX(Component.CENTER_ALIGNMENT);
        selectStore.setFont(new Font("Arial", Font.BOLD, 20));

        JButton accountPageButton = createBackToAccountPageButton();

        JButton backButton = createBackToMenuButton();
        backButton.setAlignmentX(Component.LEFT_ALIGNMENT);
        //topPanel.add(backButton);
        storeListingsPanel.add(backButton);


        topPanel.add(Box.createHorizontalStrut(30));
        topPanel.add(backButton);
        topPanel.add(Box.createHorizontalStrut(50));
        topPanel.add(selectStore);
        topPanel.add(Box.createHorizontalStrut(50));
        topPanel.add(accountPageButton);
        topPanel.add(Box.createHorizontalStrut(20));

        ArrayList<Store> sellerStores = getAllStores();

        String [] dropdownOptions = new String[]{"Most Products Sold", "Least Products Sold"};
        JComboBox sortByDropdown = new JComboBox<>(dropdownOptions);
        sortByDropdown.setBackground(greyButtonColor);
        sortByDropdown.setBorder(customBorder);
        sortByDropdown.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String selection = sortByDropdown.getSelectedItem().toString();
                System.out.println(selection);
                Store temp;
                switch (selection) {
                    case "Most Products Sold" -> {
                        for (int i = 0; i < sellerStores.size(); i++) {
                            for (int j = i; j < sellerStores.size(); j++) {
                                if (sellerStores.get(i).getProductsSold()< sellerStores.get(j).getProductsSold()) {
                                    temp = sellerStores.get(i);
                                    sellerStores.set(i, sellerStores.get(j));
                                    sellerStores.set(j, temp);
                                }
                            }
                        }
                        for (Store store : sellerStores) {
                            System.out.println(store.getName() + " " + store.getProductsSold());
                        }
                    }
                    case "Least Products Sold" -> {
                        System.out.println("reached");
                        for (int i = 0; i < sellerStores.size(); i++) {
                            for (int j = i; j < sellerStores.size(); j++) {
                                if (sellerStores.get(i).getProductsSold() > sellerStores.get(j).getProductsSold()) {
                                    temp = sellerStores.get(i);
                                    sellerStores.set(i, sellerStores.get(j));
                                    sellerStores.set(j, temp);
                                }
                            }
                        }
                        for (Store store : sellerStores) {
                            System.out.println(store.getName() + store.getProductsSold());
                        }
                    }
                    default -> {
                        for (int i = 0; i < sellerStores.size(); i++) {
                            for (int j = i; j < sellerStores.size(); j++) {
                                if (sellerStores.get(i).getProductsSold()< sellerStores.get(j).getProductsSold()) {
                                    temp = sellerStores.get(i);
                                    sellerStores.set(i, sellerStores.get(j));
                                    sellerStores.set(j, temp);
                                }
                            }
                        }
                        for (Store store : sellerStores) {
                            System.out.println(store.getName() + " " + store.getProductsSold());
                        }
                    }
                }
                allStoresPanel.removeAll();
                for (Store store: sellerStores) {
                    if (sellerStores.isEmpty()) {
                        break;
                    }
                    JLabel label = new JLabel(store.getName());
                    label.setAlignmentX(Component.CENTER_ALIGNMENT);
                    label.setFont(new Font("Arial", Font.PLAIN, 18));
                    label.addMouseListener(new MouseAdapter() {
                        public void mouseClicked(MouseEvent e) {
                            JPanel panel = createIndividualStoreListingsPanel(store);
                            cardPanel.add(panel, "Store Page");
                            cardLayout.show(cardPanel, "Store Page");
                        }
                    });
                    allStoresPanel.add(label);
                    allStoresPanel.add(new JSeparator(JSeparator.HORIZONTAL));
                }
                if (!sellerStores.isEmpty()) {
                    allStoresPanel.remove(allStoresPanel.getComponentCount() - 1);
                }
                allStoresPanel.revalidate();
                allStoresPanel.repaint();
            }
        });



        for (Store store : sellerStores) {
            if (sellerStores.isEmpty()) {
                break;
            }

            String name = store.getName();
            JLabel label = new JLabel(name);
            label.setAlignmentX(Component.CENTER_ALIGNMENT);
            label.setFont(new Font("Arial", Font.PLAIN, 18));
            label.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    // Handle click event
                    storeName = label.getText();
//                    sStore = sellerStores.get(currentS);
                    // Update the display with the selected seller's name
                    //selectedStoreLabel.setText("Selected Store: " + storeName);

                    // cardLayout.show(cardPanel,"Individual Store");

                    JPanel panel = createIndividualStoreListingsPanel(store);

                    cardPanel.add(panel, "Store Page");
                    cardLayout.show(cardPanel, "Store Page");



                }
            });
            allStoresPanel.add(label);
            allStoresPanel.add(new JSeparator(JSeparator.HORIZONTAL));

        }


        if(!sellerStores.isEmpty()){
            allStoresPanel.remove(allStoresPanel.getComponentCount() - 1);
        }

        JPanel dropDownPanel = new JPanel();
        dropDownPanel.setOpaque(false);
        dropDownPanel.setMaximumSize(new Dimension(400, 40));
        dropDownPanel.setMinimumSize(new Dimension(400, 40));
        dropDownPanel.add(sortByDropdown);

        JScrollPane jsp = new JScrollPane(allStoresPanel, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jsp.setMinimumSize(new Dimension(325, 275));
        jsp.setMaximumSize(new Dimension(325, 275));
        jsp.getViewport().setBackground(greyButtonColor);
        jsp.setBorder(customBorder);


        JLabel instructionLabel = new JLabel("Click a store to view its page.");
        instructionLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        instructionLabel.setFont(new Font("Arial", Font.PLAIN, 18));


        storeListingsPanel.add(Box.createVerticalStrut(20));
        storeListingsPanel.add(topPanel);
        storeListingsPanel.add(Box.createVerticalStrut(10));
        storeListingsPanel.add(dropDownPanel);
        storeListingsPanel.add(Box.createVerticalStrut(10));

        storeListingsPanel.add(jsp);
        storeListingsPanel.add(Box.createVerticalStrut(10));
        storeListingsPanel.add(instructionLabel);
        storeListingsPanel.add(Box.createVerticalStrut(20));
        //storeListingsPanel.add(selectedStoreLabel);


        return storeListingsPanel;
    }

    // method that creates panel that list the store with all of its products
    private JPanel createIndividualStoreListingsPanel(Store store) {
//        frame.setTitle("Customers Store Page");
        JPanel individualStorePanel = new JPanel();

        individualStorePanel.setBackground(customColor);
        individualStorePanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        individualStorePanel.setLayout(new BoxLayout(individualStorePanel, BoxLayout.Y_AXIS));

        JPanel listStoreProductsPanel = new JPanel();
        listStoreProductsPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        listStoreProductsPanel.setOpaque(false);
        listStoreProductsPanel.setMinimumSize(new Dimension(325, 300));
        listStoreProductsPanel.setMaximumSize(new Dimension(325, 300));
        listStoreProductsPanel.setLayout(new BoxLayout(listStoreProductsPanel, BoxLayout.Y_AXIS));

        JPanel topPanel = new JPanel();
        topPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        topPanel.setOpaque(false);
        topPanel.setMaximumSize(new Dimension(400, 50));
        topPanel.setMinimumSize(new Dimension(400, 50));
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.X_AXIS));

        JLabel sName = new JLabel(store.getName());
        sName.setAlignmentX(Component.CENTER_ALIGNMENT);
        sName.setFont(new Font("Arial", Font.BOLD, 20));
//        sName.setMaximumSize(new Dimension(200, 40));
//        sName.setMinimumSize(new Dimension(200, 40));


        JButton backButton = createBackToMenuButton();
        JButton accountButton = createBackToAccountPageButton();


        topPanel.add(Box.createHorizontalStrut(20));
        topPanel.add(backButton);
        topPanel.add(Box.createHorizontalStrut(50));
        topPanel.add(sName);
        topPanel.add(Box.createHorizontalStrut(40));
        topPanel.add(accountButton);
        topPanel.add(Box.createHorizontalStrut(20));



        ArrayList<Product> storeProducts = store.getProducts();

        for (Product storeProduct : storeProducts) {
            if (storeProducts.isEmpty()) {
                break;
            }


            String name = storeProduct.getName();
            JLabel label = new JLabel(name);
            label.setAlignmentX(Component.CENTER_ALIGNMENT);
            label.setFont(new Font("Arial", Font.PLAIN, 18));

            label.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    // Handle click event
                    productName = label.getText();
//                    sStore = sellerStores.get(currentS);
                    // Update the display with the selected seller's name
                    //selectedStoreLabel.setText("Selected Store: " + storeName);
                    // cardLayout.show(cardPanel,"Individual Store");
                    JPanel panel = createProductPanel();
                    cardPanel.add(panel, "Product Page");
                    cardLayout.show(cardPanel, "Product Page");


                }
            });
            listStoreProductsPanel.add(label);
            listStoreProductsPanel.add(new JSeparator(JSeparator.HORIZONTAL));

        }




        if(!storeProducts.isEmpty()){
            listStoreProductsPanel.remove(listStoreProductsPanel.getComponentCount() - 1);
        }


        JScrollPane jsp = new JScrollPane(listStoreProductsPanel, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jsp.setMinimumSize(new Dimension(325, 300));
        jsp.setMaximumSize(new Dimension(325, 300));
        jsp.getViewport().setBackground(greyButtonColor);
        jsp.setBorder(customBorder);

        JLabel instructionLabel = new JLabel("Click a Product to View its Page");
        instructionLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        instructionLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        instructionLabel.setMinimumSize(new Dimension(300, 50));
        instructionLabel.setMaximumSize(new Dimension(300, 50));

        individualStorePanel.add(Box.createVerticalStrut(20));
        individualStorePanel.add(topPanel);
        individualStorePanel.add(Box.createVerticalStrut(20));
        individualStorePanel.add(jsp);
        individualStorePanel.add(Box.createVerticalStrut(20));
        individualStorePanel.add(instructionLabel);
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

    private JPanel createPastPurchasesPanel() {
        JPanel pastPurchasesPanel = new JPanel();
        pastPurchasesPanel.setLayout(new BoxLayout(pastPurchasesPanel, BoxLayout.Y_AXIS));

        // Back button
        JButton backButtonPastPurchases = createBackToMenuButton();
        backButtonPastPurchases.setMaximumSize(new Dimension(45, 30));

        Dimension titleDimension = new Dimension(250, 50);
        JLabel titleLabelPastPurchases = new JLabel("Export Purchase History");

        titleLabelPastPurchases.setMinimumSize(titleDimension);
        titleLabelPastPurchases.setMaximumSize(titleDimension);
        titleLabelPastPurchases.setAlignmentX(Component.CENTER_ALIGNMENT);
        titleLabelPastPurchases.setFont(new Font("Arial", Font.BOLD, 16));

        // Panel for back button and title
        JPanel titlePanelPastPurchases = new JPanel();
        titlePanelPastPurchases.setLayout(new BoxLayout(titlePanelPastPurchases, BoxLayout.X_AXIS));
        titlePanelPastPurchases.add(Box.createHorizontalStrut(15));
        titlePanelPastPurchases.add(backButtonPastPurchases);
        titlePanelPastPurchases.add(Box.createHorizontalStrut(20));
        titlePanelPastPurchases.add(titleLabelPastPurchases);

        // Column labels
        JLabel productLabel = new JLabel("Product");
        JLabel priceLabel = new JLabel("Price");
        JLabel quantityLabel = new JLabel("Quantity");

        JPanel columnLabelsPanel = new JPanel();
        columnLabelsPanel.setLayout(new GridLayout(1, 3));
        columnLabelsPanel.add(productLabel);
        columnLabelsPanel.add(priceLabel);
        columnLabelsPanel.add(quantityLabel);

        JPanel storeNamePanelPastPurchases = new JPanel();
        String[] dummyStoresItem = new String[]{"Pdt 1", "Price 1", "Qty 1",
                "Pdt 2", "Price 2", "Qty 2",
                "Pdt 3", "Price 3", "Qty 3",
                "Pdt 4", "Price 4", "Qty 4",
                "Pdt 5", "Price 5", "Qty 5",
                "Pdt 6", "Price 6", "Qty 6",
                "Pdt 7", "Price 7", "Qty 7",
                "Pdt 8", "Price 8", "Qty 8",
                "Pdt 9", "Price 9", "Qty 9",
                "Pdt 10", "Price 10", "Qty 10",
                "Pdt 11", "Price 11", "Qty 11",
                "Pdt 12", "Price 12", "Qty 12",
                "Pdt 13", "Price 13", "Qty 13",
                "Pdt 14", "Price 14", "Qty 14",
                "Pdt 15", "Price 15", "Qty 15"};

        storeNamePanelPastPurchases.setLayout(new GridLayout(dummyStoresItem.length / 3, 3)); // 3 columns


        for (int i = 0; i < dummyStoresItem.length; i += 3) {
            JLabel productLabelItem = new JLabel(dummyStoresItem[i]);
            JLabel priceLabelItem = new JLabel(dummyStoresItem[i + 1]);
            JLabel quantityLabelItem = new JLabel(dummyStoresItem[i + 2]);

            productLabelItem.setAlignmentX(Component.CENTER_ALIGNMENT);
            productLabelItem.setFont(new Font("Arial", Font.PLAIN, 18));

            priceLabelItem.setAlignmentX(Component.CENTER_ALIGNMENT);
            priceLabelItem.setFont(new Font("Arial", Font.PLAIN, 18));

            quantityLabelItem.setAlignmentX(Component.CENTER_ALIGNMENT);
            quantityLabelItem.setFont(new Font("Arial", Font.PLAIN, 18));


            storeNamePanelPastPurchases.add(productLabelItem);
            storeNamePanelPastPurchases.add(priceLabelItem);
            storeNamePanelPastPurchases.add(quantityLabelItem);
        }

        // Add a line between columns
        storeNamePanelPastPurchases.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, Color.BLACK));

        JScrollPane pspPastPurchases = new JScrollPane(storeNamePanelPastPurchases, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        pspPastPurchases.setPreferredSize(new Dimension(300, 320));

        JButton confirmButtonPastPurchases = new JButton("Export");
        confirmButtonPastPurchases.setAlignmentX(Component.CENTER_ALIGNMENT);
        confirmButtonPastPurchases.setFont(new Font("Arial", Font.PLAIN, 18));
        confirmButtonPastPurchases.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (dummyStoresItem == null) {
                    JOptionPane.showMessageDialog(createPastPurchasesPanel(), "You have no Purchase History to Export", "Error!", JOptionPane.ERROR_MESSAGE);
                } else {
                    // Handle confirmation action
                    List<String[]> data = new ArrayList<>();
                    data.add(new String[]{"Product", "Price", "Quantity"});

                    for (int i = 0; i < dummyStoresItem.length; i += 3) {
                        String[] row = new String[]{dummyStoresItem[i], dummyStoresItem[i + 1], dummyStoresItem[i + 2]};
                        data.add(row);
                    }

                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd-HHmmss");
                    String timestamp = dateFormat.format(new Date());
                    String fileName = timestamp + "-" + customerShoppingCartQuantity + "-purchase history.csv";

                    try (FileWriter writer = new FileWriter(fileName)) {
                        for (String[] rowData : data) {
                            writer.write(String.join(",", rowData) + "\n");
                        }
                        JOptionPane.showMessageDialog(createPastPurchasesPanel(), "Export successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(createPastPurchasesPanel(), "Error exporting to CSV.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        pastPurchasesPanel.setLayout(new BoxLayout(pastPurchasesPanel, BoxLayout.Y_AXIS));
        pastPurchasesPanel.add(Box.createVerticalStrut(20));
        pastPurchasesPanel.add(titlePanelPastPurchases);
        pastPurchasesPanel.add(columnLabelsPanel);
        pastPurchasesPanel.add(pspPastPurchases);
        pastPurchasesPanel.add(Box.createVerticalStrut(20));
        pastPurchasesPanel.add(exportButton);

        pastPurchasesPanel.add(Box.createVerticalStrut(20));

        return pastPurchasesPanel;
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
                    JPanel panel = createIndividualStoreListingsPanel(store);
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
        instructions.setFont(new Font("Arial", Font.PLAIN, 18));
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

    private JPanel createProductPanel() {
        frame.setSize(400,500);
        frame.setLocationRelativeTo(null);

        JPanel productPanel = new JPanel();
        productPanel.setLayout(new BoxLayout(productPanel, BoxLayout.Y_AXIS));

        JPanel topPanel = new JPanel();
        topPanel.setPreferredSize(new Dimension(400,50));
        topPanel.setMaximumSize(new Dimension(400,50));
        topPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        JLabel titleLabel = new JLabel("Product Page", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));

        topPanel.add(createBackToProductListingsButton());
        topPanel.add(Box.createHorizontalStrut(40));
        topPanel.add(titleLabel);
        topPanel.add(Box.createHorizontalStrut(70));

        JPanel productDetails = new JPanel();
        productDetails.setAlignmentX(Component.CENTER_ALIGNMENT);
        productDetails.setLayout(new BoxLayout(productDetails, BoxLayout.Y_AXIS));
        productDetails.setPreferredSize(new Dimension(300, 250));
        productDetails.setMaximumSize(new Dimension(300,250));

        JLabel productName = new JLabel(String.format("Product: %s", selectedProduct.getName()));
        productName.setAlignmentX(Component.LEFT_ALIGNMENT);
        productName.setFont(new Font("Arial", Font.PLAIN, 18));

        JLabel productPrice = new JLabel(String.format("Price: $%.2f", selectedProduct.getPrice()));
        productPrice.setAlignmentX(Component.LEFT_ALIGNMENT);
        productPrice.setFont(new Font("Arial", Font.PLAIN, 18));

        JLabel productDescription = new JLabel(String.format("Description: %s", selectedProduct.getDescription()));
        productDescription.setAlignmentX(Component.LEFT_ALIGNMENT);
        productDescription.setFont(new Font("Arial", Font.PLAIN, 18));

        JLabel productStock = new JLabel(String.format("Quantity Available: %d", selectedProduct.getQuantity()));
        productStock.setAlignmentX(Component.LEFT_ALIGNMENT);
        productStock.setFont(new Font("Arial", Font.PLAIN, 18));

        JButton goToStoreButton = new JButton("View Product's Store");
        goToStoreButton.setAlignmentX(Component.LEFT_ALIGNMENT);
        goToStoreButton.setFont(new Font("Arial", Font.PLAIN, 18));
        goToStoreButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                JPanel panel = createStorePanel(selectedProduct.getStore());
                cardPanel.add(panel, "Individual Store");
                cardLayout.show(cardPanel, "Individual Store");

            }
        });

        productDetails.add(productName);
        productDetails.add(Box.createVerticalStrut(20));
        productDetails.add(goToStoreButton);
        productDetails.add(Box.createVerticalStrut(20));
        productDetails.add(productPrice);
        productDetails.add(Box.createVerticalStrut(20));
        productDetails.add(productDescription);
        productDetails.add(Box.createVerticalStrut(20));
        productDetails.add(productStock);
        productDetails.add(Box.createVerticalStrut(20));

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        JButton addToCartButton = new JButton("Add to Cart");
        addToCartButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        addToCartButton.setFont(new Font("Arial", Font.PLAIN, 18));
        addToCartButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                Customer customer = new Request().getCustomer(userEmail);

                ShoppingCart customerShoppingCart = customer.getShoppingCart();
                for (Product product : customerShoppingCart.getProductList()) {

                    if (product.equals(selectedProduct)) {

                        JOptionPane.showMessageDialog(null, selectedProduct.getName() + " is already in your shopping cart.", "Shopping Cart", JOptionPane.ERROR_MESSAGE);
                        return;

                    }

                }

                customer.getShoppingCart().getProductList().add(selectedProduct);

                JOptionPane.showMessageDialog(null, selectedProduct.getName() + " successfully added to your shopping cart.", "Shopping Cart", JOptionPane.INFORMATION_MESSAGE);

                new Request().updateCustomer(customer);

            }
        });

        buttonPanel.add(addToCartButton);

        productPanel.add(Box.createVerticalStrut(20));
        productPanel.add(topPanel);
        productPanel.add(new JSeparator(JSeparator.HORIZONTAL));
        productPanel.add(Box.createVerticalStrut(20));
        productPanel.add(productDetails);
        productPanel.add(Box.createVerticalStrut(10));
        productPanel.add(buttonPanel);
        return productPanel;
    }

    private JPanel createStorePanel(Store store) {
        Seller seller = new Request().getSeller(store.getSellerOwner().getEmail());

        Store updatedStore = store;

        for (Store store1 : seller.getSellerStores()) {

            if (store1.equals(store)) {

                updatedStore = store1;
              
            }

        }

        frame.setSize(400,500);
        frame.setLocationRelativeTo(null);

        JPanel storePanel = new JPanel();
        storePanel.setLayout(new BoxLayout(storePanel, BoxLayout.Y_AXIS));

        JPanel topPanel = new JPanel();
        topPanel.setPreferredSize(new Dimension(400,50));
        topPanel.setMaximumSize(new Dimension(400,50));
        topPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        JLabel titleLabel = new JLabel("Store Page");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));

        topPanel.add(createBackToMenuButton());
        topPanel.add(Box.createHorizontalStrut(20));
        topPanel.add(titleLabel);
        topPanel.add(Box.createHorizontalStrut(50));

        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));

        JLabel storeName = new JLabel(updatedStore.getName());
        storeName.setFont(new Font("Arial", Font.BOLD, 18));
        storeName.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel helperPanel = new JPanel();
        helperPanel.setLayout(new BorderLayout());

        JPanel storeProducts = new JPanel();
        storeProducts.setLayout(new BoxLayout(storeProducts, BoxLayout.Y_AXIS));

        for (Product product : updatedStore.getProducts()) {

            JLabel productLine = new JLabel(String.format("Product: %s | $%.2f", product.getName(), product.getPrice()));
            productLine.setAlignmentX(Component.CENTER_ALIGNMENT);
            productLine.setFont(new Font("Arial", Font.PLAIN, 15));
            productLine.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {

                    selectedProduct = product;
                    cardPanel.add(createProductPanel(), "Product Page");
                    cardLayout.show(cardPanel, "Product Page");

                }
            });

            storeProducts.add(productLine);
            storeProducts.add(Box.createVerticalStrut(1));
            storeProducts.add(new JSeparator(JSeparator.HORIZONTAL));

        }

        if (!updatedStore.getProducts().isEmpty()) {
            storeProducts.remove(storeProducts.getComponentCount() - 1);
        }

        helperPanel.add(storeProducts, BorderLayout.NORTH);

        JScrollPane jsp = new JScrollPane(helperPanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        jsp.setAlignmentX(Component.CENTER_ALIGNMENT);
        jsp.setPreferredSize(new Dimension(300, 250));
        jsp.setMaximumSize(new Dimension(300,250));
        jsp.setMinimumSize(new Dimension(300,250));

        centerPanel.add(storeName);
        centerPanel.add(Box.createVerticalStrut(5));
        centerPanel.add(jsp);

        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        JLabel instructionLabel = new JLabel("Click a product to view its page.");
        instructionLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        instructionLabel.setFont(new Font("Arial", Font.PLAIN, 18));

        bottomPanel.add(instructionLabel);

        storePanel.add(Box.createVerticalStrut(25));
        storePanel.add(topPanel);
        storePanel.add(new JSeparator(JSeparator.HORIZONTAL));
        storePanel.add(Box.createVerticalStrut(10));
        storePanel.add(centerPanel);
        storePanel.add(Box.createVerticalStrut(10));
        storePanel.add(bottomPanel);
        storePanel.add(Box.createVerticalStrut(25));

        return storePanel;

    }

    private Component createBackToProductListingsButton() {

        JButton backButton = new JButton("<");
        Font largeFont = new Font("Arial", Font.PLAIN, 18);
        backButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        backButton.setFont(largeFont);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "Product Listings");
                frame.setSize(400, 500);
                frame.setLocationRelativeTo(null);
            }
        });
        return backButton;
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

        storeNamesButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ArrayList<Store> stores = getAllStores();
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
                cardLayout.show(cardPanel, "Search Store Input");
                frame.setSize(450, 300);
                frame.setLocationRelativeTo(null);
            }
        });

        JButton productDescriptionsButton = new JButton("Product Description");
        productDescriptionsButton.setMaximumSize(buttonDimension);
        productDescriptionsButton.setMinimumSize(buttonDimension);
        productDescriptionsButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        productDescriptionsButton.setFont(new Font("Arial", Font.PLAIN, 18));
        productDescriptionsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                ArrayList<Product> products = getAllProducts();

//                if (products.isEmpty()) {
//                    JOptionPane.showMessageDialog(null, "There are no currently no products.", "Customers", JOptionPane.ERROR_MESSAGE);
//
//                }
//                else {
//                    cardLayout.show(cardPanel, "Search Product Description Input");
//                    frame.setSize(400, 300);
//                    frame.setLocationRelativeTo(null);
//                }
                cardLayout.show(cardPanel, "Search Product Description Input");
                frame.setSize(400, 300);
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

    private JPanel createShoppingCartPanel() {
        frame.setSize(400, 300);
        frame.setLocationRelativeTo(null);
        String customerUsername = customerAccount.getUsername();
      
        JPanel shoppingCartPanel = new JPanel();
        shoppingCartPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;

        // Customer Name Label


        JButton backButton = createBackToMenuButton();

        JLabel customerNameLabel = new JLabel(customerUsername +"'s Shopping Cart");
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

        // Retrieve customer shopping cart

        Customer customer = new Request().getCustomer(userEmail);
        ShoppingCart customerShoppingCart = customer.getShoppingCart();

        // Create a JPanel to hold the cart items
        JPanel cartItemsPanel = new JPanel();
        cartItemsPanel.setPreferredSize(new Dimension(350, 100));
        cartItemsPanel.setMaximumSize(new Dimension(350, 100));
        cartItemsPanel.setLayout(new BoxLayout(cartItemsPanel, BoxLayout.Y_AXIS));

        JPanel helperPanel = new JPanel();
        helperPanel.setLayout(new BorderLayout());

        // Headers
        JPanel headersPanel = new JPanel();
        headersPanel.setAlignmentY(Component.TOP_ALIGNMENT);
        headersPanel.setLayout(new GridLayout(1, 5, 10, 5)); // 1 row, 5 columns
        headersPanel.add(new JLabel("Store"));
        headersPanel.add(new JLabel("Product"));
        headersPanel.add(new JLabel("Quantity"));
        headersPanel.add(new JLabel("Price"));
        headersPanel.add(new JLabel("Delete"));

        helperPanel.add(headersPanel, BorderLayout.NORTH);

        cartItemsPanel.add(helperPanel);

        // Populate cart with shopping cart items

        for (Product product : customerShoppingCart.getProductList()) {

            JPanel rowPanel = new JPanel();
            rowPanel.setLayout(new GridLayout(1, 5, 10, 5)); // 1 row, 5 columns
            JLabel storeLabel = new JLabel(product.getStore().getName());
            JLabel productLabel = new JLabel(product.getName());
            JLabel priceLabel = new JLabel(String.valueOf(product.getPrice()));

            SpinnerModel quantityModel = new SpinnerNumberModel(1, 0, Integer.MAX_VALUE, 1);
            JSpinner quantitySpinner = new JSpinner(quantityModel);
            Dimension spinnerSize = new Dimension(60, 20);
            quantitySpinner.setMaximumSize(spinnerSize);
            quantitySpinner.setMinimumSize(spinnerSize);
            JSpinner.DefaultEditor editor = (JSpinner.DefaultEditor) quantitySpinner.getEditor();
            editor.getTextField().setEditable(false);

            JButton deleteButton = new JButton("Delete");
            deleteButton.addActionListener(e -> {
                int deleteConfirmation = JOptionPane.showConfirmDialog(shoppingCartPanel,
                        "Are you sure you want to delete this item?\n\n" +
                                "Store: " + storeLabel.getText() + "\n" +
                                "Product: " + productLabel.getText(),
                        "Confirm Deletion", JOptionPane.YES_NO_OPTION);

                if (deleteConfirmation == JOptionPane.YES_OPTION) {
                    // User confirmed deletion, remove all components from the cartItemsPanel

                    cartItemsPanel.remove(rowPanel);
                    customerShoppingCart.getProductList().remove(product);
                    new Request().updateCustomer(customer);
                    cartItemsPanel.revalidate();
                    cartItemsPanel.repaint();

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
        gbc.weighty = 10;
        gbc.gridy = 0;
        shoppingCartPanel.add(titlePanel, gbc);
        gbc.gridy = 1;
        shoppingCartPanel.add(itemsLabel, gbc);
        gbc.gridy = 2;
        shoppingCartPanel.add(cartItemsPanel, gbc);
        gbc.gridy = 3;
        shoppingCartPanel.add(checkoutButton, gbc);

        return shoppingCartPanel;
    }

    private JPanel searchStoreNamePanel(){
        JPanel searchedStoreNameInput = new JPanel();
        searchedStoreNameInput.setBackground(customColor);
        searchedStoreNameInput.setLayout(new BoxLayout(searchedStoreNameInput, BoxLayout.Y_AXIS));
        searchedStoreNameInput.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel title = new JLabel("Enter Store Name.");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        title.setFont(new Font("Arial", Font.BOLD, 18));

        JButton backToMenuButton = createBackToMenuButton();
        JButton accountPageButton = createBackToAccountPageButton();

        JPanel titlePanel = new JPanel();
        titlePanel.setOpaque(false);
        titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.X_AXIS));
        titlePanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        Dimension titlePanelDimension = new Dimension(400, 50);
        titlePanel.setMinimumSize(titlePanelDimension);
        titlePanel.setMaximumSize(titlePanelDimension);

        titlePanel.add(Box.createHorizontalStrut(20));
        titlePanel.add(backToMenuButton);
        titlePanel.add(Box.createHorizontalStrut(30));
        titlePanel.add(title);
        titlePanel.add(Box.createHorizontalStrut(20));
        titlePanel.add(accountPageButton);
        titlePanel.add(Box.createHorizontalStrut(20));

        JTextField search = new JTextField();
        search.setAlignmentX(Component.CENTER_ALIGNMENT);
        search.setHorizontalAlignment(JTextField.CENTER); // Center-align the text horizontally
        Dimension searchDimension = new Dimension(350, 150);
        search.setMinimumSize(searchDimension);
        search.setMaximumSize(searchDimension);
        search.setBackground(greyButtonColor);
        search.setForeground(Color.BLACK);
        search.setBorder(customBorder);
        search.setFont(new Font("Arial", Font.PLAIN, 24));

        JButton searchButton = new JButton("Search");
        searchButton.setMinimumSize(new Dimension(100, 30));
        searchButton.setMaximumSize(new Dimension(100, 30));
        searchButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        searchButton.setFont(new Font("Arial", Font.PLAIN, 18));
        searchButton.setBackground(greyButtonColor);
        searchButton.setForeground(Color.BLACK);
        searchButton.setBorder(customBorder);

        searchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                boolean matchFound = false;
                ArrayList<Store> stores = getAllStores();
                for (Store store : stores) {
                    if (store.getName().equalsIgnoreCase(search.getText())) {
                        matchFound = true;
                    }

                }
                if(matchFound){

                    searchInputStoreName = search.getText();
                    JPanel panel = createSearchStoresPanel(searchInputStoreName);
                    cardPanel.add(panel, "Search Results for Store Name");
                    cardLayout.show(cardPanel, "Search Results for Store Name");
                    frame.setSize(400, 500);
                    frame.setLocationRelativeTo(null);

                }
                else{
                    JOptionPane.showMessageDialog(null, "There are no matching stores", "Customers", JOptionPane.ERROR_MESSAGE);

                }

                search.setText("");

            }
        });

        searchedStoreNameInput.add(Box.createVerticalStrut(20));
        searchedStoreNameInput.add(titlePanel);
        searchedStoreNameInput.add(Box.createVerticalStrut(20));
        searchedStoreNameInput.add(search);
        searchedStoreNameInput.add(Box.createVerticalStrut(20));
        searchedStoreNameInput.add(searchButton);
        searchedStoreNameInput.add(Box.createVerticalStrut(20));


        return searchedStoreNameInput;

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
                    if (product.getName().contains(search.getText())) {
                        matchFound = true;
                    }
                }

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

                /*
                searchInputProductName = search.getText();
                JPanel panel = createSearchProductsPanel(searchInputProductName);
                cardPanel.add(panel, "Search Results for Product Name");
                cardLayout.show(cardPanel, "Search Results for Product Name");
                frame.setSize(400, 500);
                frame.setLocationRelativeTo(null);

                 */

                search.setText("");
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

    private JPanel createSearchStoresPanel(String storeName) {
        JPanel searchedStoresPanel = new JPanel();
        searchedStoresPanel.setBackground(customColor);
        searchedStoresPanel.setLayout(new BoxLayout(searchedStoresPanel, BoxLayout.Y_AXIS));
        searchedStoresPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel titlePanel = new JPanel();
        titlePanel.setOpaque(false);
        titlePanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.X_AXIS));
        Dimension titlePanelDimension = new Dimension(400, 50);
        titlePanel.setMinimumSize(titlePanelDimension);
        titlePanel.setMaximumSize(titlePanelDimension);

        JLabel title = new JLabel("Searched Stores");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        title.setFont(new Font("Arial", Font.BOLD, 18));
        title.setMinimumSize(new Dimension(150,50));
        title.setMaximumSize(new Dimension(150,50));


        titlePanel.add(Box.createHorizontalStrut(20));
        titlePanel.add(createBackToMenuButton());
        titlePanel.add(Box.createHorizontalStrut(20));
        titlePanel.add(title);
        titlePanel.add(Box.createHorizontalStrut(20));
        titlePanel.add(createBackToAccountPageButton());
        titlePanel.add(Box.createHorizontalStrut(20));

        JPanel allStoresPanel = new JPanel();
        allStoresPanel.setOpaque(false);
        allStoresPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        allStoresPanel.setLayout(new BoxLayout(allStoresPanel, BoxLayout.Y_AXIS));

        ArrayList<Store> allStores = getAllStores();


        for (Store store : allStores) {
            if (store.getName().contains(storeName)) {

                JLabel label = new JLabel(store.getName());
                label.setAlignmentX(Component.CENTER_ALIGNMENT);
                label.setFont(new Font("Arial", Font.PLAIN, 18));

                label.addMouseListener(new MouseAdapter() {
                    public void mouseClicked(MouseEvent e) {
                        //code to go to store page
                        searchStoreName = label.getText();
                        JPanel panel = createIndividualStoreListingsPanel(store);
                        cardPanel.add(panel, "Store Page");
                        cardLayout.show(cardPanel, "Store Page");

                    }
                });
                allStoresPanel.add(label);
                allStoresPanel.add(new JSeparator(JSeparator.HORIZONTAL));
            }
        }

        if (!allStores.isEmpty()) {
            allStoresPanel.remove(allStoresPanel.getComponentCount() - 1);
        }




        JScrollPane jsp = new JScrollPane(allStoresPanel, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jsp.setAlignmentX(Component.CENTER_ALIGNMENT);
        jsp.setMaximumSize(new Dimension(350, 300));
        jsp.setMinimumSize(new Dimension(350, 300));
        jsp.getViewport().setBackground(greyButtonColor);
        jsp.setBorder(customBorder);

        JPanel bottomPanel = new JPanel();
        bottomPanel.setOpaque(false);
        bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.Y_AXIS));
        bottomPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        bottomPanel.setMaximumSize(new Dimension(400, 50));
        bottomPanel.setMinimumSize(new Dimension(400, 50));

        JLabel instructions = new JLabel("Select a Store to view its page");
        instructions.setAlignmentX(Component.CENTER_ALIGNMENT);
        instructions.setFont(new Font("Arial", Font.PLAIN, 18));

        bottomPanel.add(Box.createHorizontalStrut(20));
        bottomPanel.add(instructions);
        bottomPanel.add(Box.createHorizontalStrut(20));

        searchedStoresPanel.add(Box.createVerticalStrut(20));
        searchedStoresPanel.add(titlePanel);
        searchedStoresPanel.add(Box.createVerticalStrut(20));
        searchedStoresPanel.add(jsp);
        searchedStoresPanel.add(Box.createVerticalStrut(20));
        searchedStoresPanel.add(bottomPanel);
        searchedStoresPanel.add(Box.createVerticalStrut(20));

        return searchedStoresPanel;

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
            if (product.getName().contains(productName)) {
                JLabel label = new JLabel(product.getName());
                label.setAlignmentX(Component.CENTER_ALIGNMENT);
                label.setFont(new Font("Arial", Font.PLAIN, 18));

                label.addMouseListener(new MouseAdapter() {
                    public void mouseClicked(MouseEvent e) {
                        JPanel panel = createProductPanel();
                        cardPanel.add(panel, "Product Page");
                        cardLayout.show(cardPanel, "Product Page");
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

        jsp.setAlignmentX(Component.CENTER_ALIGNMENT);
        jsp.setMaximumSize(new Dimension(350, 300));
        jsp.setMinimumSize(new Dimension(350, 300));
        jsp.getViewport().setBackground(greyButtonColor);
        jsp.setBorder(customBorder);



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
                if (searchDescription.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please enter your search words", "Customers", JOptionPane.ERROR_MESSAGE);
                }

                else if (!searchDescription.getText().isEmpty()) {


                    for (Product product : products) {
                        if (product.getDescription().contains(searchDescription.getText())) {
                            matchFound = true;
                            break;
                        }
                    }


                    if (matchFound) {


                        JPanel panel = createSearchProductDescriptionsPanel(searchDescription.getText());
                        cardPanel.add(panel, "Search Results Product Description");
                        cardLayout.show(cardPanel, "Search Results Product Description");
                        frame.setSize(500, 500);
                        frame.setLocationRelativeTo(null);
                    } else {
                        JOptionPane.showMessageDialog(null, "There are no matching products", "Customers", JOptionPane.ERROR_MESSAGE);
                    }
                    searchDescription.setText("");


                }

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

        JLabel title = new JLabel("Searched Products by Description");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        title.setFont(new Font("Arial", Font.BOLD, 18));

        titlePanel.add(createBackToMenuButton());
        titlePanel.add(title);
        titlePanel.add(createBackToAccountPageButton());

        JPanel allProductsPanel = new JPanel();
        allProductsPanel.setLayout(new BoxLayout(allProductsPanel, BoxLayout.Y_AXIS));

        ArrayList<Product> allProducts = getAllProducts();

        for (Product product: allProducts) {

            if (product.getDescription().contains(productDescription)) {

                JLabel label = new JLabel(product.getName());
                label.setAlignmentX(Component.CENTER_ALIGNMENT);
                label.setFont(new Font("Arial", Font.PLAIN, 18));


                label.addMouseListener(new MouseAdapter() {
                    public void mouseClicked(MouseEvent e) {
                        JPanel panel = createProductPanel(product);
                        cardPanel.add(panel, "Product Page");
                        cardLayout.show(cardPanel, "Product Page");
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

        jsp.setMaximumSize(new Dimension(350, 300));
        jsp.setMinimumSize(new Dimension(350, 300));
        jsp.getViewport().setBackground(greyButtonColor);
        jsp.setBorder(customBorder);

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


    private JLabel selectedSellerLabel;

    private JButton createBackToMenuButton() {
        JButton backButton = new JButton("<");
        Font largeFont = new Font("Arial", Font.PLAIN, 18);
        backButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        backButton.setFont(largeFont);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardPanel.removeAll();
                cardPanel.add(createMainPanel(), "Main Page");
                cardLayout.show(cardPanel, "Main Page");
            }
        });
        return backButton;
    }


    private JButton createBackToAccountPageButton() {
        JButton accountButton = new JButton("Account");
        accountButton.setFont(new Font("Arial", Font.PLAIN, 15));
        accountButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        accountButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardPanel.add(createAccountPanel(), "Account Options");
                cardLayout.show(cardPanel, "Account Options");
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
    private static JPanel createFieldWithLabel(String labelText, JComponent field) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
        panel.setOpaque(false);
        JLabel label = new JLabel(labelText);
        label.setFont(new Font("Arial", Font.PLAIN, 18));
        label.setPreferredSize(new Dimension(100, 20));
        panel.add(label);
        panel.add(field);

        return panel;
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

    private static ArrayList<Product> sortPriceHighToLow(ArrayList<Product> products) {
        Product temp;
        for (int i = 0; i < products.size(); i++) {
            for (int j = i; j < products.size(); j++) {
                if (products.get(i).getPrice() < products.get(j).getPrice()) {
                    temp = products.get(i);
                    products.set(i, products.get(j));
                    products.set(j, temp);

                }
            }
        }
        return products;
    }

    private static ArrayList<Product> sortPriceLowToHigh(ArrayList<Product> products) {
        Product temp;
        for (int i = 0; i < products.size(); i++) {
            for (int j = i; j < products.size(); j++) {
                if (products.get(i).getPrice() > products.get(j).getPrice()) {
                    temp = products.get(i);
                    products.set(i, products.get(j));
                    products.set(j, temp);

                }
            }
        }
        return products;
    }
    private static ArrayList<Product> sortQuantityHighToLow (ArrayList<Product> products) {
        Product temp;
        for (int i = 0; i < products.size(); i++) {
            for (int j = i; j < products.size(); j++) {
                if (products.get(i).getQuantity() < products.get(j).getQuantity()) {
                    temp = products.get(i);
                    products.set(i, products.get(j));
                    products.set(j, temp);

                }
            }
        }
        return products;
    }
    private static ArrayList<Product> sortQuantityLowToHigh (ArrayList<Product> products) {
        Product temp;
        for (int i = 0; i < products.size(); i++) {
            for (int j = i; j < products.size(); j++) {
                if (products.get(i).getQuantity() > products.get(j).getQuantity()) {
                    temp = products.get(i);
                    products.set(i, products.get(j));
                    products.set(j, temp);

                }
            }
        }
        return products;
    }


}