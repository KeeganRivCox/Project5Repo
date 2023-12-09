import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Collections;

public class CustomersPanel {
    private JFrame frame;
    private JPanel cardPanel;

    private String custShoppingCartName;

    private String pastPurchasesProductName;

    private String pastPurchasesStoreName;

    private CardLayout cardLayout;

    private String sellerName;

    private JLabel sellerSelectedLabel;

    private JLabel selectedProductLabel;
    private String selectedProduct;

    private String searchInputProductName;

    private String searchInputProductDescription;

    private int customerShoppingCartQuantity = 0;

    private Product bestSellerOne;
    private Product bestSellerTwo;
    private Product bestSellerThree;
    private String storeName;

    private JLabel selectedStoreLabel = new JLabel("");;

    private String productName;

    private Seller selectedSeller;
    Color customColor = new Color(206, 184, 136);
    Color greyButtonColor = new Color(196,191,192);
    Border customBorder = BorderFactory.createLineBorder(Color.BLACK, 3);



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
        cardPanel.add(createSellerListingSortPanel(), "Seller Listing Sort");
        cardPanel.add(createLogOutPanel(), "Log Out");
        cardPanel.add(createContactSellerPanel(), "Contact Sellers");
        cardPanel.add(createSellerListingsPanel(), "Seller Listings");
        cardPanel.add(createStoreListingsPagePanel(), "Store Listings");
        cardPanel.add(createSearchOptionsPanel(), "Search Options");
        cardPanel.add(searchProductNamePanel(), "Search Product Input");
        cardPanel.add(searchProductDescriptionPanel(), "Search Product Description Input");

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
        mainPagePanel.setBackground(customColor);
        mainPagePanel.setLayout(new BoxLayout(mainPagePanel, BoxLayout.Y_AXIS));
        //mainPagePanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        ImageIcon imageIcon = new ImageIcon("images/BoilerBay1.png");
        JLabel imageLabel = new JLabel();
        imageLabel.setMinimumSize(new Dimension(100,100));
        imageLabel.setMaximumSize(new Dimension(100,100));
        imageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
//        imageLabel.setIcon(imageIcon);
        imageLabel.setIcon(new ImageIcon(imageIcon.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT)));


//        JLabel applicationLabel = new JLabel("Boiler Bay");
//        applicationLabel.setMaximumSize(new Dimension(125,50));
//        applicationLabel.setMinimumSize(new Dimension(125,50));
//        applicationLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
//        applicationLabel.setFont(new Font("Times New Roman", Font.BOLD, 26));

        JButton switchToAccountPanelButton = createBackToAccountPageButton();
        switchToAccountPanelButton.setBackground(greyButtonColor);
        switchToAccountPanelButton.setForeground(Color.BLACK);
        switchToAccountPanelButton.setBorder(customBorder);
        switchToAccountPanelButton.setFont(new Font("Arial", Font.PLAIN, 16));
        switchToAccountPanelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "Account Options");
                frame.setSize(400,300);
                frame.setLocationRelativeTo(null);

            }
        });

        JPanel topPanel = new JPanel();
        topPanel.setOpaque(false);
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.X_AXIS));
        topPanel.setAlignmentY(Component.CENTER_ALIGNMENT);

        topPanel.add(Box.createHorizontalStrut(120));
        topPanel.add(imageLabel);
//        topPanel.add(Box.createHorizontalStrut(20));
//        topPanel.add(applicationLabel);
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

                cardLayout.show(cardPanel, "Search Options");
                frame.setSize(400,350);
                frame.setLocationRelativeTo(null);
            }
        });


        //JTextField searchField = new JTextField();
        //searchField.setPreferredSize(new Dimension(300, 30));

        JPanel searchPanel = new JPanel(new FlowLayout());
        searchPanel.setOpaque(false);
        Dimension searchPanelDimension = new Dimension(400, 40);
        searchPanel.setPreferredSize(searchPanelDimension);
        searchPanel.add(searchButton);

        Dimension buttonDimension = new Dimension(100,100);
        JButton seeAllProductsButton = new JButton("Products");
        seeAllProductsButton.setBackground(greyButtonColor);
        seeAllProductsButton.setForeground(Color.BLACK);
        seeAllProductsButton.setBorder(customBorder);
        seeAllProductsButton.setPreferredSize(buttonDimension);
        //seeAllProductsButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        seeAllProductsButton.setFont(new Font("Arial", Font.PLAIN, 16));
        seeAllProductsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                cardLayout.show(cardPanel, "Product Listings");
                frame.setSize(400,500);
                frame.setLocationRelativeTo(null);

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
                cardLayout.show(cardPanel, "Store Listings");
                frame.setSize(400,500);
                frame.setLocationRelativeTo(null);
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
                cardLayout.show(cardPanel, "Seller Listing Sort");
                frame.setSize(400,300);
                frame.setLocationRelativeTo(null);
            }
        });

        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.setOpaque(false);
        Dimension buttonPanelDimension = new Dimension(400, 80);
        buttonPanel.setPreferredSize(buttonPanelDimension);
        buttonPanel.add(seeAllProductsButton);
        buttonPanel.add(seeAllStoresButton);
        buttonPanel.add(seeAllSellersButton);


        ImageIcon imageIconBottom = new ImageIcon("images/Purdue.png");
        JLabel imageBottomLabel = new JLabel();
        imageBottomLabel.setMinimumSize(new Dimension(100,100));
        imageBottomLabel.setMaximumSize(new Dimension(100,100));
        imageBottomLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
//        imageLabel.setIcon(imageIcon);
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
        JPanel createAccountPanel = new JPanel();
        createAccountPanel.setBackground(customColor);
        createAccountPanel.setLayout(new BoxLayout(createAccountPanel, BoxLayout.Y_AXIS));

        JButton backButton = createBackToMenuButton();

        JLabel titleLabel = new JLabel("Customer Name's Account");
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));

        JPanel topPanel = new JPanel();
        topPanel.setOpaque(false);
        Dimension topPanelDimension = new Dimension(400, 50);
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.X_AXIS));
        topPanel.setMaximumSize(topPanelDimension);
        topPanel.setMaximumSize(topPanelDimension);
        topPanel.setAlignmentY(Component.CENTER_ALIGNMENT);

        topPanel.add(Box.createHorizontalStrut(20));
        topPanel.add(backButton);
        topPanel.add(Box.createHorizontalStrut(20));
        topPanel.add(titleLabel);
        topPanel.add(Box.createHorizontalStrut(20));

        Dimension buttonDimension = new Dimension(250, 50);
        JButton shoppingCartButton = new JButton(("View Shopping Cart"));
        shoppingCartButton.setMaximumSize(buttonDimension);
        shoppingCartButton.setMinimumSize(buttonDimension);
        shoppingCartButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        shoppingCartButton.setFont(new Font("Arial", Font.PLAIN, 18));
        shoppingCartButton.setBackground(greyButtonColor);
        shoppingCartButton.setForeground(Color.BLACK);
        shoppingCartButton.setBorder(customBorder);
        shoppingCartButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                cardPanel.add(createShoppingCartPanel(), "Shopping Cart");
                cardLayout.show(cardPanel, "Shopping Cart");
                frame.setSize(400, 400);
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
        logOutButton.setMaximumSize(buttonDimension);
        logOutButton.setMinimumSize(buttonDimension);
        logOutButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        logOutButton.setBackground(greyButtonColor);
        logOutButton.setForeground(Color.BLACK);
        logOutButton.setBorder(customBorder);
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
    private JPanel createSellerListingsPanel() {
        JPanel sellerListingsPanel = new JPanel();
        sellerListingsPanel.setBackground(customColor);
        sellerListingsPanel.setLayout(new BoxLayout(sellerListingsPanel, BoxLayout.Y_AXIS));
        sellerListingsPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton backButton = createBackToMenuButton();

        JLabel titleLabel = new JLabel("All Sellers");
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));

        JPanel titlePanel = new JPanel();
        titlePanel.setOpaque(false);
        titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.X_AXIS));
        Dimension titleDimension = new Dimension(400, 50);
        titlePanel.setMaximumSize(titleDimension);
        titlePanel.setMinimumSize(titleDimension);

        titlePanel.add(Box.createHorizontalStrut(20));
        titlePanel.add(createBackToMenuButton());
        titlePanel.add(Box.createHorizontalStrut(60));
        titlePanel.add(titleLabel);
        titlePanel.add(Box.createHorizontalStrut(20));


        JPanel allSellersPanel = new JPanel();
        allSellersPanel.setBackground(greyButtonColor);
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
        Dimension paneDimension = new Dimension(300,300);
        jsp.setMaximumSize(paneDimension);
        jsp.setMinimumSize(paneDimension);
        jsp.getViewport().setBackground(greyButtonColor);
        jsp.setBorder(customBorder);

        JLabel instructions = new JLabel("Click on a Seller to View its Page");
        instructions.setAlignmentX(Component.CENTER_ALIGNMENT);
        instructions.setFont(new Font("Arial", Font.PLAIN, 18));
        Dimension instructionDimension = new Dimension(275, 50);
        instructions.setMinimumSize(instructionDimension);
        instructions.setMaximumSize(instructionDimension);

        sellerListingsPanel.add(Box.createVerticalStrut(20));
        sellerListingsPanel.add(titlePanel);
        sellerListingsPanel.add(Box.createVerticalStrut(20));
        sellerListingsPanel.add(jsp);
        sellerListingsPanel.add(Box.createVerticalStrut(10));
        sellerListingsPanel.add(instructions);
        sellerListingsPanel.add(Box.createVerticalStrut(10));

        return sellerListingsPanel;

    }


    // method that creates the panel for the product listings page
    private JPanel createProductListingsPanel() {
        JPanel productsListingPanel = new JPanel();
        productsListingPanel.setBackground(customColor);
        productsListingPanel.setLayout(new BoxLayout(productsListingPanel, BoxLayout.Y_AXIS));

        JButton backToMenuButton = createBackToMenuButton();

        JLabel titleLabel = new JLabel("All Products"); //not completely centered
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        titleLabel.setAlignmentX((Component.CENTER_ALIGNMENT));
        Dimension titleLabelDimension = new Dimension(100, 30);
        titleLabel.setMinimumSize(titleLabelDimension);
        titleLabel.setMaximumSize(titleLabelDimension);

        JButton accountPageButton = createBackToAccountPageButton();

        Dimension titleDimension = new Dimension(400,50);
        JPanel titlePanel = new JPanel();
        titlePanel.setOpaque(false);
        titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.X_AXIS));
        titlePanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        titlePanel.setMinimumSize(titleDimension);
        titlePanel.setMaximumSize(titleDimension);

        titlePanel.add(Box.createHorizontalStrut(50));
        titlePanel.add(backToMenuButton);
        titlePanel.add(Box.createHorizontalStrut(40));
        titlePanel.add(titleLabel);
        titlePanel.add(Box.createHorizontalStrut(30));
        titlePanel.add(accountPageButton);
        titlePanel.add(Box.createHorizontalStrut(40));


        String [] dropdownOptions = new String[]{"Price High To Low", "Price Low to High", "Quantity Least to Greatest", "Quantity Greatest to Least"};
        JComboBox sortByDropdown = new JComboBox<>(dropdownOptions);
        sortByDropdown.setBackground(greyButtonColor);
        sortByDropdown.setBorder(customBorder);

        sortByDropdown.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (sortByDropdown.getSelectedItem().equals("Price High To Low")) {

                }
            }
        });

        JPanel dropDownPanel = new JPanel();
        dropDownPanel.setOpaque(false);
        dropDownPanel.setMaximumSize(new Dimension(400, 40));
        dropDownPanel.setMinimumSize(new Dimension(400, 40));
        dropDownPanel.add(sortByDropdown);

        JPanel productPanel = new JPanel();
        productPanel.setOpaque(false);
        productPanel.setLayout((new BoxLayout(productPanel, BoxLayout.Y_AXIS)));
        productPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JScrollPane jsp = new JScrollPane(productPanel, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jsp.setAlignmentX(Component.CENTER_ALIGNMENT);
        jsp.setMaximumSize(new Dimension(340, 300));
        jsp.setMinimumSize(new Dimension(340, 300));
        jsp.getViewport().setBackground(greyButtonColor);
        jsp.setBorder(customBorder);


//        // changes scrollpane to have white background
//        JViewport viewport = new JViewport();
//        viewport.setBackground(Color.WHITE);
//        jsp.setViewport(viewport);

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


        productsListingPanel.add(Box.createVerticalStrut(20));
        productsListingPanel.add(titlePanel);
        productsListingPanel.add(Box.createVerticalStrut(10));
        productsListingPanel.add(dropDownPanel);
        productsListingPanel.add(Box.createVerticalStrut(10));
        productsListingPanel.add(jsp);
        productsListingPanel.add(Box.createVerticalStrut(20));
        productsListingPanel.add(instructionLabel);
        productsListingPanel.add(Box.createVerticalStrut(20));

        return productsListingPanel;
    }


    // method that creates panel for the store listings page
    // TODO: idk how to format the jscrollpane its not letting it change
    private JPanel createStoreListingsPagePanel() {
        JPanel storeListingsPanel = new JPanel();
        storeListingsPanel.setLayout(new BoxLayout(storeListingsPanel, BoxLayout.Y_AXIS));
        storeListingsPanel.setBackground(customColor);

        JPanel topPanel = new JPanel();
        topPanel.setMaximumSize(new Dimension(400,50));
        topPanel.setMinimumSize(new Dimension(400,50));
        topPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        topPanel.setOpaque(false);
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.X_AXIS));

        JPanel allStoresPanel = new JPanel();
        allStoresPanel.setMaximumSize(new Dimension(325,250));
        allStoresPanel.setMinimumSize(new Dimension(325,250));
        allStoresPanel.setOpaque(false);
        allStoresPanel.setLayout(new BoxLayout(allStoresPanel, BoxLayout.Y_AXIS));
        allStoresPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel selectStore = new JLabel("All Stores");
        selectStore.setAlignmentX(Component.CENTER_ALIGNMENT);
        selectStore.setFont(new Font("Arial", Font.BOLD, 20));
//        Dimension titleLabelDimension = new Dimension(250, 30);
//        selectStore.setMinimumSize(titleLabelDimension);
//        selectStore.setMaximumSize(titleLabelDimension);

        JButton accountPageButton = createBackToAccountPageButton();

        JButton backButton = createBackToMenuButton();

        topPanel.add(Box.createHorizontalStrut(30));
        topPanel.add(backButton);
        topPanel.add(Box.createHorizontalStrut(50));
        topPanel.add(selectStore);
        topPanel.add(Box.createHorizontalStrut(50));
        topPanel.add(accountPageButton);
        topPanel.add(Box.createHorizontalStrut(20));

        String [] dummyStores = new String[]{"Store One" ,"Store Two","Store Three","Store Four", "Store Five", "Store Six", "Store Seven", "Store Eight",
                "Store Nine", "Store Ten", "Store Eleven", "Store Twelve","Store Thirst","Store Fourteen","Store Fifth-teener","Store Sixty"};
/*
        for(int i =0; i < sellerStores.size();i++){

            String name = sellerStores.get(i).getName();
            JLabel label = new JLabel(name);
            label.setAlignmentX(Component.CENTER_ALIGNMENT);
            label.setFont(new Font("Arial", Font.PLAIN, 18));

            label.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    // Handle click event
                    storeName = label.getText();
                    //System.out.println(storeName);
                    // Update the display with the selected seller's name
                    //selectedStoreLabel.setText("Selected Store: " + storeName);
                    cardLayout.show(cardPanel,"Individual Store");


                }
            });

            allStoresPanel.add(label);
            allStoresPanel.add(new JSeparator(JSeparator.HORIZONTAL));
        }
        */

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

        allStoresPanel.remove(allStoresPanel.getComponentCount() - 1);
        allStoresPanel.setBackground(greyButtonColor);

        JScrollPane jsp = new JScrollPane(allStoresPanel, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jsp.setMinimumSize(new Dimension(325,200));
        jsp.setMaximumSize(new Dimension(325,200));
        jsp.getViewport().setBackground(greyButtonColor);
        jsp.setBorder(customBorder);

        // Display selected Store name
        //selectedStoreLabel = new JLabel("Selected Store: ");
        //selectedStoreLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        //selectedStoreLabel.setFont(new Font("Arial", Font.PLAIN, 18));

        storeListingsPanel.add(Box.createVerticalStrut(20));
        storeListingsPanel.add(topPanel);
        storeListingsPanel.add(Box.createVerticalStrut(20));
        storeListingsPanel.add(jsp);
        storeListingsPanel.add(Box.createVerticalStrut(20));


        return storeListingsPanel;
    }

    // method that creates panel for the store listings page
    private JPanel createIndividualStoreListingsPanel(Store store) {
        JPanel individualStorePanel = new JPanel();
        individualStorePanel.setBackground(customColor);
        individualStorePanel.setLayout(new BoxLayout(individualStorePanel, BoxLayout.Y_AXIS));

        JPanel listStoreProductsPanel = new JPanel();
        listStoreProductsPanel.setOpaque(false);
        listStoreProductsPanel.setLayout(new BoxLayout(listStoreProductsPanel, BoxLayout.Y_AXIS));

        JLabel sName = selectedStoreLabel;
        sName.setFont(new Font("Arial", Font.PLAIN, 18));

        JButton backButton = createBackToMenuButton();

        individualStorePanel.add(sName);
        individualStorePanel.add(backButton);

        String [] dummyProducts = new String[]{"Product One" ,"Product Two","Product Three","Product Four", "Product Five", "Product Six", "Product Seven", "Product Eight",
                "Product Nine", "Product Ten", "Product Eleven", "Product Twelve", "Product Thriteen", "Product Fourteen"};

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

        listStoreProductsPanel.remove(listStoreProductsPanel.getComponentCount() - 1);
        JScrollPane jsp = new JScrollPane(listStoreProductsPanel, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        //individualStorePanel.add(selectStore);
        individualStorePanel.add(Box.createVerticalStrut(20));
        individualStorePanel.add(jsp);
        individualStorePanel.add(Box.createVerticalStrut(20));


        return individualStorePanel;
    }


    private JPanel createLogOutPanel() {
        JPanel logOutPanel = new JPanel();
        logOutPanel.setBackground(customColor);
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
        noButton.setBackground(greyButtonColor);
        noButton.setForeground(Color.BLACK);
        noButton.setBorder(customBorder);
        noButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "Account Options");
                frame.setSize(400,375);
                frame.setLocationRelativeTo(null);
            }
        });

        JButton yesButton = new JButton("Yes");
        yesButton.setFont(new Font("Arial", Font.PLAIN, 18));
        yesButton.setPreferredSize(buttonDimension);
        yesButton.setBackground(greyButtonColor);
        yesButton.setForeground(Color.BLACK);
        yesButton.setBorder(customBorder);
        yesButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
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

    private JPanel createSellerListingSortPanel() {
        JPanel sellerListingSortPanel = new JPanel();
        sellerListingSortPanel.setBackground(customColor);
        sellerListingSortPanel.setLayout(new BoxLayout(sellerListingSortPanel, BoxLayout.Y_AXIS));
        sellerListingSortPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel questionLabel = new JLabel("What would you like to do?");
        questionLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        questionLabel.setFont(new Font("Arial", Font.BOLD, 18));

        Dimension buttonDimension = new Dimension(200, 100);
        JButton sellerListingButton = new JButton("Show All Sellers");
        sellerListingButton.setMinimumSize(buttonDimension);
        sellerListingButton.setMaximumSize(buttonDimension);
        sellerListingButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        sellerListingButton.setFont(new Font("Arial", Font.PLAIN, 18));
        sellerListingButton.setBackground(greyButtonColor);
        sellerListingButton.setForeground(Color.BLACK);
        sellerListingButton.setBorder(customBorder);
        sellerListingButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "Seller Listings");
                frame.setSize(400,500);
                frame.setLocationRelativeTo(null);
            }
        });

        JButton contactSellersButton = new JButton("Contact Sellers");
        contactSellersButton.setMaximumSize(buttonDimension);
        contactSellersButton.setMaximumSize(buttonDimension);
        contactSellersButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        contactSellersButton.setFont(new Font("Arial", Font.PLAIN, 18));
        contactSellersButton.setBackground(greyButtonColor);
        contactSellersButton.setForeground(Color.BLACK);
        contactSellersButton.setBorder(customBorder);
        contactSellersButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "Contact Sellers");
                frame.setSize(400,500);
                frame.setLocationRelativeTo(null);
            }
        });

        JPanel bottomPanel = new JPanel();
        bottomPanel.setOpaque(false);
        bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.X_AXIS));
        bottomPanel.add(Box.createHorizontalStrut(20));
        bottomPanel.add(createBackToMenuButton());
        bottomPanel.setMaximumSize(new Dimension(400,50));
        bottomPanel.setMinimumSize(new Dimension(400,50));


        sellerListingSortPanel.add(Box.createVerticalStrut(20));
        sellerListingSortPanel.add(questionLabel);
        sellerListingSortPanel.add(Box.createVerticalStrut(20));
        sellerListingSortPanel.add(sellerListingButton);
        sellerListingSortPanel.add(Box.createVerticalStrut(20));
        sellerListingSortPanel.add(contactSellersButton);
        sellerListingSortPanel.add(Box.createVerticalStrut(20));
        sellerListingSortPanel.add(bottomPanel);
        sellerListingSortPanel.add(Box.createVerticalStrut(20));

        return sellerListingSortPanel;
    }

    private String messageSellerName;  // Variable to store the selected seller's name

    private JPanel createContactSellerPanel() {
        JPanel contactSellerPanel = new JPanel();
        contactSellerPanel.setBackground(customColor);
        contactSellerPanel.setLayout(new BoxLayout(contactSellerPanel, BoxLayout.Y_AXIS));
        contactSellerPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Back button
        JButton backButton = createBackToMenuButton();


        JLabel titleLabel = new JLabel("Message a Seller");
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));

        // Panel for back button and title
        JPanel titlePanel = new JPanel();
        Dimension titleDimension = new Dimension(400,50);
        titlePanel.setMinimumSize(titleDimension);
        titlePanel.setMaximumSize(titleDimension);
        titlePanel.setOpaque(false);
        titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.X_AXIS));

        titlePanel.add(Box.createHorizontalStrut(20)); // Add some spacing
        titlePanel.add(backButton);
        titlePanel.add(Box.createHorizontalStrut(50)); // Add some spacing
        titlePanel.add(titleLabel);
        titlePanel.add(Box.createHorizontalStrut(20)); // Add some spacing


        JPanel sellerNamePanel = new JPanel();
        sellerNamePanel.setOpaque(false);
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
        Dimension jspDimension = new Dimension(300,200);
        jsp.setMinimumSize(jspDimension);
        jsp.setMaximumSize(jspDimension);
        jsp.getViewport().setBackground(greyButtonColor);
        jsp.setBorder(customBorder);

        // Display selected seller's name
        selectedSellerLabel = new JLabel("Selected Seller: ");
        selectedSellerLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        selectedSellerLabel.setFont(new Font("Arial", Font.PLAIN, 18));

        // Container for the message components
        JPanel messagePanel = new JPanel();
        messagePanel.setOpaque(false);
        messagePanel.setLayout(new BoxLayout(messagePanel, BoxLayout.Y_AXIS));

        // Label above JTextArea
        JLabel messageLabel = new JLabel("What's your message?");
        messageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        messageLabel.setFont(new Font("Arial", Font.PLAIN, 14));

        // JTextArea for entering the message
        JTextArea messageTextArea = new JTextArea(3,20);
        messageTextArea.setMaximumSize(new Dimension(300, 60));
        messageTextArea.setMinimumSize(new Dimension(300, 60));
        messageTextArea.setLineWrap(true);
        messageTextArea.setWrapStyleWord(true);
        messageTextArea.setBackground(greyButtonColor);
        messageTextArea.setFont(new Font("Arial", Font.PLAIN, 18));


        JScrollPane messageScrollPane = new JScrollPane(messageTextArea);
        messageScrollPane.setMaximumSize(new Dimension(300, 60));
        messageScrollPane.setMinimumSize(new Dimension(300, 60));
        messageScrollPane.setBackground(greyButtonColor);
        messageScrollPane.setForeground(Color.BLACK);
        messageScrollPane.setBorder(customBorder);

        // Button to send the message
        JButton sendMessageButton = new JButton("Send Message");
        sendMessageButton.setMaximumSize(new Dimension(200,50));
        sendMessageButton.setMinimumSize(new Dimension(200,50));
        sendMessageButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        sendMessageButton.setBackground(greyButtonColor);
        sendMessageButton.setForeground(Color.BLACK);
        sendMessageButton.setBorder(customBorder);

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
        messagePanel.add(Box.createVerticalStrut(5));
        messagePanel.add(messageScrollPane);
        messagePanel.add(Box.createVerticalStrut(20));
        messagePanel.add(sendMessageButton);


        contactSellerPanel.setLayout(new BoxLayout(contactSellerPanel, BoxLayout.Y_AXIS));

        contactSellerPanel.add(Box.createVerticalStrut(20));
        contactSellerPanel.add(titlePanel);
        contactSellerPanel.add(Box.createVerticalStrut(20));
        contactSellerPanel.add(jsp);
        contactSellerPanel.add(Box.createVerticalStrut(10));
        contactSellerPanel.add(selectedSellerLabel);
        contactSellerPanel.add(Box.createVerticalStrut(10));
        contactSellerPanel.add(messagePanel);
        contactSellerPanel.add(Box.createVerticalStrut(20));


        return contactSellerPanel;
    }

    private JPanel createPastPurchasesPanel() {
        JPanel pastPurchasesPanel = new JPanel();
        pastPurchasesPanel.setBackground(customColor);
        pastPurchasesPanel.setLayout(new BoxLayout(pastPurchasesPanel, BoxLayout.Y_AXIS));
        pastPurchasesPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Back button
        JButton backButtonPastPurchases = createBackToMenuButton();

        Dimension titleDimension = new Dimension(250, 50);
        JLabel titleLabelPastPurchases = new JLabel("Select a Product from " + pastPurchasesStoreName);
        titleLabelPastPurchases.setMinimumSize(titleDimension);
        titleLabelPastPurchases.setMaximumSize(titleDimension);
        titleLabelPastPurchases.setAlignmentX(Component.CENTER_ALIGNMENT);
        titleLabelPastPurchases.setFont(new Font("Arial", Font.BOLD, 16));

        // Panel for back button and title
        JPanel titlePanelPastPurchases = new JPanel();
        titlePanelPastPurchases.setMinimumSize(new Dimension(400,50));
        titlePanelPastPurchases.setMaximumSize(new Dimension(400,50));
        titlePanelPastPurchases.setOpaque(false);
        titlePanelPastPurchases.setLayout(new BoxLayout(titlePanelPastPurchases, BoxLayout.X_AXIS));
        titlePanelPastPurchases.add(Box.createHorizontalStrut(20));
        titlePanelPastPurchases.add(backButtonPastPurchases);
        titlePanelPastPurchases.add(Box.createHorizontalStrut(20));
        titlePanelPastPurchases.add(titleLabelPastPurchases);
        titlePanelPastPurchases.add(Box.createHorizontalStrut(20));


        // Column labels
        JLabel productLabel = new JLabel("Product");
        JLabel priceLabel = new JLabel("Price");
        JLabel quantityLabel = new JLabel("Quantity");

        JPanel columnLabelsPanel = new JPanel();
        columnLabelsPanel.setOpaque(false);
        columnLabelsPanel.setLayout(new GridLayout(1, 3));
        columnLabelsPanel.add(Box.createHorizontalStrut(5));
        columnLabelsPanel.add(productLabel);
        columnLabelsPanel.add(Box.createHorizontalStrut(5));
        columnLabelsPanel.add(priceLabel);
        columnLabelsPanel.add(Box.createHorizontalStrut(5));
        columnLabelsPanel.add(quantityLabel);
        columnLabelsPanel.add(Box.createHorizontalStrut(50));


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

        JLabel selectedStoreLabelPastPurchases = new JLabel("Selected Product: ");
        selectedStoreLabelPastPurchases.setAlignmentX(Component.CENTER_ALIGNMENT);
        selectedStoreLabelPastPurchases.setFont(new Font("Arial", Font.PLAIN, 18));

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

            productLabelItem.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    // Handle click event
                    pastPurchasesProductName = productLabelItem.getText();
                    // Update the display with the selected product's name
                    selectedStoreLabelPastPurchases.setText("Selected Product: " + pastPurchasesProductName);
                }
            });

            storeNamePanelPastPurchases.add(productLabelItem);
            storeNamePanelPastPurchases.add(Box.createHorizontalStrut(2));
            storeNamePanelPastPurchases.add(priceLabelItem);
            storeNamePanelPastPurchases.add(Box.createHorizontalStrut(2));
            storeNamePanelPastPurchases.add(quantityLabelItem);

        }

        // Add a line between columns
        storeNamePanelPastPurchases.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, Color.BLACK));
        storeNamePanelPastPurchases.setBackground(greyButtonColor);

        JScrollPane pspPastPurchases = new JScrollPane(storeNamePanelPastPurchases, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        pspPastPurchases.setMinimumSize(new Dimension(350, 250));
        pspPastPurchases.setMaximumSize(new Dimension(350, 250));
        pspPastPurchases.getViewport().setBackground(greyButtonColor);
        pspPastPurchases.setBorder(customBorder);


        JButton exportButton = new JButton("Export");
        exportButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        exportButton.setFont(new Font("Arial", Font.PLAIN, 18));
        exportButton.setMaximumSize(new Dimension(100,50));
        exportButton.setMinimumSize(new Dimension(100,50));
        exportButton.setBackground(greyButtonColor);
        exportButton.setForeground(Color.BLACK);
        exportButton.setBorder(customBorder);
        exportButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (pastPurchasesProductName == null) {
                    JOptionPane.showMessageDialog(createPastPurchasesPanel(), "Please select a Product first.", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    // Handle confirmation action
                    List<String[]> data = new ArrayList<>();
                    data.add(new String[]{"Product", "Price", "Quantity"});

                    for (int i = 0; i < dummyStoresItem.length; i += 3) {
                        String[] row = new String[]{dummyStoresItem[i], dummyStoresItem[i + 1], dummyStoresItem[i + 2]};
                        data.add(row);
                    }

                    try (FileWriter writer = new FileWriter("exported_data.csv")) {
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
        pastPurchasesPanel.add(selectedStoreLabelPastPurchases);
        pastPurchasesPanel.add(Box.createVerticalStrut(10));
        pastPurchasesPanel.add(exportButton);
        pastPurchasesPanel.add(Box.createVerticalStrut(20));

        return pastPurchasesPanel;
    }





    private JPanel createSellerPanel(Seller seller) { //should have an object parameter that takes a Seller object
        JPanel sellerPanel = new JPanel();
        sellerPanel.setBackground(customColor);
        sellerPanel.setLayout(new BoxLayout(sellerPanel, BoxLayout.Y_AXIS));
        sellerPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel titlePanel = new JPanel();
        titlePanel.setOpaque(false);
        titlePanel.setLayout(new BoxLayout(titlePanel,BoxLayout.X_AXIS));
        titlePanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        titlePanel.setMaximumSize(new Dimension(400, 50));
        titlePanel.setMinimumSize(new Dimension(400, 50));

        JButton backButton = createBackToMenuButton();
        JButton accountButton = createBackToAccountPageButton();

        JLabel sellerUsername = new JLabel(seller.getUsername());
        sellerUsername.setMaximumSize(new Dimension(200,50));
        sellerUsername.setMinimumSize(new Dimension(100,50));
        sellerUsername.setAlignmentX(Component.CENTER_ALIGNMENT);
        sellerUsername.setFont(new Font("Arial", Font.BOLD, 18));

        titlePanel.add(Box.createHorizontalStrut(20));
        titlePanel.add(backButton);
        titlePanel.add(Box.createHorizontalStrut(60));
        titlePanel.add(sellerUsername);
        titlePanel.add(accountButton);
        titlePanel.add(Box.createHorizontalStrut(20));

        ArrayList<Store> allStores = getAllStores();

        JPanel storesPanel = new JPanel();
        storesPanel.setOpaque(false);
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
        jsp.setMaximumSize(new Dimension(350, 275));
        jsp.setMinimumSize(new Dimension(350, 275));
        jsp.getViewport().setBackground(greyButtonColor);
        jsp.setBorder(customBorder);

        JPanel bottomPanel = new JPanel();
        bottomPanel.setOpaque(false);
        bottomPanel.setMaximumSize(new Dimension(400, 50));
        bottomPanel.setMinimumSize(new Dimension(400, 50));
        bottomPanel.setLayout(new BoxLayout(bottomPanel,BoxLayout.Y_AXIS));
        bottomPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel instructions = new JLabel("Click on a Store to View its Page");
        instructions.setAlignmentX(Component.CENTER_ALIGNMENT);
        instructions.setFont(new Font("Arial", Font.BOLD, 18));
        bottomPanel.add(instructions);

        JPanel dropdownPanel = new JPanel();
        dropdownPanel.setOpaque(false);
        dropdownPanel.setMinimumSize(new Dimension(300, 50));
        dropdownPanel.setMaximumSize(new Dimension(300, 50));

        String[] options = new String[]{"Most Products", "Least Products"};
        JComboBox sortDropdown = new JComboBox(options);
        sortDropdown.setMaximumSize(new Dimension(250, 30));
        sortDropdown.setMaximumSize(new Dimension(250, 30));
        sortDropdown.setAlignmentX(Component.CENTER_ALIGNMENT);
        dropdownPanel.add(sortDropdown);
        sortDropdown.setBackground(greyButtonColor);
        sortDropdown.setBorder(customBorder);

        sellerPanel.add(Box.createVerticalStrut(20));
        sellerPanel.add(titlePanel);
        sellerPanel.add(Box.createVerticalStrut(15));
        sellerPanel.add(sortDropdown);
        sellerPanel.add(Box.createVerticalStrut(20));
        sellerPanel.add(jsp);
        sellerPanel.add(Box.createVerticalStrut(30));
        sellerPanel.add(bottomPanel);
        sellerPanel.add(Box.createVerticalStrut(20));


        return sellerPanel;

    }

    private JPanel createProductPanel(Product product) {
        JPanel productPanel = new JPanel();
        productPanel.setBackground(customColor);
        productPanel.setLayout(new BoxLayout(productPanel, BoxLayout.Y_AXIS));
        productPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

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
        goToStoreButton.setBackground(greyButtonColor);
        goToStoreButton.setForeground(Color.BLACK);
        goToStoreButton.setBorder(customBorder);

        SpinnerModel quantityModel = new SpinnerNumberModel(0, 1, Integer.MAX_VALUE, 1);
        JSpinner quantitySpinner = new JSpinner(quantityModel);

        quantitySpinner.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {

                customerShoppingCartQuantity = (int) quantitySpinner.getValue();
            }
        });

        productPanel.add(createFieldWithLabel("Quantity", quantitySpinner));

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
        addToCartButton.setBackground(greyButtonColor);
        addToCartButton.setForeground(Color.BLACK);
        addToCartButton.setBorder(customBorder);
        addToCartButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (customerShoppingCartQuantity > product.getQuantity()) {
                    JOptionPane.showMessageDialog(null, "Please choose a smaller quantity.", "Customers", JOptionPane.ERROR_MESSAGE);
                }
                else {
                    JOptionPane.showMessageDialog(null, "Added " + product.getName() + "x" + customerShoppingCartQuantity + "to your shopping cart!" , "Customers", JOptionPane.INFORMATION_MESSAGE);
                }

            }
        });

        JButton viewStoreButton = new JButton("View Store");
        viewStoreButton.setBackground(greyButtonColor);
        viewStoreButton.setForeground(Color.BLACK);
        viewStoreButton.setBorder(customBorder);
        viewStoreButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JPanel panel = createShopPanel(product.getStore());
                cardPanel.add(panel, "Product Store");
                cardLayout.show(cardPanel, "Product Store");
            }
        });

        JPanel quantityPanel = new JPanel();
        quantityPanel.setOpaque(false);

        JTextField quantityTextField = new JTextField("       0       ");
        quantityTextField.setAlignmentX(Component.CENTER_ALIGNMENT);
        quantityTextField.setFont(new Font("Arial", Font.PLAIN, 18));
        quantityPanel.setLayout(new BoxLayout(quantityPanel, BoxLayout.Y_AXIS));

        /*
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

         */
        //quantityPanel.add(minusButton);
        quantityPanel.add(quantityTextField);
        //quantityPanel.add(addButton);

        productPanel.add(quantityPanel);
        productPanel.add(addToCartButton);

        return productPanel;
    }

    private JPanel createShopPanel(Store store) {
        JPanel shopPanel = new JPanel();
        shopPanel.setBackground(customColor);
        shopPanel.setLayout(new BoxLayout(shopPanel, BoxLayout.Y_AXIS));
        shopPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel storeName = new JLabel(store.getName());
        shopPanel.add(Box.createVerticalStrut(20));
        shopPanel.add(storeName);

        return shopPanel;
    }

    private JPanel createSearchOptionsPanel() {
        JPanel searchOptionsPanel = new JPanel();
        searchOptionsPanel.setBackground(customColor);
        searchOptionsPanel.setLayout(new BoxLayout(searchOptionsPanel, BoxLayout.Y_AXIS));
        searchOptionsPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel questionLabel = new JLabel("What would you like to search by?");
        questionLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        questionLabel.setFont(new Font("Arial", Font.BOLD, 18));

        Dimension buttonDimension = new Dimension(225, 50);
        JButton productNamesButton = new JButton("Product");
        productNamesButton.setMaximumSize(buttonDimension);
        productNamesButton.setMinimumSize(buttonDimension);
        productNamesButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        productNamesButton.setFont(new Font("Arial", Font.PLAIN, 18));
        productNamesButton.setBackground(greyButtonColor);
        productNamesButton.setForeground(Color.BLACK);
        productNamesButton.setBorder(customBorder);
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
                frame.setSize(450, 300);
                frame.setLocationRelativeTo(null);
            }
        });

        JButton storeNamesButton = new JButton("Store");
        storeNamesButton.setMaximumSize(buttonDimension);
        storeNamesButton.setMinimumSize(buttonDimension);
        storeNamesButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        storeNamesButton.setFont(new Font("Arial", Font.PLAIN, 18));
        storeNamesButton.setBackground(greyButtonColor);
        storeNamesButton.setForeground(Color.BLACK);
        storeNamesButton.setBorder(customBorder);

        JButton productDescriptionsButton = new JButton("Product Description");
        productDescriptionsButton.setMaximumSize(buttonDimension);
        productDescriptionsButton.setMinimumSize(buttonDimension);
        productDescriptionsButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        productDescriptionsButton.setFont(new Font("Arial", Font.PLAIN, 18));
        productDescriptionsButton.setBackground(greyButtonColor);
        productDescriptionsButton.setForeground(Color.BLACK);
        productDescriptionsButton.setBorder(customBorder);
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
                frame.setSize(450, 300);
                frame.setLocationRelativeTo(null);
            }
        });

        JPanel bottomPanel = new JPanel();
        bottomPanel.setMaximumSize(new Dimension(400,50));
        bottomPanel.setMinimumSize(new Dimension(400,50));
        bottomPanel.setOpaque(false);
        bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.X_AXIS));
        bottomPanel.add(Box.createHorizontalStrut(20));
        bottomPanel.add(createBackToMenuButton());


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
        custShoppingCartName = "Robert";
        JPanel shoppingCartPanel = new JPanel();
        shoppingCartPanel.setBackground(customColor);
        shoppingCartPanel.setLayout(new BoxLayout(shoppingCartPanel, BoxLayout.Y_AXIS));
        shoppingCartPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Customer Name Label
        JButton backButton = createBackToMenuButton();

        JLabel customerNameLabel = new JLabel(custShoppingCartName+"'s Shopping Cart");
        customerNameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        customerNameLabel.setFont(new Font("Arial", Font.BOLD, 20));

        // Panel for back button and title
        JPanel titlePanel = new JPanel();
        titlePanel.setMaximumSize(new Dimension(400,50));
        titlePanel.setMinimumSize(new Dimension(400,50));
        titlePanel.setOpaque(false);
        titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.X_AXIS));
        titlePanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        titlePanel.add(Box.createHorizontalStrut(20)); // Add some spacing
        titlePanel.add(backButton);
        titlePanel.add(Box.createHorizontalStrut(20)); // Add some spacing
        titlePanel.add(customerNameLabel);

        // Items Label
        JLabel itemsLabel = new JLabel("Items currently in cart:");
        itemsLabel.setMaximumSize(new Dimension(250,50));
        itemsLabel.setMinimumSize(new Dimension(250,50));
        itemsLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        itemsLabel.setFont(new Font("Arial", Font.PLAIN, 16));

        // Sample Data (Replace with dynamic data)
        String[] storeData = {"Store One", "Store Two", "Store Three"};
        String[] productData = {"Product A", "Product B", "Product C"};
        double[] priceData = {10.0, 20.0, 15.0};

        // Create a JPanel to hold the cart items
        JPanel cartItemsPanel = new JPanel();
        cartItemsPanel.setMinimumSize(new Dimension(350,100));
        cartItemsPanel.setMaximumSize(new Dimension(350,100));
        cartItemsPanel.setOpaque(false);
        cartItemsPanel.setLayout(new BoxLayout(cartItemsPanel, BoxLayout.Y_AXIS));

        // Headers
        JPanel headersPanel = new JPanel();
        headersPanel.setOpaque(false);
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
            deleteButton.setBackground(greyButtonColor);
            deleteButton.setForeground(Color.BLACK);
            deleteButton.setBorder(customBorder);
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
        checkoutButton.setMinimumSize(new Dimension(100,50));
        checkoutButton.setMaximumSize(new Dimension(100,50));
        checkoutButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        checkoutButton.setFont(new Font("Arial", Font.PLAIN, 16));
        checkoutButton.setForeground(Color.BLACK);
        checkoutButton.setBackground(greyButtonColor);
        checkoutButton.setBorder(customBorder);

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
        shoppingCartPanel.add(Box.createVerticalStrut(20));
        shoppingCartPanel.add(titlePanel);
        shoppingCartPanel.add(itemsLabel);
        shoppingCartPanel.add(cartItemsPanel);
        shoppingCartPanel.add(Box.createVerticalStrut(50));
        shoppingCartPanel.add(checkoutButton);
        shoppingCartPanel.add(Box.createVerticalStrut(20));

        return shoppingCartPanel;
    }

    private JPanel searchProductNamePanel () {
        JPanel searchedProductNameInput = new JPanel();
        searchedProductNameInput.setBackground(customColor);
        searchedProductNameInput.setLayout(new BoxLayout(searchedProductNameInput, BoxLayout.Y_AXIS));
        searchedProductNameInput.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton backToMenuButton = createBackToMenuButton();


        JLabel title = new JLabel("Enter the Product Name");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        title.setFont(new Font("Arial", Font.BOLD, 18));
        Dimension titleDimension = new Dimension(225, 30);
        title.setMinimumSize(titleDimension);
        title.setMaximumSize(titleDimension);

        JButton accountPageButton = createBackToAccountPageButton();

        JPanel titlePanel = new JPanel();
        titlePanel.setOpaque(false);
        titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.X_AXIS));
        titlePanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        Dimension titlePanelDimension = new Dimension(400,50);
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
        Dimension searchDimension = new Dimension(350,150);
        search.setMinimumSize(searchDimension);
        search.setMaximumSize(searchDimension);
        search.setBackground(greyButtonColor);
        search.setForeground(Color.BLACK);
        search.setBorder(customBorder);
        search.setFont(new Font("Arial", Font.PLAIN, 24));

        JButton searchButton = new JButton("Search");
        searchButton.setMinimumSize(new Dimension(100,30));
        searchButton.setMaximumSize(new Dimension(100,30));
        searchButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        searchButton.setFont(new Font("Arial", Font.PLAIN, 18));
        searchButton.setBackground(greyButtonColor);
        searchButton.setForeground(Color.BLACK);
        searchButton.setBorder(customBorder);

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
        searchedProductNameInput.add(titlePanel);
        searchedProductNameInput.add(Box.createVerticalStrut(20));
        searchedProductNameInput.add(search);
        searchedProductNameInput.add(Box.createVerticalStrut(20));
        searchedProductNameInput.add(searchButton);
        searchedProductNameInput.add(Box.createVerticalStrut(20));

        return searchedProductNameInput;

    }

    private JPanel createSearchProductsPanel (String productName) {
        JPanel searchedProductsPanel = new JPanel();
        searchedProductsPanel.setBackground(customColor);
        searchedProductsPanel.setLayout(new BoxLayout(searchedProductsPanel, BoxLayout.Y_AXIS));
        searchedProductsPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel titlePanel = new JPanel();
        titlePanel.setOpaque(false);
        titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.X_AXIS));
        titlePanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        Dimension titlePanelDimension = new Dimension(400,50);
        titlePanel.setMinimumSize(titlePanelDimension);
        titlePanel.setMaximumSize(titlePanelDimension);

        JLabel title = new JLabel("Searched Products");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        title.setFont(new Font("Arial", Font.BOLD, 18));

        titlePanel.add(Box.createHorizontalStrut(20));
        titlePanel.add(createBackToMenuButton());
        titlePanel.add(Box.createHorizontalStrut(20));
        titlePanel.add(title);
        titlePanel.add(Box.createHorizontalStrut(20));
        titlePanel.add(createBackToAccountPageButton());
        titlePanel.add(Box.createHorizontalStrut(20));

        JPanel allProductsPanel = new JPanel();
        allProductsPanel.setOpaque(false);
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
        jsp.setMaximumSize(new Dimension(350, 300));
        jsp.setMinimumSize(new Dimension(350, 300));
        jsp.getViewport().setBackground(greyButtonColor);
        jsp.setBorder(customBorder);

//        // changes scrollpane to have white background
//        JViewport viewport = new JViewport();
//        viewport.setBackground(Color.WHITE);
//        jsp.setViewport(viewport);


        JPanel bottomPanel = new JPanel();
        bottomPanel.setOpaque(false);
        bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.Y_AXIS));
        bottomPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        bottomPanel.setMaximumSize(new Dimension(400, 50));
        bottomPanel.setMinimumSize(new Dimension(400,50));

        JLabel instructions = new JLabel("Select a Product to View its Page");
        instructions.setAlignmentX((Component.CENTER_ALIGNMENT));
        instructions.setFont(new Font("Arial", Font.PLAIN, 18));

        bottomPanel.add(Box.createHorizontalStrut(20));
        bottomPanel.add(instructions);
        bottomPanel.add(Box.createHorizontalStrut(20));


        searchedProductsPanel.add(Box.createVerticalStrut(20));
        searchedProductsPanel.add(titlePanel);
        searchedProductsPanel.add(Box.createVerticalStrut(20));
        searchedProductsPanel.add(jsp);
        searchedProductsPanel.add(Box.createVerticalStrut(10));
        searchedProductsPanel.add(bottomPanel);
        searchedProductsPanel.add(Box.createVerticalStrut(10));

        return searchedProductsPanel;
    }

    private JPanel searchProductDescriptionPanel() {
        JPanel searchProductDescriptionPanel = new JPanel();
        searchProductDescriptionPanel.setBackground(customColor);
        searchProductDescriptionPanel.setLayout(new BoxLayout(searchProductDescriptionPanel, BoxLayout.Y_AXIS));
        searchProductDescriptionPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton backToMenuButton = createBackToMenuButton();

        JLabel title = new JLabel("Enter Product Description");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        title.setFont(new Font("Arial", Font.BOLD, 16));
        Dimension titleDimension = new Dimension(225, 30);
        title.setMinimumSize(titleDimension);
        title.setMaximumSize(titleDimension);

        JButton accountPageButton = createBackToAccountPageButton();

        JPanel titlePanel = new JPanel();
        titlePanel.setOpaque(false);
        titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.X_AXIS));
        titlePanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        Dimension titlePanelDimension = new Dimension(400,50);
        titlePanel.setMinimumSize(titlePanelDimension);
        titlePanel.setMaximumSize(titlePanelDimension);

        titlePanel.add(Box.createHorizontalStrut(20));
        titlePanel.add(backToMenuButton);
        titlePanel.add(Box.createHorizontalStrut(30));
        titlePanel.add(title);
        titlePanel.add(Box.createHorizontalStrut(20));
        titlePanel.add(accountPageButton);
        titlePanel.add(Box.createHorizontalStrut(20));

        JTextField searchDescription = new JTextField();
        searchDescription.setAlignmentX(Component.CENTER_ALIGNMENT);
        searchDescription.setHorizontalAlignment(JTextField.CENTER); // Center-align the text horizontally
        Dimension searchDimension = new Dimension(350,150);
        searchDescription.setMinimumSize(searchDimension);
        searchDescription.setMaximumSize(searchDimension);
        searchDescription.setFont(new Font("Arial", Font.PLAIN, 24));
        searchDescription.setBackground(greyButtonColor);
        searchDescription.setForeground(Color.BLACK);
        searchDescription.setBorder(customBorder);

        JButton searchButton = new JButton("Search");
        searchButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        searchButton.setFont(new Font("Arial", Font.PLAIN, 18));
        searchButton.setBackground(greyButtonColor);
        searchButton.setForeground(Color.BLACK);
        searchButton.setBorder(customBorder);
        searchButton.setMaximumSize(new Dimension(100,50));
        searchButton.setMinimumSize(new Dimension(100,50));

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
                frame.setSize(475, 500);
                frame.setLocationRelativeTo(null);

            }
        });

        searchProductDescriptionPanel.add(Box.createVerticalStrut(20));
        searchProductDescriptionPanel.add(titlePanel);
        searchProductDescriptionPanel.add(Box.createVerticalStrut(20));
        searchProductDescriptionPanel.add(searchDescription);
        searchProductDescriptionPanel.add(Box.createVerticalStrut(20));
        searchProductDescriptionPanel.add(searchButton);
        searchProductDescriptionPanel.add(Box.createVerticalStrut(20));

        return searchProductDescriptionPanel;
    }

    private JPanel createSearchProductDescriptionsPanel(String productDescription) {
        JPanel productDescriptions = new JPanel();
        productDescriptions.setBackground(customColor);
        productDescriptions.setLayout(new BoxLayout(productDescriptions, BoxLayout.Y_AXIS));
        productDescriptions.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel titlePanel = new JPanel();
        titlePanel.setOpaque(false);
        titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.X_AXIS));
        titlePanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        Dimension titlePanelDimension = new Dimension(500,50);
        titlePanel.setMinimumSize(titlePanelDimension);
        titlePanel.setMaximumSize(titlePanelDimension);

        JLabel title = new JLabel("Searched Products by Description");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        title.setFont(new Font("Arial", Font.BOLD, 16));

        titlePanel.add(Box.createHorizontalStrut(20));
        titlePanel.add(createBackToMenuButton());
        titlePanel.add(Box.createHorizontalStrut(20));
        titlePanel.add(title);
        titlePanel.add(Box.createHorizontalStrut(20));
        titlePanel.add(createBackToAccountPageButton());
        titlePanel.add(Box.createHorizontalStrut(20));

        JPanel allProductsPanel = new JPanel();
        allProductsPanel.setOpaque(false);
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
        jsp.setMaximumSize(new Dimension(400,300));
        jsp.setMinimumSize(new Dimension(400,300));
        jsp.getViewport().setBackground(greyButtonColor);
        jsp.setBorder(customBorder);

//        // changes scrollpane to have white background
//        JViewport viewport = new JViewport();
//        viewport.setBackground(Color.WHITE);
//        jsp.setViewport(viewport);

        JPanel bottomPanel = new JPanel();
        bottomPanel.setOpaque(false);
        JLabel instructions = new JLabel("Select a Product to View More Info");
        instructions.setFont(new Font("Arial", Font.PLAIN, 18));
        instructions.setAlignmentX(Component.CENTER_ALIGNMENT);
        bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.Y_AXIS));
        bottomPanel.add(instructions);

        productDescriptions.add(Box.createVerticalStrut(20));
        productDescriptions.add(titlePanel);
        productDescriptions.add(Box.createVerticalStrut(20));
        productDescriptions.add(jsp);
        productDescriptions.add(Box.createVerticalStrut(20));
        productDescriptions.add(bottomPanel);
        productDescriptions.add(Box.createVerticalStrut(10));

        return productDescriptions;
    }


    private JLabel selectedSellerLabel;

    private JButton createBackToMenuButton() {
        JButton backButton = new JButton("<");
        Font largeFont = new Font("Arial", Font.PLAIN, 18);
        backButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        backButton.setFont(largeFont);
        backButton.setBackground(greyButtonColor);
        backButton.setForeground(Color.BLACK);
        backButton.setBorder(customBorder);

        Dimension  backDimension = new Dimension(60,30);
        backButton.setMinimumSize(backDimension);
        backButton.setMaximumSize(backDimension);

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
        accountButton.setFont(new Font("Arial", Font.PLAIN, 12));
        accountButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        accountButton.setBackground(greyButtonColor);
        accountButton.setForeground(Color.BLACK);
        accountButton.setBorder(customBorder);

        Dimension accountDimension = new Dimension(70,30);
        accountButton.setMinimumSize(accountDimension);
        accountButton.setMaximumSize(accountDimension);
        accountButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "Account Options");
                frame.setSize(400, 375);
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