import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 Project 4 -- Customer

 In our project, we've developed a comprehensive online market application with various classes
 collaborating to provide a rich and dynamic user experience. We designed this system to allow
 users to create accounts, navigate through a variety of features, and interact with the market's
 extensive functionalities.


 @author , Lab Section 39

 @version November 13th, 2023


 */

public class Customer extends Account implements Serializable {
    private ArrayList<PurchasedProduct> previouslyPurchasedProducts;
    private ShoppingCart shoppingCart;

    // What a user sees when they enter list seller stores
    private static final String LIST_SELLER_STORES_PROMPT =
            """
            What would you like to do?
            1. See stores by products sold
            2. See stores by previous account purchases
            3. Return to see sellers
            """;

    // What a user sees when they enter see sellers
    private static final String SEE_SELLERS_PROMPT =
            """
            What would you like to do?
            1. List seller stores
            2. Contact sellers
            3. Return to main page
            """;

    // What a user sees when they enter search
    private static final String SEARCH_PROMPT =
            """
            What would you like to do?
            1. Search by name
            2. Search by store
            3. Search by description
            4. Return to main page
            """;

    // What a user sees when they enter see all listings
    private static final String SORT_PROMPT =
            """
            What would you like to do?
            1. See all listings
            2. Sort listings
            """;

    // What a user sees when they enter a product's page
    private static final String PAGE_PROMPT =
            """
            What would you like to do?
            1. Purchase product
            2. Add to shopping cart
            3. Return to listings
            """;

    // What a user sees when they enter a listing page while searching
    private static final String SEARCH_LISTING_PROMPT =
            """
            What would you like to do?
            1. Go to a product's page
            2. Return to search
            """;

    // What a user sees when they enter a listing page while viewing all listings
    private static final String LISTING_PROMPT =
            """
            What would you like to do?
            1. Go to a product's page
            2. Return to main page
            """;

    // What a user sees when they enter previously purchased products
    private static final String PURCHASED_PRODUCTS_PROMPT =
            """
            What would you like to do?
            1. Export purchase history to CSV
            2. Return to my account
            """;

    // What a user sees when they enter my account
    private static final String MY_ACCOUNT_PROMPT =
            """
            What would you like to do?
            1. View my previously purchased products
            2. View my shopping cart
            3. Return to main page
            """;

    // What a user sees when they enter my shopping cart
    private static final String SHOPPING_CART_PROMPT =
            """
            What would you like to do?
            1. Checkout
            2. Remove Product
            3. Return to my account
            """;


    public Customer(String name, String email, String username, ArrayList<PurchasedProduct> previouslyPurchasedProducts, ShoppingCart shoppingCart) {
        super(name, email, username);
        this.previouslyPurchasedProducts = previouslyPurchasedProducts;
        this.shoppingCart = shoppingCart;
    }


    // Keegan - Made an account constructor
    public Customer (Account account, ArrayList<PurchasedProduct> previouslyPurchasedProducts, ShoppingCart shoppingCart) {
        super(account);
        this.previouslyPurchasedProducts = previouslyPurchasedProducts;
        this.shoppingCart = shoppingCart;
    }

    // a method for fetching stored and returning customer data for a specific customer account
    public static Customer fetchCustomer(Account account) {

        Customer customer;

        System.out.println("Fetching customer data...");

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(Market.CUSTOMER_DATA))) {

            Customer test;

            while (true) {

                try {

                    test = (Customer) ois.readObject();
                    if (test.getEmail().equals(account.getEmail())) {
                        customer = test;
                        // System.out.println(customer.getShoppingCart().getProductList().get(0).getName());
                        System.out.println("Customer found!");
                        return customer;
                    }

                } catch (EOFException | ClassNotFoundException e) {

                    System.out.println("Customer not found");
                    System.out.println("Initializing new account...");
                    return new Customer(account, new ArrayList<>(), new ShoppingCart());

                }

            }

        } catch (EOFException e) {

            System.out.println("Customer not found");
            System.out.println("Initializing new account...");
            return new Customer(account, new ArrayList<>(), new ShoppingCart());

        } catch (IOException e) {

            System.out.println("Failed to fetch customer data.");
            return new Customer(account, new ArrayList<>(), new ShoppingCart());

        }

    }

    // A method for updating stored customer data.
    public static void updateCustomerData(Customer customer) {

        ArrayList<Customer> storedCustomers = new ArrayList<>();

        boolean initialEntry = false;

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(Market.CUSTOMER_DATA))) {

            Customer test;

            while (true) {

                try {

                    test = (Customer) ois.readObject();
                    storedCustomers.add(test);

                } catch (EOFException | ClassNotFoundException e) {

                    break;

                }

            }

        } catch (EOFException e) {

            initialEntry = true;

        } catch (IOException e) {

            System.out.println("Failed to update customer data.");
            return;

        }

        String customerEmail = customer.getEmail();
        boolean foundSeller = false;
        for (Customer entry : storedCustomers) {

            if (entry.getEmail().equals(customerEmail)) {

                entry.shoppingCart = customer.shoppingCart;
                entry.previouslyPurchasedProducts = customer.previouslyPurchasedProducts;
                foundSeller = true;
                break;

            }

        }

        if (!foundSeller) {

            storedCustomers.add(customer);

        }

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(Market.CUSTOMER_DATA))) {

            if (initialEntry) {

                oos.writeObject(customer);

            } else {

                for (Customer entry : storedCustomers) {

                    oos.writeObject(entry);

                }

            }

        } catch (IOException e) {

            System.out.println("Error occurred writing updated customer data");

        }

    }

    // A method to create a listing page for an array of products
    public static void listingPage(ArrayList<Product> products, Scanner scanner, Customer customer, boolean searched) {
        boolean inListings = true;

        while (inListings) {

            System.out.println("Listings: ");

            Product.listProducts(products);

            if (searched) {

                System.out.print(SEARCH_LISTING_PROMPT);

            }else {

                System.out.print(LISTING_PROMPT); // 1. Go to a product's page, 2. Return to main page

            }


            int listingPromptInput = Market.validatePromptInput(2, scanner);

            switch (listingPromptInput) {

                case 1 -> {


                    System.out.println("Select the associated #. of the product you'd like to visit"); // 1-Amount of products listed
                    int intProductSelected = Market.validatePromptInput(products.size(), scanner);

                    Product productSelected = products.get(intProductSelected - 1);

                    boolean inProductPage = true;
                    while (inProductPage) {
                        Product.printProductPage(productSelected);
                        System.out.print(PAGE_PROMPT); // 1. Purchase product, 2. Add to shopping cart, 3. Return to listings
                        int pagePromptChoice = Market.validatePromptInput(3, scanner);

                        switch (pagePromptChoice) {
                            case 1 -> Customer.purchaseProduct(customer, productSelected, scanner);
                            case 2 -> Customer.addToShoppingCart(customer, productSelected);
                            case 3 -> inProductPage = false;
                        }
                    }
                  

                }

                case 2 -> inListings = false;

            }

        }
    }

    // Keegan -  Listings method to handle see listings option from customer main page
   public static void listings(Customer customer, Scanner scanner) {

       ArrayList<Product> allProducts = Seller.getAllProducts();

       if (allProducts.isEmpty()) {

           System.out.println("There are currently no seller listed products");
           return;

       }

       System.out.print(SORT_PROMPT); // 1. See all listings, 2. Sort listings
       int sortPromptInput = Market.validatePromptInput(2, scanner);

       switch (sortPromptInput) {

           case 1 -> listingPage(allProducts, scanner, customer, false);

           case 2 -> {


               ArrayList<Product> sortedProducts = Product.sortProducts(allProducts, scanner);
               listingPage(sortedProducts, scanner, customer, false);


           }

       }

   }

   // A method to handle adding an item to a customer's shopping cart
    public static void addToShoppingCart(Customer customer, Product product) {
        if (!inShoppingCart(customer, product)) {
            customer.shoppingCart.getProductList().add(product);
            Customer.updateCustomerData(customer);
            System.out.printf("%s has been added to your shopping cart.\n", product.getName());
        }
    }

    //
    private  static void purchaseProduct(Customer customer, Product product, Scanner scanner) {

        if (product.getQuantity() == 0) {
            System.out.println("This product has 0 quantity available to purchase.");
            return;
        }

        System.out.println("How many would you like to purchase?");
        int quantityToPurchase = Market.validatePromptInput(product.getQuantity(), scanner);
        product.updateProduct(quantityToPurchase);

        Purchaser newPurchaser = new Purchaser(customer.getUsername(), product.getName(), quantityToPurchase, product.getPrice() * quantityToPurchase);
        product.getStore().getPurchasers().add(newPurchaser);

        Customer.updatePurchasedProducts(new PurchasedProduct(product, quantityToPurchase), customer);
        Customer.addToPurchasedProducts(customer, product, quantityToPurchase);

        Seller.updateSellerData(product.getStore().getSellerOwner());
        Customer.updateCustomerData(customer);

        System.out.println("Product successfully purchased.");

    }

    private static void updatePurchasedProducts(PurchasedProduct purchasedProduct, Customer customer) {

        ArrayList<PurchasedProduct> purchasedProducts = customer.getPreviouslyPurchasedProducts();

        for (PurchasedProduct purchasedProduct1 : purchasedProducts) {

            if (purchasedProduct1.getPurchasedProduct().equals(purchasedProduct.getPurchasedProduct())) {

                purchasedProduct1.addAmountPurchased(purchasedProduct.getAmountPurchased());
                return;
            }

        }

        customer.getPreviouslyPurchasedProducts().add(purchasedProduct);

    }

    // A method to add a customer's purchase to a text file for data storage

    private static void addToPurchasedProducts(Customer customer, Product product, int quantityPurchased) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(Market.CUSTOMER_PURCHASES, true), true)) {
            double amountSpent = product.getPrice() * quantityPurchased;
            Seller seller = product.getStore().getSellerOwner();
            // customerEmail,sellerEmail,customerUsername,sellerUsername,productName,productDescription,storeName,productPrice,quantityPurchased,amountSpent
            String purchaseLine = String.format("%s,%s,%s,%s,%s,%s,%s,%.2f,%d,%.2f", customer.getEmail(), seller.getEmail(), customer.getUsername(), seller.getUsername(), product.getName(), product.getDescription(), product.getStore().getName(), product.getPrice(), quantityPurchased, amountSpent);
            pw.println(purchaseLine);
        } catch (IOException e) {
            System.out.println("Customer purchases read error.");
        }
    }

    public static ArrayList<String> returnCustomerPurchases(Customer customer) {

        ArrayList<String> purchases = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(Market.CUSTOMER_PURCHASES))) {

            String line;

            while ((line = br.readLine()) != null) { // Returns customer purchase lines to arraylist based on email

                String customerEmail = line.substring(0, line.indexOf(","));
                if (customerEmail.equals(customer.getEmail())) {

                    purchases.add(line);

                }

            }

            return purchases;

        } catch (IOException e) {

            System.out.println("Customer purchases read error.");
            return purchases;

        }

    }

    public static ArrayList<Customer> returnAllCustomers() {

        ArrayList<Customer> customers = new ArrayList<>();

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(Market.CUSTOMER_DATA))) {

            Customer test = null;

            while (true) {

                try {

                    test = (Customer) ois.readObject();
                    customers.add(test);

                } catch (EOFException | ClassNotFoundException e) {

                    break;

                }

            }

            return customers;

        } catch (EOFException e) {
            return customers;
        } catch (IOException e) {
            System.out.println("Error occurred reading customer data");
            return customers;
        }

    }

    public ArrayList<PurchasedProduct> getPreviouslyPurchasedProducts() {
        return previouslyPurchasedProducts;
    }

    // Keegan - A method to check if the product is already inside a customer's shopping cart
    private static boolean inShoppingCart(Customer customer, Product product) {

        ShoppingCart customerShoppingCart = customer.getShoppingCart();
        for (Product product1 : customerShoppingCart.getProductList()) {

            if (product1.equals(product)) {

                System.out.printf("%s is/are already in your shopping cart!\n", product.getName());
                return true;

            }

        }

        return false;

    }

    // Keegan - A method used to send customers into a loop where they can inspect all registered sellers and their respective stats

    public static void seeSellersLoop(Customer customer, Scanner scanner) {

        while (true) {

            System.out.print(SEE_SELLERS_PROMPT); // 1. List seller stores, 2. Contact sellers, 3. Return to main page
            int seeSellersPromptChoice = Market.validatePromptInput(3, scanner);

            switch (seeSellersPromptChoice) {

                case 1 -> listSellerStores(customer, scanner);

                case 2 -> messageSellerInterface(customer, scanner);

                case 3 -> {


                    return;


                }

            }

        }

    }

    private static void messageSellerInterface(Customer customer, Scanner scanner) {

        ArrayList<Seller> allSellers = Seller.returnAllSellers();

        if (allSellers.isEmpty()) {

            System.out.println("There are currently no contactable sellers");
            return;

        }

        System.out.println("Here is a list of a contactable sellers");

        int counter = 1;
        for (Seller seller : allSellers) {

            System.out.printf("%d. %s\n", counter, seller.getUsername());
            counter++;

        }

        System.out.print(
                """
                What would you like to do?
                1. Message a seller
                2. Return to see sellers
                """
        );
        int contactSellerChoice = Market.validatePromptInput(2, scanner);

        switch (contactSellerChoice) {

            case 1 -> {


                System.out.println("Which seller would you like to contact? (#)");
                int intSelectedSeller = Market.validatePromptInput(allSellers.size(), scanner);
                Seller selectedSeller = allSellers.get(intSelectedSeller - 1);

                messageSeller(selectedSeller, customer, scanner);


            }

            case 2 -> {

            }

        }

    }

    private static void messageSeller(Seller selectedSeller, Customer customer, Scanner scanner) {

        System.out.printf("Enter your message to seller %s: \n", selectedSeller.getUsername());
        String customerMessage = scanner.nextLine();

        try (PrintWriter pw = new PrintWriter(new FileWriter(Market.CUSTOMER_MESSAGES, true), true)) {

            // customerEmail,customerUsername,sellerEmail,message
            pw.println(String.format("%s,%s,%s,%s", customer.getEmail(), customer.getUsername(), selectedSeller.getEmail(), customerMessage));

            System.out.println("Message successfully sent");

        } catch (IOException e) {

            System.out.println("Failed to store message");

        }

    }

    private static void listSellerStores(Customer customer, Scanner scanner) {

        ArrayList<Seller> allSellers = Seller.returnAllSellers();

        if (allSellers.isEmpty()) {

            System.out.println("There are currently no registered sellers");
            return;

        }

        ArrayList<Store> allStores = new ArrayList<>();

        for (Seller seller : allSellers) {

            allStores.addAll(seller.getStores());

        }

        if (allStores.isEmpty()) {

            System.out.println("There are currently no seller registered stores");
            return;

        }

        while (true) {

            System.out.print(LIST_SELLER_STORES_PROMPT); // 1. See stores by products sold, 2. See stores by previous account purchases, 3. Return to see sellers
            int listSellerStoresPromptChoice = Market.validatePromptInput(3, scanner);

            switch (listSellerStoresPromptChoice) {
                case 1 -> { // By products sold


                    System.out.print(
                            """
                            What would you like to do?
                            1. Sort by products sold high to low
                            2. Sort by products sold low to high
                            3. Sort by none
                            """
                    );
                    int sortStoresPromptChoice = Market.validatePromptInput(3, scanner);

                    switch (sortStoresPromptChoice) {
                        case 1 -> { // high to low


                            ArrayList<Store> sortedStores = Store.sortByProductsSold(allStores, "high");

                            for (Store store : sortedStores) {

                                System.out.printf("Seller: %s, Store: %s, Products Sold: %d\n", store.getSellerOwner().getUsername(), store.getName(), store.getProductsSold());

                            }


                        }
                        case 2 -> { // low to high


                            ArrayList<Store> sortedStores = Store.sortByProductsSold(allStores, "low");

                            for (Store store : sortedStores) {

                                System.out.printf("Seller: %s, Store: %s, Products Sold: %d\n", store.getSellerOwner().getUsername(), store.getName(), store.getProductsSold());

                            }


                        }
                        case 3 -> { // no sort


                            for (Store store : allStores) {

                                System.out.printf("Seller: %s, Store: %s, Products Sold: %d\n", store.getSellerOwner().getUsername(), store.getName(), store.getProductsSold());

                            }

                        }

                    }


                }
                case 2 -> { // By products purchased

                    if (customer.getPreviouslyPurchasedProducts().isEmpty()) {

                        System.out.println("You have no previously purchased products");
                        return;
                    }


                    System.out.print(
                            """
                            What would you like to do?
                            1. Sort by products purchased high to low
                            2. Sort by products purchased low to high
                            3. Sort by none
                            """
                    );
                    int sortStoresPromptChoice = Market.validatePromptInput(3, scanner);

                    switch (sortStoresPromptChoice) {
                        case 1 -> { // high to low


                            ArrayList<PurchasedProduct> sortedPurchasedProducts = PurchasedProduct.sortByProductsPurchased(customer, "high");

                            for (PurchasedProduct purchasedProduct : sortedPurchasedProducts) {

                                Product product = purchasedProduct.getPurchasedProduct();
                                System.out.printf("Seller: %s, Store: %s, Products Purchased: %d\n", product.getStore().getSellerOwner().getUsername(), product.getStore().getName(), product.getStore().getProductsSold());

                            }


                        }
                        case 2 -> { // low to high


                            ArrayList<PurchasedProduct> sortedPurchasedProducts = PurchasedProduct.sortByProductsPurchased(customer, "low");

                            for (PurchasedProduct purchasedProduct : sortedPurchasedProducts) {

                                Product product = purchasedProduct.getPurchasedProduct();
                                System.out.printf("Seller: %s, Store: %s, Products Purchased: %d\n", product.getStore().getSellerOwner().getUsername(), product.getStore().getName(), product.getStore().getProductsSold());

                            }


                        }
                        case 3 -> { // none


                            ArrayList<PurchasedProduct> purchasedProducts = customer.getPreviouslyPurchasedProducts();

                            for (PurchasedProduct purchasedProduct : purchasedProducts) {

                                Product product = purchasedProduct.getPurchasedProduct();
                                System.out.printf("Seller: %s, Store: %s, Products Purchased: %d\n", product.getStore().getSellerOwner().getUsername(), product.getStore().getName(), product.getStore().getProductsSold());

                            }


                        }

                    }

                }
                case 3 -> {


                    return;


                }

            }

        }

    }


    // a method that allows a user to view their myAccount page
    public static void myAccount(Customer customer, Scanner scanner) {

        boolean inMyAccount = true;

        while (inMyAccount) {

            System.out.print(MY_ACCOUNT_PROMPT); // 1. View my previously purchased products 2. View my shopping cart 3. Return
            int myAccountInput = Market.validatePromptInput(3, scanner);

            switch (myAccountInput) {

                case 1 -> {


                    ArrayList<String> customerPurchases = Customer.returnCustomerPurchases(customer);

                    if (customerPurchases.isEmpty()) {

                        System.out.println("You have not purchased any products yet!");
                        return;

                    }

                    System.out.println("Previously Purchased Products: ");
                    ArrayList<String> purchaseLines = Customer.printPurchaseLines(customerPurchases);

                    System.out.print(PURCHASED_PRODUCTS_PROMPT); // 1. Export purchase history to CSV (file must exist) 2. Return
                    int purchasedProductsInput= Market.validatePromptInput(2, scanner);

                    switch (purchasedProductsInput) {

                        case 1 -> {


                            String fileName = customer.getUsername() + "_purchase_history.txt";
                            Customer.exportPurchasedProducts(purchaseLines, fileName);
                            return;


                        }

                        case 2 -> {



                        }

                    }

                }

                case 2 -> {

                    boolean inShoppingCart = true;

                    while (inShoppingCart) {

                        if (customer.getShoppingCart().getProductList().isEmpty()) {

                            System.out.println("There are no products in your shopping cart!");
                            break;

                        }

                        System.out.println("Products in shopping cart: ");
                        int counter = 1;
                        for (Product product : customer.getShoppingCart().getProductList()) {

                            System.out.println(product.getStore().getSellerOwner().getUsername());
                            System.out.print(counter + ". ");
                            System.out.print(product.productLine());
                            counter++;

                        }

                        System.out.print(SHOPPING_CART_PROMPT);  // 1. Checkout 2. Remove Product 3. Return to my account
                        int shoppingCartChoice = Market.validatePromptInput(3, scanner);

                        switch (shoppingCartChoice) {

                            case 1 -> {


                                Customer.checkOut(customer, scanner);
                                return;


                            }

                            case 2 -> Customer.removeFromShoppingCart(customer, scanner);

                            case 3 -> {

                                inShoppingCart = false;

                            }

                        }

                    }

                }

               case 3 -> inMyAccount = false;

            }

        }

    }

    private static void removeFromShoppingCart(Customer customer, Scanner scanner) {

        System.out.println("Which product would you like to remove? (#)");
        int productSelected = Market.validatePromptInput(customer.getShoppingCart().getProductList().size(), scanner);
        customer.getShoppingCart().getProductList().remove(productSelected - 1);
        Customer.updateCustomerData(customer);
        System.out.println("Product successfully removed from shopping cart");

    }

    private static void checkOut(Customer customer, Scanner scanner) {

        ShoppingCart customerShoppingCart = customer.getShoppingCart();

        for (Product product : customerShoppingCart.getProductList()) {
            System.out.printf("%s:\n", product.getName());
            Customer.purchaseProduct(customer, product, scanner);
        }

        customerShoppingCart.getProductList().clear();

    }

    private static void exportPurchasedProducts(ArrayList<String> purchaseLines, String fileName) {

        try (PrintWriter pw = new PrintWriter(new FileWriter(fileName, true), true)) {

            for (String line : purchaseLines) {

                pw.println(line);

            }

            System.out.println("Purchased products successfully exported");

        } catch (IOException e) {

            System.out.println("Error writing to export file");

        }
    }

    private static ArrayList<String> printPurchaseLines(ArrayList<String> customerPurchases) {
        ArrayList<String> purchaseLines = new ArrayList<>();
        for (String line : customerPurchases) {
            String[] purchaseData = line.split(",");
            // customerEmail,sellerEmail,customerUsername,sellerUsername,productName,productDescription,storeName,productPrice,quantityPurchased,amountSpent
            String productName = purchaseData[4];
            String productDescription = purchaseData[5];
            String sellerUsername = purchaseData[3];
            String storeName = purchaseData[6];
            double productPrice = Double.parseDouble(purchaseData[7]);
            int quantityPurchased = Integer.parseInt(purchaseData[8]);
            double amountSpent = Double.parseDouble(purchaseData[9]);
            String purchaseLine = String.format("Product: %s, Description: %s, Seller: %s, Store: %s, Price: $%.2f, Amount Purchased: %d, Amount Spent: $%.2f", productName, productDescription, sellerUsername, storeName, productPrice, quantityPurchased, amountSpent);
            purchaseLines.add(purchaseLine);
            System.out.println(purchaseLine);
        }
        return purchaseLines;
    }


    // Keegan - A sort products method that returns back products sorted in the user requested manner
  
    public static void searchForProducts(Customer customer, Scanner scanner) {

        ArrayList<Product> allProducts = Seller.getAllProducts();

        if (allProducts.isEmpty()) {

            System.out.println("There are currently no seller listed products");
            return;

        }

        boolean searched = true;

        while (true) {

            ArrayList<Product> searchedProducts = new ArrayList<>();

            System.out.print(SEARCH_PROMPT); // 1. Sort by name, 2. Sort by store, 3. Sort by description, 4. Return to main page
            int searchChoice = Market.validatePromptInput(4, scanner);

            switch (searchChoice) {
                case 1 -> {

                    System.out.println("Please enter a product name: ");
                    String searchedProductName = scanner.nextLine().toLowerCase();


                    for (Product product : allProducts) {
                        String productName = product.getName().toLowerCase();
                        if (productName.contains(searchedProductName)) {
                            searchedProducts.add(product);
                        }
                    }


                }
                case 2 -> {


                    System.out.println("Please enter a store name: ");
                    String searchedStoreName = scanner.nextLine().toLowerCase();

                    for (Product product : allProducts) {
                        String productStoreName = product.getStore().getName().toLowerCase();
                        if (productStoreName.contains(searchedStoreName)) {
                            searchedProducts.add(product);
                        }
                    }


                }
                case 3 -> {


                    System.out.println("Please enter a product description: ");
                    String searchedDescription = scanner.nextLine().toLowerCase();

                    for (Product product : allProducts) {
                        String productDescription = product.getDescription().toLowerCase();
                        if (productDescription.contains(searchedDescription)) {
                            searchedProducts.add(product);
                        }
                    }


                }
                case 4 -> {


                    return;


                }

            }

            if (searchedProducts.isEmpty()) {
                System.out.println("There are currently no products that match your description.");
                continue;
            }

            System.out.print(SORT_PROMPT); // 1. See all listings, 2. Sort listings
            int sortChoice = Market.validatePromptInput(2, scanner);

            switch (sortChoice) {
                case 1 -> listingPage(searchedProducts, scanner, customer, searched);

                case 2 -> {


                    ArrayList<Product> sortedSearchedProducts = Product.sortProducts(searchedProducts, scanner);
                    listingPage(sortedSearchedProducts, scanner, customer, searched);


                }

            }

        }

    }

    public static ArrayList<ShoppingCart> returnAllShoppingCarts() {
        ArrayList<ShoppingCart> shoppingCarts = new ArrayList<>();


        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(Market.CUSTOMER_DATA))) {
            ArrayList<Customer> customers = new ArrayList<>();
            Customer test;
            while (true) {

                try {
                    test = (Customer) ois.readObject();
                    customers.add(test);
                } catch (EOFException | ClassNotFoundException e) {
                    break;
                }

            }

            for (Customer customer : customers) {
                shoppingCarts.add(customer.getShoppingCart());
            }

            return shoppingCarts;

        } catch (EOFException e) {
            System.out.println("No customers currently stored");
            return shoppingCarts;
        } catch (IOException e) {
            System.out.println("Customer data file not found.");
            return shoppingCarts;
        }
    }

    // a method that allows a user to delete their customer account and data
    public static void removeCustomer(String email) {

        ArrayList<Customer> customerList = new ArrayList<>();

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(Market.CUSTOMER_DATA))) {

            Customer test;

            while (true) {

                try {

                    test = (Customer) ois.readObject();
                    customerList.add(test);

                } catch (EOFException | ClassNotFoundException e) {

                    break;

                }

            }

            int customerIndex = -1;
            for (int i = 0; i < customerList.size(); i++) {

                String sellerEmail = customerList.get(i).getEmail();
                if (email.equals(sellerEmail)) {

                    customerIndex = i;
                    break;

                }

            }

            if (customerIndex != -1) {

                System.out.println("Customer removed");
                customerList.remove(customerIndex);

            }

            if (!customerList.isEmpty()) {

                try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(Market.CUSTOMER_DATA))) {

                    for (Customer customer : customerList) {

                        oos.writeObject(customer);

                    }

                }

            } else {

                new FileOutputStream(Market.CUSTOMER_DATA).close();

            }

        } catch (EOFException e) {

            System.out.println("Customer removed");

        } catch (IOException e) {

            System.out.println("Customer data read error.");

        }

    }

    public ShoppingCart getShoppingCart() {
        return this.shoppingCart;
    }

}
