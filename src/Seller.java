import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 Project 4 -- Seller

 The Seller Class

 Seller class provides a plethora of functionalities. Sellers can manage stores, view statistics,
 create, edit, or delete stores, and log out seamlessly. We handle file I/O for reading and writing seller data,
 displaying store and customer statistics, and providing a user-friendly interface for various operations.
 The class provides a detailed interface to display product and customer sales information display,
 sorting options, and store creation and management.

 @author  Adeetya, Jordan, Keegan, Natalie, and Robert, Lab Section 39

 @version November 13th, 2023


 */

public class Seller extends Account implements Serializable {

    private ArrayList<Store> sellerStores;
    private static final String PRODUCT_SALES_SORT_PROMPT =
            """
            What would you like to do? (#)
            1. Sort by quantity sold - high to low
            2. Sort by revenue generated - high to low
            3. Sort by quantity sold - low to high
            4. Sort by revenue generated - low to high
            """;

    // constructor
    public Seller(String name, String email, String username, ArrayList<Store> sellerStores) {
        super(name, email, username);
        this.sellerStores = sellerStores;
    }

    //A Seller constructor with the parameters Account and Store arraylist to create a Seller
    public Seller(Account account, ArrayList<Store> sellerStores) {
        super(account);
        this.sellerStores = sellerStores;
    }

    //The removeSeller method takes in the Seller email as a parameter and searches a Seller_Data file for that email and deletes that Seller
    public static void removeSeller(String email) {
        ArrayList<Seller> sellerList = new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(Market.SELLER_DATA))) {
            Seller test;
            while (true) {
                try {
                    test = (Seller) ois.readObject();
                    sellerList.add(test);
                } catch (EOFException | ClassNotFoundException e) {
                    break;
                }
            }

            int sellerIndex = -1;
            for (int i = 0; i < sellerList.size(); i++) {
                String sellerEmail = sellerList.get(i).getEmail();
                if (email.equals(sellerEmail)) {
                    sellerIndex = i;
                    break;
                }
            }

            if (sellerIndex != -1) {
                System.out.println("Seller removed");
                sellerList.remove(sellerIndex);
            }

            if (!sellerList.isEmpty()) {
                try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(Market.SELLER_DATA))) {
                    for (Seller seller : sellerList) {
                        oos.writeObject(seller);
                    }
                }
            } else {
                new FileOutputStream(Market.SELLER_DATA).close();
            }

        } catch (EOFException e) {
            System.out.println("Seller removed");
        } catch (IOException e) {
            System.out.println("Customer data read error.");
        }
    }

    // a method that returns a list of all the products from a seller
    public static ArrayList<Product> getAllProducts() {
        ArrayList<Seller> sellers = Seller.returnAllSellers();
        ArrayList<Product> allProducts = new ArrayList<>();
        for (Seller seller : sellers) {
            for (Store store : seller.getStores()) {
                allProducts.addAll(store.getProducts());
            }
        }
        return allProducts;
    }

    // fetchSeller method to return a seller object based on account signed into
    public static Seller fetchSeller(Account account) {

        Seller seller;

        System.out.println("Fetching seller data...");

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(Market.SELLER_DATA))) {

            Seller test;

            while (true) {

                try {

                    test = (Seller) ois.readObject();
                    if (test.getEmail().equals(account.getEmail())) {
                        seller = test;
                        System.out.println("Seller found!");
                        return seller;
                    }

                } catch (EOFException | ClassNotFoundException e) {

                    System.out.println("Seller not found");
                    System.out.println("Initializing new account...");
                    return new Seller(account, new ArrayList<>());

                }

            }

        } catch (EOFException e) {

            System.out.println("Seller not found");
            System.out.println("Initializing new account...");
            return new Seller(account, new ArrayList<>());

        } catch (IOException e) {

            System.out.println("Failed to fetch seller data.");
            return new Seller(account, new ArrayList<>());

        }

    }


    // Equals method for seller to check if two sellers are equal
    public boolean equals(Seller seller) {
        return seller.getUsername().equals(this.getUsername()) && seller.getEmail().equals(this.getEmail());
    }

    // Added seller stores update method to keep seller data accurate
    public static void updateSellerData(Seller seller) {

        ArrayList<Seller> storedSellers = new ArrayList<>();

        boolean initialEntry = false;

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(Market.SELLER_DATA))) {

            Seller test;

            while (true) {
                try {
                    test = (Seller) ois.readObject();
                    storedSellers.add(test);
                } catch (EOFException | ClassNotFoundException e) {
                    break;
                }
            }

        } catch (EOFException e) {

            initialEntry = true;

        } catch (IOException e) {

            System.out.println("Initial data file read error.");
            return;

        }

        String sellerEmail = seller.getEmail();
        boolean foundSeller = false;
        for (Seller entry : storedSellers) {
            if (entry.getEmail().equals(sellerEmail)) {
                entry.sellerStores = seller.sellerStores;
                foundSeller = true;
            }
        }

        if (!foundSeller) {
            storedSellers.add(seller);
        }

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(Market.SELLER_DATA))) {

            if (initialEntry) {
                oos.writeObject(seller);
            } else {
                for (Seller entry : storedSellers) {
                    oos.writeObject(entry);
                }
            }

        } catch (IOException e) {
            System.out.println("Error occurred writing updated seller data");
        }

    }

    // Keegan - A method that returns all stored sellers as an arraylist

    public static ArrayList<Seller> returnAllSellers() {

        ArrayList<Seller> sellers = new ArrayList<>();

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(Market.SELLER_DATA))) {

            Seller test;

            while (true) {

                try {

                    test = (Seller) ois.readObject();
                    sellers.add(test);

                } catch (EOFException | ClassNotFoundException e) {

                    break;

                }

            }

            return sellers;

        } catch (EOFException e) {

            return sellers;

        } catch (IOException e) {

            System.out.println("Error occurred trying to fetch all sellers.");
            return sellers;

        }

    }

    // List Stores method to print all Store names and return a Store array
    public static void listDetailedStores(Seller seller) {
        ArrayList<Store> sellerStores = seller.getStores();
        if (sellerStores.isEmpty()) {
            System.out.println("You have no stores!");
            return;
        }
        int counter = 1;
        for (Store store : sellerStores) {
            System.out.printf("%d. %s:\n", counter, store.getName());
            for (Product product : store.getProducts()) {
                System.out.printf("Product: %s, Quantity: %d, Description: %s, Price: $%.2f\n", product.getName(), product.getQuantity(), product.getDescription(), product.getPrice());
            }
            counter++;
        }
    }

    // A method to list store statistics for a specific seller store
    public static void storeStatistics(Seller seller, Scanner scanner) {

        ArrayList<Store> sellerStores = seller.getStores();

        if (sellerStores.isEmpty()) {
            System.out.println("You have no stores!");
            return;
        }

        while (true) {

            System.out.print(
                    """
                    What would you like to do?
                    1. View all store sales
                    2. View specific store statistics
                    3. Customer shopping carts
                    4. Return to main page
                    """
            );
            int storeStatisticsSelection = Market.validatePromptInput(4, scanner);


            switch (storeStatisticsSelection) {

                case 1 -> Seller.printTotalSellerStoreStats(seller);

                case 2 -> Seller.viewSpecificStoreStatistics(seller, scanner);

                case 3 -> Seller.viewCustomerShoppingCarts(seller);

                case 4 -> {


                    return;


                }

            }

        }

    }

    private static void viewCustomerShoppingCarts(Seller seller) {

        ArrayList<ShoppingCart> allShoppingCarts = Customer.returnAllShoppingCarts();

        System.out.println("Customer shopping carts: ");

        for (Store store : seller.getStores()) {

            System.out.printf("%s:\n", store.getName());

            for (Product productInStore : store.getProducts()) {

                int amountInShoppingCarts = 0;

                for (ShoppingCart shoppingCart : allShoppingCarts) {

                    for (Product productInShoppingCart : shoppingCart.getProductList()) {

                        if (productInShoppingCart.equals(productInStore)) {

                            amountInShoppingCarts++;

                        }

                    }

                }

                System.out.printf("Product %s is/are in %d customer's shopping carts\n", productInStore.getName(), amountInShoppingCarts);

            }

        }

    }

    private static void viewSpecificStoreStatistics(Seller seller, Scanner scanner) {

        while (true) {

            System.out.print(
                    """
                    What would you like to do?
                    1. View a store's statistics
                    2. Return to view store statistics
                    """
            );
            int storeStatisticsChoice = Market.validatePromptInput(2, scanner);

            if (storeStatisticsChoice == 2) {

                return;

            }

            System.out.println("Which store would you like to see statistics for? (#)");
            Store.printSellerStores(seller);
            int storeChoice = Market.validatePromptInput(seller.getStores().size(), scanner);
            Store sellerStore = seller.getStores().get(storeChoice - 1);

            System.out.print(
                    """
                            Which statistics would you like to see?
                            1. Customer Sales
                            2. Product Sales
                            """
            );

            int statChoice = Market.validatePromptInput(2, scanner);

            switch (statChoice) {

                case 1 -> Seller.printCustomerSales(sellerStore, scanner);

                case 2 -> Seller.printProductSales(sellerStore, scanner);

            }

        }

    }

    // A method to handle printing the product sales statistic of a store
    private static void printProductSales(Store store, Scanner scanner) {

        if (store.getProducts().isEmpty()) {
            System.out.println("Your store doesn't have products yet!");
            return;
        }

        System.out.println("Would you like to sort product sales? (yes or no)");
        String answer = Market.validateYesOrNo(scanner);

        switch (answer) {

            case "yes" -> {


                System.out.print(PRODUCT_SALES_SORT_PROMPT); // 1. Sort by quantity sold - high to low, 2. Sort by revenue generated - high to low, 3. Sort by quantity sold - low to high, 4. Sort by revenue generated - low to high
                int sortChoice = Market.validatePromptInput(4, scanner);

                switch (sortChoice) {

                    case 1 -> { // Quantity sold - high to low

                        ArrayList<Product> sortedProducts = Product.sortByQuantitySold(store.getProducts(), "high");

                        for (Product product : sortedProducts) {
                            System.out.printf("Product Name: %s, Quantity Sold: %s, Revenue Generated: $%.2f\n", product.getName(), product.getQuantitySold(), product.getRevenueGenerated());
                        }

                    }

                    case 2 -> { // Revenue generated - high to low

                        ArrayList<Product> sortedProducts = Product.sortByRevenueGenerated(store.getProducts(), "high");

                        for (Product product : sortedProducts) {
                            System.out.printf("Product Name: %s, Quantity Sold: %s, Revenue Generated: $%.2f\n", product.getName(), product.getQuantitySold(), product.getRevenueGenerated());
                        }

                    }

                    case 3 -> {

                        ArrayList<Product> sortedProducts = Product.sortByQuantitySold(store.getProducts(), "low");

                        for (Product product : sortedProducts) {
                            System.out.printf("Product Name: %s, Quantity Sold: %s, Revenue Generated: $%.2f\n", product.getName(), product.getQuantitySold(), product.getRevenueGenerated());
                        }

                    }

                    case 4 -> {

                        ArrayList<Product> sortedProducts = Product.sortByRevenueGenerated(store.getProducts(), "low");

                        for (Product product : sortedProducts) {
                            System.out.printf("Product Name: %s, Quantity Sold: %s, Revenue Generated: $%.2f\n", product.getName(), product.getQuantitySold(), product.getRevenueGenerated());
                        }

                    }

                }

            }
            case "no" -> {

                for (Product product : store.getProducts()) {
                    System.out.printf("Product Name: %s, Quantity Sold: %s, Revenue Generated: $%.2f\n", product.getName(), product.getQuantitySold(), product.getRevenueGenerated());
                }
                return;
            }
            default -> System.out.println("Invalid choice. Please try again by entering yes or no. ");
        }

    }

    // Keegan - A method to handle printing the customer sales statistic of a store

    private static void printCustomerSales(Store store, Scanner scanner) {

        if (store.getPurchasers().isEmpty()) {
            System.out.println("Customers have not purchased from this store yet!");
            return;
        }

        System.out.println("Would you like to sort customer sales? (yes or no)");
        String answer = Market.validateYesOrNo(scanner);

        switch (answer) {
            case "yes" -> {


                ArrayList<Purchaser> sortedPurchasers = Purchaser.sortPurchasers(store, scanner);

                System.out.printf("%s's customer sales: \n", store.getName());

                for (Purchaser purchaser : sortedPurchasers) {

                    System.out.printf("Product purchased: %s, Customer: %s, Quantity Purchased: %d, Revenue Generated: $%.2f\n", purchaser.getNameOfProductPurchased(), purchaser.getUsername(), purchaser.getQuantityPurchased(), purchaser.getRevenueGenerated());

                }

              
            }
            
            case "no" -> {


                System.out.printf("%s's customer sales: ", store.getName());

                for (Purchaser purchaser : store.getPurchasers()) {
                    System.out.printf("Product Purchased: %s, Customer: %s, Quantity Purchased: %d, Revenue Generated: $%.2f\n", purchaser.getUsername(), purchaser.getUsername(), purchaser.getQuantityPurchased(), purchaser.getRevenueGenerated());

                }


            }

        }

    }

    // A create store method that handles prompts and creating a store that's added to a seller's store list
    public static void createStore(Seller seller, Scanner scanner) {
        ArrayList<Purchaser> purchasers = new ArrayList<>();
        ArrayList<Product> products = new ArrayList<>();

        System.out.println("Enter the name of the store: ");
        String storeName = scanner.nextLine();

        Store newStore = new Store(storeName, seller, purchasers, products);

        while (true) {

            System.out.println("Would you like to add products? (yes or no)");

            String productAddAnswer = scanner.nextLine();
            productAddAnswer = productAddAnswer.toLowerCase();

            switch(productAddAnswer) {

                case "yes" -> {
                    products = returnProducts(newStore, scanner);
                    newStore.setProducts(products);
                    seller.getStores().add(newStore);
                    Seller.updateSellerData(seller);
                    System.out.println("Store successfully created");
                    return;
                }
                case "no" -> {
                    seller.getStores().add(new Store(storeName, seller, purchasers, products));
                    Seller.updateSellerData(seller);
                    System.out.println("Store successfully created");
                    return;
                }
                default -> System.out.println("Invalid choice. Please try again by entering yes or no.");
            }
        }
    }

    public static void viewCustomerMessages(Seller seller) {

        ArrayList<String> messages = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(Market.CUSTOMER_MESSAGES))) {

            String line;

            while ((line = br.readLine()) != null) {

                // customerEmail,customerUsername,sellerEmail,message
                String[] messageData = line.split(",");
                String sellerEmail = messageData[2];

                if (seller.getEmail().equals(sellerEmail)) {

                    messages.add(String.format("Customer: %s, Message: %s", messageData[1], messageData[3]));

                }

            }

            if (messages.isEmpty()) {

                System.out.println("You do not have any customer messages yet");

            }

            System.out.println("Customer messages: ");

            for (String message : messages) {

                System.out.println(message);

            }

        } catch (IOException e) {

            System.out.println("Failed to read customer messages");

        }


    }

    //A method that takes in the Store and a Scanner for parameters and returns an ArrayList of the products created by the Seller
    public static ArrayList<Product> returnProducts(Store store, Scanner scanner) {

        System.out.println("""
                    How would you like to add products to your store? (#)
                    1. Manual entry
                    2. CSV Entry (Import file must exist)""");
        int addProductsChoice = Market.validatePromptInput(2, scanner);

        switch (addProductsChoice) {

            case 1 -> {



                return manualProductEntry(store, scanner);



            }

            case 2 -> {



                return CSVProductEntry(store, scanner);



            }

        }

        return new ArrayList<>();

    }

    // a method that writes the list of products a store to a csv file
    private static ArrayList<Product> CSVProductEntry(Store store, Scanner scanner) {

        ArrayList<Product> products = new ArrayList<>();

        System.out.println("Required file format: productName,productPrice,productQuantity,productDescription for each line");
        System.out.println("Enter the name of the file you'd like to import products from (File must exist): ");
        String fileName = scanner.nextLine();

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {

            String line;
            int counter = 0;

            while ((line = br.readLine()) != null) {

                counter++;

                String[] productData = line.split(",");

                if (productData.length != 4) {

                    System.out.printf("Entry %d is incorrectly formatted. Skipping import\n", counter);
                    continue;

                }

                String productName = productData[0];
                double productPrice;
                int productQuantity;
                String productDescription = productData[3];

                try {

                    productPrice = Double.parseDouble(productData[1]);

                } catch (NumberFormatException e) {

                    System.out.printf("Entry %d product price is not a valid input...\nSetting product price to 0. See edit store to fix\n", counter);
                    productPrice = 0;

                }

                try  {

                    productQuantity = Integer.parseInt(productData[2]);

                } catch (NumberFormatException e) {

                    System.out.printf("Entry %d product quantity is not a valid input...\nSetting product quantity to 0. See edit store to fix\n", counter);
                    productQuantity = 0;

                }

                products.add(new Product(productName, productPrice, productQuantity, productDescription, store));

            }

            return products;

        } catch (IOException e) {

            System.out.println("Error occurred while trying to read from product CSV file...\nSetting store products to none. See edit store to fix");
            return products;

        }

    }

    // a method that allows sellers to manually import products into their stores
    private static ArrayList<Product> manualProductEntry(Store store, Scanner scanner) {


            System.out.println("How many products would you like to create? ");
            int numberOfProducts = Market.validatePromptInput(Integer.MAX_VALUE, scanner);
            ArrayList<Product> products = new ArrayList<>();

            for (int i = 1; i < numberOfProducts + 1; i++) {

                products.add(Product.generateProduct(store, scanner, i));

            }

        return products;

    }

    // a method that allows sellers to modify their stores
    public static void editStore(Seller seller, Scanner scanner) {

        ArrayList<Store> sellerStores = seller.getStores();

        if (sellerStores.isEmpty()) {

            System.out.println("You have no stores!");
            return;

        }

        System.out.println("Here is a list of your stores: ");
        Store.printSellerStores(seller);

        while (true) {

            System.out.println("""
                    What would you like to do? (#)
                    1. Edit a store
                    2. Export a store's products to a CSV file
                    3. Return to main page""");
            int editOptionChoice = Market.validatePromptInput(3, scanner);

            switch (editOptionChoice) {

                case 1 -> { // Edit a store


                    Seller.editStoreInterface(seller, scanner);


                }

                case 2 -> { // Export a store's products to a CSV file



                    Seller.exportProductsInterface(seller, scanner);


                }
                case 3 -> {

                    return;

                }
            }
        }

    }

    private static void exportProductsInterface(Seller seller, Scanner scanner) {

        System.out.println("Which store would you like to export products from? (#)");
        Store.printSellerStores(seller);
        int storeChoice = Market.validatePromptInput(seller.getStores().size(), scanner);
        Store selectedStore = seller.getStores().get(storeChoice - 1);

        String fileName = String.format("%s_%s_products.txt", seller.getUsername(), selectedStore.getName());

        System.out.printf("Exporting products to %s...\n", fileName);

        try (PrintWriter pw = new PrintWriter(new FileWriter(fileName, false), true)) {

            for (Product product : selectedStore.getProducts()) {

                // name,price,quantity,description
                pw.printf("%s,%.2f,%d,%s\n", product.getName(), product.getPrice(), product.getQuantity(), product.getDescription());

            }

            System.out.println("Products successfully exported!");

        } catch (IOException e) {

            System.out.println("Error occurred writing to export file");

        }


    }

    private static void editStoreInterface(Seller seller, Scanner scanner) {

        System.out.println("Which store would you like to edit? (#)");
        Store.printSellerStores(seller);
        int storeChoice = Market.validatePromptInput(seller.getStores().size(), scanner);
        Store selectedStore = seller.getStores().get(storeChoice - 1);

        while (true) {

            System.out.println("""
                                        What would you like to do? (#)
                                        1. Edit store Name
                                        2. Edit store Products
                                        3. Return to edit store""");
            int editChoice = Market.validatePromptInput(3, scanner);

            switch (editChoice) {

                case 1 -> {


                    System.out.printf("Current Store Name: %s\n", selectedStore.getName());
                    System.out.println("Enter The New Name of Your Store: ");
                    String storeNewName = scanner.nextLine();
                    selectedStore.setName(storeNewName);
                    System.out.println("Store name successfully reassigned!");


                }

                case 2 -> {


                    if (selectedStore.getProducts().isEmpty()) {
                        System.out.printf("Store %s currently has no products!", selectedStore.getName());
                        continue;
                    }
                    Store.editProducts(selectedStore, scanner);


                }

                case 3 -> {

                    return;

                }

            }

        }

    }

    // returns a list of stores owned by sellers
    public ArrayList<Store> getStores() {
        return this.sellerStores;
    }

    // Keegan - Edited for user work flow

    public static void deleteStore(Seller seller, Scanner scanner){
        if(seller.getStores().isEmpty()) {
            System.out.println("You do not have any stores to delete!");
            return;
        }

        int counter = 1;
        System.out.println("Here is a list of your stores: ");
        for (Store store : seller.getStores()) {
            System.out.printf("%d. %s\n", counter, store.getName());
            counter++;
        }

        while (true) {
            System.out.printf("Which store would you like to delete? (1-%d)\n", seller.getStores().size());
            int choice = scanner.nextInt();
            scanner.nextLine();

            if (choice > 0 && choice <= seller.getStores().size()) {
                Store sellerStore = seller.getStores().get(choice - 1);

                while(true) {
                    System.out.println("Are you sure? (yes or no)");
                    String answer = scanner.nextLine();
                    answer = answer.toLowerCase();

                    switch (answer) {
                        case "yes" -> {
                            ArrayList<Product> productsInCart = new ArrayList<>();
                            ArrayList<Customer> allCustomers = Customer.returnAllCustomers();
                            for (Customer customer : allCustomers) {
                                productsInCart.clear();
                                for (Product product : customer.getShoppingCart().getProductList()) {
                                    if (product.getStore().equals(sellerStore)) {
                                        productsInCart.add(product);
                                    }
                                }

                                for (Product product : productsInCart) {
                                    customer.getShoppingCart().getProductList().remove(product);
                                }
                                Customer.updateCustomerData(customer);
                            }
                            seller.getStores().remove(sellerStore);
                            System.out.printf("Store %s has been successfully deleted!\n", sellerStore.getName());
                            return;
                        }
                        case "no" -> {
                            return;
                        }
                        default -> System.out.println("Invalid choice. Please try again by entering yes or no.");
                    }
                }
            } else {
                System.out.println("Invalid choice. Please try again.");
            }
        }
    }


    // Method that prints list of total sales from each store and all stores combined while providing info on the customers
    public static void printTotalSellerStoreStats(Seller seller){

        ArrayList<Store> sellerStores = seller.getStores();

        double totalRevenue = 0;

        for (Store store : sellerStores) {
            double storeRevenue = 0;
            for (Product product : store.getProducts()) {
                storeRevenue += product.getRevenueGenerated();
            }
            totalRevenue += storeRevenue;
            System.out.printf("%s total revenue: $%.2f\n", store.getName(), storeRevenue);
        }

        System.out.printf("Total revenue from all stores: $%.2f\n", totalRevenue);
    }

}
