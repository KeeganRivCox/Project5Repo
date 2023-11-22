import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

/**
 Project 4 -- Product Class

 In our project, we've developed a comprehensive online market application with various classes
 collaborating to provide a rich and dynamic user experience. We designed this system to allow
 users to create accounts, navigate through a variety of features, and interact with the market's
 extensive functionalities.

 @author Adeetya, Jordan, Keegan, Natalie, and Robert, Lab Section 39

 @version November 13th, 2023


 */

public class Product implements Serializable {
    private String name;
    private String description;
    private int quantity;
    private int quantitySold;
    private double price;
    double revenueGenerated;
    private final Store store;
  
    // Keegan - Prompt that pops up when asking to sort
    private static final String SORT_PROMPT =
            """
            How would you like to sort?
            1. Price - high to low
            2. Quantity - high to low
            3. Price - low to high
            4. Quantity - low to high
            """;

    public Product(String name, double price, int quantity, String description, Store store) {
        this.name = name;
        this.description = description;
        this.quantity = quantity;
        this.price = price;
        this.quantitySold = 0;
        this.revenueGenerated = 0;
        this.store = store;
    }

    // creates a product to be put in a Store
    public static Product generateProduct(Store store, Scanner scanner, int i) {

        System.out.println("Enter the name of product " + i);
        String productName = scanner.nextLine();

        System.out.println("Enter the price of product " + i);
        double productPrice = Market.validateDouble(scanner);

        System.out.println("Enter the quantity of product " + i);
        int productQuantity = Market.validatePromptInput(Integer.MAX_VALUE, scanner);

        System.out.println("Enter the description of product " + i);
        scanner.nextLine();
        String productDescription = scanner.nextLine();

        return new Product(productName, productPrice, productQuantity, productDescription, store);
    }

    // prints the product page with the product and its details in a certain format
    public static void printProductPage(Product product) {
        String sellerName = product.getStore().getSellerOwner().getUsername();
        String storeName = product.getStore().getName();
        System.out.printf("Seller: %s\nStore: %s\nProduct Name: %s\nDescription: %s\nPrice: $%.2f\nQuantity Available: %d\n", sellerName, storeName, product.getName(), product.getDescription(), product.getPrice(), product.getQuantity());
    }

    // sorts the products by price and quantity: high to low and low to high
    public static ArrayList<Product> sortProducts(ArrayList<Product> allProducts, Scanner scanner) {

        System.out.print(SORT_PROMPT); // 1. Price - High to low, 2. Quantity - High to low, 3. Price - Low to high, 4. Quantity - Low to high
        int sortChoice = Market.validatePromptInput(4, scanner);

        switch (sortChoice) { // sortType: high = high to low, low = low to high
            case 1 -> {


                return Product.sortByPrice(allProducts, "high");


            }
            case 2 -> {


                return Product.sortByQuantity(allProducts, "high");


            }
            case 3 -> {


                return Product.sortByPrice(allProducts, "low");


            }
            case 4 -> {


                return Product.sortByQuantity(allProducts, "low");


            }
        }
        return allProducts;
    }

    // sorts the list of products by price
    private static ArrayList<Product> sortByPrice(ArrayList<Product> allProducts, String sortType) {

        Comparator<Product> priceComparator = (c1, c2) -> (int) (c1.price - c2.price);

        ArrayList<Product> sortedProducts = allProducts;

        switch (sortType) {
            case "high" -> {


                sortedProducts.sort(Collections.reverseOrder(priceComparator));
                return sortedProducts;


            }
            case "low" -> {


                sortedProducts.sort(priceComparator);
                return sortedProducts;


            }
            default -> {


                return sortedProducts;


            }
        }
    }

    // sorts the list of products by quantity
    private static ArrayList<Product> sortByQuantity(ArrayList<Product> allProducts, String sortType) {

        Comparator<Product> quantityComparator = Comparator.comparingInt(c -> c.quantity);

        ArrayList<Product> sortedProducts = allProducts;

        switch (sortType) {
            case "high" -> {


                sortedProducts.sort(Collections.reverseOrder(quantityComparator));
                return sortedProducts;


            }
            case "low" -> {


                sortedProducts.sort(quantityComparator);
                return sortedProducts;


            }
            default -> {


                return sortedProducts;


            }

        }

    }

    public static ArrayList<Product> sortByQuantitySold(ArrayList<Product> products, String sortType) {

        Comparator<Product> quantitySoldComparator = Comparator.comparingInt(c -> c.quantitySold);

        ArrayList<Product> sortedProducts = products;

        switch (sortType) {
            case "high" -> {


                sortedProducts.sort(Collections.reverseOrder(quantitySoldComparator));
                return sortedProducts;


            }
            case "low" -> {


                sortedProducts.sort(quantitySoldComparator);
                return sortedProducts;


            }
            default -> {


                return sortedProducts;


            }

        }

    }

    public static ArrayList<Product> sortByRevenueGenerated(ArrayList<Product> products, String sortType) {

        Comparator<Product> revenueGeneratedComparator = Comparator.comparingDouble(c -> c.revenueGenerated);

        ArrayList<Product> sortedProducts = products;

        switch (sortType) {
            case "high" -> {


                sortedProducts.sort(Collections.reverseOrder(revenueGeneratedComparator));
                return sortedProducts;


            }
            case "low" -> {


                sortedProducts.sort(revenueGeneratedComparator);
                return sortedProducts;


            }
            default -> {


                return sortedProducts;


            }

        }

    }

    public void updateProduct(int quantityToPurchase) {
      
        int quantityAvailable = this.quantity;
        quantityAvailable -= quantityToPurchase;
        this.quantity = quantityAvailable;
        this.revenueGenerated += quantityToPurchase * this.price;
        this.quantitySold += quantityToPurchase;
      
    }

    // formats the product details
    public String productLine() {
        return String.format("Product: %s, Description: %s, Seller: %s, Store: %s, Price: $%.2f, Quantity Available: %d\n", this.name, this.description, this.getStore().getSellerOwner().getUsername(), this.getStore().getName(), this.getPrice(), this.getQuantity());
    }

    // lists all the products
    public static void listProducts(ArrayList<Product> products) {
        int counter = 1;
        for (Product product : products) {
            System.out.printf("%d. ", counter);
            System.out.print(product.productLine());
            counter++;
        }
    }

    // returns the product name
    public String getName() {
        return name;
    }

    // returns the product description
    public String getDescription() {
        return description;
    }

    // returns the quantity of a product
    public int getQuantity() {
        return quantity;
    }

    // returns the price of a product
    public double getPrice() {
        return price;
    }

    // returns the quantity sold of a product
    public int getQuantitySold() {
        return quantitySold;
    }

    // returns the revenue generated from a product
    public double getRevenueGenerated() {
        return this.revenueGenerated;
    }

    // returns the store the product is located at
    public Store getStore() {
        return this.store;
    }

    // modifies the name of a product
    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    // modifies the quantity of a product
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    // modifies the price of a product
    public void setPrice(double price) {
        this.price = price;
    }


    // Keegan - A method to return if two products are equal

    public boolean equals(Product product) {
        return this.getStore().equals(product.getStore()) && this.getDescription().equals(product.getDescription()) && this.getPrice() == product.getPrice() && this.getQuantity() == product.getQuantity();
    }
}

