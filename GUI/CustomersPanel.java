import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.time.Year;
import java.util.*;
import java.util.List;

public class CustomersPanel {

    String userEmail;
    Account customerAccount;

    private JFrame frame;
    private JPanel cardPanel;
    private JPanel productPanel;
    private JPanel storeListings;
    private JPanel sellerListings;
    private JTextArea selectedMessage;

    Boolean fromProductListings = false;
    Boolean fromStoreListings = false;
    Boolean fromSellerListings = false;

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

    Color customColor = new Color(206, 184, 136);
    Color greyButtonColor = new Color(196, 191, 192);
    Border customBorder = BorderFactory.createLineBorder(Color.BLACK, 3);


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
//        cardPanel.add(searchProductDescriptionPanel(), "Search Product Description Input");;
//

        frame.add(cardPanel);

        cardLayout.show(cardPanel, "Main Page");
        frame.setLocation(800,250);
        frame.setVisible(true);

    }

    //method that creates the panel for the main page
    private JPanel createMainPanel() {
        frame.setSize(400,500);
        frame.setLocation(800,250);
        frame.setResizable(false);
        JPanel mainPagePanel = new JPanel();
        mainPagePanel.setBackground(customColor);
        mainPagePanel.setLayout(new BoxLayout(mainPagePanel, BoxLayout.Y_AXIS));
        //mainPagePanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        ImageIcon imageIcon = new ImageIcon("images/boilerBayLogo.png");
        JLabel imageLabel = new JLabel();
        imageLabel.setMinimumSize(new Dimension(100, 100));
        imageLabel.setMaximumSize(new Dimension(100, 100));
        imageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        imageLabel.setIcon(new ImageIcon(imageIcon.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT)));

        JButton switchToAccountPanelButton = createBackToAccountPageButton();
        switchToAccountPanelButton.setBackground(greyButtonColor);
        switchToAccountPanelButton.setForeground(Color.BLACK);
        switchToAccountPanelButton.setBorder(customBorder);
        switchToAccountPanelButton.setFont(new Font("Arial", Font.PLAIN, 16));
        switchToAccountPanelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.setSize(400, 300);
                cardPanel.add(createAccountPanel(), "Account Options");
                cardLayout.show(cardPanel, "Account Options");

            }
        });

        JPanel topPanel = new JPanel();
        topPanel.setOpaque(false);
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.X_AXIS));
        topPanel.setAlignmentY(Component.CENTER_ALIGNMENT);

        topPanel.add(Box.createHorizontalStrut(120));
        topPanel.add(imageLabel);
        topPanel.add(Box.createHorizontalStrut(50));
        topPanel.add(switchToAccountPanelButton);
        topPanel.add(Box.createHorizontalStrut(10));

        JButton searchButton = new JButton("Search");
        searchButton.setBackground(greyButtonColor);
        searchButton.setForeground(Color.BLACK);
        searchButton.setBorder(customBorder);
        searchButton.setPreferredSize(new Dimension(300, 50));
        searchButton.setFont(new Font("Times New Roman", Font.PLAIN, 30));
        searchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                cardPanel.add(createSearchOptionsPanel(), "Search Options");
                cardLayout.show(cardPanel, "Search Options");
                frame.setSize(400,300);
            }
        });


        //JTextField searchField = new JTextField()
        //searchField.setPreferredSize(new Dimension(300, 30));

        JPanel searchPanel = new JPanel(new FlowLayout());
        searchPanel.setOpaque(false);
        Dimension searchPanelDimension = new Dimension(400, 40);
        searchPanel.setPreferredSize(searchPanelDimension);
        //searchPanel.add(searchField);
        searchPanel.add(searchButton);

        Dimension buttonDimension = new Dimension(100,100);
        JButton seeAllProductsButton = new JButton("Products");
        seeAllProductsButton.setPreferredSize(buttonDimension);
        seeAllProductsButton.setBackground(greyButtonColor);
        seeAllProductsButton.setForeground(Color.BLACK);
        seeAllProductsButton.setBorder(customBorder);
        //seeAllProductsButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        seeAllProductsButton.setFont(new Font("Arial", Font.PLAIN, 16));
        seeAllProductsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.setSize(400, 500);
                cardPanel.add(createProductListingsPanel(getAllProducts()), "Product Listings");
                cardLayout.show(cardPanel, "Product Listings");

            }
        });

        JButton seeAllStoresButton = new JButton("Stores");
        seeAllStoresButton.setBackground(greyButtonColor);
        seeAllStoresButton.setForeground(Color.BLACK);
        seeAllStoresButton.setBorder(customBorder);
        seeAllStoresButton.setPreferredSize(buttonDimension);
        //seeAllStoresButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        seeAllStoresButton.setFont(new Font("Arial", Font.PLAIN, 16));
        seeAllStoresButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardPanel.add(createStoreListingsPagePanel(getAllStores()), "Store Listings");
                cardLayout.show(cardPanel, "Store Listings");
            }
        });

        JButton seeAllSellersButton = new JButton("Sellers");
        seeAllSellersButton.setPreferredSize(buttonDimension);
        seeAllSellersButton.setBackground(greyButtonColor);
        seeAllSellersButton.setForeground(Color.BLACK);
        seeAllSellersButton.setBorder(customBorder);
        //seeAllSellersButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        seeAllSellersButton.setFont(new Font("Arial", Font.PLAIN, 16));
        seeAllSellersButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardPanel.removeAll();
                cardPanel.add(createSellerListingSortPanel(), "Seller Listing Sort");
                cardLayout.show(cardPanel, "Seller Listing Sort");

            }
        });

        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.setOpaque(false);
        Dimension buttonPanelDimension = new Dimension(400, 80);
        buttonPanel.setPreferredSize(buttonPanelDimension);
        buttonPanel.add(seeAllProductsButton);
        buttonPanel.add(seeAllStoresButton);
        buttonPanel.add(seeAllSellersButton);

        
        ImageIcon imageIconBottom = new ImageIcon("images/boilerTrain.png");
        JLabel imageBottomLabel = new JLabel();
        imageBottomLabel.setMinimumSize(new Dimension(100, 100));
        imageBottomLabel.setMaximumSize(new Dimension(100, 100));
        imageBottomLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        imageBottomLabel.setIcon(new ImageIcon(imageIconBottom.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT)));

        JPanel bestSellersPanel = new JPanel();
        bestSellersPanel.setOpaque(false);
        bestSellersPanel.setLayout(new BoxLayout(bestSellersPanel, BoxLayout.Y_AXIS));
        bestSellersPanel.add(imageBottomLabel);


        mainPagePanel.add(Box.createVerticalStrut(10));
        mainPagePanel.add(topPanel);
        mainPagePanel.add(searchPanel);
        mainPagePanel.add(Box.createVerticalStrut(10));
        mainPagePanel.add(buttonPanel);

        mainPagePanel.add(Box.createVerticalStrut(20));
        mainPagePanel.add(bestSellersPanel);

        mainPagePanel.add(Box.createVerticalStrut(20));

        return mainPagePanel;
    }

    //method that creates the panel for the account page
    private JPanel createAccountPanel() {
        frame.setSize(400, 500);

        JPanel createAccountPanel = new JPanel();
        createAccountPanel.setBackground(customColor);
        createAccountPanel.setLayout(new BoxLayout(createAccountPanel, BoxLayout.Y_AXIS));

        String customerUsername = customerAccount.getUsername();
        JLabel titleLabel = new JLabel(String.format("%s's Account", customerUsername));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));


        JPanel topPanel = new JPanel();
        topPanel.setOpaque(false);
        Dimension topPanelDimension = new Dimension(400, 50);
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.X_AXIS));
        topPanel.setMaximumSize(topPanelDimension);
        topPanel.setMaximumSize(topPanelDimension);
        topPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        topPanel.add(Box.createHorizontalStrut(20));
        topPanel.add(createBackToMenuButton());
        topPanel.add(Box.createHorizontalStrut(20));
        topPanel.add(titleLabel);
        topPanel.add(Box.createHorizontalStrut(20));
        //topPanel.add(logOutButton);

        Dimension buttonDimension = new Dimension(250, 50);
        JButton shoppingCartButton = new JButton(("View Shopping Cart"));
        shoppingCartButton.setMaximumSize(buttonDimension);
        shoppingCartButton.setMinimumSize(buttonDimension);
        shoppingCartButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        shoppingCartButton.setMaximumSize(buttonDimension);
        shoppingCartButton.setMinimumSize(buttonDimension);
        shoppingCartButton.setBackground(greyButtonColor);
        shoppingCartButton.setForeground(Color.BLACK);
        shoppingCartButton.setBorder(customBorder);
        shoppingCartButton.setFont(new Font("Arial", Font.PLAIN, 18));

        shoppingCartButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                cardPanel.add(createShoppingCartPanel(), "Shopping Cart");
                cardLayout.show(cardPanel, "Shopping Cart");
            }
        });

        JButton purchaseHistoryButton = new JButton(("My Purchase History"));
        purchaseHistoryButton.setMaximumSize(buttonDimension);
        purchaseHistoryButton.setMinimumSize(buttonDimension);
        purchaseHistoryButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        purchaseHistoryButton.setFont(new Font("Arial", Font.PLAIN, 18));
        purchaseHistoryButton.setBackground(greyButtonColor);
        purchaseHistoryButton.setForeground(Color.BLACK);
        purchaseHistoryButton.setBorder(customBorder);
        purchaseHistoryButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {


                cardPanel.add(createPastPurchasesPanel(), "Past Purchases");
                cardLayout.show(cardPanel, "Past Purchases");
                frame.setSize(400, 500);
            }
        });


        JButton logOutButton = new JButton(("Log Out"));
        logOutButton.setBackground(greyButtonColor);
        logOutButton.setBorder(customBorder);
        logOutButton.setPreferredSize(buttonDimension);
        logOutButton.setMaximumSize(buttonDimension);
        logOutButton.setMinimumSize(buttonDimension);
        logOutButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        logOutButton.setBackground(greyButtonColor);
        logOutButton.setForeground(Color.BLACK);
        logOutButton.setBorder(customBorder);
        logOutButton.setFont(new Font("Arial", Font.PLAIN, 18));
        logOutButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardPanel.removeAll();
                cardPanel.add(createLogOutPanel(), "Log Out");
                cardLayout.show(cardPanel, "Log Out");
                frame.setSize(400,200);
            }
        });

        createAccountPanel.add(Box.createVerticalStrut(20));
        createAccountPanel.add(topPanel);
        createAccountPanel.add(Box.createVerticalStrut(20));
        createAccountPanel.add(shoppingCartButton);
        createAccountPanel.add(Box.createVerticalStrut(25));
        createAccountPanel.add(purchaseHistoryButton);
        createAccountPanel.add(Box.createVerticalStrut(25));
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
    private JPanel createSellerListingsPanel(ArrayList<Seller> sellers) {
        frame.setSize(400,500);

        JPanel sellerListingsPanel = new JPanel(); // Main Panel
        sellerListingsPanel.setBackground(customColor);
        sellerListingsPanel.setLayout(new BoxLayout(sellerListingsPanel, BoxLayout.Y_AXIS));

        JPanel topPanel = new JPanel(); // Top panel to hold title and buttons
        topPanel.setOpaque(false);
        topPanel.setMinimumSize(new Dimension(400, 50));
        topPanel.setMaximumSize(new Dimension(400, 50));
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.X_AXIS));
        topPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel titleLabel = new JLabel("All Sellers"); // Panel title
        titleLabel.setMinimumSize(new Dimension(150, 50));
        titleLabel.setMaximumSize(new Dimension(150, 50));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));

        // Creating buttons
        JButton backToMenuButton = createBackToMenuButton();
        JButton accountPageButton = createBackToAccountPageButton();

        topPanel.add(Box.createHorizontalStrut(20));
        topPanel.add(backToMenuButton);
        topPanel.add(Box.createHorizontalStrut(60));
        topPanel.add(titleLabel);
        topPanel.add(Box.createHorizontalStrut(20));
        topPanel.add(accountPageButton);
        topPanel.add(Box.createHorizontalStrut(20));

        JSeparator topSep = new JSeparator(JSeparator.HORIZONTAL);
        topSep.setForeground(Color.BLACK);
        topSep.setBackground(Color.BLACK);

        sellerListingsPanel.add(Box.createVerticalStrut(20));
        sellerListingsPanel.add(topPanel);
        sellerListingsPanel.add(Box.createVerticalStrut(15));
        sellerListingsPanel.add(topSep);


        String [] dropdownOptions = new String[]{"Products Sold High to Low", "Products Sold Low to High"};
        JComboBox sortByDropdown = new JComboBox<>(dropdownOptions);
        sortByDropdown.setBackground(greyButtonColor);
        sortByDropdown.setBorder(customBorder);
        sortByDropdown.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String selection = sortByDropdown.getSelectedItem().toString();

                ArrayList<Seller> sortedSellers = new ArrayList<>();

                switch (selection) {

                    case "Products Sold High to Low" -> {

                        sortedSellers = Seller.sortByTotalProductsSold(sellers, "high");

                    }
                    case "Products Sold Low to High" -> {

                        sortedSellers = Seller.sortByTotalProductsSold(sellers, "low");

                    }

                }

                sellerListings.removeAll();

                for (Seller seller : sellers) {

                    JLabel sellerLabel = new JLabel(String.format("Seller: %s", seller.getUsername()));
                    sellerLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
                    sellerLabel.setFont(new Font("Arial", Font.PLAIN, 15));
                    sellerLabel.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(MouseEvent e) {

                            fromSellerListings = true;
                            cardPanel.add(createSellerPanel(seller), "Seller Page");
                            cardLayout.show(cardPanel, "Seller Page");

                        }
                    });

                    JSeparator newSep = new JSeparator(JSeparator.HORIZONTAL);
                    newSep.setForeground(Color.BLACK);
                    newSep.setBackground(Color.BLACK);

                    sellerListings.add(sellerLabel);
                    sellerListings.add(newSep);
                }

                if(!sellers.isEmpty()){
                    sellerListings.remove(sellerListings.getComponentCount() - 1);
                }

                sellerListings.revalidate();
                sellerListings.repaint();

            }
        });

        JLabel sortLabel = new JLabel("Sort by:");
        sortLabel.setFont(new Font("Arial", Font.BOLD, 18));

        JPanel dropDownPanel = new JPanel();
        dropDownPanel.setOpaque(false);
        dropDownPanel.setPreferredSize(new Dimension(400, 40));
        dropDownPanel.setMaximumSize(new Dimension(400,40));
        dropDownPanel.add(sortLabel);
        dropDownPanel.add(sortByDropdown);

        sellerListingsPanel.add(dropDownPanel);

        JPanel helperPanel = new JPanel();
        helperPanel.setBackground(greyButtonColor);
        helperPanel.setLayout(new BorderLayout());

        sellerListings = new JPanel();
        sellerListings.setLayout(new BoxLayout(sellerListings, BoxLayout.Y_AXIS));

        for (Seller seller : sellers) {

            JLabel sellerLabel = new JLabel(String.format("Seller: %s", seller.getUsername()));
            sellerLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            sellerLabel.setFont(new Font("Arial", Font.PLAIN, 15));
            sellerLabel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {

                    fromSellerListings = true;
                    cardPanel.add(createSellerPanel(seller), "Seller Page");
                    cardLayout.show(cardPanel, "Seller Page");

                }
            });

            JSeparator sep = new JSeparator(JSeparator.HORIZONTAL);
            sep.setBackground(Color.BLACK);
            sep.setForeground(Color.BLACK);

            sellerListings.add(sellerLabel);
            sellerListings.add(sep);
        }


        if(!sellers.isEmpty()){
            sellerListings.remove(sellerListings.getComponentCount() - 1);
        }

        helperPanel.add(sellerListings, BorderLayout.NORTH);

        JScrollPane jsp = new JScrollPane(helperPanel, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jsp.setPreferredSize(new Dimension(325,275));
        jsp.setMinimumSize(new Dimension(325, 275));
        jsp.setMaximumSize(new Dimension(325, 275));
        jsp.getViewport().setBackground(greyButtonColor);
        jsp.setBorder(customBorder);

        sellerListingsPanel.add(Box.createVerticalStrut(10));
        sellerListingsPanel.add(jsp);

        JLabel instructionLabel = new JLabel("Click a seller to view their page.");
        instructionLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        instructionLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        Dimension instructionDimension = new Dimension(275, 50);
        instructionLabel.setMinimumSize(instructionDimension);
        instructionLabel.setMaximumSize(instructionDimension);

        sellerListingsPanel.add(Box.createVerticalStrut(10));
        sellerListingsPanel.add(instructionLabel);
        sellerListingsPanel.add(Box.createVerticalStrut(20));

        return sellerListingsPanel;
    }


    // method that creates the panel for the product listings page
    private JPanel createProductListingsPanel(ArrayList<Product> products) { //not entirely sure how to implement the refresh feature based on what is selected in the dropdow
        frame.setSize(400,500);

        fromProductListings = true;

        JPanel productsListingPanel = new JPanel();
        productsListingPanel.setBackground(customColor);
        productsListingPanel.setLayout(new BoxLayout(productsListingPanel, BoxLayout.Y_AXIS));

        JLabel titleLabel = new JLabel("Products"); //not completely centered
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        titleLabel.setMinimumSize(new Dimension(150, 50));
        titleLabel.setMaximumSize(new Dimension(150, 50));
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));

        JPanel titlePanel = new JPanel();
        titlePanel.setOpaque(false);
        titlePanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        titlePanel.setMinimumSize(new Dimension(400, 50));
        titlePanel.setMaximumSize(new Dimension(400, 50));
        titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.X_AXIS));


        titlePanel.add(Box.createHorizontalStrut(20));
        titlePanel.add(createBackToMenuButton());
        titlePanel.add(Box.createHorizontalStrut(70));
        titlePanel.add(titleLabel);
        titlePanel.add(Box.createHorizontalStrut(30));
        titlePanel.add(createBackToAccountPageButton());
        titlePanel.add(Box.createHorizontalStrut(20));

        String [] dropdownOptions = new String[]{"Price High To Low", "Price Low to High", "Quantity High to Low", "Quantity Low to High"};
        JComboBox sortByDropdown = new JComboBox<>(dropdownOptions);
        sortByDropdown.setMinimumSize(new Dimension(100, 50));
        sortByDropdown.setMaximumSize(new Dimension(100, 50));
        sortByDropdown.setBackground(greyButtonColor);
        sortByDropdown.setBorder(customBorder);
        sortByDropdown.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String selection = sortByDropdown.getSelectedItem().toString();

                ArrayList<Product> sortedProducts = new ArrayList<>();

                switch (selection) {

                    case "Price High To Low" -> {

                        sortedProducts = Product.sortByPrice(products, "high");

                    }
                    case "Price Low to High" -> {

                        sortedProducts = Product.sortByPrice(products, "low");

                    }
                    case "Quantity High to Low" -> {

                        sortedProducts = Product.sortByQuantity(products, "high");

                    }
                    case "Quantity Low to High" -> {

                        sortedProducts = Product.sortByQuantity(products, "low");

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

                if (!products.isEmpty()) {
                    productPanel.remove(productPanel.getComponentCount() - 1);
                }

                productPanel.revalidate();
                productPanel.repaint();

            }
        });


        JPanel dropDownPanel = new JPanel();
        dropDownPanel.setOpaque(false);
        dropDownPanel.setPreferredSize(new Dimension(400, 40));
        dropDownPanel.setMaximumSize(new Dimension(400,40));
        dropDownPanel.add(sortByDropdown);

        JPanel helperPanel = new JPanel();
        helperPanel.setLayout(new BoxLayout(helperPanel, BoxLayout.Y_AXIS));
        helperPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        helperPanel.setBackground(greyButtonColor);

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

            JSeparator sep = new JSeparator(JSeparator.HORIZONTAL);
            sep.setForeground(Color.BLACK);

            productPanel.add(productLine);
            productPanel.add(Box.createVerticalStrut(1));
            productPanel.add(sep);
        }

        helperPanel.add(productPanel);

        JScrollPane jsp = new JScrollPane(helperPanel, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jsp.setPreferredSize(new Dimension(325, 300));
        jsp.setMaximumSize(new Dimension(325,300));
        jsp.setBorder(customBorder);

        if (!products.isEmpty()) {
            productPanel.remove(productPanel.getComponentCount() - 1);
        }

        JSeparator newSep = new JSeparator(JSeparator.HORIZONTAL);
        newSep.setForeground(Color.BLACK);
        newSep.setBackground(Color.BLACK);
        newSep.setBorder(customBorder);

        JLabel instructionLabel = new JLabel("Click a product to view its page.");
        instructionLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        instructionLabel.setFont(new Font("Arial", Font.PLAIN, 18));

        productsListingPanel.add(Box.createVerticalStrut(20));
        productsListingPanel.add(titlePanel);
        productsListingPanel.add(Box.createVerticalStrut(15));
        productsListingPanel.add(newSep);
        productsListingPanel.add(Box.createVerticalStrut(10));
        productsListingPanel.add(dropDownPanel);
        productsListingPanel.add(Box.createVerticalStrut(5));
        productsListingPanel.add(jsp);
        productsListingPanel.add(Box.createVerticalStrut(20));
        productsListingPanel.add(instructionLabel);
        productsListingPanel.add(Box.createVerticalStrut(20));

        return productsListingPanel;
    }



    // method that creates panel that lists all created stores
    private JPanel createStoreListingsPagePanel(ArrayList<Store> sellerStores) {
        frame.setSize(400,500);

        fromStoreListings = true;

        JPanel storeListingsPanel = new JPanel(); // Main Panel
        storeListingsPanel.setLayout(new BoxLayout(storeListingsPanel, BoxLayout.Y_AXIS));
        storeListingsPanel.setBackground(customColor);

        JPanel topPanel = new JPanel(); // Top panel to hold title and buttons
        topPanel.setOpaque(false);
        topPanel.setPreferredSize(new Dimension(400, 50));
        topPanel.setMaximumSize(new Dimension(400, 50));
        topPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        topPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel titleLabel = new JLabel("Stores"); // Panel title
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));

        // Creating buttons
        JButton backToMenuButton = createBackToMenuButton();
        JButton accountPageButton = createBackToAccountPageButton();

        topPanel.add(backToMenuButton);
        topPanel.add(Box.createHorizontalStrut(60));
        topPanel.add(titleLabel);
        topPanel.add(Box.createHorizontalStrut(30));
        topPanel.add(accountPageButton);

        storeListingsPanel.add(Box.createVerticalStrut(20));
        storeListingsPanel.add(topPanel);
        JSeparator sep = new JSeparator(JSeparator.HORIZONTAL);
        sep.setForeground(Color.BLACK);
        sep.setBackground(Color.BLACK);
        storeListingsPanel.add(sep);


        String [] dropdownOptions = new String[]{"Most Products Sold", "Least Products Sold"};
        JComboBox sortByDropdown = new JComboBox<>(dropdownOptions);
        sortByDropdown.setBorder(customBorder);
        sortByDropdown.setBackground(greyButtonColor);
        sortByDropdown.setBorder(customBorder);
        sortByDropdown.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String selection = sortByDropdown.getSelectedItem().toString();

                ArrayList<Store> sortedStores = new ArrayList<>();

                switch (selection) {

                    case "Most Products Sold" -> {

                        sortedStores = Store.sortByProductsSold(sellerStores, "high");

                    }
                    case "Least Products Sold" -> {

                        sortedStores = Store.sortByProductsSold(sellerStores, "low");

                    }

                }

                storeListings.removeAll();

                for (Store store : sortedStores) {

                    JLabel storeLabel = new JLabel(String.format("Store: %s | Seller: %s", store.getName(), store.getSellerOwner().getUsername()));
                    storeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
                    storeLabel.setFont(new Font("Arial", Font.PLAIN, 15));

                    storeLabel.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(MouseEvent e) {

                            cardPanel.add(createStorePanel(store), "Store Page");
                            cardLayout.show(cardPanel, "Store Page");

                        }
                    });

                    storeListings.add(storeLabel);
                    storeListings.add(Box.createVerticalStrut(1));
                    storeListings.add(new JSeparator(JSeparator.HORIZONTAL));

                }

                if(!sortedStores.isEmpty()){
                    storeListings.remove(storeListings.getComponentCount() - 1);
                }

                storeListings.revalidate();
                storeListings.repaint();

            }
        });

        JPanel dropDownPanel = new JPanel();
        dropDownPanel.setOpaque(false);
        dropDownPanel.setPreferredSize(new Dimension(400, 40));
        dropDownPanel.setMaximumSize(new Dimension(400,40));
        dropDownPanel.add(sortByDropdown);

        storeListingsPanel.add(dropDownPanel);

        JPanel helperPanel = new JPanel();
        helperPanel.setLayout(new BorderLayout());

        storeListings = new JPanel();
        storeListings.setLayout(new BoxLayout(storeListings, BoxLayout.Y_AXIS));

        for (Store store : sellerStores) {

            JLabel storeLabel = new JLabel(String.format("Store: %s | Seller: %s", store.getName(), store.getSellerOwner().getUsername()));
            storeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            storeLabel.setFont(new Font("Arial", Font.PLAIN, 15));
            storeLabel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {

                    cardPanel.add(createStorePanel(store), "Store Page");
                    cardLayout.show(cardPanel, "Store Page");

                }
            });

            storeListings.add(storeLabel);
            storeListings.add(new JSeparator(JSeparator.HORIZONTAL));

        }


        if(!sellerStores.isEmpty()){
            storeListings.remove(storeListings.getComponentCount() - 1);
        }

        helperPanel.add(storeListings, BorderLayout.NORTH);

        JScrollPane jsp = new JScrollPane(helperPanel, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jsp.setPreferredSize(new Dimension(325,275));
        jsp.setMinimumSize(new Dimension(325, 275));
        jsp.setMaximumSize(new Dimension(325, 275));
        jsp.getViewport().setBackground(greyButtonColor);
        jsp.setBorder(customBorder);

        storeListingsPanel.add(Box.createVerticalStrut(10));
        storeListingsPanel.add(jsp);

        JLabel instructionLabel = new JLabel("Click a store to view its page.");
        instructionLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        instructionLabel.setFont(new Font("Arial", Font.PLAIN, 18));

        storeListingsPanel.add(Box.createVerticalStrut(10));
        storeListingsPanel.add(instructionLabel);
        storeListingsPanel.add(Box.createVerticalStrut(20));

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
                cardPanel.removeAll();
                cardPanel.add(createAccountPanel(), "Account Options");
                cardLayout.show(cardPanel, "Account Options");
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

        frame.setSize(400,200);

        JPanel sellerListingSortPanel = new JPanel();
        sellerListingSortPanel.setBackground(customColor);

        sellerListingSortPanel.setLayout(new BoxLayout(sellerListingSortPanel, BoxLayout.Y_AXIS));

        JLabel questionLabel = new JLabel("What would you like to do?");
        questionLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        questionLabel.setFont(new Font("Arial", Font.BOLD, 18));

        Dimension buttonDimension = new Dimension(300, 40);
        JButton sellerListingButton = new JButton("Show All Sellers");
        sellerListingButton.setBackground(greyButtonColor);
        sellerListingButton.setBorder(customBorder);
        sellerListingButton.setPreferredSize(buttonDimension);
        sellerListingButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        sellerListingButton.setFont(new Font("Arial", Font.PLAIN, 18));
        sellerListingButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                cardPanel.add(createSellerListingsPanel(new Request().getAllSellers()), "Seller Listings");
                cardLayout.show(cardPanel, "Seller Listings");
                frame.setSize(400,500);
            }
        });

        JButton contactSellersButton = new JButton("Contact Sellers");
        contactSellersButton.setBackground(greyButtonColor);
        contactSellersButton.setBorder(customBorder);
        contactSellersButton.setPreferredSize(buttonDimension);
        contactSellersButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        contactSellersButton.setFont(new Font("Arial", Font.PLAIN, 18));
        contactSellersButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardPanel.add(createContactSellerPanel(), "Contact Sellers");
                cardLayout.show(cardPanel, "Contact Sellers");
                frame.setSize(400,500);
                frame.setLocation(800,250);
            }
        });
        JPanel topPanel = new JPanel();
        topPanel.setOpaque(false);
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.X_AXIS));
        topPanel.add(createBackToMenuButton());
        topPanel.add(Box.createHorizontalStrut(25));
        topPanel.add(questionLabel);
        topPanel.add(Box.createHorizontalStrut(60));

        sellerListingSortPanel.add(Box.createVerticalStrut(10));
        sellerListingSortPanel.add(topPanel);
        sellerListingSortPanel.add(Box.createVerticalStrut(5));
        sellerListingSortPanel.add(sellerListingButton);
        sellerListingSortPanel.add(Box.createVerticalStrut(5));
        sellerListingSortPanel.add(contactSellersButton);
        sellerListingSortPanel.add(Box.createVerticalStrut(20));
        return sellerListingSortPanel;
    }

    private Seller recievingSeller;  // Variable to store the selected seller's name

    private JPanel createContactSellerPanel() {
        recievingSeller = null;
        frame.setSize(400, 500);  // Set the frame size accordingly

        JPanel contactSellerPanel = new JPanel();
        contactSellerPanel.setBackground(customColor);
        contactSellerPanel.setLayout(new BoxLayout(contactSellerPanel, BoxLayout.Y_AXIS));

        // Back button
        JButton backButton = createBackToSellersButton();

        // title label
        JLabel titleLabel = new JLabel("Message a Seller", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));

        // inbox button
        JButton messagesButton = new JButton("Messages");
        messagesButton.setBorder(customBorder);
        messagesButton.setBackground(greyButtonColor);
        //messagesButton.setFont(new Font("Arial", Font.BOLD, 10));
        messagesButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardPanel.removeAll();
                cardPanel.add(createCustomerMessagesPanel(), "View Customer Messages");
                cardLayout.show(cardPanel, "View Customer Messages");
            }
        });

        JPanel titlePanel = new JPanel();
        titlePanel.setOpaque(false);
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



        JPanel sellerNamePanel = new JPanel();
        sellerNamePanel.setOpaque(false);
        ArrayList<Seller> allSellers = new Request().getAllSellers();
        sellerNamePanel.setLayout(new BoxLayout(sellerNamePanel, BoxLayout.Y_AXIS));
        for (Seller seller : allSellers) {
            JLabel label = new JLabel(seller.getUsername());
            label.setAlignmentX(Component.CENTER_ALIGNMENT);
            label.setFont(new Font("Arial", Font.PLAIN, 18));

            label.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    // Handle click event
                    recievingSeller = seller;
                    // Update the display with the selected seller's name
                    selectedSellerLabel.setText("Selected Seller: " + recievingSeller.getUsername());

                }
            });

            sellerNamePanel.add(label);
            sellerNamePanel.add(Box.createVerticalStrut(1));
            sellerNamePanel.add(new JSeparator(JSeparator.HORIZONTAL));
        }

        JPanel helperPanel = new JPanel();
        helperPanel.setLayout(new BorderLayout());
        helperPanel.add(sellerNamePanel, BorderLayout.NORTH);

        // Remove the last separator to avoid an extra line at the end
        if (!allSellers.isEmpty()) {sellerNamePanel.remove(sellerNamePanel.getComponentCount() - 1);}

        JScrollPane jsp = new JScrollPane(helperPanel, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jsp.setBorder(customBorder);
        jsp.getViewport().setBackground(greyButtonColor);
        // Display selected seller's name
        jsp.setPreferredSize(new Dimension(250, 300));
        jsp.setMaximumSize(new Dimension(250,300));
        selectedSellerLabel = new JLabel("Selected Seller: ");
        selectedSellerLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        selectedSellerLabel.setFont(new Font("Arial", Font.PLAIN, 16));

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
        messageTextArea.setAlignmentX(Component.CENTER_ALIGNMENT);
        messageTextArea.setLineWrap(true);
        messageTextArea.setWrapStyleWord(true);
        messageTextArea.setFont(new Font("Arial", Font.PLAIN, 16));


        JScrollPane messageScrollPane = new JScrollPane(messageTextArea);
        messageScrollPane.setBorder(customBorder);
        messageScrollPane.setPreferredSize(new Dimension(250, 100));
        messageScrollPane.setMaximumSize(new Dimension(250, 100));

        // Button to send the message
        JButton sendMessageButton = new JButton("Send Message");
        sendMessageButton.setBorder(customBorder);
        sendMessageButton.setBackground(greyButtonColor);
        sendMessageButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        sendMessageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle sending the message
                String message = messageTextArea.getText();

                if (recievingSeller == null ) {
                    JOptionPane.showMessageDialog(null, "Error! Please select a customer before sending a message.");
                } else if (messageTextArea.getText().equals("")){
                    JOptionPane.showMessageDialog(null, "Error! Please write a message before sending to a customer.");
                } else {
                    // testing purposes
                    String messageSent = messageTextArea.getText();

                    Account senderAccount = new Request().getAccount(userEmail);

                    Account recievingAccount = recievingSeller.getAccount();

                    boolean response = new Request().updateMessages(senderAccount, recievingAccount, messageSent);

                    if (response) {
                        JOptionPane.showMessageDialog(null, "Message successfully sent to " + recievingSeller.getUsername() + ".", "Messaging", JOptionPane.INFORMATION_MESSAGE);
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
        contactSellerPanel.add(selectedSellerLabel);
        contactSellerPanel.add(Box.createVerticalStrut(10));
        contactSellerPanel.add(messagePanel);
        contactSellerPanel.add(Box.createVerticalStrut(20));

        return contactSellerPanel;

    }

    private JButton createBackToSellersButton() {
        JButton backButton = new JButton("<");
        backButton.setBackground(greyButtonColor);
        backButton.setBorder(customBorder);
        Font largeFont = new Font("Arial", Font.PLAIN, 18);
        backButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        backButton.setFont(largeFont);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardPanel.removeAll();
                cardPanel.add(createSellerListingSortPanel(), "Seller Sort");
                cardLayout.show(cardPanel, "Seller Sort");
            }
        });
        return backButton;
    }

    private JPanel createCustomerMessagesPanel() {

        frame.setSize(400, 500);
        JPanel customerMessagesPanel = new JPanel();
        customerMessagesPanel.setLayout(new BoxLayout(customerMessagesPanel, BoxLayout.Y_AXIS));

        JButton backToContactCustomersButton = createBackToContactSellersButton();
        backToContactCustomersButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        backToContactCustomersButton.setFont(new Font("Arial", Font.PLAIN, 18));

        JLabel customerMessagesLabel = new JLabel("Customer Messages");
        customerMessagesLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        customerMessagesLabel.setFont(new Font("Arial", Font.BOLD, 24));

        Dimension topDimension = new Dimension(400, 75);
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.X_AXIS));
        topPanel.setMinimumSize(topDimension);
        topPanel.setMaximumSize(topDimension);
        topPanel.add(Box.createHorizontalStrut(20));
        topPanel.add(backToContactCustomersButton);
        topPanel.add(Box.createHorizontalStrut(40));
        topPanel.add(customerMessagesLabel);
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
                    selectedMessage.setText(messageParts[3]);
                    label.setFont(new Font("Arial", Font.PLAIN, 18));

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

        JPanel fullMessagePanel = new JPanel(new FlowLayout(FlowLayout.CENTER)); // Use FlowLayout with left alignment

        selectedMessage = new JTextArea("Please select a Message to Expand");
        selectedMessage.setLineWrap(true);
        selectedMessage.setWrapStyleWord(true);
        selectedMessage.setEditable(false); // Prevent editing in JTextArea
        selectedMessage.setAlignmentX(Component.CENTER_ALIGNMENT);
        selectedMessage.setFont(new Font("Arial", Font.PLAIN, 18));

        JScrollPane messageScrollPane = new JScrollPane(selectedMessage, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        messageScrollPane.setPreferredSize(new Dimension(350, 100));
        fullMessagePanel.add(messageScrollPane);

        customerMessagesPanel.add(Box.createVerticalStrut(20));
        customerMessagesPanel.add(topPanel);
        customerMessagesPanel.add(Box.createVerticalStrut(20));
        customerMessagesPanel.add(jsp);
        customerMessagesPanel.add(Box.createVerticalStrut(20));
        customerMessagesPanel.add(fullMessagePanel);
        customerMessagesPanel.add(Box.createVerticalStrut(20));

        return customerMessagesPanel;

    }

    private JButton createBackToContactSellersButton() {
        JButton backButton = new JButton("<");
        Font largeFont = new Font("Arial", Font.PLAIN, 18);
        backButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        backButton.setFont(largeFont);

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardPanel.add(createContactSellerPanel(), "Contact Sellers");
                cardLayout.show(cardPanel, "Contact Sellers");
            }
        });
        return backButton;
    }

    private JPanel createPastPurchasesPanel() {
        frame.setSize(400, 500);

        JPanel pastPurchasesPanel = new JPanel();
        pastPurchasesPanel.setLayout(new BoxLayout(pastPurchasesPanel, BoxLayout.Y_AXIS));
        JLabel titleLabel = new JLabel("Purchase History"); //not completely centered
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
//
        JPanel titlePanel = new JPanel();
        titlePanel.setPreferredSize(new Dimension(400, 50));
        titlePanel.setMaximumSize(new Dimension(400, 50));
        titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.X_AXIS));
        titlePanel.add(Box.createHorizontalStrut(20));
        titlePanel.add(createBackToAccountPageButton());
        titlePanel.add(Box.createHorizontalGlue());
        titlePanel.add(titleLabel);
        titlePanel.add(Box.createHorizontalStrut(50));
        titlePanel.add(Box.createHorizontalGlue());

        JPanel helperPanel = new JPanel();
        helperPanel.setLayout(new BorderLayout());

        JPanel pastPurchases = new JPanel();
        pastPurchases.setLayout((new BoxLayout(pastPurchases, BoxLayout.Y_AXIS)));

        ArrayList<PurchasedProduct> purchasedProducts = new Request().getCustomer(userEmail).getPreviouslyPurchasedProducts();

        for (PurchasedProduct purchasedProduct : purchasedProducts) {

            JLabel historyLine = new JLabel(String.format("%d %s at $%.2f", purchasedProduct.getAmountPurchased(), purchasedProduct.getPurchasedProduct().getName(), purchasedProduct.getPurchasedProduct().getPrice()));
            historyLine.setAlignmentX(Component.CENTER_ALIGNMENT);
            historyLine.setFont(new Font("Arial", Font.PLAIN, 15));

            pastPurchases.add(historyLine);
            pastPurchases.add(Box.createVerticalStrut(1));
            pastPurchases.add(new JSeparator(JSeparator.HORIZONTAL));

        }

        helperPanel.add(pastPurchases, BorderLayout.NORTH);

        JScrollPane jsp = new JScrollPane(helperPanel, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jsp.setPreferredSize(new Dimension(300, 300));
        jsp.setMaximumSize(new Dimension(300, 300));


        if (!purchasedProducts.isEmpty()) {
            pastPurchases.remove(pastPurchases.getComponentCount() - 1);
        }

        JButton exportButton = new JButton("Export to CSV");
        exportButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        exportButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int choice = JOptionPane.showConfirmDialog(null, "Do you want to export purchase history to a CSV file?", "Export History", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

                if (choice == JOptionPane.YES_OPTION) {

                    String fileName = JOptionPane.showInputDialog(null, "Enter the name of the file you'd like to create", "Export History", JOptionPane.INFORMATION_MESSAGE);

                    try (PrintWriter pw = new PrintWriter(new FileWriter(fileName))){

                        for (PurchasedProduct purchasedProduct : purchasedProducts) {

                            String productName = purchasedProduct.getPurchasedProduct().getName();;

                            double productPrice = purchasedProduct.getPurchasedProduct().getPrice();

                            int quantityPurchased = purchasedProduct.getAmountPurchased();

                            String productDescription = purchasedProduct.getPurchasedProduct().getDescription();

                            pw.println(String.format("%s,%.2f,%d,%s", productName, productPrice, quantityPurchased, productDescription));

                        }

                        JOptionPane.showMessageDialog(null, "Purchase history successfully exported", "Export History", JOptionPane.INFORMATION_MESSAGE);

                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }

                } else {
                    return;
                }

            }
        });

        pastPurchasesPanel.add(Box.createVerticalStrut(20));
        pastPurchasesPanel.add(titlePanel);
        pastPurchasesPanel.add(new JSeparator(JSeparator.HORIZONTAL));
        pastPurchasesPanel.add(Box.createVerticalStrut(10));
        pastPurchasesPanel.add(jsp);
        pastPurchasesPanel.add(Box.createVerticalStrut(10));
        pastPurchasesPanel.add(exportButton);
        pastPurchasesPanel.add(Box.createVerticalStrut(50));
        return pastPurchasesPanel;
    }

    private JPanel createSellerPanel(Seller seller) { //should have an object parameter that takes a Seller object
        Seller updatedSeller = new Request().getSeller(seller.getEmail());

        frame.setSize(400,500);

        JPanel sellerPanel = new JPanel();
        sellerPanel.setLayout(new BoxLayout(sellerPanel, BoxLayout.Y_AXIS));

        JPanel topPanel = new JPanel();
        topPanel.setPreferredSize(new Dimension(400,50));
        topPanel.setMaximumSize(new Dimension(400,50));
        topPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        JLabel titleLabel = new JLabel("Seller Page");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));

        topPanel.add(createBackToSellerListingsButton());
        topPanel.add(Box.createHorizontalStrut(50));
        topPanel.add(titleLabel);
        topPanel.add(Box.createHorizontalStrut(80));

        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));

        JPanel sellerDetails = new JPanel();
        sellerDetails.setLayout(new BoxLayout(sellerDetails, BoxLayout.Y_AXIS));
        sellerDetails.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel sellerName = new JLabel(updatedSeller.getUsername());
        sellerName.setFont(new Font("Arial", Font.BOLD, 18));
        sellerName.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel totalProductsSold = new JLabel(String.format("Total Products Sold: %s", updatedSeller.getTotalProductsSold()));
        totalProductsSold.setFont(new Font("Arial", Font.BOLD, 12));
        totalProductsSold.setAlignmentX(Component.CENTER_ALIGNMENT);

        sellerDetails.add(sellerName);
        sellerDetails.add(totalProductsSold);
        sellerDetails.add(Box.createVerticalStrut(10));

        centerPanel.add(sellerDetails);

        JPanel helperPanel = new JPanel();
        helperPanel.setLayout(new BorderLayout());

        JPanel sellerStores = new JPanel();
        sellerStores.setLayout(new BoxLayout(sellerStores, BoxLayout.Y_AXIS));

        for (Store store : updatedSeller.getSellerStores()) {

            JLabel storeLine = new JLabel(String.format("%s", store.getName()));
            storeLine.setAlignmentX(Component.CENTER_ALIGNMENT);
            storeLine.setFont(new Font("Arial", Font.PLAIN, 15));
            storeLine.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {

                    cardPanel.add(createStorePanel(store), "Store Page");
                    cardLayout.show(cardPanel, "Store Page");

                }
            });

            sellerStores.add(storeLine);
            sellerStores.add(Box.createVerticalStrut(1));
            sellerStores.add(new JSeparator(JSeparator.HORIZONTAL));

        }

        if (!updatedSeller.getSellerStores().isEmpty()) {
            sellerStores.remove(sellerStores.getComponentCount() - 1);
        }

        helperPanel.add(sellerStores, BorderLayout.NORTH);

        JScrollPane jsp = new JScrollPane(helperPanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        jsp.setAlignmentX(Component.CENTER_ALIGNMENT);
        jsp.setPreferredSize(new Dimension(300, 250));
        jsp.setMaximumSize(new Dimension(300,250));
        jsp.setMinimumSize(new Dimension(300,250));

        centerPanel.add(Box.createVerticalStrut(5));
        centerPanel.add(jsp);

        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        JLabel instructionLabel = new JLabel("Click a store to view its page.");
        instructionLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        instructionLabel.setFont(new Font("Arial", Font.PLAIN, 18));

        bottomPanel.add(instructionLabel);

        sellerPanel.add(Box.createVerticalStrut(25));
        sellerPanel.add(topPanel);
        sellerPanel.add(new JSeparator(JSeparator.HORIZONTAL));
        sellerPanel.add(Box.createVerticalStrut(10));
        sellerPanel.add(centerPanel);
        sellerPanel.add(Box.createVerticalStrut(10));
        sellerPanel.add(bottomPanel);
        sellerPanel.add(Box.createVerticalStrut(25));

        return sellerPanel;

    }

    private JButton createBackToSellerListingsButton() {

        JButton backButton = new JButton("<");
        Font largeFont = new Font("Arial", Font.PLAIN, 18);
        backButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        backButton.setFont(largeFont);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (fromSellerListings) {
                    cardPanel.removeAll();
                    cardPanel.add(createSellerListingsPanel(new Request().getAllSellers()), "Seller Listings");
                    cardLayout.show(cardPanel, "Seller Listings");
                } else {
                    cardPanel.removeAll();
                    cardPanel.add(createMainPanel(), "Main Page");
                    cardLayout.show(cardPanel, "Main Page");
                }
            }
        });
        return backButton;

    }

    private JPanel createProductPanel() {
        frame.setSize(400,500);
        frame.setLocation(800,250);

        JPanel productPanel = new JPanel();
        productPanel.setBackground(customColor);
        productPanel.setLayout(new BoxLayout(productPanel, BoxLayout.Y_AXIS));

        JPanel topPanel = new JPanel();
        topPanel.setOpaque(false);
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
        productDetails.setOpaque(false);
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
        goToStoreButton.setBackground(greyButtonColor);
        goToStoreButton.setBorder(customBorder);
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
        buttonPanel.setOpaque(false);
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        JButton addToCartButton = new JButton("Add to Cart");
        addToCartButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        addToCartButton.setBackground(greyButtonColor);
        addToCartButton.setBorder(customBorder);
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
        JSeparator sep = new JSeparator(JSeparator.HORIZONTAL);
        sep.setForeground(Color.BLACK);
        sep.setBackground(Color.BLACK);
        productPanel.add(sep);
        productPanel.add(Box.createVerticalStrut(20));
        productPanel.add(productDetails);
        productPanel.add(Box.createVerticalStrut(10));
        productPanel.add(buttonPanel);
        return productPanel;
    }

    private JPanel createStorePanel(Store store) {
        Seller seller = new Request().getSeller(store.getSellerOwner().getEmail());

        Store updatedStore = seller.getStore(store);

        frame.setSize(400,500);

        JPanel storePanel = new JPanel();
        storePanel.setLayout(new BoxLayout(storePanel, BoxLayout.Y_AXIS));
        storePanel.setBackground(customColor);

        JPanel topPanel = new JPanel();
        topPanel.setOpaque(false);
        topPanel.setPreferredSize(new Dimension(400,50));
        topPanel.setMaximumSize(new Dimension(400,50));
        topPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        JLabel titleLabel = new JLabel("Store Page");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));

        topPanel.add(createBackToStoreListingsButton());
        topPanel.add(Box.createHorizontalStrut(50));
        topPanel.add(titleLabel);
        topPanel.add(Box.createHorizontalStrut(80));

        JPanel centerPanel = new JPanel();
        centerPanel.setOpaque(false);
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));

        JPanel storeDetails = new JPanel();
        storeDetails.setOpaque(false);
        storeDetails.setLayout(new BoxLayout(storeDetails, BoxLayout.Y_AXIS));
        storeDetails.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel storeName = new JLabel(updatedStore.getName());
        storeName.setFont(new Font("Arial", Font.BOLD, 18));
        storeName.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel sellerName = new JLabel(String.format("Seller: %s", updatedStore.getSellerOwner().getUsername()));
        sellerName.setFont(new Font("Arial", Font.BOLD, 12));
        sellerName.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel storeProductsSold = new JLabel(String.format("Products Sold: %s", updatedStore.getProductsSold()));
        storeProductsSold.setFont(new Font("Arial", Font.BOLD, 12));
        storeProductsSold.setAlignmentX(Component.CENTER_ALIGNMENT);

        storeDetails.add(storeName);
        storeDetails.add(sellerName);
        storeDetails.add(storeProductsSold);
        storeDetails.add(Box.createVerticalStrut(10));

        centerPanel.add(storeDetails);

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
        jsp.setBorder(customBorder);
        jsp.setPreferredSize(new Dimension(300, 250));
        jsp.setMaximumSize(new Dimension(300,250));
        jsp.setMinimumSize(new Dimension(300,250));

        centerPanel.add(Box.createVerticalStrut(5));
        centerPanel.add(jsp);

        JPanel bottomPanel = new JPanel();
        bottomPanel.setOpaque(false);
        bottomPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        JLabel instructionLabel = new JLabel("Click a product to view its page.");
        instructionLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        instructionLabel.setFont(new Font("Arial", Font.PLAIN, 18));

        bottomPanel.add(instructionLabel);

        storePanel.add(Box.createVerticalStrut(25));
        storePanel.add(topPanel);
        JSeparator sep = new JSeparator(JSeparator.HORIZONTAL);
        sep.setForeground(Color.black);
        sep.setBackground(Color.black);
        storePanel.add(sep);
        storePanel.add(Box.createVerticalStrut(10));
        storePanel.add(centerPanel);
        storePanel.add(Box.createVerticalStrut(10));
        storePanel.add(bottomPanel);
        storePanel.add(Box.createVerticalStrut(25));

        return storePanel;

    }

    private Component createBackToStoreListingsButton() {

        JButton backButton = new JButton("<");
        backButton.setBackground(greyButtonColor);
        backButton.setBorder(customBorder);
        Font largeFont = new Font("Arial", Font.PLAIN, 18);
        backButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        backButton.setFont(largeFont);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (fromStoreListings) {
                    cardPanel.removeAll();
                    cardPanel.add(createStoreListingsPagePanel(getAllStores()), "Store Listings");
                    cardLayout.show(cardPanel, "Store Listings");
                } else {
                    cardPanel.removeAll();
                    cardPanel.add(createMainPanel(), "Main Page");
                    cardLayout.show(cardPanel, "Main Page");
                }
            }
        });
        return backButton;

    }

    private Component createBackToProductListingsButton() {

        JButton backButton = new JButton("<");
        backButton.setBorder(customBorder);
        backButton.setBackground(greyButtonColor);
        Font largeFont = new Font("Arial", Font.PLAIN, 18);
        backButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        backButton.setFont(largeFont);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (fromProductListings) {
                    cardPanel.removeAll();
                    cardPanel.add(createProductListingsPanel(getAllProducts()), "Product Listings");
                    cardLayout.show(cardPanel, "Product Listings");
                } else {
                    cardPanel.removeAll();
                    cardPanel.add(createMainPanel(), "Main Page");
                    cardLayout.show(cardPanel, "Main Page");
                }
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
        frame.setSize(400,300);

        JPanel searchOptionsPanel = new JPanel();
        searchOptionsPanel.setBackground(customColor);
        searchOptionsPanel.setLayout(new BoxLayout(searchOptionsPanel, BoxLayout.Y_AXIS));
        searchOptionsPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel questionLabel = new JLabel("What would you like to search by?");
        questionLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        questionLabel.setFont(new Font("Arial", Font.BOLD, 18));

        Dimension buttonDimension = new Dimension(225, 40);
        JButton productNamesButton = new JButton("Product");
        productNamesButton.setBorder(customBorder);
        productNamesButton.setBackground(greyButtonColor);
        productNamesButton.setMaximumSize(buttonDimension);
        productNamesButton.setMinimumSize(buttonDimension);
        productNamesButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        productNamesButton.setFont(new Font("Arial", Font.PLAIN, 18));
        productNamesButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardPanel.add(searchProductNamePanel(), "Search Product Input");
                cardLayout.show(cardPanel, "Search Product Input");
                frame.setSize(400, 200);
            }
        });

        JButton storeNamesButton = new JButton("Store");
        storeNamesButton.setBorder(customBorder);
        storeNamesButton.setBackground(greyButtonColor);
        storeNamesButton.setMaximumSize(buttonDimension);
        storeNamesButton.setMinimumSize(buttonDimension);
        storeNamesButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        storeNamesButton.setFont(new Font("Arial", Font.PLAIN, 18));

        storeNamesButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardPanel.add(searchStoreNamePanel(), "Search Store Input");
                cardLayout.show(cardPanel, "Search Store Input");
            }
        });

        JButton productDescriptionsButton = new JButton("Product Description");
        productDescriptionsButton.setMaximumSize(buttonDimension);
        productDescriptionsButton.setBackground(greyButtonColor);
        productDescriptionsButton.setBorder(customBorder);
        productDescriptionsButton.setMinimumSize(buttonDimension);
        productDescriptionsButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        productDescriptionsButton.setFont(new Font("Arial", Font.PLAIN, 18));
        productDescriptionsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                cardPanel.add(searchProductDescriptionPanel(), "Search Product Description Input");
                cardLayout.show(cardPanel, "Search Product Description Input");

            }
        });

        JPanel bottomPanel = new JPanel();
        bottomPanel.setOpaque(false);
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
        frame.setSize(400, 400);
        String customerUsername = customerAccount.getUsername();

        JPanel shoppingCartPanel = new JPanel();
        shoppingCartPanel.setLayout(new BoxLayout(shoppingCartPanel, BoxLayout.Y_AXIS));

        JButton backButton = createBackToAccountPageButton();

        JLabel customerNameLabel = new JLabel(customerUsername +"'s Shopping Cart");
        customerNameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        customerNameLabel.setFont(new Font("Arial", Font.BOLD, 20));

        // Panel for back button and title
        JPanel titlePanel = new JPanel();
        titlePanel.setPreferredSize(new Dimension(400, 50));
        titlePanel.setMaximumSize(new Dimension(400, 50));
        titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.X_AXIS));
        titlePanel.add(Box.createHorizontalStrut(20));
        titlePanel.add(backButton);
        titlePanel.add(Box.createHorizontalGlue()); // Add some spacing
        titlePanel.add(customerNameLabel);
        titlePanel.add(Box.createHorizontalStrut(50));
        titlePanel.add(Box.createHorizontalGlue());

        // Headers
        JPanel headersPanel = new JPanel();
        headersPanel.setPreferredSize(new Dimension(350, 30));
        headersPanel.setMaximumSize(new Dimension(350, 30));
        headersPanel.setAlignmentY(Component.CENTER_ALIGNMENT);
        headersPanel.setLayout(new GridLayout(1, 5, 10, 5)); // 1 row, 5 columns
        headersPanel.add(new JLabel("Store"), JLabel.LEFT_ALIGNMENT);
        headersPanel.add(new JLabel("Product"), JLabel.LEFT_ALIGNMENT);
        headersPanel.add(new JLabel("Price"), JLabel.LEFT_ALIGNMENT);
        headersPanel.add(new JLabel("Quantity"), JLabel.LEFT_ALIGNMENT);
        headersPanel.add(new JLabel("Delete"), JLabel.LEFT_ALIGNMENT);

        // Create a JPanel to hold the cart items
        JPanel cartItemsPanel = new JPanel();
        cartItemsPanel.setLayout(new BoxLayout(cartItemsPanel, BoxLayout.Y_AXIS));

        JPanel helperPanel = new JPanel();
        helperPanel.setLayout(new BorderLayout());

        // Populate cart with shopping cart items

        Customer currentCustomer = new Request().getCustomer(userEmail);

        Customer.updateShoppingCart(currentCustomer);

        ShoppingCart customerShoppingCart = currentCustomer.getShoppingCart();

        HashMap<Product, Integer> potentialCheckOuts = new HashMap<>();

        for (Product product : customerShoppingCart.getProductList()) {

            JPanel rowPanel = new JPanel();
            rowPanel.setPreferredSize(new Dimension(330, 50));
            rowPanel.setMaximumSize(new Dimension(330, 50));
            rowPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
            rowPanel.setLayout(new GridLayout(1, 5, 10, 5)); // 1 row, 5 columns
            JLabel storeLabel = new JLabel(product.getStore().getName());
            JLabel productLabel = new JLabel(product.getName());
            JLabel priceLabel = new JLabel(String.format("$%.2f", product.getPrice()));

            SpinnerModel quantityModel = new SpinnerNumberModel(1, 1, Integer.MAX_VALUE, 1);
            JSpinner quantitySpinner = new JSpinner(quantityModel);
            Dimension spinnerSize = new Dimension(50, 30);
            quantitySpinner.setMaximumSize(spinnerSize);
            quantitySpinner.setMinimumSize(spinnerSize);
            quantitySpinner.setPreferredSize(spinnerSize);
            JSpinner.DefaultEditor editor = (JSpinner.DefaultEditor) quantitySpinner.getEditor();
            editor.getTextField().setEditable(false);
            potentialCheckOuts.put(product, (Integer) quantitySpinner.getValue());
            quantitySpinner.addChangeListener(new ChangeListener() {
                @Override
                public void stateChanged(ChangeEvent e) {
                    potentialCheckOuts.put(product, (Integer) quantitySpinner.getValue());
                }
            });

            JButton deleteButton = new JButton("Delete");
            deleteButton.addActionListener(e -> {
                int deleteConfirmation = JOptionPane.showConfirmDialog(shoppingCartPanel,
                        "Are you sure you want to delete this item?\n\n" +
                                "Store: " + storeLabel.getText() + "\n" +
                                "Product: " + productLabel.getText(),
                        "Confirm Deletion", JOptionPane.YES_NO_OPTION);

                if (deleteConfirmation == JOptionPane.YES_OPTION) {
                    // User confirmed deletion, remove all components from the cartItemsPanel

                    potentialCheckOuts.remove(product);
                    cartItemsPanel.remove(rowPanel);
                    customerShoppingCart.getProductList().remove(product);
                    new Request().updateCustomer(currentCustomer);
                    cartItemsPanel.revalidate();
                    cartItemsPanel.repaint();

                }
            });

            rowPanel.add(storeLabel);
            rowPanel.add(productLabel);
            rowPanel.add(priceLabel);
            rowPanel.add(quantitySpinner);
            rowPanel.add(deleteButton);

            cartItemsPanel.add(rowPanel);

        }

        helperPanel.add(cartItemsPanel, BorderLayout.NORTH);

        JScrollPane jsp = new JScrollPane(helperPanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        jsp.setPreferredSize(new Dimension(350, 200));
        jsp.setMaximumSize(new Dimension(350, 200));


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
                StringBuilder purchaseLines = new StringBuilder();
                HashMap<Product, Integer> definiteCheckouts = new HashMap<>();
                for (Map.Entry<Product, Integer> entry : potentialCheckOuts.entrySet()) {
                    Product product = entry.getKey();
                    Integer amount = entry.getValue();
                    Product updatedProduct = Product.updateProduct(product);
                    if (updatedProduct.getQuantity() < amount) {
                        JOptionPane.showMessageDialog(null, String.format("There are only %d %s in stock", updatedProduct.getQuantity(), updatedProduct.getName()));
                        return;
                    }
                    definiteCheckouts.put(updatedProduct, amount);
                    purchaseLines.append(String.format("%d %s at $%.2f\n", amount, product.getName(), product.getPrice()));
                }
                JOptionPane.showMessageDialog(null, String.format("Checking out %d items...\n%s", potentialCheckOuts.size(), purchaseLines.toString()));

                for (Map.Entry<Product, Integer> entry : definiteCheckouts.entrySet()) {

                    Product product = entry.getKey();

                    product.purchaseProduct(entry.getValue());

                    product.getStore().getPurchasers().add(new Purchaser(currentCustomer.getUsername(), product.getName(), entry.getValue(), entry.getValue() * product.getPrice()));

                    new Request().updateSeller(product.getStore().getSellerOwner());

                    currentCustomer.getPreviouslyPurchasedProducts().add(new PurchasedProduct(product, entry.getValue()));

                }

                customerShoppingCart.getProductList().clear();
                new Request().updateCustomer(currentCustomer);
                cartItemsPanel.removeAll();
                cartItemsPanel.revalidate();
                cartItemsPanel.repaint();

            }
        });

        shoppingCartPanel.add(titlePanel);
        shoppingCartPanel.add(Box.createVerticalStrut(10));
        shoppingCartPanel.add(headersPanel);
        shoppingCartPanel.add(jsp);
        shoppingCartPanel.add(Box.createVerticalStrut(10));
        shoppingCartPanel.add(checkoutButton);
        shoppingCartPanel.add(Box.createVerticalStrut(20));

        return shoppingCartPanel;
    }

    private JPanel searchStoreNamePanel(){

        frame.setSize(400, 200);
        JPanel searchedStoreNamePanel = new JPanel();
        searchedStoreNamePanel.setBackground(customColor);
        searchedStoreNamePanel.setLayout(new BoxLayout(searchedStoreNamePanel, BoxLayout.Y_AXIS));
        searchedStoreNamePanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel topPanel = new JPanel();
        topPanel.setOpaque(false);
        topPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        topPanel.setPreferredSize(new Dimension(400, 40));
        topPanel.setMaximumSize(new Dimension(400, 40));

        JLabel title = new JLabel("Enter a store name");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        title.setFont(new Font("Arial", Font.BOLD, 18));

        topPanel.add(createBackToMenuButton());
        topPanel.add(Box.createVerticalStrut(30));
        topPanel.add(title);
        topPanel.add(Box.createVerticalStrut(50));

        JTextField search = new JTextField();
        search.setAlignmentX(Component.CENTER_ALIGNMENT);
        search.setPreferredSize(new Dimension(300, 50));
        search.setMaximumSize(new Dimension(300, 50));

        JButton searchButton = new JButton("Search");
        searchButton.setBackground(greyButtonColor);
        searchButton.setBorder(customBorder);
        searchButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        searchButton.setFont(new Font("Arial", Font.PLAIN, 18));

        searchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                boolean matchFound = false;
                ArrayList<Product> products = getAllProducts();
                ArrayList<Product> searchedProducts = new ArrayList<>();
                for (Product product : products) {
                    if (product.getStore().getName().toLowerCase().contains(search.getText().toLowerCase())) {
                        matchFound = true;
                        searchedProducts.add(product);
                    }
                }

                if (matchFound) {
                    cardPanel.add(createProductListingsPanel(searchedProducts), "Searched Product Listings");
                    cardLayout.show(cardPanel, "Searched Product Listings");
                    frame.setSize(400, 500);
                    frame.setLocation(800,250);
                } else {
                    JOptionPane.showMessageDialog(null, "There are no matching products", "Customers", JOptionPane.ERROR_MESSAGE);
                }

                search.setText("");
            }
        });

        searchedStoreNamePanel.add(Box.createVerticalStrut(5));
        searchedStoreNamePanel.add(topPanel);
        searchedStoreNamePanel.add(Box.createVerticalStrut(10));
        searchedStoreNamePanel.add(search);
        searchedStoreNamePanel.add(Box.createVerticalStrut(5));
        searchedStoreNamePanel.add(searchButton);
        searchedStoreNamePanel.add(Box.createVerticalStrut(5));
        return searchedStoreNamePanel;

    }
    private JPanel searchProductNamePanel () {
        frame.setSize(400, 200);
        JPanel searchedProductNameInput = new JPanel();
        searchedProductNameInput.setBackground(customColor);
        searchedProductNameInput.setLayout(new BoxLayout(searchedProductNameInput, BoxLayout.Y_AXIS));
        searchedProductNameInput.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel topPanel = new JPanel();
        topPanel.setOpaque(false);
        topPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        topPanel.setPreferredSize(new Dimension(400, 40));
        topPanel.setMaximumSize(new Dimension(400, 40));

        JLabel title = new JLabel("Enter the name of the product");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        title.setFont(new Font("Arial", Font.BOLD, 18));

        topPanel.add(createBackToSearchOptionsButton());
        topPanel.add(Box.createVerticalStrut(30));
        topPanel.add(title);
        topPanel.add(Box.createVerticalStrut(50));

        JTextField search = new JTextField();
        search.setAlignmentX(Component.CENTER_ALIGNMENT);
        search.setPreferredSize(new Dimension(300, 50));
        search.setMaximumSize(new Dimension(300, 50));

        JButton searchButton = new JButton("Search");
        searchButton.setBackground(greyButtonColor);
        searchButton.setBorder(customBorder);
        searchButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        searchButton.setFont(new Font("Arial", Font.PLAIN, 18));

        searchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                boolean matchFound = false;
                ArrayList<Product> products = getAllProducts();
                ArrayList<Product> searchedProducts = new ArrayList<>();
                for (Product product : products) {
                    if (product.getName().toLowerCase().contains(search.getText().toLowerCase())) {
                        matchFound = true;
                        searchedProducts.add(product);
                    }
                }

                if (matchFound) {
                    cardPanel.add(createProductListingsPanel(searchedProducts), "Searched Product Listings");
                    cardLayout.show(cardPanel, "Searched Product Listings");
                    frame.setSize(400, 500);
                    frame.setLocation(800,250);
                } else {
                    JOptionPane.showMessageDialog(null, "There are no matching products", "Customers", JOptionPane.ERROR_MESSAGE);
                }

                search.setText("");
            }
        });

        searchedProductNameInput.add(Box.createVerticalStrut(5));
        searchedProductNameInput.add(topPanel);
        searchedProductNameInput.add(Box.createVerticalStrut(10));
        searchedProductNameInput.add(search);
        searchedProductNameInput.add(Box.createVerticalStrut(5));
        searchedProductNameInput.add(searchButton);
        searchedProductNameInput.add(Box.createVerticalStrut(5));
        return searchedProductNameInput;

    }

    private JButton createBackToSearchOptionsButton() {
        JButton backButton = new JButton("<");
        backButton.setBorder(customBorder);
        backButton.setBackground(greyButtonColor);
        Font largeFont = new Font("Arial", Font.PLAIN, 18);
        backButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        backButton.setFont(largeFont);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardPanel.removeAll();
                cardPanel.add(createSearchOptionsPanel(), "Search Options");
                cardLayout.show(cardPanel, "Search Options");
            }
        });
        return backButton;
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

    private JPanel searchProductDescriptionPanel() {
        frame.setSize(400, 200);
        JPanel searchedProductDescription = new JPanel();
        searchedProductDescription.setBackground(customColor);
        searchedProductDescription.setLayout(new BoxLayout(searchedProductDescription, BoxLayout.Y_AXIS));
        searchedProductDescription.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel topPanel = new JPanel();
        topPanel.setOpaque(false);
        topPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        topPanel.setPreferredSize(new Dimension(400, 40));
        topPanel.setMaximumSize(new Dimension(400, 40));

        JLabel title = new JLabel("Enter a product description");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        title.setFont(new Font("Arial", Font.BOLD, 18));

        topPanel.add(createBackToMenuButton());
        topPanel.add(Box.createVerticalStrut(30));
        topPanel.add(title);
        topPanel.add(Box.createVerticalStrut(50));

        JTextField search = new JTextField();
        search.setAlignmentX(Component.CENTER_ALIGNMENT);
        search.setPreferredSize(new Dimension(300, 50));
        search.setMaximumSize(new Dimension(300, 50));

        JButton searchButton = new JButton("Search");
        searchButton.setBackground(greyButtonColor);
        searchButton.setBorder(customBorder);
        searchButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        searchButton.setFont(new Font("Arial", Font.PLAIN, 18));

        searchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                boolean matchFound = false;
                ArrayList<Product> products = getAllProducts();
                ArrayList<Product> searchedProducts = new ArrayList<>();
                for (Product product : products) {
                    if (product.getDescription().toLowerCase().contains(search.getText().toLowerCase())) {
                        matchFound = true;
                        searchedProducts.add(product);
                    }
                }

                if (matchFound) {
                    cardPanel.add(createProductListingsPanel(searchedProducts), "Searched Product Listings");
                    cardLayout.show(cardPanel, "Searched Product Listings");
                    frame.setSize(400, 500);
                    frame.setLocation(800,250);
                } else {
                    JOptionPane.showMessageDialog(null, "There are no matching products", "Customers", JOptionPane.ERROR_MESSAGE);
                }

                search.setText("");
            }
        });

        searchedProductDescription.add(Box.createVerticalStrut(5));
        searchedProductDescription.add(topPanel);
        searchedProductDescription.add(Box.createVerticalStrut(10));
        searchedProductDescription.add(search);
        searchedProductDescription.add(Box.createVerticalStrut(5));
        searchedProductDescription.add(searchButton);
        searchedProductDescription.add(Box.createVerticalStrut(5));
        return searchedProductDescription;
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
        backButton.setBorder(customBorder);
        backButton.setBackground(greyButtonColor);
        backButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        backButton.setFont(largeFont);
        backButton.setBackground(greyButtonColor);
        backButton.setForeground(Color.BLACK);
        backButton.setBorder(customBorder);
        Dimension backDimension = new Dimension(60, 30);
        backButton.setMinimumSize(backDimension);
        backButton.setMaximumSize(backDimension);


        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fromProductListings = false;
                fromStoreListings = false;
                cardPanel.add(createMainPanel(), "Main Page");
                frame.setSize(400, 500);
                cardLayout.show(cardPanel, "Main Page");
            }
        });
        return backButton;
    }


    private JButton createBackToAccountPageButton() {
        JButton accountButton = new JButton("Account");
        accountButton.setFont(new Font("Arial", Font.PLAIN, 12));
        accountButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        accountButton.setBackground(greyButtonColor);
        accountButton.setForeground(Color.BLACK);
        accountButton.setBorder(customBorder);

        Dimension accountDimension = new Dimension(70, 30);
        accountButton.setMinimumSize(accountDimension);
        accountButton.setMaximumSize(accountDimension);

        accountButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardPanel.add(createAccountPanel(), "Account Options");
                cardLayout.show(cardPanel, "Account Options");
                frame.setSize(400, 375);

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



}